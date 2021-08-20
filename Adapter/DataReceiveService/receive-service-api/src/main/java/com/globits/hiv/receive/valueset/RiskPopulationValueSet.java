package com.globits.hiv.receive.valueset;

import com.globits.hiv.receive.dto.SystemCodeDto;

public enum RiskPopulationValueSet {
    PWID, MSM, SW, CLIENT_SW, PARTNER_PLHIV, TG_WOMEN, TG_MEN, CHILD_WOMAN_HIV, CLIENT_SEXW, BLOOD_DONOR, SUSPECT_HIV,
    TB_PATIENT, STD_PERSON, PRISONER, PREGNANT, MILITARY_RECRUITS, OTHER;

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
