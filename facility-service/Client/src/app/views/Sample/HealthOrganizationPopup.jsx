import {
    Grid, MenuItem, FormControl, Button, Input, InputLabel, Select,
    InputAdornment, TablePagination, Radio, Dialog, Icon, IconButton
} from "@material-ui/core";
import { createMuiTheme } from "@material-ui/core/styles";
import React, { Component } from "react";
import ReactDOM from "react-dom";
import MaterialTable, { MTableToolbar, Chip, MTableBody, MTableHeader } from 'material-table';
import { useTranslation, withTranslation, Trans } from 'react-i18next';
import { searchByPage } from '../HealthOrg/HealthOrgService';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Draggable from 'react-draggable';
import Paper from '@material-ui/core/Paper';
import { Link } from "react-router-dom";
import SearchIcon from "@material-ui/icons/Search";
import Constant from './Constant';
function PaperComponent(props) {
    return (
        <Draggable handle="#draggable-dialog-title" cancel={'[class*="MuiDialogContent-root"]'}>
            <Paper {...props} />
        </Draggable>
    );
}
class HealthOrganizationPopup extends Component {
    state = {
        rowsPerPage: 10,
        page: 0,
        healthOrganizationList: [],
        item: {},
        isGetAll: true,
        isExternalOrg: "null",
        orgType: 1,//Là đươn vị lấy mẫu
        shouldOpenEditorDialog: false,
        shouldOpenConfirmationDialog: false,
        selectedItem: {},
        keyword: ''
    };
    setPage = page => {
        this.setState({ page }, function () {
            this.updatePageData();
        });
    };

    handleKeyDownEnterSearch = e => {
        if (e.key === 'Enter') {
            this.search();
        }
    };
    setRowsPerPage = event => {
        this.setState({ rowsPerPage: event.target.value, page: 0 }, function () {
            this.updatePageData();
        });
    };

    handleChangePage = (event, newPage) => {
        this.setPage(newPage);
    };

    updatePageData = () => {
        var searchObject = {};
        searchObject.text = this.state.keyword;
        searchObject.pageIndex = this.state.page + 1;
        searchObject.pageSize = this.state.rowsPerPage;
        searchObject.orgType = this.state.orgType;
        searchObject.isGetAll = this.state.isGetAll;
        searchObject.isExternalOrg = this.state.isExternalOrg !== "null" ? this.state.isExternalOrg: null;
        searchByPage(searchObject).then(({ data }) => {
            this.setState({
                healthOrganizationList: [...data.content], totalElements: data.totalElements
            }, () => {
            });
        }
        );
    };

    handleChange = (event, source) => {
        this.setState({ keyword: event.target.value }, function () { });


    };
    handleChangeOrgType = (event, source) => {
        if (source === 'orgType') {
            let orgType = this.state;
            orgType = event.target.value;
            this.setState({ orgType: orgType }, () => {
                this.search()
            })
            return
        }
        if(source === 'isExternalOrg'){
            let isExternalOrg = this.state;
            isExternalOrg = event.target.value
            this.setState({isExternalOrg: isExternalOrg}, ()=>{
                this.search()
            })
            return
        }
    }

    componentDidMount() {
        // this.updatePageData(this.state.page, this.state.rowsPerPage);
    }

    handleClick = (event, item) => {
        if (item.id != null) {
            this.setState({ selectedValue: item.id, selectedItem: item });
        } else {
            this.setState({ selectedValue: item.id, selectedItem: null });
        }
    }
    componentWillMount() {
        let { open, handleClose, item ,isExternalOrg, orgType} = this.props;
        if (item != null && item.id != null) {
            this.setState({ selectedValue: item.id });
        }
        if(orgType != null){
            if(isExternalOrg == null || isExternalOrg == "null"){
                isExternalOrg = "null"
            }
            this.setState({orgType: orgType, isExternalOrg: isExternalOrg}, ()=>{
                this.updatePageData();
            })
        }else{
            this.updatePageData();
        }
    }
    search = () => {
        var searchObject = {};
        searchObject.text = this.state.keyword;
        searchObject.pageIndex = this.state.page + 1;
        searchObject.pageSize = this.state.rowsPerPage;
        searchObject.orgType = this.state.orgType;
        searchObject.isGetAll = this.state.isGetAll;
        searchObject.isExternalOrg = this.state.isExternalOrg !== "null" ? this.state.isExternalOrg: null;
        searchByPage(searchObject).then(({ data }) => {
            this.setState({
                healthOrganizationList: [...data.content], totalElements: data.totalElements
            });
        }
        );
    };

