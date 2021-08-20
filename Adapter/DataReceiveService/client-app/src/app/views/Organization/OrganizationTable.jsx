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
import { getAllOrganization, getItemById, deleteItem } from "./OrganizationService";
import OrganizationDialog from "./OrganizationDialog";
import { Breadcrumb, ConfirmationDialog } from "egret";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
import moment from "moment";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import DeleteIcon from '@material-ui/icons/Delete';
import AddIcon from '@material-ui/icons/Add';

toast.configure({
  autoClose: 1000,
  draggable: false,
  limit: 3
});
function MaterialButton(props) {
  const { t, i18n } = useTranslation();
  const item = props.item;
  return <div>
    <IconButton onClick={() => props.onSelect(item, 0)}>
      <Icon color="primary">edit</Icon>
    </IconButton>
    <IconButton onClick={() => props.onSelect(item, 1)}>
      <Icon color="error">delete</Icon>
    </IconButton>
  </div>;
}

class OrganizationTable extends Component {
  state = {
    keyword: '',
    rowsPerPage: 10,
    page: 1,
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

  //   handleTextChange = event => {
  //     this.setState({ keyword: event.target.value }, function () {
  //     })
  //   };

  //   handleKeyDownEnterSearch = e => {
  //     if (e.key === 'Enter') {
  //       this.search();
  //     }
  //   };

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

  //   search() {
  //     this.setState({ page: 1 }, function () {
  //       var searchObject = {};
  //       searchObject.text = this.state.keyword;
  //       searchObject.pageIndex = this.state.page + 1;
  //       searchObject.pageSize = this.state.rowsPerPage;
  //       searchByPage(searchObject, this.state.page, this.state.rowsPerPage).then(({ data }) => {
  //         this.setState({ itemList: [...data.content], totalElements: data.totalElements })
  //       });
  //     });
  //   }

  updatePageData = () => {
    var searchObject = {};
    searchObject.text = this.state.keyword;
    searchObject.pageIndex = this.state.page + 1;
    searchObject.pageSize = this.state.rowsPerPage;
    getAllOrganization().then(({ data }) => {
      let listData = data.entry
      let itemList = this.state.itemList
      itemList = []

      if (listData != null && listData.length > 0) {
        listData.forEach(e => {
          let p = {}
          //dia chi
          if (e.resource.address != null && e.resource.address.length > 0) {
            let address = e.resource.address[0];
            let data = "";
            // if (address.line != null && address.line.length > 0) {
            //   data += address.line[0];
            // }
            if (address.text != null) {
              data +=  address.text
            }
            // if (address.country != null) {
            //   data += " - " + address.country
            // }
            p.address = data
          }
          //so dien thoai
          if (e.resource.telecom != null && e.resource.telecom.length > 0) {
            let phones = e.resource.telecom[0]
            let phone = ""
            if (phones.value != null) {
              phone = phones.value
            }
            p.phone = phone
          }

          //kiểu tổ chức.
          if (e.resource.type != null && e.resource.type.length > 0) {
            let types = e.resource.type[0]
            let type = ""
            if (types.text != null) {
              type = types.text
            }
            p.type = type
          }
          p.active = e.resource.active
          p.id = e.resource.id
          p.name = e.resource.name ? e.resource.name : ""
          p.resourceType = e.resource.resourceType ? e.resource.resourceType : ""

          itemList.push(p)
        })
      }


      this.setState({ itemList }, () => {
        console.log(itemList)
      })
    }
    );
  };

  handleDialogClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false,
      shouldOpenConfirmationDeleteAllDialog: false
    },()=>{
      this.updatePageData();
    });
  };

  handleOKEditClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false
    });
    this.updatePageData();
  };

  handleConfirmationResponse = () => {
    let { t } = this.props
    deleteItem(this.state.id).then(() => {
      this.updatePageData();
      this.handleDialogClose();
      toast.success(t("Delete Success"))
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




    handleDelete = id => {
      this.setState({
        id,
        shouldOpenConfirmationDialog: true
      });
    };

  //   async handleDeleteList(list) {
  //     for (var i = 0; i < list.length; i++) {
  //       await deleteItem(list[i].id);
  //     }
  //   }

  //   handleDeleteAll = (event) => {
  //     //alert(this.data.length);
  //     this.handleDeleteList(this.data).then(() => {
  //       this.updatePageData();
  //       this.handleDialogClose();
  //     }
  //     );
  //   };

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
      { title: t("Name"), field: "name", width: "150" },
      { title: t("Address"), field: "address", align: "left", width: "150" },
      { title: t("Phone"), field: "phone", align: "left", width: "150" },
      { title: t("Resource Type"), field: "resourceType", align: "left", width: "150" },
      { title: t("Type Organization"), field: "type", align: "left", width: "150" },
      {
        title: t("Active"), field: "active", align: "left", width: "150",
        render: rowData => rowData.active ? <span>active</span> : <span>inactive</span>
      },
      {
        title: t("Action"),
        field: "custom",
        type: 'numeric',
        width: "250",
        render: rowData => <MaterialButton item={rowData}
          onSelect={(rowData, method) => {
            if (method === 0) {
                getItemById(rowData.id).then(({ data }) => {
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
          <Breadcrumb routeSegments={[{ name: t('Organization') }]} />
        </div>

        <Grid container spacing={3}>
          <Grid item xs={12}>
            <Button
              className="mb-16 mr-16 align-bottom"
              variant="contained"
              color="primary"
              onClick={() => {
                this.handleEditItem({ startDate: new Date(), endDate: new Date() });
              }
              }
            >
              {t('Add new')}
            </Button>
            {/* <Button className="mb-16 mr-36 align-bottom" variant="contained" color="primary"
              onClick={() => this.setState({ shouldOpenConfirmationDeleteAllDialog: true })}>
              {t('Delete')}
            </Button> */}

            {/* {shouldOpenConfirmationDeleteAllDialog && (
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
              type="text"
              name="keyword"
              value={keyword}
              onKeyDown={this.handleKeyDownEnterSearch}
              onChange={this.handleTextChange} />
            <Button
              className="mb-16 mr-16 align-bottom"
              variant="contained"
              color="primary" onClick={() => this.search(keyword)}>
              {t('Search')}
            </Button> */}
          </Grid>
          <Grid item xs={12}>
            <div>
              {shouldOpenEditorDialog && (
                <OrganizationDialog t={t} i18n={i18n}
                  handleClose={this.handleDialogClose}
                  open={shouldOpenEditorDialog}
                  handleOKEditClose={this.handleOKEditClose}
                  item={item}
                />
              )}

              {shouldOpenConfirmationDialog && (
                <ConfirmationDialog
                  title={t("general.confirm")}
                  open={shouldOpenConfirmationDialog}
                  onConfirmDialogClose={this.handleDialogClose}
                  onYesClick={this.handleConfirmationResponse}
                  text={t('general.deleteConfirm')}
                  cancel = {t("general.cancel")}
                  agree = {t("general.agree")}
                />
              )}
            </div>
            <MaterialTable
              title={t('List')}
              data={itemList ? itemList : []}
              columns={columns}
              //parentChildData={(row, rows) => rows.find(a => a.id === row.parentId)}

              options={{
                selection: true,
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
                tableLayout: 'fixed'
              }}
              components={{
                Toolbar: (props) => <MTableToolbar {...props} />,
              }}
              onSelectionChange={(rows) => {
                this.data = rows;
              }}
            //   actions={[
            //     {
            //       tooltip: 'Remove All Selected Users',
            //       icon: 'delete',
            //       onClick: (evt, data) => {
            //         this.handleDeleteAll(data);
            //         alert('You want to delete ' + data.length + ' rows');
            //       }
            //     },
            //   ]}
            />
            {/* <TablePagination
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
            /> */}
          </Grid>
        </Grid>
      </div>
    );
  }
}

export default OrganizationTable;
