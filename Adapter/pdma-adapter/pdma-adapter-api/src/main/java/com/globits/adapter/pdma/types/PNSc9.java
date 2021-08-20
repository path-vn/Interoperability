package com.globits.adapter.pdma.types;

public enum PNSc9 {
	// UNKNOW
		UNKNOWN(-1,"Không rõ"),	
		answer1(1,"HIV âm tính"),
		answer2(2,"Khẳng định HIV dương tính"),
		answer3(3,"Không xác định"),
		answer4(4,"Từ chối trả lời");

		private final int number;
		private final String description;

		private PNSc9(int number,String description) {
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
