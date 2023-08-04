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

	// 얘는 프론트랑 상의 후에...(백에서 처리? 프론트에서 처리?) -> 위에서 where 절이 빠질지도?
	//	// group별 대학 질문 리스트 조회
	//	@Query("SELECT new com.a304.ggong.dto.response.AllAnswerResponse(q) FROM Question q WHERE q.type = '대학'")
	//	List<AllAnswerResponse> getUniversityQuestions(int questionGroup);
	//
	//	// group별 기업 질문 리스트 조회
	//	@Query("SELECT new com.a304.ggong.dto.response.AllAnswerResponse(q) FROM Question q WHERE q.type = '기업'")
	//	List<AllAnswerResponse> getCompanyQuestions(int questionGroup);

	// 그 외, 지역별, 연령별, 대학별, 기업별은 프론트에서 질문ID를 넘겨줄 수 있는지의 여부에 따라 메소드가 다르게 짜일듯!
	// 만약 넘겨준다면?
	// 지역별 질문 응답 데이터 조회

	// 이 쿼리문 다시 손보자!
	@Query("SELECT new com.a304.ggong.dto.response.AreaAnswerResponse(m, q) FROM Vote v INNER JOIN v.machine m INNER JOIN v.question q")
	List<AreaAnswerResponse> findByArea(int questionGroup, int questionId);

	// group별(지난주 or 이번주) type(공통 or 특화)에 따라 A or B(answer)판단해서 count 해주기
	// 먼저 A
	@Query("SELECT COUNT(v) FROM Vote v WHERE v.question.type = :questionType AND v.answer = 0 GROUP BY v.question.group HAVING v.question.group = :questionGroup")
	Long countByA(int questionGroup, String questionType);

	// group별(지난주 or 이번주) type(공통 or 특화)에 따라 A or B(answer)판단해서 count 해주기
	// 먼저 B
	@Query("SELECT COUNT(v) FROM Vote v WHERE v.question.type = :questionType AND v.answer = 1 GROUP BY v.question.group HAVING v.question.group = :questionGroup")
	Long countByB(int questionGroup, String questionType);

	//당일 수거함 사용자 수(실시간), 지난달 사용자 수 추출 메서드
	@Query("SELECT COUNT(v) FROM Vote v WHERE v.voteDate >= :startDate AND v.voteDate < :endDate")
	Long countByVoteDate(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

	//기기별 사용자 수(지난주) 추출 메서드
	@Query("SELECT COUNT(v.machine) FROM Vote v WHERE v.voteDate >= :startDate AND v.voteDate < :endDate AND v.machine = :machine")
	Long countByMachine(@Param("machine") Machine machine, @Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);
}
