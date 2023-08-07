package com.a304.ggong.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a304.ggong.entity.FavoriteMachine;

@Repository
public interface FavoriteMachineRepository extends JpaRepository<FavoriteMachine, Long> {

	Optional<FavoriteMachine> findByMachineNo(Long machineNo);

	Optional<FavoriteMachine> findByEmail(String email);
}
