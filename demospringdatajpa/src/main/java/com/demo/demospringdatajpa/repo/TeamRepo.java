package com.demo.demospringdatajpa.repo;

import com.demo.demospringdatajpa.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface TeamRepo extends JpaRepository<Team, Long> {
}
