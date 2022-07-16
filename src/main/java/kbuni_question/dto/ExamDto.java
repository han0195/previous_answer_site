package kbuni_question.dto;

import kbuni_question.domain.exam.CategoryEntity;
import kbuni_question.domain.exam.DepartmentEntity;
import kbuni_question.domain.exam.TestinformationEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamDto {

    private String cname;
    private String dname;
    private String tname;
    private String tyear;
    private String tquarter;

    private List<ProblemDto> problemDtolist;


    //카테고리 entity 변환
    public CategoryEntity categorytoentity(){
        return CategoryEntity.builder()
                .cname(this.cname)
                .build();
    }

    //학과 entity 변환
    public DepartmentEntity departmenttoentity(){
        return DepartmentEntity.builder()
                .dname(this.dname)
                .build();
    }

    //시험 정보 entity 변환
    public TestinformationEntity testinformationtoentity(){
        return TestinformationEntity.builder()
                .tname(this.tname)
                .tyear(this.tyear)
                .tquarter(this.tquarter)
                .build();
    }






}
