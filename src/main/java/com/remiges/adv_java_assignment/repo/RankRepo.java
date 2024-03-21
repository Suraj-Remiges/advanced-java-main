package com.remiges.adv_java_assignment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.remiges.adv_java_assignment.entity.Ranks;

public interface RankRepo extends JpaRepository<Ranks, Integer> {

}
