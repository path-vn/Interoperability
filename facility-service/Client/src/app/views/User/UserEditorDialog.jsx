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
  DialogContent,
} from "@material-ui/core";
import Autocomplete from "@material-ui/lab/Autocomplete";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import {
  getUserByUsername,
  getUserByEmail,
  saveUser,
  addNewUser,
  checkEmail,
  getAllInfoByUserLogin,
  getRoleUser,
  getAllRoles,
  saveUserOrg,
} from "./UserService";
import UserRoles from "app/services/UserRoles";
import AsynchronousAutocomplete from "../utilities/AsynchronousAutocomplete";

class UserEditorDialog extends Component {
  constructor(props) {
    super(props);

    // getAllRoles().then((result) => {
    //   let listRole = result.data;
    //   this.setState({ listRole: listRole });
    // });
  }
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

  handleFormSubmit = () => {
    let { id, user } = this.state;
    let userOrg = {};
    if (user == null) {
      user = {};
    }
    user.username = this.state.username;
    user.email = this.state.email;
    user.person = this.state.person;
    user.roles = this.state.roles;
    user.active = this.state.active;
    user.changePass = this.state.changePass;
    user.isActive = this.state.isActive;
    user.password = this.state.password;
    userOrg.user = user;
    userOrg.org = this.state.org;
    userOrg.id = this.state.id;
    getUserByUsername(this.state.username).then((data) => {
      if (data.data && data.data.id) {
        if (!user.id || (id && data.data.id != user.id)) {
          alert("Tên đăng nhập đã tồn tại!");
          return;
        }
      }
      // checkEmail(userOrg).then((data)=>{
      //   console.log(data.data)
      //   if(data?.data){
      //     alert("Địa chỉ email đã tồn tại!");
      //     return;
      //   }
      

      // })
      saveUserOrg(userOrg).then(() => {
        this.props.handleOKEditClose();
      });
      // getUserByEmail(this.state.username).then((data2) => {
      //   if (data2.data && data2.data.id) {
      //     alert("Địa chỉ email đã tồn tại!");
      //     return;
      //   }
      // });
    });
  };

  selectRoles = (rolesSelected) => {
    this.setState({ roles: rolesSelected }, function () { });
  };

  componentWillMount() {
    let { open, handleClose, item } = this.props;
  }

