package kbuni_question.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    Optional< MemberEntity> findBymid( String mid ); // select  sql 문법 없이 검색 메소드 생성

    @Query(value = "select * from member where mname = :mname", nativeQuery = true)
    Optional<MemberEntity> findBymnames(@Param("mname") String mname);

}
