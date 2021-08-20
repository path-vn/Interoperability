package com.globits.adapter.pdma.types;

public enum PNSHivStatus {
	UNKNOWN(-1,"Không rõ"),	
	answer1(1,"HIV+ đã được khẳng định"),
	answer2(2,"Không biết hoặc HIV-");

	private final int number;
	private final String description;

	private PNSHivStatus(int number,String description) {
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
