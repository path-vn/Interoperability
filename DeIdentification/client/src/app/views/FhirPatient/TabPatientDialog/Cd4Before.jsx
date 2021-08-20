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

class Cd4Before extends Component {
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
    let listData =[]
    let p ={}
    if(item.cd4BeforeART != null){
    //    p = item.cd4BeforeART
       listData.push(item.cd4BeforeART)
    }
    
    this.setState({ listData: listData });

  }

  render() {
    let {
      id,

    } = this.state;

    let { open, handleClose, handleOKEditClose, t, i18n } = this.props;

    let columns = [
      // {
      //   title: t("general.select"),
      //   field: "custom",
      //   align: "left",
      //   width: "250",        
      //   cellStyle:{
      //     padding:'0px',
      //     paddingLeft:'10px',  
      //   },        
      //   render: rowData => <Checkbox id={`radio${rowData.id}`} name="radSelected" value={rowData.id} 
      //   checked={rowData.isCheck} 
      //   onClick={(event) => this.handleClick(event, rowData)}
      //   />
      // },
      {
        title: t("STT"),
        field: "Stt",
        align: "left",
        width: "250",
        headerStyle: {
          minWidth: "150px",
          paddingLeft: "10px",
          paddingRight: "0px",
        },
        cellStyle: {
          minWidth: "150px",
          paddingLeft: "10px",
          paddingRight: "0px",
          textAlign: "left",
        },
        render: (rowData) =>
        rowData ? (<span>{rowData.tableData.id + 1}</span>) : ("")
      },
      
      {
        title: t("Value"),
        field: "stringValue",
        align: "left",
        width: "250",
        headerStyle: {
          minWidth: "150px",
          paddingLeft: "10px",
          paddingRight: "0px",
        },
        cellStyle: {
          minWidth: "150px",
          paddingLeft: "10px",
          paddingRight: "0px",
          textAlign: "left",
        },
      },
      {
        title: t("Test Performance Date"), field: "testPerformanceDate", align: "left",
        width: "250",
        headerStyle: {
          minWidth: "150px",
          paddingLeft: "10px",
          paddingRight: "0px",
        },
        cellStyle: {
          minWidth: "150px",
          paddingLeft: "10px",
          paddingRight: "0px",
          textAlign: "left",
        },
        render: (rowData) =>
          rowData.testPerformanceDate ? (<span>{moment(rowData.testPerformanceDate).format("DD/MM/YYYY")}</span>) : ("")
      },
      {
        title: t("Specimen Collection Date"), field: "specimenCollectionDate", align: "left",
        width: "250",
        headerStyle: {
          minWidth: "150px",
          paddingLeft: "10px",
          paddingRight: "0px",
        },
        cellStyle: {
          minWidth: "150px",
          paddingLeft: "10px",
          paddingRight: "0px",
          textAlign: "left",
        },
        render: (rowData) =>
          rowData.specimenCollectionDate ? (<span>{moment(rowData.specimenCollectionDate).format("DD/MM/YYYY")}</span>) : ("")
      },
      {
        title: t("Specimen Collection Place"),
        field: "specimenCollectionPlace",
        align: "left",
        width: "250",
        headerStyle: {
          minWidth: "150px",
          paddingLeft: "10px",
          paddingRight: "0px",
        },
        cellStyle: {
          minWidth: "150px",
          paddingLeft: "10px",
          paddingRight: "0px",
          textAlign: "left",
        },
      },
    ];

    return (
      <React.Fragment>
        <MaterialTable
          title={""}
          data={this.state.listData ? this.state.listData  : []}
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

export default Cd4Before;
