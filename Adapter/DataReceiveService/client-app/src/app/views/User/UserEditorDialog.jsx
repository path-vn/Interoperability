import React, { Component } from "react";
import {
  Dialog,
  Button,
  Grid,
  InputLabel,
  FormControl,
  MenuItem,
  Select,
  Checkbox,
  TextField,
  FormControlLabel,
  DialogTitle,
  IconButton,
  Icon,
  DialogActions,
  DialogContent
} from "@material-ui/core";
import Autocomplete from "@material-ui/lab/Autocomplete";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import {
  getUserByUsername,
  // getAllInfoByUserLogin,
  getRoleUser,
  getAllRoles,
  saveUserOrg,
} from "./UserService";
import UserRoles from "app/services/UserRoles";
import { findAllChildHealthOrganizationByUserLogin as getOrg } from "../HealthOrg/HealthOrgService";
import { toast } from 'react-toastify';
import Draggable from 'react-draggable';
import Paper from '@material-ui/core/Paper';
import 'react-toastify/dist/ReactToastify.css';
import SaveIcon from '@material-ui/icons/Save';
import BlockIcon from '@material-ui/icons/Block';
import ChangePassWordAccordion from './ChangePassWordAccordion';
import InputPopupHealthOrg from '../HealthOrg/InputPopup/InputPopup';
import UserEditorForm from './UserEditorForm';
toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3
  //etc you get the idea
});
function PaperComponent(props) {
  return (
    <Draggable handle="#draggable-dialog-title" cancel={'[class*="MuiDialogContent-root"]'}>
      <Paper {...props} />
    </Draggable>
  );
}
class UserEditorDialog extends Component {
  state = {
    isAddNew: false,
    listRole: [],
    roles: [],
    active: true,
    email: "",
    person: {},
    username: "",
    org: {},
    changePass: true,
    password: "",
    confirmPassword: "",
  };

  listGender = [
    { id: "M", name: "Nam" },
    { id: "F", name: "Nữ" },
    { id: "U", name: "Không rõ" },
  ];

