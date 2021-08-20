package com.globits.hiv.receive.valueset;

import com.globits.hiv.receive.dto.SystemCodeDto;

public enum LabTestReasonValueSet {

    BEFORE_ART, ROUTINE_TEST, ROUTINE_6, ROUTINE_12, TREATMENT_FAIL, AFTER_HIGH_3_MOUNT, PREGNANT, OTHER;

    public SystemCodeDto getData() {
        switch (this) {
            case BEFORE_ART:
                return new SystemCodeDto("before_art", "Before ART", "");
            case ROUTINE_TEST:
                return new SystemCodeDto("routine_test", "Routine test", "");
            case ROUTINE_6:
                return new SystemCodeDto("routine-6", "6 months after ART initiation", "");
            case ROUTINE_12:
                return new SystemCodeDto("routine-12", "12 months after ART initiation", "");
            case TREATMENT_FAIL:
                return new SystemCodeDto("treatment-fail", "Suspected treatment failure (clinical, immunological)", "");
            case AFTER_HIGH_3_MOUNT:
                return new SystemCodeDto("3month-after-high", "3 month after high viral load", "");
            case PREGNANT:
                return new SystemCodeDto("pregnant", "Pregnant/Lactating woman", "");
            case OTHER:
                return new SystemCodeDto("other", "Other", "");

            default:
                return null;
        }
    }
}
