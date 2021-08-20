import React from 'react';
import {
    Grid,
    makeStyles,
    TextField,
    DialogActions,
    Button,
    DialogContent
} from '@material-ui/core';
import { useTranslation } from 'react-i18next';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import SaveIcon from '@material-ui/icons/Save';
import BlockIcon from '@material-ui/icons/Block';
import { Link } from "react-router-dom";
import 'styles/globitsStyles.css';

const useStyles = makeStyles((theme) => ({
    root: {
        // padding: '10px 0'
    },
    gridItem: {
        margin: '10px 0 !important'
    },
    gridContainerForm: {
        marginBottom: 10,
        borderBottom: `1px solid ${theme.palette.myTextColor?.textIcon}`
    },
    textField: {
        width: '100%',
        backgroundColor: '#fff',
        margin: '10px 0px !important',
    },
}));

export default function ChangePasswordForm(props) {

    const classes = useStyles();

    const { initialValues, handleSubmit } = props;

    const { t } = useTranslation();

    const validationSchema = Yup.object({
        oldPassword: Yup.string().required(),
        password: Yup.string().required().min(6, 'Too short'),
        confirmPassword: Yup.string().required()
        .oneOf([Yup.ref('password'), null], 'Passwords must match')
    });

    const formik = useFormik({
        enableReinitialize: true,
        initialValues,
        validationSchema: validationSchema,
        onSubmit: values => {
            handleSubmit(values);
        }
    });
    return (
        <div className={classes.root}>
            <form onSubmit={formik.handleSubmit}>
                <div className="dialog-body">
                    <DialogContent className="o-hidden">
                        <Grid container spacing={2} className={classes.gridContainerForm}>
                            <Grid item sm={6} xs={12}>
                                <TextField
                                    fullWidth
                                    variant="outlined"
                                    disabled
                                    label={t('user.display_name')}
                                    value={formik.values.user.displayName != null ? formik.values.user.displayName : ''}
                                    size="small"
                                />
                            </Grid>
                            <Grid item sm={6} xs={12}>
                                <TextField
                                    fullWidth
                                    variant="outlined"
                                    disabled
                                    label={t('user.username')}
                                    value={formik.values.user.username != null ? formik.values.user.username : ''}
                                    size="small"
                                />
                            </Grid>
                            <Grid item sm={6} xs={12}>
                                <TextField
                                    required
                                    classes={{ root: classes.textField }}
                                    id="password-current"
                                    size="small"
                                    name="oldPassword"
                                    type="password"
                                    label={t('user.current_password')}
                                    variant="outlined"
                                    value={formik.values.oldPassword}
                                    onChange={formik.handleChange}
                                    error={formik.touched.oldPassword && Boolean(formik.errors.oldPassword)}
                                    helperText={formik.touched.oldPassword && formik.errors.oldPassword}
                                />
                            </Grid>
                            <Grid item sm={6} xs={12}>
                                <TextField
                                    required
                                    classes={{ root: classes.textField }}
                                    id="password-current"
                                    size="small"
                                    name="password"
                                    type="password"
                                    label={t('user.new_password')}
                                    variant="outlined"
                                    value={formik.values.password}
                                    onChange={formik.handleChange}
                                    error={formik.touched.password && Boolean(formik.errors.password)}
                                    helperText={formik.touched.password && formik.errors.password}
                                />
                            </Grid>
                            <Grid item sm={6} xs={12}>
                                <TextField
                                    classes={{ root: classes.textField }}
                                    id="confirm-password"
                                    size="small"
                                    name="confirmPassword"
                                    type="password"
                                    label={t('user.re_password')}
                                    variant="outlined"
                                    value={formik.values.confirmPassword}
                                    onChange={formik.handleChange}
                                    error={formik.touched.confirmPassword && Boolean(formik.errors.confirmPassword)}
                                    helperText={formik.touched.confirmPassword && formik.errors.confirmPassword}
                                />
                            </Grid>
                        </Grid>
                    </DialogContent>
                </div>
                <div className="dialog-footer">
                    <DialogActions className="p-0">
                        <div className="flex flex-space-between flex-middle">
                            <Link to="/user-profile">
                                <Button
                                    startIcon={<BlockIcon />}
                                    variant="contained"
                                    className="mr-12 btn btn-secondary d-inline-flex"
                                    // color="secondary"
                                    // onClick={() => handleClose()}
                                >
                                    {t("general.button.cancel")}
                                </Button>
                            </Link>
                            <Button
                                startIcon={<SaveIcon />}
                                className="mr-0 btn btn-success d-inline-flex"
                                variant="contained"
                                // color="primary"
                                type="submit">
                                {t("general.button.save")}
                            </Button>
                        </div>
                    </DialogActions>
                </div>
            </form>
        </div>
    );
}