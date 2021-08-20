import React, { Component } from "react";
import {
  Grid,
  IconButton,
  Icon,
  Button
} from "@material-ui/core";
import MaterialTable, { } from 'material-table';
import { deleteItem, searchByDto, getUserOrgById, getAllInfoByUserLogin } from "./UserService";
import UserEditorDialog from "./UserEditorDialog";
import { Breadcrumb, ConfirmationDialog } from "egret";
import { useTranslation } from 'react-i18next';
import UserRoles from "app/services/UserRoles";
import 'styles/globitsStyles.css';
import SearchInput from '../Component/SearchInput/SearchInput';
import NicePagination from '../Component/Pagination/NicePagination';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
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
    <IconButton size="small" onClick={() => props.onSelect(item, 0)}>
      <Icon fontSize="small" color="primary">edit</Icon>
    </IconButton>
    {/* <IconButton size="small" onClick={() => props.onSelect(item, 1)}>
      <Icon fontSize="small" color="error">delete</Icon>
    </IconButton> */}
  </div>;
}
class User extends Component {
  state = {
    text: '',
    rowsPerPage: 10,
    page: 1,
    totalPages: 0,
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false
  }

  setPage = page => {
    var searchObject = {};
    this.setState({ page }, function () {
      searchObject.text = this.state.text;
      searchObject.pageIndex = this.state.page;
      searchObject.idHealthOrg = this.state.idHealthOrg;
      this.updatePageData(searchObject);
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

  // search = (keyword) => {
  //   this.setState({ page: 1 }, function () {
  //     var searchObject = {};
  //     this.state.keyword = keyword
  //     searchObject.text = this.state.keyword;
  //     searchObject.pageIndex = this.state.page;
  //     searchObject.pageSize = this.state.rowsPerPage;
  //     searchObject.idHealthOrg = this.state.idHealthOrg;
  //     searchByDto(searchObject).then(({ data }) => {
  //       this.setState({ itemList: [...data.content], totalElements: data.totalElements, totalPages: data.totalPages })
  //     });
  //   });
  // }

  updatePageData = (item) => {
    var searchObject = {};
    if (item != null) {
      this.setState({
        page: 1,
        text: item.text,
        idHealthOrg: item.idHealthOrg,
      }, () => {
        searchObject.text = this.state.text;
        searchObject.pageIndex = this.state.page;
        searchObject.pageSize = this.state.rowsPerPage;
        searchObject.idHealthOrg = this.state.idHealthOrg;
        searchByDto(searchObject).then(({ data }) => {
          this.setState({ itemList: [...data.content], totalElements: data.totalElements, totalPages: data.totalPages })
        });
      })
    } else {
      searchObject.text = this.state.text;
      searchObject.pageIndex = this.state.page;
      searchObject.pageSize = this.state.rowsPerPage;
      searchByDto(searchObject).then(({ data }) => {
        this.setState({ itemList: [...data.content], totalElements: data.totalElements, totalPages: data.totalPages })
      });
    }
    
  };

  handleDialogClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false
    });
  };

  handleOKEditClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false
    });
    this.updatePageData();
  };

  handleDeleteUser = id => {
    this.setState({
      id,
      shouldOpenConfirmationDialog: true
    });
  };

  handleEditUser = item => {
    getUserOrgById(item.id).then((result) => {
      this.setState({
        item: result.data,
        shouldOpenEditorDialog: true
      });
    });
  };

  handleConfirmationResponse = () => {
    deleteItem(this.state.id).then(() => {
      this.updatePageData();
      this.handleDialogClose();
    });
  };

  componentDidMount() {
    // if (!UserRoles.isAdmin()) {
    //   getAllInfoByUserLogin().then(({ data }) => {
    //     let idHealthOrg = data?.userOrganization?.org?.id
    //     this.setState({ idHealthOrg: idHealthOrg }, () => {
    //       this.updatePageData();
    //     })
    //   })
    // } else {
    //   this.updatePageData();
    // }
    this.updatePageData();
  }

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
        width: "250",
        render: rowData => <MaterialButton item={rowData} id={rowData.id ? rowData.id : null}
          onSelect={(rowData, method) => {
            console.log(rowData);
            if (method === 0) {
              getUserOrgById(rowData.id).then(({ data }) => {
                if (data.parent === null) {
                  data.parent = {};
                }
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
      { title: t("user.username"), field: "user.username", width: "150" },
      { title: t("user.display_name"), field: "user.person.displayName", width: "150" },
      { title: t("user.email"), field: "user.email", align: "left", width: "150" },
      { title: t("user.org.title"), field: "org.name", align: "left", width: "150" },
    ];

    return (
      <div className="m-sm-30">
        <div className="mb-sm-30">
          <Breadcrumb routeSegments={[
            { name: t('navigation.manage.list_user') }
            ]} />
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
          </Grid>
          <Grid item lg={6} md={6} sm={12} xs={12}>
            <SearchInput
              search={this.updatePageData}
              t={t}
            />
          </Grid>
          <Grid item xs={12}>
            <div>
              {shouldOpenEditorDialog && (
                <UserEditorDialog t={t} i18n={i18n}
                  handleClose={this.handleDialogClose}
                  open={shouldOpenEditorDialog}
                  handleOKEditClose={this.handleOKEditClose}
                  item={item}
                />
              )}

              {/* {shouldOpenConfirmationDialog && (
                <ConfirmationDialog
                  title={t("confirm")}
                  open={shouldOpenConfirmationDialog}
                  onConfirmDialogClose={this.handleDialogClose}
                  onYesClick={this.handleConfirmationResponse}
                  text={t('DeleteConfirm')}
                />
              )} */}
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
                selection: false,
                actionsColumnIndex: -1,
                paging: false,
                search: false,
                toolbar: false,
                maxBodyHeight: "440px",
                headerStyle: {
                  backgroundColor: "#2a80c8",
                  color: "#fff",
                  whiteSpace: 'nowrap'
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
              totalElements={this.state.totalElements}
              page={this.state.page}
            />
          </Grid>
        </Grid>
      </div>
    );
  }
}

export default User;
