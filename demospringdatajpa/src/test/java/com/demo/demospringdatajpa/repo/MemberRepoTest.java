package com.demo.demospringdatajpa.repo;

import com.demo.demospringdatajpa.dto.MemberDto;
import com.demo.demospringdatajpa.entity.Member;
import com.demo.demospringdatajpa.entity.Team;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepoTest {
    @Autowired
    MemberRepo memberRepo;
    @Autowired
    EntityManager entityManager;

    @Test
    public void testMember() {
        Member nameA = new Member("name a");
        Member save = memberRepo.save(nameA);

        Member member = memberRepo.findById(save.getId()).get();
        assertThat(nameA.getId()).isEqualTo(save.getId());
        assertThat(nameA.getName()).isEqualTo(save.getName());
        assertThat(nameA).isEqualTo(save);
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
        assertThat(members.size()).isEqualTo(1);


    }


    @Test
    void findByName() {
        Member memberA = new Member("member a", 30);
        Member memberB = new Member("member b", 35);
        memberRepo.save(memberA);
        memberRepo.save(memberB);

        List<Member> queryForMembers = memberRepo.findByName("member a");
        int countForMembers = queryForMembers.size();

        assertThat(countForMembers).isEqualTo(1);
    }

    @Test
    void findUser() {
        Member memberA = new Member("member a", 30);
        Member memberB = new Member("member b", 35);
        memberRepo.save(memberA);
        memberRepo.save(memberB);

        List<Member> queryForMember = memberRepo.findUser("member a", 30);
        int countForMember = queryForMember.size();

        assertThat(countForMember).isEqualTo(1);
    }

    @Autowired
    TeamRepo teamRepo;

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


    @Test
    public void paging() {
        Member memberA = new Member("member a", 10);
        Member memberB = new Member("member b", 10);
        Member memberC = new Member("member c", 10);
        Member memberD = new Member("member d", 10);
        Member memberE = new Member("member e", 10);

        memberRepo.save(memberA);
        memberRepo.save(memberB);
        memberRepo.save(memberC);
        memberRepo.save(memberD);
        memberRepo.save(memberE);

//        given
        int age = 10;
        int offset = 0;
        int limit = 3;

//        when
        PageRequest pageCondition = PageRequest.of(offset, limit, Sort.Direction.DESC, "name");
        Page<Member> pageMemberByAge = memberRepo.findByAge(age, pageCondition);

        Page<MemberDto> toDtoMap = pageMemberByAge.map(MemberRepoTest::getMemberDto);

//        then
        List<Member> pageMemberContent = pageMemberByAge.getContent();
        long totalMemberCount = pageMemberByAge.getNumberOfElements();

        int memberSizePerPage = pageMemberContent.size();
        assertThat(totalMemberCount).isEqualTo(memberSizePerPage);
        assertThat(pageMemberByAge.getNumber()).isEqualTo(0);
        assertThat(pageMemberByAge.getPageable().getPageNumber()).isEqualTo(2);
        assertThat(pageMemberByAge.isFirst()).isTrue();
        assertThat(pageMemberByAge.hasNext()).isTrue();
    }

    public static MemberDto getMemberDto(Member member) {
        return new MemberDto(member.getId(), member.getName(), member.getTeam().getName());
    }

    @Test
    void bulkAgePlus() {
        loadMemberData();
        int updateCount = memberRepo.bulkAgePlus(20);
//        entityManager.flush();
//        entityManager.clear();
    }

    @Test
    void findMemberFetchJoin() {
        memberRepo.findMemberFetchJoin();
    }

    @Test
    void findAll() {
        memberRepo.findAll();
    }

    private void loadMemberData() {
       Member memberA = new Member("member a", 10);
        Member memberB = new Member("member b", 10);
        Member memberC = new Member("member c", 10);
        Member memberD = new Member("member d", 10);
        Member memberE = new Member("member e", 10);

        memberRepo.save(memberA);
        memberRepo.save(memberB);
        memberRepo.save(memberC);
        memberRepo.save(memberD);
        memberRepo.save(memberE);
    }

    @Test
    void findByNameGraph() {
        memberRepo.findEntityGraphByName("member a");
    }

    @Test
    void findEntityGraphByName() {
        memberRepo.findEntityGraphByName("member a");
    }

    @Test
    public void queryHint() {
//        given
        Member aMember = new Member("a member", 10);
        memberRepo.save(aMember);
        entityManager.flush();
        entityManager.clear();

//        when
        String aMemberName = aMember.getName();
        Member otherMember = memberRepo.findReadOnlyByName(aMemberName);
        otherMember.setName("other member");

        entityManager.flush();
    }

    @Test
    public void lock() {
//        given
        Member memberA = new Member("member a", 10);
        memberRepo.save(memberA);
        entityManager.flush();
        entityManager.clear();

//        when
        List<Member> members = memberRepo.findLockByName("member a");
    }

    @Test
    public void callCustom(){
        List<Member> memberCustom = memberRepo.findMemberCustom();
    }
}