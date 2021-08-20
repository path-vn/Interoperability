import React, { Component } from "react";
import {
  Dialog,
  Button,
  Grid,
  FormControlLabel,
  DialogActions,
  IconButton,
  TextField,
  Checkbox
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
import { ValidatorForm, TextValidator, } from "react-material-ui-form-validator";
import { addOrganization , updateOrganization} from "./OrganizationService";
import { generateRandomId } from "utils";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

toast.configure({
  autoClose: 1000,
  draggable: false,
  limit: 3
});
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
class OrganizationDialog extends Component {
  state = {
    id: "",
    name: "",
    code: "",
    active: "",
    address: [],
    identifier: [],
    line: "",
    country: "",
    city: "",
    telecom: [],
    phone: "",
    textType: "",
    meta: {},
    resourceType: "Organization",
    type: [],
    isActive: false
  };

  handleChange = (event, source) => {
    event.persist();
    if (source === "switch") {
      this.setState({ isActive: event.target.checked });
      return;
    }
    if (source === "active") {
      this.setState({ active: event.target.checked })
    }
    this.setState({
      [event.target.name]: event.target.value
    });
  };

  handleFormSubmit = () => {
    let {
      id,
      name,
      active,
      address,
      identifier,
      telecom,
      meta,
      textType,
      line,
      city,
      country,
      phone,
      resourceType,
      type
    } = this.state;
    let {t} =this.props
    let data = {};
    // let addresss = {};
    let types = {}

    data.active = active
    data.name = name
    data.resourceType = resourceType
    data.id = id
    // if (line != "") {
    //   let lines = []
    //   lines.push(line)
    //   addresss.line = lines
    // }
    // if (city != "") {
    //   addresss.city = city
    // }
    // if (country != "") {
    //   addresss.country = country
    // }
    // address = []
    // address.push(addresss)
    data.address = address

    if (textType != "") {
      type = []
      types.text = textType
      type.push(types)
      data.type = type
    }

    if (phone != "") {
      let phones = {}
      telecom = []
      phones.system = "phone"
      phones.value = phone
      telecom.push(phones)
      data.telecom = telecom
    }
    if(id){
      updateOrganization(data).then(() => {
        this.props.handleOKEditClose();
        toast.success(t("general.updateSuccess"))
      });
    }else{
      addOrganization(data).then(() => {
        this.props.handleOKEditClose();
        toast.success(t("general.addSuccess"))
      });
    }
    

  };

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
  handleAddRow(rowId) {
    let list = [];
    let { address } = this.state;
    list = address;
    let n = {text: "",
    // coi: "",
    city: "",
    state: ""};
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
    if(address == null){
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
  componentWillMount() {
    let { open, handleClose, item } = this.props;

    this.GenBottle();
    if(item.telecom != null && item.telecom.length > 0){
      item.phone = item.telecom[0]?.value
    }
    if(item.type != null && item.type.length > 0){
      item.textType = item.type[0]?.text
    }
    this.setState(item);
  }

  render() {
    let { open, handleClose, handleOKEditClose, t, i18n } = this.props;
    let {
      id,
      name,
      code,
      active,
      address,
      identifier,
      meta,
      textType,
      line,
      city,
      country,
      phone,
      resourceType,
      type
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
            multiline
            rowsMax={4}
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
            multiline
            rowsMax={4}
          />
        ),
      },
      {
        title: t("District name"),
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
            multiline
            rowsMax={4}
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
            multiline
            rowsMax={4}
          />
        ),
      },
      
      {
        title: "",
        field: "custom",
        type: 'numeric',
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
      <Dialog open={open} maxWidth="md" fullWidth>
        <div className="p-24">
          <h4 className="mb-20">{t('Save Or Update')}</h4>
          <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
            <Grid className="mb-16" container spacing={1}>
              <Grid item md={6} sm={12} xs={12}>
                {/* <TextValidator
                  className="w-100 mb-16"
                  label={t('Id')}
                  onChange={this.handleChange}
                  type="text"
                  name="id"
                  value={id}
                  validators={["required"]}
                  errorMessages={["this field is required"]}
                /> */}
                <TextValidator
                  className="w-100 mb-16"
                  label={t('Name')}
                  onChange={this.handleChange}
                  type="text"
                  name="name"
                  value={name}
                  validators={["required"]}
                  errorMessages={["this field is required"]}
                  variant="outlined"
                  size="small"
                />
              </Grid>
              {/* <Grid item md={6} sm={12} xs={12}>
                <TextValidator
                  className="w-100 mb-16"
                  label={t('Resource Type')}
                  onChange={this.handleChange}
                  type="text"
                  name="resourceType"
                  value={resourceType}
                  variant="outlined"
                  size="small"
                  validators={["required"]}
                  errorMessages={["this field is required"]}
                />
              </Grid> */}
             <Grid item md={6} sm={12} xs={12}>
                <TextValidator
                  className="w-100 mb-16"
                  label={t('Phone')}
                  onChange={this.handleChange}
                  type="text"
                  name="phone"
                  value={phone}
                  variant="outlined"
                  size="small"

                />
              </Grid>
                {/*
              <Grid item md={6} sm={12} xs={12}>
                <TextValidator
                  className="w-100 mb-16"
                  label={t('Line')}
                  onChange={this.handleChange}
                  type="text"
                  name="line"
                  value={line}
                  variant="outlined"
                  size="small"
                />
              </Grid>
              <Grid item md={6} sm={12} xs={12}>
                <TextValidator
                  className="w-100 mb-16"
                  label={t('City')}
                  onChange={this.handleChange}
                  type="text"
                  name="city"
                  value={city}
                  variant="outlined"
                  size="small"
                />
              </Grid>
              <Grid item md={6} sm={12} xs={12}>
                <TextValidator
                  className="w-100 mb-16"
                  label={t('Country')}
                  onChange={this.handleChange}
                  type="text"
                  name="country"
                  value={country}
                  variant="outlined"
                  size="small"
                />
              </Grid> */}
              <Grid item md={6} sm={12} xs={12}>
                <TextValidator
                  className="w-100 mb-16"
                  label={t('Type Organization')}
                  onChange={this.handleChange}
                  type="text"
                  name="textType"
                  value={textType}
                  variant="outlined"
                  size="small"
                />
              </Grid>
              <Grid item md={6} sm={12} xs={12}>
                <FormControlLabel
                  label={<span className="font">{t('active')}</span>}
                  control={<Checkbox checked={active}
                    onChange={(active) =>
                      this.handleChange(active, 'active')
                    }
                  />}

                />
              </Grid>
              <Grid item container sm={12} xs={12} md={12}>
                <fieldset className="mt-16" style={{ width: "100%", borderRadius: "10px" }}>
                <legend>{<span className="styleColor">{t("Address")}</span>}</legend>
                  <MaterialTable
                    data={this.state.address ? this.state.address :[]}
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
                style={{marginRight:'15px'}}
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

export default OrganizationDialog;
