import React, { Component } from "react";
import ConstantList from "../../appConfig";
import {
  Card,
  Checkbox,
  FormControlLabel,
  Grid,
  Button,
  Select,
  Input,
  InputLabel,
  FormControl,
  MenuItem,
  FormHelperText
} from "@material-ui/core";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import { getAllEQARound, getAllHealthOrgType, signUpAccount, checkuserName, checkEmail } from "./SessionService";
import { connect } from "react-redux";
import { Helmet } from 'react-helmet';

class SignUp extends Component {
  constructor(props) {
    super(props);
  }

  state = {
    displayName: "",
    userName: "",
    gender: "",
    email: "",
    password: "",
    confirmPassword: "",
  };
  listGender = [
      { id: 'M', name: 'Nam' },
      { id: 'F', name: 'Nữ' },
      { id: 'U', name: 'Không rõ' },
    ]

  handleChange = (event, source) => {
    event.persist()
    if (source === 'switch') {
      this.setState({ isActive: event.target.checked })
      return
    }
    if (source === 'changePass') {
      this.setState({ changePass: event.target.checked })
      return
    }
    if (source === 'active') {
      this.setState({ active: event.target.checked })
      return
    }
    if (source === 'displayName') {
      let displayName = this.state;
      displayName = event.target.value;
      this.setState({ displayName: displayName })
      return
    }
    if (source === 'gender') {
      let gender = this.state;
      gender = event.target.value;
      this.setState({ gender: gender })
      return
    }
    this.setState({
      [event.target.name]: event.target.value,
    })
  }
  openSelectDepartmentPopup = () => {
    this.setState({
      shouldOpenSelectDepartmentPopup: true,
    })
  }

  handleFormSubmit = event => {
    let registerDto = {};
    registerDto.email = this.state.email;
    registerDto.userName = this.state.userName;

    checkuserName(registerDto).then((result) => {
      if (result && result.data && result.data != '') {
        alert('Email này đã được sử dụng.');
      }
      else {
        checkEmail(registerDto).then((result) => {
          if (result && result.data && result.data != '') {
            alert('Email này đã được sử dụng.');
          }
          else {
            signUpAccount(this.state).then((result) => {
              if (result != null && result.data != null && result.data != '') {
                if (result.data.hasEmail) {
                  alert('Email này đã được sử dụng.');
                }
                else if (result.data.sendEmailFailed) {
                  alert('Có lỗi khi gửi email thông báo đến email của bạn. Vui lòng thử lại.');
                }
                else {
                  alert('Đăng ký thành công.');
                  this.props.history.push('/session/signin');
                }
              }
              else {
                alert('Có lỗi xảy ra khi đăng ký.');
              }
            });
          }
        }, (error) => {
          alert('Có lỗi xảy ra khi đăng ký.');
        });
      }
    }, (error) => {
      alert('Có lỗi xảy ra khi đăng ký.');
    });
  };

  componentDidMount() {
    // custom rule will have name 'isPasswordMatch'
    ValidatorForm.addValidationRule('isPasswordMatch', (value) => {
      if (value !== this.state.password) {
        return false;
      }
      return true;
    });
  }
  componentWillUnmount() {
    // remove rule when it is not needed
    ValidatorForm.removeValidationRule('isPasswordMatch');
  }

