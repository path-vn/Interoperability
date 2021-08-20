package com.globits.hiv.receive.valueset;

import com.globits.hiv.receive.dto.SystemCodeDto;

public enum ArvEnrollmentTypeValueSet {
    NEW_REGISTRANTION, TRANSFER_IN, RE_INITIATION;

    public SystemCodeDto getData() {
        switch (this) {
            case NEW_REGISTRANTION:
                return new SystemCodeDto("new-registration", "New registration", "");

            case TRANSFER_IN:
                return new SystemCodeDto("transfer-in", "Transfer in", "");

            case RE_INITIATION:
                return new SystemCodeDto("re-initiation", "Re-initiation", "");

            default:
                return null;
        }
    }
}
