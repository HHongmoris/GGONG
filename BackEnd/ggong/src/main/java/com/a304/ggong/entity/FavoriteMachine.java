package com.a304.ggong.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "favoritemachine")
@Getter
@Setter
public class FavoriteMachine {

	// 얘는 다대다를 일대다, 다대일로 뺀 테이블이므로
	// 머지하고 작성하겠습니다!
	// 왜냐믄 User랑 연결된거거등
}
