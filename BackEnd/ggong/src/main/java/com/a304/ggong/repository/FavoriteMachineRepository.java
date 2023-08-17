package com.a304.ggong.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.a304.ggong.entity.FavoriteMachine;

@Repository
public interface FavoriteMachineRepository extends JpaRepository<FavoriteMachine, Long> {

//	Optional<FavoriteMachine> findByMachine_MachineNo(Long machineNo);
	List<FavoriteMachine> findByUser_UserNo(Long userNo);
	Optional<FavoriteMachine> findByUser_UserNoAndMachine_MachineNo(Long userNo, Long machineNo);

	// @Query("SELECT f FROM FavoriteMachine f WHERE f.user.email = :email")
	// List<FavoriteMachine> findByUserEmail(@Param("email") String email);
}
