import React, { Component } from "react";
import {
  Dialog,
  Button,
  Grid,
  DialogActions,
  FormControl,
  Paper,
  DialogTitle,
  DialogContent,
} from "@material-ui/core";
import Draggable from "react-draggable";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import { checkCode, updateItem, saveItem, administrativeUnitSearchByDto } from "./IsolationCenterService";
import AsynchronousAutocomplete from '../utilities/AsynchronousAutocomplete'
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker,
} from "@material-ui/pickers";
import DateFnsUtils from '@date-io/date-fns';

toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3,
});

function PaperComponent(props) {
  return (
    <Draggable
      handle="#draggable-dialog-title"
      cancel={'[class*="MuiDialogContent-root"]'}
    >
      <Paper {...props} />
    </Draggable>
  );
}

class EpidemiologicalFactorsDialog extends Component {
  state = {
    id: "",
    type: "",
    shouldOpenNotificationPopup: false,
    Notification: "",
  };

  handleDialogClose = () => {
    this.setState({ shouldOpenNotificationPopup: false });
  };

  handleChange = (event, source) => {
    event.persist();
    if (source === "switch") {
      this.setState({ isActive: event.target.checked });
      return;
    }
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  handleFormSubmit = () => {
    let { id } = this.state;
    let { code } = this.state;
    var { t } = this.props;
    checkCode(id, code).then((result) => {
      //Nếu trả về true là code đã được sử dụng
      if (result.data) {
        toast.warning(t("general.dupli_code"));
        // alert("Code đã được sử dụng");
      } else {
        //Nếu trả về false là code chưa sử dụng có thể dùng
        if (id) {
          updateItem({
            ...this.state,
          }).then(() => {
            toast.success(t("general.updateSuccess"));
            this.props.handleOKEditClose();
          });
        } else {
          saveItem({
            ...this.state,
          }).then(() => {
            toast.success(t("general.addSuccess"));
            this.props.handleOKEditClose();
          });
        }
      }
    });
  };

  componentWillMount() {
    //getUserById(this.props.uid).then(data => this.setState({ ...data.data }));
    let { open, handleClose, item } = this.props;
    this.setState({ ...item });
  }

  selectadministrativeUnit = (adminUnit) => {
    // this.setState({ suspectedType: suspectedType }, function () {
    // });
    
    this.setState({ adminUnit: adminUnit }, function () {
    });
}

  render() {
    let {
      id,
      name,
      code,
      address,
      contact,
      note,
      description,
      adminUnit,
      person,
      shouldOpenNotificationPopup,
    } = this.state;
    let { open, handleClose, handleOKEditClose, t, i18n } = this.props;
    let searchObject = { pageIndex: 0, pageSize: 100000 }
    return (
      <Dialog
        open={open}
        PaperComponent={PaperComponent}
        maxWidth="sm"
        fullWidth
      >
        <DialogTitle
          style={{ cursor: "move", paddingBottom: "0px" }}
          id="draggable-dialog-title"
        >
          <h4 className="">{id ? t("Sửa") : t("Thêm mới")}</h4>
        </DialogTitle>

        <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
          <DialogContent>
            <Grid className="" container spacing={2}>

              {/* Tên */}
              <Grid item sm={12} xs={12}>
                <TextValidator
                  className="w-100 "
                  label={
                    <span>
                      <span style={{ color: "red" }}>*</span>
                      {t("Tên")}
                    </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="name"
                  value={name}
                  validators={["required"]}
                  errorMessages={[t("required")]}
                />
              </Grid>

              {/* Mã */}
              <Grid item sm={12} xs={12}>
                <TextValidator
                  className="w-100 "
                  label={
                    <span>
                      <span style={{ color: "red" }}>*</span>
                      {t("Mã")}
                    </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="code"
                  value={code}
                  validators={["required"]}
                  errorMessages={[t("required")]}
                />
              </Grid>

              {/* Địa chỉ */}
              <Grid item sm={12} xs={12}>
                <TextValidator
                  className="w-100 "
                  label={
                    <span>
                      <span style={{ color: "red" }}>*</span>
                      {t("Địa chỉ")}
                    </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="address"
                  value={address}
                  validators={["required"]}
                  errorMessages={[t("required")]}
                />
              </Grid>

              {/* Thông tin liên hệ */}
              <Grid item sm={12} xs={12}>
                <TextValidator
                  className="w-100 "
                  label={
                    <span>
                      <span style={{ color: "red" }}>*</span>
                      {t("Thông tin liên hệ")}
                    </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="contact"
                  value={contact}
                  validators={["required"]}
                  errorMessages={[t("required")]}
                />
              </Grid>

              {/* Ghi chú */}
              <Grid item lg={12} md={6} sm={12} xs={12}>
                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                  <KeyboardDatePicker
                    size="large"
                    className="w-100"
                    margin="none"
                    id="date-picker-dialog"
                    label={
                      <span>
                        <span style={{ color: "red" }}>*</span>
                        {t("Ghi chú")}
                      </span>
                    }
                    inputVariant="standard"
                    type="text"
                    autoOk={true}
                    format="dd/MM/yyyy"
                    value={note}
                    onChange={(value) => {
                      this.setState({ note: value })
                    }}
                    KeyboardButtonProps={{
                      'aria-label': 'change date',
                    }}
                    validators={["required"]}
                    errorMessages={t('general.errorMessages_required')}
                  />
                </MuiPickersUtilsProvider>
              </Grid>

              {/* Đơn vị hành chính */}
              <Grid item sm={12} xs={12}>
                <AsynchronousAutocomplete label={t("Đơn vị hành chính")}
                  searchFunction={administrativeUnitSearchByDto}
                  searchObject={searchObject}
                  value={adminUnit ? adminUnit : null}
                  defaultValue={adminUnit ? adminUnit : null}
                  multiple={false}
                  displayLable={'name'}
                  // validators={["required"]}
                  // errorMessages={t('')}
                  variant="standard"
                  className="mb-16 w-100"
                  onSelect={this.selectadministrativeUnit}
                />
              </Grid>
            </Grid>
          </DialogContent>
          <DialogActions>
            <div className="flex flex-space-between flex-middle mt-12">
              <Button
                variant="contained"
                className="mr-12"
                color="secondary"
                onClick={() => this.props.handleClose()}
              >
                {t("Huỷ")}
              </Button>
              <Button
                variant="contained"
                style={{ marginRight: "15px" }}
                color="primary"
                type="submit"
              >
                {t("Lưu")}
              </Button>
            </div>
          </DialogActions>
        </ValidatorForm>
      </Dialog>
    );
  }
}
export default EpidemiologicalFactorsDialog;
