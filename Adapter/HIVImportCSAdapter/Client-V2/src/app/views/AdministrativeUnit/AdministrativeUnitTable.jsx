import React, { Component } from "react";
import {
  IconButton,
  Table,
  TableHead,
  TableBody,
  TableRow,
  TableCell,
  Icon,
  TablePagination,
  TableContainer,
  Button,
  Card
} from "@material-ui/core";
import { getByPage, deleteAdministrativeUnit } from "./AdministrativeUnitService";
import AdministrativeUnitEditorDialog from "./AdministrativeUnitEditorDialog";
import { Breadcrumb, ConfirmationDialog } from "egret";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
import shortid from "shortid";
import { saveAs } from 'file-saver';
class AdministrativeUnitTable extends Component {
  state = {
    rowsPerPage: 10,
    page: 0,
    administrativeList: [],
    item: {},
    shouldOpenEditorDialog: false,
    shouldOpenConfirmationDialog: false
  };

  setPage = page => {
    this.setState({ page });
  };

  setRowsPerPage = event => {
    this.setState({ rowsPerPage: event.target.value });
  };

  handleChangePage = (event, newPage) => {
    this.setPage(newPage);
  };
  handleDownload = () => {
    var blob = new Blob(["Hello, world!"], { type: "text/plain;charset=utf-8" });
    saveAs(blob, "hello world.txt");
  }
  handleDialogClose = () => {
    this.setState({
      shouldOpenEditorDialog: false,
      shouldOpenConfirmationDialog: false
    });
    this.updatePageData();
  };

  handleDeleteAdministrativeUnit = id => {
    this.setState({
      id,
      shouldOpenConfirmationDialog: true
    });
  };

  handleEditAdministrativeUnit = item => {
    this.setState({
      item: item,
      shouldOpenEditorDialog: true
    });
  };

  handleConfirmationResponse = () => {
    deleteAdministrativeUnit(this.state.id).then(() => {
      this.handleDialogClose();
    });
  };

  componentDidMount() {
    this.updatePageData();
  }

  updatePageData = () => {
    var searchObject = {};
    // searchObject.text = this.state.keyword;
    searchObject.pageIndex = this.state.page + 1;
    searchObject.pageSize = this.state.rowsPerPage;
    getByPage(searchObject).then(({ data }) => {
      this.setState({
        administrativeList: [...data.content], totalElements: data.totalElements
      });
    }
    );
  };

  render() {
    // var blob = new Blob(["Hello, world!"], {type: "text/plain;charset=utf-8"});
    // saveAs(blob, "hello world.txt");
    const { t, i18n } = this.props;
    let {
      rowsPerPage,
      page,
      administrativeList,
      shouldOpenConfirmationDialog,
      shouldOpenEditorDialog
    } = this.state;
    return (
      <div className="m-sm-30">

        <div className="mb-sm-30">
          <Breadcrumb routeSegments={[{ name: t('AdministrativeUnit.title') }]} />
        </div>

        <Button
          className="mb-16 mr-16"
          variant="contained"
          color="primary"
          onClick={() => this.setState({ shouldOpenEditorDialog: true, item: {} })}
        >
          {t('Add')}
        </Button>
        <TableContainer>
          <Table className="crud-table" style={{ whiteSpace: "pre", minWidth: "750px" }}>
            <TableHead>
              <TableRow>
                <TableCell>{t('Name')}</TableCell>
                <TableCell>{t('Code')}</TableCell>
                {/* <TableCell>Level</TableCell> */}
                <TableCell>{t('Status')}</TableCell>
                <TableCell>{t('Action')}</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {administrativeList
                .map((adminUnit, index) => (
                  <TableRow key={shortid.generate()}>
                    <TableCell className="px-0">{adminUnit.name}</TableCell>
                    <TableCell className="px-0" align="left">
                      {adminUnit.code}
                    </TableCell>

                    {/* <TableCell className="px-0" align="left">
                      {adminUnit.level}
                    </TableCell> */}
                    <TableCell className="px-0">
                      {adminUnit.isActive ? (
                        <small className="border-radius-4 bg-primary text-white px-8 py-2 ">
                          active
                        </small>
                      ) : (
                          <small className="border-radius-4 bg-light-gray px-8 py-2 ">
                            inactive
                          </small>
                        )}
                    </TableCell>
                    <TableCell className="px-0 border-none">
                      <IconButton
                        onClick={() =>
                          this.handleEditAdministrativeUnit(adminUnit)
                        }
                      >
                        <Icon color="primary">edit</Icon>
                      </IconButton>
                      <IconButton onClick={() => this.handleDeleteAdministrativeUnit(adminUnit.id)}>
                        <Icon color="error">delete</Icon>
                      </IconButton>
                    </TableCell>
                  </TableRow>
                ))}
            </TableBody>
          </Table>

          <TablePagination
            className="px-16"
            rowsPerPageOptions={[5, 10, 25]}
            component="div"
            count={administrativeList.length}
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

          {shouldOpenEditorDialog && (
            <AdministrativeUnitEditorDialog
              handleClose={this.handleDialogClose}
              open={shouldOpenEditorDialog}
              item={this.state.item}
              t={t} i18n={i18n}
            />
          )}
          {shouldOpenConfirmationDialog && (
            <ConfirmationDialog
              title={t("general.confirm")}
              open={shouldOpenConfirmationDialog}
              onConfirmDialogClose={this.handleDialogClose}
              onYesClick={this.handleConfirmationResponse}
              text="Are you sure to delete?"
              agree={t('general.agree')}
              cancel={t('general.cancel')}
            />
          )}
        </TableContainer>
      </div>
    );
  }
}

export default AdministrativeUnitTable;
