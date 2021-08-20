package com.globits.adapter.pdma.types;

public enum Gender {

	MALE(0),

	FEMALE(1),

	OTHER(2),

	NOT_DISCLOSED(3);

	private final int number;

	private Gender(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		String ret = "";
		switch (this.number) {
			case 0:
				ret = "Nam";
				break;
			case 1:
				ret = "Nữ";
				break;
			case 2:
				ret = "Khác";
				break;
			case 3:
				ret = "Không tiết lộ";
				break;
			default:
				break;
		}

		return ret;
	}
}
