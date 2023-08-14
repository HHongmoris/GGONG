package com.a304.ggong.service;

import com.a304.ggong.dto.response.AllAnswerResponse;
import com.a304.ggong.dto.response.AnswerDetailResponse;
import com.a304.ggong.entity.Question;
import com.a304.ggong.entity.QuestionType;
import com.a304.ggong.entity.Vote;
import com.a304.ggong.repository.QuestionRepository;
import com.a304.ggong.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RequiredArgsConstructor // 생성자 주입
@Service
public class AnswerServiceImpl implements AnswerService{

    // 파라미터로 들어오는 questionGroup은 controller에서 구해줘야함.

    // repo
    private final VoteRepository voteRepository;
    private final QuestionRepository questionRepository;

    // 공통 사용 변수
    // 모든 질문 추출
//    private List<Question> questions;
    private List<AllAnswerResponse> list;
    // 질문 상세 페이지
    private List<Vote> votes;

    // 공통 사용 메소드
    // AllAnswerResponse에 나머지 값(answerA, answerB) 구해주기
    private List<AllAnswerResponse> getAnswers(int questionGroup, QuestionType questionType){
//        // 임시 AllAnswerResponse 객체를 만들고 거기에 Question을 넣어줌
//        AllAnswerResponse tmp = new AllAnswerResponse(questions.get(idx));
//
//        // answerA 구해서 객체에 넣어줌
//        Long answerA = voteRepository.countByQuestionGroupAndAnswerTypeAndQuestionType(questionGroup, 0, questionType);
//        tmp.setAnswerA(answerA);
//
//        // answerB 구해서 객체에 넣어줌
//        Long answerB = voteRepository.countByQuestionGroupAndAnswerTypeAndQuestionType(questionGroup, 1, questionType);
//        tmp.setAnswerA(answerB);
//
//        // rate 구해주기
//        Long answerAll = voteRepository.countByQuestionGroupAndQuestionType(questionGroup,questionType);
//
//        Long rateA = answerA / answerAll;
//        Long rateB = answerB / answerAll;
//
//        tmp.setRateA(rateA);
//        tmp.setRateB(rateB);
        List<Question> questions = questionRepository.findAll();
        //AllAnswerResponse 리스트 만들기
        List<AllAnswerResponse> allAnswerResponses = new ArrayList<>();
        //선택한 questiongroup과 questionType에 맞는 질문 전체 응답 수 카운트 리스트 생성
        List<Long[]> allQA = voteRepository.countByQuestionGroupAndQuestionType(questionGroup, questionType);
        List<Long[]> answerACnt = voteRepository.countByQuestionGroupAndAnswerTypeAndQuestionType(questionGroup, 0, questionType);

        for(int i=0; i<allQA.size(); i++){
            //allQA에 있는 질문 아이디를 통해 질문 데이터 불러와서 AllAnswerResponse에 담기

            //allQA로부터 질문 아이디 가져오기
            Long qID = allQA.get(i)[0];
            //가져온 질문 아이디의 전체 답변 수
            Long allCnt = allQA.get(i)[1];
            //가져온 질문 아이디의 A 항목 답변 수
            Long ACnt = answerACnt.get(i)[1];
            //담아야할 AllAnswerResponse 바구니 생성
            AllAnswerResponse allAnswerResponse = new AllAnswerResponse();
                //바구니에 값 하나하나 입력해주기
            allAnswerResponse.setQuestionID(qID);
            allAnswerResponse.setContent(questions.get(qID.intValue()).getContent());
            allAnswerResponse.setOptionA(questions.get(qID.intValue()).getOptionA());
            allAnswerResponse.setOptionB(questions.get(qID.intValue()).getOptionB());

            //비율 계산하여 총합 100이 나오게 백분율로 계산한 값 넘기기
            //근데 이제 처음엔 0이기 때문에 조건 나누기

            if(allCnt != 0){
                if(ACnt == null){
                    ACnt = 0L;
                    Long rateA = 0L;
                    Long rateB = 100L;
                    allAnswerResponse.setAnswerA(ACnt);
                    allAnswerResponse.setAnswerB(allCnt-ACnt);
                    allAnswerResponse.setRateA(rateA);
                    allAnswerResponse.setRateB(rateB);

                }else if(ACnt == allCnt){
                    Long BCnt = 0L;
                    Long rateA = 100L;
                    Long rateB = 0L;
                    allAnswerResponse.setAnswerA(ACnt);
                    allAnswerResponse.setAnswerB(BCnt);
                    allAnswerResponse.setRateA(rateA);
                    allAnswerResponse.setRateB(rateB);
                }else{
                    allAnswerResponse.setAnswerA(ACnt);
                    allAnswerResponse.setAnswerB(allCnt - ACnt);
                    allAnswerResponse.setRateA((ACnt*100)/allCnt);
                    allAnswerResponse.setRateB(100 - (ACnt*100)/allCnt);
                }

            }else{
                //allCnt가 0일때는 둘다 0으로 처리
                allAnswerResponse.setAnswerA(0L);
                allAnswerResponse.setAnswerB(0L);
                allAnswerResponse.setRateA(0L);
                allAnswerResponse.setRateB(0L);
            }
            //넣어준 allAnswerResponse를 리스트에 담기
            allAnswerResponses.add(allAnswerResponse);

        }

        return allAnswerResponses;
    }

