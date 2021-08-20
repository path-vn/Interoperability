import React, { Component } from "react";
import {
  Dialog,
  Button,
  Grid,
  FormControlLabel,
  DialogActions,
  IconButton,
  TextField,
} from "@material-ui/core";
import {
  MuiPickersUtilsProvider,
  DateTimePicker,
  KeyboardTimePicker,
  KeyboardDatePicker,
} from "@material-ui/pickers";
import Autocomplete from "@material-ui/lab/Autocomplete";
import DateFnsUtils from "@date-io/date-fns";
import { withStyles } from "@material-ui/core/styles";
import Tooltip from "@material-ui/core/Tooltip";
import RemoveCircleOutlineIcon from "@material-ui/icons/RemoveCircleOutline";
import AddCircleOutlineIcon from "@material-ui/icons/AddCircleOutline";
import { useTranslation } from "react-i18next";
import MaterialTable, { MTableToolbar } from "material-table";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import { addPatient, updatePatient } from "./PatientService";
// import { getAllOrganization } from "../Organization/OrganizationService";
import TabPatientDialog from './TabPatientDialog';
import { generateRandomId } from "utils";
const LightTooltip = withStyles((theme) => ({
  tooltip: {
    backgroundColor: theme.palette.common.white,
    color: "rgba(0, 0, 0, 0.87)",
    boxShadow: theme.shadows[1],
    fontSize: 11,
    marginLeft: "-1.5em",
  },
}))(Tooltip);
function MaterialButton(props) {
  const { t, i18n } = useTranslation();
  const item = props.item;
  const colorIcon = props.colorIcon;
  const enableIcon = props.enableIcon;

  return (
    <div className="none_wrap">
      {
        <LightTooltip
          title={t("general.deleteRow")}
          placement="right-end"
          enterDelay={300}
          leaveDelay={200}
          disabled={enableIcon}
          PopperProps={{
            popperOptions: {
              modifiers: { offset: { enabled: true, offset: "10px, 0px" } },
            },
          }}
        >
          <IconButton size="small" onClick={() => props.onSelect(item, 0)}>
            <RemoveCircleOutlineIcon fontSize="small" color={colorIcon ? "disabled" : "error"} />
          </IconButton>
        </LightTooltip>
      }
      <LightTooltip
        title={t("general.addRow")}
        placement="right-end"
        enterDelay={300}
        leaveDelay={200}
        PopperProps={{
          popperOptions: {
            modifiers: { offset: { enabled: true, offset: "10px, 0px" } },
          },
        }}
      >
        <IconButton size="small" onClick={() => props.onSelect(item, 1)}>
          <AddCircleOutlineIcon fontSize="small" color="primary" />
        </IconButton>
      </LightTooltip>
    </div>
  );
}
class PatientDialog extends Component {
  state = {
    name: [],
    code: "",
    description: "",
    isActive: false,
    text: "",
    resourceType: "Patient",
  };

