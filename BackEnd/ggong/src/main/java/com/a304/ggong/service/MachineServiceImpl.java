package com.a304.ggong.service;

import com.a304.ggong.dto.request.LikeDeleteRequest;
import com.a304.ggong.dto.request.LikeRegistRequest;
import com.a304.ggong.dto.response.AllMachinesResponse;
import com.a304.ggong.dto.response.LikeResponse;
import com.a304.ggong.dto.response.MachineDetailResponse;
import com.a304.ggong.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor // 생성자 주입
@Service
public class MachineServiceImpl implements MachineService{

    // repo
    private final MachineRepository machineRepository;

    @Override
    public List<AllMachinesResponse> getAllMachines() {
    }

    @Override
    public List<LikeResponse> getAllFavoriteMachines(String email) {
        return null;
    }

    @Override
    public MachineDetailResponse getMachineDetail(Long machineId) {
        return null;
    }

    @Override
    public LikeResponse addFavoriteMachine(String email, LikeRegistRequest entity) {
        return null;
    }

    @Override
    public LikeResponse deleteFavoriteMachine(LikeDeleteRequest entity) {
        return null;
    }
}
