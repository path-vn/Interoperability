import React, { Component } from "react";
import {
  Grid,
  Card,
  Icon,
  IconButton,
  Button,
  FormControl,
  InputLabel,
  Input,
  InputAdornment,
  Select,
  MenuItem,
  TextField,
} from "@material-ui/core";
import SearchIcon from "@material-ui/icons/Search";
import { Helmet } from "react-helmet";
import { Breadcrumb, SimpleCard } from "egret";
import ReactEcharts from "echarts-for-react";
import { withStyles } from "@material-ui/styles";
import ConstantList from "../../appConfig";
import TotalRemainedCases from "./charts/TotalRemainedCases";
import Barchart from "./charts/Barchart";
import MultiAxisLine from "./charts/MultiAxisLine";
import authService from "../../services/jwtAuthService";
import moment from "moment";
import {
  getTotalTestedCases,
  getTotalRemainedCases,
  getListStatus,
  getTotalSampleByHealthOrg,
  getTotalGroupByHealthOrg,
  getTotalCases,
} from "./AnalyticsService";
import HighchartsSummarySampleTestingStatus from "./charts/HighchartsSummarySampleTestingStatus";
import Autocomplete from "@material-ui/lab/Autocomplete";
import { TextValidator } from "react-material-ui-form-validator";
import { isThisSecond } from "date-fns";
class Analytics extends Component {
  state = {
    listTotalToday: [],
    listTotalAllTime: [],
    totalToday: {},
    totalAllTime: {},
    epidemiologicalFactorsTotal: [],
    administrativeTotal: [],
    listStatus: null,
    listTotalSampleByHealthOrg: [],
    listTotalGroupByHealthOrg: [],
    listOrgByUserLogin: [],
    healthOrganizationId: "",
    isHealthOrgPopup: false,
    healthOrg: {
      name: "",
      id: "",
    },
  };

  componentWillMount() {
    let user = authService.getLoginUser();
    this.updatePageData();
  }

  updatePageData = () => {
    this.setState(
      {
        listTotalCases: [],
        listStatus: [],
        listTotalSampleByHealthOrg: [],
        listTotalGroupByHealthOrg: [],
      },
      () => {
        let date = new Date(moment().subtract(10, "days").calendar());
        let y = date.getFullYear();
        let M = date.getMonth();
        let d = date.getDate();
        let searchObject = {};
        console.log(date);
        // searchObject.sampleDateFrom = moment().subtract(10, "days").calendar();
        // searchObject.sampleDateTo = new Date(y, M, d, 0, 0, 0);
        searchObject.fromDate = new Date(y, M, d, 0, 0, 0);
        searchObject.toDate = new Date();
        searchObject.healthOrganizationId = this.state.healthOrganizationId;
        getTotalCases(searchObject).then(({ data }) => {
          this.setState({
            listTotalCases: data ? data : [],
          });
        });
        getListStatus(searchObject).then(({ data }) => {
          this.setState({
            listStatus: data ? data : null,
          });
        });
        getTotalSampleByHealthOrg(searchObject).then(({ data }) => {
          this.setState({
            listTotalSampleByHealthOrg: data ? data : [],
          });
        });
        getTotalGroupByHealthOrg(searchObject).then(({ data }) => {
          this.setState({
            listTotalGroupByHealthOrg: data ? data : [],
          });
        });
      }
    );
  };

  handleSelectHealthOrganization = (healthOrg) => {
    if (healthOrg && healthOrg.id) {
      this.setState(
        { healthOrg: healthOrg, healthOrganizationId: healthOrg.id },
        () => this.updatePageData()
      );
      this.handleClose();
    }
  };

  handleClearOrg = () => {
    this.setState({ healthOrg: {}, healthOrganizationId: "" }, () =>
      this.updatePageData()
    );
  };

