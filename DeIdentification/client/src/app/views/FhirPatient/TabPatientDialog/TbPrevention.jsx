import React, { Component } from "react";
import moment from "moment";
import MaterialTable from "material-table";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

toast.configure({
  autoClose: 1000,
  draggable: false,
  limit: 3
});


class TbPrevention extends Component {
  state = {

  };

  handleChange = (event) => {
    event.persist();
    let { item } = this.state

    const name = event.target.name;
    const value = event.target.value;
    item[name] = value
    this.setState({
      item: item
    });
  };

  handleDateChange = () => {
    let { item } = this.state
    // item["dateOfIssue"] = date
    this.setState({
      item: item
    });
  };

  componentWillMount() {
    let { item } = this.props;
    if (item == null) {
      item = {}
    }
    this.setState({ item: item });

  }

  render() {
    let { t } = this.props;
    let columns = [
      {
        title: t("patient.tab.no"),
        field: "Stt",
        render: (rowData) =>
          rowData ? (<span>{rowData.tableData.id + 1}</span>) : ("")
      },
      {
        title: t("patient.tab.start_date"),
        field: "startDate",
        render: (rowData) =>
          rowData.startDate ? (<span>{moment(rowData.startDate).format("DD/MM/YYYY")}</span>) : ("")
      }, {
        title: t("patient.tab.end_date"),
        field: "endDate",
        render: (rowData) =>
          rowData.endDate ? (<span>{moment(rowData.endDate).format("DD/MM/YYYY")}</span>) : ("")
      },
      {
        title: t("patient.tab.treatment_place"),
        field: "placeProvided",
      },
    ];
    return (
      <React.Fragment>
        <MaterialTable
          data={this.state.item ? this.state.item.listOfTpt ? this.state.item.listOfTpt : [] : []}//this.state.item ? this.state.item.cd4List ? this.state.item.cd4List : [] :
          columns={columns}
          options={{
            selection: false,
            actionsColumnIndex: -1,
            paging: false,
            search: false,
            toolbar: false,
            maxBodyHeight: "200px",
            headerStyle: {
              backgroundColor: "#2a80c8",
              color: "#fff",
            },
            rowStyle: (rowData, index) => ({
              backgroundColor: index % 2 === 1 ? 'rgb(237, 245, 251)' : '#FFF',
            }),

          }}
          onSelectionChange={(rows) => {
            this.data = rows;
            // this.setState({selectedItems:rows});
          }}
          localization={{
            body: {
              emptyDataSourceMessage: `${t(
                "general.emptyDataMessageTable"
              )}`,
            },
          }}
        />
      </React.Fragment>
    );
  }
}

export default TbPrevention;
