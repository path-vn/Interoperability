package com.globits.hiv.receive.valueset;

import com.globits.hiv.receive.dto.SystemCodeDto;

public enum TransmissionRouteValueSet {
    BLOOD, DRUG_INJECTION, BLOOD_TRANSFUSION, OCCUPATIONAL_ACCIDENT, SEXUAL_TRANSMISSION, HOMOSEXUAL, HETEROSEXUAL,
    MOTHER_TO_CHILD_TRANSMISSION, UNIDENTIFIED;

    public SystemCodeDto getData() {
        switch (this) {
            case BLOOD:
                return new SystemCodeDto("312425004", "Blood", "");
            case DRUG_INJECTION:
                return new SystemCodeDto("226034001", "Drug injection", "");
            case BLOOD_TRANSFUSION:
                return new SystemCodeDto("250373003", "Blood transfusion", "");
            case OCCUPATIONAL_ACCIDENT:
                return new SystemCodeDto("53348004", "Occupational accident", "");
            case SEXUAL_TRANSMISSION:
                return new SystemCodeDto("417564009", "Sexual Transmission", "");
            case HOMOSEXUAL:
                return new SystemCodeDto("38628009", "Homosexual", "");
            case HETEROSEXUAL:
                return new SystemCodeDto("20430005", "Heterosexual", "");
            case MOTHER_TO_CHILD_TRANSMISSION:
                return new SystemCodeDto("438998000", "Mother to Child Transmission", "");
            case UNIDENTIFIED:
                return new SystemCodeDto("69910005", "Unidentified", "");
            default:
                return null;
        }
    }
}