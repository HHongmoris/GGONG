package com.a304.ggong.dto.response;

import com.a304.ggong.entity.Machine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MachineStatResponse {
    private String machineName;
    private Long userCount;

    public MachineStatResponse(Machine entity){
        this.machineName = entity.getName();
    }
}
