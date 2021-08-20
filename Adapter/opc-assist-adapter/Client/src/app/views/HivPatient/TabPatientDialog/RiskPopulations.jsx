import React, { Component } from "react";
import {
  Dialog,
  Button,
  DialogActions,
  Grid,
  Checkbox,
  FormControlLabel,
  DialogTitle,
  TextField,
  DialogContent,
  FormControl,
  MenuItem,
  Select,
  InputLabel,
  FormHelperText
} from "@material-ui/core";
import { TextValidator } from "react-material-ui-form-validator";
import { MuiPickersUtilsProvider, DateTimePicker } from "@material-ui/pickers";
import Autocomplete from '@material-ui/lab/Autocomplete';
import DateFnsUtils from "@date-io/date-fns";
import Draggable from 'react-draggable';
import Paper from '@material-ui/core/Paper';
import { toast } from 'react-toastify';
import moment from "moment";
import MaterialTable, {
  MTableToolbar,
  Chip,
  MTableBody,
  MTableHeader,
} from "material-table";
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

class RickPopulation extends Component {
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
    console.log(item);
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
        title: t("Code"),
        field: "code",
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
        title: t("Display"),
        field: "display",
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
        title: t("Definition"),
        field: "Definition",
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
      }
    ];
    return (
      <React.Fragment>
        <MaterialTable
          title={""}
          data={this.state.item ? this.state.item.listRiskPopulation ? this.state.item.listRiskPopulation : [] : []}
          columns={columns}
          options={{
            selection: false,
            actionsColumnIndex: -1,
            paging: false,
            search: false,
            rowStyle: (rowData, index) => ({
              backgroundColor: index % 2 === 1 ? "#EEE" : "#FFF",
            }),
            maxBodyHeight: "450px",
            minBodyHeight: "370px",
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

export default RickPopulation;
