package com.a304.ggong.repository;

import java.awt.print.Pageable;
import java.util.List;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.a304.ggong.entity.Point;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
	//선택 기간에 따른 포인트 내역 조회
	@Query("SELECT p FROM Point p WHERE p.eventTime >= :startDate AND p.eventTime <= :endDate AND p.user.userNo = :userNo")
	List<Point> findByUserNoAndEventTimeBetween(@Param("userNo") Long userNo, @Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

	// UserEmail에 따라 포인트 조회
//	@Query("SELECT p FROM Point p WHERE p.user.email = :userEmail")
//	List<Point> findAllByUserEmail(@Param("userEmail") String userEmail);

    
    //잔여 포인트 계산
    @Query("SELECT SUM(p.point) FROM Point p WHERE p.eventTime <= :theDate AND p.user.userNo = :userNo")
    Integer selectBalancePoint(@Param("theDate") Timestamp theDate, @Param("userNo") Long userNo);

	// 특정 user의 마지막 point entity 찾기
	// jpql은 limit가 안된대...
	// nativeQuery = true 추가해주기
//	Point findTopByUser_UserNoOOrderByEventTimeDesc(Long userNo);

//		@Query("SELECT p FROM Point p WHERE p.user.userNo = :userNo ORDER BY p.eventTime DESC")
//		List<Point> findLatestByUserNo(@Param("userNo") Long userNo, Pageable pageable);

	List<Point> findAllByUser_UserNo(Long userNo);

	Optional<Long> findBalancePointByUser_UserNo(Long userNo);
}
