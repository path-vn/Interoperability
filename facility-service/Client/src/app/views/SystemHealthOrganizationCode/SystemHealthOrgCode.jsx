import React, { Component } from "react";
import { FormControl, Input, Radio, InputAdornment, Grid, MuiThemeProvider, IconButton, Icon, TextField, Button, TableHead, TableCell, TableRow, Checkbox, TablePagination } from "@material-ui/core";
import MaterialTable, { MTableToolbar, Chip, MTableBody, MTableHeader } from 'material-table';
import { getByPage, getUserById, deleteSystemHealthOrgCode } from "./SystemHealthOrgCodeService";
import { getByPage as listHealthOrganizationSearch } from "../HealthOrganization/HealthOrganizationService";
import ConstantList from "../../appConfig";
import SystemHealthOrgCodeEditorDialog from "./SystemHealthOrgCodeEditorDialog";
import { Breadcrumb, ConfirmationDialog } from "egret";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
import { Link } from "react-router-dom";
import SearchIcon from '@material-ui/icons/Search';
import shortid from "shortid";
import { saveAs } from 'file-saver';
import Autocomplete from "@material-ui/lab/Autocomplete";
import { Helmet } from 'react-helmet';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3
});

function MaterialButton(props) {
  const { t, i18n } = useTranslation();
  const item = props.item;
  return <div>
    <IconButton size="small" onClick={() => props.onSelect(item, 0)}>
      <Icon fontSize="small" color="primary">edit</Icon>
    </IconButton>
    <IconButton size="small" onClick={() => props.onSelect(item, 1)}>
      <Icon fontSize="small" color="error">delete</Icon>
    </IconButton>
  </div>;
}
class SystemHealthOrgCodeTable extends Component {
  state = {
    rowsPerPage: 10,
    page: 0,
    systemHealthOrgCodeList: [],
    item: {},
    keyword: '',
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false,
    shouldOpenConfirmationDeleteAllDialog: false,
  };
  constructor(props) {
    super(props);
    this.handleTextSearchChange = this.handleTextSearchChange.bind(this);
  }
  listSystemType = ConstantList.SYSTEM_TYPE;
  handleTextSearchChange = event => {
    this.setState({ keyword: event.target.value }, function () {
    })
  };
  handleKeyDownEnterSearch = (e) => {
    if (e.key === 'Enter') {
      this.search()
    }
  }
  async handleDeleteList(list) {
    let { t } = this.props;
    let deleteSuccess = 0, deleteError = 0, error = 0;
    for (let i = 0; i < list.length; i++) {
      await deleteSystemHealthOrgCode(list[i].id).then((res) => {
        deleteSuccess++;
      }
      ).catch(() => {
        error++
        // this.handleDialogClose();
      });
    }
    if (deleteSuccess != 0) {
      toast.info(t("administrativeUnit.notify.deleteSuccess") + " " + deleteSuccess);
    }
    if (error != 0) {
      toast.warning(t('administrativeUnit.notify.error') + " " + error);
    }
    this.updatePageData();
    this.handleDialogClose();
  }
  handleDeleteAll = (event) => {
    let { t } = this.props;
    if (this.data != null) {
      this.handleDeleteList(this.data);
    } else {
      toast.warning(t('general.select_data'));
      this.handleDialogClose();
    };
  };
  search() {
    var searchObject = {};
    searchObject.pageIndex = this.state.page + 1;
    searchObject.pageSize = this.state.rowsPerPage;
    searchObject.text = this.state.keyword.trim();
    getByPage(searchObject).then(({ data }) => {
      this.setState({
        systemHealthOrgCodeList: [...data.content], totalElements: data.totalElements
      });
    }
    );
  };
  setPage = page => {
    this.setState({ page }, function () {
      this.updatePageData();
    });
  };

  setRowsPerPage = event => {
    this.setState({ rowsPerPage: event.target.value, page: 0 }, function () {
      this.updatePageData();
    });
  };

