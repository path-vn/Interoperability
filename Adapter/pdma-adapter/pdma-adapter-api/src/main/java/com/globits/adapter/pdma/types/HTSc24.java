package com.globits.adapter.pdma.types;

public enum HTSc24 {
	// UNKNOW
	UNKNOWN(-1,"Không rõ"),	
	answer1(1,"Khách hàng dương tính mới"),
	answer2(2,"Khách hàng dương tính cũ"),
	answer3(3,"Đang chờ kết quả xác minh");

	private final int number;
	private final String description;

	private HTSc24(int number,String description) {
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
