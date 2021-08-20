package com.globits.adapter.pdma.types;

public enum HTSc131 {
	// UNKNOW
	UNKNOWN(-1,"Không rõ"),	
	answer1(1,"a. ≤ 12 tháng"),
	answer2(2,"b. > 12 tháng");

	private final int number;
	private final String description;

	private HTSc131(int number,String description) {
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
