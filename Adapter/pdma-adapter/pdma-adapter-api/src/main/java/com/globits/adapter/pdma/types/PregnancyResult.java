package com.globits.adapter.pdma.types;

public enum PregnancyResult {
	GAVEBIRTH(0),

	MISCARRIAGE(1),

	STILLBIRTH(2),

	ABORTION(3),

	UNKNOWN(4);

	private final int number;

	private PregnancyResult(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		String ret = "";
		switch (this) {
			case GAVEBIRTH:
				ret = "Đã sinh";
				break;
			case MISCARRIAGE:
				ret = "Bị sảy thai";
				break;
			case STILLBIRTH:
				ret = "Chưa sinh";
				break;
			case ABORTION:
				ret = "Đã nạo thai";
				break;
			case UNKNOWN:
				ret = "Không rõ";
				break;
		}

		return ret;
	}
}