  handleChangePage = (event, newPage) => {
    this.setPage(newPage);
  };
  handleDownload = () => {
    var blob = new Blob(["Hello, world!"], { type: "text/plain;charset=utf-8" });
    saveAs(blob, "hello world.txt");
  }
  handleDialogClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false,
      shouldOpenConfirmationDeleteAllDialog: false,
    });
    this.updatePageData();
  };

  handleDelete = id => {
    this.setState({
      id,
      shouldOpenConfirmationDialog: true
    });
  };

  handleConfirmationResponse = () => {
    let { t } = this.props;
    deleteSystemHealthOrgCode(this.state.id).then(() => {
      this.updatePageData();
      this.handleDialogClose();
      toast.success(t("general.deleteSuccess"));
    }).catch(() => {
      toast.warning(t('general.error'));
      this.handleDialogClose();
    });
  };

  componentDidMount() {
    this.updatePageData();
  }
 
  componentWillMount() {
     //Get HealthOrganization, systemType for combobox
     this.healthOrganizationSelect();
     //this.updatePageData();

  }
  //onSelect combobox HealthOrganization for filter
  healthOrganizationSelect = () => {
    listHealthOrganizationSearch({pageIndex: 1, pageSize: 10}).then(({data}) => {
      this.setState({listHealthOrganization: data?.content})
    });
  } 
  selectHealthOrganization = (event, value, source) => {
    //debugger
    if(source == "healthOrganization"){
      this.setState({ healthOrganization: value?.id, healthOrganizationObj: value }, function () {
        this.updatePageData();
      });
    }
    if(source == "systemType"){
      this.setState({ systemType: value?.name, systemTypeValue: value }, function () {
        this.updatePageData();
      });
    }
  }
  updatePageData = () => {
    debugger
    var searchObject = {};
    searchObject.pageIndex = this.state.page + 1;
    searchObject.pageSize = this.state.rowsPerPage;
    searchObject.healthOrgId = this.state.healthOrganization;
    searchObject.systemType = this.state.systemType;
    searchObject.text = this.state.keyword.trim();
    getByPage(searchObject).then(({ data }) => {
      this.setState({
        systemHealthOrgCodeList: [...data.content], totalElements: data.totalElements
      });
    }
    );
  };

  render() {
    const { t, i18n } = this.props;
    let {
      rowsPerPage,
      page,
      keyword,
      systemType,
      systemTypeValue,
      listHealthOrganization,
      shouldOpenConfirmationDialog,
      shouldOpenConfirmationDeleteAllDialog,
      shouldOpenEditorDialog,
      healthOrganizationObj,
      healthOrganization,
      totalElements
    } = this.state;
    let columns = [
      {
        title: t("general.action"),
        field: "custom",
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
        render: rowData => <MaterialButton item={rowData}
          onSelect={(rowData, method) => {
            if (method === 0) {
              getUserById(rowData.id).then(({ data }) => {
                this.setState({
                  item: data,
                  shouldOpenEditorDialog: true
                });
              })
            } else if (method === 1) {
              this.handleDelete(rowData.id);
            } else {
              alert('Call Selected Here:' + rowData.id);
            }
          }}
        />
      },
      {
        title: t("systemHealthOrganizationCode.code"), field: "code", width: "150",
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
        title: t('systemHealthOrganizationCode.name'), field: "name", align: "left", width: "150",
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
        title: t('Tên đơn vị'), field: "healthOrganization.name", align: "left", width: "150",
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

    ]
    return (
      <div className="m-sm-30">
        <div className="mb-sm-30">
          <Helmet>
            <title>{t("systemHealthOrganizationCode.title")} | {t("web_site")}</title>
          </Helmet>
          <Breadcrumb routeSegments={[{ name: t("systemHealthOrganizationCode.title"), path: "/directory/apartment" }]} />
        </div>
        {/* Filter */}
          
        <Grid container md={12} lg={12} sm={12} spacing={2} className="mt-10">
            <Grid item md={3} lg={3} sm={3}>
            <h6>Lọc theo nguồn dữ liệu</h6>
            <Autocomplete
              options={this.listSystemType}
              getOptionLabel={(option) => option.name}
              id="debug"
              value={systemTypeValue || null}
              onChange={(event, value) => { this.selectHealthOrganization(event, value, "systemType") }}
              renderInput={(params) => (
                <TextField
                  {...params}
                  variant="outlined"
                  size="small"
                  label=""
                />
              )}
            />
          </Grid>
          <Grid item md={3} lg={3} sm={3}>
            <h6>Lọc theo đơn vị y tế</h6>
            <Autocomplete
              //options={[]}
              options={listHealthOrganization ? listHealthOrganization : []}
              getOptionLabel={(option) => option.name}
              id="debug"
              value={healthOrganizationObj || null}
              onChange={(event, value) => { this.selectHealthOrganization(event, value, "healthOrganization") }}
              renderInput={(params) => (
                <TextField
                  {...params}
                  variant="outlined"
                  size="small"
                  label=""
                />
              )}
            />
          </Grid>
        </Grid>
        <Grid container spacing={3}>
          <Grid item lg={7} md={7} sm={12} xs={12}>
            <Button
              className="mb-16 mr-16"
              variant="contained"
              color="primary"
              onClick={() => this.setState({ shouldOpenEditorDialog: true, item: {} })}
            >
              {t('Add')}
            </Button>
            <Button
              className="mb-16 mr-16"
              variant="contained"
              color="primary"
              onClick={() =>
                this.setState({ shouldOpenConfirmationDeleteAllDialog: true })
              }
            >
              {t("general.delete")}
            </Button>
          </Grid>
          <Grid item lg={5} md={5} sm={12} xs={12} >
            <FormControl fullWidth>
              <Input
                className='mt-10 search_box w-100 stylePlaceholder'
                type="text"
                name="keyword"
                value={keyword}
                onChange={this.handleTextSearchChange}
                onKeyDown={this.handleKeyDownEnterSearch}
                placeholder={t('general.enterSearch')}
                id="search_box"
                startAdornment={
                  <InputAdornment >
                    <Link to="#"> <SearchIcon
                      onClick={() => this.search(keyword)}
                      style={{
                        position: "absolute",
                        top: "0",
                        right: "0"
                      }} /></Link>
                  </InputAdornment>
                }
              />
            </FormControl>
          </Grid>
          <Grid item xs={12}>
            <MaterialTable
              title={t('List')}
              data={this.state.systemHealthOrgCodeList}
              columns={columns}
              parentChildData={(row, rows) => {
                var list = rows.find(a => a.id === row.parentId);
                return list;
              }}
              options={{
                selection: true,
                actionsColumnIndex: -1,
                paging: false,
                search: false,
                rowStyle: (rowData, index) => ({
                  backgroundColor: (index % 2 === 1) ? '#EEE' : '#FFF',
                }),
                maxBodyHeight: '450px',
                minBodyHeight: '370px',
                headerStyle: {
                  backgroundColor: '#358600',
                  color: '#fff',
                },
                padding: 'dense',
                toolbar: false
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
            // actions={[
            //   {
            //     tooltip: 'Remove All Selected Users',
            //     icon: 'delete',
            //     onClick: (evt, data) => {
            //       this.handleDelete(data);
            //       alert('You want to delete ' + data.length + ' rows');
            //     }
            //   },
            // ]}
            />
            <TablePagination
              align="left"
              className="px-16"
              rowsPerPageOptions={[1, 2, 5, 10, 25, 50, 100]}
              component="div"
              labelRowsPerPage={t('general.rows_per_page')}
              labelDisplayedRows={({ from, to, count }) => `${from}-${to} ${t('general.of')} ${count !== -1 ? count : `more than ${to}`}`}
              count={this.state.totalElements}
              rowsPerPage={this.state.rowsPerPage}
              page={this.state.page}
              backIconButtonProps={{
                "aria-label": "Previous Page"
              }}
              nextIconButtonProps={{
                "aria-label": "Next Page"
              }}
              onChangePage={this.handleChangePage}
              onChangeRowsPerPage={this.setRowsPerPage}
            />

            {shouldOpenEditorDialog && (
              <SystemHealthOrgCodeEditorDialog
                handleClose={this.handleDialogClose}
                open={shouldOpenEditorDialog}
                item={this.state.item}
                t={t} i18n={i18n}
              />
            )}
            {shouldOpenConfirmationDialog && (
              <ConfirmationDialog
                title={t("confirm")}
                open={shouldOpenConfirmationDialog}
                onConfirmDialogClose={this.handleDialogClose}
                onYesClick={this.handleConfirmationResponse}
                text={t('DeleteConfirm')}
                Yes={t('general.Yes')}
                No={t('general.No')}
              />
            )}

            {shouldOpenConfirmationDeleteAllDialog && (
              <ConfirmationDialog
                open={shouldOpenConfirmationDeleteAllDialog}
                onConfirmDialogClose={this.handleDialogClose}
                onYesClick={this.handleDeleteAll}
                title={t("confirm")}
                text={t("DeleteAllConfirm")}
                Yes={t("general.Yes")}
                No={t("general.No")}
              />
            )}
          </Grid>
        </Grid>
      </div>
    );
  }
}

export default SystemHealthOrgCodeTable;
