import React, { Component, Fragment } from "react";
import Highcharts from 'highcharts';

class RadialBarChart extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      series: [{
        name: 'Số bệnh nhân đang điều trị',
        data: [533, 332, 243, 111]
      }, {
        name: 'Số bệnh nhân không điều trị',
        data: [223, 433, 332, 333]
      },
      {
        name: 'Bệnh nhân đến khám chưa tiếp nhận',
        data: [218, 149, 232, 224]
      }]
    }
  }

  highChartsRender() {
    Highcharts.setOptions({
      // colors: ['#058DC7', '#50B432', '#ED561B']
    });
    Highcharts.chart({
      chart: {
        type: 'column',
        inverted: true,
        polar: true,
        renderTo: 'radial-bar'
      },
      title: {
        text: ''
      },
      credits:{
        enabled: false
      },
      tooltip: {
        outside: true
      },
      pane: {
        size: '85%',
        innerSize: '20%',
        endAngle: 270
      },
      xAxis: {
        tickInterval: 1,
        labels: {
          align: 'right',
          useHTML: true,
          allowOverlap: false,
          step: 1,
          y: 3,
          style: {
            fontSize: '13px'
          }
        },
        lineWidth: 0,
        categories: [
          'Miền Bắc', 'Miền Trung', 'Miền Nam(TP.HCM)', 'Miền Nam(Cần Thơ)'
        ]
      },
      yAxis: {
        crosshair: {
          enabled: true,
          color: '#333'
        },
        title: {
          text: ''
        },
        lineWidth: 0,
        reversedStacks: true,
        endOnTick: true,
        showLastLabel: true
      },
      plotOptions: {
        column: {
          stacking: 'normal',
          borderWidth: 0,
          pointPadding: 0,
          groupPadding: 0.15
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
      <div id="radial-bar">
      </div>
    );
  }
}
export default RadialBarChart;