import React, { Component } from "react";
import {
  Dialog,
  Button,
  Grid,
  DialogActions,
  IconButton,
  TextField,
  Icon,
  DialogTitle,
  DialogContent
} from "@material-ui/core";
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker,
} from "@material-ui/pickers";
import Autocomplete from "@material-ui/lab/Autocomplete";
import DateFnsUtils from "@date-io/date-fns";
import { withStyles } from "@material-ui/core/styles";
import Tooltip from "@material-ui/core/Tooltip";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import { addPatient, updatePatient } from "./PatientService";
import TabPatientDialog from './TabPatientDialog';
import PatientJson from './PatientJson';
import Draggable from 'react-draggable';
import Paper from '@material-ui/core/Paper';
import SubjectIcon from '@material-ui/icons/Subject';
import BlockIcon from '@material-ui/icons/Block';
import moment from 'moment';

function PaperComponent(props) {
  return (
    <Draggable handle="#draggable-dialog-title" cancel={'[class*="MuiDialogContent-root"]'}>
      <Paper {...props} />
    </Draggable>
  );
}

const LightTooltip = withStyles((theme) => ({
  tooltip: {
    backgroundColor: theme.palette.common.white,
    color: "rgba(0, 0, 0, 0.87)",
    boxShadow: theme.shadows[1],
    fontSize: 11,
    marginLeft: "-1.5em",
  },
}))(Tooltip);
class PatientDialog extends Component {
  state = {
    name: [],
    code: "",
    death: {},
    description: "",
    isActive: false,
    text: "",
    resourceType: "Patient",
    shouldOpenJson: false
  };

  listGender = [{ code: 'transgender-to-female', display: "Transgender female" },
  { code: 'transgender-to-male', display: "Transgender male" },
  { code: 'non-binary', display: "non-binary" },
  { code: 'male', display: "male" },
  { code: 'female', display: "female" },
  { code: 'other', display: "Other" },
  { code: 'not-disclosed', display: "Does not wish to disclose" },
  ]


  handleChange = (event, source) => {
    event.persist();
    if (source === "switch") {
      this.setState({ isActive: event.target.checked });
      return;
    }
    if (source === "causeOfDeath") {
      let { death } = this.state;
      death['causeOfDeath'] = event.target.value;

      this.setState({ death: death })
    }

    this.setState({
      [event.target.name]: event.target.value
    });
  };

  handleChangeSystemCode = (event, source) => {
    if (source === "ethnicity") {
      let ethnicity = {};
      ethnicity['code'] = event.target.value;
      ethnicity['name'] = event.target.value;
      // ethnicity['display'] = event.target.value;
      // ethnicity['definition'] = event.target.value;
      this.setState({ ethnicity: ethnicity })
    } else if (source === "occupation") {
      let systemCode = {}
      systemCode['code'] = event.target.value;
      systemCode['display'] = event.target.value;
      systemCode['definition'] = event.target.value;
      this.setState({ occupation: systemCode })
    } else if (source === "treatmentStatus") {
      let systemCode = {}
      systemCode['code'] = event.target.value;
      systemCode['display'] = event.target.value;
      systemCode['definition'] = event.target.value;
      this.setState({ treatmentStatus: systemCode })
    }
  }
  handleChangeDate = (date, name) => {
    let { diagnosis, death } = this.state;
    if (diagnosis == null) {
      diagnosis = {}
    }
    if (name === "specimenCollectionDate") {
      diagnosis["specimenCollectionDate"] = date
      this.setState({ diagnosis: diagnosis })
      return
    }
    if (name === "OnAidsDiagnosis") {
      diagnosis["OnAidsDiagnosis"] = date
      this.setState({ diagnosis: diagnosis })
      return
    }
    if (name === "dateOfDeath") {
      death["dateOfDeath"] = date;
      this.setState({ death: death })
      return
    }
    if (name === "birthDate") {
      try {
        let dateNow = new Date();
        let old = dateNow.getFullYear() - date.getFullYear();
        this.setState({ birthDate: date, old: old })
        return
      } catch {

      }
    }

    this.setState({
      [name]: date
    });
  }
  handleFormSubmit = () => {
    console.log(this.state);
    let { id } = this.state;
    let { name, telecom } = this.state;

    let patient = {}

    patient.firstName = this.state.firstName;
    patient.lastName = this.state.lastName
    patient.name = this.state.name;
    patient.birthDate = this.state.birthDate;
    patient.ethnicity = this.state.ethnicity;
    patient.artID = this.state.artID;
    patient.nationalID = this.state.nationalID;
    patient.healthInsuranceNumber = this.state.healthInsuranceNumber;
    patient.passportNumber = this.state.passportNumber;
    patient.currentAddress = this.state.currentAddress;
    patient.registeredAddress = this.state.registeredAddress;
    patient.occupation = this.state.occupation;
    patient.riskPopulations = this.state.item.riskPopulations;
    patient.riskBehaviors = this.state.item.riskBehaviors;
    patient.transmissionRoutes = this.state.item.transmissionRoutes;
    patient.regimens = this.state.item.regimens;
    patient.cd4List = this.state.item.cd4List;
    patient.viralLoadList = this.state.item.viralLoadList;
    patient.listOfHbv = this.state.item.listOfHbv;
    patient.listOfHcv = this.state.item.listOfHcv;
    patient.listOfTb = this.state.item.listOfTb;
    patient.gender = this.state.gender;
    patient.diagnosis = this.state.diagnosis;
    patient.rapidRecencyTest = this.state.rapidRecencyTest;
    patient.viralLoadRecencyTest = this.state.viralLoadRecencyTest;
    patient.cd4BeforeART = this.state.cd4BeforeART;
    patient.treatmentStatus = this.state.treatmentStatus;
    patient.death = this.state.death;
    patient.text = this.state.text;

    if (id != null) {
      updatePatient({ ...patient }, id).then(() => {
        this.props.handleOKEditClose();
      });
    } else {
      addPatient({ ...patient }).then(() => {
        this.props.handleOKEditClose();
      });
    }
  };


