package com.a304.ggong.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a304.ggong.dto.response.AllAnswerResponse;
import com.a304.ggong.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	Optional<Question> findByType(String type);

	// group별(지난주 or 이번주) 모든 질문 리스트 조회
	// type을 service에서 따로 줘야해...
	List<Question> findAllByGroupAndType(int questionGroup, String type);
}
