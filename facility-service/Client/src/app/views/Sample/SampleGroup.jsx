import React, { Component } from "react";
import {
    Grid,
    IconButton,
    Icon,
    TablePagination,
    FormControl,
    Input, InputAdornment,
    Button, Link,
    TextField
} from "@material-ui/core";
import MaterialTable, { MTableToolbar, Chip, MTableBody, MTableHeader } from 'material-table';
import { searchByPageDto, getItemById } from "./SampleService";
import SampleEditorDialog from "./SampleEditorDialog";
import { Breadcrumb, ConfirmationDialog } from "egret";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
import { Helmet } from 'react-helmet'
import NotificationPopup from '../Component/NotificationPopup/NotificationPopup'
import { healthOrganizationSearchByPage } from './SampleService'
import Autocomplete from '@material-ui/lab/Autocomplete';
import { getAllOrgByUserId } from "../User/UserService";
import SearchIcon from '@material-ui/icons/Search';
import authService from "../../services/jwtAuthService";
import ImportExcelDialog from "./ImportExcelDialog";
import UserRoles from "app/services/UserRoles";
import moment from 'moment';

function MaterialButton(props) {
    const { t, i18n } = useTranslation();
    const item = props.item;
    return <div>
        <IconButton onClick={() => props.onSelect(item, 0)}>
            <Icon color="primary">edit</Icon>
        </IconButton>
        {/* <IconButton onClick={() => props.onSelect(item, 1)}>
            <Icon color="error">delete</Icon>
        </IconButton> */}
    </div>;
}

class Sample extends Component {
    state = {
        keyword: '',
        rowsPerPage: 10,
        page: 0,
        item: {},
        shouldOpenEditorDialog: false,
        shouldOpenConfirmationDialog: false,
        itemList: [],
        totalElements: 0,
        shouldOpenConfirmationDeleteAllDialog: false,
        shouldOpenImportExcelDialog: false,
        labTest: {},
        labTestList: [],
        sampleCollectOrg: {},
        sampleCollectOrgList: [],
        userOrgType: 0
    };

    handleTextChange(event) {
        this.setState({ keyword: event.target.value })
    }

    handleKeyDownEnterSearch = (e) => {
        if (e.key === 'Enter') {
            this.search()
        }
    }

    //TODO càn code lai, viet nhanh de tam nhu nay
    healthOrganizationSearchByPage = () => {
        healthOrganizationSearchByPage({ pageIndex: 0, pageSize: 100000, orgType: 1 }).then(({ data }) => {
            this.setState({ labTestList: data?.content })
        });
    }
    sampleCollectOrgSearchByPage = () => {
        healthOrganizationSearchByPage({ pageIndex: 0, pageSize: 100000, orgType: 2 }).then(({ data }) => {
            this.setState({ sampleCollectOrgList: data?.content })
        });
    }
    componentDidMount() {
        this.updatePageData();
        this.healthOrganizationSearchByPage();
        this.sampleCollectOrgSearchByPage();
        if (!UserRoles.isAdmin()) {
            this.getUserOrgType();
        }
    }

    getUserOrgType() {
        let user = authService.getLoginUser()
        if (user != null) {
            getAllOrgByUserId(user.id).then(({ data }) => {
                if (data != null && data.org != null)
                    this.setState({ userOrgType: data.org.orgType })
            });
        }

    }

    selectHealthOrganization = (labTest) => {
        this.setState({ labTest: labTest }, function () {
            this.search()
        });
    }

    updatePageData = () => {
        var searchObject = {}
        searchObject.keyword = ''
        searchObject.pageIndex = this.state.page + 1
        searchObject.pageSize = this.state.rowsPerPage
        searchObject.isChidren = true
        searchByPageDto(searchObject, this.state.page, this.state.rowsPerPage).then(({ data }) => {
            this.setState({ itemList: [...data.content], totalElements: data.totalElements })
        });
    }


    setPage = (page) => {
        this.setState({ page }, function () {
            this.updatePageData()
        })
    }

    setRowsPerPage = (event) => {
        this.setState({ rowsPerPage: event.target.value, page: 0 }, function () {
            this.updatePageData()
        })
    }

    handleChangePage = (event, newPage) => {
        this.setPage(newPage)
    }

    handleOKEditClose = () => {
        this.setState(
            {
                shouldOpenEditorDialog: false,
                shouldOpenConfirmationDialog: false,
            },
            () => {
                this.updatePageData()
            }
        )
    }

    handleDelete = (id) => {
        this.setState({
            id,
            shouldOpenConfirmationDialog: true,
        })
    }
    handleDialogClose = () => {
        this.setState(
            {
                shouldOpenEditorDialog: false,
                shouldOpenConfirmationDialog: false,
                shouldOpenConfirmationDeleteAllDialog: false,
                shouldOpenNotificationPopup: false,
                shouldOpenImportExcelDialog: false,
                data: [],
            },
            () => {
                this.updatePageData()
            }
        )
    }