  componentWillMount() {
    let { item } = this.props;
    let dateNow = new Date();
    let birthDate = new Date(item.birthDate);
    try {
      item.old = dateNow.getFullYear() - birthDate.getFullYear();
    } catch {

    }

    this.setState({ ...item, item: item }, () => { });
  }

  handleChangeNameData = (event) => {
    let { given } = this.state;
    given = []
    let fullName = ""
    if (event.target.name == "firstName") {
      given.push(event.target.value)
      fullName = event.target.value + ' ' + (this.state.lastName ? this.state.lastName : '')
    }
    if (event.target.name == "lastName") {
      fullName = (this.state.firstName ? this.state.firstName : '') + ' ' + event.target.value
    }

    this.setState({ [event.target.name]: event.target.value, name: fullName, given: given });
  };

  handleChangeDiagnosis = (event, source) => {
    let { diagnosis } = this.state;
    if (diagnosis == null) {
      diagnosis = {}
    }
    const name = event.target.name;
    const value = event.target.value;

    if (source === "confirmatoryLab") {
      let confirmatoryLab = {}
      confirmatoryLab['code'] = event.target.value;
      confirmatoryLab['name'] = event.target.value;
      diagnosis['confirmatoryLab'] = confirmatoryLab;
      this.setState({ diagnosis: diagnosis });
    } else if (source === "riskPopulation") {
      let systemCode = {}
      systemCode['code'] = event.target.value;
      systemCode['display'] = event.target.value;
      systemCode['definition'] = event.target.value;
      diagnosis['riskPopulation'] = systemCode
      this.setState({ diagnosis: diagnosis });
    } else if (source === "riskBehaviour") {
      let systemCode = {}
      systemCode['code'] = event.target.value;
      systemCode['display'] = event.target.value;
      systemCode['definition'] = event.target.value;
      diagnosis['riskBehavior'] = systemCode
      this.setState({ diagnosis: diagnosis });
    } else if (source === "transmissionRoute") {
      let systemCode = {}
      systemCode['code'] = event.target.value;
      systemCode['display'] = event.target.value;
      systemCode['definition'] = event.target.value;
      diagnosis['transmissionRoute'] = systemCode
      this.setState({ diagnosis: diagnosis });
    }
    else {
      diagnosis[name] = value;
      this.setState({ diagnosis: diagnosis });
    }
  }

