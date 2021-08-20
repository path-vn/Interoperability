package com.globits.adapter.opcassist.types;

public enum LabTestReason {
	VL_AT_6MONTH(0),

	VL_AT_12MONTH(1),

	VL_ROUTINE_12MONTH(2),

	VL_FOLLOWUP_3MONTH(3),

	VL_PREGNANCY(4),

	CD4_BASELINE(5),

	CD4_ROUTINE(6), 

	VL_RECENCY(7);

	private final int number;

	private LabTestReason(int number) {
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
				ret = "Tại thời điểm 6 tháng sau ART";
				break;
			case 1:
				ret = "Tại thời điểm 12 tháng sau ART";
				break;
			case 2:
				ret = "Định kỳ sau 12 tháng";
				break;
			case 3:
				ret = "Có biểu hiện nghi ngờ TBĐT";
				break;
			case 4:
				ret = "Phụ nữ mang thai, cho con bú";
				break;
			case 5:
				ret = "Trước khi bắt đầu ARV";
				break;
			case 6:
				ret = "Xét nghiệm định kỳ";
				break;
			case 7:
				ret = "XN TLVR để khẳng định nhiễm mới";
				break;
			default:
				break;
		}

		return ret;
	}
}
