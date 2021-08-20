package com.globits.adapter.pdma.types;

public enum PNSCaseContactRelationshipType {
	// UNKNOW
		UNKNOWN(-1,"Không rõ"),	
		answer1(1,"Vợ/chồng"),
		answer2(2,"Bạn tình khác"),
		answer3(3,"Bạn TCMT chung"),
		answer4(4,"Con đẻ ≤15 tuổi của mẹ HIV+"),
		answer5(5,"Mẹ sinh con ≤15 tuổi HIV+");

		private final int number;
		private final String description;

		private PNSCaseContactRelationshipType(int number,String description) {
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
