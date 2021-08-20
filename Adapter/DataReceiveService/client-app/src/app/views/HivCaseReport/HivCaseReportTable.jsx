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
import { getAll, getById } from "./HivCaseReportService";
import HivCaseReportEditorDialog from "./HivCaseReportEditorDialogNew";
import { Breadcrumb, ConfirmationDialog } from "egret";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
import shortid from "shortid";
import { saveAs } from 'file-saver';
import NiceActionButton from '../Component/Table/NiceActionButton';

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

class HivCaseReportTable extends Component {
  state = {
    keyword: '',
    rowsPerPage: 5,
    page: 0,
    itemList: [],
    item: {},
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false,
    selectAllItem: false,
    selectedList: [],
    totalElements: 0,
    shouldOpenConfirmationDeleteAllDialog: false
  };


  setRowsPerPage = event => {
    //debugger
    this.setState({ rowsPerPage: event.target.value, page: 0 }, function () {
      this.search();
    })
  };

  handleChangePage = (event, newPage) => {
    //this.setPage(newPage);
    //debugger
    this.updateData(newPage);
  };
  updateData(pageNumber){
    this.setState({ page: pageNumber }, function () {
      var pageIndex = this.state.page + 1;
      var pageSize = this.state.rowsPerPage;
      getAll(pageIndex, pageSize).then(({ data }) => {
        this.setState({ itemList: [...data.content], totalElements: data.totalElements })
      });
    });
  }
  componentDidMount() {
    this.updatePageData();
  }
  updatePageData = () => {
    //debugger
    var pageIndex = this.state.page + 1;
    var pageSize = this.state.rowsPerPage;
    getAll(pageIndex, pageSize).then(({ data }) => {
      //debugger
      if(data){
        this.setState({ itemList: [...data.content], totalElements: data.totalElements })
      }else{
        this.setState({itemList: this.state.itemList})
      }
    }
    );
  };
  
  search() {
    this.setState({ page: 0 }, function () {
      //debugger
      var pageIndex = this.state.page + 1;
      var pageSize = this.state.rowsPerPage;
      getAll(pageIndex, pageSize).then(({ data }) => {
        this.setState({ itemList: [...data.content], totalElements: data.totalElements })
      });
      //this.updatePageData();
    });
  }
  handleEditItem = item => {
    this.setState({
        item: item,
        shouldOpenEditorDialog: true
      });
  }
  handleDialogClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false,
      shouldOpenConfirmationDeleteAllDialog: false
    });
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
      { title: t("Tiêu đề"), field: "title", width: "150" },
      { title: t("Nguồn"), field: "systemId", align: "left", width: "150" },
      {
        title: t('general.action'),
        field: "custom",
        type: 'numeric',
        width: "250",
        render: rowData =>
          <>
            <NiceActionButton
              item={rowData}
              size="small"
              fontSize="small"
              color="primary"
              icon="edit"
              onSelect={(rowData) => {
                getById(rowData.id).then(({ data }) => {
                  this.handleEditItem(data);
                  console.log(data);
                })
              }}
            />
            {/* <NiceActionButton
              item={rowData}
              size="small"
              fontSize="small"
              color="error"
              icon="delete"
              onSelect={(rowData) => {
                this.handleDelete(rowData.id);
              }}
            /> */}
          </>
      },
    ];

    return (
      <div className="m-sm-30">
        <div className="mb-sm-30">
          <Breadcrumb routeSegments={[{ name: t('Báo cáo trường hợp HIV') }]} />
        </div>
        <Grid container spacing={3}>
          <Grid item xs={12}>
            {shouldOpenConfirmationDeleteAllDialog && (
              <ConfirmationDialog
                open={shouldOpenConfirmationDeleteAllDialog}
                onConfirmDialogClose={this.handleDialogClose}
                onYesClick={this.handleDeleteAll}
                text={t('DeleteAllConfirm')}
              />
            )}
          </Grid>
          <Grid item xs={12}>
            <div>
              {shouldOpenEditorDialog && (
                <HivCaseReportEditorDialog t={t} i18n={i18n}
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
              title={t('Danh sách báo cáo')}
              data={itemList}
              columns={columns}
              options={{
                selection: false,
                actionsColumnIndex: -2,
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
                // tableLayout: 'fixed'
              }} 
              components={{
                Toolbar: props => (
                  <MTableToolbar {...props} />
                ),
              }}
              onSelectionChange={(rows) => {
                this.data = rows;
              }}
              actions={[
                {
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

export default HivCaseReportTable;
