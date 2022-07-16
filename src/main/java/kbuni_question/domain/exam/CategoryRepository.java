package kbuni_question.domain.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    @Query(value = "select * from categry where cname = :cname", nativeQuery = true)
    Optional<CategoryEntity> findBycname(@Param("cname") String cname);

}
