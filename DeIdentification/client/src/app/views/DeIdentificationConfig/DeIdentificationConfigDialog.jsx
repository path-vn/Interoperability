import React, { Component } from "react";
import {
  Dialog,
  Button,
  Grid,
  FormControlLabel,
  Checkbox,
  DialogTitle,
  IconButton,
  Icon,
  DialogContent,
  DialogActions
} from "@material-ui/core";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import { checkCode, addNew, update } from "./DeIdentificationConfigService";
import { toast } from 'react-toastify';
import Draggable from 'react-draggable';
import Paper from '@material-ui/core/Paper';
import 'react-toastify/dist/ReactToastify.css';
toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3
  //etc you get the idea
});
function PaperComponent(props) {
  return (
    <Draggable handle="#draggable-dialog-title" cancel={'[class*="MuiDialogContent-root"]'}>
      <Paper {...props} />
    </Draggable>
  );
}
class DeIdentificationConfigDialog extends Component {
  state = {
    name: "",
    code: "",
    isUsed: false,
    isActive: false
  };

  handleChange = (event, source) => {
    event.persist();
    if (source === "switch") {
      this.setState({ isActive: event.target.checked });
      return;
    }
    if (source === "isUsed") {
      this.setState({ isUsed: event.target.checked });
      return;
    }
    this.setState({
      [event.target.name]: event.target.value
    });
  };

  handleFormSubmit = () => {
    let { t } = this.props
    let { id, code } = this.state;
    checkCode(id, code).then((result) => {
      if (result.data) {
        toast.success(t('toast.duplicate_code'));
      } else {
        if (id) {
          update({ ...this.state }).then(() => {
            this.props.handleDialogClose();
            toast.warning(t('toast.update_success'));
          });

        } else {
          addNew({
            ...this.state,
          }).then(() => {
            this.props.handleDialogClose();
            toast.success(t('toast.add_success'));
          });
        }
      }
    });
  };
  componentWillUnmount() {
    this.props.updatePageData();
  }
  componentDidMount() {
    let item = this.props.item;
    if (item) {
      this.setState({ ...item });
    }
  }

  render() {
    let { open, handleClose, t } = this.props;
    let {
      id,
      name,
      code,
      isUsed
    } = this.state;

    return (
      <Dialog
        className="dialog-container"
        open={open}
        PaperComponent={PaperComponent}
        fullWidth
        maxWidth="sm"
      >
        <DialogTitle
          className="dialog-header bgc-primary-d1"
          style={{ cursor: 'move' }}
          id="draggable-dialog-title"
        >
          <span className="mb-20 text-white" > {(id ? t('general.button.edit') : t('general.button.add')) + " " + t("de_config.title")} </span>
          <IconButton style={{ position: "absolute", right: "10px", top: "10px" }} onClick={() => handleClose()}>
            <Icon color="disabled" title={t('general.close')}>close</Icon>
          </IconButton>
        </DialogTitle>
        <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
          <div className="dialog-body">
            <DialogContent className="o-hidden">
              <Grid className="" container spacing={4}>
                <Grid item sm={12} xs={12}>
                  <TextValidator
                    className="w-100 mb-16"
                    label={t('de_config.code')}
                    onChange={this.handleChange}
                    type="text"
                    name="code"
                    value={code}
                    validators={["required"]}
                    errorMessages={[t("general.required")]}
                  />
                  <TextValidator
                    className="w-100 mb-16"
                    label={t('de_config.name')}
                    onChange={this.handleChange}
                    type="text"
                    name="name"
                    value={name}
                    validators={["required"]}
                    errorMessages={[t("general.required")]}
                  />
                  <FormControlLabel
                    value={isUsed}
                    className="mb-16"
                    name="isUsed"
                    onChange={isUsed => this.handleChange(isUsed, "isUsed")}
                    control={<Checkbox
                      checked={isUsed}
                    />}
                    label={t("de_config.is_used")}
                  />
                </Grid>
              </Grid>
            </DialogContent>
          </div>
          <div className="dialog-footer">
            <DialogActions className="p-0">
              <div className="flex flex-space-between flex-middle">
                <Button
                  variant="contained"
                  className="mr-12 btn btn-secondary"
                  color="secondary"
                  onClick={() => this.props.handleClose()}
                >
                  {t("general.button.cancel")}
                </Button>
                <Button
                  className="mr-0 btn btn-success"
                  variant="contained"
                  color="primary"
                  type="submit">
                  {t("general.button.save")}
                </Button>
              </div>
            </DialogActions>
          </div>

        </ValidatorForm>
      </Dialog>
    );
  }
}

export default DeIdentificationConfigDialog;
