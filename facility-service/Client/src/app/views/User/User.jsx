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
import { findUserByUserName, deleteItem, searchByPage, getItemById,searchByDto,getUserOrgById,getAllInfoByUserLogin } from "./UserService";
import UserEditorDialog from "./UserEditorDialog";
import { Breadcrumb, ConfirmationDialog } from "egret";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
import shortid from "shortid";
import { saveAs } from 'file-saver';
import { database } from "firebase";
import UserRoles from "app/services/UserRoles";

function MaterialButton(props) {
  const { t, i18n } = useTranslation();
  const item = props.item;
  const id = props.id
  return <div>
    {id != null && (<IconButton size="small" onClick={() => props.onSelect(item, 0)}>
      <Icon fontSize="small" color="primary">edit</Icon>
    </IconButton>)}
    {/* <IconButton onClick={() => props.onSelect(item, 1)}>
      <Icon color="error">delete</Icon>
    </IconButton> */}
  </div>;
}

class User extends Component {
  state = {
    keyword: '',
    rowsPerPage: 10,
    page: 0,
    eQAHealthOrgType: [],
    item: {},
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false,
    selectAllItem: false,
    selectedList: [],
    totalElements: 0,
    shouldOpenConfirmationDeleteAllDialog: false
  };
  numSelected = 0;
  rowCount = 0;

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
    this.setState({ rowsPerPage: event.target.value, page: 0 }, function () {
      this.updatePageData();
    });
  };

  handleChangePage = (event, newPage) => {
    this.setPage(newPage);
  };

  search() {
    this.setState({ page: 0 }, function () {
      var searchObject = {};
      searchObject.text = this.state.keyword;
      searchObject.pageIndex = 1;
      searchObject.pageSize = this.state.rowsPerPage;
      searchObject.idHealthOrg = this.state.idHealthOrg;
      searchByDto(searchObject).then(({ data }) => {
        this.setState({ itemList: [...data.content], totalElements: data.totalElements })
      });
    });
  }

  updatePageData = () => {
    var searchObject = {};
    searchObject.text = this.state.keyword;
    searchObject.pageIndex = this.state.page + 1;
    searchObject.pageSize = this.state.rowsPerPage;
    searchObject.idHealthOrg = this.state.idHealthOrg;
    searchByDto(searchObject).then(({ data }) => {
      this.setState({ itemList: [...data.content], totalElements: data.totalElements })
    });
  };

  handleDownload = () => {
    var blob = new Blob(["Hello, world!"], { type: "text/plain;charset=utf-8" });
    saveAs(blob, "hello world.txt");
  }
  handleDialogClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false,
      shouldOpenConfirmationDeleteAllDialog: false
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
    if (!UserRoles.isAdmin()) {
      getAllInfoByUserLogin().then(({data})=>{
        let idHealthOrg = data?.userOrganization?.org?.id
        this.setState({idHealthOrg : idHealthOrg}, () =>{
          this.updatePageData();
        })
      })
    }else{
      this.updatePageData();
    }
  }

  handleEditItem = item => {
    this.setState({
      item: item,
      shouldOpenEditorDialog: true
    });
  };

  handleDelete = id => {
    this.setState({
      id,
      shouldOpenConfirmationDialog: true
    });
  };

  async handleDeleteList(list) {
    for (var i = 0; i < list.length; i++) {
      await deleteItem(list[i].id);
    }
  }

  handleDeleteAll = (event) => {
    //alert(this.data.length);
    this.handleDeleteList(this.data).then(() => {
      this.updatePageData();
      this.handleDialogClose();
    }
    );
  };

  render() {
    const { t, i18n } = this.props;
    let {
      keyword,
      rowsPerPage,
      page,
      totalElements,
      itemList,
      item,
      shouldOpenConfirmationDialog,
      shouldOpenEditorDialog,
      shouldOpenConfirmationDeleteAllDialog
    } = this.state;

    let columns = [
      { title: t("username"), field: "user.username", width: "150" },
      { title: t("user.displayName"), field: "user.person.displayName", width: "150" },
      { title: t("general.email"), field: "user.email", align: "left", width: "150" },
      { title: t("healthOrg.name"), field: "org.name", align: "left", width: "150" },
      { title: t("healthOrg.type"), field: "org.type", align: "left", width: "150",
        render: rowData => 
        (rowData.org?.orgType === 0) ? <span>Hỗn hợp</span> :  (rowData.org?.orgType == 1) ? <p>Đơn vị xét nghiệm</p> :  (rowData.org?.orgType == 2) ? <p>Đơn vị lấy mẫu</p> :  (rowData.org?.orgType == 3) ? <p>Đơn vị cách ly</p> : ''
      },
      {
        title: t("Action"),
        field: "custom",
        align: "left",
        width: "250",
        render: rowData => <MaterialButton item={rowData} id= {rowData.id ? rowData.id : null}
          onSelect={(rowData, method) => {
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
    ];

    return (
      <div className="m-sm-30">

        <div className="mb-sm-30">
          <Breadcrumb routeSegments={[{ name: t('user.title') }]} />
        </div>

        <Grid container spacing={3}>
          <Grid item xs={12}>
            <Button
              className="mb-16 mr-16 align-bottom"
              variant="contained"
              color="primary"
              onClick={() => {
                this.handleEditItem({ startDate: new Date(), endDate: new Date(), isAddNew: true });
              }
              }
            >
              {t('Add')}
            </Button>
            {shouldOpenConfirmationDeleteAllDialog && (
              <ConfirmationDialog
                open={shouldOpenConfirmationDeleteAllDialog}
                onConfirmDialogClose={this.handleDialogClose}
                onYesClick={this.handleDeleteAll}
                text={t('DeleteAllConfirm')}
              />
            )}
            <TextField
              label={t('EnterSearch')}
              className="mb-16 mr-10"
              style={{minWidth: '400px'}}
              type="text"
              name="keyword"
              value={keyword}
              onKeyDown={this.handleKeyDownEnterSearch}
              onChange={this.handleTextChange} />
            <Button
              className="mb-16 mr-16 align-bottom"
              variant="contained"
              color="primary" onClick={() => this.search(keyword)}>
              {t('general.search')}
            </Button>
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

              {shouldOpenConfirmationDialog && (
                <ConfirmationDialog
                  title={t("confirm")}
                  open={shouldOpenConfirmationDialog}
                  onConfirmDialogClose={this.handleDialogClose}
                  onYesClick={this.handleConfirmationResponse}
                  text={t('DeleteConfirm')}
                />
              )}
            </div>
            <MaterialTable
              title={t('List')}
              data={itemList}
              columns={columns}
              //parentChildData={(row, rows) => rows.find(a => a.id === row.parentId)}
              parentChildData={(row, rows) => {
                var list = rows.find(a => a.id === row.parentId);
                return list;
              }}
              options={{
                selection: false,
                actionsColumnIndex: 0,
                paging: false,
                search: false,
                padding: 'dense',
                toolbar: false     
              }}
              components={{
                Toolbar: props => (
                  <MTableToolbar {...props} />
                ),
              }}
              onSelectionChange={(rows) => {
                this.data = rows;
                // this.setState({selectedItems:rows});
              }}
              actions={[
                {
                  hidden: true,
                  isFreeAction:true,
                  tooltip: 'Remove All Selected Users',
                  icon: 'delete',
                  onClick: (evt, data) => {
                    this.handleDeleteAll(data);
                    alert('You want to delete ' + data.length + ' rows');
                  }
                },
              ]}
            />
            <TablePagination
              align="left"
              className="px-16"
              rowsPerPageOptions={[1, 2, 3, 5, 10, 25, 50, 100]}
              component="div"
              count={totalElements}
              rowsPerPage={rowsPerPage}
              page={page}
              backIconButtonProps={{
                "aria-label": "Previous Page"
              }}
              nextIconButtonProps={{
                "aria-label": "Next Page"
              }}
              onChangePage={this.handleChangePage}
              onChangeRowsPerPage={this.setRowsPerPage}
            />
          </Grid>
        </Grid>
      </div>
    );
  }
}

export default User;
