import React, { Component, Fragment } from "react";
import Highcharts from 'highcharts';

class PieChartGender extends React.Component {
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
                    name: t("chart.female"),
                    y: this.props.analytics.listCountByAge ? this.countAgeFemale(this.props.analytics.listCountByAge):0,
                    color: '#f2c60b'
                },
                {
                    name: t("chart.male"),
                    y: this.props.analytics.listCountByAge ? this.countAgeMale(this.props.analytics.listCountByAge):0,
                    color: '#dc7967'
                }
            ]
        }
    }


    countAgeMale =(listData)=>{
        // debugger
        let count = 0;
 
        if(listData && listData.length > 0){
            listData.forEach(a=>{
                if(a.gender == 1){
                    count += a.count
            }
            })
        }

        return count;
        
    }

    //
    countAgeFemale =(listData)=>{
        let count = 0
        
        if(listData && listData.length > 0){
            // listData.forEach(()=>{})
            
            listData.forEach((a)=>{
                if(a.gender == 0){
                    count += a.count
            }
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
                renderTo: 'ratio_gender',
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
                // verticalAlign: 'middle',
                // floating: true,
                text: this.props.t("chart.patientIndexByGenderChart"),
                style: {
                    fontSize: '14px',
                    fontFamily: 'Arial'
                }
            },
            tooltip: {
                // valueSuffix: '%',
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
            <div id="ratio_gender">
            </div>
        );
    }
}
export default PieChartGender;