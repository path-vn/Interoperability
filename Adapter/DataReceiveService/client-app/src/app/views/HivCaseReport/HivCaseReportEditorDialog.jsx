import React, { Component } from "react";
import {
  Dialog,
  Button,
  Grid,
  FormControlLabel,
  Switch
} from "@material-ui/core";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import {getById } from "./HivCaseReportService";
import { generateRandomId } from "utils";

class HivCaseReportEditorDialog extends Component {
  state = {
    title: "",
    content: "",
    contentObj:{},
    currentAddressObj:{},
    registeredAddressObj:{},
    occupationObj:{},
    riskPopulationsObj:[],
    regimensObj:[],
    cd4ListObj:[],
    viralLoadListObj:[],
    genderObj:{},
    diagnosisObj:{},
    cd4BeforeARTObj:{},
    listOfHbvObj:[],
    ethnicityObj:{},
    isActive: false,
    //các đối tượng con của diagnosis
    diagnosisConfirmatoryLabObj:{},
    diagnosisRiskPopulationObj:{},
    diagnosisRiskBehaviorObj:{},
    diagnosisTransmissionRouteObj:{},
  };

  handleChange = (event, source) => {
    event.persist();
    if (source === "switch") {
      this.setState({ isActive: event.target.checked });
      return;
    }
    this.setState({
      [event.target.name]: event.target.value
    });
  };

  handleFormSubmit = () => {
    let { id } = this.state;
    //let { code } = this.state;
  }

  componentWillMount() {
    //getUserById(this.props.uid).then(data => this.setState({ ...data.data }));
    let { open, handleClose,item } = this.props;
    this.setState(item);
  }
  componentDidMount(){
    //debugger
    this.setState({
      contentObj: JSON.parse(this.state.content)
    }, function(){
      this.setState({
        ethnicityObj: this.state.contentObj.ethnicity,
        currentAddressObj: this.state.contentObj.currentAddress,
        registeredAddressObj: this.state.contentObj.registeredAddress,
        occupationObj: this.state.contentObj.occupation,
        riskPopulationsObj: this.state.contentObj.riskPopulations,
        regimensObj: this.state.contentObj.regimens,
        cd4ListObj: this.state.contentObj.cd4List,
        viralLoadListObj: this.state.contentObj.viralLoadList,
        genderObj: this.state.contentObj.gender,
        diagnosisObj: this.state.contentObj.diagnosis,
        cd4BeforeARTObj: this.state.contentObj.cd4BeforeART,
        listOfHbvObj: this.state.contentObj.listOfHbv,
      }, function(){
        this.setState({
          diagnosisConfirmatoryLabObj: this.state.diagnosisObj.confirmatoryLab,
          diagnosisRiskPopulationObj: this.state.diagnosisObj.riskPopulation,
          diagnosisRiskBehaviorObj: this.state.diagnosisObj.riskBehavior,
          diagnosisTransmissionRouteObj:this.state.diagnosisObj.transmissionRoute,
        })
      })
      console.log(this.state.contentObj.ethnicity)
    });
  }

