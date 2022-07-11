package kbuni_question.dto;

import kbuni_question.domain.TestEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TestDto {

    private int tno; // 시험지번호
    private String tname; // 시험지이름
    private String tgrade; // 학년
    private String tyear; // 기출연도
    private String tanswer; // 시험답지
    private String testof; //시험구분

    private List<MultipartFile> timg; // 시험지이미지

    private String tcname; // 시험카테고리


    //entity 변환
    public TestEntity toentity(){
        return TestEntity.builder()
                .tno(this.tno)
                .tname(this.tname)
                .tgrade(this.tgrade)
                .tanswer(this.tanswer)
                .tyear(this.tyear)
                .testof(this.testof)
                .timgEntityList(new ArrayList<>())
                .build();
    }

}