  listGender = [{ id: 1, value: "male" },
  { id: 2, value: "female" },
  { id: 3, value: "other" },
  { id: 4, value: "unknown" }
  ]
  listContactPoint = [{ id: 1, value: "phone" },
  { id: 2, value: "fax" },
  { id: 3, value: "email" },
  { id: 4, value: "pager" },
  { id: 5, value: "url" },
  { id: 6, value: "sms" },
  { id: 7, value: "other" },
  ]

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
  handleChangeDate = (date, name) => {
    this.setState({
      [name]: date
    });
  }
  handleFormSubmit = () => {
    let { id } = this.state;
    let { name, telecom } = this.state;
    name = []
    telecom = []

    let n = {}
    let t = {}
    n.text = this.state.text
    n.given = this.state.given
    n.family = this.state.family
    name.splice(name.length - 1, 0, n);
    t.value = this.state.contactpointDetails
    t.system = this.state.system
    telecom.splice(telecom.length - 1, 0, t);
    let patient = {}
    patient.name = name
    patient.gender = this.state.gender
    patient.id = this.state.id
    patient.birthDate = this.state.birthDate
    patient.resourceType = this.state.resourceType
    patient.address = this.state.address
    patient.telecom = telecom

    if (id != null) {
      updatePatient({ ...patient }, id).then(() => {
        this.props.handleOKEditClose();
      });
    } else {
      addPatient({ ...patient }).then(() => {
        this.props.handleOKEditClose();
      });
    }

    // checkCode(id, code).then((result) => {
    //   //Nếu trả về true là code đã được sử dụng
    //   if (result.data) {
    //     alert("Code đã được sử dụng");
    //   } else {
    //     //Nếu trả về false là code chưa sử dụng có thể dùng
    //     if (id) {
    //         updateEQAhealthOrgLevel({
    //         ...this.state
    //       }).then(() => {
    //         this.props.handleOKEditClose();
    //       });
    //     } else {
    //         addNewEQAhealthOrgLevel({
    //         ...this.state
    //       }).then(() => {
    //         this.props.handleOKEditClose();
    //       });
    //     }
    //   }
    // });
  };

  componentWillMount() {
    let { open, handleClose, item } = this.props;

    this.setState({ ...item }, () => {
      console.log(this.state.address)
      if (typeof this.state.address === "undefined" || this.state.address == null || this.state.address.length == 0) {
        // alert("abc")
        this.GenBottle();
      }
      if (this.state.name != null && this.state.name.length > 0) {
        let text = this.state.name[0].text
        let given = this.state.name[0].given
        let family = this.state.name[0].family
        this.setState({ text: text ? text : "", given: given ? given : [], family: family ? family : "", dataGiven: given ? given[0] : "" })

      }
      if (this.state.telecom != null && this.state.telecom.length > 0) {
        let contact = null;
        if (this.listContactPoint != null && this.listContactPoint.length > 0) {
          this.listContactPoint.forEach(e => {
            if (e.value == this.state.telecom[0].system) {
              contact = e;
            }
          })
        }
        let value = this.state.telecom[0].value
        let system = this.state.telecom[0].system
        this.setState({ system: system, contactpointDetails: value, contact: contact })

      }

      if (this.listGender != null && this.listGender.length > 0) {
        let dataGender = null;
        this.listGender.forEach(e => {
          if (e.value == this.state.gender) {
            dataGender = e;
          }
        })
        this.setState({ dataGender: dataGender })
      }
    });
    // var searchObject = {};
    // searchObject.text = this.state.keyword;
    // searchObject.pageIndex = this.state.page + 1;
    // searchObject.pageSize = this.state.rowsPerPage;
    // getAllOrganization().then(({ data }) => {
    //   let listData = data.entry
    //   let { listOrganization } = this.state
    //   if (listOrganization == null) {
    //     listOrganization = []
    //   }
    //   if (listData != null && listData.length > 0) {
    //     listData.forEach(e => {
    //       listOrganization.push(e.resource)
    //     })
    //   }
    //   this.setState({ listOrganization: listOrganization ? listOrganization : [] }, () => {
    //   })
    // }
    // );

  }
  GenBottle() {
    let { address } = this.state;
    let list = [];
    for (var i = 0; i < 2; i++) {
      list.push({
        text: "",
        city: "",
        state: "",
      });
    }
    address = list;
    this.setState({ address: address });
  }
  handleChangeNameData = (event, code) => {
    let { given } = this.state;
    given = []
    let n = {}
    let fullname = ""
    // name[code][dataName] = value;

    if (event.target.name == "dataGiven") {
      given.push(event.target.value)
      fullname = event.target.value + ' ' + (this.state.family ? this.state.family : '')
    }
    if (event.target.name == "family") {
      fullname = (this.state.dataGiven ? this.state.dataGiven : '') + ' ' + event.target.value
    }

    // n["text"]=fullname

    this.setState(
      {
        [event.target.name]: event.target.value, text: fullname, given: given
      },

    );
  };
  // handleChangeStartDate = (code, event) => {
  //   let { name } = this.state;
  //   if(name == null){
  //       name = []
  //   }
  //   name[code].start = event
  //   this.setState(
  //     {
  //       name: name,
  //     },
  //   );
  // };
  // handleChangeEndDate = (code, event) => {
  //   let { name } = this.state;
  //   if(name == null){
  //       name = []
  //   }
  //   name[code].end = event
  //   this.setState(
  //     {
  //       name: name,
  //     },
  //   );
  // };

