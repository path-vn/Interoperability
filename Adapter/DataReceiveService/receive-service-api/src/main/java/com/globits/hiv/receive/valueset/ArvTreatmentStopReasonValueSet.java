package com.globits.hiv.receive.valueset;

import com.globits.hiv.receive.dto.SystemCodeDto;

public enum ArvTreatmentStopReasonValueSet {
    TRANSFER_OUT, DROP_OUT, DEATH;

    public SystemCodeDto getData() {
        switch (this) {
            case TRANSFER_OUT:
                return new SystemCodeDto("transfer-out	", "Transfer out", "");

            case DROP_OUT:
                return new SystemCodeDto("drop-out", "Drop out or LTFU", "");

            case DEATH:
                return new SystemCodeDto("death", "Death", "");

            default:
                return null;
        }
    }
}
