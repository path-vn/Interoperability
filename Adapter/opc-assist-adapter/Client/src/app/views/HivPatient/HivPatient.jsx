import React, { Component } from "react";
import {
  Grid,
  IconButton,
  Icon,
  TablePagination,
  Button,
  FormControl,
  Input,
  InputAdornment,
} from "@material-ui/core";
import { Link } from "react-router-dom";
import SearchIcon from "@material-ui/icons/Search";
import MaterialTable, { MTableToolbar } from 'material-table';
import { addListassist, searchByPage, getItemById, getById } from "./HivPatientService";
import { Breadcrumb, ConfirmationDialog } from "egret";
import { useTranslation } from 'react-i18next';
import VisibilityIcon from '@material-ui/icons/Visibility';
import HivPatientDetail from './HivPatientDetail';
function MaterialButton(props) {
  const item = props.item;
  return <div>
    <IconButton onClick={() => props.onSelect(item, 0)}>
      <Icon color="primary">cloud_upload</Icon>
    </IconButton>
    <IconButton onClick={() => props.onSelect(item, 1)}>
      <Icon color="primary"><VisibilityIcon /></Icon>
    </IconButton>

  </div>;
}

class HivPatient extends Component {
  state = {
    text: '',
    rowsPerPage: 10,
    page: 0,
    eQAHealthOrgType: [],
    item: {},
    shouldOpenDetailDialog: false,
    selectAllItem: false,
    selectedList: [],
    totalElements: 0,
    shouldOpenConfirmSendToOpenhim: false
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
      searchObject.text = this.state.text;
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
    searchObject.text = this.state.text;
    searchObject.pageIndex = this.state.page + 1;
    searchObject.pageSize = this.state.rowsPerPage;
    searchByPage(searchObject).then(({ data }) => {
      this.setState({ itemList: [...data.content], totalElements: data.totalElements })
    }
    );
  };

  handleDialogClose = () => {
    this.setState({
      shouldOpenDetailDialog: false,
      shouldOpenConfirmSendToOpenhim: false,
      checkView: false
    }, () => {
      this.updatePageData()
    });
  };
  handleTextChange = (event) => {
    this.setState({ text: event.target.value }, function () { });
  };

  handleKeyDownEnterSearch = (e) => {
    if (e.key === "Enter") {
      this.search();
    }
  };

  componentDidMount() {
    this.updatePageData();
  }
  componentWillMount() {

  }

  handleShowDetail = (id) => {

  }

  handleSaveList = (list) => {
    let listData = []
    if (list && list.length > 0) {
      for (var i = 0; i < list.length; i++) {
        listData.push(list[i].caseId);
      }
    }
    return listData;
  }

  handleSaveAll = (event) => {
    let listData = []

    listData = this.handleSaveList(this.data)
    console.log(listData)
    if (listData && listData.length > 0) {
      addListassist(listData).then(() => {
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
      item,
      shouldOpenConfirmSendToOpenhim,
      shouldOpenDetailDialog,
      text
    } = this.state;

    let columns = [
      { title: t("Name"), field: "fullname", width: "150" },
      { title: t("Arv Treatment Place Initiation"), field: "arvTreatmentPlaceInitiation", align: "left", width: "150" },
      {
        title: t("Action"),
        field: "custom",
        align: "left",
        width: "250",
        render: rowData => <MaterialButton item={rowData}
          onSelect={(rowData, method) => {
            if (method === 1) {
              getById(rowData.caseId).then(({ data }) => {
                if (data.parent === null) {
                  data.parent = {};
                }
                this.setState({
                  item: data,
                  shouldOpenDetailDialog: true,
                  checkView: true
                });
                

              })
            } else if (method === 0) {
              getItemById(rowData.caseId).then(res => {
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
          <Breadcrumb
            routeSegments={[
              { name: t("Hiv Patient"), path: "/dashboard/patient" },
              { name: TitlePage },
            ]}
          />
        </div>

        <Grid container spacing={3} justify="space-between">

          {shouldOpenConfirmSendToOpenhim && (
            <ConfirmationDialog
              open={shouldOpenConfirmSendToOpenhim}
              onConfirmDialogClose={this.handleDialogClose}
              onYesClick={this.handleSaveAll}
              text={t('Confirmation sendding data to Openhim?')}
              agree={t('general.agree')}
              cancel={t('general.cancel')}
            />
          )}

          <Grid container spacing={2}>
            <Grid item lg={3} md={3} sm={6} xs={12}>
              <Button className="mb-16 mr-36 align-bottom" variant="contained" color="primary"
                onClick={() => this.setState({ shouldOpenConfirmSendToOpenhim: true })}>
                {t('Send to Openhim')}
              </Button>
            </Grid>
            <Grid item lg={4} md={4} sm={6} xs={12}>
              <FormControl fullWidth>
                <Input
                  className="search_box w-100"
                  placeholder={t("general.search")}
                  onChange={this.handleTextChange}
                  onKeyDown={this.handleKeyDownEnterSearch}
                  name="text"
                  value={text}
                  id="search_box"
                  startAdornment={
                    <InputAdornment>
                      <Link>
                        {" "}
                        <SearchIcon
                          onClick={() => {
                            this.search();
                          }}
                          style={{
                            position: "absolute",
                            top: "0",
                            right: "0",
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
            <div>
            </div>
            <MaterialTable
              title={t('List')}
              data={itemList}
              columns={columns}
              options={{
                selection: true,
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
              }}
            />

            {shouldOpenDetailDialog && (
              <HivPatientDetail
                t={t} i18n={i18n}
                handleClose={this.handleDialogClose}
                open={shouldOpenDetailDialog}
                item={item}
              />
            )}

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
