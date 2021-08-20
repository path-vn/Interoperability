import React, { useEffect } from 'react';
import {
    Grid,
    makeStyles,
    TextField,
    FormControlLabel,
    Checkbox
} from '@material-ui/core';
import NiceAutocomplete from './NiceAutocomplete';
import NiceTextField from './NiceTextField';
import {
    getAllChildByParentId as getAllChildUnitByParentId
} from "../../AdministrativeUnit/AdministrativeUnitService";

const useStyles = makeStyles(() => ({
    root: {
        // padding: '10px 0'
    }
}));


export default function Nice2AddressSelect(props) {

    const {
        t,
        formik,
        listRootUnit,
    } = props;

    const classes = useStyles();

    const [listDistrict, setListDistrict] = React.useState(null);

    const [listCommune, setListCommune] = React.useState(null);

    const [sync, setSync] = React.useState(formik.values.syncAddress);

    const [buttonText, setButtonText] = React.useState(t('hiv_case.syn'));

    const handleSync = () => {
        formik.values.syncAddress = !formik.values.syncAddress;
        setSync(formik.values.syncAddress);
    }

    // useEffect(() => {
    //     console.log(formik.values.syncAddress);
    //     console.log(sync);
    //     formik.setFieldValue(field, formik.values[field]);
    // });

    useEffect(() => {
        setSync(formik.values.syncAddress);
        if (formik.values.syncAddress) {
            formik.values.currentCity = formik.values.registeredCity ? formik.values.registeredCity : null
            formik.values.currentDistrict = formik.values.registeredDistrict ? formik.values.registeredDistrict : null
            formik.values.currentCommune = formik.values.registeredCommune ? formik.values.registeredCommune : null
            setButtonText(t('hiv_case.synOff'))
        } else {
            formik.values.currentCity = null
            formik.values.currentDistrict = null
            formik.values.currentCommune = null
            setButtonText(t('hiv_case.syn'))
        }
    }, [formik.values.syncAddress])

    useEffect(() => {
        if (formik.values.registeredCity != null && formik.values.registeredCity.id != null) {
            if (formik.values.registeredDistrict && formik.values.registeredDistrict.parent
                && formik.values.registeredDistrict.parent.code !== formik.values.registeredCity.code) {
                formik.values.registeredDistrict = null;
                formik.values.registeredCommune = null;
            }
            if ((formik.values.currentCity != null) && (formik.values.registeredCity.code !== formik.values.currentCity.code)) {
                handleSync();
                formik.values.registeredDistrict = null;
                formik.values.registeredCommune = null;
            }
            getAllChildUnitByParentId(formik.values.registeredCity.id).then(({ data }) => {
                setListDistrict(data);
            })
        }
        else {
            if (formik.values.currentCity) {
                handleSync();
                // formik.values.registeredDistrict = null;
                // formik.values.registeredCommune = null;
            }
            formik.values.registeredDistrict = null;
            formik.values.registeredCommune = null;
        }
    }, [formik.values.registeredCity]);

    useEffect(() => {
        if (formik.values.registeredDistrict != null && formik.values.registeredDistrict.id != null) {
            if (formik.values.registeredCommune && formik.values.registeredCommune.parent
                && formik.values.registeredCommune.parent.code !== formik.values.registeredDistrict.code) {
                formik.values.registeredCommune = null;
            }
            if ((formik.values.currentDistrict != null) && (formik.values.registeredDistrict.code !== formik.values.currentDistrict.code)) {
                handleSync();
                formik.values.registeredCommune = null;
            }
            getAllChildUnitByParentId(formik.values.registeredDistrict.id).then(({ data }) => {
                setListCommune(data);
            })
        }
        else {
            if (formik.values.currentDistrict) {
                handleSync();
                // formik.values.registeredCommune = null;
            }
            formik.values.registeredCommune = null;
        }
    }, [formik.values.registeredDistrict]);

    useEffect(() => {
        if (formik.values.registeredCommune != null && formik.values.registeredCommune.id != null) {
            if ((formik.values.currentCommune != null) && (formik.values.registeredCommune.code !== formik.values.currentCommune.code)) {
                handleSync();
            }
        }
        else {
            if (formik.values.currentCommune) {
                handleSync();
            }
        }
    }, [formik.values.registeredCommune]);

    useEffect(() => {
        if (formik.values.currentCity != null && formik.values.currentCity.id != null) {
            if (formik.values.currentDistrict && formik.values.currentDistrict.parent
                && formik.values.currentDistrict.parent.code !== formik.values.currentCity.code) {
                formik.values.currentDistrict = null;
                formik.values.currentCommune = null;
            }
            getAllChildUnitByParentId(formik.values.currentCity.id).then(({ data }) => {
                setListDistrict(data);
            })
        }
        else {
            formik.values.currentDistrict = null;
            formik.values.currentCommune = null;
        }

    }, [formik.values.currentCity]);

    useEffect(() => {
        if (formik.values.currentDistrict != null && formik.values.currentDistrict.id != null) {
            if (formik.values.currentCommune && formik.values.currentCommune.parent
                && formik.values.currentCommune.parent.code !== formik.values.currentDistrict.code) {
                formik.values.currentCommune = null;
            }
            getAllChildUnitByParentId(formik.values.currentDistrict.id).then(({ data }) => {
                setListCommune(data);
            })
        }
        else {
            formik.values.currentCommune = null;
        }
    }, [formik.values.currentDistrict]);

    // useEffect(() => {
    //     if (formik.values.currentCommune != null && formik.values.currentCommune.id != null) {

    //     }
    //     else {

    //     }

    // }, [formik.values.currentCommune]);

    return (
        <>
            <Grid item md={6} sm={12} xs={12}>
                <fieldset>
                    <legend>{t('hiv_case.permanent_residence')}</legend>
                    <Grid className='mt-40' container spacing={2}>
                        <Grid item md={4} xs={4} sm={4}>
                            <TextField
                                disabled
                                classes={{ root: classes.textField }}
                                id="registeredCityCode"
                                size="small"
                                name="registeredCityCode"
                                label={t('hiv_case.city_code')}
                                variant="outlined"
                                value={formik.values.registeredCity ? formik.values.registeredCity.code : ''}
                            />
                        </Grid>
                        <Grid item md={8} xs={8} sm={8}>
                            <NiceAutocomplete
                                formik={formik}
                                field="registeredCity"
                                label={t('hiv_case.city')}
                                options={listRootUnit ? listRootUnit : []}
                                variant="outlined"
                                size="small"
                            />
                        </Grid>
                        <Grid item md={4} xs={4} sm={4}>
                            <TextField
                                disabled
                                classes={{ root: classes.textField }}
                                id="registeredDistrictCode"
                                size="small"
                                name="registeredDistrictCode"
                                label={t('hiv_case.district_code')}
                                variant="outlined"
                                value={formik.values.registeredDistrict ? formik.values.registeredDistrict.code : ''}
                            />

                        </Grid>
                        <Grid item md={8} xs={8} sm={8}>
                            <NiceAutocomplete
                                formik={formik}
                                field="registeredDistrict"
                                label={t('hiv_case.district')}
                                options={listDistrict ? listDistrict : []}
                                variant="outlined"
                                size="small"
                            />
                        </Grid>

                        <Grid item md={4} xs={4} sm={4}>
                            <TextField
                                disabled
                                classes={{ root: classes.textField }}
                                id="registeredCommuneCode"
                                size="small"
                                name="registeredCommuneCode"
                                label={t('hiv_case.commune_code')}
                                variant="outlined"
                                value={formik.values.registeredCommune ? formik.values.registeredCommune.code : ''}
                            />
                        </Grid>
                        <Grid item md={8} xs={8} sm={8}>
                            <NiceAutocomplete
                                formik={formik}
                                field="registeredCommune"
                                label={t('hiv_case.commune')}
                                options={listCommune ? listCommune : []}
                                variant="outlined"
                                size="small"
                            />
                        </Grid>
                        <Grid item sm={12} xs={12}>
                            <NiceTextField
                                formik={formik}
                                classes={{ root: classes.textField }}
                                field="registeredAddressDetail"
                                size="small"
                                label={t("health_org.address_detail")}
                                variant="outlined"
                            />
                        </Grid>
                    </Grid>
                </fieldset>
            </Grid>
            <Grid item md={6} sm={12} xs={12}>
                <fieldset>
                    <legend>{t('hiv_case.current_residence')}</legend>
                    <Grid item sm={12} xs={12}>
                        <FormControlLabel
                            style={{ marginTop: '6px' }}
                            control={
                                <Checkbox
                                    //nếu chưa chọn hết địa chỉ hộ khẩu thì disabled
                                    disabled={!(formik.values.registeredCity
                                        && formik.values.registeredDistrict
                                        && formik.values.registeredCommune)}
                                    onChange={handleSync}
                                    color="primary"
                                    checked={sync}
                                    name="checkedSync" />
                            }
                            label={buttonText}
                        />
                    </Grid>
                    <Grid container spacing={2}>
                        <Grid item md={4} xs={4} sm={4}>
                            <TextField
                                disabled
                                classes={{ root: classes.textField }}
                                id="currentCityCode"
                                size="small"
                                name="currentCityCode"
                                label={t('hiv_case.city_code')}
                                variant="outlined"
                                value={formik.values.currentCity ? formik.values.currentCity.code : ''}
                            />
                        </Grid>
                        <Grid item md={8} xs={8} sm={8}>
                            <NiceAutocomplete
                                formik={formik}
                                field="currentCity"
                                label={t('hiv_case.city')}
                                options={listRootUnit ? listRootUnit : []}
                                variant="outlined"
                                size="small"
                                disabled={formik.values.syncAddress}
                            />
                        </Grid>
                        <Grid item md={4} xs={4} sm={4}>
                            <TextField
                                disabled
                                classes={{ root: classes.textField }}
                                id="currentDistrictCode"
                                size="small"
                                name="currentDistrictCode"
                                label={t('hiv_case.district_code')}
                                variant="outlined"
                                value={formik.values.currentDistrict ? formik.values.currentDistrict.code : ''}
                            />

                        </Grid>
                        <Grid item md={8} xs={8} sm={8}>
                            <NiceAutocomplete
                                formik={formik}
                                field="currentDistrict"
                                label={t('hiv_case.district')}
                                options={listDistrict ? listDistrict : []}
                                variant="outlined"
                                size="small"
                                disabled={formik.values.syncAddress}
                            />
                        </Grid>

                        <Grid item md={4} xs={4} sm={4}>
                            <TextField
                                disabled
                                classes={{ root: classes.textField }}
                                id="currentCommuneCode"
                                size="small"
                                name="currentCommuneCode"
                                label={t('hiv_case.commune_code')}
                                variant="outlined"
                                value={formik.values.currentCommune ? formik.values.currentCommune.code : ''}
                            />
                        </Grid>
                        <Grid item md={8} xs={8} sm={8}>
                            <NiceAutocomplete
                                formik={formik}
                                field="currentCommune"
                                label={t('hiv_case.commune')}
                                options={listCommune ? listCommune : []}
                                variant="outlined"
                                size="small"
                                disabled={formik.values.syncAddress}
                            />
                        </Grid>
                        <Grid item sm={12} xs={12}>
                            <NiceTextField
                                formik={formik}
                                classes={{ root: classes.textField }}
                                field="currentAddressDetail"
                                size="small"
                                label={t("health_org.address_detail")}
                                variant="outlined"
                            />
                        </Grid>
                    </Grid>
                </fieldset>
            </Grid>
        </>
    )
}