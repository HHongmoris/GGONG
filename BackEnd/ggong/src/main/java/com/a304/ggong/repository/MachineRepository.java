package com.a304.ggong.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a304.ggong.entity.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

	Optional<Machine> findByAreaGu(String areaGu);

	Optional<Machine> findByName(String name);
}