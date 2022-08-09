package kbuni_question.service;

import kbuni_question.domain.exam.*;
import kbuni_question.dto.ExamDto;
import kbuni_question.dto.ProblemDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;

@Service
public class ExamService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    TestinformationRepository testinformationRepository;

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    ImgRepository imgRepository;

    @Transactional
    public boolean saveexaminfo(ExamDto examDto) {

        //카테고리
        CategoryEntity categoryEntity = categoryRepository.findBycname(examDto.getCname()).get();

        System.out.println(categoryEntity.getCname());
        //학과
        DepartmentEntity departmentEntity = new DepartmentEntity();
        //학과 중복검사
        List<DepartmentEntity> departmentEntityList = departmentRepository.findAll();

        boolean pass = true;
        for(DepartmentEntity temp : departmentEntityList){
            if(temp.getDname().equals(examDto.getDname())){ // 학과가 존재한다면
                departmentEntity = temp;
                pass = false;
            }
        }
        if(pass){ // 학과가 존재하지않는다면
            departmentEntity = examDto.departmenttoentity();
            departmentEntity.setCategoryEntity(categoryEntity);
            departmentRepository.save(departmentEntity);

        }
        //시험지
        TestinformationEntity testinformationEntity = examDto.testinformationtoentity();
        testinformationEntity.setDepartmentEntity(departmentEntity);
        testinformationRepository.save(testinformationEntity);
        return true;
    }


    //시험정보리스트 출력
    public JSONArray getexamlist(int dno) {
        List<ExamDto> examDtos = new ArrayList<>();

        //시험지정보 모두 가져오기
        List<TestinformationEntity> testinformationEntityList = testinformationRepository.findbydno(dno);

        JSONArray jsonArray = new JSONArray();
        for(TestinformationEntity temp : testinformationEntityList){
            JSONObject object = new JSONObject();
            object.put("tno",temp.getTno());
            object.put("tname",temp.getTname());
            object.put("tquarter", temp.getTquarter());
            object.put("tyear", temp.getTyear());
            object.put("dno", temp.getDepartmentEntity().getDno());
            object.put("dname", temp.getDepartmentEntity().getDname());
            object.put("pnum", temp.getProblemEntityList().size());
            jsonArray.put(object);
        }
        return jsonArray;
    }

    // 학과리스트 가져오기
    public JSONArray getdlist(){
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();

        JSONArray jsonArray = new JSONArray();

        for(DepartmentEntity temp : departmentEntities){
            JSONObject object = new JSONObject();
            object.put("dno", temp.getDno());
            object.put("dname", temp.getDname());
            jsonArray.put(object);
        }

        return jsonArray;
    }
    
    //시험지정보 한개가져와서 리턴
    public JSONObject getinfo(int tno){
        TestinformationEntity testinformation = testinformationRepository.findById(tno).get();

        JSONObject object = new JSONObject();
        object.put("tno", testinformation.getTno());
        object.put("tname", testinformation.getTname());
        object.put("tyear", testinformation.getTyear());
        object.put("tquarter", testinformation.getTquarter());
        object.put("pcount", testinformation.getProblemEntityList().size());

        return object;
    }

    @Transactional
    //문제 저장
    public boolean saveproblem(ProblemDto problemDto, int tno){

        // pk값 test 가져오기
        TestinformationEntity testinformation = testinformationRepository.findById(tno).get();

        // 문제 옵션 치환
        problemDto.setPoption(problemDto.getPoption().replace("①",""));
        problemDto.setPoption(problemDto.getPoption().replace("②","_"));
        problemDto.setPoption(problemDto.getPoption().replace("③","_"));
        problemDto.setPoption(problemDto.getPoption().replace("④","_"));


        // problem 저장
        ProblemEntity problemEntity = problemDto.toentity();
        System.out.println(problemEntity.getPoption());
        problemRepository.save(problemEntity);

        // pk fk 연결
        problemEntity.setTestinformationEntity(testinformation);

        // 이미지 저장
        String uuidfile = null;
        for(MultipartFile file : problemDto.getPimg()){
            if(file.isEmpty()){ /* 이미지가 없다면 */
                System.out.println("없음!");
            }else{
                // 중복이름방지 난수 생성
                UUID uuid = UUID.randomUUID();
                uuidfile = uuid.toString();

                // 2. 경로 설정
                String dir = "C:\\Users\\gks01\\git\\previous_answer_site\\src\\main\\resources\\static\\examimg\\";
                String filepath = dir+uuidfile;

                try {
                    file.transferTo(new File(filepath));

                    //이미지 엔티티 생성
                    ImgEntity imgentity = ImgEntity.builder()
                            .imgurl(uuidfile)
                            .problemEntity(problemEntity)
                            .build();

                    // 이미지와 문제연결
                    problemEntity.getImgEntityList().add(imgentity);

                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }
        return true;
    }

    // 문제리스트가져오기
    public JSONArray getproblemlist(int tno){

        JSONArray jsonArray = new JSONArray();

        //문제리스트 db에서 가져오기
        List<ProblemEntity> problemEntityList = problemRepository.findallbytno(tno);

        for(ProblemEntity temp : problemEntityList){
            JSONObject object = new JSONObject();
            object.put("pno", temp.getPno() );
            object.put("pindex",temp.getPindex());
            object.put("pname", temp.getPname());
            object.put("panswer", temp.getPanswer());

            // 문제저장
            String[] option = temp.getPoption().split("_");
            System.out.println(Arrays.toString(option));
            JSONArray optionjsons = new JSONArray();
            for(int i = 0; i < option.length ; i++){
                if(option[i] != null) {
                    optionjsons.put(option[i]);
                }
            }
            object.put("poption", optionjsons);

            object.put("tno", temp.getTestinformationEntity().getTno());

            //이미지 저장
            JSONArray pimgjsons = new JSONArray();
            for(ImgEntity pimg : temp.getImgEntityList()) {
                pimgjsons.put(pimg.getImgurl());
            }
            object.put("pimg", pimgjsons);

            jsonArray.put(object);
        }

        return jsonArray;
    }

    // 문제삭제
    public boolean pdelete(int pno) {

        Optional<ImgEntity> imgEntity = imgRepository.findbypno(pno);
        if(imgEntity.isPresent()){
            ImgEntity entity = imgEntity.get();
            //이미지 삭제
            File file = new File("C:\\Users\\gks01\\git\\previous_answer_site\\src\\main\\resources\\static\\examimg\\"+entity.getImgurl());
            file.delete();
        }else{

        }

        problemRepository.deleteById(pno);

        return true;
    }

    // 한문제 가져오기
    public JSONObject getproblem(int pno){
        JSONObject object = new JSONObject();

        ProblemEntity problemEntity = problemRepository.findById(pno).get();

        object.put("pno", problemEntity.getPno() );
        object.put("pindex",problemEntity.getPindex());
        object.put("pname", problemEntity.getPname());
        object.put("panswer", problemEntity.getPanswer());

        // 문제저장
        String[] option = problemEntity.getPoption().split("_");
        System.out.println(Arrays.toString(option));
        JSONArray optionjsons = new JSONArray();
        for(int i = 0; i < option.length ; i++){
            if(option[i] != null) {
                optionjsons.put(option[i]);
            }
        }
        object.put("poption", optionjsons);

        object.put("tno", problemEntity.getTestinformationEntity().getTno());

        //이미지 저장
        JSONArray pimgjsons = new JSONArray();
        for(ImgEntity pimg : problemEntity.getImgEntityList()) {
            pimgjsons.put(pimg.getImgurl());
        }
        object.put("pimg", pimgjsons);

        return object;
    }

    @Transactional
    // 문제 수정 처리
    public boolean setproblem(ProblemDto problemDto) {
        ProblemEntity problemEntity = problemRepository.findById(problemDto.getPno()).get();

        System.out.println(problemEntity);

        problemEntity.setPanswer(problemDto.getPanswer());
        problemEntity.setPname(problemDto.getPname());
        problemEntity.setPoption(problemDto.getPoption());
        problemEntity.setPindex(problemDto.getPindex());
        /* 이미지 보류 */

        return true;

    }

    // 문제 제목가져오기 서비스
    public JSONObject gettitle(int tno){
        JSONObject object = new JSONObject();

        TestinformationEntity entity = testinformationRepository.findById(tno).get();

        object.put("tname", entity.getTname() +" | "+ entity.getTquarter() +" | "+ entity.getTyear());

        System.out.println("확인: " +  entity.getTname());
        return object;

    }

}
