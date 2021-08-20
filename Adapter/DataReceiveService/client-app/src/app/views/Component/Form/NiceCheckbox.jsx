import React, {useEffect} from 'react';
import { Field } from "formik";
import {
    FormControlLabel,
    Checkbox
  } from "@material-ui/core";
export default function NiceCheckbox(props) {
    const {
        formik,
        disabled,
        field,
        label
    } = props;

    const [check, setCheck] = React.useState(formik.values[field]);
    useEffect(() => {
        formik.values[field] = check
    }, [check]);
    useEffect(() => {
        setCheck(formik.values[field])
    }, [field, formik.values]);
    return (
        <>
            <FormControlLabel
                value={check}
                // className="mb-16"
                name={field}
                onChange={() => {
                    setCheck(!check)
                }}
                control={<Checkbox checked={check} />}
                label={label}
            />
        </>
    );
}