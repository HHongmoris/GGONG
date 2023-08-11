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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.a304.ggong.entity.QuestionType.*;

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
    private List<Question> questions;
    private List<AllAnswerResponse> list;
    // 질문 상세 페이지
    private List<Vote> votes;

    // 공통 사용 메소드
    // AllAnswerResponse에 나머지 값(answerA, answerB) 구해주기
    AllAnswerResponse getAnswers(int idx, int questionGroup, QuestionType questionType){
        // 임시 AllAnswerResponse 객체를 만들고 거기에 Question을 넣어줌
        AllAnswerResponse tmp = new AllAnswerResponse(questions.get(idx));

        // answerA 구해서 객체에 넣어줌
        Long answerA = voteRepository.countByQuestionGroupAndAnswerTypeAndQuestionType(questionGroup, 0, questionType);
        tmp.setAnswerA(answerA);

        // answerB 구해서 객체에 넣어줌
        Long answerB = voteRepository.countByQuestionGroupAndAnswerTypeAndQuestionType(questionGroup, 1, questionType);
        tmp.setAnswerA(answerB);

        // rate 구해주기
        Long answerAll = voteRepository.countByQuestionGroupAndQuestionType(questionGroup,questionType);

        Long rateA = answerA / answerAll;
        Long rateB = answerB / answerAll;

        tmp.setRateA(rateA);
        tmp.setRateB(rateB);

        return tmp;
    }

    // 질문 상세 페이지
    // 대학
    @Override
    public List<AnswerDetailResponse>[] selectDetailAnswer(int questionGroup, String machineLocation) {
        // findAllWithMachineAndQuestionFetchJoin 사용
        // for문도 돌리고... if문도 돌리고...
        // 먼저 Vote들을 리스트로 받아오기
        votes = voteRepository.findAllWithMachineAndQuestionFetchJoin(questionGroup);

        // 객체 넣어줄 map
        HashMap<String, AnswerDetailResponse> areaMap = new HashMap<String, AnswerDetailResponse>();
        HashMap<String, AnswerDetailResponse> ageMap = new HashMap<String, AnswerDetailResponse>();
        HashMap<String, AnswerDetailResponse> uniMap = new HashMap<String, AnswerDetailResponse>();
        HashMap<String, AnswerDetailResponse> comMap = new HashMap<String, AnswerDetailResponse>();

        List<AnswerDetailResponse>[] result = new List[3];

        // for문 돌리기
        // 지역
        for(int idx = 0; idx < votes.size(); idx++){
            Vote tmpVote = votes.get(idx);

            // 답변
            int answer = tmpVote.getAnswer();
            // answer가 0(A)이면 true로 바뀌게
            boolean b = false;
            if(answer == 0){
                b = true;
            }

            // 먼저 List에 넣어줄 객체 만들고
            AnswerDetailResponse tmp = new AnswerDetailResponse();

            // 지역
            String areaGu = tmpVote.getMachine().getAreaGu();
            if(!areaMap.containsKey(areaGu)){

                // 지역구 넣어주고
                tmp.setDataLabel(areaGu);
                if(b){ // A를 선택했으면?
                    tmp.setAnswerA(tmp.getAnswerA()+1);
                }else {
                    tmp.setAnswerB(tmp.getAnswerB()+1);
                }

                // map에 넣어주기
                areaMap.put(areaGu, tmp);
            }else {
                tmp = areaMap.get(areaGu);
                if(b){ // A를 선택했으면?
                    tmp.setAnswerA(tmp.getAnswerA()+1);
                }else { // B를 선택했으면?
                    tmp.setAnswerB(tmp.getAnswerB()+1);
                }
            }

            // 연령
            String age = tmpVote.getUser().getAgeRange();
            if(!ageMap.containsKey(age)){

                // 지역구 넣어주고
                tmp.setDataLabel(age);
                if(b){ // A를 선택했으면?
                    tmp.setAnswerA(tmp.getAnswerA()+1);
                }else {
                    tmp.setAnswerB(tmp.getAnswerB()+1);
                }

                // map에 넣어주기
                ageMap.put(age, tmp);
            }else {
                tmp = ageMap.get(age);
                if(b){ // A를 선택했으면?
                    tmp.setAnswerA(tmp.getAnswerA()+1);
                }else { // B를 선택했으면?
                    tmp.setAnswerB(tmp.getAnswerB()+1);
                }
            }

            // 대학
            if(machineLocation.equals("대학")){
                String uni = tmpVote.getMachine().getName();

                // 대학인지 기업인지 구분
                if (!uni.contains("대학교")) {
                    continue;
                }

                if (!uniMap.containsKey(uni)) {

                    // 대학명 넣어주고
                    tmp.setDataLabel(uni);
                    if (b) { // A를 선택했으면?
                        tmp.setAnswerA(tmp.getAnswerA() + 1);
                    } else {
                        tmp.setAnswerB(tmp.getAnswerB() + 1);
                    }

                    // map에 넣어주기
                    uniMap.put(uni, tmp);
                } else {
                    tmp = uniMap.get(uni);
                    if (b) { // A를 선택했으면?
                        tmp.setAnswerA(tmp.getAnswerA() + 1);
                    } else { // B를 선택했으면?
                        tmp.setAnswerB(tmp.getAnswerB() + 1);
                    }
                }
            }else { // 기업
                String com = tmpVote.getMachine().getName();

                // 대학인지 기업인지 구분
                if (com.contains("대학교")) {
                    continue;
                }

                if (!comMap.containsKey(com)) {

                    // 대학명 넣어주고
                    tmp.setDataLabel(com);
                    if (b) { // A를 선택했으면?
                        tmp.setAnswerA(tmp.getAnswerA() + 1);
                    } else {
                        tmp.setAnswerB(tmp.getAnswerB() + 1);
                    }

                    // map에 넣어주기
                    comMap.put(com, tmp);
                } else {
                    tmp = comMap.get(com);
                    if (b) { // A를 선택했으면?
                        tmp.setAnswerA(tmp.getAnswerA() + 1);
                    } else { // B를 선택했으면?
                        tmp.setAnswerB(tmp.getAnswerB() + 1);
                    }
                }

            }

        }
        List<AnswerDetailResponse> tmpAreaLIst = new ArrayList<>(areaMap.values());
        List<AnswerDetailResponse> tmpAgeLIst = new ArrayList<>(ageMap.values());
        List<AnswerDetailResponse> tmpUniLIst = new ArrayList<>(uniMap.values());
        List<AnswerDetailResponse> tmpComLIst = new ArrayList<>(comMap.values());

        for(int idx = 0; idx < 3; idx++){
            result[idx] = new ArrayList<>();
        }

        // 각 value를 List로 묶어서 배열에 넣어주기
        result[0] = tmpAreaLIst;
        result[1] = tmpAgeLIst;

        if(machineLocation.equals("대학")){
            result[2] = tmpUniLIst;
        }else {
            result[2] = tmpComLIst;
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

        // 먼저, 그룹별, 타입별 질문을 몽땅 가져오자
        questions = questionRepository.findAllByGroupAndType(questionGroup,공통);

        // 먼저 list 만들어서
        list = new ArrayList<>();

        // for문 돌려서 AllAnswerResponse에 나머지 값 answerA, answerB 구하기
        for(int idx = 0; idx < questions.size(); idx++){
            AllAnswerResponse tmp = getAnswers(idx, questionGroup, 공통);

            list.add(tmp);
        }

        return list;
    }

    // 대학 질문 응답 데이터 조회
    @Override
    public List<AllAnswerResponse> selectAnswersGroupByUnis(int questionGroup) {
        // countByAnswer 사용해서 AllAnswerResponse에 넣어주기
        // findByQuestionGroupAndType 사용

        // 먼저, 그룹별, 타입별 질문을 몽땅 가져오자
        questions = questionRepository.findAllByGroupAndType(questionGroup,대학);

        // 먼저 list 만들어서
        list = new ArrayList<>();

        // for문 돌려서 AllAnswerResponse에 나머지 값 answerA, answerB 구하기
        for(int idx = 0; idx < questions.size(); idx++){
            AllAnswerResponse tmp = getAnswers(idx, questionGroup, 대학);

            list.add(tmp);
        }

        return list;
    }

    // 기업 질문 응답 데이터 조회
    @Override
    public List<AllAnswerResponse> selectAnswersGroupByCompanies(int questionGroup) {
        // countByAnswer 사용해서 AllAnswerResponse에 넣어주기
        // findByQuestionGroupAndType 사용

        // 먼저, 그룹별, 타입별 질문을 몽땅 가져오자
        questions = questionRepository.findAllByGroupAndType(questionGroup, 기업);

        // 먼저 list 만들어서
        list = new ArrayList<>();

        // for문 돌려서 AllAnswerResponse에 나머지 값 answerA, answerB 구하기
        for(int idx = 0; idx < questions.size(); idx++){
            AllAnswerResponse tmp = getAnswers(idx, questionGroup, 기업);

            list.add(tmp);
        }

        return list;
    }

    // 지난 vote 초기화
    public void iniAnswers(){
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime startOfToday = now.with(LocalTime.MIN).minusDays(32);

        Timestamp deleteDate = Timestamp.valueOf(startOfToday);

        // 이부분 다시 보기!
//        voteRepository.deleteByDate(deleteDate);
    }
}