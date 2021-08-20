package com.globits.facility.utilities;

import org.springframework.util.StringUtils;

public class FacilityEnums {
	public enum SystemType {
		/**
		 * HivInfo
		 */
		HIVINFO("HivInfo"),
		/**
		 * OpcAssist
		 */
		OPC_ASSIST("OpcAssist"),
		/**
		 * OpcEclinica
		 */
		OPC_ECLINICA("OpcEclinica"),
		/**
		 * HtcElog
		 */
		HTC_ELOG("HtcElog"),
		/**
		 * Pdma
		 */
		PDMA("Pdma");

		private final String value;

		private SystemType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public static SystemType get(String systemType) {
			if (systemType != null && StringUtils.hasText(systemType)) {
				if (systemType.toLowerCase().equals(SystemType.HIVINFO.getValue().toLowerCase())) {
					return HIVINFO;
				} else if (systemType.toLowerCase().equals(SystemType.OPC_ASSIST.getValue().toLowerCase())) {
					return OPC_ASSIST;
				} else if (systemType.toLowerCase().equals(SystemType.OPC_ECLINICA.getValue().toLowerCase())) {
					return OPC_ECLINICA;
				} else if (systemType.toLowerCase().equals(SystemType.HTC_ELOG.getValue().toLowerCase())) {
					return HTC_ELOG;
				} else if (systemType.toLowerCase().equals(SystemType.PDMA.getValue().toLowerCase())) {
					return PDMA;
				}
			}
			return null;
		}
	}

}
