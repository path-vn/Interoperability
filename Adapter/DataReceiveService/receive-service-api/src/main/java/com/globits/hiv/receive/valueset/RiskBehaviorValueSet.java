package com.globits.hiv.receive.valueset;
import com.globits.hiv.receive.dto.SystemCodeDto;

public enum RiskBehaviorValueSet {
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
