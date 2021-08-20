package com.globits.hiv.receive.valueset;

import com.globits.hiv.receive.dto.SystemCodeDto;

public enum BirthDefectsValueSet {
    POSITIVE, NEGATIVE, UNIDENTIFIED, UNKNOW;

    public SystemCodeDto getData() {
        switch (this) {
            case POSITIVE:
                return new SystemCodeDto("Positive", "Positive", "");

            case NEGATIVE:
                return new SystemCodeDto("Negative", "Negative", "");
            case UNIDENTIFIED:
                return new SystemCodeDto("Unidentified", "Unidentified", "");
            case UNKNOW:
                return new SystemCodeDto("unknown", "Unknown", "");
            default:
                return null;
        }
    }
}
