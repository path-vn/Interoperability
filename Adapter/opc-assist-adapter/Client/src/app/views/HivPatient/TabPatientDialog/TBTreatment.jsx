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

class TBTreatment extends Component {
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
        field: "tbDiagnoseDate",
        render: (rowData) =>
          rowData.tbDiagnoseDate ? (<span>{moment(rowData.tbDiagnoseDate).format("DD/MM/YYYY")}</span>) : ("")
      },
      {
        title: t("Start Date"),
        field: "tbTreamentDateStart",
        render: (rowData) =>
          rowData.tbTreamentDateStart ? (<span>{moment(rowData.tbTreamentDateStart).format("DD/MM/YYYY")}</span>) : ("")
      },
      {
        title: t("End Date"),
        field: "tbTreamentDateEnd",
        render: (rowData) =>
          rowData.tbTreamentDateEnd ? (<span>{moment(rowData.tbTreamentDateEnd).format("DD/MM/YYYY")}</span>) : ("")
      },
      {
        title: t("Treament Facility"),
        field: "tbTreamentFacility",
      },
    ];
    return (
      <React.Fragment>
        <MaterialTable
          title={""}
          data={this.state.item ? this.state.item.listTBTreatment ? this.state.item.listTBTreatment : [] : []}
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

export default TBTreatment;
