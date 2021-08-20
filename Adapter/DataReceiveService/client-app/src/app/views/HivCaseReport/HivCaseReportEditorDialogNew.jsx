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

class HivCaseReportEditorDialogNew extends Component {
  state = {
    title: "",
    patientDto:{},
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
   this.setState({
       patientDto: this.state.patientDto?this.state.patientDto:null,
   })
  }

  render() {
    let { open, handleClose, handleOKEditClose, t, i18n, item } = this.props;
    let {
      id,
      title,
      patientDto,
    } = this.state;

    return (
      <Dialog  open={open}>
        <div className="p-24">
          <h4 style={{textAlign:"center", backgroundColor: "#e6f2ff",padding:16}} className="mb-20"><label style={{color:"blue", textAlign:"center"}}>{t('Detail')}</label></h4>
          <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
            <Grid className="mb-16" container spacing={4}>
              <Grid item sm={12} xs={12}>
              <label style={{color:"blue", fontSize:20}}>Title</label>
                <TextValidator
                  style={{margin:"10px 10px"}}
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
              </Grid>
              {
                this.state.patientDto && (
                  <Grid className="mb-16" container spacing={4} style={{margin:"12px 12px"}}>
                    <Grid item xs={12} sm={12} md={6}>
                    <label style={{color:"blue"}}>Name:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('Name')}
                      onChange={this.handleChange}
                      type="text"
                      name="name"
                      value={patientDto?.name}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                    </Grid>

                  <Grid item xs={12} sm={12} md={6}>
                  <label style={{color:"blue"}}>Birth Date:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('Birth Date')}
                      //onChange={this.handleChange}
                      type="text"
                      name="birthDate"
                      value={new Date(patientDto?.birthDate).toLocaleDateString()}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                  </Grid>
                  <Grid item xs={12} sm={12} md={6}>
                  <label style={{color:"blue"}}>Gender:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('Ethnicity')}
                      type="text"
                      name="gender"
                      value={patientDto?.gender?.display}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                  </Grid>
                  <Grid item xs={12} sm={12} md={6}>
                  <label style={{color:"blue"}}>Ethnicity:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('Ethnicity')}
                      type="text"
                      name="ethnicity"
                      value={patientDto?.ethnicity?.display}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                  </Grid>
                <Grid item xs={12} sm={12} md={6}>
                  <label style={{color:"blue"}}>National ID09:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('National ID')}
                    
                      type="text"
                      name="content"
                      value={patientDto?.nationalID9}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                  </Grid>
                  <Grid item xs={12} sm={12} md={6}>
                  <label style={{color:"blue"}}>National ID12:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('National ID')}
                    
                      type="text"
                      name="content"
                      value={patientDto?.nationalID12}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                  </Grid>
                  <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>Register Address:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('National ID')}
                      type="text"
                      name="registeredAddress"
                      value={patientDto?.registeredAddress?.text}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                  </Grid>
                  <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>Current Address:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('National ID')}
                      type="text"
                      name="currentAddress"
                      value={patientDto?.currentAddress?.text}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                  </Grid>
                  <Grid item xs={12} sm={12} md={6}>
                  <label style={{color:"blue"}}>Passport Number:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('National ID')}
                      type="text"
                      name="passportNumber"
                      value={patientDto?.passportNumber}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                  </Grid>
                  <Grid item xs={12} sm={12} md={6}>
                  <label style={{color:"blue"}}>Driver License:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('National ID')}
                      type="text"
                      name="driverLicense"
                      value={patientDto?.driverLicense}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                  </Grid>
                  <Grid item xs={12} sm={12} md={6}>
                  <label style={{color:"blue"}}>Insurance Number:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('National ID')}
                      type="text"
                      name="insuranceNumber"
                      value={patientDto?.insuranceNumber}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                  </Grid>
                  <Grid item xs={12} sm={12} md={6}>
                  <label style={{color:"blue"}}>Insurance Exp Date:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('National ID')}
                      type="text"
                      name="insuranceExpDate"
                      value={new Date(patientDto?.insuranceExpDate).toLocaleDateString()}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                  </Grid>
                  
                  <Grid item xs={12} sm={12} md={6}>
                  <label style={{color:"blue"}}>Facility:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('National ID')}
                      type="text"
                      name="facility"
                      value={patientDto?.facility?.display}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                  </Grid>
                  
                  <Grid item xs={12} sm={12} md={6}>
                  <label style={{color:"blue"}}>Occupation:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('National ID')}
                      type="text"
                      name="occupation"
                      value={patientDto?.occupation?.display}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                  </Grid>
                  <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>Arv Number:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('National ID')}
                      type="text"
                      name="arvNumber"
                      value={patientDto?.arvNumber}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                  </Grid>
                  <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>Death:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('National ID')}
                      type="text"
                      name="death"
                      value={"Date of death: "+new Date(patientDto?.death?.dateOfDeath).toLocaleDateString()+" - Cause: "+patientDto?.death?.causeOfDeath?.display}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                  </Grid>
                  <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>Pregnancies:</label>
                  {
                      patientDto?.pregnancies?.map(obj => {
                        return(
                          <Grid item xs={12} sm={12} md={12}>
                            <TextValidator
                            className="w-100 mb-16"
                            //label={t('Risk Population')}
                            //onChange={this.handleChange}
                            type="text"
                            name="content"
                            value={"Date reported: "+new Date(obj?.dateReported).toLocaleDateString()+", Delivery place: "+obj?.deliveryPlace?.name+", Outcome code: "+obj?.outcomeCode?.display+", Place reported: "+obj?.placeReported?.name}
                            validators={["required"]}
                            errorMessages={["this field is required"]}
                            color="secondary"
                          />
                        {
                          obj?.childs?.map((childObj, index) => {
                            return(
                              <TextValidator
                              className="w-100 mb-16"
                              label={t('Pregnancies - Child ')+(index+1)}
                              //onChange={this.handleChange}
                              type="text"
                              name="child"
                              value={"Birth weight: "+childObj?.birthWeight + ", Birth defects: "+childObj?.birthDefects+", Child Arv start date: "+new Date(childObj?.childArvStartDate).toLocaleDateString()+", HIV diagnosis date: "+new Date(childObj?.hivDiagnosisDate).toLocaleDateString()+", HIV status: "+childObj?.hivStatus?.display}
                              validators={["required"]}
                              errorMessages={["this field is required"]}
                              color="secondary"
                              />
                            )
                          })
                        }
                        {
                          obj?.gestationalAgeAtDelivery?.map((gadObj, index) => {
                            return(
                              <TextValidator
                              className="w-100 mb-16"
                              label={t('Pregnancies - Gestational Age At Delivery ')+(index+1)}
                              //onChange={this.handleChange}
                              type="text"
                              name="child"
                              value={gadObj}
                              validators={["required"]}
                              errorMessages={["this field is required"]}
                              color="secondary"
                              />
                          )
                          })
                        }
                        </Grid>
                        )
                      })
                    }
                  </Grid>
                  <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>CD4List:</label>
                  {
                      patientDto?.cd4List?.map((obj, index) => {
                        return(
                          <Grid item xs={12} sm={12} md={12}>
                            <TextValidator
                            className="w-100 mb-16"
                            label={t('CD4 ')+(index+1)}
                            //onChange={this.handleChange}
                            type="text"
                            name="content"
                            value={"Specimen collection date: "+new Date(obj?.specimenCollectionDate).toLocaleDateString()+", Specimen collection place: "+ obj?.specimenCollectionPlace?.name+", Test performance date: "+new Date(obj?.testPerformanceDate).toLocaleDateString()+", Test reason: "+obj?.testReason?.display}
                            validators={["required"]}
                            errorMessages={["this field is required"]}
                            color="secondary"
                          />
                          <TextValidator
                            className="w-100 mb-16"
                            label={t('CD4 ')+(index+1)}
                            //onChange={this.handleChange}
                            type="text"
                            name="content"
                            value={"RESULT - "+"Value number: "+obj?.valueNumber+", String value: "+obj?.stringValue+", Test result other: "+obj?.testResultOther}
                            validators={["required"]}
                            errorMessages={["this field is required"]}
                            color="secondary"
                          />
                        </Grid>
                        )
                      })
                    }
                  </Grid>
                  <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>Drug Resistance List:</label>
                  {
                      patientDto?.drugResistanceList?.map((obj,index) => {
                        return(
                          <Grid item xs={12} sm={12} md={12}>
                           <Grid item xs={12} sm={12} md={12}>
                            <TextValidator
                            className="w-100 mb-16"
                            label={t('Drug resistance ')+(index+1)}
                            //onChange={this.handleChange}
                            type="text"
                            name="content"
                            value={"Specimen collection date: "+new Date(obj?.specimenCollectionDate).toLocaleDateString()+", Specimen collection place: "+ obj?.specimenCollectionPlace?.name+", Test performance date: "+new Date(obj?.testPerformanceDate).toLocaleDateString()+", Test reason: "+obj?.testReason?.display}
                            validators={["required"]}
                            errorMessages={["this field is required"]}
                            color="secondary"
                          />
                          <TextValidator
                            className="w-100 mb-16"
                            label={t('Drug resistance ')+(index+1)}
                            //onChange={this.handleChange}
                            type="text"
                            name="content"
                            value={"RESULT - "+"Value number: "+obj?.valueNumber+", String value: "+obj?.stringValue+", Test result other: "+obj?.testResultOther}
                            validators={["required"]}
                            errorMessages={["this field is required"]}
                            color="secondary"
                          />
                        </Grid>

                        </Grid>
                        )
                      })
                    }
                  </Grid>
                  <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>Diagnosis:</label>
                    <TextValidator
                      className="w-100 mb-16"
                      //label={t('National ID')}
                      type="text"
                      name="diagnosis"
                      value={"Confirmatory date: "+new Date(patientDto?.diagnosis?.confirmatoryDate).toLocaleDateString()+", Confirmatory Lab: "+ patientDto?.diagnosis?.confirmatoryLab?.name+", Specimen collection date: "+new Date(patientDto?.diagnosis?.confirmatoryDate).toLocaleDateString()}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                     <TextValidator
                      className="w-100 mb-16"
                      //label={t('National ID')}
                      type="text"
                      name="diagnosis"
                      value={"Risk behavior: "+patientDto?.diagnosis?.riskBehavior?.display+", "+"Risk population: "+patientDto?.diagnosis?.riskPopulation?.display+", Transmission route: "+ patientDto?.diagnosis?.transmissionRoute?.display}
                      validators={["required"]}
                      errorMessages={["this field is required"]}
                    />
                  </Grid>
                  <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>List of Arv treatment:</label>
                  {
                      patientDto?.listOfArvTreatment?.map(obj => {
                        return(
                          <Grid item xs={12} sm={12} md={12}>
                            <TextValidator
                            className="w-100 mb-16"
                            //label={t('Risk Population')}
                            //onChange={this.handleChange}
                            type="text"
                            name="content"
                            value={"Date last examination: "+new Date(obj?.dateLastExamination).toLocaleDateString()+", Date next appointment: "+new Date(obj?.dateNextAppointment).toLocaleDateString()+", Enrollment date: "+new Date(obj?.enrollmentDate).toLocaleDateString()+", Enrollment facility: "+obj?.enrollmentFacility?.display +", Facility transfer in: "+ obj?.facilityTransferIn?.name+", Initiation date: "+ new Date(obj?.initiationDate).toLocaleDateString()+", initiationFacility: "+ obj?.initiationFacility?.name+", placeTransferOut:" +obj?.placeTransferOut?.display+", treatmentStopDate:"+new Date(obj?.treatmentStopDate).toLocaleDateString()+", treatmentStopReason:" +obj?.treatmentStopReason?.display}
                            validators={["required"]}
                            errorMessages={["this field is required"]}
                            color="secondary"
                          />
                        {
                          obj?.arvRegimens?.map((aRObj, index) => {
                            return(
                              <TextValidator
                              className="w-100 mb-16"
                              label={t('Arv Regiment ')+(index+1)}
                              //onChange={this.handleChange}
                              type="text"
                              name="arvRegiment"
                              value={"dateRegimenStarted: "+new Date(aRObj?.dateRegimenStarted).toLocaleDateString()+", dateRegimenStopped:"+new Date(aRObj?.dateRegimenStopped).toLocaleDateString()+", line: "+aRObj?.line?.display+", name: "+aRObj?.name?.display+", regimenChangeReason: "+ aRObj?.regimenChangeReason?.display}
                              validators={["required"]}
                              errorMessages={["this field is required"]}
                              color="secondary"
                              />
                            )
                          })
                        }
                        {
                          obj?.whoStage?.map((whoStageObj, index) => {
                            return(
                              <TextValidator
                              className="w-100 mb-16"
                              label={t('WhoStage ')+(index+1)}
                              //onChange={this.handleChange}
                              type="text"
                              name="whoStage"
                              value={"dateWhoStage: "+new Date(whoStageObj?.dateWhoStage).toLocaleDateString()+", whoClinicalStage: "+whoStageObj?.display}
                              validators={["required"]}
                              errorMessages={["this field is required"]}
                              color="secondary"
                              />
                          )
                          })
                        }
                        </Grid>
                        )
                      })
                    }
                  </Grid>
                  <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>List Of Hbv:</label>
                  {
                      patientDto?.listOfHbv?.map(obj => {
                        return(
                          <Grid item xs={12} sm={12} md={12}>
                            <TextValidator
                            className="w-100 mb-16"
                            //label={t('Risk Population')}
                            //onChange={this.handleChange}
                            type="text"
                            name="content"
                            value={"diagnosisDate: "+ new Date(obj?.diagnosisDate).toLocaleDateString()+", diagnosisResult: "+obj?.diagnosisResult?.display+", hbsagDate: "+new Date(obj.hbsagDate).toLocaleDateString()+", hbsagResult: "+ obj?.hbsagResult?.display}
                            validators={["required"]}
                            errorMessages={["this field is required"]}
                            color="secondary"
                          />
                        {
                          obj?.treatment?.map((tObj, index) => {
                            return(
                              <TextValidator
                              className="w-100 mb-16"
                              label={t('Treatment ')+(index+1)}
                              //onChange={this.handleChange}
                              type="text"
                              name="tratement"
                              value={"startDate: "+ new Date(tObj?.startDate).toLocaleDateString()+", endDate: "+new Date(tObj?.endDate).toLocaleDateString()+", isComplete: "+tObj?.isComplete+", placeProvided: "+ tObj?.placeProvided?.name+", treatmentId: "+tObj?.treatmentId}
                              validators={["required"]}
                              errorMessages={["this field is required"]}
                              color="secondary"
                              />
                            )
                          })
                        }
                        </Grid>
                        )
                      })
                    }
                  </Grid>
                  <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>List of Hcv:</label>
                  {
                      patientDto?.listOfHcv?.map(obj => {
                        return(
                          <Grid item xs={12} sm={12} md={12}>
                            <TextValidator
                            className="w-100 mb-16"
                            //label={t('Risk Population')}
                            //onChange={this.handleChange}
                            type="text"
                            name="listOfHcv"
                            value={"antiHcvDate: "+new Date(obj?.antiHcvDate).toLocaleDateString()+",  antiHcvResult:"+obj?.antiHcvResult+", diagnosisDate: "+new Date(obj?.diagnosisDate).toLocaleDateString()+", diagnosisResult: "+obj?.diagnosisResult+", vlDiagnosisDate: "+new Date(obj?.vlDiagnosisDate).toLocaleDateString()+", vlDiagnosisResult: "+obj?.vlDiagnosisResult}
                            validators={["required"]}
                            errorMessages={["this field is required"]}
                            color="secondary"
                          />
                        {
                          obj?.treatment?.map((tObj, index) => {
                            return(
                              <TextValidator
                              className="w-100 mb-16"
                              label={t('Treatment ')+(index+1)}
                              //onChange={this.handleChange}
                              type="text"
                              name="tratement"
                              value={"startDate: "+ new Date(tObj?.startDate).toLocaleDateString()+", endDate: "+ new Date(tObj?.endDate).toLocaleDateString()+", isComplete: "+tObj?.isComplete+", placeProvided: "+ tObj?.placeProvided?.name+", treatmentId: "+tObj?.treatmentId}
                              validators={["required"]}
                              errorMessages={["this field is required"]}
                              color="secondary"
                              />
                            )
                          })
                        }
                        </Grid>
                        )
                      })
                    }
                  </Grid>
                  <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>Tuberculosis:</label>
                  {
                      patientDto?.tuberculosis?.map(obj => {
                        return(
                          <Grid item xs={12} sm={12} md={12}>
                            <TextValidator
                            className="w-100 mb-16"
                            //label={t('Risk Population')}
                            //onChange={this.handleChange}
                            type="text"
                            name="content"
                            value={"diagnosisDate: "+ new Date(obj?.diagnosisDate).toLocaleDateString()+", diagnosisResult:"+ obj?.diagnosisResult}
                            validators={["required"]}
                            errorMessages={["this field is required"]}
                            color="secondary"
                          />
                        {
                          obj?.prevent?.map((pObj, index) => {
                            return(
                              <TextValidator
                              className="w-100 mb-16"
                              label={t('Prevent ')+(index+1)}
                              //onChange={this.handleChange}
                              type="text"
                              name="prevent"
                              value={"startDate: "+new Date(pObj?.startDate).toLocaleDateString()+"endDate: "+ new Date(pObj?.endDate).toLocaleDateString()+", isComplete: "+pObj?.isComplete+", placeProvided: "+pObj?.placeProvided?.name+", treatmentId: "+pObj?.treatmentId}
                              validators={["required"]}
                              errorMessages={["this field is required"]}
                              color="secondary"
                              />
                            )
                          })
                        }
                        {
                          obj?.treatment?.map((tObj, index) => {
                            return(
                              <TextValidator
                              className="w-100 mb-16"
                              label={t('Treatment ')+(index+1)}
                              //onChange={this.handleChange}
                              type="text"
                              name="tratement"
                              value={"startDate: "+ new Date(tObj?.startDate).toLocaleDateString()+", endDate: "+new Date(tObj?.endDate).toLocaleDateString()+", isComplete: "+tObj?.isComplete+", placeProvided: "+ tObj?.placeProvided?.name+", treatmentId: "+tObj?.treatmentId}
                              validators={["required"]}
                              errorMessages={["this field is required"]}
                              color="secondary"
                              />
                            )
                          })
                        }
                        </Grid>
                        )
                      })
                    }
                  </Grid>
                  <Grid item xs={12} sm={12} md={12}>
                  <label style={{color:"blue"}}>Viral Load List:</label>
                  {
                      patientDto?.viralLoadList?.map((obj, index) => {
                        return(
                          <Grid item xs={12} sm={12} md={12}>
                            <TextValidator
                            className="w-100 mb-16"
                            label={t('CD4 ')+(index+1)}
                            //onChange={this.handleChange}
                            type="text"
                            name="content"
                            value={"specimenCollectionDate: "+new Date(obj?.specimenCollectionDate).toLocaleDateString()+", specimenCollectionPlace: "+obj?.specimenCollectionPlace+", testPerformanceDate:"+new Date(obj?.testPerformanceDate).toLocaleDateString()+", testReason: code-"+obj?.testReason?.code+", display-"+obj?.testReason?.display+", valueNumber"+ obj?.valueNumber+", testResultOther: "+ obj?.testResultOther}
                            validators={["required"]}
                            errorMessages={["this field is required"]}
                            color="secondary"
                          />
                        </Grid>
                        )
                      })
                    }
                  </Grid>
                  </Grid>
                )
              }
              {
                !this.state.patientDto && (
                  <Grid item sm={12} xs={12}>
                    <label style={{color:"black", textAlign:"center", display:"block"}}>Patient null</label>
                  </Grid>
                )
              }
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

export default HivCaseReportEditorDialogNew;
