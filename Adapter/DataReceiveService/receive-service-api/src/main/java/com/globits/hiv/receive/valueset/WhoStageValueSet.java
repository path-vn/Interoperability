package com.globits.hiv.receive.valueset;

import com.globits.hiv.receive.dto.SystemCodeDto;

public enum WhoStageValueSet {
    STAGE1, STAGE2, STAGE3, STAGE4;

    public SystemCodeDto getData() {
        switch (this) {
            case STAGE1:
                return new SystemCodeDto("stage1", "WHO Stage 1", "");

            case STAGE2:
                return new SystemCodeDto("stage2", "WHO Stage 2", "");

            case STAGE3:
                return new SystemCodeDto("stage3", "WHO Stage 3", "");

            case STAGE4:
                return new SystemCodeDto("stage4", "WHO Stage 4", "");

            default:
                return null;
        }
    }
}
