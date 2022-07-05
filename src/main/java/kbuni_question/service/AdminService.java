package kbuni_question.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import kbuni_question.domain.*;
import kbuni_question.dto.TestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class AdminService {

    @Autowired
    TestRopository testRopository;
    @Autowired
    TimgRopository timgRopository;
    @Autowired
    TcategoryRepository tcategoryRepository;

    // 1. 시험지 등록
    public boolean inserttest(TestDto testDto) {

        //카테고리 저장
        TcategoryEntity tcategoryEntity = new TcategoryEntity(); // 카테고리 entity객체
        boolean pass = false; // 카테고리존재여부
        List<TcategoryEntity> tcategoryEntities = tcategoryRepository.findAll();
        for (TcategoryEntity temp : tcategoryEntities) {
            if (temp.equals(testDto.getTcname())) { // 카테고리가 존재하면
                tcategoryEntity = tcategoryRepository.findById(temp.getTcno()).get();
                pass = true;
                break;
            }
        }
        if (!pass) {// 카테고리가 존재하지않으면
            tcategoryEntity = TcategoryEntity.builder()
                    .tcname(testDto.getTcname())
                    .build();
            tcategoryRepository.save(tcategoryEntity);
        }

        // test db 저장
        TestEntity testEntity = testDto.toentity();
        testRopository.save(testEntity);

        tcategoryEntity.getTestEntities().add(testEntity); // 카테고리의 엔티티추가
        testEntity.setTcategoryEntity(tcategoryEntity); // 시험지의 카테고리 추가


        int i = 0; // 이미지 순서 식별
        for (MultipartFile file : testDto.getTimg()) {
            // test 이미지 저장
            String uuidfile = null;
            UUID uuid = UUID.randomUUID(); // 난수생성
            // 이미지 에러시 여기 확인
            uuidfile = uuid.toString() +"_"+ file.getOriginalFilename().replaceAll("_","-");
            // 이미지 저장경로
            String dir = "C:\\Users\\gks01\\git\\Korea_Broadcasting_University\\src\\main\\resources\\static\\testimg\\";
            String filepath = dir + uuidfile;
            try {
                file.transferTo(new File(filepath)); // 파일업로드
                System.out.println(i);
                TimgEntity timgEntity = TimgEntity.builder()
                        .turl(uuidfile) // 이미지 저장 이름저장
                        .imgno(i)
                        .build();

                timgRopository.save(timgEntity); // 이미지저장
                timgEntity.setTestEntity(testEntity); // 이미지 카테고리 연결 ??근대 최조한개만됨
                testEntity.getTimgEntityList().add(timgEntity); // 이미지 추가
            } catch (Exception e) {
                System.out.println("저장 에러");
            }
            i++;
        }


        return true;
    }
}
