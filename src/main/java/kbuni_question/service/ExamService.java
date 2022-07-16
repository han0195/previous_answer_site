package kbuni_question.service;

import kbuni_question.domain.exam.*;
import kbuni_question.dto.ExamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
