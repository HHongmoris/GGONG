package com.a304.ggong.repository;

import java.sql.Timestamp;
import java.util.Optional;

import com.a304.ggong.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.a304.ggong.entity.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

	Optional<Vote> findByUserId(Long userId);

	Optional<Vote> findByMachineId(Long machineId);

	//당일 수거함 사용자 수(실시간), 지난달 사용자 수 추출 메서드
	@Query("SELECT COUNT(v) FROM Vote v WHERE v.voteDate >= :startDate AND v.voteDate < :endDate")
	Long countByVoteDate(Timestamp startDate, Timestamp endDate);

	//기기별 사용자 수(지난주) 추출 메서드
	@Query("SELECT COUNT(v.machine) FROM Vote v WHERE v.voteDate >= :startDate AND v.voteDate < :endDate AND v.machine = :machine")
	Long countByMachine(Machine machine, Timestamp startDate, Timestamp endDate);
}
