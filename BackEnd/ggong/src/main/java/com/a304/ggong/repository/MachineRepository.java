package com.a304.ggong.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.a304.ggong.entity.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

	Optional<Machine> findByAreaGu(String areaGu);

	Optional<Machine> findByName(String name);

	// areaGu로 machine name 찾아서
	// dataLabel은 service에서 추출해서 dto에 넣어주기
	@Query("SELECT m.name FROM Machine m WHERE m.areaGu")
	List<String> findByAreaGu();
}