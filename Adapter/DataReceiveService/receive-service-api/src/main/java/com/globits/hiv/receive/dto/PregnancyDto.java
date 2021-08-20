package com.globits.hiv.receive.dto;

import java.util.Date;
import java.util.List;

public class PregnancyDto {

    private Date dateReported;
    private OrganizationDto deliveryPlace;
    private OrganizationDto placeReported;
    private SystemCodeDto outcomeCode;
    private List<Date> deliveryDate;
    private List<Integer> gestationalAgeAtDelivery;
    private List<ChildDto> childs;

    public Date getDateReported() {
        return dateReported;
    }

    public void setDateReported(Date dateReported) {
        this.dateReported = dateReported;
    }

    public OrganizationDto getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(OrganizationDto deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public OrganizationDto getPlaceReported() {
        return placeReported;
    }

    public void setPlaceReported(OrganizationDto placeReported) {
        this.placeReported = placeReported;
    }

    public SystemCodeDto getOutcomeCode() {
        return outcomeCode;
    }

    public void setOutcomeCode(SystemCodeDto outcomeCode) {
        this.outcomeCode = outcomeCode;
    }

    public List<Date> getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(List<Date> deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<Integer> getGestationalAgeAtDelivery() {
        return gestationalAgeAtDelivery;
    }

    public void setGestationalAgeAtDelivery(List<Integer> gestationalAgeAtDelivery) {
        this.gestationalAgeAtDelivery = gestationalAgeAtDelivery;
    }

    public List<ChildDto> getChilds() {
        return childs;
    }

    public void setChilds(List<ChildDto> childs) {
        this.childs = childs;
    }

}
