package com.demo.demospringdatajpa.repo;

import com.demo.demospringdatajpa.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MemberRepoImpl implements MemberRepoCustom{


    private final EntityManager entityManager;
    @Override
    public List<Member> findMemberCustom() {
        String requestQuery="select m from Member m";
        Query query = entityManager.createQuery(requestQuery);
        List<Member> queryResult = query.getResultList();
        return queryResult;
    }
}
