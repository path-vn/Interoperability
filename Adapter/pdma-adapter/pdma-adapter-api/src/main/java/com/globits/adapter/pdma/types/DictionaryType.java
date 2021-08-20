package com.globits.adapter.pdma.types;

public enum DictionaryType {
	RESIDENCE_REGISTRATION_TYPE(0),

	OCCUPATION(1),

	MONTHLY_INCOME(2),

	WEALTH_STATUS(3),

	ARV_REGIMEN(4),

	NO_SHI_REASON(5),

	SERVICE_USED_PAIDBY_SHI(6),

	TESTING_MODALITY(7),

	VCT_CLIENT_TYPE(8),

	HIV_STATUS(9),

	HIV_RESULT(10),

	RISK(11),

	// Other

	EDUCATION(12),

	ETHNIC(13),

	DISEASE(14),

	NATIONALITY(15),

	RELIGION(16),

	REASON_FOR_VL_TESTING(17),

	VL_FUNDING_SOURCE(18),

	REASON_FOR_CD4_TESTING(19),

	REASON_FOR_MMT_STOP(20);

	private final int number;

	private DictionaryType(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
}
