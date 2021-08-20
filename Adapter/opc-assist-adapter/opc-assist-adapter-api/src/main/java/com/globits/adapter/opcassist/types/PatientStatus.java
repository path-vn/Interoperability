package com.globits.adapter.opcassist.types;

public enum PatientStatus {
	// NULL
		NULL(0),

		// When a patient is enrolled in an organization
		ACTIVE(1),

		// When a patient is confirmed lost to follow up
		LTFU(2),

		// When a patient is confirmed dead
		DEAD(3),

		// When a patient is transferred out of an organization
		TRANSFERRED_OUT(4),

		// When a patient is referred to an organization but not yet arrived
		PENDING_ENROLLMENT(5),

		// When a patient is transferred, but did not arrive at the receiving site
		// Instead, that patient is confirmed cancelled/LTFU, or enroll at another site
		// or return to the referring site
		CANCELLED_ENROLLMENT(6);

		private final int number;

		private PatientStatus(int number) {
			this.number = number;
		}

		public int getNumber() {
			return number;
		}

		@Override
		public String toString() {
			String ret = "";
			switch (this) {
				case NULL:
					ret = "Không có thông tin";
					break;
				case ACTIVE:
					ret = "Đang quản lý/điều trị";
					break;
				case LTFU:
					ret = "Đã bỏ trị";
					break;
				case DEAD:
					ret = "Đã tử vong";
					break;
				case TRANSFERRED_OUT:
					ret = "Đã chuyển đi";
					break;
				case PENDING_ENROLLMENT:
					ret = "Chờ tiếp nhận";
					break;
				case CANCELLED_ENROLLMENT:
					ret = "Không tới đăng ký";
					break;
			}

			return ret;
		}
}
