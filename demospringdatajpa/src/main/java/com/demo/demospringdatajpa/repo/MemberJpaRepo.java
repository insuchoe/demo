package com.demo.demospringdatajpa.repo;

import com.demo.demospringdatajpa.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepo {
    @PersistenceContext
    private EntityManager entityManager;

    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }

    public Member find(Long id) {
        return entityManager.find(Member.class, id);
    }
    public void delete(Member member){
        entityManager.remove(member);
    }

    public List<Member> findAll(){
        String query="select m from Member m";
        TypedQuery<Member> members = entityManager.createQuery(query, Member.class);
        return members.getResultList();
    }


    public Optional<Member> findById(Long id){
        Member member = entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public long count(){
        String query="select count(m) from Member m";
        TypedQuery<Long> queryForMember = entityManager.createQuery(query, Long.class);
        Long countForMember = queryForMember.getSingleResult();
        return countForMember;
    }


}
