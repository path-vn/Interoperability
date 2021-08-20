package com.globits.adapter.pdma.types;

public enum HTSc5 {
	// UNKNOW
	UNKNOWN(-1,"Không rõ"),
	
	// 
	answer1(1,"Khoa phòng bệnh viện (không bao gồm PKNT HIV)"),

	// 
	answer2(2,"Cơ sở TVXN HIV (VCT, PKNT, MMT, PrEP, trại giam)");

	private final int number;
	private final String description;
	private HTSc5(int number,String description) {
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

