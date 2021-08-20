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
  FormControlLabel
} from "@material-ui/core";
import Autocomplete from '@material-ui/lab/Autocomplete';
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import { getUserByUsername, saveUser, addNewUser, getAllRoles } from "./UserService";
import AsynchronousAutocomplete from "../utilities/AsynchronousAutocomplete";

class UserEditorDialog extends Component {
  constructor(props) {
    super(props);

    getAllRoles().then((result) => {
      let listRole = result.data;
      this.setState({ listRole: listRole });
    });
  }
  state = {
    isAddNew: false,
    listRole: [],
    roles: [],
    active: true,
    email: '',
    person: {},
    username: '',
    changePass: true,
    password: '',
    confirmPassword: ''
  };

  listGender = [
    { id: 'M', name: 'Nam' },
    { id: 'F', name: 'Nữ' },
    { id: 'U', name: 'Không rõ' }
  ]

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
      let {person} = this.state;
      person = person ? person : {};
      person.displayName = event.target.value;
      this.setState({ person: person });
      return;
    }
    if (source === "gender") {
      let {person} = this.state;
      person = person ? person : {};
      person.gender = event.target.value;
      this.setState({ person: person });
      return;
    }
    this.setState({
      [event.target.name]: event.target.value
    });
  };

  handleFormSubmit = () => {
    let { id } = this.state;

    getUserByUsername(this.state.username).then((data) => {
      if (data && data.id) {
          if (!id || (id && data.id != id)) {
              alert("Tên đăng nhập đã tồn tại!");
              return;
          }
      }

      getUserByUsername(this.state.username).then((data2) => {
        if (data2 && data2.id) {
            alert("Địa chỉ email đã tồn tại!");
            return;
        }
        
      });

      saveUser({
        ...this.state
      }).then(() => {
        this.props.handleOKEditClose();
      });

    });
  };

  selectRoles = (rolesSelected) => {
    this.setState({ roles: rolesSelected }, function () {
    });
  }

  componentWillMount() {
    let { open, handleClose, item } = this.props;
    this.setState(item);
  }

  componentDidMount() {
    // custom rule will have name 'isPasswordMatch'
    ValidatorForm.addValidationRule('isPasswordMatch', (value) => {
      if (value !== this.state.password) {
        return false;
      }
      return true;
    });

    getAllRoles().then(({ data }) => {
      this.setState({
        listRole: data
      });
    });
  }

  render() {
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
      changePass,
      password,
      confirmPassword
    } = this.state;

    return (
      <Dialog onClose={handleClose} open={open} maxWidth={'md'} fullWidth={true}>
        <div className="p-24">
          <h4 className="mb-20">{t('SaveUpdate')}</h4>
          <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
            <Grid className="mb-16" container spacing={2}>
              <Grid item sm={6} xs={12}>
                <TextValidator
                  className="w-100 mb-16"
                  label={t('user.displayName')}
                  onChange={displayName => this.handleChange(displayName, "displayName")}
                  type="text"
                  name="name"
                  value={person ? person.displayName : ''}
                  validators={["required"]}
                  errorMessages={["this field is required"]}
                />
              </Grid>
              <Grid item sm={6} xs={12}>
                <FormControl fullWidth={true}>
                  <InputLabel htmlFor="gender-simple">{t('user.gender')}</InputLabel>
                  <Select
                    value={person ? person.gender : ''}
                    onChange={gender => this.handleChange(gender, "gender")}
                    inputProps={{
                      name: "gender",
                      id: "gender-simple"
                    }}
                  >
                    {this.listGender.map(item => {
                      return <MenuItem key={item.id} value={item.id}>{item.name}</MenuItem>;
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
                  label={t('user.username')}
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
                    "Email is not valid"
                  ]}
                />
              </Grid>
              <Grid item sm={12} xs={12}>
                {listRole && (<Autocomplete
                  style={{ width: '100%' }}
                  multiple
                  id="combo-box-demo"
                  defaultValue={roles}
                  options={listRole}
                  getOptionSelected={(option, value) => option.id === value.id}
                  getOptionLabel={(option) => option.authority}
                  onChange={(event, value)=> {
                    this.selectRoles(value);
                  }}
                  renderInput={(params) =>
                    <TextValidator
                      {...params}
                      value={roles}
                      label={t('user.role')}
                      fullWidth
                      validators={["required"]}
                      errorMessages={[t('user.please_select_permission')]}
                    />}
                />)}
              </Grid>
              {!isAddNew && <Grid item sm={6} xs={12}>
                <FormControlLabel
                  value={changePass}
                  className="mb-16"
                  name="changePass"
                  onChange={changePass => this.handleChange(changePass, "changePass")}
                  control={<Checkbox
                    checked={changePass}
                  />}
                  label={t("user.changePass")}
                />
              </Grid>}
              <Grid item sm={6} xs={12}>
                <FormControlLabel
                  value={active}
                  className="mb-16"
                  name="active"
                  onChange={active => this.handleChange(active, "active")}
                  control={<Checkbox
                    checked={active}
                  />}
                  label={t("user.active")}
                />
              </Grid>
              {
                (changePass != null && changePass == true)
                  ?
                  <Grid container spacing={2}>
                    <Grid item sm={6} xs={12}>
                      <TextValidator
                        className="mb-16 w-100"
                        label={t('password')}
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
                        label={t('re_password')}
                        variant="outlined"
                        onChange={this.handleChange}
                        name="confirmPassword"
                        type="password"
                        value={confirmPassword}
                        validators={['required', 'isPasswordMatch']}
                        errorMessages={['This field is required', 'Password mismatch']}
                      />
                    </Grid>
                  </Grid>
                  :
                  <div></div>
              }
            </Grid>

            <div className="flex flex-space-between flex-middle">
              <Button variant="contained" color="primary" type="submit">
                Save
              </Button>
              <Button variant="contained" color="secondary" onClick={() => this.props.handleClose()}>Cancel</Button>
            </div>
          </ValidatorForm>
        </div>
      </Dialog>
    );
  }
}

export default UserEditorDialog;
