import {
    Grid,
    DialogActions,
    InputAdornment,
    MuiThemeProvider,
    TextField,
    Button,
    IconButton,
    Icon,
    TablePagination,
    Radio,
    Dialog,
} from '@material-ui/core'
import { createMuiTheme } from '@material-ui/core/styles'
import React, { Component } from 'react'
import ReactDOM from 'react-dom'
import MaterialTable, {
    MTableToolbar,
    Chip,
    MTableBody,
    MTableHeader,
} from 'material-table'

import { saveItem, healthOrganizationSearchByPage, checkCode, suspectedTypeSearchByPage, suspectedLevelSearchByPage, epidemiologicalFactorsSearchByPage } from './SampleService'
import DateFnsUtils from '@date-io/date-fns'
import { ValidatorForm, TextValidator } from 'react-material-ui-form-validator'
import DialogContent from '@material-ui/core/DialogContent'
import DialogTitle from '@material-ui/core/DialogTitle'
import Input from '@material-ui/core/Input'
import { getByPageByParentId as getAdministrativeUnitByPage } from "../AdministrativeUnit/AdministrativeUnitService";
import InputLabel from '@material-ui/core/InputLabel'
import MenuItem from '@material-ui/core/MenuItem'
import FormControl from '@material-ui/core/FormControl'
import Select from '@material-ui/core/Select'
import AsynchronousAutocomplete from '../utilities/AsynchronousAutocomplete'
import Draggable from 'react-draggable'
import { useTranslation, withTranslation, Trans } from 'react-i18next';
import Paper from '@material-ui/core/Paper'
import NotificationPopup from '../Component/NotificationPopup/NotificationPopup'
import HealthOrganizationPopup from './HealthOrganizationPopup'
import SuspectedPersonSearchDialog from './SuspectedPersonSearchDialog'
import Constant from './Constant';
import {
    MuiPickersUtilsProvider,
    KeyboardDatePicker,
} from "@material-ui/pickers";
import SelectSamplePopup from "./SelectSamplePopup";
import Autocomplete from '@material-ui/lab/Autocomplete'
import authService from "../../services/jwtAuthService";
import { getAllOrgByUserId } from "../User/UserService";
import UserRoles from "../../services/UserRoles";
import { RemoveCircleOutlineSharp } from '@material-ui/icons'
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
toast.configure({
    autoClose: 2000,
    draggable: false,
    limit: 3
});

function MaterialButton(props) {
    const { t, i18n } = useTranslation();
    const item = props.item;
    return <div>
        {/* <IconButton onClick={() => props.onSelect(item, 0)}>
            <Icon color="primary">edit</Icon>
        </IconButton> */}
        <IconButton onClick={() => props.onSelect(item, 0)}>
            <Icon color="error">delete</Icon>
        </IconButton>
    </div>;
}
function PaperComponent(props) {
    return (
        <Draggable
            handle="#draggable-dialog-title"
            cancel={'[class*="MuiDialogContent-root"]'}
        >
            <Paper {...props} />
        </Draggable>
    )
}
function SelectStatus(props) {

    return (
        <Select
            value={props.value}
            onChange={(sampleStatus) => {
                props.onChange(sampleStatus, "sampleStatus")
            }}
            inputProps={{
                name: "sampleStatus",
                id: "sampleStatus-simple",
                readOnly: props.readOnly
            }}
        >
            {props.listSampleStatus.map((item) => {
                return (
                    <MenuItem key={item.id} value={item.id}>
                        {item.name}
                    </MenuItem>
                );
            })}
        </Select>
    );
}
class SampleEditorDialog extends React.Component {
    state = {
        path: '',
        rowsPerPage: 5,
        page: 0,
        data: [],
        totalElements: 0,
        sampleTestType: 0,
        itemList: [],
        shouldOpenEditorDialog: false,
        shouldOpenConfirmationDialog: false,
        selectedItem: {},
        person: null,
        keyword: '',
        wardOfResidence: {},
        wardOfResidenceSearch: {},
        districtOfResidence: {},
        districtOfResidenceSearch: {},
        provinceOfResidence: {},
        provinceOfResidence: {},
        isolationPlace: {},
        provinceOfResidenceSearch: { pageIndex: 0, pageSize: 10000000, isGetAllCity: true },
        shouldOpenPhysicalAdminUnitPopup: false,
        shouldOpenResidentAdminUnitPopup: false,
        shouldOpenHealthOrganizationPopup: false,
        shouldOpenNotificationPopup: false,
        shouldOpenSelectSamplePopup: false,
        shouldOpenSuspectedPersonPopup: false,
        shouldOpenSampleCollectOrgPopup: false,
        sampleType: null,
        allTypeOrgUser: false,
        formReadonly: {
            personalInfo: false,
            sampleType: false,
            code: false,
            sampleStatus: false,
            sampleDate: false,
            shipDate: false,
            testingDate: false,
            resultDate: false,
            sampleCollectOrg: false,
            labTest: false,
            sampleResult: false,
            saveBtn: false,
        },
    }

    handleDialogClose = () => {
        this.setState({
            shouldOpenEditorDialog: false,
            shouldOpenConfirmationDialog: false,
            shouldOpenNotificationPopup: false,
            shouldOpenSelectParentPopup: false,
            shouldOpenHealthOrganizationPopup: false,
            shouldOpenSampleCollectOrgPopup: false
        })
    }

    handleSuspectedPersonClose = () => {
        this.setState({
            shouldOpenSuspectedPersonPopup: false
        })
    }

    openParentPopup = (event) => {
        this.setState({ shouldOpenSelectParentPopup: true });
    }
    handleSelectParent = (parent) => {
        this.setState({ chidren: parent });
        this.setState({ shouldOpenSelectParentPopup: false });
    }
    handleChange = (event, source) => {
        event.persist();
        if (source === 'sampleType') {
            let sampleType = this.state;
            sampleType = event.target.value;
            this.setState({ sampleType: sampleType })
            return
        }
        if (source === 'sampleTestType') {
            let sampleTestType = this.state;
            sampleTestType = event.target.value;
            this.setState({ sampleTestType: sampleTestType })
            return
        }
        if (source === 'sampleStatus') {
            let sampleStatus = this.state;
            sampleStatus = event.target.value;
            this.setState({ sampleStatus: sampleStatus })
            return
        }
        if (source === 'sampleResult') {
            let sampleResult = this.state;
            sampleResult = event.target.value;
            this.setState({ sampleResult: sampleResult })
            return
        }
        this.setState({
            [event.target.name]: event.target.value,
        })
    }
    handleChangePerson = (event, source) => {
        let { person } = this.state;
        if (person == null) {
            person = {}
        }
        if (source === 'birthDate') {
            person["birthDate"] = event;
            this.setState({ person: person })
            return
        }

        if (source === 'lastContactDate') {
            person["lastContactDate"] = event;
            this.setState({ person: person })
            return
        }
        let name = event.target.name;
        let value = event.target.value;
        person[name] = value;

        this.setState({
            person: person
        })
    }

