package com.demo.demospringdatajpa.dto;
import com.demo.demospringdatajpa.entity.Member;
import com.demo.demospringdatajpa.entity.Team;
import lombok.*;

@Data
public class MemberDto {
    private Long id;
    private String name;
    private String teamName;

    public MemberDto(Long id, String name, String teamName) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
    }
    public MemberDto(Member member){
        id=member.getId();
        name=member.getName();
        teamName=null;
    }
}
