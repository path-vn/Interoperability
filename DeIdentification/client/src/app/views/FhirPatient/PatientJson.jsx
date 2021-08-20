import React, { Component } from "react";
import {
  Dialog,
  Button,
  Grid,
  DialogActions,
  IconButton,
  TextField,
} from "@material-ui/core";
import { withStyles } from "@material-ui/core/styles";
import Tooltip from "@material-ui/core/Tooltip";
import RemoveCircleOutlineIcon from "@material-ui/icons/RemoveCircleOutline";
import AddCircleOutlineIcon from "@material-ui/icons/AddCircleOutline";
import { useTranslation } from "react-i18next";
import { ValidatorForm } from "react-material-ui-form-validator";
// import { getAllOrganization } from "../Organization/OrganizationService";
import BlockIcon from '@material-ui/icons/Block';
const LightTooltip = withStyles((theme) => ({
  tooltip: {
    backgroundColor: theme.palette.common.white,
    color: "rgba(0, 0, 0, 0.87)",
    boxShadow: theme.shadows[1],
    fontSize: 11,
    marginLeft: "-1.5em",
  },
}))(Tooltip);
function MaterialButton(props) {
  const { t, i18n } = useTranslation();
  const item = props.item;
  const colorIcon = props.colorIcon;
  const enableIcon = props.enableIcon;

  return (
    <div className="none_wrap">
      {
        <LightTooltip
          title={t("general.deleteRow")}
          placement="right-end"
          enterDelay={300}
          leaveDelay={200}
          disabled={enableIcon}
          PopperProps={{
            popperOptions: {
              modifiers: { offset: { enabled: true, offset: "10px, 0px" } },
            },
          }}
        >
          <IconButton size="small" onClick={() => props.onSelect(item, 0)}>
            <RemoveCircleOutlineIcon fontSize="small" color={colorIcon ? "disabled" : "error"} />
          </IconButton>
        </LightTooltip>
      }
      <LightTooltip
        title={t("general.addRow")}
        placement="right-end"
        enterDelay={300}
        leaveDelay={200}
        PopperProps={{
          popperOptions: {
            modifiers: { offset: { enabled: true, offset: "10px, 0px" } },
          },
        }}
      >
        <IconButton size="small" onClick={() => props.onSelect(item, 1)}>
          <AddCircleOutlineIcon fontSize="small" color="primary" />
        </IconButton>
      </LightTooltip>
    </div>
  );
}
class PatientJson extends Component {
  state = {
    name: [],
    code: "",
    death: {},
    description: "",
    isActive: false,
    text: "",
    resourceType: "Patient",
  };



  

  componentWillMount() {
    let { open, handleClose, item } = this.props;
    var json = JSON.stringify(item);

    this.setState({json: json }, () => {});
    
    
  }

 

  render() {
    let { open, handleClose, handleOKEditClose, t, i18n } = this.props;
    let {
   
    } = this.state;
    return (
      <Dialog open={open} maxWidth="md" fullWidth>
        <div className="p-24">
          <h4 className="mb-20">{this.props.check ? "Fhir Detail":"Elastic Search Detail"}</h4>
          <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
            <Grid className="mb-16" container spacing={2} md={12} sm={12} xs={12}>
                  <Grid item md={12} sm={12} xs={12}>
                  <TextField
                    id="filled-multiline-flexible"
                    label="Multiline"
                    multiline
                    rowsMax={10}
                    value={this.state.json}
                    // onChange={handleChange}
                    variant="filled"
                    fullWidth
                    />
              </Grid>
            </Grid>

            <DialogActions>
              <div className="flex flex-space-between flex-middle">
              <Button
                  variant="contained"
                  startIcon={<BlockIcon />}
                  className="mr-12 d-inline-flex btn btn-secondary"
                  color="secondary"
                  onClick={() => this.props.handleClose()}
                >
                  {t("general.button.cancel")}
                </Button>

              </div>
            </DialogActions>
          </ValidatorForm>
        </div>
      </Dialog>
    );
  }
}

export default PatientJson;
