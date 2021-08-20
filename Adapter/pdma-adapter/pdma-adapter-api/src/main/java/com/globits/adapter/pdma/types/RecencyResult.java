package com.globits.adapter.pdma.types;

public enum RecencyResult {
	RECENT(0),

	OLD(1),

	NEGATIVE(2),

	INDETERMINATE(3);

	private final int number;

	private RecencyResult(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		String ret = "";

		switch (this) {
			case RECENT:
				ret = "Nhiễm mới";
				break;
			case OLD:
				ret = "Nhiễm cũ";
				break;
			case NEGATIVE:
				ret = "Âm tính";
				break;
			case INDETERMINATE:
				ret = "Không xác định";
				break;
		}

		return ret;
	}
}