    handleDelete = (row) => {
        let { chidren } = this.state;
        console.log(row, chidren)
    }

    selectHealthOrganization = (sampleCollectOrg) => {
        this.setState({ sampleCollectOrg: sampleCollectOrg }, function () {
        });
    }

    handleSelectHealthOrganization = (labTest) => {
        if (labTest && labTest.id) {
            this.setState({ labTest: labTest });
            this.handleDialogClose();
        }
    }

    handleSelectSampleCollectOrg = (sampleCollectOrg) => {
        if (sampleCollectOrg && sampleCollectOrg.id) {
            this.setState({ sampleCollectOrg: sampleCollectOrg });
            this.handleDialogClose();
        }
    }

    handleSelectSuspectedPerson = (person) => {
        console.log(person);
        this.setState({ ...{ person: person } });
        this.handleSuspectedPersonClose();
    }
    selectSuspectedType = (suspectedType) => {
        // this.setState({ suspectedType: suspectedType }, function () {
        // });
        let { person } = this.state;
        if (person == null) {
            person = {}
        }
        person["suspectedType"] = suspectedType
        this.setState({ person: person }, function () {
        });
    }

    selectEpidemiologicalFactors = (epidemiologicalFactors) => {
        // this.setState({ suspectedType: suspectedType }, function () {
        // });
        let { person } = this.state;
        if (person == null) {
            person = {}
        }
        person["epidemiologicalFactors"] = epidemiologicalFactors
        this.setState({ person: person }, function () {
        });
    }

    selectSuspectedLevel = (suspectedLevel) => {
        let { person } = this.state;
        if (person == null) {
            person = {}
        }
        person["suspectedLevel"] = suspectedLevel
        this.setState({ person: person }, function () {
        });
    }

    handleSelectdministrativeUnit = (value, source) => {
        let { person } = this.state;
        if (person == null) {
            person = {}
        }
        if ("provinceOfResidence" == source) {
            person.provinceOfResidence = value
            //   this.setState({ provinceOfResidence: value });
            this.setState({ person: person })
            if (value != null) {
                this.setState({ districtOfResidenceSearch: { pageIndex: 0, pageSize: 10000000, parentId: value.id } });
            } else {
                // this.setState({ districtOfResidence: {} });
                // this.setState({ wardOfResidence: {} });
                person.districtOfResidence = {}
                person.wardOfResidence = {}
                this.setState({ person: person })
                this.setState({ districtOfResidenceSearch: { pageIndex: 0, pageSize: 10000000 } });
                this.setState({ wardOfResidenceSearch: { pageIndex: 0, pageSize: 10000000 } });
            }
        }
        if ("districtOfResidence" == source) {
            person.districtOfResidence = value
            //   this.setState({ districtOfResidence: value });
            this.setState({ person: person })
            if (value != null) {
                this.setState({ wardOfResidenceSearch: { pageIndex: 0, pageSize: 10000000, parentId: value.id } });
            } else {
                person.wardOfResidence = {}
                this.setState({ person: person })
                // this.setState({ wardOfResidence: {} });
                this.setState({ wardOfResidenceSearch: { pageIndex: 0, pageSize: 10000000 } });
            }
        }
        if ("wardOfResidence" == source) {
            //   this.setState({ wardOfResidence: value });
            person.wardOfResidence = value
            this.setState({ person: person })
        }

    }

    selectIsolationHealthOrganization = (isolationPlace) => {
        let { person } = this.state;
        if (person == null) {
            person = {}
        }
        person.isolationPlace = isolationPlace
        this.setState({ person: person }, function () { });
    };

    componentDidMount() {
    }
    componentWillMount() {
        let { item } = this.props;
        this.setState({ ...item }, () => {
            let user = authService.getLoginUser();
            if (user != null && !UserRoles.isAdmin()) {
                getAllOrgByUserId(user.id).then(({ data }) => {
                    if (data != null && data.org != null) {
                        if (data.org.orgType == 1) {
                            if (this.state.isAddnew) {
                                this.setState({
                                    formReadonly: {
                                        personalInfo: false,
                                        sampleType: true,
                                        code: false,
                                        sampleStatus: false,
                                        sampleDate: false,
                                        shipDate: false,
                                        testingDate: false,
                                        resultDate: false,
                                        sampleCollectOrg: false,
                                        sampleResult: false,
                                        labTest: false,
                                        sampleTestType: true
                                    },
                                    labTestUser: true,
                                    labTest: data.org,
                                    sampleTestType: 1//Mẫu nhận từ ngoài vào
                                });
                            } else {
                                this.setState({
                                    formReadonly: {
                                        personalInfo: true,
                                        sampleType: true,
                                        code: true,
                                        sampleStatus: false,
                                        sampleDate: true,
                                        shipDate: true,
                                        testingDate: false,
                                        resultDate: false,
                                        sampleCollectOrg: true,
                                        sampleResult: false,
                                        labTest: true,
                                        sampleTestType: true
                                    }, labTestUser: true,
                                    // labTest: data.org
                                });
                            }
                        }
                        if (data.org.orgType == 2) {
                            if (this.state.isAddnew) {
                                this.setState({
                                    sampleCollectUser: true,
                                    sampleCollectOrg: data.org
                                });
                            } else {
                                this.setState({
                                    sampleCollectUser: true,
                                });
                            }

                            if (item != null && item.id != null && item.sampleStatus != 'Draft') {
                                if (this.state.sampleTestType == 2) {
                                    this.setState({
                                        formReadonly: {
                                            personalInfo: true,
                                            sampleType: true,
                                            code: true,
                                            sampleStatus: false,
                                            sampleDate: true,
                                            shipDate: true,
                                            testingDate: false,
                                            resultDate: false,
                                            sampleCollectOrg: true,
                                            labTest: true,
                                            saveBtn: false,
                                            sampleResult: false,
                                            sampleTestType: true
                                        },
                                    });
                                } else {
                                    this.setState({
                                        formReadonly: {
                                            personalInfo: true,
                                            sampleType: true,
                                            code: true,
                                            sampleStatus: true,
                                            sampleDate: true,
                                            shipDate: true,
                                            testingDate: true,
                                            resultDate: true,
                                            sampleCollectOrg: true,
                                            labTest: true,
                                            saveBtn: true,
                                            sampleResult: true,
                                            sampleTestType: true
                                        },
                                    });
                                }
                            } else {
                                this.setState({
                                    formReadonly: {
                                        personalInfo: false,
                                        sampleType: true,
                                        code: false,
                                        sampleStatus: false,
                                        sampleDate: false,
                                        shipDate: false,
                                        testingDate: true,
                                        resultDate: true,
                                        sampleResult: true,
                                        sampleCollectOrg: false,
                                        labTest: false,
                                        // sampleTestType: true
                                    },
                                });
                            }
                        }
                        if (data.org.orgType == 0) {
                            this.setState({
                                allTypeOrgUser: true,
                            });
                        }
                    }

                });
            }
            this.setState({ collectUser: true }, function () {

            });
        }
        );
    }

