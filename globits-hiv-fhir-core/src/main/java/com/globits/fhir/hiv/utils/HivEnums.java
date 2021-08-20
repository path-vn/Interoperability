package com.globits.fhir.hiv.utils;

public class HivEnums {

	public enum CD4Valueset {
		BEFORE_ART, ROUTINE_TEST, OTHER;

		public Valueset getData() {
			switch (this) {
				case BEFORE_ART:
					return new Valueset("before_art", "Before ART", "");

				case ROUTINE_TEST:
					return new Valueset("routine_test", "Routine test", "");

				case OTHER:
					return new Valueset("other", "Other", "");

				default:
					return null;
			}
		}
	}

	public enum VlValueset {
		ROUTINE_6, ROUTINE_12, TREATMENT_FAIL, AFTER_HIGH_3_MONTH, PREGNANT, OTHER;

		public Valueset getData() {
			switch (this) {
				case ROUTINE_6:
					return new Valueset("routine-6", "6 months after ART initiation", "");
				case ROUTINE_12:
					return new Valueset("routine-12", "12 month routine", "");

				case TREATMENT_FAIL:
					return new Valueset("treatment-fail", "Suspected treatment failure (clinical, immunological)", "");
				case AFTER_HIGH_3_MONTH:
					return new Valueset("3month-after-high", "3 month after high viral load", "");
				case PREGNANT:
					return new Valueset("pregnant", "Pregnant/Lactating woman", "");

				case OTHER:
					return new Valueset("other", "Other", "");

				default:
					return null;
			}
		}
	}

	public enum GenderValueset {
		MALE, FEMALE, UNIDENTIFIED;

		public Valueset getData() {
			switch (this) {
				case MALE:
					return new Valueset("male", "Male", "");

				case FEMALE:
					return new Valueset("female", "Female", "");

				case UNIDENTIFIED:
					return new Valueset("unidentified", "Unidentified", "");

				default:
					return null;
			}
		}
	}

	public enum OccupationValueset {
		ARMY, GOV_EMPL, WORKER, STUDENT, FREE_LANCER, UNEMPLOYMENT, CHILD, PRISONER, FAMER, OTHER;

		public Valueset getData() {
			switch (this) {
				case ARMY:
					return new Valueset("army", "Army personnel", "");
				case GOV_EMPL:
					return new Valueset("gov_empl", "Government employee", "");
				case WORKER:
					return new Valueset("worker", "Worker", "");
				case STUDENT:
					return new Valueset("student", "Pupil/Student", "");
				case FREE_LANCER:
					return new Valueset("free_lancer", "Free lancer", "");
				case UNEMPLOYMENT:
					return new Valueset("unemployment", "Unemployment", "");
				case CHILD:
					return new Valueset("child", "Children", "");
				case PRISONER:
					return new Valueset("prisoner", "Prisoner", "");
				case FAMER:
					return new Valueset("famer", "Farmer", "");
				case OTHER:
					return new Valueset("other", "Other", "");
				default:
					return null;
			}
		}
	}

	public enum PopulationGroupValueset {
		PWID, MSM, SW, CLIENT_SW, PARTNER_PLHIV, TG_WOMEN, TG_MEN, CHILD_WOMAN_HIV, CLIENT_SEXW, BLOOD_DONOR,
		SUSPECT_HIV, TB_PATIENT, STD_PERSON, PRISONER, PREGNANT, MILITARY_RECRUITS, OTHER;

