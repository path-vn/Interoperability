package com.globits.hiv.receive.valueset;

import com.globits.hiv.receive.dto.SystemCodeDto;

public enum LabTestResultOtherValueSet {
    UNDETECTABLE, RECENT, LONG_TERM, NEGATIVE;

    public SystemCodeDto getData() {
        switch (this) {
            case UNDETECTABLE:
                return new SystemCodeDto("undetectable", "Undetectable", "");
            case RECENT:
                return new SystemCodeDto("recent", "Recent", "");
            case LONG_TERM:
                return new SystemCodeDto("long-term", "Long term", "");
            case NEGATIVE:
                return new SystemCodeDto("negative", "Negative", "");
            default:
                return null;
        }
    }
}
