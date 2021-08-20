package com.globits.hiv.receive.valueset;

import com.globits.hiv.receive.dto.SystemCodeDto;

public enum RegimenChangeReasonValueSet {
    LACK_OF_DRUG, SIDE_EFFECTS, TREATMENT_FAILURE, DRUG_INTERACTION, NEW_GUIDANCE, OTHER;

    public SystemCodeDto getData() {
        switch (this) {
            case LACK_OF_DRUG:
                return new SystemCodeDto("lack_of_drug", "Lack of drug", "");
            case SIDE_EFFECTS:
                return new SystemCodeDto("side_effects", "Side effects", "");
            case TREATMENT_FAILURE:
                return new SystemCodeDto("treatment_failure", "Treatment failure", "");
            case DRUG_INTERACTION:
                return new SystemCodeDto("drug_interaction", "Drug interaction", "");
            case NEW_GUIDANCE:
                return new SystemCodeDto("new-guidance", "New regimen as national guidance", "");
            case OTHER:
                return new SystemCodeDto("other", "Other", "");

            default:
                return null;
        }
    }
}
