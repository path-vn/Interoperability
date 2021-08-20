import React, { Component, Fragment } from "react";
import Highcharts from "highcharts";
import moment from "moment";
import {
  getTotalTestedCases,
  getTotalRemainedCases,
} from "../AnalyticsService";

class MultiAxisLine extends React.Component {
  constructor(props) {
    super(props);
    // if()
    let { t } = this.props;
    this.state = {
      series: [
        // {
        //   name: "",
        //   data: this.props.listResults ? this.props.listResults : [],
        //   color: "red",
        // },
        // {
        //   name: "chart.tuberculosisHIV",
        //   data: this.props.listResultsHiv ? this.props.listResultsHiv : [],
        //   color: "blue"
        // },
        {
          color: "red",
          name: "Tổng số mẫu đã xét nghiệm trong ngày",
          data:
            this.props.listTotalCases && this.props.listTotalCases != []
              ? this.props.listTotalCases.map(function (item) {
                  return item["totalTestedCases"];
                })
              : [],
        },
        {
          color: "blue",
          name: "Tổng số mẫu xét nghiệm trong ngày",
          data:
            this.props.listTotalCases && this.props.listTotalCases != []
              ? this.props.listTotalCases.map(function (item) {
                  return item["totalRemainedCases"];
                })
              : [],
        },
        {
          color: "green",
          name: "Tổng số mẫu đã xét nghiệm",
          data:
            this.props.listTotalCases && this.props.listTotalCases != []
              ? this.props.listTotalCases.map(function (item) {
                  return item["totalAllTimeTestedCases"];
                })
              : [],
        },
        {
          color: "#e2f50f",
          name: "Tổng số mẫu xét nghiệm",
          data:
            this.props.listTotalCases && this.props.listTotalCases != []
              ? this.props.listTotalCases.map(function (item) {
                  return item["totalAllTimeRemainedCases"];
                })
              : [],
        },
      ],
    };
  }

  // dateUltil = () => {
  //   let fromDate = new Date(moment().subtract(10, "days").calendar());
  //   let itemDate = [];
  //   let iDate = "";
  //   for (let i = 1; i <= 10; i++) {
  //     iDate = moment(fromDate).add(i, "days").calendar();
  //     itemDate.push(moment(iDate).format("DD/MM/yyyy"));
  //   }
  //   this.setState({ itemDate: itemDate });
  // };

  highChartsRender() {
    var Highcharts = require("highcharts");

    require("highcharts/modules/exporting")(Highcharts);
    require("highcharts/highcharts-more")(Highcharts);
    require("highcharts/highcharts-3d")(Highcharts);
    Highcharts.chart({
      chart: {
        type: "spline",
        renderTo: "atmospheric-a",
      },
      title: {
        // verticalAlign: 'middle',
        // floating: true,
        text: this.props.sampleByTime,
        // style: {
        //   fontSize: '14px',
        //   fontFamily: 'Arial'
        // }
      },
      credits: {
        enabled: false,
      },
      xAxis: {
        categories:
          this.props.listTotalCases && this.props.listTotalCases != []
            ? this.props.listTotalCases.map(function (item) {
                return moment(item['sampleTestDate']).format("DD/MM/yyyy");
              })
            : [],
        max: 10,
        allowDecimals: false,
      },
      yAxis: {
        min: 0,
        title: {
          text: "",
        },
        allowDecimals: false,
      },
      plotOptions: {
        spline: {
          dataLabels: {
            format: "{point.name}: {point.percentage:.1f} %",
          },
          innerSize: "70%",
        },
      },
      series: this.state.series,
    });
  }
  componentWillMount() {
    // this.dateUltil();
  }
  componentDidMount() {
    let { analytics, categories, listResultsHiv, listResultChildren } =
      this.props;

    this.highChartsRender();
  }

  render() {
    return <div id="atmospheric-a"></div>;
  }
}
export default MultiAxisLine;
