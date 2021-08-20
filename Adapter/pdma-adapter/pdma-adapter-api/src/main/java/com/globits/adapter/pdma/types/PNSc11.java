package com.globits.adapter.pdma.types;

public enum PNSc11 {
	// UNKNOW
		UNKNOWN(-1,"Không rõ"),	
		answer1(1,"Mới nhiễm HIV và chưa điều trị"),
		answer2(2,"Chưa điều trị ARV"),
		answer3(3,"Điều trị ARV < 6 tháng"),
		answer4(4,"Điều trị ARV >= 6 tháng, TLVR >200 bản sao/ml"),
		answer5(5,"Điều trị ARV >= 6 tháng, nghi ngờ thất bại"),
		answer6(6,"Điều trị ARV >= 6 tháng, TLVR <= 200 bản sao/ml"),
		answer7(7,"Bỏ trị ARV");

		private final int number;
		private final String description;

		private PNSc11(int number,String description) {
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
