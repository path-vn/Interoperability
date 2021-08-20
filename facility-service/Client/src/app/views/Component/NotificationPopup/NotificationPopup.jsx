import React from "react";
import { Dialog, Button, DialogActions, DialogTitle, DialogContent } from "@material-ui/core";
const NotificationPopup = ({
  open,
  onConfirmDialogClose,
  text,
  title,
  agree,
  size,
  cancel,
  onYesClick
}) => {
  debugger
  return (
    <Dialog
      maxWidth={size ? size : "xs"}
      fullWidth={true}
      open={open}
      scroll={"paper"}
      onClose={onConfirmDialogClose}
    >
      <DialogTitle style={{ cursor: "move" }} id="draggable-dialog-title">
        <span className="mb-20 styleColor">
          {title}
        </span>
      </DialogTitle>
      <DialogContent dividers>
        {text}
      </DialogContent>
      <DialogActions>
        <div className="flex flex-space-between flex-middle">
          <Button
            variant="contained"
            color="primary"
            onClick={onYesClick}
          >
            {agree}
          </Button>
        </div>
      </DialogActions>
    </Dialog>
  );
};

export default NotificationPopup;
