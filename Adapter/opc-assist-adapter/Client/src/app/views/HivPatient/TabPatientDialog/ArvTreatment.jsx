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

class ArvTreatment extends Component {
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
        title: t("Start Date"),
        field: "arvTreatmentDateStart",
        render: (rowData) =>
          rowData.arvTreatmentDateStart ? (<span>{moment(rowData.arvTreatmentDateStart).format("DD/MM/YYYY")}</span>) : ("")
      },
      {
        title: t("End Date"),
        field: "arvTreatmentDateEnd",
        render: (rowData) =>
          rowData.arvTreatmentDateEnd ? (<span>{moment(rowData.arvTreatmentDateEnd).format("DD/MM/YYYY")}</span>) : ("")
      },
      {
        title: t("Place Initiation"),
        field: "arvTreatmentPlaceInitiation",
      },
      {
        title: t("Regimen Line"),
        field: "arvRegimenLine",
      },
      {
        title: t("Regimen Name"),
        field: "arvRegimenName",
      },
    ];
    return (
      <React.Fragment>
        <MaterialTable
          title={""}
          data={this.state.item ? this.state.item.listArvTreatment ? this.state.item.listArvTreatment : [] : []}
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

export default ArvTreatment;
