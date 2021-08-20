import React, { useEffect } from 'react';
import {
    makeStyles,
    TextField,
    Grid
} from '@material-ui/core';
import DateFnsUtils from "@date-io/date-fns";
import {
    MuiPickersUtilsProvider,
    KeyboardDatePicker,
} from "@material-ui/pickers";
import { useTranslation } from 'react-i18next';
const useStyles = makeStyles(() => ({
    root: {
        // padding: '10px 0'
    }
}));

export default function DobInput(props) {
    const { t } = useTranslation();
    const classes = useStyles();

    const dateNow = new Date();

    const [age, setAge] = React.useState('');

    const [birthDate, setBirthDate] = React.useState(props.formik.values.birthDate);

    const {
        formik
    } = props;

    useEffect(() => {
        formik.values.birthDate = birthDate
        setAge(
            dateNow.getFullYear() - (new Date(birthDate) ? new Date(birthDate) : new Date()).getFullYear())
        // console.log(new Date(props.formik.values.birthDate))
    }, [birthDate]);

    return (
        <>
            <Grid item lg={4} md={4} sm={12} xs={12}>
                <TextField
                    fullWidth
                    disabled
                    classes={{ root: classes.textField }}
                    id="age"
                    size="small"
                    type="number"
                    name="age"
                    label={t('hiv_case.age')}
                    variant="outlined"
                    value={age}
                // onChange={formik.handleChange}
                />
            </Grid>
            <Grid item md={4} sm={12} xs={12}>
                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                    <KeyboardDatePicker
                        id="birthDate"
                        name="birthDate"
                        autoOk
                        variant="inline"
                        inputVariant="outlined"
                        label={t('hiv_case.birth_date')}
                        format={"dd/MM/yyyy"}
                        size="small"
                        InputAdornmentProps={{ position: "end" }}
                        onChange={date => setBirthDate(date)}
                        value={birthDate}
                        error={formik.touched.birthDate && Boolean(formik.errors.birthDate)}
                        helperText={formik.touched.birthDate && formik.errors.birthDate}
                    />
                </MuiPickersUtilsProvider>
            </Grid>
        </>
    )
}