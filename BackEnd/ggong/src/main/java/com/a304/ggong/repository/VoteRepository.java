package com.a304.ggong.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.a304.ggong.dto.response.AllAnswerResponse;
import com.a304.ggong.entity.Machine;
import com.a304.ggong.entity.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

	// return값이 Vote이므로 service에서 entity -> dto로 넘겨주는 로직 필요!
	Optional<Vote> findByUserId(Long userId);

	Optional<Vote> findByMachineId(Long machineId);

	// group별(지난주 or 이번주) 모든 질문 리스트 조회
	// type을
	@Query("SELECT new com.a304.ggong.dto.response.AllAnswerResponse(q) FROM Question q WHERE q.type = :type GROUP BY q.group HAVING q.group = :questionGroup")
	List<AllAnswerResponse> findByQuestionGroup(@Param("questionGroup") int questionGroup, @Param("type") String type);

	// group별(지난주 or 이번주) type(공통 or 특화)에 따라 A or B(answer)판단해서 count 해주기
	@Query("SELECT COUNT(v) FROM Vote v WHERE v.question.type = :questionType AND v.answer = :answerType GROUP BY v.question.group HAVING v.question.group = :questionGroup")
	Long countByA(@Param("questionGroup") int questionGroup, @Param("answerType") int answerType,
		@Param("questionType") String questionType);

	//당일 수거함 사용자 수(실시간), 지난달 사용자 수 추출 메서드
	@Query("SELECT COUNT(v) FROM Vote v WHERE v.voteDate >= :startDate AND v.voteDate < :endDate")
	Long countByVoteDate(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

	//기기별 사용자 수(지난주) 추출 메서드
	@Query("SELECT COUNT(v.machine) FROM Vote v WHERE v.voteDate >= :startDate AND v.voteDate < :endDate AND v.machine = :machine")
	Long countByMachine(@Param("machine") Machine machine, @Param("startDate") Timestamp startDate,
		@Param("endDate") Timestamp endDate);
}
