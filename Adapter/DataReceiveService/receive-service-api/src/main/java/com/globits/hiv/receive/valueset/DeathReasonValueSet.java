package com.globits.hiv.receive.valueset;

import com.globits.hiv.receive.dto.SystemCodeDto;

public enum DeathReasonValueSet {
    AIDS, OTHER_DISEASES, SUICIDE, OVERDOSE, ACCIDENT, UNKNOW, OTHER;

    public SystemCodeDto getData() {
        switch (this) {
            case AIDS:
                return new SystemCodeDto("aids", "Last stage of AIDS", "");

            case OTHER_DISEASES:
                return new SystemCodeDto("other-diseases", "Caused by other diseases", "");
            case SUICIDE:
                return new SystemCodeDto("suicide", "Suicide", "");

            case OVERDOSE:
                return new SystemCodeDto("overdose", "Drug overdose", "");

            case ACCIDENT:
                return new SystemCodeDto("accident", "	Accident", "");

            case UNKNOW:
                return new SystemCodeDto("unknown", "Unknown", "");
            case OTHER:
                return new SystemCodeDto("other", "Other", "");

            default:
                return null;
        }
    }
}
