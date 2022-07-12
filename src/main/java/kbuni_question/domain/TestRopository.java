package kbuni_question.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestRopository extends JpaRepository<TestEntity, Integer> {


    // 해당 카테고리와 일치하는 시험 뽑기
    @Query(value = "select * from test where tcno = :tcno and tgrade = :tgrade", nativeQuery = true)
    List<TestEntity> findBytcno(@Param("tcno") int tcno, @Param("tgrade") String tgrade);
}
