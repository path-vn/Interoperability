/**
 * 
 */
package com.globits.adapter.eclinica;

public final class Constants {

	/**
	 * User names
	 */
	public static final String USER_ADMIN_USERNAME = "admin";

	/**
	 * User roles
	 */
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	public static final String ROLE_SITE_MANAGER = "ROLE_SITE_MANAGER";

	public static final String ROLE_DISTRICT_MANAGER = "ROLE_DISTRICT_MANAGER";

	public static final String ROLE_PROVINCIAL_MANAGER = "ROLE_PROVINCIAL_MANAGER";

	public static final String ROLE_NATIONAL_MANAGER = "ROLE_NATIONAL_MANAGER";

	public static final String ROLE_DONOR = "ROLE_DONOR";

	public static final String ROLE_USER = "ROLE_USER";

	/**
	 * Administrative unit maximum level
	 */
	public static final String PROP_ADMINUNIT_MAX_LEVEL = "adminunit.level.max";

	/**
	 * Predefined durations for tasks
	 */
	public static final String PROP_FREEZED_DURATION = "status.freezed.duration";

	public static final String PROP_PNS_BASELINE_TO_DATE = "pns_assessment_date.baseline_date";

	public static final String PROP_PNS_POST_FROM_DATE = "pns_assessment_date.post_from_date";

	public static final String PROP_PNS_POST_TO_DATE = "pns_assessment_date.post_to_date";

	/**
	 * List of provinces that have OPC-Assist implemented
	 */
	public static final String PROP_OPCASSIST_PROVINCES = "opcassist_provinces";

	/**
	 * Other organization
	 * 
	 * Because we may not be able to capture all organizations, we create this one
	 * to support for tracking Case-Org data, especially when referring patients
	 * from one facility to another
	 */
	public static final String CODE_ORGANIZATION_OTHER = "organization_other_specified";

}
