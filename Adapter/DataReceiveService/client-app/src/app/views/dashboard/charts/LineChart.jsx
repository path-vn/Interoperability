import React from "react";
import { render } from "react-dom";
// Import Highcharts
import Highcharts from "highcharts/highstock";
import HighchartsReact from "highcharts-react-official";
import highcharts3d from 'highcharts/highcharts-3d';
highcharts3d(Highcharts);

class LineChart extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {

        const options = {
            credits: {
                enabled: false
            },
            chart: {
                type: 'spline',

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
                // line: {
                //     dataLabels: {
                //         enabled: true
                //     },
                //     enableMouseTracking: true,
                // }
                spline: {
                    marker: {
                        radius: 4,
                        lineColor: '#666666',
                        lineWidth: 1
                    }
                }
            },
            xAxis: {
                categories: this.props.xAxis
            },
            yAxis: {
                title: {
                    text: this.props.t("Dashboard.chart.amount")
                }
            },
            title: {
                text: this.props.title,
                style: {"fontFamily": "sans-serif", "fontWeight": "bold"}
            },
            series: [
                {
                    name: this.props.t('Dashboard.chart.hiv_case'),
                    data: this.props.data,
                }
            ]
        };
        return (
            <div>
                <HighchartsReact
                    highcharts={Highcharts}
                    options={options}
                />
            </div>
        );
    }
}
export default (LineChart);