    handleOKEditClose = () => {
        this.setState({
            shouldOpenEditorDialog: false,
            shouldOpenConfirmationDialog: false,
            shouldOpenConfirmationDeleteAllDialog: false,
            shouldOpenImportExcelDialog: false,
        })
        this.setPage(0)
    }

    handleDelete = (id) => {
        this.setState({
            id,
            shouldOpenConfirmationDialog: true,
        })
    }

    handleConfirmationResponse = () => {
        if (this.state.itemList.length === 1 && this.state.page === 1) {
            let count = this.state.page - 1
            this.setState({
                page: count,
            })
        }
    }
    handleEditItem = (item) => {
        this.setState({
            item: item,
            shouldOpenEditorDialog: true,
        })

    }
    handleDeleteButtonClick = () => {
        if (
            typeof this.state.data === 'undefined' ||
            this.state.data.length === 0
        ) {
            this.setState({
                shouldOpenNotificationPopup: true,
                Notification: "general.noti_check_data"
            })
        } else {
            this.setState({ shouldOpenConfirmationDeleteAllDialog: true })
        }
    }

    handleDeleteAll = (event) => {
        this.handleDeleteList(this.state.data).then(() => {
            this.updatePageData()
        })
    }
    handleTextChange = event => {
        this.setState({ keyword: event.target.value }, function () {
        })
    };

    handleKeyDownEnterSearch = e => {
        if (e.key === 'Enter') {
            this.search();
        }
    };

