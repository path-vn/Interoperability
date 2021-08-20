import React, { Component, Fragment } from "react";
import Highcharts from "highcharts";

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
  },
});

const options = {
  title: {
    text: "",
  },
  chart: {
    events: {
      redraw() {},
    },
  },
  series: [
    {
      data: [],
      //keys: ["y", "name"],
      type: "pie",
    },
  ],
  plotOptions: {
    pie: {
      allowPointSelect: true,
      cursor: "pointer",
      dataLabels: {
        enabled: true,
        format: "<b>{point.namevi}</b>: {point.percentage:.1f} %",
      },
    },
  },
  tooltip: {
    pointFormat: "Total: {point.y}",
  },

  credits: {
    enabled: false,
  },
};

class HighchartsSummarySampleTestingStatus extends React.Component {
  constructor(props) {
    super(props);
    this.state = { isHidden: false };
    var listData = [];
    // if (!this.props.listStatus) {
    //     this.props = {
    //         listStatus :[
    //             {
    //                 name: "Draft",
    //                 count: 10
    //             },
    //             {
    //                 name: "Pending",
    //                 count: 22
    //             },
    //             {
    //                 name: "Accepted",
    //                 count: 33
    //             },
    //             {
    //                 name: "Checking",
    //                 count: 44
    //             },
    //             {
    //                 name: "Positive",
    //                 count: 15
    //             },
    //             {
    //                 name: "Negative",
    //                 count: 66
    //             },
    //             {
    //                 name: "Rejected",
    //                 count: 5
    //             },
    //             {
    //                 name: "Canceled",
    //                 count: 8
    //             }
    //         ]
    //     };
    // }

    if (this.props.listStatus) {
      this.props.listStatus.map((data, i) => {
        var newData = { name: data.name, y: data.count };
        if (data.name == "Positive") {
          newData.color = "#F7043B";
          newData.name = newData.namevi = "Dương tính";
        } else if (data.name == "Negative") {
          newData.color = "#007DED";
          newData.name = newData.namevi = "Âm tính";
        } else if (data.name == "Pending") {
          newData.color = "#f2c60b";
          newData.name = newData.namevi = "Chờ xử lý";
        } else if (data.name == "Accepted") {
          newData.color = "#05b514";
          newData.name = newData.namevi = "Đã được chấp nhận";
        } else if (data.name == "Checking") {
          //   newData.color = "#f2c60b";
          newData.name = newData.namevi = "Dương tính chờ xác nhận";
        } else if (data.name == "Rejected") {
          newData.color = "#ff0000";
          newData.name = newData.namevi = "Mẫu không thể sử dụng";
        } else if (data.name == "Draft") {
          newData.color = "#827676";
          newData.name = newData.namevi = "Bản nháp";
        } else if (data.name == "Canceled") {
          newData.color = "#09120a";
          newData.name = newData.namevi = "Mẫu bị hủy";
        } else if (data.name == null) {
            newData.name = newData.namevi = "";
            newData.y= null
        }

        listData.push(newData);
      });
    }
    if (listData && listData.length > 0) {
      options.series[0].data = listData;
    }

    options.title = {
      text: this.props.t("chart.highchartsSummarySampleTestingStatus"),
    };
  }

  componentDidMount() {}

  render() {
    return (
      <div>
        <HighchartsReact
          highcharts={Highcharts}
          constructorType={"chart"}
          options={options}
          update={false}
        />
      </div>
    );
  }
}
export default HighchartsSummarySampleTestingStatus;
