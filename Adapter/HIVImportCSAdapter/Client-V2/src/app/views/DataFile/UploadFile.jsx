import React, { Component } from "react";
import {
  Grid,
  IconButton,
  Icon,
  TablePagination,
  Button,
  TextField
} from "@material-ui/core";
import MaterialTable, { MTableToolbar, Chip, MTableBody, MTableHeader } from 'material-table';
import { getAllEQAHealthOrgTypes, deleteItem, searchByPage, getItemById, getFile } from "./DataFileService";
import DataFileDialog from "./DataFileDialog";
import { Breadcrumb, ConfirmationDialog } from "egret";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
import shortid from "shortid";
import { saveAs } from 'file-saver';
import { searchByPage as getDataSource } from "../DataSource/DataSourceService"
import Autocomplete from "@material-ui/lab/Autocomplete";
import VisibilityIcon from '@material-ui/icons/Visibility';
import { toast } from "react-toastify";
import 'styles/globitsStyles.scss';
import "react-toastify/dist/ReactToastify.css";
import SearchInput from '../Component/SearchInput/SearchInput';
import NicePagination from '../Component/Pagination/NicePagination';
import DeleteIcon from '@material-ui/icons/Delete';
import AddIcon from '@material-ui/icons/Add';

toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3
  //etc you get the idea
});

function MaterialButton(props) {
  const { t, i18n } = useTranslation();
  const item = props.item;
  return <div>
    <IconButton onClick={() => props.onSelect(item, 2)}>
      <Icon color="primary">cloud_upload</Icon>
    </IconButton>
    <IconButton onClick={() => props.onSelect(item, 0)}>
      <Icon color="primary"><VisibilityIcon /></Icon>
    </IconButton>
    <IconButton onClick={() => props.onSelect(item, 1)}>
      <Icon color="error">delete</Icon>
    </IconButton>

  </div>;
}

class UploadFile extends Component {
  state = {
    keyword: '',
    rowsPerPage: 10,
    page: 0,
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false,
    shouldOpenConfirmationDeleteListDialog: false
  };

  //handle search start
  updatePageData = () => {
    var searchObject = {};
    searchObject.text = this.state.text;
    searchObject.pageIndex = this.state.page;
    searchObject.pageSize = this.state.rowsPerPage;
    searchObject.dataSourceId = this.state.dataSource ? this.state.dataSource.id : "";
    searchByPage(searchObject).then(({ data }) => {
      this.setState({
        itemList: [...data.content],
        totalElements: data.totalElements,
        totalPages: data.totalPages
      })
    }
    );
  };

