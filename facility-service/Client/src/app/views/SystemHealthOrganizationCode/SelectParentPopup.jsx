import { Grid, InputAdornment, Input, Button, TablePagination, Radio, Dialog, DialogActions } from "@material-ui/core";
import React from "react";
import MaterialTable, { MTableToolbar } from 'material-table';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import Draggable from 'react-draggable';
import Paper from '@material-ui/core/Paper';
import { getByPage } from "../HealthOrganization/HealthOrganizationService";
import { Link } from "react-router-dom";
import SearchIcon from '@material-ui/icons/Search';
function PaperComponent(props) {
  return (
    <Draggable handle="#draggable-dialog-title" cancel={'[class*="MuiDialogContent-root"]'}>
      <Paper {...props} />
    </Draggable>
  );
}
class SelectParentPopup extends React.Component {
  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
  }
  state = {
    rowsPerPage: 5,
    page: 0,
    data: [],
    totalElements: 0,
    itemList: [],
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false,
    selectedItem: {},
    keyword: '',
    shouldOpenProductDialog: false,
    item: null
  };

  setPage = page => {
    this.setState({ page }, function () {
      this.updatePageData();
    })
  };

  setRowsPerPage = event => {
    //debugger
    this.setState({ rowsPerPage: event.target.value, page: 0 }, function () {
      debugger
      //this.updatePageData();
      this.search();
    })
  };

  handleChangePage = (event, newPage) => {
    //this.setPage(newPage);
    debugger
    this.updateData(newPage);
  };
  updateData(pageNumber){
    this.setState({ page: pageNumber }, function () {
      var searchObject = {};
      searchObject.text = this.state.keyword;
      searchObject.pageIndex = this.state.page + 1;
      searchObject.pageSize = this.state.rowsPerPage;
      getByPage(searchObject, this.state.page, this.state.rowsPerPage).then(({ data }) => {
        this.setState({ itemList: [...data.content], totalElements: data.totalElements })
      });
    });
  }

  updatePageData = () => {
    var searchObject = {};
    searchObject.keyword = this.state.keyword;
    searchObject.pageIndex = this.state.page + 1;
    searchObject.pageSize = this.state.rowsPerPage;
    getByPage(searchObject).then(({ data }) => {
      this.setState({ itemList: [...data.content], totalElements: data.totalElements })
    });
  }

  componentDidMount() {
    this.updatePageData();
  }

  handleClick = (event, item) => {
    //alert(item);
    if (item.id != null) {
      this.setState({ selectedValue: item.id, selectedItem: item });
    } else {
      this.setState({ selectedValue: null, selectedItem: null });
    }
  }

  componentWillMount() {
    let { open, handleClose, selectedItem } = this.props;
    //this.setState(item);
    this.setState({ selectedValue: selectedItem.id });
  }
  getName = (item) => {
    var address = item.name;
    if(item.parent != null){
      address = item.name+'-'+item.parent.name;
      if(item.parent.parent != null){
        address +='-'+ item.parent.parent.name;
      }
    }
    return address;
  } 
  handleKeyDownEnterSearch = e => {
    if (e.key === 'Enter') {
      this.search();
    }
  };

  search() {
    this.setState({ page: 0 }, function () {
      var searchObject = {};
      searchObject.text = this.state.keyword;
      searchObject.pageIndex = this.state.page + 1;
      searchObject.pageSize = this.state.rowsPerPage;
      getByPage(searchObject, this.state.page, this.state.rowsPerPage).then(({ data }) => {
        this.setState({ itemList: [...data.content], totalElements: data.totalElements })
      });
      //this.updatePageData();
    });
  }

  handleChange = (event, source) => {
    event.persist();
    this.setState({
      [event.target.name]: event.target.value
    }, function () {
       this.search();
    });
  };

  handleOpenProductDialog = () => {
    this.setState({
      shouldOpenProductDialog: true
    })
  }

  handleDialogProductClose = () => {
    this.setState({
      shouldOpenProductDialog: false
    })
  }

  handleOKEditClose = () => {
    this.setState({
      shouldOpenProductDialog: false
    });
    this.updatePageData();
  };

  onClickRow = (selectedRow) => {
    document.querySelector(`#radio${selectedRow.id}`).click();
  }

  render() {
    const { t, i18n, handleClose, handleSelect, selectedItem, open } = this.props;
    let { keyword, shouldOpenProductDialog, itemList } = this.state;
    let columns = [
      {
        title: t("general.select"),
        field: "custom",
        align: "left",
        width: "250",
        render: rowData => <Radio id={`radio${rowData.id}`} name="radSelected" value={rowData.id} checked={this.state.selectedValue === rowData.id} onClick={(event) => this.handleClick(event, rowData)}
         />
      },
      { title: t("general.code"), field: "code", align: "left", width: "150" },
      { title: t("general.name"),
        field: "name",
        width: "150",
        render: (item) => this.getName(item)
       },
    ];
    return (
      <Dialog onClose={handleClose} open={open} PaperComponent={PaperComponent} maxWidth={'md'} fullWidth>
        <DialogTitle style={{ cursor: 'move' }} id="draggable-dialog-title">
          <span className="mb-20">{""}</span>
        </DialogTitle>
        <DialogContent>
          <Grid item xs={12}>
            <Input
              label={t('general.enterSearch')}
              type="text"
              name="keyword"
              value={keyword}
              onChange={this.handleChange}
              onKeyDown={this.handleKeyDownEnterSearch}
              onKeyUp={this.handleKeyUp}
              style={{ width: '50%' }}
              className="mb-16 mr-12"
              id="search_box"
              placeholder={t('general.enterSearch')}
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
            {/* { itemList.length !== 0 ? '' :
                <Button className="mb-16 mr-16 align-bottom"
                  variant="contained"
                  color="primary"
                  // onClick={() => this.handleOpenProductDialog()} 
                  onClick={() => this.handleAddItem()}
                  >
                  {t('Product.add')}
                </Button>
              } */}
          </Grid>
          <Grid item xs={12}>

            <MaterialTable
              data={itemList}
              columns={columns}
              onRowClick={((evt, selectedRow) => this.onClickRow(selectedRow))}
              parentChildData={(row, rows) => {
                var list = rows.find(a => a.id === row.parentId);
                return list;
              }}
              options={{
                toolbar: false,
                selection: false,
                actionsColumnIndex: -1,
                paging: false,
                search: false
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
            />
            <TablePagination
              align="left"
              className="px-16"
              rowsPerPageOptions={[1, 2, 3, 5, 10, 25]}
              component="div"
              count={this.state.totalElements}
              rowsPerPage={this.state.rowsPerPage}
              labelRowsPerPage={t('general.rows_per_page')}
              labelDisplayedRows={({ from, to, count }) => `${from}-${to} ${t('general.of')} ${count !== -1 ? count : `more than ${to}`}`}
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
          </Grid>
        </DialogContent>
        <DialogActions>
          <Button
            className="mb-16 mr-36 align-bottom"
            variant="contained"
            color="secondary"
            onClick={() => handleClose()}
            >
            {t('general.cancel')}</Button>
          <Button className="mb-16 mr-16 align-bottom"
            variant="contained"
            color="primary"
            onClick={() => handleSelect(this.state.selectedItem)}
            >
            {t('general.select')}
          </Button>
        </DialogActions>
      </Dialog>
    )
  }
}
export default SelectParentPopup;