package kbuni_question.domain.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProblemRepository extends JpaRepository<ProblemEntity, Integer> {

    @Query(value = "select * from kbuniquestion.problem where tno = :tno order by pindex asc", nativeQuery = true)
    List<ProblemEntity> findallbytno(@Param("tno") int tno);

}
