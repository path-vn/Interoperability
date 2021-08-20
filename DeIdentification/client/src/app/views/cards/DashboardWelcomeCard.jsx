import React from "react";
import { Card, Icon, Grid, Fab, withStyles } from "@material-ui/core";
import { Link } from "react-router-dom";

const styles = theme => ({
  root: {
    background: `url("/assets/images/dots.png"),
    linear-gradient(90deg, ${theme.palette.primary.main} -19.83%, ${theme.palette.primary.light} 189.85%)`,
    backgroundRepeat: "no-repeat",
    backgroundSize: "100%"
  }
});

const DashboardWelcomeCard = ({ classes, analytics, t }) => {
  return (
    <Grid container spacing={3}>
      {/* <Grid item lg={6} md={6} sm={6} xs={12}>
    <a href={"/fhir/organization"}  >
      <Card
        
        elevation={3}
        className={`p-16 py-28 text-center h-100  click ${classes.root}`}
      >
          <div className="font-weight-300 flex flex-space-between ">
            <div className="text-white margin-auto">
              <div className="font-size-32"><b>{analytics.assetNum}</b></div>
              <p className="uppercase bold m-0" ><b>Organization</b></p>
            </div>
          </div>
      </Card>
      </a>
    </Grid> */}
      <Grid item lg={4} md={4} sm={12} xs={12}>
        {/* <a href={"/fhir/FhirPatient"} > */}
        <Link to={"/fhir/FhirPatient"}>
          <Card
            elevation={3}
            className={`p-16 py-28 text-center h-100 click ${classes.root}`}
          >
            <div className="font-weight-300 flex flex-space-between">
              <div className="text-white margin-auto">
                <div className="font-size-32"><b>{analytics.allocationVoucherNum}</b></div>
                <p className="uppercase m-0"><b>Fhir Patient</b></p>
              </div>
            </div>

          </Card>
        </Link>

      </Grid>
      <Grid item lg={4} md={4} sm={12} xs={12}>
        {/* <a href={"/fhir/EsPatient"} > */}
        <Link to={"/fhir/EsPatient"}>
        <Card
            elevation={3}
            className={`p-16 py-28 text-center h-100 click ${classes.root}`}
          >
            <div className="font-weight-300 flex flex-space-between">
              <div className="text-white margin-auto">
                <div className="font-size-32"><b>{analytics.allocationVoucherNum}</b></div>
                <p className="uppercase m-0"><b>Es Patient</b></p>
              </div>
            </div>

          </Card>
        </Link>
          
        {/* </a> */}
      </Grid>
      <Grid item lg={4} md={4} sm={12} xs={12}>
        {/* <a href={"/fhir/deIdentification-config"} > */}
        <Link to={"/dashboard/deIdentification-config"}>
        <Card
            elevation={3}
            className={`p-16 py-28 text-center h-100 click ${classes.root}`}
          >
            <div className="font-weight-300 flex flex-space-between">
              <div className="text-white margin-auto">
                <div className="font-size-32"><b>{analytics.allocationVoucherNum}</b></div>
                <p className="uppercase m-0"><b>DeIdentification Config</b></p>
              </div>
            </div>

          </Card>
        </Link>
          
        {/* </a> */}
      </Grid>
      {/* <Grid item lg={3} md={3} sm={6} xs={12}>
    <a href={"list/Observation"} >
      <Card
        elevation={3}
        className={`p-16 py-28 text-center h-100 click ${classes.root}`}
      >
        
          <div className="font-weight-300 flex flex-space-between">
            <div className="text-white margin-auto">
              <div className="font-size-32"><b>{analytics.assetNum}</b></div>
              <p className="uppercase bold m-0"><b>Observation</b></p>
            </div>
          </div>
       
      </Card>
      </a>
    </Grid>
    <Grid item lg={3} md={3} sm={6} xs={12}>
    <a href={"/salary/salaryitem"} >
      <Card
        elevation={3}
        className={`p-16 py-28 text-center h-100 click ${classes.root}`}
      >
        <div className="font-weight-300 flex flex-space-between">
          <div className="text-white margin-auto">
            <div className="font-size-32"><b>{analytics.allocationVoucherNum}</b></div>
            <p className="uppercase m-0"><b>  Danh mục phần tử lương</b></p>
          </div>
        </div>
      </Card>
      </a>
    </Grid> */}
    </Grid>

  );
};

export default withStyles(styles, { withTheme: true })(DashboardWelcomeCard);
