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


class MultiAxisLineSickAndCure extends React.Component {
  constructor(props) {
    super(props);
    let { t} = this.props
    this.state = {
      series: [
        {
          name: t("chart.new"),
          data: this.props.listResultsNew,
          color: "red"
        },
        {
          name: t("chart.relapse"),
          data: this.props.listResultsUpdate,
          // dashStyle: 'ShortDashDot',
          color: "blue"
        },
        {
          name: t("chart.successfulTreatment"),
          data: this.props.listResultSsuccess,
          // dashStyle: 'ShortDashDot',
          color: "#00FF00"
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
        renderTo: 'atmospheric-sick-and-cure'
      },
      title: {
        // verticalAlign: 'middle',
        // floating: true,
        text: this.props.t("chart.indicatorsRelapsesSuccessfulTreatment"),
        style: {
          fontSize: '14px',
          fontFamily: "Arial"
        }
      },
      credits: {
        enabled: false
      },
      xAxis: {
        // categories: [0,1,2,3,4,5,6],
        // allowDecimals: false,
        //   accessibility: {
        //     rangeDescription: 'Range: 2010 to 2018'
        // }
        categories: this.props.categories
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
        series: {

          // pointStart: 2010
        }
      },
      series: this.state.series
    });
  }

  componentDidMount() {


    this.highChartsRender();
  }

  render() {
    return (
      <div id="atmospheric-sick-and-cure">
      </div>
    );
  }
}
export default MultiAxisLineSickAndCure;