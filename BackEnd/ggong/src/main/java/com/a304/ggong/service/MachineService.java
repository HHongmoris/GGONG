package com.a304.ggong.service;

import java.util.List;

import com.a304.ggong.dto.response.AllMachinesResponse;
import com.a304.ggong.dto.response.LikeResponse;
import com.a304.ggong.dto.response.MachineDetailResponse;

public interface MachineService {

	// 모든 기기 조회
	List<AllMachinesResponse> selectAllMachines();

	// 관심 기기 목록 (email로 찾기)
	List<LikeResponse> selectAllFavoriteMachines(String email);

	// 특정 기기의 상세 정보 조회
	MachineDetailResponse selectMachineDetail(Long machineId);

	// 관심 기기 등록
	// Response를 프론트에 바로 보내줘버리기...?
	void insertFavoriteMachine(String email, Long machineNo);

	// 관심 기기 삭제
	void deleteFavoriteMachine(String email, Long machineNo);

}
