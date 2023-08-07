package com.a304.ggong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.a304.ggong.entity.Point;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
	//선택 기간에 따른 포인트 내역 조회
	@Query("SELECT p FROM Point p WHERE p.eventTime >= :startDate AND p.eventTime <= :endDate")
	List<Point> findByEventTimeBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);

	// UserEmail에 따라 포인트 조회
	@Query("SELECT p FROM Point p WHERE p.userNo.email = :userEmail")
	List<Point> findAllByUserEmail(@Param("userEmail") String userEmail);
}
