import React, { Component } from "react";
import { Breadcrumb } from "egret";

import { withStyles } from "@material-ui/styles";

const data = {
  labels: ['Red', 'Blue', 'Yellow', 'Green'],
  datasets: [
    {
      label: '# of Votes',
      data: [12, 19, 3, 5],
      backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(255, 206, 86, 0.2)',
        'rgba(75, 192, 192, 0.2)',

      ],
      borderColor: [
        'rgba(255, 99, 132, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)',
      ],
      borderWidth: 1,
    },
  ],
}
class Analytics extends Component {
  render() {
    const { t, i18n, theme } = this.props;
    const org = JSON.parse(localStorage.getItem('org'));
    //console.log(org.level)
    return (
      <div className="analytics m-sm-30">
        <div className="mb-sm-30">
          <Breadcrumb
            routeSegments={[
              { name: "Dashboard", path: "/dashboard" },
              { name: t("Dashboard.analytics.title") }
            ]}
          />
        </div>
        {/* {(org.level == 1) && <Grid container spacing={3}>
          {org.manager && <ManagerDashboard t={t} />}
          <Grid item lg={12} md={12} sm={12} xs={12} className="div-parent-chart">


          </Grid>

        </Grid>}
        {(org.level != 1) && <Grid container spacing={3}>
          {org.manager && <ManagerDashboard t={t} />}
          {org.checking && <CheckingDashboard t={t} />}
          {org.confirmation && <ConfirmationLab t={t} />}
          {org.screening && <ScreeningDashboard t={t} />}
          <Grid item lg={12} md={12} sm={12} xs={12} className="div-parent-chart">


          </Grid>

        </Grid>} */}
      </div>
    );
  }
}

export default withStyles({}, { withTheme: true })(Analytics);
