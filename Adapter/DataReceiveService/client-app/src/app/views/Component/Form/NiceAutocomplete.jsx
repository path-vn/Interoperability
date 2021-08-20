import React, { useEffect } from 'react';
import {
    makeStyles,
    TextField,
    FormControl
} from '@material-ui/core';
import Autocomplete from "@material-ui/lab/Autocomplete";


const useStyles = makeStyles((theme) => ({
    formControl: {
        width: '100%'
    }
}));


export default function NiceAutocomplete(props) {
    // const classes = useStyles();
    const {
        formik,
        field,
        label,
        options,
        variant,
        multiple,
        required,
        size,
        displayData,
        disabled,
        classes
    } = props;

    return (
        <Autocomplete 
            classes={classes}
            multiple={multiple ? multiple : false}
            disabled={disabled ? disabled : false}
            value={formik.values[field]}
            id={field}
            name={field}
            onChange={(even, value) => {
                formik.setFieldValue(field, value);
            }}
            options={options}
            // options={options.map(option => option[idData ? idData : "code"])}

            getOptionLabel={(option) => option[displayData ? displayData : "name"] ? option[displayData ? displayData : "name"] : ""}
            // getOptionLabel={(optionId) =>
            //     options.filter(option => option[idData ? idData : "id"] === optionId)[0]?.[displayData ? displayData : "name"]
            // }
            getOptionSelected={(option, value) => option.id === value.id}

            filterSelectedOptions
            renderInput={params => (
                <TextField
                    {...params}
                    required={required ? required : false}
                    size={size}
                    variant={variant}
                    label={label}
                    error={formik.touched[field] && Boolean(formik.errors[field])}
                    helperText={formik.touched[field] && formik.errors[field]}
                />
            )}
        />
    );

}