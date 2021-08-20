package com.globits.hivimportcsadapter;

import org.springframework.util.StringUtils;

import com.globits.fhir.hiv.utils.Valueset;
import com.globits.hivimportcsadapter.dto.SystemCodeDto;

public class ServerConst {
	public static String HIVINFO = "hiv-info";
	public static String HTCELOG = "htc-elog";
	public static String OPCASSIST = "opc-assist";
	public static String OPCECLINICA = "opc-eclinica";

	public enum ConceptDataType{
		coded(0),//Concept
		booleans(1),//True-False
		datetimes(2),//Date
		numeric(3),//numeric
		text(4)//text
		;
		private Integer value;
		private ConceptDataType(int value) {
		    this.value = value;
		}
	
		public Integer getValue() {
			return value;
		}
	}
	
	public enum GenderType {
		unidentified("Unidentified"), female("Female"), male("Male");
		
		private String value;
		
		private GenderType(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}

	}
	
	public enum ResultRapidTestType {
		recent, long_term;
		
		public SystemCodeDto getData() {
			switch (this) {
			case recent:
				return new SystemCodeDto("recent", "Recent", "");
			case long_term:
				return new SystemCodeDto("long_term", "Long term", "");
			default:
				return null;
			}
		}

	}
	
	public static String getGenderTypeEnumFromString(String gender) {
		switch(gender){
		case "Nam":
			return GenderType.male.getValue();
		case "Nữ":
			return GenderType.female.getValue();
		default:
			return null;
		}
	}

	public static String getGenderTypeFromString(String value) {
		switch(value){
		case "F":
			return "Nữ";
		case "M":
			return "Nam";
		default:
			return null;
		}
	}
	public enum testResult {
		Negative("1"), Positive("2");
		
		private String value;
		
		private testResult(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}

	}
	public enum OccupationValueset {
		ARMY, GOV_EMPL, WORKER, STUDENT, FREE_LANCER, UNEMPLOYMENT, CHILD, PRISONER, FAMER, OTHER;

		public SystemCodeDto getData() {
			switch (this) {
			case ARMY:
				return new SystemCodeDto("army", "Army personnel", "");
			case GOV_EMPL:
				return new SystemCodeDto("gov_empl", "Government employee", "");
			case WORKER:
				return new SystemCodeDto("worker", "Worker", "");
			case STUDENT:
				return new SystemCodeDto("student", "Pupil/Student", "");
			case FREE_LANCER:
				return new SystemCodeDto("free_lancer", "Free lancer", "");
			case UNEMPLOYMENT:
				return new SystemCodeDto("unemployment", "Unemployment", "");
			case CHILD:
				return new SystemCodeDto("child", "Children", "");
			case PRISONER:
				return new SystemCodeDto("prisoner", "Prisoner", "");
			case FAMER:
				return new SystemCodeDto("famer", "Farmer", "");
			case OTHER:
				return new SystemCodeDto("other", "Other", "");
			default:
				return null;
			}
		}
	}
	
