package com.globits.adapter.pdma.types;

public enum HTSc20 {
	// UNKNOW
	UNKNOWN(-1,"Không rõ"),	
	answer1(1,"Chưa có thông tin"),
	answer2(2,"Không"),
	answer3(3,"Có");

	private final int number;
	private final String description;

	private HTSc20(int number,String description) {
		this.number = number;
		this.description = description;
	}

	public int getNumber() {
		return number;
	}

	public String getDescription() {
		return description;
	}
}
