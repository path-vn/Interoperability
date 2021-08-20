import React, { Component, Fragment } from "react";
import Highcharts from 'highcharts';
import { forEach } from "lodash-es";
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


class BarGender extends React.Component {
    constructor(props) {
        super(props);
        

        this.state = {
            series: [{
                name: 'Nữ',
                data: this.countAgeFemale(this.props.analytics.listCountByAge)
            }, {
                name: 'Nam',
                data: this.countAgeMale(this.props.analytics.listCountByAge)
            }],
            categories: [
                '0-4', '5-14', '15-24', '25-34',
                '35-44', '45-54', '55-64', '65+'
            ]
        }
    }


    countAgeMale =(listData)=>{
        // debugger
        let listDataMale = [];
        let countInfant = 0;
        let countChild = 0;
        let countJuvenile = 0;//15-24
        let countYouth =0;//24-34
        let countAdult = 0;//35-44
        let countMiddleAge = 0;//45-54
        let countElder = 0;//55-64
        let countOldPeople = 0;//65+
        if(listData && listData.length > 0){
            listData.forEach(a=>{
                if(a.gender == 1){
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
            }
            })
        }
        listDataMale.push(countInfant)
        listDataMale.push(countChild)
        listDataMale.push(countJuvenile)
        listDataMale.push(countYouth)
        listDataMale.push(countAdult)
        listDataMale.push(countMiddleAge)
        listDataMale.push(countElder)
        listDataMale.push(countOldPeople)
        return listDataMale;
        
    }

    //
    countAgeFemale =(listData)=>{
        
        let listDataFemale = [];
        let countInfant = 0;
        let countChild = 0;
        let countJuvenile = 0;//15-24
        let countYouth =0;//24-34
        let countAdult = 0;//35-44
        let countMiddleAge = 0;//45-54
        let countElder = 0;//55-64
        let countOldPeople = 0;//65+
        if(listData && listData.length > 0){
            // listData.forEach(()=>{})
            
            listData.forEach((a)=>{
                if(a.gender == 0){
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
            }
            })
        }
        listDataFemale.push(-countInfant)
        listDataFemale.push(-countChild)
        listDataFemale.push(-countJuvenile)
        listDataFemale.push(-countYouth)
        listDataFemale.push(-countAdult)
        listDataFemale.push(-countMiddleAge)
        listDataFemale.push(-countElder)
        listDataFemale.push(-countOldPeople)
        return listDataFemale;
    }

    highChartsRender() {
        Highcharts.chart({
            chart: {
                type: 'bar',
                renderTo: 'atmospheric-g'
            },
            credits: {
                enabled: false
            },
            title: {
                text: ' '
            },
            // subtitle: {
            //     text: 'Source: <a href="http://populationpyramid.net/germany/2018/">Population Pyramids of the World from 1950 to 2100</a>'
            // },
            accessibility: {
                point: {
                    valueDescriptionFormat: '{index}. Age {xDescription}, {value}%.'
                }
            },
            xAxis: [{
                categories: this.state.categories,
                reversed: false,
                labels: {
                    step: 1
                },
                accessibility: {
                    description: 'Tuổi (Nữ)'
                }
            }, { // mirror axis on right side
                opposite: true,
                reversed: false,
                categories: this.state.categories,
                linkedTo: 0,
                labels: {
                    step: 1
                },
                accessibility: {
                    description: 'Tuổi (Nam)'
                }
            }],
            yAxis: {
                title: {
                    text: null
                },
                labels: {
                    formatter: function () {
                        return Math.abs(this.value);
                    }
                },
                accessibility: {
                    description: 'Percentage population',
                    rangeDescription: 'Range: 0 to 5%'
                }
            },

            plotOptions: {
                series: {
                    stacking: 'normal'
                }
            },
            // tooltip: {
            //     formatter: function () {
            //         return '<b>' + this.series.name + ', age ' + this.point.category + '</b><br/>' +
            //             'Population: ' + Highcharts.numberFormat(Math.abs(this.point.y), 1) + '%';
            //     }
            // },

            series: this.state.series
        });
    }

    componentDidMount() {
        this.highChartsRender();
    }

    render() {
        return (
            <div id="atmospheric-g">
            </div>
        );
    }
}
export default BarGender;