		public Valueset getData() {
			switch (this) {
				case PWID:
					return new Valueset("pwid", "Injecting drug users", "");
				case MSM:
					return new Valueset("msm", "Men who have sex with men", "");
				case SW:
					return new Valueset("sw", "Sex workers", "");
				case CLIENT_SW:
					return new Valueset("client_sw", "Clients of sex worker", "");
				case PARTNER_PLHIV:
					return new Valueset("partner_plhiv", "Sexual partner of PLHIV", "");
				case TG_WOMEN:
					return new Valueset("tg_women", "Transgender women", "");
				case TG_MEN:
					return new Valueset("tg_men", "Transgender men", "");
				case CHILD_WOMAN_HIV:
					return new Valueset("child_woman_hiv", "Child of mother who is infected with HIV", "");
				case CLIENT_SEXW:
					return new Valueset("client_sexw", "Client of sex workers", "");
				case BLOOD_DONOR:
					return new Valueset("blood-donor", "Blood seller / blood donor", "");
				case SUSPECT_HIV:
					return new Valueset("suspect_hiv", "People suspected for AIDs", "");
				case TB_PATIENT:
					return new Valueset("tb_patient", "Tuberculosis patient", "");
				case STD_PERSON:
					return new Valueset("std_person", "Persons with STD", "");
				case PRISONER:
					return new Valueset("prisoner", "Prisoner", "");
				case PREGNANT:
					return new Valueset("pregnant", "Pregnant women", "");
				case MILITARY_RECRUITS:
					return new Valueset("military_recruits", "	Young recruits for military service", "");
				case OTHER:
					return new Valueset("other", "Other", "");
				default:
					return null;
			}
		}
	}

	public enum RiskBehaviorValueset {
		PWID, SEX_PARTNER, MSM, SMP, PARTNER_HIV, OTHER;

		public Valueset getData() {
			switch (this) {
				case PWID:
					return new Valueset("pwid", "PWID", "");
				case MSM:
					return new Valueset("msm", "Men who have sex with men", "");
				case SEX_PARTNER:
					return new Valueset("sex_partner", "Having sex with sex worker or sex buyer", "");
				case SMP:
					return new Valueset("smp", "Having sex with multiple partners (not for money or drug)", "");
				case PARTNER_HIV:
					return new Valueset("partner_hiv", "Having sex with spouse/partners who are HIV infected", "");
				case OTHER:
					return new Valueset("other", "Other", "");
				default:
					return null;
			}
		}
	}

	public enum TransmissionRouteValueset {
		BLOOD, DRUG_INJECTION, BLOOD_TRANSFUSION, OCCUPATIONAL_ACCIDENT, SEXUAL_TRANSMISSION, HOMOSEXUAL, HETEROSEXUAL,
		MOTHER_TO_CHILD_TRANSMISSION, UNIDENTIFIED;

		public Valueset getData() {
			switch (this) {
				case BLOOD:
					return new Valueset("312425004", "Blood", "");
				case DRUG_INJECTION:
					return new Valueset("226034001", "Drug injection", "");
				case BLOOD_TRANSFUSION:
					return new Valueset("250373003", "Blood transfusion", "");
				case OCCUPATIONAL_ACCIDENT:
					return new Valueset("53348004", "Occupational accident", "");
				case SEXUAL_TRANSMISSION:
					return new Valueset("417564009", "Sexual Transmission", "");
				case HOMOSEXUAL:
					return new Valueset("38628009", "Homosexual", "");
				case HETEROSEXUAL:
					return new Valueset("20430005", "Heterosexual", "");
				case MOTHER_TO_CHILD_TRANSMISSION:
					return new Valueset("438998000", "Mother to Child Transmission", "");
				case UNIDENTIFIED:
					return new Valueset("69910005", "Unidentified", "");
				default:
					return null;
			}
		}
	}

	public enum RapidTestValueset {
		RECENT, LONG_TERM, NEGATIVE;

		public Valueset getData() {
			switch (this) {
				case RECENT:
					return new Valueset("recent", "Recent", "");

				case LONG_TERM:
					return new Valueset("long_term", "Long term", "");

				case NEGATIVE:
					return new Valueset("negative", "Negative", "");

				default:
					return null;
			}
		}
	}

	public enum RecentInfectionConclusionValueset {
		RECENT, LONG_TERM;

