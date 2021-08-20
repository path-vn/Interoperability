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

import { Breadcrumb, SimpleCard, EgretProgressBar } from "egret";
// import DashboardWelcomeCard from "../cards/DashboardWelcomeCard";
import AreaChart from "../charts/echarts/AreaChart";
import ConstantList from "./../../appConfig";
import { format } from "date-fns";
import ModifiedAreaChart from "./ModifiedAreaChart";
import { withStyles } from "@material-ui/styles";

class Analytics extends Component {
  state = {};

  // recenBuyerList = [
  //   {
  //     imgUrl: "/assets/images/face-1.jpg",
  //     name: "john doe",
  //     date: "18 january, 2019"
  //   },
  //   {
  //     imgUrl: "/assets/images/face-2.jpg",
  //     name: "kessy bryan",
  //     date: "10 january, 2019"
  //   },
  //   {
  //     imgUrl: "/assets/images/face-3.jpg",
  //     name: "james cassegne",
  //     date: "8 january, 2019"
  //   },
  //   {
  //     imgUrl: "/assets/images/face-4.jpg",
  //     name: "lucy brown",
  //     date: "1 january, 2019"
  //   }
  // ];

  render() {
    let { theme } = this.props;

    return (
      <div className="analytics m-sm-30">
        <div className="mb-sm-30">
          <Breadcrumb
            routeSegments={[
              { name: "Dashboard", path: "/dashboard" },
              { name: "Analytics" }
            ]}
          />
        </div>
        <Grid container spacing={3}>
          {/* <Grid item lg={6} md={6} sm={12} xs={12}>
            <DashboardWelcomeCard />
          </Grid> */}
          <Grid item lg={3} md={3} sm={12} xs={12}>
            <Card elevation={3} className="h-100">
              <div className="px-24 pt-20">
                <div className="card-title">Upload File</div>
                <div className="card-subtitle mb-24"></div>
                <Button
                  className="text-white"
                  variant="contained"
                  color="secondary"
                  href = {ConstantList.ROOT_PATH + "dashboard/upload_file"}
                >
                  Upload File
                </Button>
              </div>
              <AreaChart height="158px" color={[theme.palette.primary.main]} />
            </Card>
          </Grid>

          <Grid item lg={3} md={3} sm={12} xs={12}>
            <Card elevation={3} className="h-100">
              <div className="px-24 pt-20">
                <div className="card-title">Data Source</div>
                <div className="card-subtitle mb-24"></div>
                <Button
                  className="text-white"
                  variant="contained"
                  color="secondary"
                  href = {ConstantList.ROOT_PATH + "list/DataSource"}
                >
                  Data Source
                </Button>
              </div>
              <AreaChart
                height="158px"
                color={[theme.palette.primary.main]}
              />
            </Card>
          </Grid>
        </Grid>
      </div>
    );
  }
}

export default withStyles({}, { withTheme: true })(Analytics);
