package com.a304.ggong.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a304.ggong.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Optional<User> findByName(String name);

	Optional<User> findByGender(String gender);

	Optional<User> findByAgeRange(String ageRange);

	//총 회원수
	Long countUserByUserNo(Long userNo);

	//연령별 통계
	Long countUserByAgeRange(String ageRange);

	//성별 통계
	Long countUserByGender(String gender);


}
