package kbuni_question.domain.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestinformationRepository extends JpaRepository<TestinformationEntity, Integer> {

    @Query(value = "select * from testinformation where dno = :dno", nativeQuery = true)
    List<TestinformationEntity> findbydno(@Param("dno") int dno);
}
