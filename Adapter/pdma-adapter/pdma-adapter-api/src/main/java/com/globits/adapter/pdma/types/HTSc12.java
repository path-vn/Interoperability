package com.globits.adapter.pdma.types;

public enum HTSc12 {
	// UNKNOW
	UNKNOWN(-1,"Không rõ"),	
	answer1(1,"Chưa bao giờ XN HIV"),
	answer2(2,"Có, kết quả XN âm tính"),
	answer3(3,"Có, kết quả XN dương tính"),
	answer4(4,"Có, không xác định hoặc không biết kết quả XN");

	private final int number;
	private final String description;

	private HTSc12(int number,String description) {
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