    search() {
        this.setState({ page: 0 }, function () {
            var searchObject = {};
            searchObject.sampleCollectOrg = this.state.sampleCollectOrg;
            searchObject.labTest = this.state.labTest;
            searchObject.text = this.state.keyword;
            searchObject.pageIndex = this.state.page + 1;
            searchObject.pageSize = this.state.rowsPerPage;
            searchObject.isChidren = true

            searchByPageDto(searchObject, this.state.page, this.state.rowsPerPage).then(({ data }) => {
                this.setState({ itemList: [...data.content], totalElements: data.totalElements })
            });
        });
    }
    timeConverter(UNIX_timestamp) {
        var a = new Date(UNIX_timestamp);
        var months = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];
        var year = a.getFullYear();
        var month = months[a.getMonth()];
        var date = a.getDate();
        // var time = date + '/' + month + '/' + year;
        var time = year
        return time;
    }
    importExcel = () => {
        this.setState({
            shouldOpenImportExcelDialog: true,
        });
    };

    render() {
        const { t, i18n } = this.props
        let {
            keyword,
            shouldOpenNotificationPopup,
            labTest,
            labTestList,
            sampleCollectOrgList,
            userOrgType,
            shouldOpenImportExcelDialog
        } = this.state
        let searchObject = { pageIndex: 0, pageSize: 100000 }
        let TitlePage = t('sample.title2')
        let columns = [
            {
                title: " " + t('general.action'),
                field: 'custom',
                align: 'left',
                width: '120px',
                headerStyle: {
                    minWidth: "150px",
                    paddingLeft: "5px",
                    paddingRight: "0px",
                },
                cellStyle: {
                    minWidth: "150px",
                    paddingLeft: "5px",
                    paddingRight: "0px",
                    textAlign: "left",
                },
                render: (rowData) => (
                    <MaterialButton
                        item={rowData}
                        onSelect={(rowData, method) => {
                            if (method === 0) {
                                getItemById(rowData.id).then(({ data }) => {
                                    if (data.parent === null) {
                                        data.parent = {}
                                    }
                                    this.setState({
                                        item: data,
                                        shouldOpenEditorDialog: true,
                                    })
                                })
                            } else if (method === 1) {
                                this.handleDelete(rowData.id)
                            } else {
                                alert('Call Selected Here:' + rowData.id)
                            }
                        }}
                    />
                ),
            },
            {
                title: t('sample.code'), field: 'code', width: '150',
                headerStyle: {
                    minWidth: "150px",
                    paddingLeft: "0px",
                    paddingRight: "0px",
                },
                cellStyle: {
                    minWidth: "150px",
                    paddingLeft: "0px",
                    paddingRight: "0px",
                    textAlign: "left",
                },
            },

            {
                title: t('Ngày lấy mẫu'), field: 'sampleDate', width: '150',
                headerStyle: {
                    minWidth: "150px",
                    paddingLeft: "0px",
                    paddingRight: "0px",
                },
                cellStyle: {
                    minWidth: "150px",
                    paddingLeft: "0px",
                    paddingRight: "0px",
                    textAlign: "left",
                },
                render: rowData =>
                    (rowData.sampleDate) ? <span>{moment(rowData.sampleDate).format('DD/MM/YYYY')}</span> : ''
            },
            {
                title: t('Ngày gửi mẫu'), field: 'shipDate', width: '150',
                headerStyle: {
                    minWidth: "150px",
                    paddingLeft: "0px",
                    paddingRight: "0px",
                },
                cellStyle: {
                    minWidth: "150px",
                    paddingLeft: "0px",
                    paddingRight: "0px",
                    textAlign: "left",
                },
                render: rowData =>
                    (rowData.shipDate) ? <span>{moment(rowData.shipDate).format('DD/MM/YYYY')}</span> : ''
            },
            {
                title: t('Ngày làm xét nghiệm'),
                field: 'testingDate',
                width: '150',
                headerStyle: {
                    minWidth: "150px",
                    paddingLeft: "0px",
                    paddingRight: "0px",
                },
                cellStyle: {
                    minWidth: "150px",
                    paddingLeft: "0px",
                    paddingRight: "0px",
                    textAlign: "left",
                },

                render: rowData =>
                    (rowData.testingDate) ? <span>{moment(rowData.testingDate).format('DD/MM/YYYY')}</span> : ''
            },
            {
                title: t('Ngày có kết quả'),
                field: 'resultDate',
                width: '150',
                headerStyle: {
                    minWidth: "150px",
                    paddingLeft: "0px",
                    paddingRight: "0px",
                },
                cellStyle: {
                    minWidth: "150px",
                    paddingLeft: "0px",
                    paddingRight: "0px",
                    textAlign: "left",
                },

                render: rowData =>
                    (rowData.resultDate) ? <span>{moment(rowData.resultDate).format('DD/MM/YYYY')}</span> : ''
            },
            {
                title: t("Đơn vị xét nghiệm"),
                field: "labTest.name",
                align: "left",
                width: "100%",
                headerStyle: {
                    minWidth: "150px",
                    paddingLeft: "0px",
                    paddingRight: "0px",
                },
                cellStyle: {
                    minWidth: "150px",
                    paddingLeft: "0px",
                    paddingRight: "0px",
                    textAlign: "left",
                },
            },
            {
                title: t("Đơn vị lấy mẫu"),
                field: "sampleCollectOrg.name",
                align: "left",
                width: "100%",
                headerStyle: {
                    minWidth: "150px",
                    paddingLeft: "0px",
                    paddingRight: "0px",
                },
                cellStyle: {
                    minWidth: "150px",
                    paddingLeft: "0px",
                    paddingRight: "0px",
                    textAlign: "left",
                },
            }
        ]

        return (
            <div className="m-sm-30">
                <Helmet>
                    <title>{TitlePage} | {t('web_site')}</title>
                </Helmet>
                <div className="mb-sm-30">
                    <Breadcrumb
                        routeSegments={[
                            { name: TitlePage },
                        ]}
                    />
                </div>
                <Grid container spacing={2} justify="space-between">
                    <Grid container spacing={2} item>
                    <Grid item md={2} sm={2}>
                           <Button
                                className="mt-10"
                                variant="contained"
                                color="primary"
                                onClick={() => this.handleEditItem({
                                    sampleType: 2,
                                    isAddnew: true
                                })}
                            >
                                {t('general.add')}
                            </Button>
                        </Grid>
                      
                        {userOrgType == 1 && <Grid item md={2} sm={4}>
                            <Autocomplete
                                options={labTestList}
                                getOptionLabel={(option) => option.name}
                                id="debug"
                                onChange={(event, value) => { this.selectHealthOrganization(value) }}
                                renderInput={(params) => <TextField {...params} label="Tìm kiếm đơn vị xét nghiệm" />}
                            />
                        </Grid>}
                        {userOrgType == 2 && <Grid item md={2} sm={4}>
                            <Autocomplete
                                options={sampleCollectOrgList}
                                getOptionLabel={(option) => option.name}
                                id="debug"
                                onChange={(event, value) => { this.selectHealthOrganization(value) }}
                                renderInput={(params) => <TextField {...params} label="Tìm kiếm đơn vị lấy mẫu" />}
                            />
                        </Grid>}
                        {(userOrgType == 0 || userOrgType == null) && <><Grid item md={2} sm={4}>
                            <Autocomplete
                                options={labTestList}
                                getOptionLabel={(option) => option.name}
                                id="debug"
                                onChange={(event, value) => { this.selectHealthOrganization(value) }}
                                renderInput={(params) => <TextField {...params} label="Tìm kiếm đơn vị xét nghiệm" />}
                            />
                        </Grid>
                            <Grid item md={2} sm={4}>
                                <Autocomplete
                                    options={sampleCollectOrgList}
                                    getOptionLabel={(option) => option.name}
                                    id="debug"
                                    onChange={(event, value) => { this.selectHealthOrganization(value) }}
                                    renderInput={(params) => <TextField {...params} label="Tìm kiếm đơn vị lấy mẫu" />}
                                />
                            </Grid></>}
                        <Grid item md={2}>
                            <FormControl fullWidth>
                                <Input
                                    className='mt-16 search_box w-100 stylePlaceholder'
                                    type="text"
                                    name="keyword"
                                    value={keyword}
                                    onChange={this.handleTextChange}
                                    onKeyDown={this.handleKeyDownEnterSearch}
                                    placeholder={t('general.enterSearch')}
                                    id="search_box"
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
                            </FormControl>

                        </Grid>
                        {/* <Grid item md={2}>
                            <Button
                                className="mt-10"
                                variant="contained"
                                color="secondary"
                                onClick={this.importExcel}
                            >
                                {t("general.importExcel")}
                            </Button>
                            {shouldOpenImportExcelDialog && (
                                <ImportExcelDialog
                                    t={t}
                                    i18n={i18n}
                                    handleClose={this.handleDialogClose}
                                    open={shouldOpenImportExcelDialog}
                                    handleOKEditClose={this.handleOKEditClose}
                                />
                            )}
                        </Grid> */}
                        {this.state.shouldOpenConfirmationDeleteAllDialog && (
                            <ConfirmationDialog
                                open={this.state.shouldOpenConfirmationDeleteAllDialog}
                                onConfirmDialogClose={this.handleDialogClose}
                                onYesClick={this.handleDeleteAll}
                                text={t('general.deleteAllConfirm')}
                                cancel={t('general.cancel')}
                                agree={t('general.agree')}
                            />
                        )}

                    </Grid>

                    <Grid item xs={12}>
                        <div>
                            {this.state.shouldOpenEditorDialog && (
                                <SampleEditorDialog
                                    t={t}
                                    i18n={i18n}
                                    handleClose={this.handleDialogClose}
                                    open={this.state.shouldOpenEditorDialog}
                                    handleOKEditClose={this.handleOKEditClose}
                                    item={this.state.item}
                                />
                            )}

                            {shouldOpenNotificationPopup && (
                                <NotificationPopup
                                    title={t('general.noti')}
                                    open={shouldOpenNotificationPopup}
                                    onYesClick={this.handleDialogClose}
                                    text={t(this.state.Notification)}
                                    agree={t('general.agree')}
                                />
                            )}

                            {this.state.shouldOpenConfirmationDialog && (
                                <ConfirmationDialog
                                    title={t('general.confirm')}
                                    open={this.state.shouldOpenConfirmationDialog}
                                    onConfirmDialogClose={this.handleDialogClose}
                                    onYesClick={this.handleConfirmationResponse}
                                    text={t('general.deleteConfirm')}
                                    agree={t('general.agree')}
                                    cancel={t('general.cancel')}
                                />
                            )}
                        </div>
                        <MaterialTable
                            title={t('general.list')}
                            data={this.state.itemList}
                            columns={columns}
                            parentChildData={(row, rows) => {
                                var list = rows.find((a) => a.id === row.parentId)
                                return list
                            }}
                            localization={{
                                body: {
                                    emptyDataSourceMessage: `${t('general.emptyDataMessageTable')}`
                                },
                                toolbar: {
                                    nRowsSelected: `${t('general.selects')}`
                                }
                            }}
                            options={{
                                selection: false,
                                actionsColumnIndex: -1,
                                paging: false,
                                search: false,
                                rowStyle: rowData => ({
                                    backgroundColor: (rowData.tableData.id % 2 === 1) ? '#EEE' : '#FFF',
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
                                Toolbar: (props) => <MTableToolbar {...props} />,
                            }}
                            onSelectionChange={(rows) => {
                                this.setState({ data: rows })
                            }}
                        />
                        <TablePagination
                            align="left"
                            className="px-16"
                            rowsPerPageOptions={[1, 2, 3, 5, 10, 25]}
                            component="div"
                            labelRowsPerPage={t('general.rows_per_page')}
                            labelDisplayedRows={({ from, to, count }) => `${from}-${to} ${t('general.of')} ${count !== -1 ? count : `more than ${to}`}`}
                            count={this.state.totalElements}
                            rowsPerPage={this.state.rowsPerPage}
                            page={this.state.page}
                            backIconButtonProps={{
                                'aria-label': 'Previous Page',
                            }}
                            nextIconButtonProps={{
                                'aria-label': 'Next Page',
                            }}
                            onChangePage={this.handleChangePage}
                            onChangeRowsPerPage={this.setRowsPerPage}
                        />
                    </Grid>
                </Grid>
            </div>
        )

    }

}

export default Sample
