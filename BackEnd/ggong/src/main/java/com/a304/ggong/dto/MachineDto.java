package com.a304.ggong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MachineDto {

	private int machineNo;
	private double latitude;
	private double longitude;
	private String name;
	private String SN;
	private String areaGu;
}
