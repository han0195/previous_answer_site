package kbuni_question.dto;

import kbuni_question.domain.board.ErrorboardEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private int eno;
    private String econtent;
    private int Solution;

    public ErrorboardEntity toentity(){
        return ErrorboardEntity.builder()
                .eno(this.getEno())
                .econtent(this.getEcontent())
                .Solution(this.getSolution())
                .build();
    }

}
