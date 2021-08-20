import React from 'react';
import {
    Dialog,
    DialogTitle,
    Icon,
    IconButton,
    DialogActions,
    Button,
    DialogContent
} from "@material-ui/core";
import moment from "moment";
import BlockIcon from '@material-ui/icons/Block';
import { toast } from 'react-toastify';
import Draggable from 'react-draggable';
import Paper from '@material-ui/core/Paper';
import 'react-toastify/dist/ReactToastify.css';
import MaterialTable from "material-table";

toast.configure({
    autoClose: 2000,
    draggable: false,
    limit: 3
    //etc you get the idea
});

class ChildDialog extends React.Component {

    state = {}

    componentDidMount() {
        let item = this.props.item;
        if (item) {
            this.setState({ ...item });
        }
        console.log(this.props);
    }

    handleFormSubmit = (values) => {
        let { t, handleClose, formik } = this.props
        let { id, tableData } = this.state;
        var obj = {};
        obj.id = id;
        obj.code = values.code;
        obj.name = values.name;
        obj.arvEnrollmentDate = values.arvEnrollmentDate;
        obj.arvEnrollmentType = values.arvEnrollmentType;
        obj.arvEnrollmentPlace = values.arvEnrollmentPlace;
        obj.arvStopDate = values.arvStopDate;
        obj.arvStopReason = values.arvStopReason;
        obj.labTestResults = values.labTestResults;
        obj.treatmentPeriodRegimens = values.treatmentPeriodRegimens;

        if (tableData) {
            //update
            let rowIndex = tableData.id;
            formik.values.treatmentPeriods = formik.values.treatmentPeriods.map(e => (e.tableData.id === rowIndex ? { ...e, ...obj } : e));
        } else {
            //create
            if (formik.values.treatmentPeriods == null) {
                formik.values.treatmentPeriods = [obj];
            } else {
                formik.values.treatmentPeriods.push(obj);
            }
        }
        handleClose();
        toast.success(t('toast.update_success'));

        // checkCode(id, values.code).then((result) => {
        //   if (result.data) {
        //     toast.warning(t('toast.duplicate_code'));
        //   } else {
        //     if (id) {
        //       update(obj).then(() => {
        //         handleClose();
        //         toast.success(t('toast.update_success'));
        //       });
        //     } else {
        //       addNew(obj).then(() => {
        //         handleClose();
        //         toast.success(t('toast.add_success'));
        //       });
        //     }
        //   }
        // });
    };



    render() {
        let { open, handleClose, t, item,childList } = this.props;
        let {
            id,
        } = this.state;

        let columns = [
            
            {
                title: t("patient.tab.no"),
                field: "Stt",
                render: (rowData) =>
                rowData ? (<span>{rowData.tableData.id + 1}</span>) : ("")
            },
            {
                title: t("prenancy.child.birth_weight"),
                field: "birthWeight",
            },
            {
                title: t("prenancy.child.birth_defects"),
                field: "birthDefects",
            },
            {
                title: t("prenancy.child.hiv_status"),
                field: "hivStatus.display",
            },
            {
                title: t("prenancy.child.hiv_diagnosis_date"),
                field: "hivDiagnosisDate",
                render: (rowData) => rowData.hivDiagnosisDate ? (<span>{moment(rowData.hivDiagnosisDate).format("DD/MM/YYYY")}</span>) : ("")
            },
            {
                title: t("prenancy.child.child_arv_start_date"),
                field: "childArvStartDate",
                render: (rowData) => rowData.childArvStartDate ? (<span>{moment(rowData.childArvStartDate).format("DD/MM/YYYY")}</span>) : ("")
            },
        ];

        return (
            <Dialog
                className="dialog-container"
                open={open}
                fullWidth
                maxWidth="md"
            >
                <DialogTitle className="dialog-header bgc-primary-d1">
                    <span className="mb-20 text-white" > {t('Thông tin đứa trẻ')} </span>
                    <IconButton style={{ position: "absolute", right: "10px", top: "10px" }} onClick={() => handleClose()}>
                        <Icon color="disabled" title={t('general.button.close')}>close</Icon>
                    </IconButton>
                </DialogTitle>
                <div>
                    <div className="dialog-body">
                        <DialogContent className="o-hidden">
                            <MaterialTable
                                data={childList ? childList : []}
                                columns={columns}
                                options={{
                                    selection: false,
                                    actionsColumnIndex: -1,
                                    paging: false,
                                    search: false,
                                    toolbar: false,
                                    maxBodyHeight: "200px",
                                    headerStyle: {
                                        backgroundColor: "#2a80c8",
                                        color: "#fff",
                                    },
                                    rowStyle: (rowData, index) => ({
                                        backgroundColor: index % 2 === 1 ? 'rgb(237, 245, 251)' : '#FFF',
                                    }),

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
                        </DialogContent>
                    </div>
                    <div className="dialog-footer">
                        <DialogActions className="p-0">
                            <div className="flex flex-space-between flex-middle">
                                <Button
                                    startIcon={<BlockIcon />}
                                    variant="contained"
                                    className="mr-12 btn btn-secondary d-inline-flex"
                                    color="secondary"
                                    onClick={() => handleClose()}
                                >
                                    {t("general.button.cancel")}
                                </Button>
                            </div>
                        </DialogActions>
                    </div>
                </div>

            </Dialog>
        );
    }
}

export default ChildDialog;