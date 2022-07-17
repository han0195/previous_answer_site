package kbuni_question.service;

import kbuni_question.domain.exam.*;
import kbuni_question.dto.ExamDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    TestinformationRepository testinformationRepository;


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

}
