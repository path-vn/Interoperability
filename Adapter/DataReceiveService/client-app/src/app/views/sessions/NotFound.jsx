import React, { Component } from "react";
import { Button } from "@material-ui/core";
import ConstantList from "../../appConfig";

class NotFound extends Component {
  state = {};
  render() {
    let {t} = this.props;
    return (
      <div className="flex flex-center flex-middle w-100 h-100vh">
        <div className="flex flex-column flex-center flex-middle" style={{ maxWidth: "320px" }}>
          <img className="mb-32" src="/assets/images/illustrations/404.svg" alt="" />
          <Button
            className="capitalize"
            variant="contained"
            color="primary"
            onClick={() => this.props.history.push(ConstantList.HOME_PAGE)}
          >
            {t('general.to_homepage')}
          </Button>
        </div>
      </div>
    );
  }
}

export default NotFound;
