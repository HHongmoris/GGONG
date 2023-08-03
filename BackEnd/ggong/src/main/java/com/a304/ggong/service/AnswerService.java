package com.a304.ggong.service;

import com.a304.ggong.dto.response.*;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface AnswerService {

    // service단에서 각각의 resDto에 answerA와 answerB의 값을 넣어줘야함.
    // universityName도 마찬가지!
    // companyName도 마찬가지!

	// 모든 질문 응답 데이터 조회
    public List<AllAnswerResponse> getAllAnswers(int questionGroup);

	// 지역별 질문 응답 데이터 조회
    public List<AreaAnswerResponse> getAnswersGroupByArea();

	// 연령별 질문 응답 데이터 조회
    public List<AgeAnswerResponse> getAnswersGroupByAge();

	// 대학별 질문 응답 데이터 조회
    public List<UniversityAnswerResponse> getAnswersGroupByUni();

	// 회사별 질문 응답 데이터 조회
    public List<CompanyAnswerResponse> getAnswersGroupByCompany();
}