    handleFormSubmit = () => {
        let { t } = this.props

        if (this.state.sampleDate == null) {
            alert("Ngày nhập mẫu không được để trống");
            return
        }
        let item = {};

        item.id = this.state.id;
        item.parent = this.state.parent;
        item.sampleDate = this.state.sampleDate;
        item.testingDate = this.state.testingDate;
        item.resultDate = this.state.resultDate;
        item.parent = this.state.parent;
        item.chidren = this.state.chidren;
        item.sampleStatus = this.state.sampleStatus;
        item.sampleType = this.state.sampleType;
        item.person = this.state.person
        item.name = this.state.name;
        item.code = this.state.code;
        item.shipDate = this.state.shipDate;
        item.labTest = this.state.labTest;
        item.sampleCollectOrg = this.state.sampleCollectOrg
        item.sampleAdminUnit = this.state.sampleAdminUnit
        item.sampleResult = this.state.sampleResult
        item.sampleTestType = this.state.sampleTestType
        checkCode(item.id, item.code).then((result) => {
            //Nếu trả về true là code đã được sử dụng
            if (result.data) {
                console.log("Code đã được sử dụng");
                toast.warning(t('mess_code'));
                this.setState({ loading: false })
            } else {
                if (item.id) {
                    saveItem(item).then(() => {
                        toast.success(t('mess_edit'));
                        this.props.handleOKEditClose()
                    })
                } else {
                    saveItem(item).then(() => {
                        toast.success(t('mess_add'));
                        this.props.handleOKEditClose()
                    })
                }
            }
        });
    }