    // 질문 상세 페이지
    // 대학
    @Override
    public List<AnswerDetailResponse>[] selectDetailAnswer(Long questionId) throws NullPointerException{

        // 먼저 Vote들을 리스트로 받아오기
//        votes = voteRepository.findAllWithMachineAndQuestionFetchJoin(questionGroup);
        List<Object[]> voteMachineUserDatas = voteRepository.findVoteDataByQuestionGroup(questionId);


        List<AnswerDetailResponse>[] result = new List[3];

        //지역, 연령, 특화 분류하는 인덱스
        //지역별 상세 결과 모음
        result[0] = new ArrayList<>();
        //지역별일 때는 dataLabel에 areagu 들어가게
        List<Object[]> areaGuData = voteRepository.findVoteDataByAreaGu(questionId);

        for(int i=0; i<areaGuData.size(); i++){
            AnswerDetailResponse answerDetailResponse = new AnswerDetailResponse();
            //dataLabel에 지역구 어디인지 넣음
            answerDetailResponse.setDataLabel((String) areaGuData.get(i)[0]);
            //답변 수 합 계산
            Long answerA =(Long) areaGuData.get(i)[1];
            Long answerB =(Long) areaGuData.get(i)[2];
            Long total = answerA + answerB;
            Long rateA = 0L;
            Long rateB = 0L;
            //각 항이 null일 때 경우 나눠서 생각
            if(total != 0L){
                if(answerA == null){
                    answerA = 0L;
                    rateA = 0L;
                    rateB = 100L;
                }else if(answerA == total){
                    answerB = 0L;
                    rateA = 100L;
                    rateB = 0L;
                }else{
                    rateA = (answerA*100)/total;
                    rateB = 100 - rateA;
                }
            }else{
                answerA = 0L;
                answerB = 0L;
                rateA = 0L;
                rateB = 0L;
            }

            //구한 값들 다 AnswerDetailResponse에 넣어주기
            answerDetailResponse.setAnswerA(answerA);
            answerDetailResponse.setAnswerB(answerB);
            answerDetailResponse.setRateA(rateA);
            answerDetailResponse.setRateB(rateB);

            result[0].add(answerDetailResponse);

        }

        //연령대별
        result[1] = new ArrayList<>();
        //연령대 별 일 때는 dataLabel에 ageRange 들어가게
        List<Object[]> ageRangeData = voteRepository.findVoteDataByAgeRange(questionId);

        for(int i=0; i<ageRangeData.size(); i++){
            AnswerDetailResponse answerDetailResponse = new AnswerDetailResponse();
            //dataLabel에 연령대 어디인지 넣음
            answerDetailResponse.setDataLabel((String) ageRangeData.get(i)[0]);
            //답변 수 합 계산
            Long answerA =(Long) ageRangeData.get(i)[1];
            Long answerB =(Long) ageRangeData.get(i)[2];
            Long total = answerA + answerB;
            Long rateA = 0L;
            Long rateB = 0L;
            //각 항이 null일 때 경우 나눠서 생각
            if(total != 0L){
                if(answerA == null){
                    answerA = 0L;
                    rateA = 0L;
                    rateB = 100L;
                }else if(answerA == total){
                    answerB = 0L;
                    rateA = 100L;
                    rateB = 0L;
                }else{
                    rateA = (answerA*100)/total;
                    rateB = 100 - rateA;
                }
            }else{
                answerA = 0L;
                answerB = 0L;
                rateA = 0L;
                rateB = 0L;
            }

            //구한 값들 다 AnswerDetailResponse에 넣어주기
            answerDetailResponse.setAnswerA(answerA);
            answerDetailResponse.setAnswerB(answerB);
            answerDetailResponse.setRateA(rateA);
            answerDetailResponse.setRateB(rateB);

            result[1].add(answerDetailResponse);
            System.out.println("연령별 : " + result[1].toString());

        }

        //대학, 기업별
        result[2] = new ArrayList<>();
        //index가 2일때는 대학인지 기업인지 판단 필요
        QuestionType special = questionRepository.findTypeByQuestionID(questionId);
        if(special.equals(QuestionType.대학)){
            //대학 특화일 때
            List<Object[]> machineNameData = voteRepository.findVoteDataByMachineName(questionId);

            for(int i=0; i<machineNameData.size(); i++) {
                AnswerDetailResponse answerDetailResponse = new AnswerDetailResponse();
                //dataLabel에 기기명 무엇인지 넣음
                answerDetailResponse.setDataLabel((String) machineNameData.get(i)[0]);
                //답변 수 합 계산
                Long answerA = (Long) machineNameData.get(i)[1];
                Long answerB = (Long) machineNameData.get(i)[2];
                Long total = answerA + answerB;
                Long rateA = 0L;
                Long rateB = 0L;

                //각 항이 null일 때 경우 나눠서 생각
                if (total != 0L) {
                    if (answerA == null) {
                        answerA = 0L;
                        rateA = 0L;
                        rateB = 100L;
                    } else if (answerA == total) {
                        answerB = 0L;
                        rateA = 100L;
                        rateB = 0L;
                    } else {
                        rateA = (answerA * 100) / total;
                        rateB = 100 - rateA;
                    }
                } else {
                    answerA = 0L;
                    answerB = 0L;
                    rateA = 0L;
                    rateB = 0L;
                }

                //구한 값들 다 AnswerDetailResponse에 넣어주기
                answerDetailResponse.setAnswerA(answerA);
                answerDetailResponse.setAnswerB(answerB);
                answerDetailResponse.setRateA(rateA);
                answerDetailResponse.setRateB(rateB);

                result[2].add(answerDetailResponse);
            }
        } else if (special.equals(QuestionType.기업)) {
            //기업 특화일 때
            List<Object[]> machineNameData = voteRepository.findVoteDataByMachineName(questionId);

            for(int i=0; i<machineNameData.size(); i++) {
                AnswerDetailResponse answerDetailResponse = new AnswerDetailResponse();
                //dataLabel에 기기명 무엇인지 넣음
                answerDetailResponse.setDataLabel((String) machineNameData.get(i)[0]);
                //답변 수 합 계산
                Long answerA = (Long) machineNameData.get(i)[1];
                Long answerB = (Long) machineNameData.get(i)[2];
                Long total = answerA + answerB;
                Long rateA = 0L;
                Long rateB = 0L;
                //각 항이 null일 때 경우 나눠서 생각
                if (total != 0L) {
                    if (answerA == null) {
                        answerA = 0L;
                        rateA = 0L;
                        rateB = 100L;
                    } else if (answerA == total) {
                        answerB = 0L;
                        rateA = 100L;
                        rateB = 0L;
                    } else {
                        rateA = (answerA * 100) / total;
                        rateB = 100 - rateA;
                    }
                } else {
                    answerA = 0L;
                    answerB = 0L;
                    rateA = 0L;
                    rateB = 0L;
                }

                //구한 값들 다 AnswerDetailResponse에 넣어주기
                answerDetailResponse.setAnswerA(answerA);
                answerDetailResponse.setAnswerB(answerB);
                answerDetailResponse.setRateA(rateA);
                answerDetailResponse.setRateB(rateB);

                result[2].add(answerDetailResponse);
            }
        }

        return result;
    }
    // service layer에서는 공통, 대학, 기업 메소드를 따로 만들어 데이터를 컨트롤러로 보내주고
    // 컨트롤러에서 리스트들을 배열에 담아 프론트로 보내주자!

