package kbuni_question.domain.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ImgRepository extends JpaRepository<ImgEntity, Integer> {

    @Query(value = "select * from img where pno = :pno", nativeQuery = true)
    Optional<ImgEntity> findbypno(@Param("pno") int pno);

}
