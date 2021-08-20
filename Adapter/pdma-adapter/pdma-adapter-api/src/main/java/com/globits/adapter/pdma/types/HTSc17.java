package com.globits.adapter.pdma.types;

public enum HTSc17 {
	// UNKNOW
	UNKNOWN(-1,"Không rõ"),	
	answer1(1,"Mới nhiễm HIV"),
	answer2(2,"Nhiễm HIV đã lâu"),
	answer3(3,"Không làm XN mới nhiễm");

	private final int number;
	private final String description;

	private HTSc17(int number,String description) {
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
