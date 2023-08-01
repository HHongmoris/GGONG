package com.a304.ggong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a304.ggong.entity.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
