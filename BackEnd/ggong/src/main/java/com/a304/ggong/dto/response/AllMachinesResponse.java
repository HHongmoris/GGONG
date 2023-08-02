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
public class AllMachinesResponse {

	private long machineNo;
	private String name;
	private double latitude;
	private double longitude;

	public AllMachinesResponse(Machine entity) {
		this.machineNo = entity.getMachineNo();
		this.name = entity.getName();
		this.latitude = entity.getLatitude();
		this.longitude = entity.getLongitude();
	}
}
