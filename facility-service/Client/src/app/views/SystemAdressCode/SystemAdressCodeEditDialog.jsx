import React, { Component } from "react";
import {
  Dialog,
  Button,
  Grid,
  FormControlLabel,
  Switch,
  FormControl,
  DialogTitle,
  DialogContent,
  InputLabel,
  Select,
  MenuItem,
  TextField,
  DialogActions, Icon, IconButton
} from "@material-ui/core";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import { getAllBasicInEdit, addNewSystemAdressCode, updateSystemAdressCode, checkCode } from "./SystemAdressCodeService";
import SelectParentPopup from "./SelectParentPopup";
import { generateRandomId } from "utils";
import Draggable from 'react-draggable';
import Paper from '@material-ui/core/Paper';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Autocomplete from "@material-ui/lab/Autocomplete";
import ConstantList from "../../appConfig";

import clsx from 'clsx';
import CircularProgress from '@material-ui/core/CircularProgress';
import { searchByPage } from "../User/UserService";
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

class SystemAdressCodeEditorDialog extends Component {
  state = {
    id: null,
    name: "",
    code: "",
    level: 0,
    // administrativeUnit: {
    //   id:'2b156e92-949c-482c-86a5-407007f31215',
    //   uuid:'2dd965db-6054-4126-9cef-f58127809895',
    //   code: '2',
    //   name: 'name2'
    // },
    shouldOpenSelectParentPopup: false,
    administrativeUnit:{},
    systemType:""
  };
  listSystemType = ConstantList.SYSTEM_TYPE;
  //popup
  handleDialogClose = () => {
    this.setState({ shouldOpenSelectParentPopup: false });
  };
  openParentPopup = (event) => {
    this.setState({ shouldOpenSelectParentPopup: true });
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
  openCircularProgress = () => {
    this.setState({ loading: true });
  };

  selectSystemType = (event, item) => { 
    //event.persist();
    this.setState(
      { isActive: event.target.checked, 
        [event.target.name]: event.target.value,
        //systemType: item.key
      });  
  }

  handleFormSubmit = async () => {
    await this.openCircularProgress();
    let { id, code } = this.state;
    let { t, item } = this.props;
    if(this.validateData()) {
      toast.warning('tr?????ng d??? li???u kh??ng th??? ????? tr???ng');
    } else {
      if (id) {
        updateSystemAdressCode({
          ...this.state
        },id).then(() => {
          toast.success(t('mess_edit'));
          this.setState({ loading: false });
          this.props.handleClose();
        });
      } else {
        addNewSystemAdressCode({
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

  handleSelectParent = (itemParent) => {
    let { t } = this.props;
    let { id } = this.state;
    let { code, administrativeUnit } = this.state;
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
                parentClone = parentClone.administrativeUnit;
            }
        }
        if (isCheck) {
            alert(t("user.warning_parent"));
            return;
        }
    }
    this.setState({ administrativeUnit: itemParent });
    this.setState({ shouldOpenSelectParentPopup: false });
};
  componentDidMount() {
    
  }

  componentWillMount() {
    //getUserById(this.props.uid).then(data => this.setState({ ...data.data }));
    let { open, handleClose, item } = this.props;
    this.setState(item);
  }

  validateData = () => {
    if(this.state.name.trim().length == 0 ||  this.state.code.trim().length == 0) {
      return true;
    }
    return false;
  }

  render() {
    let { open, handleClose, handleOKEditClose, t, i18n } = this.props;
    let {
      id,
      systemType,
      name,
      code,
      level,
      administrativeUnit,
      isActive,
      loading
    } = this.state;
    
    return (
      <Dialog open={open} PaperComponent={PaperComponent} maxWidth={'sm'} fullWidth={true}>
        <DialogTitle style={{ cursor: 'move' }} id="draggable-dialog-title">
          <span className="mb-20" > {(id ? t("update") : t("Add")) + " " + t("systemAdressCode.titlePopup")} </span>
          <IconButton style={{ position: "absolute", right: "10px", top: "10px" }} onClick={() => handleClose()}><Icon color="error"
            title={t("close")}>
            close
            </Icon>
          </IconButton>
        </DialogTitle>
        <ValidatorForm ref="form" onSubmit={this.handleFormSubmit} >
          <DialogContent>
            <Grid className="" container spacing={2}>
              <Grid item sm={12} xs={12}>
                <FormControl fullWidth={true} variant="standard" size="small">
                    <InputLabel htmlFor="type-simple">
                        {
                          <span className="">
                            {t("Ngu???n d??? li???u")}
                          </span>
                        }
                    </InputLabel>
                    <Select
                        // style={{ marginTop: '16px' }}
                        value={systemType ? systemType : null}
                        onChange={(key) => this.selectSystemType(key)}
                        inputProps={{
                            name: "systemType",
                            id: "objectType-simple",
                        }}
                        validators={["required"]}
                        errorMessages={[t("general.required")]}
                    >
                        {this.listSystemType.map((item) => {
                            return (
                                <MenuItem key={item.key} value={item.name}>
                                    {item.key}
                                </MenuItem>
                            );
                        })}
                    </Select>
                </FormControl>
              </Grid>
              <Grid item sm={12} xs={12}>
                <TextValidator
                  className="w-100 mb-16"
                  label={<span className="font">
                    <span style={{ color: "red" }}> *</span>
                    {t('systemAdressCode.code')}
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
              <Grid item sm={12} xs={12}>
                <TextValidator
                  className="w-100 mb-16"
                  label={<span className="font">
                    <span style={{ color: "red" }}> *</span>
                    {t('systemAdressCode.name')}
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
                            {t("????n v??? c???p tr??n")}
                        </span>
                    }
                    // className="w-80"
                    value={
                        this.state.administrativeUnit != null ? this.state.administrativeUnit.name ? this.state.administrativeUnit.name : "" : ""
                    }
                />

                {this.state.shouldOpenSelectParentPopup && (
                    <SelectParentPopup
                        open={this.state.shouldOpenSelectParentPopup}
                        handleSelect={this.handleSelectParent}
                        selectedItem={
                            this.state.item != null && this.state.item.administrativeUnit != null
                                ? this.state.item.administrativeUnit
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

export default SystemAdressCodeEditorDialog;
