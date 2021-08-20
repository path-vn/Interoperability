import React from 'react';
import {
    Grid,
    makeStyles,
    TextField,
    DialogActions,
    Button,
    DialogContent
} from '@material-ui/core';
import Constant from './Constants';
import { useTranslation } from 'react-i18next';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import SaveIcon from '@material-ui/icons/Save';
import BlockIcon from '@material-ui/icons/Block';
import NiceTextField from '../Component/Form/NiceTextField';
import Autocomplete from "@material-ui/lab/Autocomplete";
const useStyles = makeStyles((theme) => ({
    root: {
        // padding: '10px 0'
    },
    gridItem: {
        margin: '10px 0 !important'
    },
    textField: {
        width: '100%',
    },
}));

export default function DataSourceForm(props) {
    const classes = useStyles();

    const { initialValues, handleSubmit, handleClose } = props;

    const { t } = useTranslation();

    const validationSchema = Yup.object({
        code: Yup.string().required(),
        name: Yup.string().required()
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
                        <Grid container className={classes.gridContainerForm} spacing={2}>
                            <Grid item sm={12}>
                                <TextField
                                    required
                                    classes={{ root: classes.textField }}
                                    id="name"
                                    size="small"
                                    name="name"
                                    label={t('occupation.name')}
                                    variant="outlined"
                                    value={formik.values.name}
                                    onChange={formik.handleChange}
                                    error={formik.touched.name && Boolean(formik.errors.name)}
                                    helperText={formik.touched.name && formik.errors.name}
                                />
                            </Grid>
                            <Grid item sm={12}>
                                <Autocomplete
                                    value={formik.values.code}
                                    id="code"
                                    name="code"
                                    onChange={(even, value) => {
                                        formik.setFieldValue("code", value);
                                    }}
                                    options={(Constant.DataSourceCode ? Constant.DataSourceCode : []).map(option => option.id)}
                                    getOptionLabel={(optionId) =>
                                        (Constant.DataSourceCode ? Constant.DataSourceCode : []).filter(option => option.id === optionId)[0]?.name
                                    }
                                    filterSelectedOptions
                                    renderInput={params => (
                                        <TextField
                                            {...params}
                                            size="small"
                                            variant="outlined"
                                            label={t('data_source.code')}
                                            error={formik.touched.code && Boolean(formik.errors.code)}
                                            helperText={formik.touched.code && formik.errors.code}
                                        />
                                    )}
                                />
                            </Grid>
                            <Grid item sm={12}>
                                <TextField
                                    required
                                    classes={{ root: classes.textField }}
                                    id="channelUrl"
                                    size="small"
                                    name="channelUrl"
                                    label={t('data_source.channel')}
                                    variant="outlined"
                                    value={formik.values.channelUrl}
                                    onChange={formik.handleChange}
                                    error={formik.touched.channelUrl && Boolean(formik.errors.channelUrl)}
                                    helperText={formik.touched.channelUrl && formik.errors.channelUrl}
                                />
                            </Grid>
                            <Grid item sm={12}>
                                <TextField
                                    classes={{ root: classes.textField }}
                                    id="description"
                                    size="small"
                                    name="description"
                                    label={t('data_source.description')}
                                    variant="outlined"
                                    value={formik.values.description}
                                    onChange={formik.handleChange}
                                    error={formik.touched.description && Boolean(formik.errors.description)}
                                    helperText={formik.touched.description && formik.errors.description}
                                />
                            </Grid>
                        </Grid>
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
                            <Button
                                startIcon={<SaveIcon />}
                                className="mr-0 btn btn-success d-inline-flex"
                                variant="contained"
                                color="primary"
                                type="submit">
                                {t("general.button.save")}
                            </Button>
                        </div>
                    </DialogActions>
                </div>
            </form>
        </div>
    )
}