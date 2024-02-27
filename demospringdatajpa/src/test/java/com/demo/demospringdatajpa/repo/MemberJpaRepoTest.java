package com.demo.demospringdatajpa.repo;

import com.demo.demospringdatajpa.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberJpaRepoTest {

    @Autowired
    MemberJpaRepo memberJpaRepo;

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
    void find() {
    }
}