  handleChangeCurrentAddress = (event) => {
    let { currentAddress } = this.state
    if (currentAddress == null) {
      currentAddress = {}
    }
    const name = event.target.name;
    const value = event.target.value;
    currentAddress[name] = value;
    this.setState({ currentAddress: currentAddress });
  };
  handleChangeRegisteredAddress = (event) => {
    let { registeredAddress } = this.state
    if (registeredAddress == null) {
      registeredAddress = {}
    }
    const name = event.target.name;
    const value = event.target.value
    registeredAddress[name] = value;
    this.setState({ registeredAddress: registeredAddress });
  };
  changeGenderSelected = (event, value) => {
    this.setState({ gender: value ? value : null }, () => { })
  }
  handleDialogClose = () => {
    this.setState({
      shouldOpenJson: false,
    }
    );
  };
  handleOKEditClose = () => {
    this.setState({
      shouldOpenJson: false,
    });
  };
  render() {
    let { open, t, i18n } = this.props;
    let {
      shouldOpenJson
    } = this.state;
    return (
      <Dialog
        className="dialog-container"
        open={open}
        PaperComponent={PaperComponent}
        fullWidth
        maxWidth="lg"
      >
        <DialogTitle
          className="dialog-header bgc-primary-d1"
          style={{ cursor: 'move' }}
          id="draggable-dialog-title"
        >
          <span className="mb-20 text-white" > {(this.props.check ? t('patient.fhir') : t('patient.es'))}</span>
          <IconButton style={{ position: "absolute", right: "10px", top: "10px" }} onClick={() => this.props.handleClose()}>
            <Icon color="disabled" title={t('general.button.close')}>close</Icon>
          </IconButton>
        </DialogTitle>
        {shouldOpenJson && (
          <PatientJson t={t} i18n={i18n}
            handleClose={this.handleDialogClose}
            open={shouldOpenJson}
            handleOKEditClose={this.handleOKEditClose}
            item={this.props.item}
          />
        )}
        <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
          <div className="dialog-body">
            <DialogContent className="o-hidden">
              <Grid className="" container spacing={2}>
                <Grid item container md={7} sm={12} xs={12}>
                  <fieldset>
                    <legend>{t('patient.person_infomation')}</legend>
                    <Grid item container sm={12} spacing={2}>
                      {this.state.urlID && (<Grid item sm={4} xs={12}>
                        <TextValidator
                          value={this.state.urlID}
                          label={t('patient.patient_code')}
                          className="w-100 mt-16"
                          name="urlID"
                          disabled
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>)}
                      <Grid item sm={4} xs={12}>
                        <TextValidator
                          value={this.state.lastUpdated ? (this.state.lastUpdated.display ? this.state.lastUpdated.display : "...") : "..."}
                          label={t('patient.last_updated')}
                          className="w-100 mt-16"
                          name="urlID"
                          disabled
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item sm={4} xs={12}>
                        <Button
                          fullWidth
                          startIcon={<SubjectIcon />}
                          className="mt-16 mr-16 d-inline-flex btn btn-danger"
                          variant="contained"
                          onClick={() => {
                            this.setState({ shouldOpenJson: true })
                          }
                          }
                        >
                          {t('Patient Json')}
                        </Button>
                      </Grid>
                      <Grid item md={4} sm={12} xs={12}>
                        <TextValidator
                          value={this.state.firstName ? this.state.firstName : '...'}
                          // code={rowData.code}
                          label={t('patient.first_name')}
                          disabled
                          className="w-100"
                          name="firstName"
                          onChange={this.handleChangeNameData}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={4} sm={12} xs={12}>
                        <TextValidator
                          className="w-100"
                          label={t('patient.last_name')}
                          onChange={this.handleChangeNameData}
                          disabled
                          type="text"
                          name="lastName"
                          value={this.state.lastName ? this.state.lastName : '...'}
                          variant="outlined"
                          size="small"
                        />

                      </Grid>
                      <Grid item md={4} sm={12} xs={12}>
                        <TextValidator
                          value={this.state.name ? this.state.name : '...'}
                          // code={rowData.code}
                          label={t('patient.full_name')}
                          className="w-100"
                          name="name"
                          disabled
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={4} sm={12} xs={12}>
                        <TextValidator
                          value={this.state.ethnicity ? this.state.ethnicity?.name : '...'}
                          label={t('patient.ethnicity')}
                          disabled
                          className="w-100"
                          name="ethnicity"
                          onChange={event => this.handleChangeSystemCode(event, "ethnicity")}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={4} sm={12} xs={12}>
                        <Autocomplete
                          className="w-100"
                          disableClearable
                          disabled
                          id="combo-box"
                          options={this.listGender ? this.listGender : []}
                          value={this.state.gender ? this.state.gender : null}
                          renderInput={(params) => (
                            <TextField
                              {...params}
                              value={this.state.gender ? this.state.gender : null}
                              label={<span><span style={{ color: "red" }}></span> {t("patient.gender")}</span>}
                              variant="outlined"
                              size="small"
                            />
                          )}
                          getOptionLabel={(option) => option.display}
                          getOptionSelected={(option, value) => option.code === value.code}
                          onChange={(event, value) => { this.changeGenderSelected(event, value); }}
                        />

                      </Grid>
                      <Grid item md={4} sm={12} xs={12}>
                        <TextValidator
                          value={this.state.passportNumber ? this.state.passportNumber : '...'}
                          label={t('patient.passport_number')}
                          className="w-100"
                          name="passportNumber"
                          disabled
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item lg={4} md={4} sm={12} xs={12}>
                        <TextValidator
                          value={this.state.old ? this.state.old : '...'}
                          label={t('patient.age')}
                          className="w-100"
                          name="age"
                          disabled
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={4} sm={12} xs={12}>
                        <TextValidator
                          value={this.state.birthDate ? moment(this.state.birthDate).format('DD/MM/YYYY') : "..."}
                          label={t('patient.birth_date')}
                          className="w-100"
                          name="urlID"
                          disabled
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={4} sm={12} xs={12}>
                        <TextValidator
                          value={this.state.nationalID ? this.state.nationalID : "..."}
                          label={t('patient.national_id')}
                          className="w-100"
                          name="urlID"
                          disabled
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={4} sm={12} xs={12}>
                        <TextValidator
                          value={this.state.artID ? this.state.artID : "..."}
                          label={t('patient.art_id')}
                          className="w-100 "
                          disabled
                          name="artID"
                          onChange={this.handleChange}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={4} sm={12} xs={12}>
                        <TextValidator
                          value={this.state.healthInsuranceNumber ? this.state.healthInsuranceNumber : "..."}
                          label={t('patient.health_insurance_id')}
                          disabled
                          className="w-100 "
                          name="healthInsuranceNumber"
                          onChange={this.handleChange}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={4} sm={12} xs={12}>
                        <TextValidator
                          value={this.state.occupation ? (this.state.occupation.display ? this.state.occupation.display : "...") : "..."}
                          label={t('patient.occupation')}
                          className="w-100 "
                          disabled
                          name="occupation"
                          onChange={(event) => this.handleChangeSystemCode(event, "occupation")}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={6} sm={12} xs={12}>
                        <TextValidator
                          value={this.state.diagnosis ? (this.state.diagnosis.riskPopulation ? this.state.diagnosis.riskPopulation.display : "...") : "..."}
                          label={t('patient.risk_population')}
                          className="w-100 "
                          disabled
                          name="riskPopulation"
                          onChange={(event) => this.handleChangeDiagnosis(event, "riskPopulation")}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={6} sm={12} xs={12}>
                        <TextValidator
                          value={this.state.diagnosis ? (this.state.diagnosis.riskBehavior ? this.state.diagnosis.riskBehavior.display : "...") : "..."}
                          label={t('patient.risk_behavior')}
                          className="w-100 "
                          disabled
                          name="riskBehaviour"
                          onChange={(event) => this.handleChangeDiagnosis(event, "riskBehaviour")}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={6} sm={12} xs={12}>
                        <TextValidator
                          value={this.state.diagnosis ? (this.state.diagnosis.transmissionRoute ? this.state.diagnosis.transmissionRoute.display : "...") : "..."}
                          label={t('patient.transmission_route')}
                          className="w-100 "
                          disabled
                          name="transmissionRoutes"
                          onChange={(event) => this.handleChangeDiagnosis(event, "transmissionRoute")}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      {/* <Grid item md={6} sm={12} xs={12}>
                        <TextValidator
                          value={this.state.currentResidenceStatus ? this.state.currentResidenceStatus : "..."}
                          label={t('patient.current_resident_status')}
                          className="w-100 "
                          disabled
                          name="currentResidenceStatus"
                          onChange={this.handleChange}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid> */}
                    </Grid>

                  </fieldset>
                </Grid>
                <Grid item container md={5} sm={12} xs={12}>
                  <fieldset>
                    <legend>{t('patient.current_residence')}</legend>
                    <Grid item container sm={12} spacing={2}>
                      <Grid item md={5} sm={12}>
                        <TextValidator
                          value={this.state.currentAddress ? (this.state.currentAddress.cityCode ? this.state.currentAddress.cityCode : '...') : '...'}
                          label={t('patient.city_code')}
                          disabled
                          className="w-100 mt-16"
                          name="cityCode"
                          onChange={this.handleChangeCurrentAddress}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={7} sm={12}>
                        <TextValidator
                          value={this.state.currentAddress ? (this.state.currentAddress.cityName ? this.state.currentAddress.cityName : '...') : '...'}
                          label={t('patient.city')}
                          className="w-100 mt-16"
                          disabled
                          name="cityName"
                          onChange={this.handleChangeCurrentAddress}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>

                      <Grid item md={5} sm={12}>
                        <TextValidator
                          value={this.state.currentAddress ? (this.state.currentAddress.districtCode ? this.state.currentAddress.districtCode : '...') : '...'}
                          label={t('patient.district_code')}
                          disabled
                          className="w-100 "
                          name="districtCode"
                          onChange={this.handleChangeCurrentAddress}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={7} sm={12}>
                        <TextValidator
                          value={this.state.currentAddress ? (this.state.currentAddress.districtName ? this.state.currentAddress.districtName : '...') : '...'}
                          label={t('patient.district')}
                          disabled
                          className="w-100 "
                          name="districtName"
                          onChange={this.handleChangeCurrentAddress}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>

                      <Grid item md={5} sm={12}>
                        <TextValidator
                          value={this.state.currentAddress ? (this.state.currentAddress.communeCode ? this.state.currentAddress.communeCode : '...') : '...'}
                          label={t('patient.commune_code')}
                          disabled
                          className="w-100 "
                          name="communeCode"
                          onChange={this.handleChangeCurrentAddress}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={7} sm={12}>
                        <TextValidator
                          value={this.state.currentAddress ? (this.state.currentAddress.communeName ? this.state.currentAddress.communeName : '...') : '...'}

                          label={t('patient.commune')}
                          className="w-100 "
                          disabled
                          name="communeName"
                          onChange={this.handleChangeCurrentAddress}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={12} sm={12}>
                        <TextValidator
                          value={this.state.currentAddress ? (this.state.currentAddress.line ? this.state.currentAddress.line : '...') : '...'}
                          label={t('patient.line')}
                          className="w-100 "
                          name="line"
                          disabled
                          multiLine
                          rows={2}
                          rowsMax={4}
                          onChange={this.handleChangeCurrentAddress}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={12} sm={12}>
                        <TextValidator
                          value={this.state.currentAddress ? (this.state.currentAddress.text ? this.state.currentAddress.text : '...') : '...'}
                          label={t('patient.address')}
                          className="w-100 "
                          name="text"
                          disabled
                          multiLine
                          rows={2}
                          rowsMax={4}
                          onChange={this.handleChangeCurrentAddress}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                    </Grid>
                  </fieldset>

                </Grid>
              </Grid>
              <Grid className="mt-16" container spacing={2}>
                <Grid item container md={7} sm={12} xs={12} >
                  <fieldset className="">
                    <legend>{t('patient.pathological')}</legend>
                    <Grid item container sm={12} spacing={2}>
                      {/* <Grid item md={6} sm={6} xs={6}>
                        <TextValidator
                          className="w-100 mt-16"
                          label={t('patient.pathological')}
                          onChange={this.handleChange}
                          type="text"
                          disabled
                          name="pathological"
                          value={this.state.pathological ? this.state.pathological : '...'}
                          variant="outlined"
                          size="small"
                        />
                      </Grid> */}
                      <Grid item md={6} sm={6} xs={6}>
                        <TextValidator
                          value={this.state.diagnosis ? (this.state.diagnosis.specimenCollectionDate ? moment(this.state.diagnosis.specimenCollectionDate).format('DD/MM/YYYY') : "...") : "..."}
                          label={t('patient.specimen_date')}
                          className="w-100 mt-16"
                          name="specimenCollectionDate"
                          disabled
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={6} sm={6} xs={6}>
                        <TextValidator
                          value={this.state.diagnosis ? (this.state.diagnosis.confirmatoryDate ? moment(this.state.diagnosis.confirmatoryDate).format('DD/MM/YYYY') : "...") : "..."}
                          label={t('patient.confirmation_date')}
                          className="w-100"
                          name="diagnosis"
                          disabled
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={6} sm={6} xs={6}>
                        <TextValidator
                          className="w-100"
                          disabled
                          label={t('patient.confirmation_lab')}
                          onChange={(event) => this.handleChangeDiagnosis(event, "confirmatoryLab")}
                          type="text"
                          name="confirmatoryLab"
                          value={this.state.diagnosis ? this.state.diagnosis.confirmatoryLab ? this.state.diagnosis.confirmatoryLab?.name : '...' : '...'}
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={6} sm={6} xs={6}>
                        <TextValidator
                          className="w-100"
                          label={t('patient.specimen_place')}
                          onChange={this.handleChangeDiagnosis}
                          disabled
                          type="text"
                          name="placeOfSpecimenCollection"
                          value={this.state.diagnosis ? (this.state.diagnosis.placeOfSpecimenCollection ? this.state.diagnosis.placeOfSpecimenCollection : "...") : "..."}
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      {/* <Grid item md={6} sm={6} xs={6}>
                        <TextValidator
                          className="w-100"
                          label={t('patient.treatment_status')}
                          disabled
                          onChange={event => this.handleChangeSystemCode(event, "treatmentStatus")}
                          type="text"
                          name="treatmentStatus"
                          value={this.state?.treatmentStatus ? this.state?.treatmentStatus.display ? this.state?.treatmentStatus.display : "..." : "..."}
                          variant="outlined"
                          size="small"
                        />
                      </Grid> */}
                      {/* <Grid item md={6} sm={6} xs={6}>
                        <TextValidator
                          value={this.state.diagnosis ? (this.state.diagnosis.OnAidsDiagnosis ? moment(this.state.diagnosis.OnAidsDiagnosis).format('DD/MM/YYYY') : "...") : "..."}
                          label={t('patient.on_aids_diagnosis')}
                          className="w-100"
                          name="OnAidsDiagnosis"
                          disabled
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid> */}
                      {/* <Grid item md={6} sm={6} xs={6}>
                        <TextValidator
                          className="w-100"
                          label={t('patient.aids_diagnosis_place')}
                          onChange={this.handleChangeDiagnosis}
                          disabled
                          type="text"
                          name="AidsDiagnosticPlace"
                          value={this.state.diagnosis ? (this.state.diagnosis.AidsDiagnosticPlace ?this.state.diagnosis.AidsDiagnosticPlace:"..." ):"..."}
                          variant="outlined"
                          size="small"
                        />
                      </Grid> */}
                      <Grid item md={6} sm={6} xs={6}>
                        <TextValidator
                          value={this.state.death ? (this.state.death.dateOfDeath ? moment(this.state.death.dateOfDeath).format('DD/MM/YYYY') : "...") : "..."}
                          label={t('patient.deceased_date')}
                          className="w-100"
                          name="dateOfDeath"
                          disabled
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={6} sm={6} xs={6}>
                        <TextValidator
                          className="w-100"
                          label={t('patient.death_cause')}
                          onChange={event => this.handleChange(event, "causeOfDeath")}
                          type="text"
                          disabled
                          name="causeOfDeath"
                          value={this.state.death ? (this.state.death.causeOfDeath ? this.state.death.causeOfDeath : '...') : '...'}
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                    </Grid>
                  </fieldset>
                </Grid>
                <Grid item container md={5} sm={12} xs={12}>
                  <fieldset>
                    <legend>{t('patient.permanent_residence')}</legend>
                    <Grid item container sm={12} spacing={2}>
                      <Grid item md={5} sm={12}>
                        <TextValidator
                          value={this.state.registeredAddress ? (this.state.registeredAddress.cityCode ? this.state.registeredAddress.cityCode : '...') : '...'}
                          label={t('patient.city_code')}
                          disabled
                          className="w-100 mt-16"
                          name="cityCode"
                          onChange={this.handleChangeRegisteredAddress}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={7} sm={12}>
                        <TextValidator
                          value={this.state.registeredAddress ? (this.state.registeredAddress.cityName ? this.state.registeredAddress.cityName : '...') : '...'}
                          label={t('patient.city')}
                          className="w-100 mt-16"
                          disabled
                          name="cityName"
                          onChange={this.handleChangeRegisteredAddress}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>

                      <Grid item md={5} sm={12}>
                        <TextValidator
                          value={this.state.registeredAddress ? (this.state.registeredAddress.districtCode ? this.state.registeredAddress.districtCode : '...') : '...'}
                          label={t('patient.district_code')}
                          className="w-100 "
                          disabled
                          name="districtCode"
                          onChange={this.handleChangeRegisteredAddress}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={7} sm={12}>
                        <TextValidator
                          value={this.state.registeredAddress ? (this.state.registeredAddress.districtName ? this.state.registeredAddress.districtName : '...') : '...'}
                          label={t('patient.district')}
                          className="w-100 "
                          disabled
                          name="districtName"
                          onChange={this.handleChangeRegisteredAddress}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>

                      <Grid item md={5} sm={12}>
                        <TextValidator
                          value={this.state.registeredAddress ? (this.state.registeredAddress.communeCode ? this.state.registeredAddress.communeCode : '...') : '...'}
                          label={t('patient.commune_code')}
                          className="w-100 "
                          disabled
                          name="communeCode"
                          onChange={this.handleChangeRegisteredAddress}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={7} sm={12}>
                        <TextValidator
                          value={this.state.registeredAddress ? (this.state.registeredAddress.communeName ? this.state.registeredAddress.communeName : '...') : '...'}
                          label={t('patient.commune')}
                          className="w-100 "
                          disabled
                          name="communeName"
                          onChange={this.handleChangeRegisteredAddress}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={12} sm={12}>
                        <TextValidator
                          value={this.state.registeredAddress ? (this.state.registeredAddress.line ? this.state.registeredAddress.line : '...') : '...'}
                          label={t('patient.line')}
                          disabled
                          className="w-100 "
                          name="line"
                          multiLine
                          rows={2}
                          rowsMax={4}
                          onChange={this.handleChangeRegisteredAddress}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                      <Grid item md={12} sm={12}>
                        <TextValidator
                          value={this.state.registeredAddress ? (this.state.registeredAddress.text ? this.state.registeredAddress.text : '...') : '...'}
                          label={t('patient.address')}
                          disabled
                          className="w-100 "
                          name="text"
                          multiLine
                          rows={2}
                          rowsMax={4}
                          onChange={this.handleChangeRegisteredAddress}
                          type="text"
                          variant="outlined"
                          size="small"
                        />
                      </Grid>
                    </Grid>
                  </fieldset>
                </Grid>
              </Grid>
              <Grid container spacing={2}>
                <Grid item lg={12} md={12} sm={12} xs={12}>
                  <TextValidator
                    className="w-100 mt-16"
                    label={t('patient.note')}
                    disabled
                    onChange={this.handleChange}
                    type="text"
                    name="text"
                    value={this.state.text ? this.state.text : '...'}
                    variant="outlined"
                    size="small"
                  />
                </Grid>
              </Grid>
              <Grid container spacing={2}>
                <Grid item container sm={12} xs={12} md={12}>
                  <TabPatientDialog t={t} item={this.state.item} />
                </Grid>
              </Grid>
            </DialogContent>
          </div>
          <div className="dialog-footer">
            <DialogActions className="p-0">
              <div className="flex flex-space-between flex-middle">
                <Button
                  variant="contained"
                  startIcon={<BlockIcon />}
                  className="mr-12 d-inline-flex btn btn-secondary"
                  color="secondary"
                  onClick={() => this.props.handleClose()}
                >
                  {t("general.button.cancel")}
                </Button>
              </div>
            </DialogActions>
          </div>
        </ValidatorForm>
      </Dialog>
    );
  }
}

export default PatientDialog;
