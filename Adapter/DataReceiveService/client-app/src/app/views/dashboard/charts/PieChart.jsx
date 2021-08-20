import React from "react";
import { render } from "react-dom";
// Import Highcharts
import Highcharts from "highcharts/highstock";
import variablePie from "highcharts/modules/variable-pie.js";
import HighchartsReact from "highcharts-react-official";
import highcharts3d from 'highcharts/highcharts-3d';
highcharts3d(Highcharts);

class PieChart extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {

        const options = {
            credits: {
                enabled: false
            },
            chart: {
                // plotBackgroundColor: null,
                // plotBorderWidth: null,
                // plotShadow: false,
                type: 'pie',
                options3d: {
                    enabled: true,
                    alpha: 45,
                    beta: 0
                }
            },
            legend: {
                layout: "horizontal",
                itemStyle: {
                    fontFamily: "sans-serif",
                    color: "#333333"
                }
            },
            accessibility: {
                point: {
                  valueSuffix: '%'
                }
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true,
                    depth: 35,
                }
            },
            title: {
                text: this.props.title,
                style: {"fontFamily": "sans-serif", "fontWeight": "bold"}
            },
            series: [
                {
                    type: 'pie',
                    innerSize: '50%',
                    minPointSize: 10,
                    zMin: 0,
                    name: this.props.t('Dashboard.chart.amount'),
                    data: this.props.data
                }
            ]
        };
        return (
            <div>
                <HighchartsReact
                    highcharts={Highcharts}
                    options={options}
                    // ref="chartComponent1"
                />
            </div>
        );
    }
}
export default (PieChart);
