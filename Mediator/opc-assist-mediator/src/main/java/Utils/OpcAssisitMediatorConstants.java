package Utils;

import org.springframework.util.StringUtils;

import com.globits.fhir.hiv.utils.HivEnums;
import com.globits.fhir.hiv.utils.Valueset;

public class OpcAssisitMediatorConstants {
	public static Valueset getOccupationByText(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("trẻ em") || value.toLowerCase().contains("Dưới 6 tuổi")
					|| value.toLowerCase().contains("Under 6 years old")) {
				return HivEnums.OccupationValueset.CHILD.getData();
			} else if (value.toLowerCase().contains("học sinh") || value.toLowerCase().contains("sinh viên")
					|| value.toLowerCase().contains("Học sinh") || value.toLowerCase().contains("Students")) {
				return HivEnums.OccupationValueset.STUDENT.getData();
			} else if (value.toLowerCase().contains("làm nông") || value.toLowerCase().contains("nông nghiệp")) {
				return HivEnums.OccupationValueset.FAMER.getData();
			} else if (value.toLowerCase().contains("thất nghiệp") || value.toLowerCase().contains("Jobless")) {
				return HivEnums.OccupationValueset.UNEMPLOYMENT.getData();
			} else if (value.toLowerCase().contains("công nhân") || value.toLowerCase().contains("Factory workers")) {
				return HivEnums.OccupationValueset.WORKER.getData();
			} else if (value.toLowerCase().contains("tù nhân") || value.toLowerCase().contains("phạm nhân")) {
				return HivEnums.OccupationValueset.PRISONER.getData();
			} else if (value.toLowerCase().contains("chiến sỹ") || value.toLowerCase().contains("Military personnel")) {
				return HivEnums.OccupationValueset.ARMY.getData();
			} else {
				return HivEnums.OccupationValueset.OTHER.getData();
			}
		}
		return null;
	}

	public static Valueset getPopulationGroupByText(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("Tiêm chích ma tuý") || value.toLowerCase().contains("chích ma túy")
					|| value.toLowerCase().contains("People who inject drug (PWID)")
					|| value.toLowerCase().contains("risk_1")) {
				return HivEnums.PopulationGroupValueset.PWID.getData();
			} else if ((value.toLowerCase().contains("nam") && value.toLowerCase().contains("Quan hệ đồng giới"))
					|| value.toLowerCase().contains("risk_2")
					|| value.toLowerCase().contains("Men who have sex with men")) {
				return HivEnums.PopulationGroupValueset.MSM.getData();
			} else if (value.toLowerCase().contains("phụ nữ bán dâm") || value.toLowerCase().contains("bán dâm")
					|| value.toLowerCase().contains("Mại dâm") || value.toLowerCase().contains("risk_3")
					|| value.toLowerCase().contains("Sex workers")) {
				return HivEnums.PopulationGroupValueset.SW.getData();
			} else if (value.toLowerCase().contains("mẹ truyền cho con")
					|| value.toLowerCase().contains("con của người nhiễm hiv")) {
				return HivEnums.PopulationGroupValueset.CHILD_WOMAN_HIV.getData();
			} else if (value.toLowerCase().contains("cho máu") || value.toLowerCase().contains("hiến máu")
					|| value.toLowerCase().contains("bán máu")) {
				return HivEnums.PopulationGroupValueset.BLOOD_DONOR.getData();
			} else if (value.toLowerCase().contains("bệnh lao")) {
				return HivEnums.PopulationGroupValueset.TB_PATIENT.getData();
			} else if (value.toLowerCase().contains("tù nhân") || value.toLowerCase().contains("phạm nhân")
					|| value.toLowerCase().contains("Phạm nhân") || value.toLowerCase().contains("risk_5")
					|| value.toLowerCase().contains("Prisoners")) {
				return HivEnums.PopulationGroupValueset.PRISONER.getData();
			} else if (value.toLowerCase().contains("phụ nữ mang thai")) {
				return HivEnums.PopulationGroupValueset.PREGNANT.getData();
			} else if (value.toLowerCase().contains("khám tuyển nghĩa vụ quân sự")) {
				return HivEnums.PopulationGroupValueset.MILITARY_RECRUITS.getData();
			} else if (value.toLowerCase().contains("Bạn tình người nhiễm") || value.toLowerCase().contains("risk_6")
					|| value.toLowerCase().contains("Sex partners of PLHIV")) {
				return HivEnums.PopulationGroupValueset.PARTNER_PLHIV.getData();
			} else if (value.toLowerCase().contains("Khách mua dâm") || value.toLowerCase().contains("risk_8")
					|| value.toLowerCase().contains("Clients of sex workers")) {
				return HivEnums.PopulationGroupValueset.CLIENT_SW.getData();
			} else {
				return HivEnums.PopulationGroupValueset.OTHER.getData();
			}
		}
		return null;
	}

	public static Valueset getGender(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().equals("nam")) {
				return HivEnums.GenderValueset.MALE.getData();
			} else if (value.toLowerCase().equals("nữ")) {
				return HivEnums.GenderValueset.FEMALE.getData();
			} else {
				return HivEnums.GenderValueset.UNIDENTIFIED.getData();
			}
		}
		return null;
	}

	public static Valueset getRiskBehavior(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("tiêm chích ma túy") || value.toLowerCase().contains("chích ma túy")) {
				return HivEnums.RiskBehaviorValueset.PWID.getData();
			} else if (value.toLowerCase().contains("nam")
					&& value.toLowerCase().contains("quan hệ tình dục đồng giới")) {
				return HivEnums.RiskBehaviorValueset.MSM.getData();
			} else if (value.toLowerCase().contains("quan hệ tình dục với nhiều người (không vì tiền)")
					|| value.toLowerCase().contains("không vì tiền")) {
				return HivEnums.RiskBehaviorValueset.SMP.getData();
			} else {
				return HivEnums.PopulationGroupValueset.OTHER.getData();
			}
		}
		return null;
	}

	public static Valueset getTransmissionRoute(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("lây qua đường máu")) {
				return HivEnums.TransmissionRouteValueset.BLOOD.getData();
			} else if (value.toLowerCase().contains("tiêm chích ma túy")
					|| value.toLowerCase().contains("chích ma túy")) {
				return HivEnums.TransmissionRouteValueset.DRUG_INJECTION.getData();
			} else if (value.toLowerCase().contains("truyền máu")) {
				return HivEnums.TransmissionRouteValueset.BLOOD_TRANSFUSION.getData();
			} else if (value.toLowerCase().contains("tai nạn nghề nghiệp")) {
				return HivEnums.TransmissionRouteValueset.OCCUPATIONAL_ACCIDENT.getData();
			} else if (value.toLowerCase().contains("lây qua đường tình dục")) {
				return HivEnums.TransmissionRouteValueset.SEXUAL_TRANSMISSION.getData();
			} else if (value.toLowerCase().contains("tình dục đồng giới")) {
				return HivEnums.TransmissionRouteValueset.HOMOSEXUAL.getData();
			} else if (value.toLowerCase().contains("mẹ truyền sang con")) {
				return HivEnums.TransmissionRouteValueset.MOTHER_TO_CHILD_TRANSMISSION.getData();
			} else {
				return HivEnums.TransmissionRouteValueset.UNIDENTIFIED.getData();
			}
		}
		return null;
	}

	public static Valueset getUndetectable() {
		return new Valueset("undetectable", "Undetectable", "");
	}

	public static Valueset getRapidTestResult(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("nhiễm lâu")) {
				return HivEnums.RapidTestValueset.LONG_TERM.getData();
			} else if (value.toLowerCase().contains("mới nhiễm")) {
				return HivEnums.RapidTestValueset.RECENT.getData();
			} else if (value.toLowerCase().contains("âm tính")) {
				return HivEnums.RapidTestValueset.NEGATIVE.getData();
			}
		}
		return null;
	}

	public static Valueset getRecentInfectionConclusion(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("nhiễm lâu")) {
				return HivEnums.RapidTestValueset.LONG_TERM.getData();
			} else if (value.toLowerCase().contains("mới nhiễm")) {
				return HivEnums.RapidTestValueset.RECENT.getData();
			}
		}
		return null;
	}

	public static Boolean getTPTCompleted(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("hoàn thành") || value.toLowerCase().contains("1")) {
				return true;
			} else {
				return false;
			}
		}
		return null;
	}

	public static Valueset getEthnicity(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("kinh")) {
				return HivEnums.EthnicityValueset.KINH.getData();
			} else if (value.toLowerCase().contains("tày")) {
				return HivEnums.EthnicityValueset.TAY.getData();
			} else if (value.toLowerCase().contains("thái")) {
				return HivEnums.EthnicityValueset.THAI.getData();
			} else if (value.toLowerCase().contains("mường")) {
				return HivEnums.EthnicityValueset.MUONG.getData();
			} else if (value.toLowerCase().contains("khơ me")) {
				return HivEnums.EthnicityValueset.KHO_ME.getData();
			} else if (value.toLowerCase().contains("h'mông")) {
				return HivEnums.EthnicityValueset.HMONG.getData();
			} else if (value.toLowerCase().contains("nùng")) {
				return HivEnums.EthnicityValueset.NUNG.getData();
			} else if (value.toLowerCase().contains("hoa")) {
				return HivEnums.EthnicityValueset.HOA.getData();
			} else if (value.toLowerCase().contains("dao")) {
				return HivEnums.EthnicityValueset.DAO.getData();
			} else if (value.toLowerCase().contains("gia rai")) {
				return HivEnums.EthnicityValueset.GIA_RAI.getData();
			} else if (value.toLowerCase().contains("ê đê")) {
				return HivEnums.EthnicityValueset.E_DE.getData();
			} else if (value.toLowerCase().contains("ba na")) {
				return HivEnums.EthnicityValueset.BA_NA.getData();
			} else if (value.toLowerCase().contains("xơ đăng")) {
				return HivEnums.EthnicityValueset.XO_DANG.getData();
			} else if (value.toLowerCase().contains("sán chay")) {
				return HivEnums.EthnicityValueset.SAN_CHAY.getData();
			} else if (value.toLowerCase().contains("cơ ho")) {
				return HivEnums.EthnicityValueset.CO_HO.getData();
			} else if (value.toLowerCase().contains("chăm")) {
				return HivEnums.EthnicityValueset.CHAM.getData();
			} else if (value.toLowerCase().contains("sán dìu")) {
				return HivEnums.EthnicityValueset.SAN_DIU.getData();
			} else if (value.toLowerCase().contains("hrê")) {
				return HivEnums.EthnicityValueset.HRE.getData();
			} else if (value.toLowerCase().contains("m'nông")) {
				return HivEnums.EthnicityValueset.MNONG.getData();
			} else if (value.toLowerCase().contains("x'tiêng")) {
				return HivEnums.EthnicityValueset.XTIENG.getData();
			} else if (value.toLowerCase().contains("bru-vân kiều")) {
				return HivEnums.EthnicityValueset.BRU_VAN_KIEU.getData();
			} else if (value.toLowerCase().contains("thổ")) {
				return HivEnums.EthnicityValueset.THO.getData();
			} else if (value.toLowerCase().contains("khơ mú")) {
				return HivEnums.EthnicityValueset.KHO_MU.getData();
			} else if (value.toLowerCase().contains("cơ tu")) {
				return HivEnums.EthnicityValueset.CO_TU.getData();
			} else if (value.toLowerCase().contains("giáy")) {
				return HivEnums.EthnicityValueset.GIAY.getData();
			} else if (value.toLowerCase().contains("giẻ triêng")) {
				return HivEnums.EthnicityValueset.GIE_TRIENG.getData();
			} else if (value.toLowerCase().contains("tà ôi")) {
				return HivEnums.EthnicityValueset.TA_OI.getData();
			} else if (value.toLowerCase().contains("mạ")) {
				return HivEnums.EthnicityValueset.MA.getData();
			} else if (value.toLowerCase().contains("co")) {
				return HivEnums.EthnicityValueset.CO.getData();
			} else if (value.toLowerCase().contains("chơ ro")) {
				return HivEnums.EthnicityValueset.CHO_RO.getData();
			} else if (value.toLowerCase().contains("xinh mun")) {
				return HivEnums.EthnicityValueset.XINH_MUN.getData();
			} else if (value.toLowerCase().contains("hà nhì")) {
				return HivEnums.EthnicityValueset.HA_NHI.getData();
			} else if (value.toLowerCase().contains("chu ru")) {
				return HivEnums.EthnicityValueset.CHU_RU.getData();
			} else if (value.toLowerCase().contains("lào")) {
				return HivEnums.EthnicityValueset.LAO.getData();
			} else if (value.toLowerCase().contains("kháng")) {
				return HivEnums.EthnicityValueset.KHANG.getData();
			} else if (value.toLowerCase().contains("la chí")) {
				return HivEnums.EthnicityValueset.LA_CHI.getData();
			} else if (value.toLowerCase().contains("phù lá")) {
				return HivEnums.EthnicityValueset.PHU_LA.getData();
			} else if (value.toLowerCase().contains("la hủ")) {
				return HivEnums.EthnicityValueset.LA_HU.getData();
			} else if (value.toLowerCase().contains("la ha")) {
				return HivEnums.EthnicityValueset.LA_HA.getData();
			} else if (value.toLowerCase().contains("pà thẻn")) {
				return HivEnums.EthnicityValueset.PA_THEN.getData();
			} else if (value.toLowerCase().contains("chứt")) {
				return HivEnums.EthnicityValueset.CHUT.getData();
			} else if (value.toLowerCase().contains("lự")) {
				return HivEnums.EthnicityValueset.LU.getData();
			} else if (value.toLowerCase().contains("lô lô")) {
				return HivEnums.EthnicityValueset.LO_LO.getData();
			} else if (value.toLowerCase().contains("mảng")) {
				return HivEnums.EthnicityValueset.MANG.getData();
			} else if (value.toLowerCase().contains("cờ lao")) {
				return HivEnums.EthnicityValueset.CO_LAO.getData();
			} else if (value.toLowerCase().contains("bố y")) {
				return HivEnums.EthnicityValueset.BO_Y.getData();
			} else if (value.toLowerCase().contains("cống")) {
				return HivEnums.EthnicityValueset.CONG.getData();
			} else if (value.toLowerCase().contains("ngái")) {
				return HivEnums.EthnicityValueset.NGAI.getData();
			} else if (value.toLowerCase().contains("si la")) {
				return HivEnums.EthnicityValueset.SI_LA.getData();
			} else if (value.toLowerCase().contains("pu péo")) {
				return HivEnums.EthnicityValueset.PU_PEO.getData();
			} else if (value.toLowerCase().contains("rơ măm")) {
				return HivEnums.EthnicityValueset.RO_MAM.getData();
			} else if (value.toLowerCase().contains("brâu")) {
				return HivEnums.EthnicityValueset.BRAU.getData();
			} else if (value.toLowerCase().contains("ơ đu")) {
				return HivEnums.EthnicityValueset.O_DU.getData();
			} else if (value.toLowerCase().contains("người nước ngoài")) {
				return HivEnums.EthnicityValueset.NGUOI_NUOC_NGOAI.getData();
			} else {
				return HivEnums.EthnicityValueset.KHONGRO.getData();
			}
		}
		return null;
	}

	// public static Valueset getRegimen(String value) {
	// if (value != null && StringUtils.hasText(value)) {
	// if (value.toLowerCase().contains("kinh")) {
	// return HivEnums.RegimenValueset.KINH.getData();
	// }
	// }
	// return null;
	// }

	public static Valueset getCD4Valueset(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("trước khi bắt đầu arv")) {
				return HivEnums.CD4Valueset.BEFORE_ART.getData();
			} else if (value.toLowerCase().contains("xét nghiệm định kỳ")) {
				return HivEnums.CD4Valueset.ROUTINE_TEST.getData();
			} else {
				return HivEnums.CD4Valueset.OTHER.getData();
			}
		}
		return null;
	}

	public static Valueset getVLValueset(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("định kỳ sau 12 tháng")) {
				return HivEnums.VlValueset.ROUTINE_12.getData();
			} else if (value.toLowerCase().contains("phụ nữ mang thai, cho con bú")) {
				return HivEnums.VlValueset.PREGNANT.getData();
			} else if (value.toLowerCase().contains("có biểu hiện nghi ngờ tbđt")) {
				return HivEnums.VlValueset.TREATMENT_FAIL.getData();
			} else {
				return HivEnums.CD4Valueset.OTHER.getData();
			}
		}
		return null;
	}

	public static Valueset getResultOther(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("không phát hiện")
					|| value.toLowerCase().contains("kph - <200 bản sao/ml")) {
				return HivEnums.ResultOtherValueset.UNDETECTABLE.getData();
			} else {
				return null;
			}
		}
		return null;
	}

	public static Valueset getOutComeCode(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("đã sinh")) {
				return HivEnums.PregnancyOutcomeValueset.BORN.getData();
			} else if (value.toLowerCase().contains("bị sảy thai")) {
				return HivEnums.PregnancyOutcomeValueset.MISCARRIED.getData();
			} else if (value.toLowerCase().contains("đã nạo thai")) {
				return HivEnums.PregnancyOutcomeValueset.ABORTION.getData();
			} else if (value.toLowerCase().contains("chưa sinh")) {
				return HivEnums.PregnancyOutcomeValueset.UNBORN.getData();
			} else if (value.toLowerCase().contains("không rõ")) {
				return HivEnums.PregnancyOutcomeValueset.UNKNOWN.getData();
			} else {
				return null;
			}
		}
		return null;
	}

	public static Valueset getBirthDefact(int value) {

		if (value == 1) {
			return HivEnums.BirthDefectsValueset.POSITIVE.getData();
		} else if (value == 2) {
			return HivEnums.BirthDefectsValueset.NEGATIVE.getData();
		} else if (value == 3) {
			return HivEnums.BirthDefectsValueset.UNKNOWN.getData();
		} else {
			return null;
		}
	}
}
