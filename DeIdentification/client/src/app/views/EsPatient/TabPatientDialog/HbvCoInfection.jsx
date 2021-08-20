import React, { Component } from "react";
import Draggable from 'react-draggable';
import Paper from '@material-ui/core/Paper';
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

function PaperComponent(props) {
  return (
    <Draggable handle="#draggable-dialog-title" cancel={'[class*="MuiDialogContent-root"]'}>
      <Paper {...props} />
    </Draggable>
  );
}

class HbvCoInfection extends Component {
  state = {

  };

  handleChange = (event, source) => {
    event.persist();
    let { item } = this.state

    const name = event.target.name;
    const value = event.target.value;
    item[name] = value
    this.setState({
      item: item
    });
  };

  handleDateChange = (date, name) => {
    let { item } = this.state
    // item["dateOfIssue"] = date
    this.setState({
      item: item
    });
  };

  componentWillMount() {
    let { open, handleClose, item } = this.props;
    if (item == null) {
      item = {}
    }
    this.setState({ item: item });

  }

  render() {
    let {
      id,

    } = this.state;
    let { open, handleClose, handleOKEditClose, t, i18n } = this.props;
    let columns = [
      {
        title: t("patient.tab.no"),
        field: "Stt",
        render: (rowData) =>
          rowData ? (<span>{rowData.tableData.id + 1}</span>) : ("")
      },
      {
        title: t("patient.tab.diagnosis_date"),
        field: "diagnosisDate",
        render: (rowData) =>
          rowData.diagnosisDate ? (rowData.diagnosisDate ? (<span>{moment(rowData.diagnosisDate).format("DD/MM/YYYY")}</span>) : ("")) : ("")
      },
      {
        title: t("patient.tab.start_date"),
        field: "treatment.startDate",
        render: (rowData) =>
          rowData.treatment ? (rowData.treatment.startDate ? (<span>{moment(rowData.treatment.startDate).format("DD/MM/YYYY")}</span>) : ("")) : ("")
      }, {
        title: t("patient.tab.end_date"),
        field: "treatment.endDate",
        render: (rowData) =>
          rowData.treatment ? (rowData.treatment.endDate ? (<span>{moment(rowData.treatment.endDate).format("DD/MM/YYYY")}</span>) : ("")) : ("")
      },
      {
        title: t("patient.tab.treatment_place"),
        field: "treatment.placeProvided",
      },
    ];

    return (
      <React.Fragment>
        <MaterialTable
          data={this.state.item ? this.state.item.listOfHbv ? this.state.item.listOfHbv : [] : []}
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

export default HbvCoInfection;
