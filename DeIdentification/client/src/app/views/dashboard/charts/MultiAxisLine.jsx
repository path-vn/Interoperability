import React, { Component, Fragment } from "react";
import Highcharts from 'highcharts';
import moment from "moment";
// const data = {
// //   labels: ['Red', 'Blue', 'Yellow', 'Green'],
//   datasets: [
//     {
//       label: '# of Votes',
//       data: [12, 19, 3, 5],
//       backgroundColor: [
//         'rgba(255, 99, 132, 0.2)',
//         'rgba(54, 162, 235, 0.2)',
//         'rgba(255, 206, 86, 0.2)',
//         'rgba(75, 192, 192, 0.2)',

//       ],
//       borderColor: [
//         'rgba(255, 99, 132, 1)',
//         'rgba(54, 162, 235, 1)',
//         'rgba(255, 206, 86, 1)',
//         'rgba(75, 192, 192, 1)',
//       ],
//       borderWidth: 1,
//     },
//   ],
// }


class MultiAxisLine extends React.Component {
  constructor(props) {
    super(props);
    // console.log(this.props.listResultsHiv)
    let { t } = this.props
    this.state = {
      series: [
        {
          name: t("chart.totalNumberOfTBPatients"),
          data: this.props.listResults ? this.props.listResults : [],
          color: "red",
        },
        {
          name: t("chart.tuberculosisHIV"),
          data: this.props.listResultsHiv ? this.props.listResultsHiv : [],
          color: "blue"
        },
        {
          name: t("chart.pediatricTuberculosisPatients"),
          data: this.props.listResultChildren,
          color: "green"
        },

      ],
    }
  }

  highChartsRender() {
    var Highcharts = require('highcharts');

    require('highcharts/modules/exporting')(Highcharts);
    require('highcharts/highcharts-more')(Highcharts);
    require('highcharts/highcharts-3d')(Highcharts);
    Highcharts.chart({
      chart: {
        type: 'spline',
        renderTo: 'atmospheric-a'
      },
      title: {
        // verticalAlign: 'middle',
        // floating: true,
        text: this.props.t("chart.tuberculosisHIVPatientsChildTuberculosis"),
        style: {
          fontSize: '14px',
          fontFamily: 'Arial'
        }
      },
      credits: {
        enabled: false
      },
      xAxis: {
        categories: this.props.categories ? this.props.categories : [],
        allowDecimals: false,
      },
      yAxis: {
        min: 0,
        title: {
          text: ''
        },
        allowDecimals: false,
      },
      plotOptions: {
        spline: {
          dataLabels: {
            format: '{point.name}: {point.percentage:.1f} %'
          },
          innerSize: '70%'
        },
      },
      series: this.state.series
    });
  }
  componentWillMount() {
  }
  componentDidMount() {
    let { analytics, categories, listResultsHiv, listResultChildren } = this.props;

    this.highChartsRender();
  }

  render() {
    return (
      <div id="atmospheric-a">
      </div>
    );
  }
}
export default MultiAxisLine;