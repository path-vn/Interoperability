import {
  Grid, MuiThemeProvider, TextField, FormControl, Button, Input,
  InputAdornment, TableHead, TableCell, TableRow, Checkbox, TablePagination, Radio, Dialog, Icon, IconButton
} from "@material-ui/core";
import { createMuiTheme } from "@material-ui/core/styles";
import React, { Component } from "react";
import ReactDOM from "react-dom";
import MaterialTable, { MTableToolbar, Chip, MTableBody, MTableHeader } from 'material-table';
import { useTranslation, withTranslation, Trans } from 'react-i18next';
import { searchByPage } from '../SuspectedPerson/SuspectedPersonService';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Draggable from 'react-draggable';
import Paper from '@material-ui/core/Paper';
import { Link } from "react-router-dom";
import SearchIcon from "@material-ui/icons/Search";
function PaperComponent(props) {
  return (
    <Draggable handle="#draggable-dialog-title" cancel={'[class*="MuiDialogContent-root"]'}>
      <Paper {...props} />
    </Draggable>
  );
}
class SuspectedPersonSearchDialog extends React.Component {
  state = {
    rowsPerPage: 10,
    page: 0,
    suspectedPersonList: [],
    item: {},
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false,
    selectedItem: {},
    keyword: ''
  };

  setPage = page => {
    this.setState({ page }, function () {
      this.updatePageData();
    });
  };

  handleKeyDownEnterSearch = e => {
    if (e.key === 'Enter') {
      this.search();
    }
  };
  setRowsPerPage = event => {
    this.setState({ rowsPerPage: event.target.value, page: 0 }, function () {
      this.updatePageData();
    });
  };

  handleChangePage = (event, newPage) => {
    this.setPage(newPage);
  };

  updatePageData = () => {
    var searchObject = {};
    searchObject.text = this.state.keyword;
    searchObject.pageIndex = this.state.page + 1;
    searchObject.pageSize = this.state.rowsPerPage;
    searchByPage(searchObject).then(({ data }) => {
      this.setState({
        suspectedPersonList: [...data.content], totalElements: data.totalElements
      }, () => {
      });
    }
    );
  };

  handleChange = (event) => {
    this.setState({ keyword: event.target.value }, function () { });
  };

  componentDidMount() {
    this.updatePageData(this.state.page, this.state.rowsPerPage);
  }

  handleClick = (event, item) => {
    if (item.id != null) {
      this.setState({ selectedValue: item.id, selectedItem: item });
    } else {
      this.setState({ selectedValue: item.id, selectedItem: null });
    }
  }
  componentWillMount() {
    let { open, handleClose, item } = this.props;
    if (item != null && item.id != null) {
      this.setState({ selectedValue: item.id, selectedItem: item });
    }
  }
  search = () => {
    var searchObject = {};
    searchObject.text = this.state.keyword;
    searchObject.pageIndex = this.state.page + 1;
    searchObject.pageSize = this.state.rowsPerPage;
    searchByPage(searchObject).then(({ data }) => {
      this.setState({
        suspectedPersonList: [...data.content], totalElements: data.totalElements
      });
    }
    );
  };
  render() {
    const { t, i18n, handleClose, handleSelect, selectedItem, open } = this.props;
    let {
      rowsPerPage,
      page,
      keyword,
      suspectedPersonList,
      shouldOpenEditorDialog
    } = this.state;

    let columns = [
      {
        title: t("general.action"),
        field: "custom",
        align: "left",
        width: "250",
        render: rowData => <Radio name="radSelected" value={rowData.id} checked={this.state.selectedValue === rowData.id} onClick={(event) => this.handleClick(event, rowData)}
        />
      },
      { title: t('suspected_person.name'), field: "name", align: "left", width: "150" },
      { title: t("suspected_person.phone"), field: "phoneNumber", width: "150" },
      { title: t("suspected_person.idNumber"), field: "idNumber", width: "150" }
    ]

    return (
      <Dialog onClose={handleClose} open={open} PaperComponent={PaperComponent} maxWidth={'lg'} fullWidth={true}>
        <DialogTitle style={{ cursor: 'move' }} id="draggable-dialog-title">
          <span className="mb-20">{t("suspected_person.title")}</span>
          <IconButton style={{ position: "absolute", right: "10px", top: "10px" }} onClick={() => handleClose()}><Icon color="error"
            title={t("close")}>
            close
            </Icon>
          </IconButton>
        </DialogTitle>
        <DialogContent dividers>
          <Grid container spacing={2}>
            <Grid item md={6} sm={6} xs={12}>
            </Grid>
            <Grid item md={6} sm={6} xs={12}>
              <FormControl fullWidth>
                <Input
                  className="search_box w-100 mb-10 stylePlaceholder"
                  type="text"
                  name="keyword"
                  value={keyword}
                  onChange={this.handleChange}
                  onKeyDown={this.handleKeyDownEnterSearch}
                  placeholder={t("general.search")}
                  id="search_box"
                  startAdornment={
                    <InputAdornment>
                      <Link to="#">
                        {" "}
                        <SearchIcon
                          onClick={() => this.search(keyword)}
                          style={{
                            position: "absolute",
                            top: "0",
                            right: "0"
                          }}
                        />
                      </Link>
                    </InputAdornment>
                  }
                />
              </FormControl>
            </Grid>
          </Grid>
          <Grid item xs={12}>
            <MaterialTable title='' data={this.state.suspectedPersonList} columns={columns}
              parentChildData={(row, rows) => {
                var list = rows.find(a => a.id === row.parentId);
                return list;
              }}
              options={{
                selection: false,
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
              components={{
                Toolbar: props => (
                  <div style={{ width: "100%" }}>
                    <MTableToolbar {...props} />
                  </div>
                ),
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
            />
            <TablePagination
              align="left"
              className="px-16"
              rowsPerPageOptions={[1, 2, 3, 5, 10, 25, 50, 100]}
              component="div"
              labelRowsPerPage={t('general.rows_per_page')}
              labelDisplayedRows={({ from, to, count }) => `${from}-${to} ${t('general.of')} ${count !== -1 ? count : `more than ${to}`}`}
              count={this.state.totalElements}
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
        </DialogContent>
        <DialogActions>
          <Button className="" variant="contained" color="secondary" onClick={() => handleClose()}>
            {t('general.cancel')}
          </Button>
          <Button className=" mr-16" variant="contained" color="primary" onClick={() => handleSelect(this.state.selectedItem)}>
            {t('general.save')}
          </Button>
        </DialogActions>
      </Dialog>
    )
  }
}
export default SuspectedPersonSearchDialog;