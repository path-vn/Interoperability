import React, { useEffect, useState } from "react";

import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker,
} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";

export default function NiceDatePicker(props) {

  const {
    formik,
    label,
    format,
    size,
    inputVariant,
    field
  } = props

  const [selectedDate, handleDateChange] = useState(formik.values[field]);

  useEffect(() => {
    formik.values[field] = selectedDate

  }, [selectedDate]);

  return (
    <MuiPickersUtilsProvider utils={DateFnsUtils}>
      <KeyboardDatePicker
        id={field}
        name={field}
        autoOk
        variant="inline"
        inputVariant={inputVariant}
        label={label}
        format={format}
        size={size}
        InputAdornmentProps={{ position: "end" }}
        onChange={date => handleDateChange(date)}
        value={selectedDate}
        error={formik.touched[field] && Boolean(formik.errors[field])}
        helperText={formik.touched[field] && formik.errors[field]}
      />
    </MuiPickersUtilsProvider>
  );
}