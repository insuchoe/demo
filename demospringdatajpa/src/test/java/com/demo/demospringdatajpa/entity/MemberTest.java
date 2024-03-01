package com.demo.demospringdatajpa.entity;

import com.demo.demospringdatajpa.repo.MemberRepo;
import jakarta.persistence.*;
import org.hibernate.resource.beans.internal.FallbackBeanInstanceProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberTest {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    MemberRepo memberRepo;
    @Test
    public void testEntity(){
//        generate team data
        Team teamA = new Team("team a");
        Team teamB = new Team("team b");

//        generate member data
        Member memberA = new Member("member a", 31, teamA);
        Member memberB = new Member("member b", 35, teamA);
        Member memberC = new Member("member c", 39, teamB);
        Member memberD = new Member("member d", 27, teamB);

//        insert to jpa context
        entityManager.persist(teamA);
        entityManager.persist(teamB);
        entityManager.persist(memberA);
        entityManager.persist(memberB);
        entityManager.persist(memberC);
        entityManager.persist(memberD);

//        jpa context initialize
        entityManager.flush();
        entityManager.clear();

//        request select query 
        String query="select m from Member m";
        TypedQuery<Member> queryForSelect = entityManager.createQuery(query, Member.class);
        System.out.println("query for member");
        for (Member member : queryForSelect.getResultList()) {
            System.out.println(member);
            System.out.println(member.getTeam());
        }
    }
    @Test
    public void JPAEventBaseEntity() throws InterruptedException {
//        given
        Member memberA = new Member("member a");
//        @PrePersist
        memberRepo.save(memberA);

        Thread.sleep(100);
        memberA.setName("member b");

//        @PreUpdate
        entityManager.flush();
        entityManager.clear();

//        when
        Long memberAId = memberA.getId();
        Optional<Member> memberAById = memberRepo.findById(memberAId);
        if (memberAById.isPresent()) {
            Member memberAIfPresent = memberAById.get();
            LocalDateTime memberCreatedDateTime = memberAIfPresent.getCreatedDate();
            LocalDateTime memberUpdatedDateTime = memberAIfPresent.getLastModifiedDate();
            String memberCreatedBy = memberAIfPresent.getCreatedBy();
            String memberLastModifiedBy = memberAIfPresent.getLastModifiedBy();
            System.out.println("member created date and time = " + memberCreatedDateTime);
            System.out.println("member updated date and time = " + memberUpdatedDateTime);
            System.out.println("member created by = " +memberCreatedBy);
            System.out.println("member modified by = "+ memberLastModifiedBy);

//            then
            Assertions.assertNotNull(memberCreatedDateTime);
            Assertions.assertNotNull(memberUpdatedDateTime);
        }

    }
}