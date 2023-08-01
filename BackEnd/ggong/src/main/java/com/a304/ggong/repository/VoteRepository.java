package com.a304.ggong.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a304.ggong.entity.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

	Optional<Vote> findByUserId(Long userId);

	Optional<Vote> findByMachineId(Long machineId);
}
