package com.globits.adapter.opcassist.types;

public enum LabTestFundingSource {
	SHI(0),

	GF(1),

	SELF(2),

	DRIVE_C(3),
	
	OTHER(4);

	private final int number;

	private LabTestFundingSource(int number) {
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
				ret = "Bảo hiểm y tế";
				break;
			case 1:
				ret = "Quỹ toàn cầu";
				break;
			case 2:
				ret = "Tự chi trả";
				break;
			case 3:
				ret = "Dự án Drive-C";
				break;
			case 4:
				ret = "Nguồn khác";
				break;
			default:
				break;
		}

		return ret;
	}
}
