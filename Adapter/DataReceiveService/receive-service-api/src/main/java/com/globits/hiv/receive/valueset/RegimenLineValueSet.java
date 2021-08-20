package com.globits.hiv.receive.valueset;

import com.globits.hiv.receive.dto.SystemCodeDto;

public enum RegimenLineValueSet {
    LVL1, LVL2, LVL3;

    public SystemCodeDto getData() {
        switch (this) {
            case LVL1:
                return new SystemCodeDto("lvl1", "First line", "");

            case LVL2:
                return new SystemCodeDto("lvl2", "Second line", "");

            case LVL3:
                return new SystemCodeDto("lvl3", "Third line", "");

            default:
                return null;
        }
    }
}
