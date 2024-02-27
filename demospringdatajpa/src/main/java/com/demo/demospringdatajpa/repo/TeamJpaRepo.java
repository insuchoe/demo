package com.demo.demospringdatajpa.repo;

import com.demo.demospringdatajpa.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamJpaRepo {
    @PersistenceContext private EntityManager entityManager;

    public Team save(Team team){
        entityManager.persist(team);
        return team;
    }
    public List<Team> findAll(){
        String query = "select t from Team t";
        TypedQuery<Team> queryForTeam = entityManager.createQuery(query, Team.class);
        List<Team> teams = queryForTeam.getResultList();
        return teams;
    }
}
