package Utils;

import org.springframework.util.StringUtils;

import com.globits.fhir.hiv.utils.HivEnums;
import com.globits.fhir.hiv.utils.Valueset;

public class HivInfoMediatorConstants {

	public static Valueset getOccupationByText(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("trẻ em")) {
				return HivEnums.OccupationValueset.CHILD.getData();
			} else if (value.toLowerCase().contains("học sinh") || value.toLowerCase().contains("sinh viên")) {
				return HivEnums.OccupationValueset.STUDENT.getData();
			} else if (value.toLowerCase().contains("làm nông") || value.toLowerCase().contains("nông nghiệp")) {
				return HivEnums.OccupationValueset.FAMER.getData();
			} else if (value.toLowerCase().contains("thất nghiệp")) {
				return HivEnums.OccupationValueset.UNEMPLOYMENT.getData();
			} else if (value.toLowerCase().contains("công nhân")) {
				return HivEnums.OccupationValueset.WORKER.getData();
			} else if (value.toLowerCase().contains("tù nhân") || value.toLowerCase().contains("phạm nhân")) {
				return HivEnums.OccupationValueset.PRISONER.getData();
			} else if (value.toLowerCase().contains("chiến sỹ")) {
				return HivEnums.OccupationValueset.ARMY.getData();
			} else {
				return HivEnums.OccupationValueset.OTHER.getData();
			}
		}
		return null;
	}

	public static Valueset getPopulationGroupByText(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("tiêm chích ma túy") || value.toLowerCase().contains("chích ma túy")) {
				return HivEnums.PopulationGroupValueset.PWID.getData();
			} else if (value.toLowerCase().contains("nam")
					&& value.toLowerCase().contains("quan hệ tình dục đồng giới")) {
				return HivEnums.PopulationGroupValueset.MSM.getData();
			} else if (value.toLowerCase().contains("phụ nữ bán dâm") || value.toLowerCase().contains("bán dâm")) {
				return HivEnums.PopulationGroupValueset.SW.getData();
			} else if (value.toLowerCase().contains("mẹ truyền cho con")
					|| value.toLowerCase().contains("con của người nhiễm hiv")) {
				return HivEnums.PopulationGroupValueset.CHILD_WOMAN_HIV.getData();
			} else if (value.toLowerCase().contains("cho máu") || value.toLowerCase().contains("hiến máu")
					|| value.toLowerCase().contains("bán máu")) {
				return HivEnums.PopulationGroupValueset.BLOOD_DONOR.getData();
			} else if (value.toLowerCase().contains("bệnh lao")) {
				return HivEnums.PopulationGroupValueset.TB_PATIENT.getData();
			} else if (value.toLowerCase().contains("tù nhân") || value.toLowerCase().contains("phạm nhân")) {
				return HivEnums.PopulationGroupValueset.PRISONER.getData();
			} else if (value.toLowerCase().contains("phụ nữ mang thai")) {
				return HivEnums.PopulationGroupValueset.PREGNANT.getData();
			} else if (value.toLowerCase().contains("khám tuyển nghĩa vụ quân sự")) {
				return HivEnums.PopulationGroupValueset.MILITARY_RECRUITS.getData();
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
			} else if (value.toLowerCase().contains("quan hệ tình dục với nhiều người (không vì tiền)") || value.toLowerCase().contains("không vì tiền")) {
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
			}
			else if (value.toLowerCase().contains("tiêm chích ma túy") || value.toLowerCase().contains("chích ma túy")) {
				return HivEnums.TransmissionRouteValueset.DRUG_INJECTION.getData();
			}
			else if (value.toLowerCase().contains("truyền máu")) {
				return HivEnums.TransmissionRouteValueset.BLOOD_TRANSFUSION.getData();
			}
			else if (value.toLowerCase().contains("tai nạn nghề nghiệp")) {
				return HivEnums.TransmissionRouteValueset.OCCUPATIONAL_ACCIDENT.getData();
			}
			else if (value.toLowerCase().contains("lây qua đường tình dục")) {
				return HivEnums.TransmissionRouteValueset.SEXUAL_TRANSMISSION.getData();
			}
			else if (value.toLowerCase().contains("tình dục đồng giới")) {
				return HivEnums.TransmissionRouteValueset.HOMOSEXUAL.getData();
			}
			else if (value.toLowerCase().contains("mẹ truyền sang con")) {
				return HivEnums.TransmissionRouteValueset.MOTHER_TO_CHILD_TRANSMISSION.getData();
			}
			else {
				return HivEnums.TransmissionRouteValueset.UNIDENTIFIED.getData();
			}
		}
		return null;
	}

	public static Valueset getUndetectable() {
		return new Valueset("undetectable","Undetectable","");
	}

	public static Valueset getRapidTestResult(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("nhiễm lâu")) {
				return HivEnums.RapidTestValueset.LONG_TERM.getData();
			}
			else if (value.toLowerCase().contains("mới nhiễm")) {
				return HivEnums.RapidTestValueset.RECENT.getData();
			}
			else if (value.toLowerCase().contains("âm tính")) {
				return HivEnums.RapidTestValueset.NEGATIVE.getData();
			}
		}
		return null;
	}

	public static Valueset getRecentInfectionConclusion(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("nhiễm lâu")) {
				return HivEnums.RapidTestValueset.LONG_TERM.getData();
			}
			else if (value.toLowerCase().contains("mới nhiễm")) {
				return HivEnums.RapidTestValueset.RECENT.getData();
			}
		}
		return null;
	}

	public static Boolean getTPTCompleted(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("hoàn thành") || value.toLowerCase().contains("1")) {
				return true;
			}
			else {
				return false;
			}
		}
		return null;
	}

	public static Valueset getPregnancyOutcomeCode(String value) {
		if (value != null && StringUtils.hasText(value)) {
			//Chưa sinh
			if (value.toLowerCase().contains("1")) {
				return HivEnums.PregnancyOutcomeValueset.UNBORN.getData();
			}
			//Đã sinh
			else if (value.toLowerCase().contains("2")) {
				return HivEnums.PregnancyOutcomeValueset.BORN.getData();
			}
			//Bị sảy thai
			else if (value.toLowerCase().contains("3")) {
				return HivEnums.PregnancyOutcomeValueset.MISCARRIED.getData();
			}
			//Đã phá thai
			else if (value.toLowerCase().contains("4")) {
				return HivEnums.PregnancyOutcomeValueset.ABORTION.getData();
			}
			//Không rõ
			else {
				return HivEnums.PregnancyOutcomeValueset.UNKNOWN.getData();
			}
		}
		return null;
	}

	public static Valueset getWhoClinicalStage(String value) {
		if (value != null && StringUtils.hasText(value)) {
			//1
			if (value.toLowerCase().contains("GĐLS I".toLowerCase())) {
				return HivEnums.WhoStageValueset.STAGE1.getData();
			}
			//2
			else if (value.toLowerCase().contains("GĐLS II".toLowerCase())) {
				return HivEnums.WhoStageValueset.STAGE2.getData();
			}
			//3
			else if (value.toLowerCase().contains("GĐLS III".toLowerCase())) {
				return HivEnums.WhoStageValueset.STAGE3.getData();
			}
			//4
			else if (value.toLowerCase().contains("GĐLS IV".toLowerCase())) {
				return HivEnums.WhoStageValueset.STAGE4.getData();
			}
		}
		return null;
	}

}
