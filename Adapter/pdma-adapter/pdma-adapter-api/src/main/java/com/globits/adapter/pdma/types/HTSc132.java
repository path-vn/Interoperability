package com.globits.adapter.pdma.types;

public enum HTSc132 {
	// UNKNOW
	UNKNOWN(-1,"Không rõ"),	
	answer1(1,"Chưa bao giờ điều trị ARV"),
	answer2(2,"Đang điều trị ARV"),
	answer3(3,"Đã bỏ điều trị ARV");

	private final int number;
	private final String description;

	private HTSc132(int number,String description) {
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
