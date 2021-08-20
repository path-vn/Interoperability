import React, { Component, Fragment } from "react";
import Highcharts from 'highcharts';

class PieChartGenderByAge extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [
                // {
                //     name: 'Tổng số bệnh nhân Lao',
                //     y: 40,
                // },
                {
                    name: '0-4',
                    y: this.props.analytics.listCountByAge ? this.countAge(this.props.analytics.listCountByAge).countInfant:0,
                    color: '#ff0000'
                },
                {
                    name: '5-14',
                    y: this.props.analytics.listCountByAge ? this.countAge(this.props.analytics.listCountByAge).countChild:0,
                    color: '#ffa500'
                },
                {
                    name: '15-24',
                    y: this.props.analytics.listCountByAge ? this.countAge(this.props.analytics.listCountByAge).countJuvenile:0,
                    color: '#ffff00'
                },
                {
                    name: '25-34',
                    y: this.props.analytics.listCountByAge ? this.countAge(this.props.analytics.listCountByAge).countYouth:0,
                    color: '#008000'
                },
                {
                    name: '35-44',
                    y: this.props.analytics.listCountByAge ? this.countAge(this.props.analytics.listCountByAge).countAdult:0,
                    color: '#0000ff'
                },
                {
                    name: '45-54',
                    y: this.props.analytics.listCountByAge ? this.countAge(this.props.analytics.listCountByAge).countMiddleAge:0,
                    color: '#4b0082'
                },
                {
                    name: '55-64',
                    y: this.props.analytics.listCountByAge ? this.countAge(this.props.analytics.listCountByAge).countElder:0,
                    color: '#ee82ee'
                },
                {
                    name: '65+',
                    y: this.props.analytics.listCountByAge ? this.countAge(this.props.analytics.listCountByAge).countOldPeople:0,
                    color: '#ff2d55'
                }
            ]
        }
    }


    countAge =(listData)=>{
        // debugger
        let listDataMale = [];
        let countInfant = 0;
        let countChild = 0;//5-14
        let countJuvenile = 0;//15-24
        let countYouth =0;//24-34
        let countAdult = 0;//35-44
        let countMiddleAge = 0;//45-54
        let countElder = 0;//55-64
        let countOldPeople = 0;//65+
        let count ={}
        if(listData && listData.length > 0){
            listData.forEach(a=>{
                if(a.age < 5){
                    countInfant += a.count;
                    
                }else if(5<= a.age && a.age < 15 ){
                    countChild += a.count;
                }else if(15<= a.age && a.age < 25){
                    countJuvenile += a.count;
                }else if(25<= a.age && a.age < 35){
                    countYouth += a.count;
                }else if(35<= a.age && a.age < 45){
                    countAdult += a.count;
                }else if(45<= a.age && a.age < 55){
                    countMiddleAge += a.count;
                }else if(55<= a.age && a.age < 65){
                    countElder += a.count;
                }else if(a.age > 65){
                    countOldPeople += a.count;
                }
            })
        }
        count.countInfant =countInfant
        count.countChild =countChild
        count.countJuvenile =countJuvenile
        count.countYouth =countYouth
        count.countAdult =countAdult
        count.countMiddleAge =countMiddleAge
        count.countElder =countElder
        count.countOldPeople =countOldPeople
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
                renderTo: 'ratio_genderByAge',
                options3d: {
                    enabled: true,
                    alpha: 45,
                    beta: 0
                }
            },
            credits: {
                enabled: false
            },
            legend: {
                shadow: false
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
                text: this.props.t("chart.patientRateByAge"),
                // opposite: true,
                style: {
                    fontSize: '14px',
                    fontFamily: 'Arial'
                }
            },
            tooltip: {
                shared: true,
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
            <div id="ratio_genderByAge">
            </div>
        );
    }
}
export default PieChartGenderByAge;