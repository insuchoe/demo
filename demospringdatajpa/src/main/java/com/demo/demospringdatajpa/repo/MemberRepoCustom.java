package com.demo.demospringdatajpa.repo;

import com.demo.demospringdatajpa.entity.Member;

import java.util.List;

public interface MemberRepoCustom {
    List<Member> findMemberCustom();

}
