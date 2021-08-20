package com.globits.hiv.receive.valueset;

import com.globits.hiv.receive.dto.SystemCodeDto;

public enum EthnicityValueSet {
    KINH, TAY, THAI, HOA, KHO_ME, MUONG, NUNG, HMONG, DAO, GIA_RAI, NGAI, E_DE, BA_NA, XO_DANG, SAN_CHAY, CO_HO,
    CHAM, SAN_DIU, HRE, MNONG, RA_GLAI, XTIENG, BRU_VAN_KIEU, THO, GIAY, CO_TU, GIE_TRIENG, MA, KHO_MU, CO, TA_OI,
    CHO_RO, KHANG, XINH_MUN, HA_NHI, CHU_RU, LAO, LA_CHI, LA_HA, PHU_LA, LA_HU, LU, LO_LO, CHUT, MANG, PA_THEN,
    CO_LAO, CONG, BO_Y, SI_LA, PU_PEO, BRAU, O_DU, RO_MAM, NGUOI_NUOC_NGOAI, KHONGRO;

    public SystemCodeDto getData() {
        switch (this) {
            case KINH:
                return new SystemCodeDto("1", "Kinh", "");
            case TAY:
                return new SystemCodeDto("2", "Tày", "");
            case THAI:
                return new SystemCodeDto("3", "Thái", "");
            case HOA:
                return new SystemCodeDto("4", "Hoa", "");
            case KHO_ME:
                return new SystemCodeDto("5", "Khơ-me", "");
            case MUONG:
                return new SystemCodeDto("6", "Mường", "");
            case NUNG:
                return new SystemCodeDto("7", "Nùng", "");
            case HMONG:
                return new SystemCodeDto("8", "Hmông", "");
            case DAO:
                return new SystemCodeDto("9", "Dao", "");
            case GIA_RAI:
                return new SystemCodeDto("10", "Gia-rai", "");
            case NGAI:
                return new SystemCodeDto("11", "Ngái", "");
            case E_DE:
                return new SystemCodeDto("12", "Ê-đê", "");
            case BA_NA:
                return new SystemCodeDto("13", "Ba-na", "");
            case XO_DANG:
                return new SystemCodeDto("14", "Xơ-đăng", "");
            case SAN_CHAY:
                return new SystemCodeDto("15", "Sán Chay", "");
            case CO_HO:
                return new SystemCodeDto("16", "Cơ-ho", "");
            case CHAM:
                return new SystemCodeDto("17", "Chăm", "");
            case SAN_DIU:
                return new SystemCodeDto("18", "Sán Dìu", "");
            case HRE:
                return new SystemCodeDto("19", "Hrê", "");
            case MNONG:
                return new SystemCodeDto("20", "Mnông", "");
            case RA_GLAI:
                return new SystemCodeDto("21", "Ra-glai", "");
            case XTIENG:
                return new SystemCodeDto("22", "Xtiêng", "");
            case BRU_VAN_KIEU:
                return new SystemCodeDto("23", "Bru-Vân Kiều", "");
            case THO:
                return new SystemCodeDto("24", "Thổ", "");
            case GIAY:
                return new SystemCodeDto("25", "Giáy", "");
            case CO_TU:
                return new SystemCodeDto("26", "Cơ-tu", "");
            case GIE_TRIENG:
                return new SystemCodeDto("27", "Gié-Triêng", "");
            case MA:
                return new SystemCodeDto("28", "Mạ", "");
            case KHO_MU:
                return new SystemCodeDto("29", "Khơ-mú", "");
            case CO:
                return new SystemCodeDto("30", "Co", "");
            case TA_OI:
                return new SystemCodeDto("31", "Ta-ôi", "");
            case CHO_RO:
                return new SystemCodeDto("32", "Chơ-ro", "");
            case KHANG:
                return new SystemCodeDto("33", "Kháng", "");
            case XINH_MUN:
                return new SystemCodeDto("34", "Xinh-mun", "");
            case HA_NHI:
                return new SystemCodeDto("35", "Hà Nhì", "");
            case CHU_RU:
                return new SystemCodeDto("36", "Chu-ru", "");
            case LAO:
                return new SystemCodeDto("37", "Lào", "");
            case LA_CHI:
                return new SystemCodeDto("38", "La Chi", "");
            case LA_HA:
                return new SystemCodeDto("39", "La Ha", "");
            case PHU_LA:
                return new SystemCodeDto("40", "Phù Lá", "");
            case LA_HU:
                return new SystemCodeDto("41", "La Hủ", "");
            case LU:
                return new SystemCodeDto("42", "Lự", "");
            case LO_LO:
                return new SystemCodeDto("43", "Lô Lô", "");
            case CHUT:
                return new SystemCodeDto("44", "Chứt", "");
            case MANG:
                return new SystemCodeDto("45", "Mảng", "");
            case PA_THEN:
                return new SystemCodeDto("46", "Pà Thẻn", "");
            case CO_LAO:
                return new SystemCodeDto("47", "Cơ Lao", "");
            case CONG:
                return new SystemCodeDto("48", "Cống", "");
            case BO_Y:
                return new SystemCodeDto("49", "Bố Y", "");
            case SI_LA:
                return new SystemCodeDto("50", "Si La", "");
            case PU_PEO:
                return new SystemCodeDto("51", "Pu Péo", "");
            case BRAU:
                return new SystemCodeDto("52", "Brâu", "");
            case O_DU:
                return new SystemCodeDto("53", "Ơ Đu", "");
            case RO_MAM:
                return new SystemCodeDto("54", "Rơ-măm", "");
            case NGUOI_NUOC_NGOAI:
                return new SystemCodeDto("55", "Người nước ngoài", "");
            case KHONGRO:
                return new SystemCodeDto("0", "Không rõ", "");
            default:
                return null;
        }
    }
}
