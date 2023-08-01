package com.a304.ggong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a304.ggong.entity.FavoriteMachine;

@Repository
public interface FavoriteMachineRepository extends JpaRepository<FavoriteMachine, Long> {
}
