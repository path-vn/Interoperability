package com.globits.cbs.deidentification.utilities;

public class Enums {
	public enum ReferralStatusEnum {
		Draft("draft"),
		Active("active"),
		Cancelled("cancelled"),
		Completed("completed")
		;
		
		private final String name;
		private ReferralStatusEnum(final String name) {
			this.name=name;
		}
		public String getName() {
			return name;
		}
		
	}
	public enum AddressUseEnum {
	    HOME("home"),
	    WORK("work");

	    private final String name;

	    /**
	     * @param name
	     */
	    private AddressUseEnum(final String name) {
	        this.name = name;
	    }

	    public String getName() {
	        return name;
	    }
	}
}
