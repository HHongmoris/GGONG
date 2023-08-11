package com.a304.ggong.exception;

public class FavoriteMachineNotFoundException extends Exception {

	public FavoriteMachineNotFoundException() {
		super("등록하신 관심 기기가 없습니다.");
	}
}
