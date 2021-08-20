import React, { Component } from "react";
import {
  Grid,
  IconButton,
  Icon,
  TablePagination
} from "@material-ui/core";
import MaterialTable, { MTableToolbar } from 'material-table';
import { searchByPage } from "./RoleService";
import RoleEditorDialog from "./RoleEditorDialog";
import { Breadcrumb, ConfirmationDialog } from "egret";
import { useTranslation } from 'react-i18next';
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


class Role extends Component {
  state = {
    keyword: '',
    rowsPerPage: 10,
    page: 1,
    totalPages: 0,
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false,
    shouldOpenConfirmationDeleteListDialog: false
  };
  numSelected = 0;
  rowCount = 0;

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

  updatePageData = () => {
    var searchObject = {};
    searchObject.pageIndex = this.state.page;
    searchObject.pageSize = this.state.rowsPerPage;
    searchByPage(searchObject.pageIndex, searchObject.pageSize).then(({ data }) => {
      this.setState({ itemList: [...data.content], totalElements: data.totalElements, totalPages: data.totalPages })
    });
  };

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

  componentDidMount() {
    this.updatePageData();
  }

  render() {
    const { t } = this.props;
    let {
      itemList } = this.state;

    let columns = [
      { title: t("role.name"), field: "name", width: "150" },
      { title: t("role.authority"), field: "authority", width: "150" },
      { title: t("role.createdBy"), field: "createdBy", align: "left", width: "150" }
    ];

    return (
      <div className="m-sm-30">
        <div className="mb-sm-30">
          <Breadcrumb routeSegments={[{ name: t('occupation.title') }]} />
        </div>

        <Grid container spacing={3}>
          <Grid item xs={12}>
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
                },
                rowStyle: (rowData, index) => ({
                  backgroundColor: index % 2 === 1 ? 'rgb(237, 245, 251)' : '#FFF',
                }),
                tableLayout: 'fixed'
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

export default Role;
