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
import { searchByDto, deleteItem, getItemById } from "./IsolationCenterService";
import EpidemiologicalFactorsDialog from "./IsolationCenterDialog";
import { Breadcrumb, ConfirmationDialog } from "egret";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
import { saveAs } from 'file-saver';
import { toast } from "react-toastify";

function MaterialButton(props) {
    const { t, i18n } = useTranslation();
    const item = props.item;
    return <div>
        <IconButton size="small" onClick={() => props.onSelect(item, 0)}>
            <Icon fontSize="small" color="primary">edit</Icon>
        </IconButton>
        <IconButton size="small" onClick={() => props.onSelect(item, 1)}>
            <Icon fontSize="small" color="error">delete</Icon>
        </IconButton>
    </div>;
}

class EpidemiologicalFactors extends Component {
    state = {
        keyword: '',
        rowsPerPage: 10,
        page: 0,
        eQAHealthOrgType: [],
        item: {},
        shouldOpenEditorDialog: false,
        shouldOpenConfirmationDialog: false,
        selectAllItem: false,
        selectedList: [],
        totalElements: 0,
        shouldOpenConfirmationDeleteAllDialog: false
    };
    numSelected = 0;
    rowCount = 0;

    handleTextChange = event => {
        this.setState({ keyword: event.target.value }, function () {
        })
    };

