import React, { Component, Fragment } from "react";
import Highcharts from 'highcharts';

class PieChartHIVChildren extends React.Component {
    constructor(props) {
        super(props);
        let { t } = this.props
        this.state = {
            data: [
                // {
                //     name: 'Tổng số bệnh nhân Lao',
                //     y: 40,
                // },
                {
                    name: t("chart.tuberculosisHIV"),
                    y: this.props.analytics.totalResultsHiv ? this.props.analytics.totalResultsHiv:0,
                    color: '#531f66'
                },
                {
                    name: t("chart.pediatricTuberculosisPatients"),
                    y: this.props.analytics.totalResultChildren ? this.props.analytics.totalResultChildren:0,
                    color: '#5d58a5'
                }
            ]
        }
    }


    countHIV =(listData)=>{
        // debugger
        let count = 0;
        if(listData && listData.length > 0){
            listData.forEach(a=>{
                count += a.count 
            })
        }
       
        return count;
        
    }

    countChilderen =(listData)=>{
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
                renderTo: 'ratio_hiv_children',
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
                    showInLegend: true,
                    depth: 35,
                    dataLabels: {
                        enabled: false,
                        format: '{point.name}'
                    }
                }
            },
            title: {
                text: this.props.t("chart.tuberculosisHIVPatientsChildTuberculosis"),
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
            <div id="ratio_hiv_children">
            </div>
        );
    }
}
export default PieChartHIVChildren;