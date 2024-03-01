package com.demo.demospringdatajpa.repo;

import com.demo.demospringdatajpa.dto.MemberDto;
import com.demo.demospringdatajpa.entity.Member;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.aspectj.weaver.MemberImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepo extends JpaRepository<Member, Long>, MemberRepoCustom{

    List<com.demo.demospringdatajpa.entity.Member> findByNameAndAgeGreaterThan(String name, int age);

    //    @Query(value = "select m from Member m where m.name = :name")
    List<com.demo.demospringdatajpa.entity.Member> findByName(@Param("name") String name);

    @Query(value = "select m from Member m where m.name = :name and m.age = :age")
    List<com.demo.demospringdatajpa.entity.Member> findUser(@Param("name") String name, @Param("age") int age);

    @Query("select m.name from Member m")
    List<String> findNameList();

    @Query("select new com.demo.demospringdatajpa.dto.MemberDto(m.id, m.name, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.name in :names")
    List<com.demo.demospringdatajpa.entity.Member> findByNames(@Param("names") List<String> names);

    List<com.demo.demospringdatajpa.entity.Member> findListByName(String name);

    com.demo.demospringdatajpa.entity.Member findMemberByName(String name);

    Optional<com.demo.demospringdatajpa.entity.Member> findOptionalByName(String name);

    @Query(value = "select m from Member m left join m.team t",
            countQuery = "select count(m) from Member m")
    Page<com.demo.demospringdatajpa.entity.Member> findByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Query("select m from Member m left join fetch m.team")
    List<com.demo.demospringdatajpa.entity.Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<com.demo.demospringdatajpa.entity.Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<com.demo.demospringdatajpa.entity.Member> findMemberEntityGraph();

    @EntityGraph(attributePaths = {"team"})
    List<com.demo.demospringdatajpa.entity.Member> findEntityGraphGByName(@Param("name") String name);

    @EntityGraph("Member.all")
    List<com.demo.demospringdatajpa.entity.Member> findEntityGraphByName(@Param("name") String name);

    @QueryHints(value = @QueryHint(name="org.hibernate.readOnly", value = "true"))
    com.demo.demospringdatajpa.entity.Member findReadOnlyByName(String name);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<com.demo.demospringdatajpa.entity.Member> findLockByName(String name);
}