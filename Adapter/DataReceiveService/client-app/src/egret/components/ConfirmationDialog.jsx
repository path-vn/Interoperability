import React from "react";
import { Dialog, Button, DialogActions } from "@material-ui/core";
const ConfirmationDialog = ({
  open,
  onClose,
  text,
  title,
  agree,
  cancel,
  onYesClick
}) => {
  return (
    <Dialog
      maxWidth="xs"
      fullWidth={true}
      open={open}
      onClose={onClose}
    >
      <div className="pt-24 px-20 pb-8">
        <h4 className="capitalize">{title}</h4>
        <p>{text}</p>
        <DialogActions>
          <div className="flex flex-space-between flex-middle">
            <Button
              variant="contained"
              className="mr-12 btn btn-secondary"
              onClick={onClose}
            >
              {cancel}
            </Button>
            <Button
              className="btn btn-success"
              variant="contained"
              onClick={onYesClick}
            >
              {agree}
            </Button>
          </div>
        </DialogActions>

      </div>
    </Dialog>
  );
};

export default ConfirmationDialog;
