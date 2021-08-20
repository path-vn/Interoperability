import React, { useEffect, useRef } from 'react';
import {
    Grid,
    makeStyles,
    TextField,
    DialogActions,
    Button,
    DialogContent
} from '@material-ui/core';
import { useTranslation } from 'react-i18next';
import NiceAutocomplete from './NiceAutocomplete';
import {
    getByRoot as getByRootUnit, getAllChildByParentId as getAllChildUnitByParentId
} from "../../AdministrativeUnit/AdministrativeUnitService";
import NiceTextField from './NiceTextField';

const useStyles = makeStyles(() => ({
    root: {
        // padding: '10px 0'
    }
}));


export default function AddressSelect(props) {
    const { t } = useTranslation();

    const classes = useStyles();

    const [cityCode, setCityCode] = React.useState('');

    const [districtCode, setDistrictCode] = React.useState('');

    const [communeCode, setCommuneCode] = React.useState('');

    const [listDistrict, setListDistrict] = React.useState(null);

    const [listCommune, setListCommune] = React.useState(null);

    const {
        formik,
        type,
        disabled
    } = props;

    useEffect(() => {
        if (props.formik.values[type + "City"] != null && props.formik.values[type + "City"].id != null) {
            setCityCode(props.formik.values[type + "City"] ? props.formik.values[type + "City"].code : null);
            getAllChildUnitByParentId(props.formik.values[type + "City"].id).then(({ data }) => {
                setListDistrict(data);
            })
        }
        else {
            setCityCode('');
            props.formik.values[type + "District"] = null;
            props.formik.values[type + "Commune"] = null;
        }

    }, [props.formik.values[type + "City"]]);

    useEffect(() => {
        if (props.formik.values[type + "District"] != null && props.formik.values[type + "District"].id != null) {
            setDistrictCode(props.formik.values[type + "District"] ? props.formik.values[type + "District"].code : null)
            getAllChildUnitByParentId(props.formik.values[type + "District"].id).then(({ data }) => {
                setListCommune(data);
            })
        }
        else {
            setDistrictCode('');
            props.formik.values[type + "Commune"] = null;
        }

    }, [props.formik.values[type + "District"]]);

    useEffect(() => {
        if (props.formik.values[type + "Commune"] != null && props.formik.values[type + "Commune"].id != null) {
            setCommuneCode(props.formik.values[type + "Commune"] ? props.formik.values[type + "Commune"].code : null)
        }
        else {
            setCommuneCode('');
        }

    }, [props.formik.values[type + "Commune"]]);

    return (
        <>
            <Grid item md={4} xs={4} sm={4}>
                <TextField
                    disabled
                    classes={{ root: classes.textField }}
                    id={type + "CityCode"}
                    size="small"
                    name={type + "CityCode"}
                    label={t('hiv_case.city_code')}
                    variant="outlined"
                    value={cityCode}
                />
            </Grid>
            <Grid item md={8} xs={8} sm={8}>
                <NiceAutocomplete
                    formik={formik}
                    field={type + "City"}
                    label={t('hiv_case.city')}
                    options={props.listRootUnit ? props.listRootUnit : []}
                    variant="outlined"
                    size="small"
                    disabled={disabled}
                />
            </Grid>
            <Grid item md={4} xs={4} sm={4}>
                <TextField
                    disabled
                    classes={{ root: classes.textField }}
                    id={type + "DistrictCode"}
                    size="small"
                    name={type + "DistrictCode"}
                    label={t('hiv_case.district_code')}
                    variant="outlined"
                    value={districtCode}
                />

            </Grid>
            <Grid item md={8} xs={8} sm={8}>
                <NiceAutocomplete
                    formik={formik}
                    field={type + "District"}
                    label={t('hiv_case.district')}
                    options={listDistrict ? listDistrict : []}
                    variant="outlined"
                    size="small"
                    disabled={disabled}
                />
            </Grid>

            <Grid item md={4} xs={4} sm={4}>
                <TextField
                    disabled
                    classes={{ root: classes.textField }}
                    id={type + "CommuneCode"}
                    size="small"
                    name={type + "CommuneCode"}
                    label={t('hiv_case.commune_code')}
                    variant="outlined"
                    value={communeCode}
                />
            </Grid>
            <Grid item md={8} xs={8} sm={8}>
                <NiceAutocomplete
                    formik={formik}
                    field={type + "Commune"}
                    label={t('hiv_case.commune')}
                    options={listCommune ? listCommune : []}
                    variant="outlined"
                    size="small"
                    disabled={disabled}
                />
            </Grid>
            <Grid item sm={12} xs={12}>
                <NiceTextField
                    formik={formik}
                    classes={{ root: classes.textField }}
                    field={type + "AddressDetail"}
                    size="small"
                    label={t("hiv_case.address_detail")}
                    variant="outlined"
                />
                {/* <TextField
                    classes={{ root: classes.textField }}
                    id={type + "AddressDetail"}
                    size="small"
                    name={type + "AddressDetail"}
                    label={t('hiv_case.commune_code')}
                    variant="outlined"
                    value={communeCode}
                /> */}
            </Grid>
        </>
    )
}