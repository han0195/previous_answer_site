package kbuni_question.dto;

import kbuni_question.domain.exam.ProblemEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProblemDto {

    private int pno;
    private String panswer;
    private String pname;
    private String poption;

    private List<MultipartFile> pimg;

    public ProblemEntity toentity(){
        return ProblemEntity.builder()
                .pno(this.pno)
                .panswer(this.panswer)
                .pname(this.pname)
                .poption(this.poption)
                .build();
    }
}