  handleChange = (event, source) => {
    event.persist();
    if (source === "switch") {
      this.setState({ isActive: event.target.checked });
      return;
    }
    if (source === "changePass") {
      this.setState({ changePass: event.target.checked });
      return;
    }
    if (source === "active") {
      this.setState({ active: event.target.checked });
      return;
    }
    if (source === "displayName") {
      let { person } = this.state;
      person = person ? person : {};
      person.displayName = event.target.value;
      this.setState({ person: person });
      return;
    }
    if (source === "gender") {
      let { person } = this.state;
      person = person ? person : {};
      person.gender = event.target.value;
      this.setState({ person: person });
      return;
    }
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  handleChangePassWord = (password) => {
    this.setState({
      password: password,
      changePass: true
    }, () => {
      // console.log(this.state.password)
    })
  }

  handleFormSubmit = (values) => {
    let { t } = this.props
    let { id, user } = this.state;
    let userOrg = {};
    if (user == null) {
      user = {};
    }
    user.username = values.username;
    user.email = values.email;
    user.person = values.person;
    user.roles = values.roles;
    user.active = values.active;

    if(values.password === values.confirmPassword) {
      user.changePass = true;
    }
    
    user.isActive = values.isActive;
    user.password = values.password;
    user.person.displayName = values.displayName;
    user.person.gender = values.gender;
    userOrg.user = user;
    userOrg.org = values.org;
    userOrg.id = id;
    console.log(user);
    console.log(values);
    getUserByUsername(values.username).then((data) => {
      if (data.data && data.data.id) {
        if (!user.id || (id && data.data.id !== user.id)) {
          toast.warning(t('toast.user_exist'));
          return;
        }
      }
      saveUserOrg(userOrg).then(() => {
        this.props.handleOKEditClose();
        toast.success(t('toast.update_success'));
      });
    });
  };

  selectRoles = (rolesSelected) => {
    this.setState({ roles: rolesSelected }, function () { });
  };
  componentDidMount() {
    // custom rule will have name 'isPasswordMatch'
    ValidatorForm.addValidationRule("isPasswordMatch", (value) => {
      if (value !== this.state.password) {
        return false;
      }
      return true;
    });
  }
  componentWillMount() {
    let { item } = this.props;
    if (!item.id) {
      this.setState({ isAddNew: true, user: item.user });
    }
    this.setState(item);
    this.setState({ ...item.user, org: item.org }, () => {
      this.setState({ id: item.id, user: item.user, org: item.org, password: '' });
    });
    console.log(this.props);
    if (!UserRoles.isAdmin()) {
      // getAllInfoByUserLogin().then(({ data }) => {
      //   let idHealthOrg = data?.userOrganization?.org?.id
      //   this.setState({ idHealthOrg: idHealthOrg }, () => {
      //     this.getHealthOrg();
      //   })
      // })
      getRoleUser().then(({ data }) => {
        this.setState({
          listRole: data,
        });
      })
    } else {
      this.getHealthOrg();
      getAllRoles().then(({ data }) => {
        this.setState({
          listRole: data,
        });
      });
    }
  }

  getHealthOrg = () => {
    var searchObject = {};
    searchObject.text = this.state.keyword;
    searchObject.pageIndex = 0;
    searchObject.pageSize = 10000000;
    searchObject.idHealthOrg = this.state.idHealthOrg
    getOrg(searchObject).then(({ data }) => {
      this.setState({
        listOrg: [...data.content],
        totalElements: data.totalElements,
      });
    });
  }

  selectHealthOrganization = (event, labTest) => {
    this.setState({ org: labTest }, function () { });
  };
  render() {
    console.log(this.state);
    let { open, handleClose, t } = this.props;
    let {
      id,
      isAddNew,
      listRole,
      roles,
      active,
      email,
      person,
      username,
      confirmPassword,
      password
    } = this.state;
    return (
      <Dialog
        className="dialog-container"
        open={open}
        PaperComponent={PaperComponent}
        fullWidth
        maxWidth="md"
      >
        <DialogTitle
          className="dialog-header bgc-primary-d1"
          style={{ cursor: 'move' }}
          id="draggable-dialog-title"
        >
          <span className="mb-20 text-white" > {(id ? t('general.button.edit') : t('general.button.add')) + " " + t('user.title')} </span>

          <IconButton
            style={{ position: "absolute", right: "10px", top: "10px" }}
            onClick={() => handleClose()}
          >
            <Icon color="disabled" title={t("close")}>
              close
            </Icon>
          </IconButton>
        </DialogTitle>
        <UserEditorForm 
          initialValues={{
            displayName: person ? person.displayName : "",
            person: person ? person : {},
            email: email ? email : "",
            username: username ? username : "",
            roles: roles ? roles : [],
            gender: person ? person.gender : {},
            org: this.state.org ? this.state.org : null,
            confirmPassword: confirmPassword ? confirmPassword : "",
            password: password ? password : "",
            active: active,
          }}
          isAddNew={isAddNew}
          handleChangePassWord={this.handleChangePassWord}
          handleClose={handleClose}
          handleSubmit={this.handleFormSubmit}
          listRole={listRole}
          listGender={this.listGender}
          listOrg={this.state.listOrg}
        />
        {/* <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
          <div className="dialog-body">
            <DialogContent className="o-hidden">
              <Grid className="mb-16" container spacing={2}>
                <Grid item sm={6} xs={12}>
                  <TextValidator
                    className="w-100 mb-16"
                    label={
                      <span className="font">
                        <span style={{ color: "red" }}>*</span>
                        {t("user.display_name")}
                      </span>
                    }
                    onChange={(displayName) =>
                      this.handleChange(displayName, "displayName")
                    }
                    type="text"
                    name="name"
                    variant="outlined"
                    size="small"
                    value={person ? person.displayName : ""}
                    validators={["required"]}
                    errorMessages={[t("general.errorMessages_required")]}
                  />
                </Grid>
                <Grid item sm={6} xs={12}>
                  <FormControl fullWidth={true} variant="outlined" size="small">
                    <InputLabel htmlFor="gender-simple">
                      {
                        <span className="font">
                          <span style={{ color: "red" }}>*</span>
                          {t("user.gender")}
                        </span>
                      }
                    </InputLabel>
                    <Select
                      value={person ? person.gender : ""}
                      onChange={(gender) => this.handleChange(gender, "gender")}
                      inputProps={{
                        name: "gender",
                        id: "gender-simple",
                      }}
                    >
                      {this.listGender.map((item) => {
                        return (
                          <MenuItem key={item.id} value={item.id}>
                            {item.name}
                          </MenuItem>
                        );
                      })}
                    </Select>
                  </FormControl>
                </Grid>
                <Grid item sm={6} xs={12}>
                  <TextValidator
                    InputProps={{
                      readOnly: !isAddNew,
                    }}
                    className="w-100 mb-16"
                    label={
                      <span className="font">
                        <span style={{ color: "red" }}>*</span>
                        {t("user.username")}
                      </span>
                    }
                    onChange={this.handleChange}
                    type="text"
                    name="username"
                    variant="outlined"
                    size="small"
                    value={username}
                    validators={["required"]}
                    errorMessages={[t("general.errorMessages_required")]}
                  />
                </Grid>
                <Grid item sm={6} xs={12}>
                  <TextValidator
                    className="w-100 mb-16"
                    label={
                      <span className="font">
                        <span style={{ color: "red" }}>*</span>
                        {t("user.email")}
                      </span>
                    }
                    onChange={this.handleChange}
                    type="email"
                    name="email"
                    variant="outlined"
                    size="small"
                    value={email}
                    validators={["required", "isEmail"]}
                    errorMessages={[
                      t("general.errorMessages_required"),
                      "Email is not valid",
                    ]}
                  />
                </Grid>
                <Grid item sm={6} xs={12}>
                  {listRole && (
                    <Autocomplete
                      style={{ width: "100%" }}
                      multiple
                      id="combo-box-demo"
                      defaultValue={roles}
                      options={listRole}
                      getOptionSelected={(option, value) =>
                        option.id === value.id
                      }
                      getOptionLabel={(option) => option.authority}
                      onChange={(event, value) => {
                        this.selectRoles(value);
                      }}
                      renderInput={(params) => (
                        <TextValidator
                          {...params}
                          value={roles}
                          label={
                            <span className="font">
                              <span style={{ color: "red" }}>*</span>
                              {t("user.role.title")}
                            </span>
                          }
                          variant="outlined"
                          size="small"
                          fullWidth
                          validators={["required"]}
                          errorMessages={[t("user.please_select_permission")]}
                        />
                      )}
                    />
                  )}
                </Grid>
                <Grid item lg={6} md={6} sm={6} xs={12}>
                  <InputPopupHealthOrg 
                    label={t("hiv_case.manage_org")}
                    // formik={formik}
                    field="manageOrg"
                    variant="outlined"
                    size="small"
                  />
                  {/* <Autocomplete
                    // className="w-100 mt-16"
                    disableClearable
                    id="combo-box"
                    options={this.state.listOrg ? this.state.listOrg : []}
                    value={this.state.org ? this.state.org : null}
                    renderInput={(params) => (
                      <TextField
                        {...params}
                        value={this.state.org ? this.state.org : null}
                        label={
                          <span>
                            <span style={{ color: "red" }}></span>
                            {t("user.org.title")}
                          </span>
                        }
                        variant="outlined"
                        size="small"
                      />
                    )}
                    getOptionLabel={(option) => option.name}
                    getOptionSelected={(option, value) => option.id === value.id}
                    onChange={(event, value) => {
                      this.selectHealthOrganization(event, value);
                    }}
                  /> */}
                {/* </Grid>

                {!isAddNew && <ChangePassWordAccordion
                  t={t}
                  handleChangePassWord={this.handleChangePassWord}
                />}
                {isAddNew &&
                  <>
                    <Grid item sm={6} xs={12}>
                      <TextValidator
                        className="mb-16 w-100"
                        label={
                          <span className="font">
                            <span style={{ color: "red" }}>*</span>
                            {t("user.password")}
                          </span>
                        }
                        variant="outlined"
                        size="small"
                        onChange={this.handleChange}
                        name="password"
                        type="password"
                        value={password}
                        validators={["required", "matchRegexp:([A-Za-z0-9]{6,})"]}
                        errorMessages={[
                          t("general.errorMessages_required"),
                          t("user.password_message"),
                        ]}
                      />
                    </Grid>
                    <Grid item sm={6} xs={12}>
                      <TextValidator
                        className="mb-16 w-100"
                        label={
                          <span className="font">
                            <span style={{ color: "red" }}>*</span>
                            {t("user.re_password")}
                          </span>
                        }
                        variant="outlined"
                        size="small"
                        onChange={this.handleChange}
                        name="confirmPassword"
                        type="password"
                        value={confirmPassword}
                        validators={["required", "isPasswordMatch"]}
                        errorMessages={[
                          t("general.errorMessages_required"),
                          t("user.password_match_message"),
                        ]}
                      />
                    </Grid>
                  </>
                }
                <Grid item >
                  <FormControlLabel
                    value={active}
                    className="mb-16"
                    name="active"
                    onChange={(active) => this.handleChange(active, "active")}
                    control={<Checkbox checked={active} />}
                    label={t("user.active")}
                  />
                </Grid>
              </Grid>
            </DialogContent>
          </div>
          <div className="dialog-footer">
            <DialogActions className="p-0">
              <div className="flex flex-space-between flex-middle">
                <Button
                  startIcon={<BlockIcon />}
                  variant="contained"
                  className="mr-12 btn btn-secondary d-inline-flex"
                  color="secondary"
                  onClick={() => this.props.handleClose()}
                >
                  {t("general.button.cancel")}
                </Button>
                <Button
                  startIcon={<SaveIcon />}
                  className="mr-0 btn btn-success d-inline-flex"
                  variant="contained"
                  color="primary"
                  type="submit"
                >
                  {t("general.button.save")}
                </Button>
              </div>
            </DialogActions>
          </div>

        </ValidatorForm> */} 

      </Dialog>
    );
  }
}

export default UserEditorDialog;
