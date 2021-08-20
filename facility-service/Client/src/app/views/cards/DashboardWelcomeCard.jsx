import React from "react";
import { Card, Icon, Fab, withStyles } from "@material-ui/core";

const styles = theme => ({
  root: {
    background: `url("/assets/images/dots.png"),
    linear-gradient(90deg, ${theme.palette.primary.main} -19.83%, ${theme.palette.primary.light} 189.85%)`,
    backgroundRepeat: "no-repeat",
    backgroundSize: "100%"
  }
});

const DashboardWelcomeCard = ({ classes }) => {
  return (
    <Card
      elevation={3}
      className={`p-16 py-28 text-center h-100 w-100 ${classes.root}`}
    >
      <Fab color="primary" className="mb-28">
        <Icon>trending_up</Icon>
      </Fab>
      <div className="mb-38 font-size-18 uppercase text-white">
        welcome back! Watson
      </div>
      <div className="font-weight-300 px-80 flex flex-space-between">
        <div className="text-white">
          <div className="font-size-32">180</div>
          <p className="uppercase m-0">sales</p>
        </div>
        <div className="text-white">
          <div className="font-size-32">220</div>
          <p className="uppercase m-0">signups</p>
        </div>
        <div className="text-white">
          <div className="font-size-32">300</div>
          <p className="uppercase m-0">visitors</p>
        </div>
      </div>
    </Card>
  );
};

export default withStyles(styles, { withTheme: true })(DashboardWelcomeCard);
