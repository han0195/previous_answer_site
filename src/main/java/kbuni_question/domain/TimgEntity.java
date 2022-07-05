package kbuni_question.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "timg")
public class TimgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timgno; // 이미지번호
    private String turl; // 이미지 주소
    private int imgno; // 이미지 순서

    @ManyToOne
    @JoinColumn(name = "tno")
    private TestEntity testEntity; // 소속 시험지


}
