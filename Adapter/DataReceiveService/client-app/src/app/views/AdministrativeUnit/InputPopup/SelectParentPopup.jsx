import { Grid, TextField, Button, Radio, Dialog, DialogActions } from "@material-ui/core";
import React from "react";
import MaterialTable, { } from 'material-table';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import Draggable from 'react-draggable';
import Paper from '@material-ui/core/Paper';
import { searchByPage, getByRoot, getAllChildByParentId } from "../AdministrativeUnitService";
import SearchInput from '../../Component/SearchInput/SearchInput';
import NicePagination from '../../Component/Pagination/NicePagination';
import Autocomplete from "@material-ui/lab/Autocomplete";
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
    // this.handleChange = this.handleChange.bind(this);
  }
  state = {
    rowsPerPage: 5,
    page: 1,
    data: [],
    totalElements: 0,
    totalPages: 0,
    itemList: [],
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false,
    selectedItem: {},
    keyword: '',
    shouldOpenProductDialog: false,
    item: null
  };

  setPage = page => {
    this.setState({ page: page, itemList: null }, function () {
      this.updatePageData();
    })
  };

  setRowsPerPage = event => {
    this.setState({ rowsPerPage: event.target.value, page: 1 }, function () {
      this.updatePageData();
    })
  };

  handleChangePage = (event, newPage) => {
    this.setPage(newPage);
  };

  updatePageData = () => {
    var searchObject = {};
    searchObject.text = this.state.keyword;
    searchObject.pageIndex = this.state.page;
    searchObject.pageSize = this.state.rowsPerPage;
    searchObject.parentId = this.props.parentId ? this.props.parentId : ""
    searchObject.adminUnitId = this.state.districtId ? this.state.districtId : (this.state.rootUnit ? this.state.rootUnit : null)
    searchObject.level = this.props.level;
    searchByPage(searchObject).then(({ data }) => {
      this.setState({ itemList: [...data.content], totalElements: data.totalElements, totalPages: data.totalPages })
    });

  }

  componentDidMount() {
    this.updatePageData();
  }

  handleClick = (event, item) => {
    let { selectedValue } = this.state;
    if (selectedValue === item.id) {
      this.setState({ selectedValue: null, selectedItem: null });
    } else {
      this.setState({ selectedValue: item.id, selectedItem: item });
    }
  }

  componentWillMount() {

    let { open, handleClose, selectedItem } = this.props;
    //this.setState(item);
    this.setState({
      selectedValue: selectedItem.id,
      selectedItem: selectedItem
    });
    // getByRoot().then(({ data }) => {
    //   this.setState({ listRootUnit: [...data] })
    // })
  }

  search = (item) => {
    // console.log(this.props.level)
    // this.setState({ page: 1 }, function () {
    //   var searchObject = {};
    //   if (type == "text") {
    //     this.state.text = keyword
    //   }

    //   searchObject.text = this.state.text;
    //   searchObject.pageIndex = this.state.page;
    //   searchObject.pageSize = this.state.rowsPerPage;
    //   searchObject.parentId = this.props.parentId ? this.props.parentId : this.props.orgId;
    //   searchObject.adminUnitId = this.state.districtId ? this.state.districtId : (this.state.rootUnit ? this.state.rootUnit : null);
    //   searchObject.level = this.props.level;
    //   searchByPage(searchObject).then(({ data }) => {
    //     this.setState({
    //       itemList: [...data.content],
    //       totalElements: data.totalElements,
    //       totalPages: data.totalPages
    //     })
    //   });
    // });
    var searchObject = {};
    if (item != null) {
      this.setState({
        page: 1,
        text: item.text,
        orgType: item.orgType,
      }, () => {
        searchObject.text = this.state.text;
        searchObject.pageIndex = this.state.page;
        searchObject.pageSize = this.state.rowsPerPage;
        searchObject.orgType = this.state.orgType
        searchByPage(searchObject).then(({ data }) => {
          this.setState({
            itemList: [...data.content],
            totalElements: data.totalElements,
            totalPages: data.totalPages
          })
          var treeValues = [];

          let itemListClone = [...data.content];

          itemListClone.forEach(item => {
            var items = this.getListItemChild(item);
            treeValues.push(...items);
          })
        }
        );
      })
    }
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

  getListItemChild(item) {
    var result = [];
    var root = {};
    root.name = item.name;
    root.code = item.code;
    root.id = item.id;
    root.description = item.description;
    root.displayOrder = item.displayOrder;
    root.foundedDate = item.foundedDate;
    root.parentId = item.parentId;
    result.push(root);
    if (item.children) {
      item.children.forEach(child => {
        var childs = this.getListItemChild(child);
        result.push(...childs);
      });
    }
    return result;
  }
  onClickRow = (selectedRow) => {
    document.querySelector(`#radio${selectedRow.id}`).click();
  }
  changeSelected = (value, source) => {
    if (value == null) {
      this.setState({ change: true }, () => {
        this.search(this.state.keyword)
      })
    }
    if (source == "rootUnit") {
      this.setState({ rootUnit: value ? (value.id ? value.id : null) : null, listDistrict: value ? [] : null, district: null }, () => {

        if (value != null && value.id != null) {
          getAllChildByParentId(value.id).then(({ data }) => {
            this.setState({ listDistrict: [...data] }, () => {
              this.search(this.state.keyword)
            })
          })
        } else {
          this.search(this.state.keyword)
        }
      })
    }
    if (source == "district") {
      this.setState({ districtId: value ? (value.id ? value.id : null) : null, district: value ? value : null }, () => {
        this.search(this.state.keyword)
      })
    }

  }
  render() {
    const { t, i18n, handleClose, handleSelect, selectedItem, open } = this.props;
    let { keyword, shouldOpenProductDialog, itemList } = this.state;
    let columns = [
      {
        title: t("general.button.select"),
        field: "custom",
        align: "left",
        width: "250",
        render: rowData => <Radio id={`radio${rowData.id}`} name="radSelected" value={rowData.id} checked={this.state.selectedValue === rowData.id} onClick={(event) => this.handleClick(event, rowData)}
        />
      },
      { title: t("administrative_unit.code"), field: "code", align: "left", width: "150" },
      { title: t("administrative_unit.name"), field: "name", width: "150" },
    ];
    return (
      <Dialog onClose={handleClose} open={open} PaperComponent={PaperComponent} maxWidth={'md'} fullWidth>
        <DialogTitle style={{ cursor: 'move' }} id="draggable-dialog-title">
          <span className="mb-20">{t("administrative_unit.select")}</span>
        </DialogTitle>
        <DialogContent>
          <Grid className="mb-16" container spacing={2}>
            <Grid item sm={4} xs={12}>
            </Grid>
            <Grid item sm={4} xs={12}>
            </Grid>
            <Grid item sm={4} xs={12}>
              <SearchInput
                search={this.search}
                t={t}
              />

            </Grid>
          </Grid>
          <Grid item xs={12}>
            <MaterialTable
              data={this.state.itemList ? this.state.itemList : []}
              columns={columns}
              onRowClick={(evt, selectedRow) => this.onClickRow(selectedRow)}
              parentChildData={(row, rows) => {
                var list = rows.find((a) => a.id === row.parentId);
                return list;
              }}
              options={{
                selection: false,
                actionsColumnIndex: -1,
                paging: false,
                search: false,
                toolbar: false,
                headerStyle: {
                  backgroundColor: "#337ab7",
                  color: "#fff",
                },
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
        </DialogContent>
        <div className='dialog-footer'>
          <DialogActions>
            <Button
              className="mb-16 mr-36 btn btn-secondary"
              variant="contained"
              onClick={() => handleClose()}>{t('general.button.cancel')}</Button>
            <Button
              className="mb-16 mr-16 btn btn-success"
              variant="contained"
              onClick={() => handleSelect(this.state.selectedItem)}>
              {t('general.button.select')}
            </Button>
          </DialogActions>
        </div>

      </Dialog>
    )
  }
}
export default SelectParentPopup;