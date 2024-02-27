package com.demo.demospringdatajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","name","age"})
@NamedQuery(name = "findByName",
            query="select m from Member m where m.name = :name")
public class Member {
    @Column(name = "member_id")
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String name) {
        this.name = name;
    }

    public Member(String name, int age, Team team) {
        this.team = team;
        this.age = age;
        if (team!=null) changeTeam(team);

    }

    public Member(String name, int age) {
        this.name=name;
        this.age=age;

    }

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }

}
