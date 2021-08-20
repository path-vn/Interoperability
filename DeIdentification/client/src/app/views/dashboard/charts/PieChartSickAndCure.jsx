import React, { Component, Fragment } from "react";
import Highcharts from 'highcharts';

class PieChartSickAndCure extends React.Component {
    constructor(props) {
        super(props);
        let { t } = this.props
        this.state = {
            data: [
                {
                    name: t("chart.new"),
                    y: this.props.analytics.totalResultsNew ? this.props.analytics.totalResultsNew:0,
                    color: '#c9b397'
                },
                {
                    name: t("chart.relapse"),
                    y: this.props.analytics.totalResultsUpdate ? this.props.analytics.totalResultsUpdate:0,
                    color: '#773c4a'
                },
                {
                    name: t("chart.successfulTreatment"),
                    y: this.props.analytics.totalResultSsuccess ? this.props.analytics.totalResultSsuccess:0,
                    color: '#4e9a27'
                }
            ]
        }
    }

    count =(listData)=>{
        // debugger
        let count = 0;
        if(listData && listData.length > 0){
            listData.forEach(a=>{
                count += a.count 
            })
        }
       
        return count;
        
    }

    
    highChartsRender() {
        var Highcharts = require('highcharts');

        require('highcharts/modules/exporting')(Highcharts);
        require('highcharts/highcharts-more')(Highcharts);
        require('highcharts/highcharts-3d')(Highcharts);

        Highcharts.chart({
            chart: {
                type: 'pie',
                renderTo: 'ratio_sick_and_cure',
                options3d: {
                    enabled: true,
                    alpha: 45,
                    beta: 0
                }
            },
            credits: {
                enabled: false
            },
            plotOptions: {
                // pie: {
                //     allowPointSelect: true,
                //     cursor: 'pointer',
                //     showInLegend: true,
                //     dataLabels: {
                //         enabled: true,
                //         format: '<b>{point.name}</b><br>{point.percentage:.1f} %',
                //         distance: -50,
                //         filter: {
                //             property: 'percentage',
                //             operator: '>',
                //             value: 4
                //         }
                //     }
                // }
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    depth: 35,
                    showInLegend: true,
                    dataLabels: {
                        enabled: false,
                        format: '{point.name}'
                    }
                }
            },
            title: {
                text: this.props.t("chart.rateChartRelapseSuccessfulTreatment"),
                style: {
                    fontSize: '14px',
                    fontFamily: 'Arial'
                }
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            series: [{
                name: "Tỉ lệ ",
                data: this.state.data,
            }]
        });
    }

    componentDidMount() {
        this.highChartsRender();
    }

    render() {
        return (
            <div id="ratio_sick_and_cure">
            </div>
        );
    }
}
export default PieChartSickAndCure;