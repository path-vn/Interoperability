import {
  Grid
} from "@material-ui/core";
import { withStyles } from "@material-ui/styles";
import { Breadcrumb } from "egret";
import React, { Component } from "react";
import { Helmet } from 'react-helmet';
import { getCurrentUser } from "../User/UserService";
import DashboardWelcomeCard from "../cards/DashboardWelcomeCard";
class Dashboard1 extends Component {
  state = {analytics: {}};

  handleChangeDate = (date, name) => {
    this.setState({ [name]: date }, () => {
      // this.updatePageData();
    });
  };

  handleKeyDownEnterSearch = e => {
    if (e.key === 'Enter') {
      this.updatePageData();
    }
  };

  changeSelected = (value, type) => {
   
  };
  updatePageData = () => {
  };

  componentDidMount() {

    this.updatePageData();
  }
  render() {
    const { t, i18n } = this.props;
    let TitlePage = t('Dashboard.dashboard');
    let {analytics} = this.state
    return (
      <div className="analytics m-sm-30">
        <div className="mb-sm-30">
          <Helmet>
            <title>{TitlePage} | {t("web_site")}</title>
          </Helmet>
          <Breadcrumb routeSegments={[{ name: TitlePage }]} />
        </div>
        <Grid container spacing={3} justify="space-between">
        <Grid item sm={12} xs={12}>
            <DashboardWelcomeCard  t={t} analytics={analytics} />
          </Grid>
        </Grid>
      </div>
    );
  }
}

export default withStyles({}, { withTheme: true })(Dashboard1);
