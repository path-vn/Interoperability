package com.globits.adapter.pdma.types;

public enum ClinicalTestingType {

	HIV_SCREENING(0),

	HIV_CONFIRMATION(1),

	CD4(2),

	VIRAL_LOAD(3),

	OTHER(4),

	HEP_B(5),

	HEP_C(6),

	ARV_DR(7),

	RECENCY(8);

	private final int number;

	private ClinicalTestingType(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		String ret = "";

		switch (this) {
			case HIV_SCREENING:
				ret = "Sàng lọc HIV";
				break;
			case HIV_CONFIRMATION:
				ret = "Khẳng định HIV";
				break;
			case CD4:
				ret = "CD4";
				break;
			case VIRAL_LOAD:
				ret = "Tải lượng virus";
				break;
			case OTHER:
				ret = "Xét nghiệm khác";
				break;
			case HEP_B:
				ret = "Viêm gan B";
				break;
			case HEP_C:
				ret = "Viêm gan C";
				break;
			case ARV_DR:
				ret = "Xét nghiệm kháng thuốc";
				break;
			case RECENCY:
				ret = "Xét nghiệm nhiễm mới";
				break;
		}

		return ret;
	}
}
