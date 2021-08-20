package Utils;

import org.springframework.util.StringUtils;

import com.globits.fhir.hiv.utils.HivEnums;
import com.globits.fhir.hiv.utils.Valueset;

public class HtcElogMediatorConstants {
	public static Valueset getPopulationGroupByText(String value) {
		if (value != null && StringUtils.hasText(value)) {
			if (value.toLowerCase().contains("Tiêm chích ma tuý") || value.toLowerCase().contains("chích ma túy") 
					|| value.toLowerCase().contains("People who inject drug (PWID)") || value.toLowerCase().contains("risk_1")) {
				return HivEnums.PopulationGroupValueset.PWID.getData();
			} else if ((value.toLowerCase().contains("nam")
					&& value.toLowerCase().contains("Quan hệ đồng giới")) || value.toLowerCase().contains("risk_2")
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
			}else if (value.toLowerCase().contains("Bạn tình người nhiễm") || value.toLowerCase().contains("risk_6")
					 || value.toLowerCase().contains("Sex partners of PLHIV") ) {
				return HivEnums.PopulationGroupValueset.PARTNER_PLHIV.getData();
			}else if (value.toLowerCase().contains("Khách mua dâm") || value.toLowerCase().contains("risk_8")
					|| value.toLowerCase().contains("Clients of sex workers") ) {
				return HivEnums.PopulationGroupValueset.CLIENT_SW.getData();
			}else {
				return HivEnums.PopulationGroupValueset.OTHER.getData();
			}
		}
		return null;
	}
}
