package com.demo.demospringdatajpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {
    @PersistenceContext
    EntityManager entityManager;
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

}