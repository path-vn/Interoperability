import React, { Component, Fragment } from "react";
import Highcharts from 'highcharts';
var createReactClass = require("create-react-class");
var HighchartsReact = createReactClass({
    componentDidMount: function () {
        var p = this.props;
        var highcharts = p.highcharts || window.Highcharts;
        var constructorType = p.constructorType || "chart";
        // Create chart
        this.chart = highcharts[constructorType](
            this.container,
            Object.assign({}, p.options)
        );
    },

    shouldComponentUpdate: function (nextProps, nextState) {
        var update = this.props.update;
        // Update if not specified or set to true
        return typeof update === "undefined" || update;
    },

    componentDidUpdate: function () {
        this.chart.update(
            Object.assign({}, this.props.options),
            true,
            !(this.props.oneToOne === false)
        );
    },

    componentWillReceiveProps: function () {
        if (this.props.update) {
            this.chart.update(
                Object.assign({}, this.props.options),
                true,
                !(this.props.oneToOne === false)
            );
        }
    },

    componentWillUnmount: function () {
        // Destroy chart
        this.chart.destroy();
    },

    render: function () {
        var self = this;
        var containerProps = this.props.containerProps || {};

        // Add ref to div props
        containerProps.ref = function (container) {
            self.container = container;
        };

        // Create container for our chart
        return React.createElement("div", containerProps);
    }
});

const optionDefaults = {
    chart: {
        type: 'column',
        renderTo: 'total-remain-cases',
    },
    title: {
        text: "Tổng các trường hợp cần xử lý"
    },
    xAxis: {
        categories: []
    },
    yAxis: {
        min: 0,
        title: {
            text: 'Total'
        },
        stackLabels: {
            enabled: true,
            style: {
                fontWeight: 'bold',
                color: ( // theme
                    Highcharts.defaultOptions.title.style &&
                    Highcharts.defaultOptions.title.style.color
                ) || 'gray'
            }
        }
    },
    legend: {
        align: 'right',
        x: -30,
        verticalAlign: 'top',
        y: 25,
        floating: true,
        backgroundColor:
            Highcharts.defaultOptions.legend.backgroundColor || 'white',
        borderColor: '#CCC',
        borderWidth: 1,
        shadow: false
    },
    tooltip: {
        headerFormat: '<b>{point.x}</b><br/>',
        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
    },
    plotOptions: {
        column: {
            stacking: 'normal',
            dataLabels: {
                enabled: true
            }
        }
    },
    credits: {
        enabled: false
    },
    series: [{
        name: 'Đã xét nghiệm',
        data: []
    }, {
        name: 'Chưa xét nghiệm',
        data: []
    }]
}
class TotalRemainedCases extends React.Component {
    constructor(props) {
        super(props);
        this.state = { options: optionDefaults };
    }

    static getDerivedStateFromProps(props, state) {
        let options = state.options;
        let { data } = props;
        let listTotalTested = [];
        let listTotalRemained = [];
        let listHealthOrg = [];
        if (data != null && data.length > 0) {

            listHealthOrg = data.map(function (item) {
                return item["name"];
            });
            listTotalRemained = data.map(function (item) {
                return item["totalRemainedCases"];
            });
            listTotalTested = data.map(function (item) {
                return item["totalCases"] - item["totalRemainedCases"];
            });
        }
        if (listHealthOrg && listHealthOrg.length > 0) {
            options.xAxis.categories = listHealthOrg;
            options.series[0].data = listTotalTested;
            options.series[1].data = listTotalRemained;
        }
        return { options: options};
    }

    render() {
        let { options } = this.state;
        return (
            <HighchartsReact
                highcharts={Highcharts}
                constructorType={"chart"}
                options={options}
                update={false}
            />
        );
    }
}
export default TotalRemainedCases;