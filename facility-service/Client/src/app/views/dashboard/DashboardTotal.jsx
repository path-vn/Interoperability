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

const DashboardTotalAllTime = ({ classes, totalType }) => {


  return (
    <Card
      elevation={3}
      className={`mb-20 text-center h-100 w-100 ${classes.root}`}
    >
      <Fab color="primary" className="mb-28">
        <Icon>equalizer</Icon>
      </Fab>
      <div className="mb-28 font-size-18 uppercase text-white">
       { totalType === "today"? "Hôm nay":"Toàn thời gian"}
      </div>
      <div className="font-weight-300 px-80 flex flex-space-between">
        <div className="text-white">
          <div className="font-size-32">180</div>
          <p className="uppercase m-0">Mẫu</p>
        </div>
        <div className="text-white">
          <div className="font-size-32">0</div>
          <p className="uppercase m-0">Dương tính</p>
        </div>
        <div className="text-white">
          <div className="font-size-32">0</div>
          <p className="uppercase m-0">Âm Tính</p>
        </div>
      </div>
    </Card>
  );
};

export default withStyles(styles, { withTheme: true })(DashboardTotalAllTime);