  render() {
    let { open, handleClose, handleOKEditClose, t, i18n } = this.props
    let {
      displayName,
      email,
      person,
      userName,
      changePass,
      password,
      confirmPassword,
      gender
    } = this.state
    let  TitlePage = t("sign_up.title_form");
    return (
      // <div className="m-sm-30">
      //   <Helmet>
      //     <title>{TitlePage} | {t('web_site')}</title>
      //   </Helmet>
      //   <div className="mb-sm-30">
      //     {/* <Breadcrumb routeSegments={[{ name: t('Product.title') }]} /> */}
      //     <Breadcrumb routeSegments={[
      //       { name: t("Dashboard.category"),path: "/list/product" },
      //        { name: TitlePage }]} />
      //   </div>
      // </div>
      <ValidatorForm ref="form" onSubmit={this.handleFormSubmit} >
        <div className="signup flex flex-center w-100 h-100vh">
          <div className="p-8">
            <Card className="signup-card-customs position-relative y-center">
              <Grid container>
                <Grid item lg={2} md={2} sm={2} xs={12}>
                  <div className="p-32 flex flex-center bg-light-gray flex-middle h-100">
                    <img
                      src="/assets/images/illustrations/posting_photo.svg"
                      alt=""
                    />
                  </div>
                </Grid>
                <Grid item lg={10} md={10} sm={10} xs={12} container spacing={2}>
                  <Grid item lg={12} md={12} sm={12} xs={12}>
                    <div className="pt-36">
                      <h4 className="text-center">{t('sign_up.title_form')}</h4>
                    </div>
                  </Grid>
                  <Grid item lg={12} md={12} sm={12} xs={12} container spacing={2}>
                    <Grid item lg={6} md={6} sm={6} xs={12}>
                      <TextValidator
                        className="w-100 mb-16"
                        label={<span><span style={{ color: "red" }}>*</span>{t('user.displayName')}</span>}
                        onChange={(displayName) =>
                          this.handleChange(displayName, 'displayName')
                        }
                        type="text"
                        name="name"
                        value={displayName}
                        validators={['required']}
                        errorMessages={['this field is required']}
                      />
                    </Grid>
                    <Grid item lg={6} md={6} sm={6} xs={12}>
                      <FormControl fullWidth={true}>
                        <InputLabel htmlFor="gender-simple">
                          {t('user.gender')}
                        </InputLabel>
                        <Select
                          value={gender}
                          onChange={(gender) => this.handleChange(gender, 'gender')}
                          inputProps={{
                            name: 'gender',
                            id: 'gender-simple',
                          }}
                        >
                          {this.listGender.map((item) => {
                            return (
                              <MenuItem key={item.id} value={item.id}>
                                {item.name}
                              </MenuItem>
                            )
                          })}
                        </Select>
                      </FormControl>
                    </Grid>
                    <Grid item lg={6} md={6} sm={6} xs={12}>
                      <TextValidator
                        className="w-100 mb-16"
                        label={<span><span style={{ color: "red" }}>*</span>{t('user.username')}</span>}
                        onChange={this.handleChange}
                        type="text"
                        name="userName"
                        value={userName}
                        validators={['required']}
                        errorMessages={['this field is required']}
                      />
                    </Grid>
                    <Grid item lg={6} md={6} sm={6} xs={12}>
                      <TextValidator
                        className="w-100 mb-16"
                        label={<span><span style={{color:"red"}}>*</span>{t('user.email')}</span>}
                        
                        onChange={this.handleChange}
                        type="email"
                        name="email"
                        value={email}
                        validators={['required', 'isEmail']}
                        errorMessages={[
                          'This field is required',
                          'Email is not valid',
                        ]}
                      />
                    </Grid>
                    <Grid item lg={6} md={6} sm={6} xs={12}>
                       <TextValidator
                        className="mb-16 w-100"
                        label={<span><span style={{color:"red"}}>*</span>{t('user.pass')}</span>}

                        // label={t('password')}
                        variant="outlined"
                        onChange={this.handleChange}
                        name="password"
                        type="password"
                        value={password}
                        validators={['required']}
                        errorMessages={['This field is required']}
                      />
                    </Grid>
                    <Grid item lg={6} md={6} sm={6} xs={12}>
                      <TextValidator
                        className="mb-16 w-100"
                        label={<span><span style={{color:"red"}}>*</span>{t('user.re_pass')}</span>}

                        // label={t('re_password')}
                        variant="outlined"
                        onChange={this.handleChange}
                        name="confirmPassword"
                        type="password"
                        value={confirmPassword}
                        validators={['required', 'isPasswordMatch']}
                        errorMessages={[
                          'This field is required',
                          'Password mismatch',
                        ]}
                      />
                    </Grid>
                    <Grid item lg={12} md={12} sm={12} xs={12}>
                      <div className="pl-36 pr-36 pb-36 h-100">
                        <div className="flex flex-middle">
                          <Button
                            className="capitalize"
                            variant="contained"
                            color="primary"
                            type="submit"
                          >
                            {t("sign_up.title")}
                          </Button>
                          <span className="ml-16 mr-8">{t("or")}</span>
                          <Button
                            className="capitalize"
                            onClick={() =>
                              this.props.history.push(ConstantList.ROOT_PATH + "session/signin")
                            }
                          >
                            {t("sign_in.title")}
                          </Button>
                        </div>
                      </div>
                    </Grid>
                  </Grid>
                </Grid>
              </Grid>
            </Card>
          </div>
        </div>
      </ValidatorForm>
    );
  }
}

const mapStateToProps = state => ({
  // setUser: PropTypes.func.isRequired
});

export default connect(
  mapStateToProps,
  {}
)(SignUp);
