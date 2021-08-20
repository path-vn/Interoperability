    import React, { useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import {
    TextField,
    IconButton
} from '@material-ui/core';
import MenuItem from '@material-ui/core/MenuItem';
import { useTranslation } from 'react-i18next';
import ClearIcon from "@material-ui/icons/Clear";

const useStyles = makeStyles((theme) => ({
    // formControl: {
    //     margin: theme.spacing(1),
    //     minWidth: 120,
    // },
    // selectEmpty: {
    //     marginTop: theme.spacing(2),
    // },
    // root: {
    //     '&:hover': {
    //         clearSelect: {
    //             opacity: 1,
    //             transition: "linear 0.2"
    //         }
    //     }
    // },
    clearSelect: {
        // opacity: 0,
        marginRight: "10px"
    }
}));

export default function NiceSelect(props) {
    const { t } = useTranslation();
    const classes = useStyles();

    // const [value, setValue] = React.useState('');

    const {
        formik,
        optionList,
        field,
        variant,
        size,
        label,
        disabled
    } = props;

    // const handleChange = (event) => {
    //     setValue(event.target.value);
    // };

    useEffect(() => {
        formik.setFieldValue(field, formik.values[field]);
    },[]);
    
    return (
        <TextField
            select
            disabled={disabled ? disabled : false}
            fullWidth
            value={formik.values[field]}
            className={field}
            onChange={(even, option) => {
                formik.setFieldValue(field, option.props.value);
            }}
            variant={variant}
            size={size}
            label={label}
            InputProps={{
                endAdornment: (
                    formik.values[field] && 
                    <IconButton size="small" onClick={() => formik.setFieldValue(field, undefined)} className={classes.clearSelect}>
                        <ClearIcon fontSize="small" />
                    </IconButton>
                    
                )
            }}
        >
            {optionList.map((option, index) => (
                <MenuItem key={option.value} value={option.value}>
                    {option.name}
                </MenuItem>
            ))}
            {/* <MenuItem value={null}>
                <em>{t('general.button.deselect')}</em>
            </MenuItem> */}
        </TextField>
    );
}