package com.globits.adapter.pdma.types;

//C5. Loại hình dịch vụ TVXN HIV:
public enum HTSc5Note {
	// UNKNOW
	UNKNOWN(-1,"Không rõ"),	
	answer1(1,"Tư vấn xét nghiệm tự nguyện (VCT)"),
	answer2(2,"TVXN HIV tại phòng khám ngoại trú"),
	answer3(3,"TVXN HIV tại cơ sở điều trị Methadone"),
	answer4(4,"TVXN HIV tại cơ sở điều trị PrEP"),
	answer5(5,"Khoa phòng bệnh viện (không bao gồm PKNT HIV)"),
	answer6(6,"TVXN HIV tại trại giam, trại tạm giam");

	private final int number;
	private final String description;
	private HTSc5Note(int number,String description) {
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
