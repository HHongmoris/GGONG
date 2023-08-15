package com.a304.ggong.dto.response;

import com.a304.ggong.entity.Machine;
import com.a304.ggong.entity.Question;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MachineDetailResponse {

	private Long machineNo;
	private String name;
	// 혼잡도 계산은 어디서? Controller나 service에서 계산하고 넣어줘야하나?
	private long[] userCount; // service에서 15분 단위로 사용자 카운트 해서
	private String content;
	// 얘도 계산 어디서..? -> service에서 해주기
	private Long answerA;
	private Long answerB;
	private String optionA;
	private String optionB;

	public MachineDetailResponse(Machine entityM, Question entityQ) {
		this.machineNo = entityM.getMachineNo();
		this.name = entityM.getName();
		this.content = entityQ.getContent();
		this.optionA = entityQ.getOptionA();
		this.optionB = entityQ.getOptionB();
	}

	// 혼잡도를 넣어줄 배열을 생성하는 메소드
	public long[] setUserCount() {
		this.userCount = new long[96];
		return this.userCount;
	}

}
