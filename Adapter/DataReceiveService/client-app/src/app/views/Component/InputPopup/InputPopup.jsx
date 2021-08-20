import React from 'react';
import {
    Button,
    Grid
} from "@material-ui/core";
import { TextValidator } from "react-material-ui-form-validator";
import './InputPopup.scss';
class InputPopup extends React.Component {

    render() {
        let { data, t, handlePopupOpen, displayData } = this.props;
        console.log(data);
        return (
            <div className="input-popup-container mt-16">
                <TextValidator
                    fullWidth
                    size="small"
                    className=""
                    InputProps={{
                        readOnly: true,
                    }}
                    label={
                        <span>
                            <span style={{ color: "red" }}></span>
                            
                            {this.props.title}
                        </span>
                    }
                    variant="outlined"
                    size="small"
                    value={
                        data != null ? data[displayData ? displayData : "name"] : ""
                    }
                />
                <Button
                    size="small"
                    className="btn btn-primary-d"
                    style={{ float: "right" }}
                    variant="contained"
                    onClick={() => {
                        handlePopupOpen();
                    }}
                >
                    {t("general.button.select")}
                </Button>
            </div>
        );
    }
}
export default InputPopup;