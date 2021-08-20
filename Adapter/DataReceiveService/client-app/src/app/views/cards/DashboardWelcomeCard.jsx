import React, { Component, Fragment } from "react";
import {
  Grid,
  Card,
  Icon,
  IconButton,
  Button,
  Checkbox,
  Fab,
  Avatar,
  Hidden
} from "@material-ui/core";
import 'styles/globitsStyles.css';

const DashboardWelcomeCard = ({ t }) => {
  return (
    <Grid container spacing={3}>
      <Grid item lg={4} md={4} sm={6} xs={12}>
        <Card elevation={3} className="bgc-primary-d1 p-16 py-32 text-center h-100 click">
          <div className="text-white margin-auto">
            <div className="card-title text-white">369</div>
            <div className="uppercase m-0">{t("Dashboard.analytics.total_patients")}</div>
          </div>
        </Card>
      </Grid>
      <Grid item lg={4} md={4} sm={6} xs={12}>
        <Card elevation={3} className="bgc-green-d1 p-16 py-32 text-center h-100 click">
          <div className="text-white margin-auto">
            <div className="card-title text-white">99</div>
            <div className="uppercase m-0">{t("Dashboard.analytics.patients_confirm_positive")}</div>
          </div>
        </Card>
      </Grid>
      <Grid item lg={4} md={4} sm={6} xs={12}>
          <Card elevation={3} className="bgc-danger-tp1 p-16 py-32 text-center h-100 click">
            <div className="text-white margin-auto">
              <div className="card-title text-white">270</div>
              <div className="uppercase m-0">{t("Dashboard.analytics.patients_on_arv")}</div>
            </div>
          </Card>
      </Grid>

    </Grid>
  
  );
};

export default DashboardWelcomeCard;
