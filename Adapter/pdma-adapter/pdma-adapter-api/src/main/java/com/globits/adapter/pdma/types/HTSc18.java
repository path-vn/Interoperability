package com.globits.adapter.pdma.types;

public enum HTSc18 {
	// UNKNOW
	UNKNOWN(-1,"Không rõ"),	
	answer1(1,"< 1.000 bản sao/ml"),
	answer2(2,"≥ 1.000 bản sao/ml"),
	answer3(3,"Không làm XN tải lượng vi-rút"),
	answer4(4,"Đang chờ kết quả XN tải lượng vi-rút");

	private final int number;
	private final String description;

	private HTSc18(int number,String description) {
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