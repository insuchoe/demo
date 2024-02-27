package com.demo.demospringdatajpa.repo;

import com.demo.demospringdatajpa.dto.MemberDto;
import com.demo.demospringdatajpa.entity.Member;
import com.demo.demospringdatajpa.entity.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepoTest {
    @Autowired
    MemberRepo memberRepo;

    @Test
    public void testMember() {
        Member nameA = new Member("name a");
        Member save = memberRepo.save(nameA);

        Member member = memberRepo.findById(save.getId()).get();
        Assertions.assertThat(nameA.getId()).isEqualTo(save.getId());
        Assertions.assertThat(nameA.getName()).isEqualTo(save.getName());
        Assertions.assertThat(nameA).isEqualTo(save);
    }


    @Test
    void findByNameAndAgeGreaterThan() {
        Team teamA = new Team("team a");
        Member memberA = new Member("member a", 30);
        Member memberB = new Member("member a", 35);
        memberRepo.save(memberA);
        memberRepo.save(memberB);

        memberRepo.flush();

        List<Member> members = memberRepo.findByNameAndAgeGreaterThan("member a", 33);
        Assertions.assertThat(members.size()).isEqualTo(1);


    }


    @Test
    void findByName() {
        Member memberA = new Member("member a", 30);
        Member memberB = new Member("member b", 35);
        memberRepo.save(memberA);
        memberRepo.save(memberB);

        List<Member> queryForMembers = memberRepo.findByName("member a");
        int countForMembers = queryForMembers.size();

        Assertions.assertThat(countForMembers).isEqualTo(1);
    }

    @Test
    void findUser() {
        Member memberA = new Member("member a", 30);
        Member memberB = new Member("member b", 35);
        memberRepo.save(memberA);
        memberRepo.save(memberB);

        List<Member> queryForMember = memberRepo.findUser("member a", 30);
        int countForMember = queryForMember.size();

        Assertions.assertThat(countForMember).isEqualTo(1);
    }

    @Autowired TeamRepo teamRepo;
    @Test
    void findMemberDto() {

        Team teamA = new Team("team a");
        Member memberA = new Member("member a", 30);
        Member memberB = new Member("member b", 35);
        memberA.setTeam(teamA);
        memberB.setTeam(teamA);

        teamRepo.save(teamA);
        memberRepo.save(memberA);
        memberRepo.save(memberB);

        List<MemberDto> memberDtos = memberRepo.findMemberDto();
        System.out.println("memberDtos = " + memberDtos);
    }

    @Test
    void findByNames() {

        Member memberA = new Member("member a", 30);
        Member memberB = new Member("member b", 35);

        memberRepo.save(memberA);
        memberRepo.save(memberB);

        List<Member> byNames = memberRepo.findByNames(Arrays.asList("member a", "member b"));
        System.out.println("byNames = " + byNames);
    }
}