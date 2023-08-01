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
public class VoteDto {

	private Long voteNo;
	private Long userNo;
	private Long machineNo;
	private Long questionID;
	private Long voteDate;
	private Long answer;
}
