import React, { Component } from "react";
import {
  Grid,
  IconButton,
  Icon,
} from "@material-ui/core";
import MaterialTable from 'material-table';
import { getAllPatient, getPatient, getPatientFromESById, savePatientToESById, deleteItem } from "./PatientService";
import PatientDialog from "./PatientDialogInfo";
import { Breadcrumb, ConfirmationDialog } from "egret";
import { useTranslation } from 'react-i18next';
import moment from "moment";
import VisibilityIcon from '@material-ui/icons/Visibility';
import SyncIcon from '@material-ui/icons/Sync';
import { withStyles } from "@material-ui/core/styles";
import Tooltip from "@material-ui/core/Tooltip";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import 'styles/globitsStyles.scss';
import SearchInput from '../Component/SearchInput/SearchInput';
import NicePagination from '../Component/Pagination/NicePagination';
import './PatientStyle.css';
toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3
  //etc you get the idea
});
const LightTooltip = withStyles((theme) => ({
  tooltip: {
    backgroundColor: theme.palette.common.white,
    color: "rgba(0, 0, 0, 0.87)",
    boxShadow: theme.shadows[1],
    fontSize: 14,
    position: "absolute",
    top: "-10px",
    left: "-25px",
    width: "80px",
  },
}))(Tooltip);
function MaterialButton(props) {
  const { t } = useTranslation();
  const item = props.item;
  return <div>
    <LightTooltip
      title={t("Fhir Detail")}
      placement="right-end"
      enterDelay={500}
      leaveDelay={300}
    >
      <IconButton onClick={() => props.onSelect(item, 0)}>
        <Icon color="primary">visibility</Icon>
      </IconButton>
    </LightTooltip>
    <LightTooltip
      title={t("Elastic Search Detail")}
      placement="right-end"
      enterDelay={500}
      leaveDelay={300}
    >
      <IconButton onClick={() => props.onSelect(item, 1)}>
        <Icon color="#000000"><VisibilityIcon /></Icon>
      </IconButton>
    </LightTooltip>
    <LightTooltip
      title={t("Sync")}
      placement="right-end"
      enterDelay={500}
      leaveDelay={300}
    >
      <IconButton onClick={() => props.onSelect(item, 2)}>
        <Icon color="primary"><SyncIcon /></Icon>
      </IconButton>
    </LightTooltip>
    {/* <IconButton onClick={() => props.onSelect(item, 1)}>
      <Icon color="error">delete</Icon>
    </IconButton> */}
  </div>;
}

class PatientTable extends Component {
  state = {
    keyword: '',
    rowsPerPage: 10,
    page: 1,
    totalPages: 0,
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false,
    shouldOpenImportExcelDialog: false,
  };
  handleTextChange = event => {
    this.setState({ keyword: event.target.value }, function () {
    })
  };

  handleKeyDownEnterSearch = e => {
    if (e.key === 'Enter') {
      this.search();
    }
  };
  setPage = page => {
    this.setState({ page }, function () {
      this.updatePageData();
    })
  };

  setRowsPerPage = event => {
    this.setState({ rowsPerPage: event.target.value, page: 1 }, function () {
      this.updatePageData();
    });
  };

  handleChangePage = (event, newPage) => {
    this.setPage(newPage);
  };

  search = (keyword) => {
    this.setState({ page: 1 }, function () {
      var searchObject = {};
      this.state.keyword = keyword
      searchObject.text = this.state.keyword;
      searchObject.pageIndex = this.state.page;
      searchObject.pageSize = this.state.rowsPerPage;
      getAllPatient(searchObject).then(({ data }) => {
        this.setState({ itemList: [...data.content], totalElements: data.totalElements, totalPages: data.totalPages })
      });
    });
  }

  updatePageData = () => {
    var searchObject = {};
    console.log(this.state.page)
    searchObject.text = this.state.keyword;
    searchObject.pageIndex = this.state.page;
    searchObject.pageSize = this.state.rowsPerPage;
    getAllPatient(searchObject).then(({ data }) => {
      let listPatient = data.content
      this.setState({ itemList: listPatient, totalElements: data.totalElements, totalPages: data.totalPages })
    }
    );
  };

  handleDialogClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false,
      shouldOpenImportExcelDialog: false,
    }
      //, () => {
      //   this.updatePageData()
      // }
    );
  };

  handleOKEditClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false,
      shouldOpenImportExcelDialog: false,
    });
    // this.updatePageData();
  };

  handleConfirmDeleteItem = () => {
    let { t } = this.props
    deleteItem(this.state.id).then(() => {
      this.handleDialogClose();
      toast.success(t('toast.delete_success'));
    });
  };
  handleDelete = id => {
    this.setState({
      id,
      shouldOpenConfirmationDialog: true
    });
  };

  componentDidMount() {
    this.updatePageData();
  }

  handleEditItem = item => {
    this.setState({
      item: item,
      shouldOpenEditorDialog: true
    });
  };

  importExcel = () => {
    this.setState({
      shouldOpenImportExcelDialog: true,
    });
  };
  render() {
    const { t, i18n } = this.props;
    let {
      itemList,
      item,
      shouldOpenConfirmationDialog,
      shouldOpenEditorDialog
    } = this.state;

    let columns = [
      {
        title: t("general.action"),
        field: "custom",
        render: rowData => <MaterialButton item={rowData}
          onSelect={(rowData, method) => {
            if (method === 0) {
              getPatient(rowData.id).then(({ data }) => {
                this.setState({
                  item: data,
                  shouldOpenEditorDialog: true,
                  check: true
                });
              })
            } else if (method === 1) {
              getPatientFromESById(rowData.id).then(({ data }) => {
                this.setState({
                  item: data,
                  shouldOpenEditorDialog: true,
                  check: false
                });
              }).catch(() => {
                alert("No Data");
              })
            }
            else if (method === 2) {
              savePatientToESById(rowData.id).then(({ data }) => {
                alert(data);
              })
            } else {
              alert('Call Selected Here:' + rowData.id);
            }
          }}
        />
      },
      {
        title: t("STT"),
        field: "Stt",
        render: (rowData) =>
          rowData ? (<span>{rowData.tableData.id + 1}</span>) : ("")
      },
      { title: t("Name"), field: "name" },
      // { title: t("patient.art_id"), field: "artID" },
      { title: t("patient.last_updated"), field: "lastUpdated.display" },
      { title: t("patient.address"), field: "currentAddress.text" },
      { title: t("patient.gender"), field: "gender.display" },
      {
        title: t("patient.birth_date"), field: "birthDate",
        render: rowData =>
          (rowData.birthDate) ? <span>{moment(rowData.birthDate).format("YYYY")}</span> : ''
      },
      {
        title: t("Action"),
        cellStyle: (data, rowData) => ({
          backgroundColor: (rowData && rowData.tableData && rowData.tableData.id) % 2 === 1 ? 'rgb(237, 245, 251)' : '#FFF',
          position: 'sticky',
          right: 0,
        }),
        headerStyle: {
          position: 'sticky',
          right: 0,
          zIndex: 11
        },
        render: rowData =>
          <LightTooltip
            title={t("Delete")}
            placement="right-end"
            enterDelay={500}
            leaveDelay={300}
          >
            <IconButton
              size="small"
              onClick={async () => {
                this.handleDelete(rowData.id);
              }}
            >
              <Icon fontSize="small" color="error">delete</Icon>
            </IconButton>
          </LightTooltip>
      }

    ];

    return (
      <div className="m-sm-30">
        <div className="">
          <Breadcrumb routeSegments={[{ name: t('patient.fhir') }]} />
        </div>
        <Grid container spacing={3}>
          <Grid item md={8} sm={12} xs={12}>
          </Grid>
          <Grid item md={4} sm={12} xs={12}>
            <SearchInput
              search={this.search}
              t={t}
            />
          </Grid>
          <Grid item xs={12}>
            <div>
              {shouldOpenEditorDialog && (
                <PatientDialog t={t} i18n={i18n}
                  handleClose={this.handleDialogClose}
                  open={shouldOpenEditorDialog}
                  handleOKEditClose={this.handleOKEditClose}
                  item={item}
                  check={this.state.check}
                />
              )}
              {shouldOpenConfirmationDialog && (
                <ConfirmationDialog
                  open={shouldOpenConfirmationDialog}
                  onConfirmDialogClose={this.handleDialogClose}
                  onYesClick={this.handleConfirmDeleteItem}
                  title={t("confirm_dialog.delete.title")}
                  text={t('confirm_dialog.delete.text')}
                  agree={t("confirm_dialog.delete.agree")}
                  cancel={t("confirm_dialog.delete.cancel")}
                />
              )}
            </div>
            <MaterialTable
              data={itemList ? itemList : []}
              columns={columns}
              options={{
                selection: false,
                actionsColumnIndex: -1,
                paging: false,
                search: false,
                toolbar: false,
                maxBodyHeight: "440px",
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
            />

            <NicePagination
              totalPages={this.state.totalPages}
              handleChangePage={this.handleChangePage}
              setRowsPerPage={this.setRowsPerPage}
              pageSize={this.state.rowsPerPage}
              pageSizeOption={[1, 2, 3, 5, 10, 25, 50, 100, 1000]}
              t={t}
              totalElements={this.state.totalElements}
              page={this.state.page}
            />
          </Grid>
        </Grid>
      </div>
    );
  }
}

export default PatientTable;
