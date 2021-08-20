import { Grid, Button, Radio, Dialog, DialogActions } from "@material-ui/core";
import React from "react";
import MaterialTable, { } from 'material-table';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import Draggable from 'react-draggable';
import Paper from '@material-ui/core/Paper';
import { searchByPage, getOrgByType, getOrgByTypes } from "../../HealthOrg/HealthOrgService";
import SearchInput from '../../Component/SearchInput/SearchInput';
import NicePagination from '../../Component/Pagination/NicePagination';
function PaperComponent(props) {
  return (
    <Draggable handle="#draggable-dialog-title" cancel={'[class*="MuiDialogContent-root"]'}>
      <Paper {...props} />
    </Draggable>
  );
}
class SelectParentPopup extends React.Component {
  state = {
    rowsPerPage: 5,
    page: 1,
    totalElements: 0,
    totalPages: 0,
    selectedItem: {},
    keyword: '',
    item: null,
  };

  setPage = page => {
    this.setState({ page }, function () {
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

  updatePageData = (item) => {
    // console.log(this.props.orgType)
    var searchObject = {};
    if (item != null) {
      this.setState({
        page: 1,
        text: item.text,
      }, () => {

        searchObject.text = this.state.text;
        searchObject.pageIndex = this.state.page;
        searchObject.pageSize = this.state.rowsPerPage;

        // searchObject.orgType = this.props.orgType;
        if (this.props.tree) {
          searchByPage(searchObject).then(({ data }) => {
            this.setState({
              itemList: [...data.content],
              totalElements: data.totalElements,
              totalPages: data.totalPages
            })
          });
        } else {
          searchObject.manager = this.props.manager;
          searchObject.screening = this.props.screening;
          searchObject.confirmation = this.props.confirmation;
          searchObject.treatment = this.props.treatment;
          searchObject.checking = this.props.checking;
          getOrgByTypes(searchObject).then(({ data }) => {
            this.setState({
              itemList: [...data.content],
              totalElements: data.totalElements,
              totalPages: data.totalPages
            })
          });
        }

      });
    } else {
      searchObject.text = this.state.text;
      searchObject.pageIndex = this.state.page;
      searchObject.pageSize = this.state.rowsPerPage;

      // searchObject.orgType = this.props.orgType;
      // console.log(searchObject)
      if (this.props.tree) {
        searchByPage(searchObject).then(({ data }) => {
          // console.log(searchObject)
          this.setState({
            itemList: [...data.content],
            totalElements: data.totalElements,
            totalPages: data.totalPages
          })
        });
      } else {
        searchObject.manager = this.props.manager;
        searchObject.screening = this.props.screening;
        searchObject.confirmation = this.props.confirmation;
        searchObject.treatment = this.props.treatment;
        searchObject.checking = this.props.checking;
        getOrgByTypes(searchObject).then(({ data }) => {
          // console.log(searchObject)
          this.setState({
            itemList: [...data.content],
            totalElements: data.totalElements,
            totalPages: data.totalPages
          })
        });
      }

    }

  }

  componentDidMount() {
    this.updatePageData();
  }

  handleClick = (event, item) => {
    // console.log(item);
    let { selectedValue } = this.state;
    if (selectedValue === item.id) {
      this.setState({ selectedValue: null, selectedItem: null })
    } else {
      this.setState({ selectedValue: item.id, selectedItem: item })
    }
    // if (this.state.selectedValue == null) {
    //   this.setState({ selectedValue: item.id, selectedItem: item }, () => {
    //     // console.log(this.state.selectedValue);
    //   });
    // }
    // else {
    //   this.setState({ selectedValue: null, selectedItem: null }, () => {
    //     // console.log(this.state.selectedValue);
    //   });
    // }
  }

  componentWillMount() {
    let { selectedItem } = this.props;
    //this.setState(item);
    this.setState({ selectedValue: selectedItem.id, selectedItem: selectedItem });
  }

  onClickRow = (selectedRow) => {
    document.querySelector(`#radio${selectedRow.id}`).click();
  }

  render() {
    const { t, handleClose, handleSelect, open } = this.props;
    let { itemList } = this.state;
    let columns = [
      {
        title: t("general.popup.select"),
        field: "custom",
        align: "left",
        width: "250",
        render: rowData =>
          <Radio
            id={`radio${rowData.id}`}
            name="radSelected"
            value={rowData.id}
            checked={this.state.selectedValue === rowData.id}
            onClick={(event) => this.handleClick(event, rowData)}
          />
      },
      { title: t("general.popup.code"), field: "code", align: "left", width: "150" },
      { title: t("general.popup.name"), field: "name", width: "150" },
    ];
    return (
      <Dialog onClose={handleClose} open={open} PaperComponent={PaperComponent} maxWidth={'md'} fullWidth>
        <DialogTitle style={{ cursor: 'move' }} id="draggable-dialog-title">
          <span className="mb-20">{t("health_org.select")}</span>
        </DialogTitle>
        <DialogContent>
          <Grid container className="mb-16">
            <Grid item md={6}></Grid>
            <Grid item md={6}>
              <SearchInput
                search={this.updatePageData}
                t={t}
              />
            </Grid>
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
                // tableLayout: 'fixed'
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
        </DialogContent>
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
      </Dialog>
    )
  }
}
export default SelectParentPopup;