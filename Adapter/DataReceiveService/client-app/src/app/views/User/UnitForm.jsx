import React, { useEffect } from 'react';
import {
    Grid,
    makeStyles,
    DialogActions,
    Button,
    DialogContent,
    TextField
} from '@material-ui/core';
import Constant from '../HealthOrg/Constant';
import SaveIcon from '@material-ui/icons/Save';
import BlockIcon from '@material-ui/icons/Block';
import { useTranslation } from 'react-i18next';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import InputPopup from '../HealthOrg/InputPopup/InputPopup';
import InputPopupAdminitrative from '../AdministrativeUnit/InputPopup/InputPopup';
import NiceTextField from '../Component/Form/NiceTextField';
import Autocomplete from "@material-ui/lab/Autocomplete";
import UserTableByOrgIdAccordion from '../HealthOrg/UserTableByOrgIdAccordion';
import { Link } from "react-router-dom";
import 'styles/globitsStyles.css';
import NiceCheckbox from '../Component/Form/NiceCheckbox';
// import NiceAutocomplete from '../Component/Form/NiceAutocomplete';

const useStyles = makeStyles((theme) => ({
    root: {
        // padding: '10px 0'
    },
    gridItem: {
        margin: '10px 0 !important'
    },
    textField: {
        backgroundColor: '#fff',
        width: '100%',
    },
}));
export default function UnitForm(props) {

    const classes = useStyles();

    const {
        initialValues,
        handleSubmit,
        itemList,
    } = props;
    const { t } = useTranslation();

    const validationSchema = Yup.object({
        code: Yup.string().required(),
        name: Yup.string().required(),
        // orgType: Yup.string().required(),
        level: Yup.string().required(),
        // adminUnit: Yup.object().required(),
    });

    const formik = useFormik({
        enableReinitialize: true,
        initialValues,
        validationSchema: validationSchema,
        onSubmit: values => {
            handleSubmit(values);
        }
    });

    const [level, setLevel] = React.useState('');

    useEffect(() => {
        setLevel(formik.values.level);
    }, [formik.values.level]);

    return (
      <div className={classes.root}>
        <form onSubmit={formik.handleSubmit}>
          <div className="dialog-body">
            <DialogContent className="o-hidden">
              {/* <DialogContent className={classes.dialogBody}> */}
              <Grid container className={classes.gridContainerForm} spacing={2}>
                <Grid item sm={12} xs={12}>
                  <NiceTextField
                    formik={formik}
                    classes={{ root: classes.textField }}
                    field="code"
                    size="small"
                    label={t("health_org.code")}
                    variant="outlined"
                  />
                </Grid>
                <Grid item sm={12} xs={12}>
                  <NiceTextField
                    formik={formik}
                    classes={{ root: classes.textField }}
                    field="name"
                    size="small"
                    label={t("health_org.name")}
                    variant="outlined"
                  />
                </Grid>
                <Grid item sm={12} xs={12}>
                  <NiceCheckbox
                    field="checking"
                    formik={formik}
                    label="Đơn vị rà soát"
                  />
                  <NiceCheckbox
                    field="treatment"
                    formik={formik}
                    label="Đơn vị điều trị"
                  />
                  <NiceCheckbox
                    field="manager"
                    formik={formik}
                    label="Đơn vị quản lý"
                  />
                  <NiceCheckbox
                    field="confirmation"
                    formik={formik}
                    label="Đơn vị Khẳng định"
                  />
                  <NiceCheckbox
                    field="screening"
                    formik={formik}
                    label="Đơn vị sàng lọc"
                  />
                </Grid>
                <Grid item md={12} sm={12} xs={12}>
                  {/* <NiceAutocomplete
                                    formik={formik}
                                    field="level"
                                    label={t('health_org.level')}
                                    options={Constant.listLevel ? Constant.listLevel : []}
                                    variant="outlined"
                                    size="small"
                                /> */}

                  <Autocomplete
                    value={formik.values.level}
                    classes={{ root: classes.textField }}
                    id="level"
                    name="level"
                    onChange={(even, value) => {
                      formik.setFieldValue("level", value);
                    }}
                    options={(Constant.listLevel ? Constant.listLevel : []).map(
                      (option) => option.value
                    )}
                    getOptionLabel={(optionId) =>
                      (Constant.listLevel ? Constant.listLevel : []).filter(
                        (option) => option.value === optionId
                      )[0]?.name
                    }
                    filterSelectedOptions
                    renderInput={(params) => (
                      <TextField
                        {...params}
                        size="small"
                        variant="outlined"
                        label={t("health_org.level")}
                        error={
                          formik.touched.level && Boolean(formik.errors.level)
                        }
                        helperText={formik.touched.level && formik.errors.level}
                      />
                    )}
                  />
                </Grid>
                {level && (
                  <Grid item md={12} sm={12} xs={12}>
                    <InputPopupAdminitrative
                        classes={{ root: classes.textField }}
                        label={t("health_org.administrative_unit")}
                        formik={formik}
                        field="adminUnit"
                        variant="outlined"
                        size="small"
                        level={level}
                    />
                  </Grid>
                )}
                <Grid item sm={12} xs={12}>
                  <InputPopup
                    classes={{ root: classes.textField }}
                    label={t("health_org.parent")}
                    formik={formik}
                    field="parent"
                    variant="outlined"
                    size="small"
                  />
                </Grid>
                {/* {itemList && <UserTableByOrgIdAccordion itemList={itemList} t={t}/>} */}
              </Grid>
            </DialogContent>
          </div>
          <div className="dialog-footer">
            <DialogActions className="p-0">
              <div className="flex flex-space-between flex-middle">
                <Link to='/dashboard/health_org'>
                    <Button
                    startIcon={<BlockIcon />}
                    variant="contained"
                    className="mr-12 btn btn-secondary d-inline-flex"
                    >
                        {t("general.button.cancel")}
                    </Button>
                </Link>
                <Button
                  startIcon={<SaveIcon />}
                  className="mr-0 btn btn-success d-inline-flex"
                  variant="contained"
                  color="primary"
                  type="submit"
                >
                  {t("general.button.save")}
                </Button>
              </div>
            </DialogActions>
          </div>
        </form>
      </div>
    );
}