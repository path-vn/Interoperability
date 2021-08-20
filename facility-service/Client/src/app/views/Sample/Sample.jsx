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
import {searchByPageDto as searchByPage, getItemById } from "./SampleService";
import SampleEditorDialog from "./SampleEditorDialog";
import { Breadcrumb, ConfirmationDialog } from "egret";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
import { Helmet } from 'react-helmet'
import NotificationPopup from '../Component/NotificationPopup/NotificationPopup'
import { healthOrganizationSearchByPage, getExcel } from './SampleService'
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
        keyword: null,
        rowsPerPage: 10,
        page: 0,
        item: {},
        shouldOpenEditorDialog: false,
        shouldOpenConfirmationDialog: false,
        itemList: [],
        totalElements: 0,
        shouldOpenConfirmationDeleteAllDialog: false,
        shouldOpenImportExcelDialog: false,
        labTest: null,
        labTestList: [],
        sampleCollectOrg: null,
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

    getListItemChild(item) {
        var result = [];
        var root = {};
        root.name = item.name;
        root.description = item.description;
        root.code = item.code;
        root.icon = item.icon;
        root.path = item.path;
        root.id = item.id;
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

    selectHealthOrganization = (labTest) => {
        this.setState({ labTest: labTest }, function () {
            this.search()
        });
    }

    updatePageData = () => {
        var searchObject = {}
        searchObject.keyword = null
        searchObject.pageIndex = this.state.page + 1
        searchObject.pageSize = this.state.rowsPerPage
        searchObject.isChidren = false; //lấy mẫu thường
        searchObject.sampleCollectOrg = this.state.sampleCollectOrg;
        searchObject.labTest = this.state.labTest;
        searchObject.text = this.state.keyword;
        searchByPage(searchObject, this.state.page, this.state.rowsPerPage).then(({ data }) => {
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
                this.search()
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
                this.search()
            }
        )
    }

    handleOKEditClose = () => {
        this.setState({
            shouldOpenEditorDialog: false,
            shouldOpenConfirmationDialog: false,
            shouldOpenConfirmationDeleteAllDialog: false,
            shouldOpenImportExcelDialog: false,
        }, () => {
            this.search()
        })
        // this.setPage(0)
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
        // deleteItem(this.state.id).then(() => {
        //     this.updatePageData();
        //     this.handleDialogClose()
        // })
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
            searchObject.isChidren = false; //lấy mẫu thường
            searchByPage(searchObject).then(({ data }) => {
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

    exportExcel = () => {
        var searchObject = {};
        searchObject.sampleCollectOrg = this.state.sampleCollectOrg;
        searchObject.labTest = this.state.labTest;
        searchObject.text = this.state.keyword;
        searchObject.pageIndex = 0;
        searchObject.pageSize =100000;
        searchObject.isChidren = false; //lấy mẫu thường
        getExcel(searchObject)
        .then((result) => {
            const url = window.URL.createObjectURL(
            new Blob([result.data], {
                type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            })
            );
            const link = document.createElement("a");
            link.href = url;
            link.setAttribute("download", "Mẫu xét nghiệm thường.xlsx");
            document.body.appendChild(link);
            link.click();
        })
        .catch((err) => {
            alert('Đã có lỗi')
        });
    }

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
        let TitlePage = t('sample.title1')
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
                title: t('Ngày tiếp xúc cuối cùng'),
                field: 'person.lastContactDate',
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
                    (rowData.person.lastContactDate) ? <span>{moment(rowData.person.lastContactDate).format('DD/MM/YYYY')}</span> : ''
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
                title: 'Kết quả mẫu', field: 'sampleResult', width: '150',
                render: rowData => {
                    if (rowData.sampleResult == 'Positive') return <span>Dương tính</span>;
                    else if (rowData.sampleResult == 'Negative') return <span>Âm tính</span>;
                    else if (rowData.sampleResult == 'Checking') return <span>Dương tính chờ xác nhận</span>;
                },
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
            },
            {
                title: t("suspected_person.name"),
                field: "person.name",
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
                title: t("suspected_person.birthDate"),
                field: "person.birthDate",
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
                render: (rowData) => (
                    <p>
                        {rowData?.person?.birthDate ? this.timeConverter(rowData.person.birthDate) : ""}
                    </p>
                ),
            },
            {
                title: t("suspected_person.gender"),
                field: "person.gender",
                align: "left",
                width: "100%",
                render: rowData => {
                    if (rowData.person.gender == 'M') {
                      return <span>Nam</span>
                    } else if (rowData.person.gender == 'F') {
                      return <span>Nữ</span>
                    }
                  },
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
                }
            },
            // {
            //     title: t("suspected_person.physicalAdminUnit"),
            //     field: "person.physicalAdminUnit.name",
            //     align: "left",
            //     width: "150px",
            //     cellStyle: {
            //         minWidth: "150px",
            //         paddingLeft: "20px",
            //         paddingRight: "0px",
            //         textAlign: "left",
            //     },
            // },
            // {
            //     title: t("suspected_person.residentAdminUnit"),
            //     field: "person.residentAdminUnit.name",
            //     align: "left",
            //     width: "150px",
            //     cellStyle: {
            //         minWidth: "150px",
            //         paddingLeft: "0px",
            //         paddingRight: "0px",
            //         textAlign: "center",
            //     },
            // },
            {
                title: t("suspected_person.suspectedLevel"),
                field: "person.suspectedLevel.name",
                align: "left",
                width: "150px",
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
            // {
            //     title: t("suspected_person.suspectedType"),
            //     field: "person.suspectedType.name",
            //     align: "left",
            //     width: "150px",
            //     headerStyle: {
            //         minWidth: "150px",
            //         paddingLeft: "0px",
            //         paddingRight: "0px",
            //     },
            //     cellStyle: {
            //         minWidth: "150px",
            //         paddingLeft: "0px",
            //         paddingRight: "0px",
            //         textAlign: "left",
            //     },
            // },
            {
                title: t("suspected_person.epidemiologicalFactors"),
                field: "person.epidemiologicalFactors.name",
                align: "left",
                width: "150px",
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
                            // { name: t('Covid19') },
                            { name: TitlePage },
                        ]}
                    />
                </div>
                <Grid container spacing={2} justify="space-between">
                    <Grid container spacing={2} item>
                    {/* {(userOrgType == 0 || userOrgType == 2 || UserRoles.isAdmin()) &&  */}
                    <Grid item md={2} sm={2}>
                           <Button
                                className="mt-10"
                                variant="contained"
                                color="primary"
                                onClick={() => this.handleEditItem({
                                    sampleType: 1,
                                    isAddnew: true
                                })}
                            >
                                {t('general.add')}
                            </Button>
                        </Grid>
                        {/* } */}
                        {/* <Grid item md={2}>
                            <TextField
                                label={t('EnterSearch')}
                                type="text"
                                className=" w-100"
                                name="keyword"
                                value={keyword}
                                onKeyDown={this.handleKeyDownEnterSearch}
                                onChange={this.handleTextChange} />
                        </Grid> */}
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
                            {/* <Button
                                className="mt-10"
                                variant="contained"
                                color="primary" onClick={() => this.search(keyword)}>
                                {t('general.search')}
                            </Button> */}

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
                        <Grid item md={4}>
                            <Button
                                className="mt-10"
                                variant="contained"
                                color="secondary"
                                onClick={this.importExcel}
                            >
                                {t("general.importExcel")}
                            </Button>

                            <Button
                                className="mt-10 ml-8"
                                variant="contained"
                                color="secondary"
                                onClick={this.exportExcel}
                            >
                                Xuất Excel
                            </Button>
                            <Link style={{marginTop: '10px' , marginLeft: '8px'}} href="https://docs.google.com/spreadsheets/d/123Jt6Et67g6q3BA31xq8NIjvK8n3rBYBjcyLHe_1FQA/edit#gid=1273819459" target="_blank">Xem mẫu tại đây</Link>
                            
                            {shouldOpenImportExcelDialog && (
                                <ImportExcelDialog
                                    t={t}
                                    i18n={i18n}
                                    handleClose={this.handleDialogClose}
                                    open={shouldOpenImportExcelDialog}
                                    handleOKEditClose={this.handleOKEditClose}
                                />
                            )}
                        </Grid>
                        {/* <Grid item md={2}>
                           
                        </Grid>

                        <Grid item md={2} style={{margin: 'auto'}}>
                            <Link  href="https://docs.google.com/spreadsheets/d/123Jt6Et67g6q3BA31xq8NIjvK8n3rBYBjcyLHe_1FQA/edit#gid=1273819459" target="_blank">Xem mẫu tại đây</Link>
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
                            // parentChildData={(row, rows) => {
                            //     var list = rows.find((a) => a.id === row.parentId)
                            //     return list
                            // }}
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
                            rowsPerPageOptions={[1, 5, 10, 25, 50, 100]}
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