		public Valueset getData() {
			switch (this) {
				case RECENT:
					return new Valueset("recent", "Recent infection", "");

				case LONG_TERM:
					return new Valueset("long-term", "Long term infection", "");

				default:
					return null;
			}
		}
	}

	public enum PregnancyOutcomeValueset {
		UNBORN, BORN, MISCARRIED, ABORTION, UNKNOWN;

		public Valueset getData() {
			switch (this) {
				case UNBORN:
					return new Valueset("unborn", "Unborn", "");

				case BORN:
					return new Valueset("born", "Born", "");

				case MISCARRIED:
					return new Valueset("miscarried", "Miscarried", "");

				case ABORTION:
					return new Valueset("abortion", "Abortion", "");

				case UNKNOWN:
					return new Valueset("unknown", "Unknown", "");

				default:
					return null;
			}
		}
	}

	public enum BirthDefectsValueset {
		POSITIVE, NEGATIVE, UNIDENTIFIED,UNKNOWN;

		public Valueset getData() {
			switch (this) {
				case POSITIVE:
					return new Valueset("positive", "Positive", "");

				case NEGATIVE:
					return new Valueset("negative", "Negative", "");

				case UNIDENTIFIED:
					return new Valueset("unidentified", "Unidentified", "");

				case UNKNOWN:
					return new Valueset("unknown", "Unknown", "");

				default:
					return null;
			}
		}
	}

	public enum WhoStageValueset {
		STAGE1, STAGE2, STAGE3, STAGE4;

		public Valueset getData() {
			switch (this) {
				case STAGE1:
					return new Valueset("stage1", "WHO Stage 1", "");

				case STAGE2:
					return new Valueset("stage2", "WHO Stage 2", "");

				case STAGE3:
					return new Valueset("stage3", "WHO Stage 3", "");

				case STAGE4:
					return new Valueset("stage4", "WHO Stage 4", "");

				default:
					return null;
			}
		}
	}

	public enum EthnicityValueset {
		KINH, TAY, THAI, HOA, KHO_ME, MUONG, NUNG, HMONG, DAO, GIA_RAI, NGAI, E_DE, BA_NA, XO_DANG, SAN_CHAY, CO_HO,
		CHAM, SAN_DIU, HRE, MNONG, RA_GLAI, XTIENG, BRU_VAN_KIEU, THO, GIAY, CO_TU, GIE_TRIENG, MA, KHO_MU, CO, TA_OI,
		CHO_RO, KHANG, XINH_MUN, HA_NHI, CHU_RU, LAO, LA_CHI, LA_HA, PHU_LA, LA_HU, LU, LO_LO, CHUT, MANG, PA_THEN,
		CO_LAO, CONG, BO_Y, SI_LA, PU_PEO, BRAU, O_DU, RO_MAM, NGUOI_NUOC_NGOAI, KHONGRO;

