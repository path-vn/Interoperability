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
import AddressSelect from './AddressSelect';

const useStyles = makeStyles(() => ({
    root: {
        // padding: '10px 0'
    }
}));

export default function SyncAddress(props) {
    const {
        t,
        formik,
        listRootUnit,
    } = props;

    const [syncAddress, setSyncAddress] = React.useState(formik.values.syncAddress);
    const [check, setCheck] = React.useState(false);
    const [buttonText, setButtonText] = React.useState(t('hiv_case.syn'));

    const handleSyncAddress = () => {
        setSyncAddress(!syncAddress)
    }
    const handleSync = () => {
        setSyncAddress(!syncAddress);
        if (!syncAddress) {
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
    }

    useEffect(() => {
        if (syncAddress) {
            formik.values.currentCity = formik.values.registeredCity ? formik.values.registeredCity : null
            formik.values.currentDistrict = formik.values.registeredDistrict ? formik.values.registeredDistrict : null
            formik.values.currentCommune = formik.values.registeredCommune ? formik.values.registeredCommune : null
            setButtonText(t('hiv_case.synOff'))
        }
        else {
            setButtonText(t('hiv_case.syn'))
        }
    }, [formik.values.registeredCity, formik.values.registeredDistrict, formik.values.registeredCommune, syncAddress]);

    return (
        <>
            <Grid item md={6} sm={12} xs={12}>
                <fieldset>
                    <legend>{t('hiv_case.permanent_residence')}</legend>
                    <Grid className='mt-40' container spacing={2}>
                        <AddressSelect
                            formik={formik}
                            listRootUnit={listRootUnit}
                            type="registered"
                        />
                    </Grid>
                </fieldset>
            </Grid>
            <Grid item md={6} sm={12} xs={12}>
                <fieldset>
                    <legend>{t('hiv_case.current_residence')}</legend>
                    <Grid item sm={12} xs={12}>
                        <FormControlLabel
                            style={{ marginTop: '6px' }}
                            value={syncAddress}
                            onChange={() => handleSync()}
                            control={<Checkbox checked={syncAddress} />}
                            label={buttonText}
                        />
                    </Grid>
                    <Grid container spacing={2}>
                        <AddressSelect
                            formik={formik}
                            listRootUnit={listRootUnit}
                            type="current"
                            disabled={syncAddress}
                        />
                    </Grid>
                </fieldset>
            </Grid>
        </>
    )
}