  search = (keyword, type) => {
    this.setState({ page: 0 }, function () {
      var searchObject = {};
      if (type == "text") {
        this.state.text = keyword
      }
      if (type == "orgType") {
        this.state.orgType = keyword
      }

      searchObject.text = this.state.text;
      searchObject.pageIndex = this.state.page;
      searchObject.pageSize = this.state.rowsPerPage;
      searchObject.dataSourceId = this.state.dataSource ? this.state.dataSource.id : "";
      searchByPage(searchObject).then(({ data }) => {
        this.setState({
          itemList: [...data.content],
          totalElements: data.totalElements,
          totalPages: data.totalPages
        })
      });
    });
  }
  //handle search end
  //Paging handle start
  setPage = (page) => {
    this.setState({ page }, function () {
      this.updatePageData()
    })
  }
  setRowsPerPage = (event) => {
    this.setState({ rowsPerPage: event.target.value, page: 0 }, function () {
      this.updatePageData()
    })
  }
  handleChangePage = (event, newPage) => {
    this.setPage(newPage)
  }
  //Paging handle end
  //handle popup open/close start
  handleClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false,
      shouldOpenConfirmationDeleteListDialog: false,
    }, () => {
      this.updatePageData();
    });
  };

  handleEditItem = item => {
    this.setState({
      item: item,
      shouldOpenEditorDialog: true
    });
  };
  //handle popup open/close end
  //handle delete start
  async handleDeleteList(list) {
    let listAlert = [];
    let { t } = this.props
    for (var i = 0; i < list.length; i++) {
      try {
        await deleteItem(list[i].id);
      } catch (error) {
        listAlert.push(list[i].name);
      }
    }
    this.handleClose();
    toast.success(t('toast.delete_success'));
  };
  handleDeleteListItem = (event) => {
    let { t } = this.props
    if (this.data != null) {
      this.handleDeleteList(this.data);
    } else {
      this.handleClose();
      toast.warning(t('toast.please_select'));
    };
  }
  handleConfirmDeleteItem = () => {
    let { t } = this.props
    deleteItem(this.state.id).then(() => {
      this.handleClose();
      toast.success(t('toast.delete_success'));
    });
  };
  handleDelete = id => {
    this.setState({
      id,
      shouldOpenConfirmationDialog: true
    });
  };
  //handle delete end

  componentDidMount() {
    this.updatePageData();
  }
  // componentWillMount() {
  //   var searchObject = {};
  //   searchObject.pageIndex = 1;
  //   searchObject.pageSize = 100000;
  //   getDataSource(searchObject).then(({ data }) => {
  //     this.setState({ listDataSource: [...data.content] })
  //   }
  //   );
  // }
  // handleEditItem = item => {
  //   this.setState({
  //     item: item,
  //     shouldOpenEditorDialog: true
  //   });
  // };



  // handleDelete = id => {
  //   this.setState({
  //     id,
  //     shouldOpenConfirmationDialog: true
  //   });
  // };

  // changeSelected = (value, type) => {
  //   if (type === 'dataSource') {
  //     this.setState({ dataSource: value }, () => {
  //       this.search()
  //     });
  //   }

  // }



  render() {
    const { t, i18n } = this.props;
    let {
      itemList,
      shouldOpenEditorDialog,
      shouldOpenConfirmationDialog,
      shouldOpenConfirmationDeleteListDialog
    } = this.state;

    let columns = [
      { title: t("upload_file.name"), field: "fileName", width: "150" },
      { title: t("upload_file.data_source"), field: "dataSource.name", align: "left", width: "150" },
      {
        title: t("general.action"),
        field: "custom",
        align: "left",
        width: "250",
        render: rowData => <MaterialButton item={rowData}
          onSelect={(rowData, method) => {
            if (method === 0) {
              getItemById(rowData.id).then(({ data }) => {
                if (data.parent === null) {
                  data.parent = {};
                }
                this.handleEditItem(data);
                console.log(data);
              })
            } else if (method === 1) {
              this.handleDelete(rowData.id);
            } else if (method === 2) {

              // getItemById(rowData.id).then(({ data }) => {
              getFile(rowData.id).then(res => {
                alert("Success")
              })
              // })



              // let fileName = rowData.fileName;
              // if(rowData.fileName != null){
              //   fileName =rowData.fileName.replace(".","/")
              // }
              // getFile(rowData).then(res=>{
              //   alert("Success")
              // })
            }
            else {
              alert('Call Selected Here:' + rowData.id);
            }
          }}
        />
      },
    ];

    return (
      <div className="m-sm-30">
        <div className="mb-sm-30">
          <Breadcrumb routeSegments={[{ name: t('upload_file.title') }]} />
        </div>

        <Grid container spacing={3}>
          <Grid item md={6} sm={12}>
            <Button
              className="mb-16 mr-16 btn btn-success d-inline-flex"
              startIcon={<AddIcon />}
              variant="contained"
              onClick={() => {
                this.handleEditItem({ startDate: new Date(), endDate: new Date() });
              }
              }
            >
              {t('general.button.add')}
            </Button>
            <Button
              className="mb-16 mr-36 btn btn-warning d-inline-flex"
              variant="contained"
              startIcon={<DeleteIcon />}
              onClick={() => this.setState({ shouldOpenConfirmationDeleteListDialog: true })}>
              {t('general.button.delete')}
            </Button>
            {shouldOpenConfirmationDeleteListDialog && (
              <ConfirmationDialog
                open={shouldOpenConfirmationDeleteListDialog}
                onConfirmDialogClose={this.handleClose}
                onYesClick={this.handleDeleteListItem}
                title={t("confirm_dialog.delete_list.title")}
                text={t('confirm_dialog.delete_list.text')}
                agree={t("confirm_dialog.delete_list.agree")}
                cancel={t("confirm_dialog.delete_list.cancel")}
              />
            )}
          </Grid>
          <Grid item lg={6} md={6} sm={12} xs={12}>
            <SearchInput
              search={this.search}
              t={t}
            />
          </Grid>
          {shouldOpenConfirmationDeleteListDialog && (
            <ConfirmationDialog
              open={shouldOpenConfirmationDeleteListDialog}
              onConfirmDialogClose={this.handleClose}
              onYesClick={this.handleDeleteAll}
              text={t('DeleteConfirm')}
              agree={t('general.agree')}
              cancel={t('general.cancel')}
            />
          )}


          <Grid item xs={12}>
            <div>
              {shouldOpenEditorDialog && (
                <DataFileDialog t={t} i18n={i18n}
                  handleClose={this.handleClose}
                  open={shouldOpenEditorDialog}
                  // handleOKEditClose={this.handleOKEditClose}
                  item={this.state.item ? this.state.item : {}}
                  checkView={this.state.checkView ? this.state.checkView : false}
                />
              )}

              {shouldOpenConfirmationDialog && (
                <ConfirmationDialog
                  open={shouldOpenConfirmationDialog}
                  onConfirmDialogClose={this.handleClose}
                  onYesClick={this.handleConfirmDeleteItem}
                  title={t("confirm_dialog.delete.title")}
                  text={t('confirm_dialog.delete.text')}
                  agree={t("confirm_dialog.delete.agree")}
                  cancel={t("confirm_dialog.delete.cancel")}
                />
              )}
            </div>
            <MaterialTable
              data={itemList}
              columns={columns}
              options={{
                selection: true,
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
              pageSizeOption={[1, 2, 3, 5, 10, 25]}
              t={t}
            />
          </Grid>
        </Grid>
      </div>
    );
  }
}

export default UploadFile;