    // 공통 질문 응답 데이터 조회
    @Override
    public List<AllAnswerResponse> selectAnswersGroupByCommon(int questionGroup) {
        // countByAnswer 사용해서 AllAnswerResponse에 넣어주기
        // findByQuestionGroupAndType 사용

        List<AllAnswerResponse> allAnswerResponses = getAnswers(questionGroup, QuestionType.공통);

        return allAnswerResponses;
    }

    // 대학 질문 응답 데이터 조회
    @Override
    public List<AllAnswerResponse> selectAnswersGroupByUnis(int questionGroup) {
        // countByAnswer 사용해서 AllAnswerResponse에 넣어주기
        // findByQuestionGroupAndType 사용
        List<AllAnswerResponse> allAnswerResponses = getAnswers(questionGroup, QuestionType.대학);

        return allAnswerResponses;
    }

    // 기업 질문 응답 데이터 조회
    @Override
    public List<AllAnswerResponse> selectAnswersGroupByCompanies(int questionGroup) {
        // countByAnswer 사용해서 AllAnswerResponse에 넣어주기
        // findByQuestionGroupAndType 사용
        List<AllAnswerResponse> allAnswerResponses = getAnswers(questionGroup, QuestionType.기업);

        return allAnswerResponses;
    }

    // 지난 vote 초기화
    public void iniAnswers(){
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime startOfLastMonth = now.minusMonths(1).withDayOfMonth(1).with(LocalTime.MIN);
        // 지난달의 마지막 일자 (이번 달의 1일 이전)
        LocalDateTime endOfLastMonth = now.withDayOfMonth(1).minusDays(1).with(LocalTime.MAX);

        Timestamp start = Timestamp.valueOf(startOfLastMonth);
        Timestamp end = Timestamp.valueOf(endOfLastMonth);

        voteRepository.deleteByVoteDateBetween(start, end);
    }
}