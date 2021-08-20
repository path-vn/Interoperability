package com.globits.adapter.pdma.types;

public enum HTSYesNoNone {

	YES(2),

	NO(1),

	NO_INFORMATION(3);

	private final int number;

	private HTSYesNoNone(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

}
