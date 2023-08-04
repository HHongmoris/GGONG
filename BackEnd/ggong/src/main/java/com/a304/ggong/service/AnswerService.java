package com.a304.ggong.service;

import java.util.List;

import com.a304.ggong.dto.response.AllAnswerResponse;

public interface AnswerService {

	// service단에서 각각의 resDto에 answerA, answerB와 rateA, rateB의 값을 넣어줘야함.

	// 질문 상세 페이지

	// 공통 질문 응답 데이터 조회
	public List<AllAnswerResponse> getAnswersGroupByCommon();

	// 대학 질문 응답 데이터 조회
	public List<AllAnswerResponse> getAnswersGroupByUnis();

	// 기업 질문 응답 데이터 조회
	public List<AllAnswerResponse> getAnswersGroupByCompanies();
}
