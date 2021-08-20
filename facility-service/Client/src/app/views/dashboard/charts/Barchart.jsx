import React, { Component, Fragment } from "react";
import Highcharts from "highcharts";

class Barchart extends React.Component {
  constructor(props) {
    super(props);
    let { t } = this.props;
    this.state = {
      series: [
        {
          name: this.props.dataName,
          color: "rgb(255, 72, 0)",
          data: this?.props?.data ? this.props.data.map(function (item) {
            return item["totalSample"];
          }) : [],
        //   pointPadding: 0.1,
        //   pointPlacement: -0.1,
        //   pointWidth: 16,
        },
      ],
      chart: {
        type: "bar",
        renderTo: "atmospheric-c",
        scrollablePlotArea: {
          minHeight: "400px",
          scrollPositionY: 0,
        },
      },
    };
  }

  highChartsRender(categories) {
    Highcharts.chart({
      chart: this.state.chart,
      title: {
        text: this.props.barChartTitle,
      },
      xAxis: {
        categories: categories,
      },
      yAxis: {
        title: {
          text: " ",
        },
      },
      legend: {
        shadow: false,
      },
      tooltip: {
        shared: true,
      },
      plotOptions: {},
      credits: {
        enabled: false,
      },
      accessibility: {
        announceNewData: {
          enabled: true,
        },
      },
      credits:{
        enabled: false,
      },

      series: this.state.series,
    });
  }

  componentDidMount() {
    let { data } = this.props;
    let categories = [];
    if (data != null && data.length > 0) {
      categories = data ? data.map(function (item) {
        return item["name"];
      }) : [];
    }
    this.highChartsRender(categories);
  }

  render() {
    return <div id="atmospheric-c"></div>;
  }
}
export default Barchart;
