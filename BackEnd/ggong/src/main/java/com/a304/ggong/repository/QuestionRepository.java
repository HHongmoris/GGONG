package com.a304.ggong.repository;

import java.util.List;

import com.a304.ggong.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.a304.ggong.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	// group별(지난주 or 이번주) 모든 질문 리스트 조회
	// type을 service에서 따로 줘야해...
	List<Question> findByGroupAndType(int group, QuestionType type);

	@Query("SELECT q.type FROM Question q WHERE q.questionID = :questionID")
	QuestionType findTypeByQuestionID(@Param("questionID") Long questionID);

}
