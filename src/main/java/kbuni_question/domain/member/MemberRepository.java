package kbuni_question.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    @Query(value = "select * from member where mname = :mname", nativeQuery = true)
    Optional<MemberEntity> findBymname(@Param("manme") String mname);

}
