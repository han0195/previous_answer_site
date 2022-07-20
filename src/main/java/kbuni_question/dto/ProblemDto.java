package kbuni_question.dto;

import kbuni_question.domain.exam.ProblemEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProblemDto {

    private int pno;
    private int pindex;
    private String panswer;
    private String pname;
    private String poption;

    private List<MultipartFile> pimg;

    public ProblemEntity toentity(){
        return ProblemEntity.builder()
                .pno(this.pno)
                .pindex(this.pindex)
                .panswer(this.panswer)
                .pname(this.pname)
                .poption(this.poption)
                .build();
    }
}
