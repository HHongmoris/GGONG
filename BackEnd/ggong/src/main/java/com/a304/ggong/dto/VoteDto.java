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

	private int voteNo;
	private int userNo;
	private int machineNo;
	private int questionID;
	private int voteDate;
	private int answer;
}
