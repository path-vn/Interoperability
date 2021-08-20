package com.globits.hiv.receive.valueset;

import com.globits.hiv.receive.dto.SystemCodeDto;

public enum OutComeCodeValueSet {
    UN_BORN, BORN, MISCARRIED, ABORTION, UNKNOW;

    public SystemCodeDto getData() {
        switch (this) {
            case UN_BORN:
                return new SystemCodeDto("unborn", "Unborn", "");

            case BORN:
                return new SystemCodeDto("born", "Born", "");
            case MISCARRIED:
                return new SystemCodeDto("miscarried", "Miscarried", "");

            case ABORTION:
                return new SystemCodeDto("abortion", "Abortion", "");

            case UNKNOW:
                return new SystemCodeDto("unknown", "Unknown", "");
            default:
                return null;
        }
    }
}
