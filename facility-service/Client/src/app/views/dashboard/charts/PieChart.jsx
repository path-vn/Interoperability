import React, { Component, Fragment } from "react";
import Highcharts from 'highcharts';
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


class PieChart extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            series: [{
                name: 'Tổng số Lao',
                data:  this.props.analytics.listResults  != []? this.props.analytics.listResults.map(function (item) {
                    return item["count"];}) : [],
                color: '#f2c60b'
            }, {
                name: 'Lao/HIV',
                data: this.props.analytics.listResultsHiv != [] ? this.props.analytics.listResultsHiv.map(function (item) {
                    return item["count"];}): [],
                    color: '#dc7967'
            }, {
                name: 'Lao trẻ em',
                data: this.props.analytics.listResultChildren != [] ? this.props.analytics.listResultChildren.map(function (item) {
                    return item["count"];}) : [],
                    color: '#5d58a5'
            },
           ]
        }
    }
    highChartsRender() {
        Highcharts.chart({
            chart: {
              type: 'bar',
              renderTo: 'atmospheric-composition'
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
            // xAxis: {
            //     categories: ['Apples', 'Oranges', 'Pears', 'Grapes', 'Bananas']
            // },
            xAxis: {
                categories:  this.props.analytics.listResults.map(function (item) {
                    return item["province"];})
            },
            yAxis:{
                title: {
                    text: ''
                  },
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
            <div id="atmospheric-composition">
            </div>
       	);
   	}
}
export default PieChart;