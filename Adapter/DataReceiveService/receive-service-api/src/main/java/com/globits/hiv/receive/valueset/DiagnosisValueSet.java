package com.globits.hiv.receive.valueset;

import com.globits.hiv.receive.dto.SystemCodeDto;

public enum DiagnosisValueSet {
    NEGATIVE, POSITIVE;

    public SystemCodeDto getData() {
        switch (this) {
            case NEGATIVE:
                return new SystemCodeDto("negative", "Negative", "");

            case POSITIVE:
                return new SystemCodeDto("positive", "Positive", "");

            default:
                return null;
        }
    }
}
