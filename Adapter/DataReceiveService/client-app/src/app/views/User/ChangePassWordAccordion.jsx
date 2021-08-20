import React, { useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import {
    Grid,
    Accordion,
    AccordionDetails,
    AccordionSummary,
    Typography,
    TextField
} from "@material-ui/core";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';

const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
    },
    container: {
        background: 'rgb(237, 245, 251)',
        boxShadow: '0 0.5rem 1rem rgb(0 0 0, 15%)'
    },
    details: {
        background: '#fff'
    },
    heading: {
        fontSize: theme.typography.pxToRem(15),
        flexBasis: '33.33%',
        flexShrink: 0,
    },
    secondaryHeading: {
        fontSize: theme.typography.pxToRem(15),
        color: theme.palette.text.secondary,
    },
}));

export default function ControlledAccordions(props) {
    const classes = useStyles();
    const [expanded, setExpanded] = React.useState(false);

    const handleChange = (panel) => (event, isExpanded) => {
        setExpanded(isExpanded ? panel : false);
    };

    const [password, setPassWord] = React.useState('');

    const [confirmPassword, setConfirmPassWord] = React.useState('');

    // useEffect(() => {
    //     ValidatorForm.addValidationRule("isPasswordMatch", (value) => {
    //         if (value !== password) {
    //             return false;
    //         }
    //         props.handleChangePassWord(password);
    //         return true;
            
    //     });
    // });
    // useEffect(() => {
        
    //     console.log(password)
    // }, [password]);

    let {
        t,
        formik,
    } = props;
    return (
        <Grid item md={12}>
            <div className={classes.root}>
                <Accordion className={classes.container} expanded={expanded === 'changePasswordPanel'} onChange={handleChange('changePasswordPanel')}>
                    <AccordionSummary
                        expandIcon={<ExpandMoreIcon />}
                        aria-controls="changePasswordPanelbh-content"
                        id="changePasswordPanelbh-header"
                    >
                        <Typography className={classes.heading}>{t('user.change_pass')}</Typography>
                    </AccordionSummary>
                    {expanded && <AccordionDetails className={classes.details}>
                        <Grid container spacing={2}>
                            <Grid item md={6} sm={6} xs={12}>
                                {/* <TextValidator
                                    fullWidth
                                    label={
                                        <span className="font">
                                            <span style={{ color: "red" }}>*</span>
                                            {t("user.password")}
                                        </span>
                                    }
                                    variant="outlined"
                                    size="small"
                                    onChange={(event) => {
                                        setPassWord(event.target.value)
                                    }}
                                    name="password"
                                    type="password"
                                    value={password}
                                    validators={["required", "matchRegexp:([A-Za-z0-9]{6,})"]}
                                    errorMessages={[
                                        t("general.errorMessages_required"),
                                        t("user.password_message"),
                                    ]}
                                /> */}
                                <TextField
                                    fullWidth
                                    required
                                    size="small"
                                    name="password"
                                    type="password"
                                    label={t('user.password')}
                                    variant="outlined"
                                    value={formik.values.password}
                                    onChange={formik.handleChange}
                                    error={formik.touched.password && Boolean(formik.errors.password)}
                                    helperText={formik.touched.password && formik.errors.password}
                                />
                            </Grid>
                            <Grid item md={6} sm={6} xs={12}>
                                {/* <TextValidator
                                    fullWidth
                                    label={
                                        <span className="font">
                                            <span style={{ color: "red" }}>*</span>
                                            {t("user.re_password")}
                                        </span>
                                    }
                                    variant="outlined"
                                    size="small"
                                    onChange={(event) => {
                                        setConfirmPassWord(event.target.value)
                                    }}
                                    name="confirmPassword"
                                    type="password"
                                    value={confirmPassword}
                                    validators={["required", "isPasswordMatch"]}
                                    errorMessages={[
                                        t("general.errorMessages_required"),
                                        t("user.password_match_message"),
                                    ]}
                                /> */}
                                <TextField
                                    fullWidth
                                    required
                                    size="small"
                                    name="confirmPassword"
                                    type="confirmPassword"
                                    label={t('user.re_password')}
                                    variant="outlined"
                                    value={formik.values.confirmPassword}
                                    onChange={formik.handleChange}
                                    error={formik.touched.confirmPassword && Boolean(formik.errors.confirmPassword)}
                                    helperText={formik.touched.confirmPassword && formik.errors.confirmPassword}
                                />
                            </Grid>
                        </Grid>
                    </AccordionDetails>}
                </Accordion>
            </div>
        </Grid>

    );
}