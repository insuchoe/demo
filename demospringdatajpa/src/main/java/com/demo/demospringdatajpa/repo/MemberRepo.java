package com.demo.demospringdatajpa.repo;

import com.demo.demospringdatajpa.dto.MemberDto;
import com.demo.demospringdatajpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepo extends JpaRepository<Member, Long> {

    List<Member> findByNameAndAgeGreaterThan(String name, int age);

//    @Query(value = "select m from Member m where m.name = :name")
    List<Member> findByName(@Param("name") String name);

    @Query(value = "select m from Member m where m.name = :name and m.age = :age")
    List<Member> findUser(@Param("name") String name, @Param("age") int age);

    @Query("select m.name from Member m")
    List<String> findNameList();

    @Query("select new com.demo.demospringdatajpa.dto.MemberDto(m.id, m.name, t.name) from Member m join m.team t")
    List<MemberDto>  findMemberDto();

    @Query("select m from Member m where m.name in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    List<Member> findListByName(String name);

    Member findMemberByName(String name);

    Optional<Member> findOptionalByName(String name);
}
