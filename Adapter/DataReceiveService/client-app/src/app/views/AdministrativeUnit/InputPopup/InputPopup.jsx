import React, { useEffect } from 'react';
import {
    Button,
    TextField
} from "@material-ui/core";
import './InputPopup.scss';
import { useTranslation } from 'react-i18next';
import SelectParentPopup from './SelectParentPopup';

export default function InputPopup(props) {
    const { t } = useTranslation();
    const {
        formik,
        field,
        size,
        required,
        variant,
        label,
        classes
    } = props;

    const [openPopup, handleOpenPopup] = React.useState(false);
    const [selectedItem, handleSelectItem] = React.useState(formik.values[field]);

    const handleSelect = (item) => {
        handleOpenPopup(false);
        handleSelectItem(item);

    }

    useEffect(() => {
        formik.values[field] = selectedItem;
        // console.log(formik.values[field]);
    }, [selectedItem]);

    // useEffect(() => {
    //     console.log(props.level);
    // }, []);

    return (

        <div className="input-popup-container">
            <TextField
                disabled
                fullWidth
                classes={classes}
                id={field}
                size={size}
                name={field}
                label={label}
                value={
                    selectedItem ? selectedItem.name : ""
                }

                onChange={formik.handleChange}
                required={required ? required : false}
                variant={variant}
                error={formik.touched[field] && Boolean(formik.errors[field])}
                helperText={formik.touched[field] && formik.errors[field]}
            />
            <Button
                size="small"
                className="btn btn-primary-d"
                style={{ float: "right" }}
                variant="contained"
                onClick={() => handleOpenPopup(true)}
            >
                {t("general.button.select")}
            </Button>
            {openPopup && (
                <SelectParentPopup
                    open={openPopup}
                    handleSelect={handleSelect}
                    selectedItem={
                        selectedItem ? selectedItem : {}
                    }
                    handleClose={() => handleOpenPopup(false)}
                    t={t}
                    level={props.level}
                />
            )}
        </div>

    );
}