  componentDidMount() {
    // custom rule will have name 'isPasswordMatch'
    ValidatorForm.addValidationRule("isPasswordMatch", (value) => {
      if (value !== this.state.password) {
        return false;
      }
      return true;
    });
 

  }
  selectHealthOrganization = (event, labTest) => {
    this.setState({ org: labTest }, function () { });
  };
  render() {
    console.log(this.state.listOrg);
    let { open, handleClose, handleOKEditClose, t, i18n } = this.props;
    let {
      id,
      isAddNew,
      listRole,
      roles,
      active,
      email,
      person,
      username,
      org,
      changePass,
      password,
      confirmPassword,
    } = this.state;
    let searchObject = { pageIndex: 0, pageSize: 100000 };
    return (
      <Dialog open={open} maxWidth={"md"} fullWidth>
        <DialogTitle
          className="styleColor"
          style={{ cursor: "move" }}
          id="draggable-dialog-title"
        >
          <span className="mb-20 styleColor">
            {" "}
            {(id ? t("update") : t("Add")) + " " + t("user.title")}{" "}
          </span>
          <IconButton
            style={{ position: "absolute", right: "10px", top: "10px" }}
            onClick={() => handleClose()}
          >
            <Icon color="error" title={t("close")}>
              close
            </Icon>
          </IconButton>
        </DialogTitle>
        <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
          <DialogContent
            dividers
            style={{
              overflow: "hidden",
            }}
          >
            <Grid className="mb-16" container spacing={2}>
              <Grid item sm={6} xs={12}>
                <TextValidator
                  className="w-100 mb-16"
                  label={
                    <span className="font">
                      <span style={{ color: "red" }}>*</span>
                      {t("user.displayName")}
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
                            {t("user.role")}
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
                <Autocomplete
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
                          {t("Đơn vị y tế")}
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
                />
              </Grid>
              {!isAddNew && (
                <Grid item sm={6} xs={12}>
                  <FormControlLabel
                    value={changePass}
                    className="mb-16"
                    name="changePass"
                    onChange={(changePass) =>
                      this.handleChange(changePass, "changePass")
                    }
                    control={<Checkbox checked={changePass} />}
                    label={t("user.changePass")}
                  />
                </Grid>
              )}
              <Grid item sm={6} xs={12}>
                <FormControlLabel
                  value={active}
                  className="mb-16"
                  name="active"
                  onChange={(active) => this.handleChange(active, "active")}
                  control={<Checkbox checked={active} />}
                  label={t("user.active")}
                />
              </Grid>
              {changePass != null && changePass == true ? (
                <Grid container spacing={2}>
                  <Grid item sm={6} xs={12}>
                    <TextValidator
                      className="mb-16 w-100"
                      label={
                        <span className="font">
                          <span style={{ color: "red" }}>*</span>
                          {t("password")}
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
                        t("user.passwordMessage"),
                      ]}
                    />
                  </Grid>
                  <Grid item sm={6} xs={12}>
                    <TextValidator
                      className="mb-16 w-100"
                      label={
                        <span className="font">
                          <span style={{ color: "red" }}>*</span>
                          {t("re_password")}
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
                        t("user.passwordMatchMessage"),
                      ]}
                    />
                  </Grid>
                </Grid>
              ) : (
                <div></div>
              )}
            </Grid>
          </DialogContent>
          {/* <div className="flex flex-space-between flex-middle"> */}
          <DialogActions spacing={4} className="flex flex-end flex-middle">
            <Grid
              container
              spacing={2}
              direction="row"
              justify="flex-end"
              alignItems="center"
              md={12}
              xs={12}
              lg={12}
              sm={12}
            >
              <Button
                variant="contained"
                color="secondary"
                className="mr-16 mb-16"
                onClick={() => this.props.handleClose()}
              >
                {t("Cancel")}
              </Button>
              <Button
                variant="contained"
                className="mr-16 mb-16"
                color="primary"
                type="submit"
              >
                {t("Save")}
              </Button>
            </Grid>

            {/* </div> */}
          </DialogActions>
        </ValidatorForm>

        {/* <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
            <Grid className="mb-16" container spacing={2}>
              <Grid item sm={6} xs={12}>
                <TextValidator
                  className="w-100 mb-16"
                  label={t("user.displayName")}
                  onChange={(displayName) =>
                    this.handleChange(displayName, "displayName")
                  }
                  type="text"
                  name="name"
                  value={person ? person.displayName : ""}
                  validators={["required"]}
                  errorMessages={["this field is required"]}
                />
              </Grid>
              <Grid item sm={6} xs={12}>
                <FormControl fullWidth={true}>
                  <InputLabel htmlFor="gender-simple">
                    {t("user.gender")}
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
                  label={t("user.username")}
                  onChange={this.handleChange}
                  type="text"
                  name="username"
                  value={username}
                  validators={["required"]}
                  errorMessages={["this field is required"]}
                />
              </Grid>
              <Grid item sm={6} xs={12}>
                <TextValidator
                  className="w-100 mb-16"
                  label="Email"
                  onChange={this.handleChange}
                  type="email"
                  name="email"
                  value={email}
                  validators={["required", "isEmail"]}
                  errorMessages={[
                    "This field is required",
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
                        label={t("user.role")}
                        fullWidth
                        validators={["required"]}
                        errorMessages={[t("user.please_select_permission")]}
                      />
                    )}
                  />
                )}
              </Grid>
              <Grid item lg={6} md={6} sm={6} xs={12}>
                <Autocomplete
                  className="w-100 mt-10"
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
                          {t("Đơn vị xét nghiệm")}
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
                />
              </Grid>
              {!isAddNew && (
                <Grid item sm={6} xs={12}>
                  <FormControlLabel
                    value={changePass}
                    className="mb-16"
                    name="changePass"
                    onChange={(changePass) =>
                      this.handleChange(changePass, "changePass")
                    }
                    control={<Checkbox checked={changePass} />}
                    label={t("user.changePass")}
                  />
                </Grid>
              )}
              <Grid item sm={6} xs={12}>
                <FormControlLabel
                  value={active}
                  className="mb-16"
                  name="active"
                  onChange={(active) => this.handleChange(active, "active")}
                  control={<Checkbox checked={active} />}
                  label={t("user.active")}
                />
              </Grid>
              {changePass != null && changePass == true ? (
                <Grid container spacing={2}>
                  <Grid item sm={6} xs={12}>
                    <TextValidator
                      className="mb-16 w-100"
                      label={t("password")}
                      variant="outlined"
                      onChange={this.handleChange}
                      name="password"
                      type="password"
                      value={password}
                      validators={["required"]}
                      errorMessages={["This field is required"]}
                    />
                  </Grid>
                  <Grid item sm={6} xs={12}>
                    <TextValidator
                      className="mb-16 w-100"
                      label={t("re_password")}
                      variant="outlined"
                      onChange={this.handleChange}
                      name="confirmPassword"
                      type="password"
                      value={confirmPassword}
                      validators={["required", "isPasswordMatch"]}
                      errorMessages={[
                        "This field is required",
                        "Password mismatch",
                      ]}
                    />
                  </Grid>
                </Grid>
              ) : (
                <div></div>
              )}
            </Grid>

            <div className="flex flex-space-between flex-middle">
              <Button variant="contained" color="primary" type="submit">
                Save
              </Button>
              <Button
                variant="contained"
                color="secondary"
                onClick={() => this.props.handleClose()}
              >
                Cancel
              </Button>
            </div>
          </ValidatorForm> */}
      </Dialog>
    );
  }
}

export default UserEditorDialog;