    render() {
        const { t, i18n, handleClose, handleSelect, listOrgType, selectedItem, open } = this.props;
        let {
            rowsPerPage,
            page,
            keyword,
            orgType,
            isExternalOrg,
            healthOrganizationList,
            shouldOpenEditorDialog
        } = this.state;

        let columns = [
            {
                title: t("Action"),
                field: "custom",
                align: "left",
                width: "250",
                render: rowData => <Radio name="radSelected" value={rowData.id} checked={this.state.selectedValue === rowData.id} onClick={(event) => this.handleClick(event, rowData)}
                />
            },
            { title: t('HealthOrganization.Name'), field: "name", align: "left", width: "150" },
            { title: t("HealthOrganization.Code"), field: "code", width: "150" },
            { title: t("Vị trí đơn vị"), field: "isExternalOrg", width:"150",
              render: rowData => rowData.isExternalOrg == true ? "Đơn vị ngoại tỉnh" : "Đơn vị nội tỉnh"  
            },
            { 
                title: t("loại đơn vị"), 
                field: "orgType", 
                align: "left", 
                width: "150",
                render: rowData =>
                  (rowData.orgType === 0) ? <span>Hỗn hợp</span> :  (rowData.orgType == 1) ? <p>Đơn vị xét nghiệm</p> :  (rowData.orgType == 2) ? <p>Đơn vị lấy mẫu</p> :  (rowData.orgType == 3) ? <p>Đơn vị cách ly</p> : ''
                
              },
        ]

        return (
            <Dialog onClose={handleClose} open={open} PaperComponent={PaperComponent} maxWidth={'lg'} fullWidth={true}>
                <DialogTitle style={{ cursor: 'move' }} id="draggable-dialog-title">
                    <span className="mb-20 ">{t("HealthOrganization.title")}</span>
                    <IconButton style={{ position: "absolute", right: "10px", top: "10px" }} onClick={() => handleClose()}><Icon color="error"
                        title={t("close")}>
                        close
                  </Icon>
                    </IconButton>
                </DialogTitle>
                <DialogContent dividers>
                    <Grid container spacing={2}>
                        <Grid item lg={4} md={4} sm={6} xs={12}>
                            <FormControl fullWidth={true} className="mb-32" >
                                <InputLabel htmlFor="sampleType-simple">
                                    {t('Loại đơn vị')}
                                </InputLabel>
                                <Select
                                    value={orgType}
                                    
                                    // style={{marginBottom: '16px'}}
                                    onChange={(orgType) => this.handleChangeOrgType(orgType, 'orgType')}
                                    inputProps={{
                                        name: 'sampleType',
                                        id: 'sampleType-simple',
                                    }}
                                >
                                    {(listOrgType ? listOrgType : Constant.listOrgType).map((item) => {
                                        return (
                                            <MenuItem key={item.id} value={item.id}>
                                                {item.name}
                                            </MenuItem>
                                        )
                                    })}
                                </Select>
                            </FormControl>
                        </Grid>
                        <Grid item lg={4} md={4} sm={6} xs={12}>
                            <FormControl fullWidth={true} className="mb-32" >
                                <InputLabel htmlFor="isExternalOrg-simple">
                                    {t('Vị trí đơn vị')}
                                </InputLabel>
                                <Select
                                    value={isExternalOrg}
                                    
                                    // style={{marginBottom: '16px'}}
                                    onChange={(isExternalOrg) => this.handleChangeOrgType(isExternalOrg, 'isExternalOrg')}
                                    inputProps={{
                                        name: 'isExternalOrg',
                                        id: 'isExternalOrg-simple',
                                    }}
                                >
                                    {Constant.ListIsExternalOrg.map((item) => {
                                        return (
                                            <MenuItem key={item.id} value={item.id}>
                                                {item.name}
                                            </MenuItem>
                                        )
                                    })}
                                </Select>
                            </FormControl>
                        </Grid>
                        <Grid item md={4} sm={4} xs={12}>
                            <FormControl fullWidth>
                                <Input
                                    className="search_box w-100 mt-16 stylePlaceholder"
                                    type="text"
                                    name="keyword"
                                    value={keyword}
                                    onChange={this.handleChange}
                                    onKeyDown={this.handleKeyDownEnterSearch}
                                    placeholder={t("HealthOrganization.EnterSearch")}
                                    id="search_box"
                                    startAdornment={
                                        <InputAdornment>
                                            <Link to="#">
                                                {" "}
                                                <SearchIcon
                                                    onClick={() => this.search(keyword)}
                                                    style={{
                                                        position: "absolute",
                                                        top: "0",
                                                        right: "0"
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
                        <MaterialTable title='' data={this.state.healthOrganizationList} columns={columns}
                            // parentChildData={(row, rows) => {
                            //     var list = rows.find(a => a.id === row.parentId);
                            //     return list;
                            // }}
                            options={{
                                selection: false,
                                actionsColumnIndex: -1,
                                paging: false,
                                search: false,
                                rowStyle: (rowData, index) => ({
                                    backgroundColor: (index % 2 === 1) ? '#EEE' : '#FFF',
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
                                Toolbar: props => (
                                    <div style={{ width: "100%" }}>
                                        <MTableToolbar {...props} />
                                    </div>
                                ),
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
                        <TablePagination
                            align="left"
                            className="px-16"
                            rowsPerPageOptions={[1, 2, 3, 5, 10, 25, 50, 100]}
                            component="div"
                            labelRowsPerPage={t('general.rows_per_page')}
                            labelDisplayedRows={({ from, to, count }) => `${from}-${to} ${t('general.of')} ${count !== -1 ? count : `more than ${to}`}`}
                            count={this.state.totalElements}
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
                </DialogContent>
                <DialogActions>
                    <Button className="mb-16 mr-36" variant="contained" color="secondary" onClick={() => handleClose()}>
                        {t('Cancel')}
                    </Button>
                    <Button className="mb-16 mr-16" variant="contained" color="primary" onClick={() => handleSelect(this.state.selectedItem)}>
                        {t('Select')}
                    </Button>
                </DialogActions>
            </Dialog>
        )
    }
}

export default HealthOrganizationPopup;


