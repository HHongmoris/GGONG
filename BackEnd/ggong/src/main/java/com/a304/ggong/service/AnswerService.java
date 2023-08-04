package com.a304.ggong.service;

import java.util.List;

import com.a304.ggong.dto.response.AllAnswerResponse;
import com.a304.ggong.dto.response.AnswerDetailResponse;

public interface AnswerService {

	// service단에서 각각의 resDto에 answerA, answerB와 rateA, rateB의 값을 넣어줘야함.

	// 지역별 질문 응답 데이터 조회
	public List<AnswerDetailResponse> getAnswersGroupByArea();

	// 연령별 질문 응답 데이터 조회
	public List<AnswerDetailResponse> getAnswersGroupByAge();

	// 대학별 질문 응답 데이터 조회
	public List<AnswerDetailResponse> getAnswersGroupByUni();

	// 회사별 질문 응답 데이터 조회
	public List<AnswerDetailResponse> getAnswersGroupByCompany();

	// 공통 질문 응답 데이터 조회
	public List<AllAnswerResponse> getAnswersGroupByCommon();

	// 대학 질문 응답 데이터 조회
	public List<AllAnswerResponse> getAnswersGroupByUnis();

	// 기업 질문 응답 데이터 조회
	public List<AllAnswerResponse> getAnswersGroupByCompanies();
}
