package com.globits.hiv.receive.valueset;
import org.springframework.util.StringUtils;
import com.globits.hiv.receive.dto.SystemCodeDto;

public enum RegimenValueSet {
    REG001, REG002, REG003, REG004, REG005, REG006, REG007, REG008, REG009, REG010, REG011, REG012, REG013, REG014,
    REG015, REG016, REG017, REG018, REG019, REG020, REG021, REG022, REG023, REG024, REG025, REG026, REG027, REG028,
    REG029, REG030, REG031, REG032, REG033, REG034, REG035, REG036, REG037, REG038, REG039, REG040, REG041, REG042,
    REG043, REG044, REG045, REG046, REG047;

    public SystemCodeDto getData() {
        switch (this) {
            case REG001:
                return new SystemCodeDto("reg001", "(TDF/3TC/DTG 300/300/50)", "");
            case REG002:
                return new SystemCodeDto("reg002", "(TDF/3TC/EFV 300/300/400)", "");
            case REG003:
                return new SystemCodeDto("reg003", "(TDF/3TC/EFV 300/300/600)", "");
            case REG004:
                return new SystemCodeDto("reg004", "(ZDV/3TC 300/150)(EFV 600)", "");
            case REG005:
                return new SystemCodeDto("reg005", "(ZDV/3TC 300/150)(EFV 200)", "");
            case REG006:
                return new SystemCodeDto("reg006", "(ZDV/3TC/NVP 300/150/200)", "");
            case REG007:
                return new SystemCodeDto("reg007", "(ABC 300)(3TC 150)(DTG 50)", "");
            case REG008:
                return new SystemCodeDto("reg008", "(TDF 300)(3TC 150)(RAL 400)", "");
            case REG009:
                return new SystemCodeDto("reg009", "(TDF/FTC 300/200)(RAL 400)", "");
            case REG010:
                return new SystemCodeDto("reg010", "(TAF 25)(3TC 150)(DTG 50)", "");
            case REG011:
                return new SystemCodeDto("reg011", "(TDF/FTC 300/200)(EFV 600)", "");
            case REG012:
                return new SystemCodeDto("reg012", "(ZDV/3TC 300/150)(LPV/r 100/25)", "");
            case REG013:
                return new SystemCodeDto("reg013", "(ZDV/3TC 300/150)(LPV/r 200/50)", "");
            case REG014:
                return new SystemCodeDto("reg014", "(ZDV/3TC 300/150)(DTG 50)", "");
            case REG015:
                return new SystemCodeDto("reg015", "(ZDV/3TC 300/150)(ATV 300)(RTV 100)", "");
            case REG016:
                return new SystemCodeDto("reg016", "(ZDV/3TC 300/150)(DRV 600)(RTV 100)", "");
            case REG017:
                return new SystemCodeDto("reg017", "(TDF/FTC 300/200)(DTG 50)", "");
            case REG018:
                return new SystemCodeDto("reg018", "(TDF/FTC 300/200)(ATV 300)(RTV 100)", "");
            case REG019:
                return new SystemCodeDto("reg019", "(TDF/FTC 300/200)(DRV 600)(RTV 100)", "");
            case REG020:
                return new SystemCodeDto("reg020", "(TDF 300)(3TC 150)(ATV 300)(RTV 100)", "");
            case REG021:
                return new SystemCodeDto("reg021", "(TDF 300)(3TC 150)(DRV 600)(RTV 100)", "");
            case REG022:
                return new SystemCodeDto("reg022", "(TDF 300)(3TC 150)(LPV/r 100/25)", "");
            case REG023:
                return new SystemCodeDto("reg023", "(TDF 300)(3TC 150)(LPV/r 200/50)", "");
            case REG024:
                return new SystemCodeDto("reg024", "(TDF 300)(3TC 150)(LPV/r 80/20mg/ml)", "");
            case REG025:
                return new SystemCodeDto("reg025", "(TDF/FTC 300/200)(LPV/r 100/25)", "");
            case REG026:
                return new SystemCodeDto("reg026", "(TDF/FTC 300/200)(LPV/r 200/50)", "");
            case REG027:
                return new SystemCodeDto("reg027", "(TDF/FTC 300/200)(LPV/r 80/20mg/ml)", "");
            case REG028:
                return new SystemCodeDto("reg028", "(ABC 300)(ZDV/3TC 300/150)", "");
            case REG029:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG030:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG031:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG032:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG033:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG034:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG035:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG036:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG037:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG038:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG039:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG040:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG041:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG042:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG043:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG044:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG045:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG046:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            case REG047:
                return new SystemCodeDto("reg029", "(ABC 300)(ZDV/3TC 60/30)", "");
            default:
                return null;
        }
    }
}
