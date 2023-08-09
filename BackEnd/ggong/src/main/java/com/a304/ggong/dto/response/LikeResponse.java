package com.a304.ggong.dto.response;

import com.a304.ggong.entity.FavoriteMachine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponse {

	private long machineNo;
	private String name;

	public LikeResponse(FavoriteMachine entity) {
		this.machineNo = entity.getMachine().getMachineNo();
		this.name = entity.getMachine().getName();
	}
}
