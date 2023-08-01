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
public class FavoriteMachineDto {

	private Long favoriteMachineNo;
	private Long userNo;
	private Long machineNo;
}