  handleKeyDown = (e) => {
    if (e.key === "Enter") {
      this.updatePageData();
    }
  };
  handleChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value,
    });
  };

  // handleChange = (e) => {

  //   this.setState({ healthOrganizationId: e.target.value }, () =>
  //     this.updatePageData()
  //   );
  // };

  handleClose = () => {
    this.setState({ isHealthOrgPopup: false });
  };

  render() {
    let { theme, t, i18n } = this.props;
    let {
      listStatus,
      listTotalSampleByHealthOrg,
      listTotalGroupByHealthOrg,
      listTotalCases,
    } = this.state;
    let TitlePage = t("Dashboard.dashboard");
    return (
      <div className="analytics m-sm-30">
        <Helmet>
          <title>
            {TitlePage} | {t("web_site")}{" "}
          </title>
        </Helmet>
        <div className="mb-sm-30">
          <Breadcrumb
            routeSegments={[
              // { name: t("Dashboard.dashboard"), path: "/dashboard" },
              { name: t("Dashboard.dashboard") },
            ]}
          />
        </div>
        <Grid
          container
          spacing={2}
          justify="flex-start"
          style={{ marginBottom: "20px" }}
        >
          <Grid item lg={4} sm={6} xs={6}>
            <TextField
              label=""
              placeholder="Chọn đơn vị y tế"
              className="w-100"
              size="small"
              name="healthOrg"
              variant="outlined"
              value={
                this?.state?.healthOrg?.name ? this.state.healthOrg.name : ""
              }
              onChange={(e) => this.handleChange(e)}
              onKeyDown={(e) => this.handleKeyDown(e)}
            />

            {/* <FormControl fullWidth>
              <Input
                className="mt-16 search_box w-100 stylePlaceholder"
                type="text"
                name="healthOrg"
                variant="outlined"
                value={
                  this?.state?.healthOrg?.name ? this.state.healthOrg.name : ""
                }
                // onChange={this.handleSelectHealthOrganization}
                // placeholder={t("general.enterSearch")}
                id="search_box"
                startAdornment={
                  <InputAdornment>
                    <SearchIcon
                      onClick={() => this.setState({ isHealthOrgPopup: true })}
                      style={{
                        position: "absolute",
                        top: "0",
                        right: "0",
                      }}
                    />

                    <Button
                      className="btn-clear"
                      onClick={() => this.handleClearOrg()}
                    >
                      X
                    </Button>
                  </InputAdornment>
                }
              />
            </FormControl> */}

            {/* <FormControl fullWidth={true} variant="outlined" size="small">
              <InputLabel htmlFor="healthOrg-simple">
                {t("Đơn vị xét nghiệm")}
              </InputLabel>
              <Select
                value={
                  this.state.healthOrganizationId
                    ? this.state.healthOrganizationId
                    : ""
                }
                onChange={(e) => this.handleChange(e)}
                inputProps={{
                  name: "healthOrganizationId",
                  id: "healthOrganizationId-simple",
                }}
              >
                {this.state.listOrgByUserLogin.map((item) => {
                  return (
                    <MenuItem key={item.id} value={item.id}>
                      {item.name}
                    </MenuItem>
                  );
                })}
              </Select>
            </FormControl> */}
          </Grid>
          <Grid item lg={3} md={3} sm={4}>
            <Button
              variant="contained"
              color="primary"
              onClick={() => this.setState({ isHealthOrgPopup: true })}
            >
              <SearchIcon />
              Chọn
            </Button>
            <Button
              variant="contained"
              color="secondary"
              onClick={() => this.handleClearOrg()}
              style={{marginLeft:"10px"}}
            >
              Xóa lựa chọn
            </Button>
          </Grid>
        </Grid>
        <Grid container spacing={3}>
          <Grid item lg={6} md={6} sm={12} xs={12} className="div-parent-chart">
            {listTotalGroupByHealthOrg != null &&
              listTotalGroupByHealthOrg.length > 0 && (
                <TotalRemainedCases
                  data={
                    this?.state?.listTotalGroupByHealthOrg
                      ? this.state.listTotalGroupByHealthOrg
                      : null
                  }
                />
              )}
            {listTotalGroupByHealthOrg == null ||
              (listTotalGroupByHealthOrg.length == 0 && (
                <div className="div-child-chart">
                  <h4>Tổng các case cần xử lý</h4>
                  <p>Không có dữ liệu</p>
                </div>
              ))}
          </Grid>
          <Grid item lg={6} md={6} sm={12} xs={12} className="div-parent-chart">
            {listStatus && listStatus.length > 0 && (
              <HighchartsSummarySampleTestingStatus
                t={t}
                listStatus={listStatus}
              />
            )}
            {listStatus == null ||
              (listStatus.length == 0 && (
                <div className="div-child-chart">
                  <h4>{t("chart.highchartsSummarySampleTestingStatus")}</h4>
                  <h5>Không có dữ liệu</h5>
                </div>
              ))}
          </Grid>
          <Grid item lg={6} md={6} sm={12} xs={12} className="div-parent-chart">
            {listTotalSampleByHealthOrg != null &&
              listTotalSampleByHealthOrg.length > 0 && (
                <Barchart
                  data={
                    this?.state?.listTotalSampleByHealthOrg
                      ? this.state.listTotalSampleByHealthOrg
                      : null
                  }
                  t={t}
                  dataName={t("Dashboard.sample")}
                  barChartTitle={t("Dashboard.bar_chart_title")}
                />
              )}
            {listTotalSampleByHealthOrg == null ||
              (listTotalSampleByHealthOrg.length == 0 && (
                <div className="div-child-chart">
                  <h4>{t("Dashboard.sample")}</h4>
                  <h5>Không có dữ liệu</h5>
                </div>
              ))}
          </Grid>
          <Grid item lg={6} md={6} sm={12} xs={12} className="div-parent-chart">
            {listTotalCases != null && listTotalCases.length > 0 && (
              <MultiAxisLine
                listTotalCases={this.state.listTotalCases}
                sampleByTime={t("Dashboard.sampleByTime")}
              />
            )}
            {listTotalCases == null ||
              (listTotalCases.length == 0 && (
                <div className="div-child-chart">
                  <h4>{t("Dashboard.sampleByTime")}</h4>
                  <h5>Không có dữ liệu</h5>
                </div>
              ))}
          </Grid>
        </Grid>
      </div>
    );
  }
}
export default withStyles({}, { withTheme: true })(Analytics);