    handleKeyDownEnterSearch = e => {
        if (e.key === 'Enter') {
            this.search();
        }
    };

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
        console.log(this.state.itemList);
        this.setState({ page: 1 }, function () {
            var searchObject = {};
            searchObject.text = this.state.keyword;
            searchObject.pageIndex = this.state.page;
            searchObject.pageSize = this.state.rowsPerPage;
            searchByDto(searchObject, this.state.page, this.state.rowsPerPage).then(({ data }) => {
                this.setState({ itemList: [...data.content], totalElements: data.totalElements })
            });
        });
    }

    updatePageData = () => {
        var searchObject = {};
        searchObject.text = this.state.keyword;
        searchObject.pageIndex = this.state.page;
        searchObject.pageSize = this.state.rowsPerPage;
        searchByDto(searchObject, this.state.page, this.state.rowsPerPage).then(({ data }) => {
            this.setState({ itemList: [...data.content], totalElements: data.totalElements })
        });
    };

    handleDownload = () => {
        var blob = new Blob(["Hello, world!"], { type: "text/plain;charset=utf-8" });
        saveAs(blob, "hello world.txt");
    }

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

    handleConfirmationResponse = () => {
        deleteItem(this.state.id).then(() => {
            this.updatePageData();
            this.handleDialogClose();
        });
    };

    componentDidMount() {
        this.updatePageData();
    }

    handleEditItem = item => {
        this.setState({
            item: item,
            shouldOpenEditorDialog: true
        });
    };

    handleDelete = id => {
        this.setState({
            id,
            shouldOpenConfirmationDialog: true
        });
    };

    async handleDeleteList(list) {
        if(list && list.length > 0) {
            for (var i = 0; i < list.length; i++) {
                await deleteItem(list[i].id);
            }
        } else {
            toast.warn("Bạn chưa chọn dữ liệu");
        }
    }

    handleDeleteAll = (event) => {
        //alert(this.data.length);
        this.handleDeleteList(this.data).then(() => {
            this.updatePageData();
            this.handleDialogClose();
        }
        );
    };

    timeConverter(UNIX_timestamp) {
        var a = new Date(UNIX_timestamp);
        var months = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];
        var year = a.getFullYear();
        var month = months[a.getMonth()];
        var date = a.getDate();
        var time = date + '/' + month + '/' + year;
        return time;
    }

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
            { title: t("Tên"), field: "name", width: "150" },
            { title: t("Mã"), field: "code", align: "left", width: "150" },
            { title: t("Địa chỉ"), field: "address", align: "left", width: "150" },
            { title: t("Điện thoại liên hệ"), field: "contact", align: "left", width: "150" },
            {
                title: t('Ghi chú'), field: 'note', width: '150',
                render: (rowData) => (
                    <p>
                        {(rowData.note) ? this.timeConverter(rowData.note): <p>--/--/----</p>}
                    </p>
                ),
            },
            { title: t("Đơn vị hành chính"), field: "adminUnit.name", align: "left", width: "150" },
            {
                title: t("general.action"),
                field: "custom",
                align: "left",
                width: "250",
                render: rowData => <MaterialButton item={rowData}
                    onSelect={(rowData, method) => {
                        if (method === 0) {
                            getItemById(rowData.id).then(({ data }) => {
                                if (data.parent === null) {
                                    data.parent = {};
                                }
                                this.setState({
                                    item: data,
                                    shouldOpenEditorDialog: true
                                });
                            })
                        } else if (method === 1) {
                            this.handleDelete(rowData.id);
                        } else {
                            alert('Call Selected Here:' + rowData.id);
                        }
                    }}
                />
            },
        ];

        return (
            <div className="m-sm-30">

                <div className="mb-sm-30">
                    <Breadcrumb routeSegments={[{ name: t('Dashboard.directory') }, { name: t('Trung tâm cách ly') }]} />
                </div>

                <Grid container spacing={3}>
                    <Grid item xs={12}>
                        <Button
                            className="mb-16 mr-16 align-bottom"
                            variant="contained"
                            color="primary"
                            onClick={() => {
                                this.handleEditItem({ startDate: new Date(), endDate: new Date() });
                            }
                            }
                        >
                            {t('Thêm mới')}
                        </Button>
                        <Button className="mb-16 mr-36 align-bottom" variant="contained" color="primary"
                            onClick={() => this.setState({ shouldOpenConfirmationDeleteAllDialog: true })}>
                            {t('Xoá')}
                        </Button>

                        {shouldOpenConfirmationDeleteAllDialog && (
                            <ConfirmationDialog
                                open={this.state.shouldOpenConfirmationDeleteAllDialog}
                                onConfirmDialogClose={this.handleDialogClose}
                                onYesClick={this.handleDeleteAll}
                                title={t("confirm")}
                                text={t('DeleteAllConfirm')}
                                Yes={t('Yes')}
                                No={t('No')}
                            />
                        )}
                        <TextField
                            label={t('EnterSearch')}
                            className="mb-16 mr-10"
                            type="text"
                            name="keyword"
                            value={keyword}
                            onKeyDown={this.handleKeyDownEnterSearch}
                            onChange={this.handleTextChange} />
                        <Button
                            className="mb-16 mr-16 align-bottom"
                            variant="contained"
                            color="primary" onClick={() => this.search(keyword)}>
                            {t('Tìm kiếm')}
                        </Button>
                    </Grid>
                    <Grid item xs={12}>
                        <div>
                            {shouldOpenEditorDialog && (
                                <EpidemiologicalFactorsDialog t={t} i18n={i18n}
                                    handleClose={this.handleDialogClose}
                                    open={shouldOpenEditorDialog}
                                    handleOKEditClose={this.handleOKEditClose}
                                    item={item}
                                />
                            )}

                            {shouldOpenConfirmationDialog && (
                                <ConfirmationDialog
                                    title={t("confirm")}
                                    open={this.state.shouldOpenConfirmationDialog}
                                    onConfirmDialogClose={this.handleDialogClose}
                                    onYesClick={this.handleConfirmationResponse}
                                    title={t("confirm")}
                                    text={t('DeleteConfirm')}
                                    Yes={t('Yes')}
                                    No={t('No')}
                                />
                            )}
                        </div>
                        <MaterialTable
                            title={t('Danh sách Yếu Tố Dịch Tễ')}
                            data={itemList}
                            columns={columns}
                            //parentChildData={(row, rows) => rows.find(a => a.id === row.parentId)}
                            // parentChildData={(row, rows) => {
                            //     var list = rows.find(a => a.id === row.parentId);
                            //     return list;
                            // }}
                            options={{
                                selection: true,
                                actionsColumnIndex: -1,
                                paging: false,
                                search: false,
                                padding: 'dense',
                                toolbar: false      
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
                            count={totalElements}
                            labelRowsPerPage={t('general.rows_per_page')}
                            labelDisplayedRows={({ from, to, count }) => `${from}-${to} ${t('general.of')} ${count !== -1 ? count : `more than ${to}`}`}
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
export default EpidemiologicalFactors;