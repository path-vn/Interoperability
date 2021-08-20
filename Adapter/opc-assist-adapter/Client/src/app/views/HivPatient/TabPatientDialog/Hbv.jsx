import React, { Component } from "react";
import moment from "moment";
import MaterialTable, {
  MTableToolbar,
} from "material-table";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

toast.configure({
  autoClose: 1000,
  draggable: false,
  limit: 3
});

class Hbv extends Component {
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
        title: t("STT"),
        field: "Stt",
        render: (rowData) =>
          rowData ? (<span>{rowData.tableData.id + 1}</span>) : ("")
      },
      {
        title: t("Diagnose Date"),
        field: "diagnosisDate",
        render: (rowData) =>
          rowData.diagnosisDate ? (<span>{moment(rowData.diagnosisDate).format("DD/MM/YYYY")}</span>) : ("")
      },
      {
        title: t("Start Date"),
        field: "treatmentStartDate",
        render: (rowData) =>
          rowData.treatmentStartDate ? (<span>{moment(rowData.treatmentStartDate).format("DD/MM/YYYY")}</span>) : ("")
      },
      {
        title: t("End Date"),
        field: "treatmentEndDate",
        render: (rowData) =>
          rowData.treatmentEndDate ? (<span>{moment(rowData.treatmentEndDate).format("DD/MM/YYYY")}</span>) : ("")
      },
      {
        title: t("Place Provided"),
        field: "placeProvided",
      },
    ];
    return (
      <React.Fragment>
        <MaterialTable
          title={""}
          data={this.state.item ? this.state.item.listHbv ? this.state.item.listHbv : [] : []}
          columns={columns}
          options={{
            selection: false,
            actionsColumnIndex: -1,
            paging: false,
            search: false,
            rowStyle: (rowData, index) => ({
              backgroundColor: index % 2 === 1 ? "#EEE" : "#FFF",
            }),
            headerStyle: {
              backgroundColor: "#358600",
              color: "#fff",
            },
            padding: "dense",
            toolbar: false,
          }}
          components={{
            Toolbar: (props) => <MTableToolbar {...props} />,
          }}
          onSelectionChange={(rows) => {
            this.data = rows;
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

export default Hbv;