		public Valueset getData() {
			switch (this) {
				case KINH:
					return new Valueset("1", "Kinh", "");
				case TAY:
					return new Valueset("2", "Tày", "");
				case THAI:
					return new Valueset("3", "Thái", "");
				case HOA:
					return new Valueset("4", "Hoa", "");
				case KHO_ME:
					return new Valueset("5", "Khơ-me", "");
				case MUONG:
					return new Valueset("6", "Mường", "");
				case NUNG:
					return new Valueset("7", "Nùng", "");
				case HMONG:
					return new Valueset("8", "Hmông", "");
				case DAO:
					return new Valueset("9", "Dao", "");
				case GIA_RAI:
					return new Valueset("10", "Gia-rai", "");
				case NGAI:
					return new Valueset("11", "Ngái", "");
				case E_DE:
					return new Valueset("12", "Ê-đê", "");
				case BA_NA:
					return new Valueset("13", "Ba-na", "");
				case XO_DANG:
					return new Valueset("14", "Xơ-đăng", "");
				case SAN_CHAY:
					return new Valueset("15", "Sán Chay", "");
				case CO_HO:
					return new Valueset("16", "Cơ-ho", "");
				case CHAM:
					return new Valueset("17", "Chăm", "");
				case SAN_DIU:
					return new Valueset("18", "Sán Dìu", "");
				case HRE:
					return new Valueset("19", "Hrê", "");
				case MNONG:
					return new Valueset("20", "Mnông", "");
				case RA_GLAI:
					return new Valueset("21", "Ra-glai", "");
				case XTIENG:
					return new Valueset("22", "Xtiêng", "");
				case BRU_VAN_KIEU:
					return new Valueset("23", "Bru-Vân Kiều", "");
				case THO:
					return new Valueset("24", "Thổ", "");
				case GIAY:
					return new Valueset("25", "Giáy", "");
				case CO_TU:
					return new Valueset("26", "Cơ-tu", "");
				case GIE_TRIENG:
					return new Valueset("27", "Gié-Triêng", "");
				case MA:
					return new Valueset("28", "Mạ", "");
				case KHO_MU:
					return new Valueset("29", "Khơ-mú", "");
				case CO:
					return new Valueset("30", "Co", "");
				case TA_OI:
					return new Valueset("31", "Ta-ôi", "");
				case CHO_RO:
					return new Valueset("32", "Chơ-ro", "");
				case KHANG:
					return new Valueset("33", "Kháng", "");
				case XINH_MUN:
					return new Valueset("34", "Xinh-mun", "");
				case HA_NHI:
					return new Valueset("35", "Hà Nhì", "");
				case CHU_RU:
					return new Valueset("36", "Chu-ru", "");
				case LAO:
					return new Valueset("37", "Lào", "");
				case LA_CHI:
					return new Valueset("38", "La Chi", "");
				case LA_HA:
					return new Valueset("39", "La Ha", "");
				case PHU_LA:
					return new Valueset("40", "Phù Lá", "");
				case LA_HU:
					return new Valueset("41", "La Hủ", "");
				case LU:
					return new Valueset("42", "Lự", "");
				case LO_LO:
					return new Valueset("43", "Lô Lô", "");
				case CHUT:
					return new Valueset("44", "Chứt", "");
				case MANG:
					return new Valueset("45", "Mảng", "");
				case PA_THEN:
					return new Valueset("46", "Pà Thẻn", "");
				case CO_LAO:
					return new Valueset("47", "Cơ Lao", "");
				case CONG:
					return new Valueset("48", "Cống", "");
				case BO_Y:
					return new Valueset("49", "Bố Y", "");
				case SI_LA:
					return new Valueset("50", "Si La", "");
				case PU_PEO:
					return new Valueset("51", "Pu Péo", "");
				case BRAU:
					return new Valueset("52", "Brâu", "");
				case O_DU:
					return new Valueset("53", "Ơ Đu", "");
				case RO_MAM:
					return new Valueset("54", "Rơ-măm", "");
				case NGUOI_NUOC_NGOAI:
					return new Valueset("55", "Người nước ngoài", "");
				case KHONGRO:
					return new Valueset("0", "Không rõ", "");
				default:
					return null;
			}
		}
	}

	public enum RegimenValueset {
		REG001, REG002, REG003, REG004, REG005, REG006, REG007, REG008, REG009, REG010, REG011, REG012, REG013, REG014,
		REG015, REG016, REG017, REG018, REG019, REG020, REG021, REG022, REG023, REG024, REG025, REG026, REG027, REG028,
		REG029, REG030, REG031, REG032, REG033, REG034, REG035, REG036, REG037, REG038, REG039, REG040, REG041, REG042,
		REG043, REG044, REG045, REG046, REG047;

