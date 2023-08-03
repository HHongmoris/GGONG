package com.a304.ggong.service;

import com.a304.ggong.dto.response.AllUserResponse;
import com.a304.ggong.dto.response.GenderStatResponse;
import com.a304.ggong.dto.response.MachineStatResponse;
import com.a304.ggong.dto.response.TodayUserResponse;
import com.a304.ggong.entity.User;

public class StatusServiceImpl implements StatusService{

    @Override
    public Long selectUserCnt(TodayUserResponse response) {

        return null;
    }

    @Override
    public Long selectLastUserCnt(AllUserResponse response) {
        return null;
    }

    @Override
    public Long selectUserByAgeCnt(GenderStatResponse response) {
        return null;
    }

    @Override
    public Long selectUserByGenderCnt(GenderStatResponse response) {
        return null;
    }

    @Override
    public Long selectUserByMachine(MachineStatResponse response) {
        return null;
    }
}