    render() {
        const {
            t,
            i18n,
            handleClose,
            handleSelect,
            selectedItem,
            open,
            item,
        } = this.props
        let searchObject = { pageIndex: 0, pageSize: 100000 }
        let {
            id,
            keyword,
            isCheck,
            sampleType,
            sampleStatus,
            name, code,
            labTest,
            person,
            isAddnew,
            shouldOpenHealthOrganizationPopup,
            shouldOpenNotificationPopup, districtOfResidenceSearch, provinceOfResidenceSearch, wardOfResidenceSearch,
            sampleCollectOrg, sampleResult, provinceOfResidence, districtOfResidence, wardOfResidence,
            sampleDate, shipDate, testingDate, resultDate, formReadonly, labTestUser, sampleTestType,
            allTypeOrgUser, sampleCollectUser, shouldOpenSampleCollectOrgPopup,
            shouldOpenSuspectedPersonPopup
        } = this.state;
        let columns = [
            // { title: t('Tên mẫu'), field: 'name', width: '150' },
            { title: t('Mã mẫu'), field: 'code', align: 'left', width: '150' },
            { title: t('Tên bệnh nhân'), field: 'person.name', align: 'left', width: '150' },
        ]

        return (
            <Dialog open={open} PaperComponent={PaperComponent} maxWidth="md" fullWidth={true}>
                {shouldOpenNotificationPopup && (
                    <NotificationPopup
                        title={t('general.noti')}
                        open={shouldOpenNotificationPopup}
                        // onConfirmDialogClose={this.handleDialogClose}
                        onYesClick={this.handleDialogClose}
                        text={t(this.state.Notification)}
                        agree={t('general.agree')}
                    />
                )}
                <DialogTitle style={{ cursor: 'move' }} id="draggable-dialog-title">
                    {t('general.saveUpdate')}
                </DialogTitle>
                <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
                    <DialogContent>
                        <Grid className="" container spacing={2}>

                            {this.state.shouldOpenSelectParentPopup && (
                                <SelectSamplePopup
                                    open={this.state.shouldOpenSelectParentPopup}
                                    handleSelect={this.handleSelectParent}
                                    selectedItem={
                                        this.state.chidren != null ? this.state.chidren : []
                                    }
                                    handleClose={this.handleDialogClose}
                                    t={t}
                                    i18n={i18n}
                                />
                            )}
                            {/* <Grid item lg={3} md={6} sm={6} xs={12}>
                                <FormControl fullWidth={true}>
                                    <InputLabel htmlFor="sampleType-simple">
                                        {t('Loại mẫu')}
                                    </InputLabel>
                                    <Select
                                        value={sampleType ? sampleType : {}}
                                        disabled={id ? true : false}
                                        onChange={(sampleType) => this.handleChange(sampleType, 'sampleType')}
                                        inputProps={{
                                            name: 'sampleType',
                                            id: 'sampleType-simple',
                                            readOnly: formReadonly.sampleType
                                        }}
                                    >
                                        {Constant.listSampleType.map((item) => {
                                            return (
                                                <MenuItem key={item.id} value={item.id}>
                                                    {item.name}
                                                </MenuItem>
                                            )
                                        })}
                                    </Select>
                                </FormControl>
                            </Grid> */}
                            <Grid item lg={3} md={6} sm={6} xs={12}>
                                <FormControl fullWidth={true}>
                                    <InputLabel htmlFor="sampleTestType-simple">
                                        {t('Nguồn gốc mẫu')}
                                    </InputLabel>
                                    <Select
                                        value={sampleTestType}
                                        onChange={(sampleTestType) => this.handleChange(sampleTestType, 'sampleTestType')}
                                        inputProps={{
                                            name: 'sampleTestType',
                                            id: 'sampleTestType-simple',
                                            readOnly: formReadonly.sampleTestType
                                        }}
                                    >
                                        {Constant.SampleTestTypeEnum.map((item) => {
                                            return (
                                                <MenuItem key={item.id} value={item.id}>
                                                    {item.name}
                                                </MenuItem>
                                            )
                                        })}
                                    </Select>
                                </FormControl>
                            </Grid>
                            <Grid item lg={3} md={6} sm={6} xs={12}>
                                <TextValidator
                                    className="w-100 "
                                    label={
                                        <span>
                                            <span style={{ color: 'red' }}> *</span>

                                            {t('Mã mẫu')}
                                        </span>
                                    }
                                    onChange={this.handleChange}
                                    type="text"
                                    name="code"
                                    value={code}
                                    inputProps={{
                                        readOnly: formReadonly.code
                                    }}
                                    validators={['required']}
                                    errorMessages={[t('general.required')]}
                                />
                            </Grid>
                            {/* <Grid item lg={6} md={6} sm={6} xs={12}>
                                <FormControl fullWidth={true}>
                                    <InputLabel htmlFor="sampleStatus-simple">
                                        {t('Tình trạng mẫu')}
                                    </InputLabel>
                                    <Select
                                        value={sampleStatus}
                                        onChange={(sampleStatus) => this.handleChange(sampleStatus, 'sampleStatus')}
                                        inputProps={{
                                            name: 'sampleStatus',
                                            id: 'sampleStatus-simple',
                                        }}
                                    >
                                        {this.listSampleStatus.map((item) => {
                                            return (
                                                <MenuItem key={item.id} value={item.id}>
                                                    {item.name}
                                                </MenuItem>
                                            )
                                        })}
                                    </Select>
                                </FormControl>
                            </Grid> */}
                            <Grid item lg={3} md={6} sm={6} xs={12}>
                                <FormControl fullWidth={true}>
                                    <InputLabel htmlFor="sampleStatus-simple">
                                        {t('Tình trạng mẫu')}
                                    </InputLabel>
                                    {labTestUser && <SelectStatus
                                        value={sampleStatus}
                                        onChange={this.handleChange}
                                        readOnly={formReadonly.sampleStatus}
                                        listSampleStatus={Constant.listSampleStatusLabtest} />}
                                    {(sampleCollectUser && this.state.sampleTestType != 2) && <SelectStatus
                                        value={sampleStatus}
                                        onChange={this.handleChange}
                                        readOnly={formReadonly.sampleStatus}
                                        listSampleStatus={Constant.listSampleStatusCollectionOrg} />}
                                    {(sampleCollectUser && this.state.sampleTestType == 2) && <SelectStatus
                                        value={sampleStatus}
                                        onChange={this.handleChange}
                                        readOnly={formReadonly.sampleStatus}
                                        listSampleStatus={Constant.listSampleStatusCollectionOrg} />}
                                    {(sampleCollectUser && this.state.sampleTestType == 2 && this.state.id) && <SelectStatus
                                        value={sampleStatus}
                                        onChange={this.handleChange}
                                        readOnly={formReadonly.sampleStatus}
                                        listSampleStatus={Constant.listSampleStatus} />}
                                    {(UserRoles.isAdmin() || allTypeOrgUser) && <SelectStatus
                                        value={sampleStatus}
                                        onChange={this.handleChange}
                                        readOnly={formReadonly.sampleStatus}
                                        listSampleStatus={Constant.listSampleStatus} />}
                                </FormControl>
                            </Grid>
                            <Grid item lg={3} md={6} sm={6} xs={12}>
                                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                                    <KeyboardDatePicker
                                        size="small"
                                        className="w-100"
                                        margin="none"
                                        id="date-picker-dialog"
                                        label={
                                            <span>
                                                <span style={{ color: "red" }}>*</span>
                                                {t("Ngày lấy mẫu")}
                                            </span>
                                        }
                                        inputVariant="standard"
                                        type="text"
                                        autoOk={true}
                                        format="dd/MM/yyyy"
                                        inputProps={{
                                            readOnly: formReadonly.sampleDate
                                        }}
                                        value={sampleDate ? sampleDate : null}
                                        onChange={(value) => {
                                            this.setState({ sampleDate: value })
                                        }}
                                        KeyboardButtonProps={{
                                            'aria-label': 'change date',
                                            disabled: formReadonly.sampleDate
                                        }}
                                        validators={["required"]}
                                        errorMessages={t('general.errorMessages_required')}
                                    />
                                </MuiPickersUtilsProvider>
                            </Grid>
                            <Grid item lg={3} md={6} sm={6} xs={12}>
                                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                                    <KeyboardDatePicker
                                        size="small"
                                        className="w-100"
                                        margin="none"
                                        id="date-picker-dialog"
                                        InputProps={{
                                            readOnly: formReadonly.shipDate
                                        }}
                                        label={
                                            <span>
                                                {/* <span style={{ color: "red" }}>*</span> */}
                                                {t("Ngày gửi mẫu")}
                                            </span>
                                        }
                                        inputVariant="standard"
                                        type="text"
                                        autoOk={true}
                                        format="dd/MM/yyyy"
                                        value={shipDate ? shipDate : null}
                                        onChange={(value) => {
                                            this.setState({ shipDate: value })
                                        }}
                                        KeyboardButtonProps={{
                                            'aria-label': 'change date',
                                            disabled: formReadonly.shipDate
                                        }}
                                    // validators={["required"]}
                                    // errorMessages={t('general.errorMessages_required')}
                                    />
                                </MuiPickersUtilsProvider>
                            </Grid>
                            <Grid item lg={3} md={6} sm={6} xs={12}>
                                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                                    <KeyboardDatePicker
                                        size="small"
                                        className="w-100"
                                        margin="none"
                                        id="date-picker-dialog"
                                        label={
                                            <span>
                                                {/* <span style={{ color: "red" }}>*</span> */}
                                                {t("Ngày làm xét nghiệm")}
                                            </span>
                                        }
                                        InputProps={{
                                            readOnly: formReadonly.testingDate
                                        }}
                                        inputVariant="standard"
                                        type="text"
                                        autoOk={true}
                                        format="dd/MM/yyyy"
                                        value={testingDate ? testingDate : null}
                                        onChange={(value) => {
                                            this.setState({ testingDate: value })
                                        }}
                                        KeyboardButtonProps={{
                                            'aria-label': 'change date',
                                            disabled: formReadonly.testingDate
                                        }}
                                    // validators={["required"]}
                                    // errorMessages={t('general.errorMessages_required')}
                                    />
                                </MuiPickersUtilsProvider>
                            </Grid>
                            <Grid item lg={3} md={6} sm={6} xs={12}>
                                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                                    <KeyboardDatePicker
                                        size="small"
                                        className="w-100"
                                        margin="none"
                                        id="date-picker-dialog"
                                        label={
                                            <span>
                                                {/* <span style={{ color: "red" }}>*</span> */}
                                                {t("Ngày có kết quả")}
                                            </span>
                                        }
                                        InputProps={{ readOnly: formReadonly.resultDate }}
                                        inputVariant="standard"
                                        type="text"
                                        autoOk={true}
                                        format="dd/MM/yyyy"
                                        value={resultDate ? resultDate : null}
                                        onChange={(value) => {
                                            this.setState({ resultDate: value })
                                        }}
                                        KeyboardButtonProps={{
                                            'aria-label': 'change date',
                                            disabled: formReadonly.resultDate
                                        }}
                                    // validators={["required"]}
                                    // errorMessages={t('general.errorMessages_required')}
                                    />
                                </MuiPickersUtilsProvider>
                            </Grid>
                            <Grid item lg={3} md={6} sm={6} xs={12}>
                                <FormControl fullWidth={true}>
                                    <InputLabel htmlFor="sampleResult-simple">
                                        {t('Kết quả mẫu')}
                                    </InputLabel>
                                    <Select
                                        value={sampleResult}
                                        onChange={(sampleResult) => this.handleChange(sampleResult, 'sampleResult')}
                                        inputProps={{
                                            name: 'sampleResult',
                                            id: 'sampleResult-simple',
                                            readOnly: formReadonly.sampleResult
                                        }}
                                    >
                                        {Constant.listSampleResult.map((item) => {
                                            return (
                                                <MenuItem key={item.id} value={item.id}>
                                                    {item.name}
                                                </MenuItem>
                                            )
                                        })}
                                    </Select>
                                </FormControl>
                            </Grid>
                            <Grid item lg={6} md={6} sm={6} xs={12}>
                                {/* <AsynchronousAutocomplete label={t("Chọn đơn vị lấy mẫu")}
                                    searchFunction={healthOrganizationSearchByPage}
                                    searchObject={searchObject}
                                    multiple={false}
                                    value={sampleCollectOrg ? sampleCollectOrg : null}
                                    defaultValue={sampleCollectOrg ? sampleCollectOrg : null}
                                    displayLable={'name'}
                                    disabled={formReadonly.personalInfo}
                                    variant="standard"
                                    className="mb-16 w-100"
                                    onSelect={this.selectHealthOrganization}
                                /> */}
                                <TextValidator
                                    size="small"
                                    className="w-100 "
                                    label={
                                        <span className="font">
                                            <span style={{ color: "red" }}> * </span>
                                            {t("Chọn đơn vị lấy mẫu")}
                                        </span>
                                    }
                                    name="sampleCollectOrg"
                                    value={sampleCollectOrg ? sampleCollectOrg.name : ""}
                                    InputProps={{
                                        readOnly: formReadonly.personalInfo,
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <Button
                                                    size={'small'}
                                                    className="align-bottom"
                                                    variant="contained"
                                                    color="primary"
                                                    onClick={() => this.setState({ shouldOpenSampleCollectOrgPopup: true })}
                                                >
                                                    {t('Select')}
                                                </Button>
                                            </InputAdornment>
                                        ),
                                    }}
                                    validators={["required"]}
                                    errorMessages={[t("general.errorMessages_required")]}
                                    // variant="outlined"
                                    size="small"
                                />
                                {shouldOpenSampleCollectOrgPopup && !formReadonly.personalInfo && (
                                    <HealthOrganizationPopup
                                        open={shouldOpenSampleCollectOrgPopup}
                                        listOrgType={Constant.listCollectOrgType}
                                        orgType={2}//Là đơn vị lấy mẫu
                                        isExternalOrg={this.state.sampleTestType == 1 ? true : "null"}
                                        handleSelect={this.handleSelectSampleCollectOrg}
                                        item={sampleCollectOrg}
                                        handleClose={this.handleDialogClose}
                                        t={t} i18n={i18n}
                                    ></HealthOrganizationPopup>
                                )
                                }
                            </Grid>

                            <Grid item lg={6} md={6} sm={6} xs={12}>
                                <TextValidator
                                    size="small"
                                    className="w-100 "
                                    label={
                                        <span className="font">
                                            <span style={{ color: "red" }}> * </span>
                                            {t("Chọn đơn vị xét nghiệm")}
                                        </span>
                                    }
                                    name="labTest"
                                    value={labTest ? labTest.name : ""}
                                    InputProps={{
                                        readOnly: formReadonly.labTest,
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <Button
                                                    size={'small'}
                                                    className="align-bottom"
                                                    variant="contained"
                                                    color="primary"
                                                    onClick={() => this.setState({ shouldOpenHealthOrganizationPopup: true })}
                                                >
                                                    {t('Select')}
                                                </Button>
                                            </InputAdornment>
                                        ),
                                    }}
                                    validators={["required"]}
                                    errorMessages={[t("general.errorMessages_required")]}
                                    // variant="outlined"
                                    size="small"
                                />
                                {shouldOpenHealthOrganizationPopup && !formReadonly.labTest && (
                                    <HealthOrganizationPopup
                                        open={shouldOpenHealthOrganizationPopup}
                                        listOrgType={Constant.listLabTestOrgType}
                                        orgType={1}//Là đơn vị xét nghiệm
                                        isExternalOrg={this.state.sampleTestType == 2 ? true : "null"}
                                        handleSelect={this.handleSelectHealthOrganization}
                                        item={labTest}
                                        handleClose={this.handleDialogClose}
                                        t={t} i18n={i18n}
                                    ></HealthOrganizationPopup>
                                )
                                }
                            </Grid>

