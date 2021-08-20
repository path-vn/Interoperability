import React, { useEffect } from 'react'
import {
    TextField,
    FormControl
} from '@material-ui/core';
export default function NiceTextField(props) {
    const {
        formik,
        field,
        label,
        variant,
        required,
        size,
        classes
    } = props;

    const [value, setValue] = React.useState(formik.values[field]);

    useEffect(() => {
        formik.values[field] = value
    }, [value]);

    return (
        <TextField
            fullWidth
            id={field}
            size={size}
            name={field}
            label={label}
            variant={variant}
            value={value}
            onChange={(event) => setValue(event.target.value)}
            required={required ? required : false}
            error={formik.touched[field] && Boolean(formik.errors[field])}
            helperText={formik.touched[field] && formik.errors[field]}
        />

    )

}