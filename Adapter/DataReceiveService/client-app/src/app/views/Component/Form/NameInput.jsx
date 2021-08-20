import React, { useEffect } from 'react'
import {
    makeStyles,
    TextField,
    Grid
} from '@material-ui/core';
import { useTranslation } from 'react-i18next';
const useStyles = makeStyles(() => ({
    root: {
        // padding: '10px 0'
    }
}));

export default function NameInput(props) {
    const classes = useStyles();
    const { t } = useTranslation();
    const {
        formik,
        disabled
    } = props;

    const [lastName, setLastName] = React.useState(formik.values.lastName ? formik.values.lastName : '');

    const [firstName, setFirstName] = React.useState(formik.values.firstName ? formik.values.firstName : '');

    const [displayName, setDisplayName] = React.useState('');

    const handleChangeNameData = (event) => {

        if (event.target.name == "firstName") {
            setLastName(event.target.value);
        }
        if (event.target.name == "lastName") {
            setFirstName(event.target.value)
        }

    };

    useEffect(() => {
        setDisplayName(firstName + " " + lastName)
        formik.values.lastName = lastName
    }, [lastName]);

    useEffect(() => {
        setDisplayName(firstName + " " + lastName)
        formik.values.firstName = firstName
    }, [firstName]);
    useEffect(() => {
        formik.values.displayName = displayName
    }, [displayName]);

    useEffect(() => {
        setLastName(formik.values.lastName)
        setFirstName(formik.values.firstName)
    });
    // useEffect(() => {
    //     alert(formik.values.firstName)
    // },[]);
    return (
        <>
            
            <Grid item md={4} sm={12} xs={12}>
                <TextField
                    disabled={disabled ? disabled : false}
                    fullWidth
                    classes={{ root: classes.textField }}
                    id="lastName"
                    size="small"
                    name="lastName"
                    label={t('hiv_case.last_name')}
                    variant="outlined"
                    value={firstName}
                    onChange={handleChangeNameData}
                />

            </Grid>
            <Grid item md={4} sm={12} xs={12}>
                <TextField
                    disabled={disabled ? disabled : false}
                    fullWidth
                    classes={{ root: classes.textField }}
                    id="firstName"
                    size="small"
                    name="firstName"
                    label={t('hiv_case.first_name')}
                    variant="outlined"
                    value={lastName}
                    onChange={handleChangeNameData}
                />
            </Grid>
            <Grid item md={4} sm={12} xs={12}>
                <TextField
                    fullWidth
                    disabled
                    classes={{ root: classes.textField }}
                    id="displayName"
                    size="small"
                    name="displayName"
                    label={t('hiv_case.display_name')}
                    variant="outlined"
                    value={displayName}
                // onChange={formik.handleChange}
                />
            </Grid>
        </>
    );
}