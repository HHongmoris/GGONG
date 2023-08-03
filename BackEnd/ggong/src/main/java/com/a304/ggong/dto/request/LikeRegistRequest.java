package com.a304.ggong.dto.request;

import com.a304.ggong.entity.FavoriteMachine;
import com.a304.ggong.entity.Machine;
import com.a304.ggong.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeRegistRequest {

	private Long machineNo;

	// Controller에서는 프론트에서 machineNo로 받고,
	// 그것을 service에서 findByMachineNo 메소드를 이용하여
	// Machine 객체를 리턴받고
	// 그것을 service에서 LikeRegistRequest.toEntity(Machine)호출하여 엔티티에 넣어주기
	public FavoriteMachine toEntity(User user, Machine machine) {
		return FavoriteMachine.builder().user(user).machine(machine).build();
	}
}
