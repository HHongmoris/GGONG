package com.a304.ggong.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.a304.ggong.dto.QuestionAndAnswerCnt;
import com.a304.ggong.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.a304.ggong.entity.Vote;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

	// return값이 Vote이므로 service에서 entity -> dto로 넘겨주는 로직 필요!
	Optional<Vote> findByUser_UserNo(Long userNo);

	List<Vote> findByMachine_MachineNo(Long machineNo);

	Optional<Vote> findAllByQuestion_QuestionID(Long questionId);

	// Vote + Machine + User Fetch Join
//	@Query("SELECT v.answer, v.voteDate, m.areaGu, m.name, u.ageRange FROM Vote v LEFT JOIN Machine m ON v.machine.machineNo = m.machineNo LEFT JOIN User u ON v.user.userNo = u.userNo WHERE v.question.group = :questionGroup")
	@Query("SELECT v.answer, v.voteDate, v.machine.areaGu, v.machine.name, v.user.ageRange FROM Vote v WHERE v.question.group = :questionGroup")
	List<Vote> findAllWithMachineAndQuestionFetchJoin(@Param("questionGroup") int questionGroup);

	// group과 type에 따라 전체 answer count하기
	@Query("SELECT v.question.questionID AS questionID, COUNT(v) AS answerCnt FROM Vote v LEFT JOIN Question q ON v.question.questionID = q.questionID WHERE q.group = :questionGroup AND q.type = :questionType GROUP BY v.question.questionID")
	List<Long[]> countByQuestionGroupAndQuestionType(@Param("questionGroup") int questionGroup, @Param("questionType") QuestionType questionType);

	// group별(지난주 or 이번주) type(공통 or 특화)에 따라 A or B(answer)판단해서 count 해주기
	@Query("SELECT q.questionID AS questionID, NULLIF((SELECT COUNT(*) FROM Vote v WHERE v.question.questionID = q.questionID AND (v.answer IS NULL OR v.answer = :answerType)), 0) AS answerCnt FROM Question q WHERE q.group = :questionGroup AND q.type = :questionType")
	List<Long[]> countByQuestionGroupAndAnswerTypeAndQuestionType(@Param("questionGroup") int questionGroup, @Param("answerType") int answerType, @Param("questionType") QuestionType questionType);

	//당일 수거함 사용자 수(실시간), 지난달 사용자 수 추출 메서드
	@Query("SELECT COUNT(v) FROM Vote v WHERE v.voteDate >= :startDate AND v.voteDate < :endDate")
	Long countByVoteDate(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

	//기기별 사용자 수(지난주) 추출 메서드
	@Query("SELECT COUNT(v.machine) FROM Vote v WHERE v.voteDate >= :startDate AND v.voteDate < :endDate AND v.machine = (SELECT m FROM Machine m WHERE m.name = :machine)")
	Long countByMachine(@Param("machine") String machine, @Param("startDate") Timestamp startDate,
		@Param("endDate") Timestamp endDate);

	//이거 일단 주석처리함 나중에 수정 필요
	// 지난달 데이터 삭제
//	@Query("DELETE FROM Vote v WHERE v.voteDate <= :deleteDate")
	@Transactional
	void deleteByVoteDateBetween(@Param("startOfLastMonth") Timestamp startOfLastMonth, @Param("endOfLastMonth") Timestamp endOfLastMonth);

	// 오늘, 어제 수거함 사용자 수
//	@Query("SELECT COUNT(v) FROM Vote v WHERE v.voteDate = :date")
//	Long countByDate(@Param("Date") Timestamp date);

	//당일 수거함 사용자 수(실시간), 지난달 사용자 수 추출 메서드
	@Query("SELECT COUNT(v) FROM Vote v WHERE v.voteDate >= :startDate AND v.voteDate < :endDate AND v.user.userNo = :userNo")
	Long countByVoteDateAndUserId(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate, @Param("userNo") Long userNo);

	// 기기 상세 조회에 쓸 vote 메소드
	// 파라미터로 기기 넘버, 퀘스천 아이디, 시작날짜, 끝날짜, A or B(answer)
	@Query("SELECT COUNT(v) FROM Vote v WHERE v.machine.machineNo = :machineNo AND v.question.questionID = :questionId AND v.answer = :answer AND v.voteDate >= :startDate AND v.voteDate < :endDate")
	Long countByMachineNoAndQuestionIdAndAnswerAndStartDayAndEndDay(@Param("machineNo") Long machineNo, @Param("questionId") Long questionId, @Param("answer") int answer, @Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);
}