  render() {
    let { open, handleClose, handleOKEditClose, t, i18n, item } = this.props;
    let {
      id,
      title,
      content,
      contentObj,
      ethnicityObj,
      currentAddressObj,
      registeredAddressObj,
      occupationObj,
      riskPopulationsObj,
      regimensObj,
      cd4ListObj,
      viralLoadListObj,
      genderObj,
      diagnosisObj,
      cd4BeforeARTObj,
      listOfHbvObj,
      diagnosisConfirmatoryLabObj,
      diagnosisRiskPopulationObj,
      diagnosisRiskBehaviorObj,
      diagnosisTransmissionRouteObj,
      description
    } = this.state;

    return (
      <Dialog  open={open}>
        <div className="p-24">
          <h4 style={{textAlign:"center"}} className="mb-20"><label style={{color:"blue", textAlign:"center"}}>{t('View Detail')}</label></h4>
          <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
            <Grid className="mb-16" container spacing={4}>
              <Grid item sm={12} xs={12}>
              <label style={{color:"blue", fontSize:20}}>Title</label>
                <TextValidator
                  className="w-100"
                  //label={t('Title')}
                  onChange={this.handleChange}
                  type="text"
                  name="title"
                  value={title?title:null}
                  validators={["required"]}
                  errorMessages={["this field is required"]}
                />
              </Grid>
              <Grid item sm={12} xs={12}>
              <label style={{color:"blue", fontSize:20}}>Content</label>
                <Grid item xs={6} sm={6} md={6}>
                <label style={{color:"blue"}}>Name:</label>
                  <TextValidator
                    className="w-100 mb-16"
                    //label={t('Name')}
                    onChange={this.handleChange}
                    type="text"
                    name="content"
                    value={contentObj?.name}
                    validators={["required"]}
                    errorMessages={["this field is required"]}
                  />
                </Grid>
                <Grid item xs={6} sm={6} md={6}>
                <label style={{color:"blue"}}>Birth Date:</label>
                  <TextValidator
                    className="w-100 mb-16"
                    //label={t('Birth Date')}
                    //onChange={this.handleChange}
                    type="text"
                    name="content"
                    value={contentObj?.birthDate}
                    validators={["required"]}
                    errorMessages={["this field is required"]}
                  />
                </Grid>
                <Grid item xs={6} sm={6} md={6}>
                <label style={{color:"blue"}}>Ethnicity:</label>
                  <TextValidator
                    className="w-100 mb-16"
                    //label={t('Ethnicity')}
                    onChange={this.handleChange}
                    type="text"
                    name="content"
                    value={ethnicityObj?.name}
                    validators={["required"]}
                    errorMessages={["this field is required"]}
                  />
                </Grid>
                <Grid item xs={6} sm={6} md={6}>
                <label style={{color:"blue"}}>Art ID:</label>
                  <TextValidator
                    className="w-100 mb-16"
                    //label={t('Art ID')}
                    onChange={this.handleChange}
                    type="text"
                    name="content"
                    value={contentObj?.artID}
                    validators={["required"]}
                    errorMessages={["this field is required"]}
                  />
                </Grid>
                <Grid item xs={6} sm={6} md={6}>
                <label style={{color:"blue"}}>National ID:</label>
                  <TextValidator
                    className="w-100 mb-16"
                    //label={t('National ID')}
                    onChange={this.handleChange}
                    type="text"
                    name="content"
                    value={contentObj?.nationalID}
                    validators={["required"]}
                    errorMessages={["this field is required"]}
                  />
                </Grid>
                <Grid item xs={6} sm={6} md={6}>
                <label style={{color:"blue"}}>Heath Insurance Number:</label>
                  <TextValidator
                    className="w-100 mb-16"
                    //label={t('Health Insurance Number')}
                    onChange={this.handleChange}
                    type="text"
                    name="content"
                    value={contentObj?.healthInsuranceNumber}
                    validators={["required"]}
                    errorMessages={["this field is required"]}
                  />
                </Grid>
                <Grid item xs={6} sm={6} md={6}>
                <label style={{color:"blue"}}>Passport Number:</label>
                  <TextValidator
                    className="w-100 mb-16"
                    //label={t('Passport Number')}
                    onChange={this.handleChange}
                    type="text"
                    name="content"
                    value={contentObj?.passportNumber}
                    validators={["required"]}
                    errorMessages={["this field is required"]}
                  />
                </Grid>
                <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>Current Adress:</label>
                  <TextValidator
                    className="w-100 mb-16"
                    //label={t('Current Adress')}
                    onChange={this.handleChange}
                    type="text"
                    name="content"
                    value={currentAddressObj?.text}
                    validators={["required"]}
                    errorMessages={["this field is required"]}
                  />
                  <TextValidator
                    className="w-100 mb-16"
                    label="Current Adress - detail"
                    onChange={this.handleChange}
                    type="text"
                    name="content"
                    value={"line: "+currentAddressObj?.line+" - city name"+currentAddressObj?.cityName+" - city code: "+currentAddressObj?.cityCode+" - commune code: "+currentAddressObj?.communeCode+" - commune name: "+currentAddressObj?.communeName+" - country code: "+currentAddressObj?.countryCode+" - country name: "+currentAddressObj?.countryName+" - type code: "+ currentAddressObj?.typeCode}
                    validators={["required"]}
                    errorMessages={["this field is required"]}
                  />
                </Grid>
                <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>Register Adress:</label>
                  <TextValidator
                    className="w-100 mb-16"
                    //label={t('Register Adress')}
                    onChange={this.handleChange}
                    type="text"
                    name="content"
                    value={registeredAddressObj?.text}
                    validators={["required"]}
                    errorMessages={["this field is required"]}
                  />
                  <TextValidator
                    className="w-100 mb-16"
                    label="Register Adress - detail"
                    onChange={this.handleChange}
                    type="text"
                    name="content"
                    value={"line: "+currentAddressObj?.line+" - city name"+registeredAddressObj?.cityName+" - city code: "+registeredAddressObj?.cityCode+" - commune code: "+registeredAddressObj?.communeCode+" - commune name: "+registeredAddressObj?.communeName+" - country code: "+currentAddressObj?.countryCode+" - country name: "+registeredAddressObj?.countryName+" - type code: "+ registeredAddressObj?.typeCode}
                    validators={["required"]}
                    errorMessages={["this field is required"]}
                  />
                </Grid>
                <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>Occupation:</label>
                  <TextValidator
                    className="w-100 mb-16 text-primary"
                    //label={t('')}
                    onChange={this.handleChange}
                    type="text"
                    name="content"
                    value={"code: "+occupationObj?.code+" - display:"+occupationObj?.display}
                    validators={["required"]}
                    errorMessages={["this field is required"]}
                    />
                </Grid>
                <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>Risk Populations List:</label>
                  {
                    riskPopulationsObj?.map(obj => {
                      return(
                        <TextValidator
                        className="w-100 mb-16"
                        //label={t('Risk Population')}
                        onChange={this.handleChange}
                        type="text"
                        name="content"
                        value={"code: "+obj?.code+" - display:"+obj?.display}
                        validators={["required"]}
                        errorMessages={["this field is required"]}
                        color="secondary"
                      />
                      )
                    })
                  }
                </Grid>
                <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>Regiments List:</label>
                  {
                    regimensObj?.map(obj => {
                      return(
                        <TextValidator
                        className="w-100 mb-16"
                        //label="Regimen"
                        onChange={this.handleChange}
                        type="text"
                        name="content"
                        value={"code: "+obj?.code+" - name: "+obj?.name+" - start date: "+obj?.startDate+" - end date"+obj?.startDate+" - regiment line: "+obj?.startDate}
                        validators={["required"]}
                        errorMessages={["this field is required"]}
                        />
                        )
                    })
                  } 
                </Grid>
                <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>CD4 List:</label>
                  {
                    cd4ListObj?.map(obj => {
                      if(obj!=null){
                        return(
                          <TextValidator
                          className="w-100 mb-16"
                          //label="CD4"
                          onChange={this.handleChange}
                          type="text"
                          name="content"
                          value={"name: "+obj?.name+" - string value: "+obj?.stringValue+" - test performance date: "+obj?.testPerformanceDate+" - specimen collection date: "+obj?.specimenCollectionDate+" - specimen collection place: "+obj?.specimenCollectionPlace+" - value number: "+obj?.valueNumber}
                          validators={["required"]}
                          errorMessages={["this field is required"]}
                        />
                        )
                      }
                    })
                  }
                </Grid>
                <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>Viral Load List:</label>
                  {
                    viralLoadListObj?.map(obj => {
                      return(
                        <TextValidator
                        className="w-100 mb-16"
                        //label="CD4"
                        onChange={this.handleChange}
                        type="text"
                        name="content"
                        value={"name: "+obj?.name+" - string value: "+obj?.stringValue+" - test performance date: "+obj?.testPerformanceDate+" - specimen collection date: "+obj?.specimenCollectionDate+" - specimen collection place: "+obj?.specimenCollectionPlace+" - value number: "+obj?.valueNumber}
                        validators={["required"]}
                        errorMessages={["this field is required"]}
                      />
                      )
                    })
                  }
                </Grid>
                <Grid item xs={6} sm={6} md={6}>
                <label style={{color:"blue"}}>Gender:</label>
                  <TextValidator
                    className="w-100 mb-16"
                    //label={t('National ID')}
                    onChange={this.handleChange}
                    type="text"
                    name="content"
                    value={genderObj.display}
                    validators={["required"]}
                    errorMessages={["this field is required"]}
                  />
                </Grid>
                <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>Diagnosis:</label>
                  <Grid item xs={12} sm={12} md={12}>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('National ID')}
                      onChange={this.handleChange}
                      type="text"
                      name="content"
                      value={"Diagnosis - confirmatory date: "+diagnosisObj?.confirmatoryDate+" - place of specimen collection: "+diagnosisObj?.placeOfSpecimenCollection+" - specimen collection date: "+diagnosisObj?.specimenCollectionDate}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                    <TextValidator
                      className="w-100 mb-16"
                      label="Diagnosis - confirmatory lab:"
                      onChange={this.handleChange}
                      type="text"
                      name="content"
                      value={"code: "+diagnosisConfirmatoryLabObj?.code+" - name: "+diagnosisConfirmatoryLabObj?.name}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                    <TextValidator
                      className="w-100 mb-16"
                      label="Diagnosis - risk population:"
                      onChange={this.handleChange}
                      type="text"
                      name="content"
                      value={"code: "+diagnosisRiskPopulationObj?.code+" - display: "+diagnosisRiskPopulationObj?.display}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                    <TextValidator
                      className="w-100 mb-16"
                      label="Diagnosis - risk behavior:"
                      onChange={this.handleChange}
                      type="text"
                      name="content"
                      value={"code: "+diagnosisRiskBehaviorObj?.code+" - display: "+diagnosisRiskBehaviorObj?.display}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                    <TextValidator
                      className="w-100 mb-16"
                      label="Diagnosis - transmission route:"
                      onChange={this.handleChange}
                      type="text"
                      name="content"
                      value={"code: "+diagnosisTransmissionRouteObj?.code+" - display: "+diagnosisTransmissionRouteObj?.display}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                            />
                    </Grid>
                </Grid>
                <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>CD4 Before ART:</label>
                  <TextValidator
                    className="w-100 mb-16"
                    //label={t('National ID')}
                    onChange={this.handleChange}
                    type="text"
                    name="content"
                    value={"name: "+cd4BeforeARTObj?.name+" - string value: "+cd4BeforeARTObj?.stringValue+" - test performance date: "+cd4BeforeARTObj?.testPerformanceDate+" - specimen collection date: "+cd4BeforeARTObj?.specimenCollectionDate+" - specimen collection place: "+cd4BeforeARTObj?.specimenCollectionPlace+" - value number: "+cd4BeforeARTObj?.valueNumber}
                    validators={["required"]}
                    errorMessages={["this field is required"]}
                  />
                </Grid>
                <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>List of HBV:</label>
                  {
                    listOfHbvObj?.map(obj => {
                      return(
                        <TextValidator
                          className="w-100 mb-16"
                          //label="CD4"
                          onChange={this.handleChange}
                          type="text"
                          name="content"
                          value={"dianosis date: "+obj?.diagnosisDate+" - treatment id: "+obj?.treatmentId}
                          validators={["required"]}
                          errorMessages={["this field is required"]}
                        />
                      )
                    })
                  }
                </Grid>
              </Grid>
            </Grid>

            <div className="flex flex-space-between flex-middle">
              {/* <Button variant="contained" color="primary" type="submit">
                Save
              </Button> */}
              <Button variant="contained" color="secondary" onClick={() => this.props.handleClose()}>Cancel</Button>
            </div>
          </ValidatorForm>
        </div>
      </Dialog>
    );
  }
}

export default HivCaseReportEditorDialog;
