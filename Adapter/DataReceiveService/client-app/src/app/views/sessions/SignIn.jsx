import React, { Component } from "react";
import ConstantList from "../../appConfig";
import {
  Card,
  Checkbox,
  InputAdornment,
  Input,
  FormControlLabel,
  Grid,
  Button,
  withStyles,
  CircularProgress,
  FormControl
} from "@material-ui/core";
import { ValidatorForm } from "react-material-ui-form-validator";
import { connect } from "react-redux";
import { PropTypes } from "prop-types";
import { withRouter } from "react-router-dom";
import { loginWithEmailAndPassword } from "../../redux/actions/LoginActions";
import PersonIcon from '@material-ui/icons/Person';
import VpnKeyIcon from '@material-ui/icons/VpnKey';
import './SignIn.scss';

const styles = theme => ({
  wrapper: {
    position: "relative"
  },

  buttonProgress: {
    position: "absolute",
    top: "50%",
    left: "50%",
    marginTop: -12,
    marginLeft: -12
  }
});

class SignIn extends Component {
  state = {
    email: "",
    password: "",
    agreement: ""
  };
  handleChange = event => {
    event.persist();
    this.setState({
      [event.target.name]: event.target.value
    });
  };
  handleFormSubmit = event => {
    this.props.loginWithEmailAndPassword({ ...this.state });
  };
  render() {
    const { t, i18n } = this.props;
    let { email, password } = this.state;
    let { classes, rememberAccount } = this.props;
    return (
      <div className="signup flex w-100 h-100vh">
        <Grid container >
          <Grid className="" item lg={12} md={12} sm={12} xs={12}>
            <Card className="signup-card position-relative y-center h-100vh">
            <Grid className="login-right-side" item lg={6} md={6} sm={12} xs={12} >
                <img src={ConstantList.ROOT_PATH + "assets/images/background/login-bg-1.png"} alt="" />
              </Grid>
              <Grid className="login-left-side" item lg={6} md={6} sm={12} xs={12}>
                <Grid item md={8} sm={8} xs={10} style={{ margin: 'auto', textAlign: 'center' }}>
                  <h2>{t('sign_in.title')}</h2>
                  <div className="p-36 h-100 position-relative">
                    <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
                      <Grid
                        className="mb-24 flex login-user-input"
                        alignItems='center'
                      >
                        <div class='w-100'>
                          <FormControl
                            fullWidth
                            required='true'

                          >
                            <Input
                              className=" w-100"
                              onChange={this.handleChange}
                              placeholder={t('user.username')}
                              type="text"
                              name="email"
                              variant="outlined"

                              startAdornment={
                                <InputAdornment>
                                  <PersonIcon style={{ color: '#2a80c8' }} />
                                </InputAdornment>
                              }
                            />
                          </FormControl>

                        </div>

                      </Grid>
                      <Grid
                        className='flex login-password-input'
                        alignItems='center'
                      >
                        <div class='w-100'>
                          <FormControl
                            fullWidth
                            required='true'
                          >
                            <Input
                              className=" w-100"
                              onChange={this.handleChange}
                              placeholder={t('user.password')}
                              type="password"
                              name="password"
                              variant="outlined"
                              value={password}

                              startAdornment={
                                <InputAdornment>
                                  <VpnKeyIcon style={{ color: '#2a80c8' }} />
                                </InputAdornment>
                              }
                            />
                          </FormControl>
                        </div>
                      </Grid>

                      <Grid className='mb-24' container xs={12} sm={12} md={12}>
                        <FormControlLabel
                          value={rememberAccount}
                          control={
                            <Checkbox
                              id="rememberAccount"
                              className="remember-account"
                              checked={rememberAccount}
                              onChange={this.rememberAccount}
                              name="rememberAccount"
                              value={rememberAccount}
                              inputProps={{
                                "aria-label":
                                  "primary checkbox",
                              }}
                            />
                          }
                          label={t("user.remember_me")}
                          labelPlacement="end"
                        />
                      </Grid>

                      <div className="flex flex-middle mb-8">
                        <div className={classes.wrapper} style={{ width: "100%" }}>
                          <Button
                            variant="contained"
                            color="primary"
                            disabled={this.props.login.loading}
                            type="submit"
                          >
                            {t("sign_in.title")}
                          </Button>
                          {this.props.login.loading && (
                            <CircularProgress
                              size={24}
                              className={classes.buttonProgress}
                            />
                          )}
                        </div>
                      </div>
                    </ValidatorForm>
                  </div>
                </Grid>
              </Grid>
              
            </Card>
          </Grid>
        </Grid>

      </div>

    );
  }
}

const mapStateToProps = state => ({
  loginWithEmailAndPassword: PropTypes.func.isRequired,
  login: state.login
});
export default withStyles(styles, { withTheme: true })(
  withRouter(
    connect(
      mapStateToProps,
      { loginWithEmailAndPassword }
    )(SignIn)
  )
);
