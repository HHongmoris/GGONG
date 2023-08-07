package com.a304.ggong.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.a304.ggong.entity.FavoriteMachine;

@Repository
public interface FavoriteMachineRepository extends JpaRepository<FavoriteMachine, Long> {

	Optional<FavoriteMachine> findByMachine_MachineNo(Long machineNo);

	@Query("SELECT f FROM FavoriteMachine f WHERE f.user.email = :email")
	Optional<FavoriteMachine> findByUserEmail(@Param("email") String email);
}
