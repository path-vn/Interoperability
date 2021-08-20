import React, { Component, Fragment } from "react";
import Highcharts from 'highcharts';

class BarTotal extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            series: [{
                name: 'Số bệnh nhân đang điều trị',
                data: [5, 3, 4,5]
            }, {
                name: 'Số bệnh nhân không điều trị',
                data: [2, 4, 3,3]
            },
            {
                name: 'Bệnh nhân đến khám chưa tiếp nhận',
                data: [1, 4, 3,4]
            }]
        }
    }

    highChartsRender() {
        Highcharts.chart({
            chart: {
              type: 'bar',
              renderTo: 'chart-total-patient'
            },
            title: {
              verticalAlign: 'middle',
              floating: true,
              text: '',
              style: {
                fontSize: '10px',
              }
            },
            credits: {
                enabled: false
            },
            xAxis: {
                categories: ['Miền Bắc', 'Miền Trung', 'Miền Nam (HCM)', 'Miền Nam (Cần Thơ)']
            },
            plotOptions: {
                spline: {
                dataLabels: {
                    format: '{point.name}: {point.percentage:.1f} %'
                },
                innerSize: '70%'
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
            <div id="chart-total-patient">
            </div>
       	);
   	}
}
export default BarTotal;