		public Valueset getData() {
			switch (this) {
				case REG001:
					return new Valueset("reg001", "(TDF/3TC/DTG 300/300/50)", "");
				case REG002:
					return new Valueset("reg002", "(TDF/3TC/EFV 300/300/400)", "");
				case REG003:
					return new Valueset("reg003", "(TDF/3TC/EFV 300/300/600)", "");
				case REG004:
					return new Valueset("reg004", "(ZDV/3TC 300/150)(EFV 600)", "");
				case REG005:
					return new Valueset("reg005", "(ZDV/3TC 300/150)(EFV 200)", "");
				case REG006:
					return new Valueset("reg006", "(ZDV/3TC/NVP 300/150/200)", "");
				case REG007:
					return new Valueset("reg007", "(ABC 300)(3TC 150)(DTG 50)", "");
				case REG008:
					return new Valueset("reg008", "(TDF 300)(3TC 150)(RAL 400)", "");
				case REG009:
					return new Valueset("reg009", "(TDF/FTC 300/200)(RAL 400)", "");
				case REG010:
					return new Valueset("reg010", "(TAF 25)(3TC 150)(DTG 50)", "");
				case REG011:
					return new Valueset("reg011", "(TDF/FTC 300/200)(EFV 600)", "");
				case REG012:
					return new Valueset("reg012", "(ZDV/3TC 300/150)(LPV/r 100/25)", "");
				case REG013:
					return new Valueset("reg013", "(ZDV/3TC 300/150)(LPV/r 200/50)", "");
				case REG014:
					return new Valueset("reg014", "(ZDV/3TC 300/150)(DTG 50)", "");
				case REG015:
					return new Valueset("reg015", "(ZDV/3TC 300/150)(ATV 300)(RTV 100)", "");
				case REG016:
					return new Valueset("reg016", "(ZDV/3TC 300/150)(DRV 600)(RTV 100)", "");
				case REG017:
					return new Valueset("reg017", "(TDF/FTC 300/200)(DTG 50)", "");
				case REG018:
					return new Valueset("reg018", "(TDF/FTC 300/200)(ATV 300)(RTV 100)", "");
				case REG019:
					return new Valueset("reg019", "(TDF/FTC 300/200)(DRV 600)(RTV 100)", "");
				case REG020:
					return new Valueset("reg020", "(TDF 300)(3TC 150)(ATV 300)(RTV 100)", "");
				case REG021:
					return new Valueset("reg021", "(TDF 300)(3TC 150)(DRV 600)(RTV 100)", "");
				case REG022:
					return new Valueset("reg022", "(TDF 300)(3TC 150)(LPV/r 100/25)", "");
				case REG023:
					return new Valueset("reg023", "(TDF 300)(3TC 150)(LPV/r 200/50)", "");
				case REG024:
					return new Valueset("reg024", "(TDF 300)(3TC 150)(LPV/r 80/20mg/ml)", "");
				case REG025:
					return new Valueset("reg025", "(TDF/FTC 300/200)(LPV/r 100/25)", "");
				case REG026:
					return new Valueset("reg026", "(TDF/FTC 300/200)(LPV/r 200/50)", "");
				case REG027:
					return new Valueset("reg027", "(TDF/FTC 300/200)(LPV/r 80/20mg/ml)", "");
				case REG028:
					return new Valueset("reg028", "(ABC 300)(ZDV/3TC 300/150)", "");
				case REG029:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG030:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG031:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG032:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG033:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG034:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG035:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG036:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG037:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG038:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG039:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG040:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG041:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG042:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG043:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG044:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG045:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG046:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				case REG047:
					return new Valueset("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
				default:
					return null;
			}
		}
	}
	
	public enum ResultOtherValueset {
		UNDETECTABLE;

		public Valueset getData() {
			switch (this) {
				case UNDETECTABLE:
					return new Valueset("http://example.org/CodeSystem/casereporting-hiv-vn-casereport-answeroptions#undetectable", "undetectable", "");
				default:
					return null;
			}
		}
	}

}
