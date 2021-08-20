package com.globits.hiv.receive.valueset;

import com.globits.hiv.receive.dto.SystemCodeDto;

public enum GenderValueSet {
    MALE, FEMALE, UNIDENTIFIED;

    public SystemCodeDto getData() {
        switch (this) {
            case MALE:
                return new SystemCodeDto("male", "Male", "");

            case FEMALE:
                return new SystemCodeDto("female", "Female", "");

            case UNIDENTIFIED:
                return new SystemCodeDto("unidentified", "Unidentified", "");

            default:
                return null;
        }
    }
}