	public static SystemCodeDto getOccupationByText(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("9")) {
				return ServerConst.OccupationValueset.CHILD.getData();
			} else if (value.toLowerCase().contains("8")) {
				return ServerConst.OccupationValueset.STUDENT.getData();
			} else if (value.toLowerCase().contains("4") || value.toLowerCase().contains("nông nghiệp")) {
				return ServerConst.OccupationValueset.FAMER.getData();
			} else if (value.toLowerCase().contains("11")) {
				return ServerConst.OccupationValueset.UNEMPLOYMENT.getData();
			} else if (value.toLowerCase().contains("5")) {
				return ServerConst.OccupationValueset.WORKER.getData();
			} else if (value.toLowerCase().contains("12") || value.toLowerCase().contains("phạm nhân")) {
				return ServerConst.OccupationValueset.PRISONER.getData();
			} else if (value.toLowerCase().contains("6")) {
				return ServerConst.OccupationValueset.ARMY.getData();
			} else {
				return ServerConst.OccupationValueset.OTHER.getData();
			}
		}
		return null;
	}
	public static SystemCodeDto getResultRapidTestTypeEnumFromString(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("1")) {
				return ServerConst.ResultRapidTestType.recent.getData();
			} else if (value.toLowerCase().contains("2")) {
				return ServerConst.ResultRapidTestType.long_term.getData();
			} 
		}
		return null;
	}
	public enum PopulationGroupValueset {
		PWID, MSM, SW, CLIENT_SW, PARTNER_PLHIV, TG_WOMEN, TG_MEN, CHILD_WOMAN_HIV, CLIENT_SEXW, BLOOD_DONOR, SUSPECT_HIV, TB_PATIENT, STD_PERSON, PRISONER, PREGNANT, MILITARY_RECRUITS, OTHER;

		public SystemCodeDto getData() {
			switch (this) {
			case PWID:
				return new SystemCodeDto("pwid", "Injecting drug users", "");
			case MSM:
				return new SystemCodeDto("msm", "Men who have sex with men", "");
			case SW:
				return new SystemCodeDto("sw", "Sex workers", "");
			case CLIENT_SW:
				return new SystemCodeDto("client_sw", "Clients of sex worker", "");
			case PARTNER_PLHIV:
				return new SystemCodeDto("partner_plhiv", "Sexual partner of PLHIV", "");
			case TG_WOMEN:
				return new SystemCodeDto("tg_women", "Transgender women", "");
			case TG_MEN:
				return new SystemCodeDto("tg_men", "Transgender men", "");
			case CHILD_WOMAN_HIV:
				return new SystemCodeDto("child_woman_hiv", "Child of mother who is infected with HIV", "");
			case CLIENT_SEXW:
				return new SystemCodeDto("client_sexw", "Client of sex workers", "");
			case BLOOD_DONOR:
				return new SystemCodeDto("blood-donor", "Blood seller / blood donor", "");
			case SUSPECT_HIV:
				return new SystemCodeDto("suspect_hiv", "People suspected for AIDs", "");
			case TB_PATIENT:
				return new SystemCodeDto("tb_patient", "Tuberculosis patient", "");
			case STD_PERSON:
				return new SystemCodeDto("std_person", "Persons with STD", "");
			case PRISONER:
				return new SystemCodeDto("prisoner", "Prisoner", "");
			case PREGNANT:
				return new SystemCodeDto("pregnant", "Pregnant women", "");
			case MILITARY_RECRUITS:
				return new SystemCodeDto("military_recruits", "	Young recruits for military service", "");
			case OTHER:
				return new SystemCodeDto("other", "Other", "");
			default:
				return null;
			}
		}
	}
	public enum TransmissionRouteValueset {
		BLOOD, DRUG_INJECTION, BLOOD_TRANSFUSION, OCCUPATIONAL_ACCIDENT, SEXUAL_TRANSMISSION, HOMOSEXUAL, HETEROSEXUAL, MOTHER_TO_CHILD_TRANSMISSION, UNIDENTIFIED;

		public SystemCodeDto getData() {
			switch (this) {
			case BLOOD:
				return new SystemCodeDto("312425004", "Blood", "");
			case DRUG_INJECTION:
				return new SystemCodeDto("226034001", "Drug injection", "");
			case BLOOD_TRANSFUSION:
				return new SystemCodeDto("250373003", "Blood transfusion", "");
			case OCCUPATIONAL_ACCIDENT:
				return new SystemCodeDto("53348004", "Occupational accident", "");
			case SEXUAL_TRANSMISSION:
				return new SystemCodeDto("417564009", "Sexual Transmission", "");
			case HOMOSEXUAL:
				return new SystemCodeDto("38628009", "Homosexual", "");
			case HETEROSEXUAL:
				return new SystemCodeDto("20430005", "Heterosexual", "");
			case MOTHER_TO_CHILD_TRANSMISSION:
				return new SystemCodeDto("438998000", "Mother to Child Transmission", "");
			case UNIDENTIFIED:
				return new SystemCodeDto("69910005", "Unidentified", "");
			default:
				return null;
			}
		}
	}
	public static SystemCodeDto getTransmissionRouteByText(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("1")) {
				return ServerConst.TransmissionRouteValueset.BLOOD.getData();
			} else if (value.toLowerCase().contains("2")) {
				return ServerConst.TransmissionRouteValueset.SEXUAL_TRANSMISSION.getData();
			} else if (value.toLowerCase().contains("3")) {
				return ServerConst.TransmissionRouteValueset.MOTHER_TO_CHILD_TRANSMISSION.getData();
			} else {
				return ServerConst.OccupationValueset.OTHER.getData();
			}
		}
		return null;
	}
	
	public enum RiskBehaviorValueset {
		PWID, SEX_PARTNER, MSM, SMP, PARTNER_HIV, OTHER;

		public SystemCodeDto getData() {
			switch (this) {
			case PWID:
				return new SystemCodeDto("pwid", "PWID", "");
			case MSM:
				return new SystemCodeDto("msm", "Men who have sex with men", "");
			case SEX_PARTNER:
				return new SystemCodeDto("sex_partner", "Having sex with sex worker or sex buyer", "");
			case SMP:
				return new SystemCodeDto("smp", "Having sex with multiple partners (not for money or drug)", "");
			case PARTNER_HIV:
				return new SystemCodeDto("partner_hiv", "Having sex with spouse/partners who are HIV infected", "");
			case OTHER:
				return new SystemCodeDto("other", "Other", "");
			default:
				return null;
			}
		}
	}
	////
	
	public enum EthnicityValueset {
		KINH,TAY,THAI,HOA,KHO_ME,MUONG,NUNG,HMONG,DAO,GIA_RAI,NGAI,E_DE,BA_NA,XO_DANG,SAN_CHAY,CO_HO,CHAM,SAN_DIU,HRE,MNONG,RA_GLAI,XTIENG,BRU_VAN_KIEU,THO,
		GIAY,CO_TU,GIE_TRIENG,MA,KHO_MU,CO,TA_OI,CHO_RO,KHANG,XINH_MUN,HA_NHI,CHU_RU,LAO,LA_CHI,LA_HA,PHU_LA,LA_HU,LU,LO_LO,CHUT,MANG,PA_THEN,CO_LAO,CONG,
		BO_Y,SI_LA,PU_PEO,BRAU,O_DU,RO_MAM,NGUOI_NUOC_NGOAI,KHONGRO
;

		public SystemCodeDto getData() {
			switch (this) {
			case KINH:
				return new SystemCodeDto("1", "Kinh", "");
			case TAY:
				return new SystemCodeDto("2", "Tày", "");
			case THAI:
				return new SystemCodeDto("3", "Thái", "");
			case HOA:
				return new SystemCodeDto("4", "Hoa", "");
			case KHO_ME:
				return new SystemCodeDto("5", "Khơ-me", "");
			case MUONG:
				return new SystemCodeDto("6", "Mường", "");
			case NUNG:
				return new SystemCodeDto("7", "Nùng", "");
			case HMONG:
				return new SystemCodeDto("8", "Hmông", "");
			case DAO:
				return new SystemCodeDto("9", "Dao", "");
			case GIA_RAI:
				return new SystemCodeDto("10", "Gia-rai", "");
			case NGAI:
				return new SystemCodeDto("11", "Ngái", "");
			case E_DE:
				return new SystemCodeDto("12", "Ê-đê", "");	
			case BA_NA:
				return new SystemCodeDto("13", "Ba-na", "");
			case XO_DANG:
				return new SystemCodeDto("14", "Xơ-đăng", "");
			case SAN_CHAY:
				return new SystemCodeDto("15", "Sán Chay", "");
			case CO_HO:
				return new SystemCodeDto("16", "Cơ-ho", "");
			case CHAM:
				return new SystemCodeDto("17", "Chăm", "");
			case SAN_DIU:
				return new SystemCodeDto("18", "Sán Dìu", "");
			case HRE:
				return new SystemCodeDto("19", "Hrê", "");
			case MNONG:
				return new SystemCodeDto("20", "Mnông", "");
			case RA_GLAI:
				return new SystemCodeDto("21", "Ra-glai", "");
			case XTIENG:
				return new SystemCodeDto("22", "Xtiêng", "");
			case BRU_VAN_KIEU:
				return new SystemCodeDto("23", "Bru-Vân Kiều", "");
			case THO:
				return new SystemCodeDto("24", "Thổ", "");
			case GIAY:
				return new SystemCodeDto("25", "Giáy", "");
			case CO_TU:
				return new SystemCodeDto("26", "Cơ-tu", "");
			case GIE_TRIENG:
				return new SystemCodeDto("27", "Gié-Triêng", "");
			case MA:
				return new SystemCodeDto("28", "Mạ", "");
			case KHO_MU:
				return new SystemCodeDto("29", "Khơ-mú", "");
			case CO:
				return new SystemCodeDto("30", "Co", "");
			case TA_OI:
				return new SystemCodeDto("31", "Ta-ôi", "");
			case CHO_RO:
				return new SystemCodeDto("32", "Chơ-ro", "");
			case KHANG:
				return new SystemCodeDto("33", "Kháng", "");
			case XINH_MUN:
				return new SystemCodeDto("34", "Xinh-mun", "");
			case HA_NHI:
				return new SystemCodeDto("35", "Hà Nhì", "");
			case CHU_RU:
				return new SystemCodeDto("36", "Chu-ru", "");
			case LAO:
				return new SystemCodeDto("37", "Lào", "");
			case LA_CHI:
				return new SystemCodeDto("38", "La Chi", "");
			case LA_HA:
				return new SystemCodeDto("39", "La Ha", "");
			case PHU_LA:
				return new SystemCodeDto("40", "Phù Lá", "");
			case LA_HU:
				return new SystemCodeDto("41", "La Hủ", "");
			case LU:
				return new SystemCodeDto("42", "Lự", "");
			case LO_LO:
				return new SystemCodeDto("43", "Lô Lô", "");
			case CHUT:
				return new SystemCodeDto("44", "Chứt", "");
			case MANG:
				return new SystemCodeDto("45", "Mảng", "");
			case PA_THEN:
				return new SystemCodeDto("46", "Pà Thẻn", "");
			case CO_LAO:
				return new SystemCodeDto("47", "Cơ Lao", "");
			case CONG:
				return new SystemCodeDto("48", "Cống", "");
			case BO_Y:
				return new SystemCodeDto("49", "Bố Y", "");
			case SI_LA:
				return new SystemCodeDto("50", "Si La", "");
			case PU_PEO:
				return new SystemCodeDto("51", "Pu Péo", "");
			case BRAU:
				return new SystemCodeDto("52", "Brâu", "");
			case O_DU:
				return new SystemCodeDto("53", "Ơ Đu", "");
			case RO_MAM:
				return new SystemCodeDto("54", "Rơ-măm", "");
			case NGUOI_NUOC_NGOAI:
				return new SystemCodeDto("55", "Người nước ngoài", "");
			case KHONGRO:
				return new SystemCodeDto("0", "Không rõ", "");
			default:
				return null;
			}
		}
	}
	//
	public static SystemCodeDto getEthnicityByText(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("1")) {
				return ServerConst.EthnicityValueset.KINH.getData();
			} else if (value.toLowerCase().contains("2")) {
				return ServerConst.EthnicityValueset.TAY.getData();
			} else if (value.toLowerCase().contains("3")) {
				return ServerConst.EthnicityValueset.THAI.getData();
			}else if (value.toLowerCase().contains("4")) {
				return ServerConst.EthnicityValueset.HOA.getData();
			}else if (value.toLowerCase().contains("5")) {
				return ServerConst.EthnicityValueset.KHO_ME.getData();
			}else if (value.toLowerCase().contains("6")) {
				return ServerConst.EthnicityValueset.MUONG.getData();
			}else if (value.toLowerCase().contains("7")) {
				return ServerConst.EthnicityValueset.NUNG.getData();
			}else if (value.toLowerCase().contains("8")) {
				return ServerConst.EthnicityValueset.HMONG.getData();
			}else if (value.toLowerCase().contains("9")) {
				return ServerConst.EthnicityValueset.DAO.getData();
			}else if (value.toLowerCase().contains("10")) {
				return ServerConst.EthnicityValueset.GIA_RAI.getData();
			}else if (value.toLowerCase().contains("11")) {
				return ServerConst.EthnicityValueset.NGAI.getData();
			}else if (value.toLowerCase().contains("12")) {
				return ServerConst.EthnicityValueset.E_DE.getData();
			}else if (value.toLowerCase().contains("13")) {
				return ServerConst.EthnicityValueset.BA_NA.getData();
			}else if (value.toLowerCase().contains("14")) {
				return ServerConst.EthnicityValueset.XO_DANG.getData();
			}else if (value.toLowerCase().contains("15")) {
				return ServerConst.EthnicityValueset.SAN_CHAY.getData();
			}else if (value.toLowerCase().contains("16")) {
				return ServerConst.EthnicityValueset.CO_HO.getData();
			}else if (value.toLowerCase().contains("17")) {
				return ServerConst.EthnicityValueset.CHAM.getData();
			}else if (value.toLowerCase().contains("18")) {
				return ServerConst.EthnicityValueset.SAN_DIU.getData();
			}else if (value.toLowerCase().contains("19")) {
				return ServerConst.EthnicityValueset.HRE.getData();
			}else if (value.toLowerCase().contains("20")) {
				return ServerConst.EthnicityValueset.MNONG.getData();
			}else if (value.toLowerCase().contains("21")) {
				return ServerConst.EthnicityValueset.RA_GLAI.getData();
			}else if (value.toLowerCase().contains("22")) {
				return ServerConst.EthnicityValueset.XTIENG.getData();
			}else if (value.toLowerCase().contains("23")) {
				return ServerConst.EthnicityValueset.BRU_VAN_KIEU.getData();
			}else if (value.toLowerCase().contains("24")) {
				return ServerConst.EthnicityValueset.THO.getData();
			}else if (value.toLowerCase().contains("25")) {
				return ServerConst.EthnicityValueset.GIAY.getData();
			}else if (value.toLowerCase().contains("26")) {
				return ServerConst.EthnicityValueset.CO_TU.getData();
			}else if (value.toLowerCase().contains("27")) {
				return ServerConst.EthnicityValueset.GIE_TRIENG.getData();
			}else if (value.toLowerCase().contains("28")) {
				return ServerConst.EthnicityValueset.MA.getData();
			}else if (value.toLowerCase().contains("29")) {
				return ServerConst.EthnicityValueset.KHO_MU.getData();
			}else if (value.toLowerCase().contains("30")) {
				return ServerConst.EthnicityValueset.CO.getData();
			}else if (value.toLowerCase().contains("31")) {
				return ServerConst.EthnicityValueset.TA_OI.getData();
			}else if (value.toLowerCase().contains("32")) {
				return ServerConst.EthnicityValueset.CHO_RO.getData();
			}else if (value.toLowerCase().contains("33")) {
				return ServerConst.EthnicityValueset.KHANG.getData();
			}else if (value.toLowerCase().contains("34")) {
				return ServerConst.EthnicityValueset.XINH_MUN.getData();
			}else if (value.toLowerCase().contains("35")) {
				return ServerConst.EthnicityValueset.HA_NHI.getData();
			}else if (value.toLowerCase().contains("36")) {
				return ServerConst.EthnicityValueset.CHU_RU.getData();
			}else if (value.toLowerCase().contains("37")) {
				return ServerConst.EthnicityValueset.LAO.getData();
			}else if (value.toLowerCase().contains("38")) {
				return ServerConst.EthnicityValueset.LA_CHI.getData();
			}else if (value.toLowerCase().contains("39")) {
				return ServerConst.EthnicityValueset.LA_HA.getData();
			}else if (value.toLowerCase().contains("40")) {
				return ServerConst.EthnicityValueset.PHU_LA.getData();
			}else if (value.toLowerCase().contains("41")) {
				return ServerConst.EthnicityValueset.LA_HU.getData();
			}else if (value.toLowerCase().contains("42")) {
				return ServerConst.EthnicityValueset.LU.getData();
			}else if (value.toLowerCase().contains("43")) {
				return ServerConst.EthnicityValueset.LO_LO.getData();
			}else if (value.toLowerCase().contains("44")) {
				return ServerConst.EthnicityValueset.CHUT.getData();
			}else if (value.toLowerCase().contains("45")) {
				return ServerConst.EthnicityValueset.MANG.getData();
			}else if (value.toLowerCase().contains("46")) {
				return ServerConst.EthnicityValueset.PA_THEN.getData();
			}else if (value.toLowerCase().contains("47")) {
				return ServerConst.EthnicityValueset.CO_LAO.getData();
			}else if (value.toLowerCase().contains("48")) {
				return ServerConst.EthnicityValueset.CONG.getData();
			}else if (value.toLowerCase().contains("49")) {
				return ServerConst.EthnicityValueset.BO_Y.getData();
			}else if (value.toLowerCase().contains("50")) {
				return ServerConst.EthnicityValueset.SI_LA.getData();
			}else if (value.toLowerCase().contains("51")) {
				return ServerConst.EthnicityValueset.PU_PEO.getData();
			}else if (value.toLowerCase().contains("52")) {
				return ServerConst.EthnicityValueset.BRAU.getData();
			}else if (value.toLowerCase().contains("53")) {
				return ServerConst.EthnicityValueset.O_DU.getData();
			}else if (value.toLowerCase().contains("54")) {
				return ServerConst.EthnicityValueset.RO_MAM.getData();
			}else if (value.toLowerCase().contains("55")) {
				return ServerConst.EthnicityValueset.NGUOI_NUOC_NGOAI.getData();
			} else {
				return ServerConst.EthnicityValueset.KHONGRO.getData();
			}
		}
		return null;
	}
}
