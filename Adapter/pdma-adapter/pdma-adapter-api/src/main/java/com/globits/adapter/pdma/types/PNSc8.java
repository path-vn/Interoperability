package com.globits.adapter.pdma.types;

public enum PNSc8 {
	// UNKNOW
		UNKNOWN(-1,"Không rõ"),	
		answer1(1,"Có, tại cơ sở này"),
		answer2(2,"Có, tại cơ sở khác"),
		answer3(3,"Không");

		private final int number;
		private final String description;

		private PNSc8(int number,String description) {
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