                            {sampleType != null && sampleType == 1 && (
                                <fieldset style={{ width: "inherit" }}>
                                    <legend>Thông tin cá nhân:</legend>
                                    <Grid container spacing={2}>

                                        {/* <Grid item lg={3} md={6} sm={6} xs={12}>
                                            <TextValidator
                                                className="w-100 "
                                                label={
                                                    <span>
                                                        <span style={{ color: 'red' }}> *</span>

                                                        {t('Họ')}
                                                    </span>
                                                }
                                                inputProps={{
                                                    readOnly: formReadonly.personalInfo
                                                }}
                                                onChange={this.handleChangePerson}
                                                type="text"
                                                name="giveName"
                                                value={person ? person.giveName : ""}
                                                validators={['required']}
                                                errorMessages={[t('general.required')]}
                                            />
                                        </Grid> */}
                                        {isAddnew && (<Grid xs={12}>
                                            <Button
                                                className="mt-8"
                                                variant="contained"
                                                size="small"
                                                color="primary"
                                                onClick={() => { this.setState({ shouldOpenSuspectedPersonPopup: true }) }}>{t('Chọn người nghi nhiễm')}
                                            </Button>

                                            {shouldOpenSuspectedPersonPopup && (
                                                <SuspectedPersonSearchDialog
                                                    open={shouldOpenSuspectedPersonPopup}
                                                    handleSelect={this.handleSelectSuspectedPerson}
                                                    item={person}
                                                    handleClose={this.handleSuspectedPersonClose}
                                                    t={t}
                                                    i18n={i18n}
                                                ></SuspectedPersonSearchDialog>
                                            )}
                                        </Grid>)
                                        }

                                        <Grid item lg={3} md={6} sm={6} xs={12}>
                                            <TextValidator
                                                className="w-100 "
                                                label={
                                                    <span>
                                                        <span style={{ color: 'red' }}> *</span>

                                                        {t('Tên bệnh nhân')}
                                                    </span>
                                                }
                                                inputProps={{
                                                    readOnly: formReadonly.personalInfo
                                                }}
                                                onChange={this.handleChangePerson}
                                                type="text"
                                                name="name"
                                                value={person ? person.name ? person.name : "" : ""}
                                                validators={['required']}
                                                errorMessages={[t('general.required')]}
                                            />
                                        </Grid>

                                        <Grid item lg={3} md={6} sm={6} xs={12}>
                                            <TextValidator
                                                className="w-100 "
                                                label={
                                                    <span>
                                                        {t('Số căn cước(CMND)')}
                                                    </span>
                                                }
                                                inputProps={{
                                                    readOnly: formReadonly.personalInfo
                                                }}
                                                onChange={this.handleChangePerson}
                                                type="text"
                                                name="idNumber"
                                                value={person ? person.idNumber ? person.idNumber : '' : ""}
                                            />
                                        </Grid>

                                        <Grid item lg={3} md={6} sm={6} xs={12}>
                                            <FormControl fullWidth={true}>
                                                <InputLabel htmlFor="gender-simple">{t('user.gender')}</InputLabel>
                                                <Select
                                                    value={person ? person.gender ? person.gender : '' : ''}
                                                    onChange={gender => this.handleChangePerson(gender, "gender")}
                                                    inputProps={{
                                                        name: "gender",
                                                        id: "gender-simple",
                                                        readOnly: formReadonly.personalInfo
                                                    }}
                                                >
                                                    {Constant.listGender.map(item => {
                                                        return <MenuItem key={item.id} value={item.id}>{item.name}</MenuItem>;
                                                    })}
                                                </Select>
                                            </FormControl>
                                        </Grid>
                                        <Grid item lg={3} md={6} sm={6} xs={12}>
                                            <MuiPickersUtilsProvider utils={DateFnsUtils}>
                                                <KeyboardDatePicker
                                                    size="small"
                                                    className="w-100"
                                                    margin="none"
                                                    id="mui-pickers-birthDate"
                                                    label={<span className="font">{t("Ngày sinh")}</span>}
                                                    inputVariant="standard"
                                                    type="text"
                                                    autoOk={true}
                                                    format="yyyy"
                                                    value={person ? person.birthDate ? person.birthDate : null : null}
                                                    onChange={date => this.handleChangePerson(date, "birthDate")}
                                                    fullWidth
                                                    validators={["required"]}
                                                    errorMessages={t('general.errorMessages_required')}
                                                    InputProps={{ readOnly: formReadonly.personalInfo }}
                                                    KeyboardButtonProps={{
                                                        'aria-label': 'change date',
                                                        disabled: formReadonly.personalInfo
                                                    }}
                                                />
                                            </MuiPickersUtilsProvider>
                                        </Grid>
                                        <Grid item lg={3} md={6} sm={6} xs={12}>
                                            <AsynchronousAutocomplete label={t("Thuộc đối tượng")}
                                                searchFunction={suspectedLevelSearchByPage}
                                                searchObject={searchObject}
                                                value={person ? (person.suspectedLevel ? person.suspectedLevel : null) : null}
                                                multiple={false}
                                                defaultValue={person ? (person.suspectedLevel ? person.suspectedLevel : null) : null}
                                                displayLable={'name'}
                                                disabled={formReadonly.personalInfo}
                                                variant="standard"
                                                className="mb-16 w-100"
                                                onSelect={this.selectSuspectedLevel}
                                            />
                                        </Grid>

                                        <Grid item lg={3} md={6} sm={6} xs={12}>
                                            <AsynchronousAutocomplete label={t("Yếu tố dịch tễ")}
                                                searchFunction={epidemiologicalFactorsSearchByPage}
                                                searchObject={searchObject}
                                                value={person ? (person.epidemiologicalFactors ? person.epidemiologicalFactors : null) : null}
                                                defaultValue={person ? (person.epidemiologicalFactors ? person.epidemiologicalFactors : null) : null}
                                                multiple={false}
                                                displayLable={'name'}
                                                disabled={formReadonly.personalInfo}
                                                // validators={["required"]}
                                                // errorMessages={t('')}
                                                variant="standard"
                                                className="mb-16 w-100"
                                                onSelect={this.selectEpidemiologicalFactors}
                                            />
                                        </Grid>
                                        <Grid item lg={3} md={6} sm={6} xs={12}>
                                            <TextValidator
                                                className="w-100 "
                                                label={
                                                    <span>
                                                        {t('Yếu tố dịch tễ chi tiết (mô tả)')}
                                                    </span>
                                                }
                                                InputProps={{ readOnly: formReadonly.personalInfo }}
                                                onChange={this.handleChangePerson}
                                                type="text"
                                                name="detailEpidemiologicalFactors"
                                                value={person ? person.detailEpidemiologicalFactors ? person.detailEpidemiologicalFactors : "" : ""}
                                            // validators={['required']}
                                            // errorMessages={[t('general.required')]}
                                            />
                                        </Grid>
                                        <Grid item lg={3} md={6} sm={6} xs={12}>
                                            <TextValidator
                                                className="w-100 "
                                                label={
                                                    <span>
                                                        <span style={{ color: 'red' }}> *</span>
                                                        {t('Số điện thoại')}
                                                    </span>
                                                }
                                                InputProps={{ readOnly: formReadonly.personalInfo }}
                                                onChange={this.handleChangePerson}
                                                type="text"
                                                name="phoneNumber"
                                                value={person ? person.phoneNumber ? person.phoneNumber : "" : ""}
                                                validators={['required']}
                                                errorMessages={[t('general.required')]}
                                            />
                                        </Grid>
                                        <Grid item lg={3} md={3} sm={6} xs={12}>
                                            <AsynchronousAutocomplete
                                                label={
                                                    <span className="font">
                                                        <span style={{ color: "red" }}> * </span>
                                                        {t("Tỉnh/Thành Phố")}
                                                    </span>
                                                }
                                                disabled={formReadonly.personalInfo}
                                                searchFunction={getAdministrativeUnitByPage}
                                                searchObject={provinceOfResidenceSearch}
                                                value={person ? person.provinceOfResidence ? person.provinceOfResidence : null : null}
                                                multiple={false}
                                                defaultValue={person ? person.provinceOfResidence ? person.provinceOfResidence : null : null}
                                                displayLable={'name'}
                                                className="w-100"
                                                validators={["required"]}
                                                errorMessages={[t("general.required")]}
                                                onSelect={(value) => { this.handleSelectdministrativeUnit(value, "provinceOfResidence") }}
                                            />
                                        </Grid>
                                        <Grid item lg={3} md={3} sm={6} xs={12}>
                                            <AsynchronousAutocomplete
                                                label={
                                                    <span className="font">
                                                        <span style={{ color: "red" }}> * </span>
                                                        {t("Quận huyện")}
                                                    </span>
                                                }
                                                searchFunction={getAdministrativeUnitByPage}
                                                searchObject={districtOfResidenceSearch}
                                                value={person ? person.districtOfResidence ? person.districtOfResidence : null : null}
                                                multiple={false}
                                                disabled={formReadonly.personalInfo}
                                                defaultValue={person ? person.districtOfResidence ? person.districtOfResidence : null : null}
                                                displayLable={'name'}
                                                className="w-100"
                                                validators={["required"]}
                                                errorMessages={[t("general.required")]}
                                                onSelect={(value) => { this.handleSelectdministrativeUnit(value, "districtOfResidence") }}
                                            />
                                        </Grid>
                                        <Grid item lg={3} md={3} sm={6} xs={12}>
                                            <AsynchronousAutocomplete
                                                label={
                                                    <span className="font">
                                                        <span style={{ color: "red" }}> * </span>
                                                        {t("Xã/Phường")}
                                                    </span>
                                                }
                                                searchFunction={getAdministrativeUnitByPage}
                                                searchObject={wardOfResidenceSearch}
                                                value={person ? person.wardOfResidence ? person.wardOfResidence : null : null}
                                                multiple={false}
                                                disabled={formReadonly.personalInfo}
                                                defaultValue={person ? person.wardOfResidence ? person.wardOfResidence : null : null}
                                                displayLable={'name'}
                                                className="w-100"
                                                validators={["required"]}
                                                errorMessages={[t("general.required")]}
                                                onSelect={(value) => { this.handleSelectdministrativeUnit(value, "wardOfResidence") }}
                                            />
                                        </Grid>
                                        <Grid item lg={3} md={3} sm={6} xs={12}>
                                            <TextValidator
                                                className="w-100 "
                                                label={
                                                    <span>
                                                        {t('Địa chỉ chi tiết hiện tại')}
                                                    </span>
                                                }
                                                InputProps={{ readOnly: formReadonly.personalInfo }}
                                                onChange={this.handleChangePerson}
                                                type="text"
                                                name="detailAddress"
                                                value={person ? person.detailAddress ? person.detailAddress : "" : ""}
                                            // validators={['required']}
                                            // errorMessages={[t('general.required')]}
                                            />
                                        </Grid>
                                        <Grid item lg={3} md={3} sm={6} xs={12}>
                                            <TextValidator
                                                className="w-100"
                                                onChange={this.handleChangePerson}
                                                name="career"
                                                value={person ? person.career ? person.career : "" : ''}
                                                label={
                                                    <span className="font">
                                                        {/* <span style={{ color: "red" }}> * </span> */}
                                                        {t("suspected_person.career")}
                                                    </span>
                                                }
                                                size="small"
                                                type="text"
                                                placeholder=""
                                            // validators={["required"]}
                                            // errorMessages={[t("general.required")]}
                                            />
                                        </Grid>

                                        <Grid item lg={3} md={3} sm={6} xs={12}>
                                            <AsynchronousAutocomplete
                                                label={t("Chọn đơn vị cách ly")}
                                                searchFunction={healthOrganizationSearchByPage}
                                                searchObject={{ pageIndex: 0, pageSize: 10000000, orgType: 3 }}
                                                multiple={false}
                                                value={person ? person.isolationPlace ? person.isolationPlace : null : null}
                                                defaultValue={person ? person.isolationPlace ? person.isolationPlace : null : null}
                                                displayLable={"name"}
                                                variant="standard"
                                                className="mb-16 w-100"
                                                disabled={formReadonly.personalInfo}
                                                onSelect={this.selectIsolationHealthOrganization}
                                            />
                                        </Grid>
                                        <Grid item lg={3} md={6} sm={6} xs={12}>
                                            <MuiPickersUtilsProvider utils={DateFnsUtils}>
                                                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                                                    <KeyboardDatePicker
                                                        size="small"
                                                        className="w-100"
                                                        margin="none"
                                                        id="mui-pickers-birthDate"
                                                        label={<span className="font"> {t("Ngày tiếp xúc cuối cùng")}</span>}
                                                        inputVariant="standard"
                                                        type="text"
                                                        autoOk={true}
                                                        format="dd/MM/yyyy"
                                                        value={person ? (person.lastContactDate ? person.lastContactDate : null) : null}
                                                        onChange={date => this.handleChangePerson(date, "lastContactDate")}
                                                        fullWidth
                                                        validators={["required"]}
                                                        errorMessages={t('general.errorMessages_required')}
                                                        InputProps={{ readOnly: formReadonly.personalInfo }}
                                                        KeyboardButtonProps={{
                                                            'aria-label': 'change date',
                                                            disabled: formReadonly.personalInfo
                                                        }}
                                                    />
                                                </MuiPickersUtilsProvider>
                                            </MuiPickersUtilsProvider>
                                        </Grid>
                                        <Grid item sm={3} xs={12}>
                                            <FormControl fullWidth={true}>
                                                <InputLabel htmlFor="feverStatus-simple">{t('Triệu chứng sốt')}</InputLabel>
                                                <Select
                                                    value={person ? person.feverStatus ? person.feverStatus : '' : ''}
                                                    onChange={feverStatus => this.handleChangePerson(feverStatus, "feverStatus")}
                                                    inputProps={{
                                                        name: "feverStatus",
                                                        id: "feverStatus-simple",
                                                        readOnly: formReadonly.personalInfo
                                                    }}
                                                >
                                                    {Constant.statusSymptom.map(item => {
                                                        return <MenuItem key={item.id} value={item.id}>{item.name}</MenuItem>;
                                                    })}
                                                </Select>
                                            </FormControl>
                                        </Grid>


                                        <Grid item sm={3} xs={12}>
                                            <FormControl fullWidth={true}>
                                                <InputLabel htmlFor="coughStatus-simple">{t('Triệu chứng ho')}</InputLabel>
                                                <Select
                                                    value={person ? person.coughStatus ? person.coughStatus : '' : ''}
                                                    onChange={coughStatus => this.handleChangePerson(coughStatus, "coughStatus")}
                                                    inputProps={{
                                                        name: "coughStatus",
                                                        id: "coughStatus-simple",
                                                        readOnly: formReadonly.personalInfo
                                                    }}
                                                >
                                                    {Constant.statusSymptom.map(item => {
                                                        return <MenuItem key={item.id} value={item.id}>{item.name}</MenuItem>;
                                                    })}
                                                </Select>
                                            </FormControl>
                                        </Grid>
                                        <Grid item sm={3} xs={12}>
                                            <FormControl fullWidth={true}>
                                                <InputLabel htmlFor="shortnessOfBreath-simple">{t('Triệu chứng khó thở')}</InputLabel>
                                                <Select
                                                    value={person ? person.shortnessOfBreath ? person.shortnessOfBreath : '' : ''}
                                                    onChange={shortnessOfBreath => this.handleChangePerson(shortnessOfBreath, "shortnessOfBreath")}
                                                    inputProps={{
                                                        name: "shortnessOfBreath",
                                                        id: "shortnessOfBreath-simple",
                                                        readOnly: formReadonly.personalInfo
                                                    }}
                                                >
                                                    {Constant.statusSymptom.map(item => {
                                                        return <MenuItem key={item.id} value={item.id}>{item.name}</MenuItem>;
                                                    })}
                                                </Select>
                                            </FormControl>
                                        </Grid>

                                        <Grid item sm={3} xs={12}>
                                            <FormControl fullWidth={true}>
                                                <InputLabel htmlFor="soreThroat-simple">{t('Triệu chứng đau họng')}</InputLabel>
                                                <Select
                                                    value={person ? person.soreThroat ? person.soreThroat : '' : ''}
                                                    onChange={soreThroat => this.handleChangePerson(soreThroat, "soreThroat")}
                                                    inputProps={{
                                                        name: "soreThroat",
                                                        id: "soreThroat-simple",
                                                        readOnly: formReadonly.personalInfo
                                                    }}
                                                >
                                                    {Constant.statusSymptom.map(item => {
                                                        return <MenuItem key={item.id} value={item.id}>{item.name}</MenuItem>;
                                                    })}
                                                </Select>
                                            </FormControl>
                                        </Grid>
                                        <Grid item sm={3} xs={12}>
                                            <FormControl fullWidth={true}>
                                                <InputLabel htmlFor="pneumoniaStatus-simple">{t('Triệu chứng viêm phổi')}</InputLabel>
                                                <Select
                                                    value={person ? person.pneumoniaStatus ? person.pneumoniaStatus : '' : ''}
                                                    onChange={pneumoniaStatus => this.handleChangePerson(pneumoniaStatus, "pneumoniaStatus")}
                                                    inputProps={{
                                                        name: "pneumoniaStatus",
                                                        id: "pneumoniaStatus-simple",
                                                        readOnly: formReadonly.personalInfo
                                                    }}
                                                >
                                                    {Constant.statusSymptom.map(item => {
                                                        return <MenuItem key={item.id} value={item.id}>{item.name}</MenuItem>;
                                                    })}
                                                </Select>
                                            </FormControl>
                                        </Grid>

                                        {/* <Grid item lg={3} md={6} sm={6} xs={12}>
                                            <TextValidator
                                                className="w-100 "
                                                label={
                                                    <span>
                                                        {t('Địa chỉ chi tiết hiện tại')}
                                                    </span>
                                                }
                                                InputProps={{ readOnly: formReadonly.personalInfo }}
                                                onChange={this.handleChangePerson}
                                                type="text"
                                                name="detailAddress"
                                                value={person ? person.detailAddress : ""}
                                            // validators={['required']}
                                            // errorMessages={[t('general.required')]}
                                            />
                                        </Grid> */}
                                    </Grid>
                                </fieldset>
                            )}

