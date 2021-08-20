package com.globits.adapter.eclinica.utils;

import java.util.Hashtable;

public class MapRegimenLine {
	
	public static String TC ="3TC";// bậc 1
	public static String AZT ="AZT"; //bậc 1
	public static String d4T3TCNVP ="d4T + 3TC + NVP";// bậc 1
	public static String d4T3TCEFV ="d4T + 3TC + EFV"; //bậc 1
	public static String AZT3TCNVP ="AZT(ZDV) + 3TC + NVP";//bậc 1
	public static String AZT3TCEFV ="AZT(ZDV) + 3TC + EFV";//bậc 1
	public static String TDF3TCEFV ="TDF + 3TC + EFV"; // PĐ bậc 1
	public static String TDF3TCNVP ="TDF + 3TC + NVP";//bậc 1
	public static String AZT3TCLPVR ="AZT(ZDV) + 3TC + LPV/r";//bậc 2
	public static String AZT3TCATVR ="AZT(ZDV) + 3TC + ATV/r";//bậc 2
	public static String TDF3TCLPVR ="TDF + 3TC + LPV/r";//bậc 2
	public static String TDF3TCATVR="TDF + 3TC + ATV/r";//bậc 2
	public static String AZT3TCLPVRTV ="AZT(ZDV) + 3TC + LPV/r + RTV";//bậc 2
	public static String AZT3TCATVRTV ="AZT(ZDV) + 3TC + ATV/r + RTV";//bậc 2
	public static String TDF3TCLPVRTV ="TDF + 3TC + LPV/r + RTV";//bậc 2
	public static String TDF3TCATVRTV ="TDF + 3TC + ATV/r + RTV";//bậc 2
	public static String TDFAZTLPVR ="TDF + AZT/3TC + LPV/r";//bậc 2
	public static String ABCTDFLPVR ="ABC + TDF + LPV/r";//bậc 2
	public static String d4T3TCLPVR ="d4T + 3TC + LPV/r";//bậc 2
	public static String ABC3TCNVP ="ABC + 3TC + NVP";//bậc 1
	public static String ABC3TCLPVR ="ABC + 3TC + LPV/r";//bậc 2 có thể là bậc 1?
	public static String ABCddILPVR ="ABC + ddI + LPV/r";//bậc 2
	public static String AZTddILPVR ="AZT + ddI + LPV/r";//bậc 2
	public static String ddIEFVLPVR ="ddI + EFV + LPV/r";//bậc 2
	public static String ddINVPLPVR ="ddI + NVP + LPV/r";//bậc 2
	
	public static String ABC3TCEFV ="ABC + 3TC + EFV";//bậc 1
	public static String ABC3TCTDF ="ABC + 3TC + TDF";//bậc 1
	public static String ABCTDFEFV ="ABC + TDF + EFV";//bậc 1
	public static String AZT3TCABC ="AZT + 3TC + ABC";//bậc 1
	public static String AZT3TCTDF ="AZT + 3TC + TDF";//bậc 1
	public static String AZTTDF3TCATVR ="AZT + TDF + 3TC + ATV/r";//bậc 2
	public static String AZTTDF3TCLPVR ="AZT + TDF + 3TC + LPV/r";//bậc 2
	public static String AZT3TCABCLPVR ="AZT+3TC+ABC+LPV/r";//bậc 2
	public static String d4T3TCTDF ="d4T + 3TC + TDF";//bậc 1
	public static String d4TTDFEFV ="d4T + TDF + EFV";//bậc 1
	public static String TDF3TCDTG ="TDF + 3TC + DTG";//bậc 1
	public static String TDFAZTEFV ="TDF + AZT + EFV";//bậc 1
	public static Hashtable mapRegimenLine = new Hashtable<String, Integer>();
	public Integer ConceptId;
	static {
		mapRegimenLine.put(TC, 1);// bậc 1
		mapRegimenLine.put(AZT, 1);// bậc 1
		
		mapRegimenLine.put(d4T3TCNVP, 1);// bậc 1
		mapRegimenLine.put(d4T3TCEFV, 1);// bậc 1
		mapRegimenLine.put(AZT3TCNVP, 1);// bậc 1
		mapRegimenLine.put(AZT3TCEFV, 1);// bậc 1
		mapRegimenLine.put(TDF3TCEFV, 1);// bậc 1		
		mapRegimenLine.put(TDF3TCNVP, 1);// bậc 1
		
		mapRegimenLine.put(AZT3TCLPVR, 2);// bậc 2
		mapRegimenLine.put(AZT3TCATVR, 2);// bậc 2
		mapRegimenLine.put(TDF3TCLPVR, 2);// bậc 2
		mapRegimenLine.put(TDF3TCATVR, 2);// bậc 2
		
		mapRegimenLine.put(AZT3TCLPVRTV, 2);// bậc 2?
		mapRegimenLine.put(AZT3TCATVRTV, 2);// bậc 2?
		mapRegimenLine.put(TDF3TCLPVRTV,2);// bậc 2?
		mapRegimenLine.put(TDF3TCATVRTV, 2);// bậc 2?
		mapRegimenLine.put(TDFAZTLPVR, 2);// bậc 2?
		mapRegimenLine.put(ABCTDFLPVR, 2);// bậc 2?
		mapRegimenLine.put(d4T3TCLPVR, 2);// bậc 2?
		
		mapRegimenLine.put(ABC3TCNVP, 1);// bậc 1
		
		mapRegimenLine.put(ABC3TCLPVR, 2);// bậc 1 có thể là 2
		
		mapRegimenLine.put(ABCddILPVR, 2);// bậc 2
		mapRegimenLine.put(AZTddILPVR,2);// bậc 2
		mapRegimenLine.put(ddIEFVLPVR, 2);// bậc 2
		mapRegimenLine.put(ddINVPLPVR, 2);// bậc 2
		
		
		mapRegimenLine.put(ABC3TCEFV, 1);// bậc 1
		mapRegimenLine.put(ABC3TCTDF, 1);// bậc 1
		mapRegimenLine.put(ABCTDFEFV, 1);// bậc 1
		mapRegimenLine.put(AZT3TCABC, 1);// bậc 1
		mapRegimenLine.put(AZT3TCTDF, 1);// bậc 1		
		mapRegimenLine.put(AZTTDF3TCATVR, 2);// bậc 2
		
		mapRegimenLine.put(AZTTDF3TCLPVR, 2);// bậc 2
		mapRegimenLine.put(AZT3TCABCLPVR, 2);// bậc 2
		mapRegimenLine.put(d4T3TCTDF, 1);// bậc 1
		mapRegimenLine.put(d4TTDFEFV, 1);// bậc 1
		mapRegimenLine.put(TDF3TCDTG, 1);// bậc 1		
		mapRegimenLine.put(TDFAZTEFV, 1);// bậc 1
	}

}
