package com.demo.demospringdatajpa.controller;

import com.demo.demospringdatajpa.dto.MemberDto;
import com.demo.demospringdatajpa.entity.Member;
import com.demo.demospringdatajpa.repo.MemberRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private final MemberRepo memberRepo;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) throws IllegalArgumentException {
        Optional<Member> memberById = memberRepo.findById(id);

        if (memberById.isPresent()) {
            String memberName = memberById.get().getName();
            return memberName;
        }
        throw new IllegalArgumentException(id + " id member not found");
    }

    @GetMapping("/otherMember/{id}")
    public String findOtherMember(@PathVariable("id") Member member){
        String memberName = member.getName();
        return memberName;
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            Member member = new Member("member "+i);
            memberRepo.save(member);
        }
    }

    @GetMapping("/members")
    public Page<MemberDto> memberPage(@PageableDefault(size = 30) Pageable pageable){
        Page<Member> pageOfMember = memberRepo.findAll(pageable);
        Page<MemberDto> pageOfMemberDto = pageOfMember.map(member -> new MemberDto(member.getId(),
                member.getName(), null));
        return pageOfMemberDto;
    }


}