                            {sampleType != null && sampleType == 2 && (
                                <fieldset style={{ width: "inherit" }}>
                                    <legend>Danh sách mẫu</legend>
                                    <Button
                                        className="mb-16 mr-36 align-bottom"
                                        variant="contained"
                                        color="secondary"
                                        onClick={this.openParentPopup}>{t('Chọn mẫu gộp')}</Button>

                                    <MaterialTable
                                        title={""}
                                        data={this.state.chidren ? this.state.chidren : []}
                                        columns={columns}
                                        options={{
                                            selection: false,
                                            actionsColumnIndex: -1,
                                            paging: false,
                                            search: false,
                                            rowStyle: (rowData, index) => ({
                                                backgroundColor: (index % 2 === 1) ? '#EEE' : '#FFF',
                                            }),
                                            headerStyle: {
                                                backgroundColor: '#358600',
                                                color: '#fff',
                                            },
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
                                        }}
                                    />
                                </fieldset>
                            )}

                        </Grid>
                    </DialogContent>
                    <DialogActions>
                        <div className="flex flex-space-between flex-middle mt-10">
                            <Button
                                variant="contained"
                                className="mr-12"
                                color="secondary"
                                onClick={() => this.props.handleClose()}
                            >
                                {t('general.cancel')}
                            </Button>
                            {(!formReadonly.saveBtn || UserRoles.isAdmin()) &&
                                <Button variant="contained" color="primary" style={{ marginRight: '15px' }} type="submit">
                                    {t('general.save')}
                                </Button>
                            }
                        </div>
                    </DialogActions>
                </ValidatorForm>

            </Dialog >
        )
    }
}
export default SampleEditorDialog