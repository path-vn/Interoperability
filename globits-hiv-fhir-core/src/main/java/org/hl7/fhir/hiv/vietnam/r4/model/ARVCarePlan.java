package org.hl7.fhir.hiv.vietnam.r4.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hl7.fhir.r4.model.CarePlan;
import org.hl7.fhir.r4.model.DateType;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.api.annotation.ResourceDef;

@ResourceDef(name="CarePlan",profile = "http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-arv-careplan")
public class ARVCarePlan extends CarePlan {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Child(name = "dateOfLFTU")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-arv-careplan#dateOfLFTU", definedLocally = false, isModifier = false)
	private DateType dateOfLFTU;
	@Child(name = "dateOfTransferOut")
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-arv-careplan#dateOfTransferOut", definedLocally = false, isModifier = false)
	private DateType dateOfTransferOut;
	
	@Child(name = "placeOfTransferOut",type = {Organization.class})
	@Extension(url="http://openhie.org/fhir/hiv-casereporting/StructureDefinition/hiv-arv-careplan#placeOfTransferOut", definedLocally = false, isModifier = false)
	private Reference placeOfTransferOut;

	
	public DateType getDateOfLFTU() {
		return dateOfLFTU;
	}

	public void setDateOfLFTU(DateType dateOfLFTU) {
		this.dateOfLFTU = dateOfLFTU;
	}

	public DateType getDateOfTransferOut() {
		return dateOfTransferOut;
	}

	public void setDateOfTransferOut(DateType dateOfTransferOut) {
		this.dateOfTransferOut = dateOfTransferOut;
	}

	public Reference getPlaceOfTransferOut() {
		return placeOfTransferOut;
	}

	public void setPlaceOfTransferOut(Reference placeOfTransferOut) {
		this.placeOfTransferOut = placeOfTransferOut;
	}
	public Boolean hivEquals(ARVCarePlan other) {
		Boolean ret = false;
		if(this.getPeriod()!=null && this.getPeriod().getStart()!=null) {
			Date start = this.getPeriod().getStart();
			if(other.getPeriod()!=null && other.getPeriod().getStart()!=null) {
				Date otherDateStart =  other.getPeriod().getStart();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				ret= sdf.format(start).equals(sdf.format(otherDateStart));
			}
		}
		return ret;
	}
}
