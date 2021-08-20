package com.globits.adapter.opcassist.types;

public enum EnrollmentType {
	NEWLY_ENROLLED(1),

	RETURNED(2),

	TRANSFERRED_IN(3);

	private final int number;

	private EnrollmentType(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		String ret = "";
		switch (this.number) {
			case 1:
				ret = "Đăng ký mới";
				break;
			case 2:
				ret = "Điều trị lại";
				break;
			case 3:
				ret = "Chuyển đến";
				break;
			default:
				break;
		}

		return ret;
	}
}
