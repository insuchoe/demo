package com.demo.demospringdatajpa.repo;

import com.demo.demospringdatajpa.entity.Member;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberJpaRepoTest {

    @Autowired
    MemberJpaRepo memberJpaRepo;
    @Autowired
    EntityManager entityManager;

    @Test
    void save() {
        Member nameA = new Member("name a");
        memberJpaRepo.save(nameA);

        Member memberNameA = memberJpaRepo.find(nameA.getId());
        Assertions.assertThat(nameA.getId()).isEqualTo(memberNameA.getId());
        Assertions.assertThat(nameA.getName()).isEqualTo(memberNameA.getName());
        Assertions.assertThat(nameA).isEqualTo(memberNameA);

    }

    @Test
    public void paging()
    {
        Member memberA = new Member("member a", 10);
        Member memberB = new Member("member b", 10);
        Member memberC = new Member("member c", 10);
        Member memberD = new Member("member d", 25);
        Member memberE = new Member("member e", 30);

        memberJpaRepo.save(memberA);
        memberJpaRepo.save(memberB);
        memberJpaRepo.save(memberC);
        memberJpaRepo.save(memberD);
        memberJpaRepo.save(memberE);

        int age= 10;
        int offset = 0;
        int limit =3;

        List<Member> members = memberJpaRepo.findByPage(age, offset, limit);
        long totalCountForMember = memberJpaRepo.totalCount(age);


        Assertions.assertThat(members.size()).isEqualTo(3);
        Assertions.assertThat(totalCountForMember).isEqualTo(3);
    }

}