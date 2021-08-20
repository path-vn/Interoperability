package com.globits.hiv.receive.valueset;

import com.globits.hiv.receive.dto.SystemCodeDto;

public enum RecentInfectionConclusionValueSet {
    RECENT, LONG_TERM;

    public SystemCodeDto getData() {
        switch (this) {
            case RECENT:
                return new SystemCodeDto("recent", "Recent infection", "");
            case LONG_TERM:
                return new SystemCodeDto("long-term", "Long term infection", "");
            default:
                return null;
        }
    }
}
