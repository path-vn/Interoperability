import React, { Component } from "react";
import {
  Grid,
  IconButton,
  Icon,
  Button} from "@material-ui/core";
import MaterialTable, {  } from 'material-table';
import { deleteItem, searchByPage, getById } from "./DeIdentificationConfigService";
import DeIdentificationConfigDialog from "./DeIdentificationConfigDialog";
import { Breadcrumb, ConfirmationDialog } from "egret";
import { useTranslation } from 'react-i18next';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import 'styles/globitsStyles.scss';
import SearchInput from '../Component/SearchInput/SearchInput';
import NicePagination from '../Component/Pagination/NicePagination';
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
    <IconButton size="small" onClick={() => props.onSelect(item, 0)}>
      <Icon fontSize="small" color="primary">edit</Icon>
    </IconButton>
    <IconButton size="small" onClick={() => props.onSelect(item, 1)}>
      <Icon fontSize="small" color="error">delete</Icon>
    </IconButton>
  </div>;
}

class DeIdentificationConfigTable extends Component {
  state = {
    keyword: '',
    rowsPerPage: 10,
    page: 0,
    totalPages: 0,
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false,
    shouldOpenConfirmationDeleteListDialog: false,
  }

  updatePageData = () => {
    var searchObject = {};
    searchObject.text = this.state.keyword;
    searchObject.pageIndex = this.state.page;
    searchObject.pageSize = this.state.rowsPerPage;
    searchByPage(searchObject).then(({ data }) => {
      this.setState({ itemList: [...data.content], totalElements: data.totalElements, totalPages: data.totalPages })
    }
    );
  };

  search = (keyword) => {
    this.setState({ page: 0 }, function () {
      var searchObject = {};
      this.state.keyword = keyword
      searchObject.text = this.state.keyword;
      searchObject.pageIndex = this.state.page;
      searchObject.pageSize = this.state.rowsPerPage;
      searchByPage(searchObject).then(({ data }) => {
        this.setState({ itemList: [...data.content], totalElements: data.totalElements, totalPages: data.totalPages })
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
    this.setState({ rowsPerPage: event.target.value, page: 1 }, function () {
      this.updatePageData()
    })
  }
  handleChangePage = (event, newPage) => {
    this.setPage(newPage)
  }
  //Paging handle end

  //handle popup open/close start
  handleDialogClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false,
      shouldOpenConfirmationDeleteListDialog: false,
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
    this.handleDialogClose()
    toast.success(t('toast.delete_success'));
  };
  handleDeleteListItem = (event) => {
    let { t } = this.props
    if (this.data != null) {
      this.handleDeleteList(this.data).then(() => {
        this.updatePageData();
      })
    } else {
      toast.warning(t('toast.please_select'));
    };
  }
  handleConfirmDeleteItem = () => {
    let { t } = this.props
    deleteItem(this.state.id).then(() => {
      this.updatePageData();
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
  //handle delete end

  componentDidMount() {
    this.updatePageData();
  }

  render() {
    const { t, i18n } = this.props;
    let {
      itemList,
      shouldOpenEditorDialog,
      shouldOpenConfirmationDialog,
      shouldOpenConfirmationDeleteListDialog
    } = this.state;

    let columns = [
      { title: t("de_config.name"), field: "name", width: "150" },
      { title: t("de_config.code"), field: "code", align: "left", width: "150" },
      {
        title: t("general.action"),
        field: "custom",
        align: "left",
        width: "250",
        render: rowData => <MaterialButton item={rowData}
          onSelect={(rowData, method) => {
            if (method === 0) {
              getById(rowData.id).then(({ data }) => {
                if (data.parent === null) {
                  data.parent = {};
                }
                this.handleEditItem(data);
                console.log(data);
              })
            } else if (method === 1) {
              this.handleDelete(rowData.id);
            } else {
              alert('Call Selected Here:' + rowData.id);
            }
          }}
        />
      },
    ];

    return (
      <div className="m-sm-30">
        <div className="mb-sm-30">
          <Breadcrumb routeSegments={[{ name: t('de_config.title') }]} />
        </div>
        <Grid container spacing={3}>
          <Grid item md={8} sm={12}>
            <Button
              className="mb-16 mr-16 btn btn-success"

              variant="contained"
              onClick={() => {
                this.handleEditItem({ startDate: new Date(), endDate: new Date() });
              }
              }
            >
              {t('general.button.add')}
            </Button>
            <Button
              className="mb-16 mr-36 btn btn-warning"
              variant="contained"

              onClick={() => this.setState({ shouldOpenConfirmationDeleteListDialog: true })}>
              {t('general.button.delete')}
            </Button>
            {shouldOpenConfirmationDeleteListDialog && (
              <ConfirmationDialog
                open={shouldOpenConfirmationDeleteListDialog}
                onConfirmDialogClose={this.handleDialogClose}
                onYesClick={this.handleDeleteListItem}
                title={t("confirm_dialog.delete_list.title")}
                text={t('confirm_dialog.delete_list.text')}
                agree={t("confirm_dialog.delete_list.agree")}
                cancel={t("confirm_dialog.delete_list.cancel")}
              />
            )}
          </Grid>
          <Grid item lg={4} md={4} sm={12} xs={12}>
            <SearchInput
              search={this.search}
              t={t}
            />
          </Grid>

          <Grid item xs={12}>
            <div>
              {shouldOpenEditorDialog && (
                <DeIdentificationConfigDialog t={t} i18n={i18n}
                handleClose={this.handleDialogClose}
                open={shouldOpenEditorDialog}
                handleDialogClose={this.handleDialogClose}
                updatePageData={this.updatePageData}
                item={this.state.item ? this.state.item : {}}
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
              data={itemList}
              columns={columns}
              options={{
                selection: true,
                actionsColumnIndex: -1,
                paging: false,
                search: false,
                toolbar: false,
                headerStyle: {
                  backgroundColor: "#2a80c8",
                  color: "#fff",
                },
                rowStyle: {
                  backgroundColor: 'rgb(237, 245, 251)',
                }
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

export default DeIdentificationConfigTable;
