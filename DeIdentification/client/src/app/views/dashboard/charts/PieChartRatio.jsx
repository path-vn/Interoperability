import React, { Component, Fragment } from "react";
import Highcharts from 'highcharts';

class PieChartRatio extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [
                {
                    name: 'Miền Bắc',
                    y: 15.5,
                },
                {
                    name: 'Miền Trung',
                    y: 39.2,
                },
                {
                    name: 'Miền Nam(TP.HCM)',
                    y: 19.3,
                },
                {
                    name: 'Miền Nam(Cần Thơ)',
                    y: 26,
                }
            ]
        }
    }

    highChartsRender() {
        Highcharts.setOptions({
            // colors: ['#058DC7', '#50B432', '#ED561B','#FF3300']
        });
        Highcharts.chart({
            chart: {
                type: 'pie',
                renderTo: 'ratio',
            },
            credits: {
                enabled: false
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b><br>{point.percentage:.1f} %',
                        distance: -50,
                        filter: {
                            property: 'percentage',
                            operator: '>',
                            value: 4
                        }
                    },
                    size: 280
                }
            },
            title: {
                verticalAlign: 'middle',
                floating: true,
                text: '',
                style: {
                    fontSize: '10px',
                }
            },
            tooltip: {
                valueSuffix: '%',
            },
            series: [{
                name: "Tỉ lệ ca mắc theo vùng",
                data: this.state.data,
            }]
        });
    }

    componentDidMount() {
        this.highChartsRender();
    }

    render() {
        return (
            <div id="ratio">
            </div>
        );
    }
}
export default PieChartRatio;