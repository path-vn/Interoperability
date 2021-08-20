import {
    Grid,
    FormControl,
    InputLabel,
    Button,
    Input,
    Checkbox,
    TablePagination,
    InputAdornment,
    Dialog,
    DialogActions,
    IconButton,Radio
  } from "@material-ui/core";
  import React, { Component } from "react";
  import SearchIcon from "@material-ui/icons/Search";
  import CloseIcon from "@material-ui/icons/Close";
  import MaterialTable, {
    MTableToolbar,
  } from "material-table";
  import {searchByPageDto as searchByPage } from "./SampleService"
  import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
  import DialogContent from "@material-ui/core/DialogContent";
  import DialogTitle from "@material-ui/core/DialogTitle";
  import Draggable from "react-draggable";
  import Paper from "@material-ui/core/Paper";
  function PaperComponent(props) {
    return (
      <Draggable
        handle="#draggable-dialog-title"
        cancel={'[class*="MuiDialogContent-root"]'}
      >
        <Paper {...props} />
      </Draggable>
    );
  }
  class SelectSamplePopup extends React.Component {
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
      text: "",
      shouldOpenProductDialog: false,
      chidren: []
    };
  
    setPage = page => {
      this.setState({ page }, function() {
        this.updatePageData();
      });
    };
  
    setRowsPerPage = event => {
      this.setState({ rowsPerPage: event.target.value, page: 0 }, function() {
        this.updatePageData();
      });
    };
  
    handleChangePage = (event, newPage) => {
      this.setPage(newPage);
    };
  
    handleChange = (event, source) => {
      event.persist();
      this.setState({
        [event.target.name]: event.target.value
      });
    };
  
    search() {
      this.setPage(0, function() {
        var searchObject = {};
        searchObject.text = this.state.text;
        searchObject.pageIndex = this.state.page;
        searchObject.pageSize = this.state.rowsPerPage;
        searchObject.isParent = true
        searchObject.isChidren = false
        searchByPage(searchObject).then(({ data }) => {
          let { chidren } = this.state;
          // nếu đã có trong list chọn rồi thì sẽ thay trạng thái isCheck bằng true
          let itemListClone = [...data.content];
  
          itemListClone.map(item => {
            const found = chidren.find(
              obj => obj.id == item.id
            );
            if (found) {
              item.isCheck = true;
            } else {
              item.isCheck = false;
            }
          });
          this.setState(
            { itemList: itemListClone, totalElements: data.totalElements },
            () => {}
          );
        });
      });
    }
  
    updatePageData = () => {
      var searchObject = {};
      searchObject.text = this.state.text;
      searchObject.pageIndex = this.state.page + 1;
      searchObject.pageSize = this.state.rowsPerPage;
      searchObject.isParent = true
      searchObject.isChidren = false
      searchByPage(searchObject).then(({ data }) => {
        let { chidren } = this.state;
        // nếu đã có trong list chọn rồi thì sẽ thay trạng thái isCheck bằng true
        let itemListClone = [...data.content];
        itemListClone.map(item => {
          const found = chidren.find(
            obj => obj.id == item.id
          );
          if (found) {
            item.isCheck = true;
          } else {
            item.isCheck = false;
          }
        });
        this.setState(
          { itemList: itemListClone, totalElements: data.totalElements },
          () => {
          }
        );
      });
    };
  
    componentDidMount() {
      this.updatePageData();
    }
  
    handleClick = (event, item) => {
      item.isCheck = event.target.checked;
      let { chidren } = this.state;
      if (chidren == null) {
        chidren = [];
      }
      if (chidren != null &&chidren.length == 0 && item.isCheck == true) {
        chidren.push(item);
      } else {
        let itemInList = false;
        chidren.forEach(el => {
          if (el.id == item.id) {
            itemInList = true;
          }
        });
        if (!itemInList && item.isCheck == true) {
          chidren.push(item);
        } else {
          if (item.isCheck === false) {
            chidren = chidren.filter(
              proper => proper.id !== item.id
            );
          }
        }
      }
      this.setState({ chidren });
    };
  
    componentWillMount() {
      let { open, handleClose, selectedItem, chidren } = this.props;
      console.log(selectedItem)
      if(selectedItem != null && selectedItem.length > 0){
        const chidren  = [...selectedItem];
        this.setState({ chidren: chidren });
      }
    }
  
    clearKeyword = () => {
      this.setState({ text: "" }, function() {});
    };
  
    handleKeyDownEnterSearch = e => {
      if (e.key === "Enter") {
        this.search();
      }
    };
  
    handleOpenProductDialog = () => {
      this.setState({
        shouldOpenProductDialog: true
      });
    };
  
    handleDialogProductClose = () => {
      this.setState({
        shouldOpenProductDialog: false
      });
    };
  
    handleOKEditClose = () => {
      this.setState({
        shouldOpenProductDialog: false
      });
      this.updatePageData();
    };
  
    onClickRow = selectedRow => {
      document.querySelector(`#radio${selectedRow.id}`).click();
    };
  
    render() {
      const { t, i18n, handleClose, handleSelect, open } = this.props;
      let {
        text,
        shouldOpenProductDialog,
        itemList,
        chidren
      } = this.state;
      let columns = [
        {
          title: t("general.select"),
          field: "custom",
          align: "left",
          width: "250",
          render: rowData => (
            <Checkbox
              id={`radio${rowData.id}`}
              name="radSelected"
              value={rowData.id}
              checked={rowData.isCheck}
              onClick={event => this.handleClick(event, rowData)}
            />
          )
        },
        {
          title: t("Mã mẫu"),
          field: "code",
          align: "left",
          width: "150"
        },
        {
          title: t("Tên bênh nhân"),
          field: "person.name",
          width: "150"
        }
      ];
      return (
        <Dialog
          // onClose={handleClose}
          open={open}
          PaperComponent={PaperComponent}
          maxWidth={"md"}
          fullWidth
        >
          <DialogTitle style={{ cursor: "move" }} id="draggable-dialog-title">
            <span className="mb-20 styleColor">
              {t("Chọn mẫu")}
            </span>
          </DialogTitle>
          <DialogContent dividers>
            <Grid container spacing={2}>
              <Grid item md={12} xs={12}>
                <FormControl fullWidth>
                  <InputLabel htmlFor="standard-adornment-amount">
                    {t("EnterSearch")}
                  </InputLabel>
                  <Input
                    id="standard-adornment-amount"
                    name="text"
                    value={text}
                    onKeyDown={this.handleKeyDownEnterSearch}
                    onChange={this.handleChange}
                    endAdornment={
                      <InputAdornment position="end">
                        <IconButton>
                          <SearchIcon onClick={() => this.search(text)} />
                        </IconButton>
                      </InputAdornment>
                    }
                  />
                </FormControl>
              </Grid>
              <Grid item xs={12}>
                <MaterialTable
                  data={itemList}
                  columns={columns}
                  options={{
                  selection: false,
                  actionsColumnIndex: -1,
                  paging: false,
                  search: false,
                  rowStyle: rowData => ({
                    backgroundColor: (rowData.tableData.id % 2 === 1) ? '#EEE' : '#FFF',
                  }), 
                  headerStyle: {
                    backgroundColor: '#358600',
                    color:'#fff',
                  },
                  padding: 'dense',
                  toolbar: false
                }}
                  components={{
                    Toolbar: props => (
                      <div style={{ witdth: "100%" }}>
                        <MTableToolbar {...props} />
                      </div>
                    )
                  }}
                  onSelectionChange={rows => {
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
                  rowsPerPageOptions={[1, 2, 3, 5, 10, 25]}
                  component="div"
                  labelRowsPerPage={t('general.rows_per_page')}
                  labelDisplayedRows={ ({ from, to, count }) => `${from}-${to} ${t('general.of')} ${count !== -1 ? count : `more than ${to}`}`}
                  count={this.state.totalElements}
                  rowsPerPage={this.state.rowsPerPage}
                  page={this.state.page}
                  labelRowsPerPage={t("general.rows_per_page")}
                  labelDisplayedRows={({ from, to, count }) =>
                    `${from}-${to} ${t("general.of")} ${
                      count !== -1 ? count : `more than ${to}`
                    }`
                  }
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
          </DialogContent>
          <DialogActions>
            <Button
              className="mb-16 mr-36 align-bottom"
              variant="contained"
              color="secondary"
              onClick={() => handleClose()}
            >
              {t("general.cancel")}
            </Button>
            <Button
              className="mb-16 mr-16 align-bottom"
              variant="contained"
              color="primary"
              onClick={() => handleSelect(chidren)}
            >
              {t("general.select")}
            </Button>
          </DialogActions>
        </Dialog>
      );
    }
  }
  export default SelectSamplePopup;
  