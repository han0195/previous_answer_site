package kbuni_question.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder
@Table(name = "test")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tno; // 시험지번호
    private String tname; // 시험지이름
    private int tgrade; // 학년
    private int tyear; // 기출연도
    private String tanswer; // 시험답지
    private String testof; //시험구분


    @OneToMany(mappedBy = "testEntity", cascade = CascadeType.ALL)
    @Builder.Default
    private List<TimgEntity> timgEntityList = new ArrayList<>(); // 시험지이미지리스트

    @ManyToOne
    @JoinColumn(name = "tcno")
    private TcategoryEntity tcategoryEntity; // 카테고리





}
