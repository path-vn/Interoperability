import React, { Component } from "react";
import {
  Dialog,
  Button,
  Grid,
  FormControlLabel,
  Switch,
  DialogTitle,
  DialogContent,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  TextField,
  DialogActions, Icon, IconButton
} from "@material-ui/core";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import {getByPage, getListBasic, addNewAdministrativeUnit, updateAdministrativeUnit, checkCode } from "./AdministrativeUnitService";
import SelectParentPopup from "./SelectParentPopup";
import { generateRandomId } from "utils";
import Draggable from 'react-draggable';
import Paper from '@material-ui/core/Paper';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Autocomplete from "@material-ui/lab/Autocomplete";

import clsx from 'clsx';
import CircularProgress from '@material-ui/core/CircularProgress';
toast.configure({
  autoClose: 1000,
  draggable: false,
  limit: 3
});

function PaperComponent(props) {
  return (
    <Draggable handle="#draggable-dialog-title" cancel={'[class*="MuiDialogContent-root"]'}>
      <Paper {...props} />
    </Draggable>
  );
}

class AdministrativeUnitEditorDialog extends Component {
  state = {
    id: null,
    name: "",
    code: "",
    level: 0,
    parent: {},
    listAdministrativeUnit:[],
    shouldOpenSelectParentPopup: false,
    parentId: "",
  };
  //subList = this.props.item.subAdministrativeUnits;
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
  openCircularProgress = () => {
    this.setState({ loading: true });
  };
  //popup
  handleDialogClose = () => {
    this.setState({ shouldOpenSelectParentPopup: false });
  };
  openParentPopup = (event) => {
    this.setState({ shouldOpenSelectParentPopup: true });
  };
  handleSelectParent = (itemParent) => {
    let { t } = this.props;
    let { id } = this.state;
    let { code, parent } = this.state;
    let idClone = id;
    let { item } = this.state;
    if (id) {
        let isCheck = false;
        let parentClone = itemParent;
        let children = this.props.item;
        // if(typeof)
        if (children.parentId === null && children.id == parentClone.id) {
            isCheck = true;
        }
        while (parentClone != null) {
            if (parentClone.id == children.id) {
                isCheck = true;
                break;
            } else {
                parentClone = parentClone.parent;
            }
        }
        if (isCheck) {
            alert(t("user.warning_parent"));
            return;
        }
    }
    this.setState({ parent: itemParent });
    this.setState({ shouldOpenSelectParentPopup: false });
};
  handleFormSubmit = async () => {
    await this.openCircularProgress();
    let { id, code } = this.state;
    let { t } = this.props;
    if (this.validateData()) {
      toast.warning('trường dữ liệu không thể để trống');
    } else {
      if (id) {
        updateAdministrativeUnit({
          ...this.state
        },id).then(() => {
          toast.success(t('mess_edit'));
          this.setState({ loading: false });
          this.props.handleClose();
        });
      } else {
        addNewAdministrativeUnit({
          ...this.state
        }).then((response) => {
          if (response.data != null && response.status == 200) {
            this.state.id = response.data.id
            this.setState({ ...this.state, loading: false })
            toast.success(t('mess_add'));
            this.props.handleClose();
          }
        });
      }
    }
  };

  componentDidMount() {
    debugger
    getListBasic(this.state.id ? this.state.id : null).then((data) => {
      this.setState({ listAdministrativeUnit: data.data });
      console.log(this.state.listAdministrativeUnit);
    });
  }
  componentWillMount() {
    let { open, handleClose, item } = this.props;
    this.setState(item);
  }

  validateData = () => {
    if (this.state.name.trim().length == 0 || this.state.code.trim().length == 0) {
      return true;
    }
    return false;
  }

  render() {
    let { open, handleClose, handleOKEditClose, t, i18n, item } = this.props;
    var subArr = this.props.item.subAdministrativeUnits;
    let {
      id,
      name,
      code,
      level,
      parent,
      parentId,
      listAdministrativeUnit,
      isActive,
      loading,
    } = this.state;
    var stop = false;
    return (
      <Dialog open={open} PaperComponent={PaperComponent} maxWidth={'sm'} fullWidth={true}>
        <DialogTitle style={{ cursor: 'move' }} id="draggable-dialog-title">
          <span className="mb-20" > {(id ? t("update") : t("Add")) + " " + t("administrativeUnit.titlePopup")} </span>
          <IconButton style={{ position: "absolute", right: "10px", top: "10px" }} onClick={() => handleClose()}><Icon color="error"
            title={t("close")}>
            close
          </Icon>
          </IconButton>
        </DialogTitle>
        <ValidatorForm ref="form" onSubmit={this.handleFormSubmit} >
          <DialogContent>
            <Grid className="" container spacing={2}>
              {/* code */}
              <Grid item sm={12} xs={12}>
                <TextValidator
                  className="w-100 mb-16"
                  label={<span className="font">
                    <span style={{ color: "red" }}> *</span>
                    {t('administrativeUnit.code')}
                  </span>
                  }
                  onChange={this.handleChange}
                  type="text"
                  name="code"
                  value={code}
                  validators={["required"]}
                  errorMessages={[t("general.errorMessages_required")]}
                  variant="outlined"
                  size="small"
                />
              </Grid>
              {/* name */}
              <Grid item sm={12} xs={12}>
                <TextValidator
                  className="w-100 mb-16"
                  label={<span className="font">
                    <span style={{ color: "red" }}> *</span>
                    {t('administrativeUnit.name')}
                  </span>}
                  onChange={this.handleChange}
                  type="text"
                  name="name"
                  value={name}
                  validators={["required"]}
                  errorMessages={[t("general.errorMessages_required")]}
                  variant="outlined"
                  size="small"
                />
              </Grid>
              {/* parent */}
              <Grid item sm={12} xs={12}>
                <Button
                    size="small"
                    style={{ float: "right" }}
                    variant="contained"
                    color="primary"
                    onClick={this.openParentPopup}>
                    {t("general.select")}
                </Button>
                <TextValidator
                    size="small"
                    // InputLabelProps={{ shrink: true }}
                    style={{ width: "85%" }}
                    InputProps={{
                        readOnly: true,
                    }}
                    label={
                        <span>
                            <span style={{ color: "red" }}></span>
                            {t("Đơn vị cấp trên")}
                        </span>
                    }
                    // className="w-80"
                    value={
                        this.state.parent != null ? this.state.parent.name ? this.state.parent.name : "" : ""
                    }
                />

                {this.state.shouldOpenSelectParentPopup && (
                    <SelectParentPopup
                        open={this.state.shouldOpenSelectParentPopup}
                        handleSelect={this.handleSelectParent}
                        selectedItem={
                            this.state.item != null && this.state.item.parent != null
                                ? this.state.item.parent
                                : {}
                        }
                        handleClose={this.handleDialogClose}
                        t={t}
                        i18n={i18n}
                    />
                )}
              </Grid>
            </Grid>
          </DialogContent>

          <DialogActions spacing={4} className="flex flex-end flex-middle">
            <Button
              variant="contained"
              color="secondary"
              onClick={() => this.props.handleClose()}>
              {t('Cancel')}
            </Button>
            <Button
              variant="contained"
              className="mr-12"
              color="primary"
              type="submit">
              {t('Save')}
            </Button>
          </DialogActions>
        </ValidatorForm>
      </Dialog>
    );
  }
}

export default AdministrativeUnitEditorDialog;
