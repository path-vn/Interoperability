package com.globits.adapter.pdma.types;

public enum HTSc14 {
	// UNKNOW
	UNKNOWN(-1,"Không rõ"),	
	answer1(1,"Âm tính"),
	answer2(2,"Dương tính"),
	answer3(3,"Không xác định"),
	answer4(4,"Không làm xét nghiệm");

	private final int number;
	private final String description;

	private HTSc14(int number,String description) {
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
