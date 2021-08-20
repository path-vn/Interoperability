import React, { Component } from "react";
import {
  Grid,
  IconButton,
  Icon,
  TablePagination,
  Button
} from "@material-ui/core";
import MaterialTable, { MTableToolbar } from 'material-table';
import { addListassist, searchByPage, postSingleObject } from "./HivPatientService";
import { Breadcrumb, ConfirmationDialog } from "egret";
function MaterialButton(props) {
  const item = props.item;
  return <div>
    <IconButton onClick={() => props.onSelect(item, 2)}>
      <Icon color="primary">cloud_upload</Icon>
    </IconButton>

  </div>;
}

class HivPatient extends Component {
  state = {
    keyword: '',
    rowsPerPage: 10,
    page: 0,
    eQAHealthOrgType: [],
    item: {},
    selectAllItem: false,
    selectedList: [],
    totalElements: 0,
    shouldOpenConfirmPostToOpenhim: false
  };
  numSelected = 0;
  rowCount = 0;

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
      searchObject.pageIndex = this.state.page + 1;
      searchObject.pageSize = this.state.rowsPerPage;
      searchByPage(searchObject).then(({ data }) => {
        console.log(data.status)
        this.setState({ itemList: [...data.content], totalElements: data.totalElements })
      });
    });
  }

  updatePageData = () => {
    var searchObject = {};
    searchObject.text = this.state.keyword;
    searchObject.pageIndex = this.state.page + 1;
    searchObject.pageSize = this.state.rowsPerPage;
    searchByPage(searchObject).then(({ data }) => {
      this.setState({ itemList: [...data.content], totalElements: data.totalElements })
    }
    );
  };

  handleDialogClose = () => {
    this.setState({
      shouldOpenConfirmPostToOpenhim: false,
      checkView: false
    }, () => {
      this.updatePageData()
    });
  };

  componentDidMount() {
    this.updatePageData();
  }
  componentWillMount() {
    var searchObject = {};
    searchObject.pageIndex = 1;
    searchObject.pageSize = 100000;
  }

  handleSaveList = (list) => {
    let listData = []
    if (list && list.length > 0) {
      for (var i = 0; i < list.length; i++) {
        listData.push(list[i].personId);
      }
    }
    return listData;
  }

  handleSaveAll = (event) => {
    var searchObject = {};
    let listData = []
    listData = this.handleSaveList(this.data)
    if (listData && listData.length > 0) {
      searchObject.patientIds = listData;
      addListassist(searchObject).then(() => {
        this.updatePageData();
        this.handleDialogClose();
      }
      );
    }
  };

  render() {
    const { t, i18n } = this.props;
    let TitlePage = t("Hiv Patient");
    let {
      rowsPerPage,
      page,
      totalElements,
      itemList,
      shouldOpenConfirmPostToOpenhim
    } = this.state;

    let columns = [
      { title: t("Name"), field: "displayName", width: "150" },
      { title: t("Arv Treatment Place Transfered Out"), field: "arvTreatmentPlaceTransferredOut", align: "left", width: "150" },
      {
        title: t("Action"),
        field: "custom",
        align: "left",
        width: "250",
        render: rowData => <MaterialButton item={rowData}
          onSelect={(rowData, method) => {
            if (method === 2) {
              postSingleObject(rowData.personId).then(res => {
                alert("Success")
              })

            }
            else {
              alert('Call Selected Here:' + rowData.id);
            }
          }}
        />
      },
    ];

    return (
      <div className="m-sm-30">

        <div className="mb-sm-30">
          {/* <Breadcrumb
            routeSegments={[
              { name: t("Dashboard.dataFile"), path: "/directory/building" },
            ]}
          /> */}
          <Breadcrumb
            routeSegments={[
              { name: t("Hiv Patient"), path: "/dashboard/patient" },
              { name: TitlePage },
            ]}
          />
        </div>

        <Grid container spacing={3} justify="space-between">

          {shouldOpenConfirmPostToOpenhim && (
            <ConfirmationDialog
              open={shouldOpenConfirmPostToOpenhim}
              onConfirmDialogClose={this.handleDialogClose}
              onYesClick={this.handleSaveAll}
              text={t('Send data to Openhim?')}
              agree={t('general.agree')}
              cancel={t('general.cancel')}
            />
          )}

          <Button className="mb-16 mr-36 align-bottom" variant="contained" color="primary"
            onClick={() => this.setState({ shouldOpenConfirmPostToOpenhim: true })}>
            {t('Post data to Openhim')}
          </Button>
          <Grid item xs={12}>
            <div>
            </div>
            <MaterialTable
              title={t('List')}
              data={itemList}
              columns={columns}
              options={{
                selection: true,
                // actionsColumnIndex: -10,
                paging: false,
                search: false
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
            />
            <TablePagination
              align="left"
              className="px-16"
              rowsPerPageOptions={[1, 2, 3, 5, 10, 25, 50, 100, 200, 300, 500, 1000]}
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

export default HivPatient;
