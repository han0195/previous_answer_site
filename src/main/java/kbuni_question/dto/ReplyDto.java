package kbuni_question.dto;


import kbuni_question.domain.board.ReplyEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyDto {

    private int rno;
    private String rcontent;
    private int rindex;

    public ReplyEntity replytoentity() {
        return ReplyEntity.builder()
                .rindex(this.rindex)
                .rcontent(this.rcontent)
                .build();
    }

}
