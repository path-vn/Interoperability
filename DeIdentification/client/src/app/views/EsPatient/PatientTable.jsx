import React, { Component } from "react";
import {
  Grid,
  IconButton,
  Icon,
  TextField
} from "@material-ui/core";
import MaterialTable from 'material-table';
import { Breadcrumb, ConfirmationDialog } from "egret";
import { getAllPatient, getPatient, getPatientFromESById, deleteItem } from "./PatientService";
import PatientDialog from "./PatientDialogInfo";
import { useTranslation } from 'react-i18next';
import SearchInput from '../Component/SearchInput/SearchInput';
import NicePagination from '../Component/Pagination/NicePagination';
import moment from "moment";
import { toast } from 'react-toastify';
import { withStyles } from "@material-ui/core/styles";
import Tooltip from "@material-ui/core/Tooltip";
import Autocomplete from "@material-ui/lab/Autocomplete";
import ConstEs from "./Const";
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
      title={t("Es Patient Detail")}
      placement="right-end"
      enterDelay={500}
      leaveDelay={300}
    >
      <IconButton onClick={() => props.onSelect(item, 0)}>
        <Icon color="primary">visibility</Icon>
      </IconButton>
    </LightTooltip>
    <LightTooltip
      title={t("Fhir Detail")}
      placement="right-end"
      enterDelay={500}
      leaveDelay={300}
    >
      <IconButton onClick={() => props.onSelect(item, 1)}>
        <Icon color="#000000">visibility</Icon>
      </IconButton>
    </LightTooltip>
  </div>;
}

class PatientTable extends Component {
  state = {
    keyword: null,
    rowsPerPage: 10,
    page: 1,
    totalPages: 0,
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false,
    shouldOpenImportExcelDialog: false,
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

  search = () => {
    this.setState({ page: 1 }, function () {
      var searchObject = {};
      // this.state.keyword = keyword
      searchObject.text = this.state.keyword;
      searchObject.pageIndex = this.state.page;
      searchObject.pageSize = this.state.rowsPerPage;
      searchObject.syncOrg = this.state.syncOrg ? this.state.syncOrg : null;
      getAllPatient(searchObject).then(({ data }) => {
        this.setState({ itemList: [...data.content], totalElements: data.totalElements, totalPages: data.totalPages })
      });
    });
  }

  updatePageData = () => {
    var searchObject = {};
    searchObject.text = this.state.keyword;
    searchObject.pageIndex = this.state.page;
    searchObject.pageSize = this.state.rowsPerPage;
    searchObject.syncOrg = this.state.syncOrg ? this.state.syncOrg : null;
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
    }, () => {
      this.updatePageData();
    }
    );
  };

  handleOKEditClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false,
      shouldOpenImportExcelDialog: false,
    });
    this.updatePageData();
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
  changeSyncOrgSelected = (event, value) => {
    this.setState({ org: value ? value : null, syncOrg: value ? value.display : null }, () => {
      this.search();
    })
  }
  changeSyncOrgSelected = (event, value) => {
    this.setState({ org: value ? value : null, syncOrg: value ? value.display : null }, () => {
      this.search();
    })
  }
  render() {
    const { t, i18n } = this.props;
    let {
      rowsPerPage,
      page,
      totalElements,
      itemList,
      item,
      shouldOpenConfirmationDialog,
      shouldOpenEditorDialog
    } = this.state;

    let columns = [
      {
        title: t("Action"),
        field: "custom",
        align: "center",
        width: "250",
        cellStyle: (data, rowData) => ({
          backgroundColor: (rowData && rowData.tableData && rowData.tableData.id) % 2 === 1 ? 'rgb(237, 245, 251)' : '#FFF',
          position: 'sticky',
          left: 0,
        }),
        headerStyle: {
          position: 'sticky',
          left: 0,
          zIndex: 11
        },
        render: rowData => <MaterialButton item={rowData}
          onSelect={(rowData, method) => {
            if (method === 0) {
              getPatientFromESById(rowData.id).then(({ data }) => {
                this.setState({
                  item: data,
                  shouldOpenEditorDialog: true,
                  check: false
                });
              }).catch(() => {
                alert("No Data");
              })
            } else if (method === 1) {
              getPatient(rowData.id).then(({ data }) => {
                this.setState({
                  item: data,
                  shouldOpenEditorDialog: true,
                  check: false
                });
              }).catch(() => {
                alert("No Data");
              })

            }
          }}
        />
      },
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
      { title: t("Name"), field: "name", width: "150" },
      // { title: t("artID"), field: "artID", width: "150" },
      { title: t("Sync Org"), field: "lastUpdated.display", width: "150" },
      { title: t("Address"), field: "currentAddress.text", align: "left", width: "150" },
      { title: t("Gender"), field: "gender.display", align: "left", width: "150" },
      {
        title: t("Birth Date"), field: "birthDate", align: "left", width: "150",
        render: rowData =>
          (rowData.birthDate) ? <span>{moment(rowData.birthDate).format("YYYY")}</span> : ''
      },
      {
        title: t("Action"),
        cellStyle: (data, rowData) => ({
          backgroundColor: (rowData && rowData.tableData && rowData.tableData.id) % 2 === 1 ? 'rgb(237, 245, 251)' : '#FFF',
          position: 'sticky',
          left: 0,
        }),
        headerStyle: {
          position: 'sticky',
          left: 0,
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
          <Breadcrumb routeSegments={[{ name: t('patient.es') }]} />
        </div>
        <Grid container spacing={3}>
          <Grid item md={8} sm={12} xs={12}>
          </Grid>
          <Grid item md={4} sm={12} xs={12}>
            {/* <SearchInput
              search={this.search}
              t={t}
            /> */}
            <Autocomplete
              className="w-100"
              id="combo-box"
              options={ConstEs.listSynOrg ? ConstEs.listSynOrg : []}
              value={this.state.org ? this.state.org : null}
              clearable
              renderInput={(params) => (
                <TextField
                  {...params}
                  value={this.state.org ? this.state.org : null}
                  label={<span><span style={{ color: "red" }}></span> {t("Sync Org")}</span>}
                // variant="outlined"
                // size="small"
                />
              )}
              getOptionLabel={(option) => option.display}
              getOptionSelected={(option, value) => option.code === value.code}
              onChange={(event, value) => { this.changeSyncOrgSelected(event, value); }}
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