  handleAddRow(rowId) {
    let list = [];
    let { address } = this.state;
    list = address;
    let n = {
      text: "",
      // coi: "",
      city: "",
      state: ""
    };
    list.splice(rowId + 1, 0, n);

    address = list;

    this.setState(
      {
        address: address,
      },
      () => {
        if (this.state.name.length > 1) {
          this.setState({ shouldDisableRemoveIcon: false, shouldChangeColorRemoveIcon: false });
        }
      }
    );
  }

  handleRemoveRow(rowId) {
    let list = [];
    let { address } = this.state;
    list = address;
    list.splice(rowId, 1);

    address = list;

    this.setState(
      {
        address: address,
      },
      () => {
        // console.log(this.state.item.sampleProperties.length == 1)
        if (this.state.address.length == 1) {
          this.setState({ shouldDisableRemoveIcon: true, shouldChangeColorRemoveIcon: true });
        }
      }
    );
  }
  handleChangeAddressNameData = (code, event) => {
    let { address } = this.state;
    if (address == null) {
      address = []
    }
    let value = event.target.value
    let dataName = event.target.name
    address[code][dataName] = value;
    this.setState(
      {
        address: address,
      },

    );
  };
  changeGenderSelected = (event, value) => {
    this.setState({ dataGender: value ? value : null, gender: value ? value.value : null }, () => {

    })
  }
  changeContactPointSelected = (event, value) => {
    this.setState({ contact: value ? value : null, system: value ? value.value : null }, () => {

    })
  }
  changeOrganizationSelected = (event, value) => {
    this.setState({ managingOrganization: value ? value : null }, () => {

    })
  }
  render() {
    let { open, handleClose, handleOKEditClose, t, i18n } = this.props;
    let {
      id,
      name,
      code,
      description, listOrganization
    } = this.state;

    let columnsAddress = [

      {
        title: t("City"),
        field: "city",
        align: "center",
        width: "30%",
        render: (rowData) => (
          <TextValidator
            value={rowData.city}
            // code={rowData.code}
            className="w-100 mt-10"
            name="city"
            onChange={(event) => this.handleChangeAddressNameData(rowData.tableData.id, event)}
            type="text"
            variant="outlined"
            size="small"
          />
        ),
      },
      {
        title: t("Country"),
        field: "country",
        align: "center",
        width: "30%",
        render: (rowData) => (
          <TextValidator
            value={rowData.country}
            // code={rowData.code}
            className="w-100 mt-10"
            name="country"
            onChange={(event) => this.handleChangeAddressNameData(rowData.tableData.id, event)}
            type="text"
            variant="outlined"
            size="small"
          />
        ),
      },
      {
        title: t("District Name"),
        field: "district",
        align: "center",
        width: "30%",
        render: (rowData) => (
          <TextValidator
            value={rowData.district}
            // code={rowData.code}
            className="w-100 mt-10"
            name="district"
            onChange={(event) => this.handleChangeAddressNameData(rowData.tableData.id, event)}
            type="text"
            variant="outlined"
            size="small"
          />
        ),
      },

      {
        title: t("Address"),
        field: "text",
        align: "center",
        width: "30%",
        render: (rowData) => (
          <TextValidator
            value={rowData.text}
            // code={rowData.code}
            className="w-100 mt-10"
            name="text"
            onChange={(event) => this.handleChangeAddressNameData(rowData.tableData.id, event)}
            type="text"
            variant="outlined"
            size="small"
          />
        ),
      },

      {
        title: "",
        field: "custom",
        align: "center",
        width: "50px",
        headerStyle: {
          padding: "0px",
          minWidth: "10px",
        },
        cellStyle: {
          padding: "0px",

        },
        render: (rowData) => (
          <MaterialButton
            item={rowData}
            colorIcon={this.state.shouldChangeColorRemoveIcon}
            enableIcon={this.state.shouldDisableRemoveIcon}
            onSelect={(rowData, method) => {
              if (method === 0) {
                this.handleRemoveRow(rowData.tableData.id);
              }
              if (method === 1) {
                this.handleAddRow(rowData.tableData.id);
              }
            }}
          />
        ),
      },
    ];
    return (
      <Dialog open={open} maxWidth="lg" fullWidth>
        <div className="p-24">
          <h4 className="mb-20">{this.props.check ? "Fhir Detail":"Elastic Search Detail"}</h4>
          <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
            <Grid className="mb-16" container spacing={2} md={12} sm={12} xs={12}>
              <Grid item md={3} sm={12} xs={12}>
                <TextValidator
                  value={this.state.family}
                  // code={rowData.code}
                  label={t('Family name')}
                  className="w-100 mt-16"
                  name="family"
                  onChange={this.handleChangeNameData}
                  type="text"
                  variant="outlined"
                  size="small"
                  validators={["required"]}
                  errorMessages={[t("general.required")]}
                />
              </Grid>
              <Grid item md={3} sm={12} xs={12}>
                <TextValidator
                  className="w-100 mt-16"
                  label={t('Given name')}
                  onChange={this.handleChangeNameData}
                  type="text"
                  name="dataGiven"
                  value={this.state.dataGiven}
                  variant="outlined"
                  size="small"
                  validators={["required"]}
                  errorMessages={[t("general.required")]}
                />

              </Grid>
              <Grid item md={3} sm={12} xs={12}>
                <TextValidator
                  value={this.state.text}
                  // code={rowData.code}
                  label={t('Full name')}
                  className="w-100 mt-16"
                  name="family"
                  disabled
                  // onChange={this.handleChangeNameData}
                  type="text"
                  variant="outlined"
                  size="small"
                />
              </Grid>
              <Grid item md={3} sm={12} xs={12}>
                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                  <KeyboardDatePicker
                    label={<span ><span style={{ color: "red" }}></span>{t('Birth date')}</span>}
                    className="w-100 mb-10"
                    disableToolbar
                    format="dd/MM/yyyy"
                    margin="normal"
                    id="date-picker-inline"
                    // label="Date picker inline"
                    name="birthDate"
                    value={this.state.birthDate ? this.state.birthDate : null}
                    //onChange={(birthDate) => this.handleChangeBirthDate()}
                    onChange={(birthDate) => this.handleChangeDate(birthDate, "birthDate")}
                    KeyboardButtonProps={{
                      'aria-label': 'change date',
                    }}
                    inputVariant="outlined"
                    // variant ="outlined"
                    size="small"
                    validators={['required', 'isTrueTime']}
                  />
                </MuiPickersUtilsProvider>
              </Grid>
              <Grid item md={3} sm={12} xs={12}>
                <Autocomplete
                  className="w-100"
                  disableClearable
                  id="combo-box"
                  options={
                    this.listGender ? this.listGender : []
                  }
                  value={
                    this.state.dataGender ? this.state.dataGender : null
                  }
                  renderInput={(params) => (
                    <TextField
                      {...params}
                      value={
                        this.state.dataGender ? this.state.dataGender : null
                      }
                      label={
                        <span>
                          <span style={{ color: "red" }}></span>
                          {t("Gender")}
                        </span>
                      }
                      variant="outlined"
                      size="small"
                    />
                  )}
                  getOptionLabel={(option) => option.value}
                  getOptionSelected={(option, value) =>
                    option.id === value.id
                  }
                  onChange={(event, value) => {
                    this.changeGenderSelected(event, value);
                  }}
                />
              </Grid>
              <Grid item md={3} sm={12} xs={12}>
                <Autocomplete
                  className="w-100"
                  disableClearable
                  id="combo-box"
                  options={
                    this.listContactPoint ? this.listContactPoint : []
                  }
                  value={
                    this.state.contact ? this.state.contact : null
                  }
                  renderInput={(params) => (
                    <TextField
                      {...params}
                      value={
                        this.state.contact ? this.state.contact : null
                      }
                      label={
                        <span>
                          <span style={{ color: "red" }}></span>
                          {t("Contact point")}
                        </span>
                      }
                      variant="outlined"
                      size="small"
                    />
                  )}
                  getOptionLabel={(option) => option.value}
                  getOptionSelected={(option, value) =>
                    option.id === value.id
                  }
                  onChange={(event, value) => {
                    this.changeContactPointSelected(event, value);
                  }}
                />
              </Grid>
              <Grid item md={3} sm={12} xs={12}>
                <TextValidator
                  value={this.state.contactpointDetails}
                  // code={rowData.code}
                  label={t('Contact point details')}
                  className="w-100 "
                  name="contactpointDetails"
                  onChange={this.handleChange}
                  type="text"
                  variant="outlined"
                  size="small"
                />
              </Grid>
              <Grid item md={3} sm={12} xs={12}>
                <Autocomplete
                  className="w-100"
                  disableClearable
                  id="combo-box"
                  options={
                    this.state.listOrganization ? this.state.listOrganization : []
                  }
                  value={
                    this.state.managingOrganization ? this.state.managingOrganization : null
                  }
                  renderInput={(params) => (
                    <TextField
                      {...params}
                      value={
                        this.state.managingOrganization ? this.state.managingOrganization : null
                      }
                      label={
                        <span>
                          <span style={{ color: "red" }}></span>
                          {t("Managing organization")}
                        </span>
                      }
                      variant="outlined"
                      size="small"
                    />
                  )}
                  getOptionLabel={(option) => option.name}
                  getOptionSelected={(option, value) =>
                    option.id === value.id
                  }
                  onChange={(event, value) => {
                    this.changeOrganizationSelected(event, value);
                  }}
                />
              </Grid>
              <Grid item container sm={12} xs={12} md={12}>
                <fieldset className="mt-16" style={{ width: "100%", borderRadius: "10px" }}>
                  <legend>{<span className="styleColor">{t("Address")}</span>}</legend>
                  <MaterialTable
                    data={this.state.address ? this.state.address : []}
                    columns={columnsAddress}
                    className="w-100"
                    options={{
                      toolbar: false,
                      selection: false,
                      actionsColumnIndex: -1,
                      paging: false,
                      search: false,
                      tableLayout: "fixed",
                      padding: "dense",
                      border: "none",
                      cellStyle: { border: "none" },
                      headerStyle: { border: "none", fontSize: "16px" },
                    }}
                    components={{
                      Toolbar: (props) => (
                        <div style={{ textHeader: "center" }}>
                          <MTableToolbar {...props} />
                        </div>
                      ),
                    }}
                    localization={{
                      body: {
                        emptyDataSourceMessage: `${t("general.emptyDataMessageTable")}`,
                      },
                    }}
                  />

                </fieldset>
              </Grid>
            </Grid>

            <DialogActions>
              <div className="flex flex-space-between flex-middle">
                <Button
                  variant="contained"
                  className="mr-12"
                  color="secondary"
                  onClick={() => this.props.handleClose()}
                >
                  {t('Cancel')}
                </Button>
                <Button
                  variant="contained"
                  color="primary"
                  type="submit"
                  style={{ marginRight: '15px' }}
                >
                  {t('Save')}
                </Button>

              </div>
            </DialogActions>
          </ValidatorForm>
        </div>
      </Dialog>
    );
  }
}

export default PatientDialog;
