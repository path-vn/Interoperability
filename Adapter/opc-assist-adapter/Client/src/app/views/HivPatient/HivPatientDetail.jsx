import React from 'react';
import {
    Dialog,
    Button,
    Grid,
    FormControlLabel,
    DialogActions,
    IconButton,
    TextField,
    DialogContent,
} from "@material-ui/core";
import { TextValidator } from "react-material-ui-form-validator";
import moment from 'moment';
import TabPatient from './TabPatient';
class HivPatientDetail extends React.Component {
    state = {
        genderString: ''
    }
    handleConvertGender() {
        let genderCode = this.props.item.singleData.gender;
        if (genderCode == 0) {
            this.state.genderString = 'Male';
        }
        else if (genderCode == 1) {
            this.state.genderString = 'Female';
        }
        else if (genderCode == 2) {
            this.state.genderString = 'Other';
        }
        else if (genderCode == 3) {
            this.state.genderString = 'Not Disclosed';
        }
    }
    componentDidMount() {

    }

    componentWillMount() {
        this.handleConvertGender();
    }

    render() {
        let { open, t, item } = this.props;
        return (
            <Dialog open={open} maxWidth="lg" fullWidth>
                <div className="p-24">
                    <h4 className="mb-20">{t('Patient Detail')}</h4>
                </div>
                <DialogContent>
                    <Grid container spacing={1}>

                        <Grid item md={2} sm={4} xs={6}>
                            <TextField
                                label={t('Case Id')}
                                defaultValue={item?.singleData?.caseId ? item.singleData.caseId : "No Data"}
                                InputProps={{
                                    readOnly: true,
                                }}
                                variant="filled"
                            />
                        </Grid>
                        <Grid item md={2} sm={4} xs={6}>
                            <TextField
                                label={t('Person Id')}
                                defaultValue={item?.singleData?.personId ? item.singleData.personId : "No Data"}
                                InputProps={{
                                    readOnly: true,
                                }}
                                variant="filled"
                            />
                        </Grid>
                        <Grid item md={2} sm={4} xs={6}>
                            <TextField
                                label={t('Last Sync Date')}
                                defaultValue={item?.singleData?.lastSynDate ? moment(this.props.item.singleData.lastSynDate).format('DD/MM/YYYY') : "No Data"}
                                InputProps={{
                                    readOnly: true,
                                }}
                                variant="filled"
                            />
                        </Grid>
                        <Grid item md={2} sm={4} xs={6}>
                            <TextField
                                label={t('Full name')}
                                defaultValue={item?.singleData?.fullname ? item.singleData.fullname : "No Data"}
                                InputProps={{
                                    readOnly: true,
                                }}
                                variant="filled"
                            />
                        </Grid>
                        <Grid item md={2} sm={4} xs={6}>
                            <TextField
                                label={t('Ethnic')}
                                defaultValue={item?.singleData?.ethnic ? item.singleData.ethnic : "No Data"}
                                InputProps={{
                                    readOnly: true,
                                }}
                                variant="filled"
                            />
                        </Grid>
                        <Grid item md={2} sm={4} xs={6}>
                            <TextField
                                label={t('Gender')}
                                defaultValue={this.state.genderString ? this.state.genderString : 'No Data'}
                                InputProps={{
                                    readOnly: true,
                                }}
                                variant="filled"
                            />
                        </Grid>
                        <Grid item md={2} sm={4} xs={6}>
                            <TextField
                                label={t('Year of birth')}
                                defaultValue={item?.singleData?.dob ? item.singleData.dob : "No Data"}
                                InputProps={{
                                    readOnly: true,
                                }}
                                variant="filled"
                            />
                        </Grid>
                        <Grid item md={2} sm={4} xs={6}>
                            <TextField
                                label={t('Passport Number')}
                                defaultValue={item?.singleData?.passportNumber ? item.singleData.passportNumber : "No Data"}
                                InputProps={{
                                    readOnly: true,
                                }}
                                variant="filled"
                            />
                        </Grid>
                        <Grid item md={2} sm={4} xs={6}>
                            <TextField
                                label={t('National Id Number')}
                                defaultValue={item?.singleData?.nationalIdNumber ? item.singleData.nationalIdNumber : "No Data"}
                                InputProps={{
                                    readOnly: true,
                                }}
                                variant="filled"
                            />
                        </Grid>
                        <Grid item md={2} sm={4} xs={6}>
                            <TextField
                                label={t('Occupation')}
                                defaultValue={item?.singleData?.occupation ? item.singleData.occupation : "No Data"}
                                InputProps={{
                                    readOnly: true,
                                }}
                                variant="filled"
                            />
                        </Grid>
                    </Grid>
                    <fieldset className="mt-16" style={{ borderRadius: "10px" }}>
                        <legend>{<span className="styleColor">{t("Permanent Address")}</span>}</legend>
                        <Grid container spacing={1}>
                            <Grid item md={3} sm={3} xs={3}>
                                <TextField
                                    label={t('Permanent Stress Address')}
                                    defaultValue={item?.singleData?.permanentStressAddress ? item.singleData.permanentStressAddress : "No Data"}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    variant="filled"
                                />
                            </Grid>
                            <Grid item md={3} sm={3} xs={3}>
                                <TextField
                                    label={t('Permanent Commune')}
                                    defaultValue={item?.singleData?.permanentCommune ? item.singleData.permanentCommune : "No Data"}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    variant="filled"
                                />
                            </Grid>
                            <Grid item md={2} sm={3} xs={3}>
                                <TextField
                                    label={t('Permanent District')}
                                    defaultValue={item?.singleData?.permanentDistrict ? item.singleData.permanentDistrict : "No Data"}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    variant="filled"
                                />
                            </Grid>
                            <Grid item md={2} sm={3} xs={3}>
                                <TextField
                                    label={t('Permanent Province')}
                                    defaultValue={item?.singleData?.permanentProvince ? item.singleData.permanentProvince : "No Data"}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    variant="filled"
                                />
                            </Grid>
                            <Grid item md={2} sm={3} xs={3}>
                                <TextField
                                    label={t('Permanent Country')}
                                    defaultValue={item?.singleData?.permanentCountry ? item.singleData.permanentCountry : "No Data"}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    variant="filled"
                                />
                            </Grid>
                        </Grid>
                    </fieldset>

                    <fieldset className="mt-16" style={{ borderRadius: "10px" }}>
                        <legend>{<span className="styleColor">{t("Current Address")}</span>}</legend>
                        <Grid container spacing={1}>
                            <Grid item md={3} sm={3} xs={3}>
                                <TextField
                                    label={t('Current Stress Address')}
                                    defaultValue={item?.singleData?.currentStressAddress ? item.singleData.currentStressAddress : "No Data"}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    variant="filled"
                                />
                            </Grid>
                            <Grid item md={3} sm={3} xs={3}>
                                <TextField
                                    label={t('Current Commune')}
                                    defaultValue={item?.singleData?.currentCommune ? item.singleData.currentCommune : "No Data"}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    variant="filled"
                                />
                            </Grid>
                            <Grid item md={2} sm={3} xs={3}>
                                <TextField
                                    label={t('Current District')}
                                    defaultValue={item?.singleData?.currentDistrict ? item.singleData.currentDistrict : "No Data"}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    variant="filled"
                                />
                            </Grid>
                            <Grid item md={2} sm={3} xs={3}>
                                <TextField
                                    label={t('Current Province')}
                                    defaultValue={item?.singleData?.currentProvince ? item.singleData.currentProvince : "No Data"}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    variant="filled"
                                />
                            </Grid>
                            <Grid item md={2} sm={3} xs={3}>
                                <TextField
                                    label={t('Current Country')}
                                    defaultValue={item?.singleData?.currentCountry ? item.singleData.currentCountry : "No Data"}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    variant="filled"
                                />
                            </Grid>
                        </Grid>
                    </fieldset>
                    <fieldset className="mt-16" style={{ borderRadius: "10px" }}>
                        <legend>{<span className="styleColor">{t("Hiv Confimation")}</span>}</legend>
                        <Grid container spacing={3}>
                            <Grid item sm={6} xs={12}>
                                <TextField
                                    label={t('HIV Confirmation Date')}
                                    defaultValue={item?.singleData?.hivConfirmationDate ? item.singleData.hivConfirmationDate : "No Data"}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    variant="filled"
                                    fullWidth
                                />
                            </Grid>
                            <Grid item sm={6} xs={12}>
                                <TextField
                                    label={t('Confirm Lab')}
                                    defaultValue={item?.singleData?.confirmLab ? item.singleData.confirmLab : "No Data"}
                                    InputProps={{
                                        readOnly: true,
                                    }}
                                    variant="filled"
                                    fullWidth
                                />
                            </Grid>
                        </Grid>

                    </fieldset>
                    <Grid item container sm={12} xs={12} md={12}>
                        <TabPatient t={t} item={item.singleData} />
                    </Grid>
                </DialogContent>
                <DialogActions>
                    <div className="flex flex-space-between flex-middle">
                        <Button
                            variant="contained"
                            className="mr-12"
                            color="secondary"
                            onClick={() => this.props.handleClose()}
                        >
                            {t('Close')}
                        </Button>

                    </div>
                </DialogActions>
            </Dialog>
        )
    }
}
export default HivPatientDetail;