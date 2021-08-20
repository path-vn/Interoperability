import { Icon, Card, Grid, Divider, Button, DialogActions, Dialog, Modal } from "@material-ui/core";
import React from "react";
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import Draggable from 'react-draggable';
import Paper from '@material-ui/core/Paper';
import { EgretProgressBar } from "egret";
import axios from "axios";
import ConstantList from "../../appConfig";
import NotificationPopup from '../Component/NotificationPopup/NotificationPopup';

function PaperComponent(props) {
  return (
    <Draggable handle="#draggable-dialog-title" cancel={'[class*="MuiDialogContent-root"]'}>
      <Paper {...props} />
    </Draggable>
  );
}


function PopupShowResult(props) {
  debugger
  let { text, open, handleClosePopupShowResult, handleShowPopupShowResult } = props;

  return (
    <div>
      <Modal
        show={open}
        backdrop="static"
        keyboard={false}
      >
        <div>
          <Modal.Header closeButton>
            <Modal.Title>Modal title</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            {text}
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClosePopupShowResult}>
              Close
          </Button>
            <Button variant="primary">Understood</Button>
          </Modal.Footer>
        </div>
      </Modal>
    </div>
  );
}

class ImportExcelDialog extends React.Component {
  state = {
    dragClass: "",
    files: [],
    statusList: [],
    queProgress: 0,
    textPopup: "",
    openPopupShowResult: false
  };
  handleFileUploadOnSelect = event => {
    let files = event.target.files;
    this.fileUpload(files[0]).then(res => {
      alert("File uploaded successfully.")
    });
  }
  handleFileSelect = event => {
    let files = event.target.files;
    let list = [];

    for (const iterator of files) {
      list.push({
        file: iterator,
        uploading: false,
        error: false,
        progress: 0
      });
    }

    this.setState({
      files: [...list]
    });
  };

  handleDragOver = event => {
    event.preventDefault();
    this.setState({ dragClass: "drag-shadow" });
  };

  handleClosePopupShowResult = () => {
    this.setState({ openPopupShowResult: false, textPopup: '' });
  };


  handleShowPopupShowResult = () => {
    this.setState({ openPopupShowResult: true });
  };


  handleDrop = event => {
    event.preventDefault();
    event.persist();

    let files = event.dataTransfer.files;
    let list = [];

    for (const iterator of files) {
      list.push({
        file: iterator,
        uploading: false,
        error: false,
        progress: 0
      });
    }

    this.setState({
      dragClass: "",
      files: [...list]
    });

    return false;
  };

  handleDragStart = event => {
    this.setState({ dragClass: "drag-shadow" });
  };

  handleSingleRemove = index => {
    let files = [...this.state.files];
    files.splice(index, 1);
    this.setState({
      files: [...files]
    });
  };

  handleAllRemove = () => {
    this.setState({ files: [] });
  };
  fileUpload(file) {
    const url = ConstantList.API_ENPOINT + "/api/file/import/importSample";
    let formData = new FormData();
    formData.append('uploadfile', file);//Lưu ý tên 'uploadfile' phải trùng với tham số bên Server side
    const config = {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    }
    return axios.post(url, formData, config)
  }

  uploadSingleFile = index => {
    let allFiles = [...this.state.files];
    let file = this.state.files[index];

    this.fileUpload(file.file).then(({ data }) => {
      
      if (data != null) {
        if (data.success) {
          alert("File uploaded successfully.");
          window.location.reload();
        }
        else {
          this.setState({ openPopupShowResult: true, textPopup: <pre dangerouslySetInnerHTML={{__html: data.text}} /> });
        }
      }
      else {
        window.location.reload();
      }
    });

    allFiles[index] = { ...file, uploading: true, error: false };

    this.setState({
      files: [...allFiles]
    });
  };

  uploadAllFile = () => {
    let allFiles = [];

    this.state.files.map(item => {
      allFiles.push({
        ...item,
        uploading: true,
        error: false
      });

      return item;
    });

    this.setState({
      files: [...allFiles],
      queProgress: 35
    });
  };

  handleSingleCancel = index => {
    let allFiles = [...this.state.files];
    let file = this.state.files[index];

    allFiles[index] = { ...file, uploading: false, error: true };

    this.setState({
      files: [...allFiles]
    });
  };

  handleCancelAll = () => {
    let allFiles = [];

    this.state.files.map(item => {
      allFiles.push({
        ...item,
        uploading: false,
        error: true
      });

      return item;
    });

    this.setState({
      files: [...allFiles],
      queProgress: 0
    });
  };

  render() {
    const { t, i18n, handleClose, handleSelect, selectedItem, open } = this.props;
    let { dragClass, files, queProgress } = this.state;
    let isEmpty = files.length === 0;

    return (
      <Dialog onClose={handleClose} open={open} PaperComponent={PaperComponent} maxWidth={'md'} fullWidth>
        <DialogTitle style={{ cursor: 'move' }} id="draggable-dialog-title">
          <span className="mb-20">{t("general.importExcel")}</span>
        </DialogTitle>
        <DialogContent>
          <div className="upload-form m-sm-30">
            <div className="flex flex-wrap mb-24">
              <label htmlFor="upload-single-file">
                <Button
                  size="small"
                  className="capitalize"
                  component="span"
                  variant="contained"
                  color="primary"
                >
                  <div className="flex flex-middle">
                    <Icon className="pr-8">cloud_upload</Icon>
                    <span>{t('general.select_file')}</span>
                  </div>
                </Button>
              </label>
              <input
                className="display-none"
                onChange={this.handleFileSelect}
                id="upload-single-file"
                type="file"
              />
              <div className="px-16"></div>
            </div>
            <Card className="mb-24" elevation={2}>
              <div className="p-16">
                <Grid
                  container
                  spacing={2}
                  justify="center"
                  alignItems="center"
                  direction="row"
                >
                  <Grid item lg={4} md={4}>
                    {t('general.file_name')}
                  </Grid>
                  <Grid item lg={4} md={4}>
                    {t('general.size')}
                  </Grid>
                  <Grid item lg={4} md={4}>
                    {t('general.action')}
                  </Grid>
                </Grid>
              </div>
              <Divider></Divider>

              {isEmpty && <p className="px-16 center">{t('general.empty_file')}</p>}

              {files.map((item, index) => {
                let { file, uploading, error, progress } = item;
                return (
                  <div className="px-16 py-16" key={file.name}>
                    <Grid
                      container
                      spacing={2}
                      justify="center"
                      alignItems="center"
                      direction="row"
                    >
                      <Grid item lg={4} md={4} sm={12} xs={12}>
                        {file.name}
                      </Grid>
                      <Grid item lg={1} md={1} sm={12} xs={12}>
                        {(file.size / 1024 / 1024).toFixed(1)} MB
                    </Grid>
                      <Grid item lg={2} md={2} sm={12} xs={12}>
                        <EgretProgressBar value={progress}></EgretProgressBar>
                      </Grid>
                      <Grid item lg={1} md={1} sm={12} xs={12}>
                        {error && <Icon color="error">error</Icon>}
                        {/* {uploading && <Icon className="text-green">done</Icon>} */}
                      </Grid>
                      <Grid item lg={4} md={4} sm={12} xs={12}>
                        <div className="flex">
                          <Button
                            variant="contained"
                            color="primary"
                            disabled={uploading}
                            onClick={() => this.uploadSingleFile(index)}
                          >
                            {t('general.upload')}
                          </Button>
                          <Button
                            className="mx-8"
                            variant="contained"
                            disabled={!uploading}
                            color="secondary"
                            onClick={() => this.handleSingleCancel(index)}
                          >
                            {t('general.cancel')}
                          </Button>
                          <Button
                            variant="contained"
                            className="bg-error"
                            onClick={() => this.handleSingleRemove(index)}
                          >
                            {t('general.remove')}
                          </Button>
                        </div>
                      </Grid>
                    </Grid>
                  </div>
                );
              })}
              {
                this.state.openPopupShowResult &&
                <NotificationPopup
                title={t('general.noti')}
                open={this.state.openPopupShowResult}
                onYesClick={this.handleClosePopupShowResult}
                text={this.state.textPopup}
                size="lg"
                agree={t('general.agree')}
            />
              }
            </Card>
          </div>
        </DialogContent>
        <DialogActions>
          <Button
            className="mb-16 mr-36 align-bottom"
            variant="contained"
            color="secondary"
            onClick={() => handleClose()}>{t('general.close')}
          </Button>
        </DialogActions>
      </Dialog>
    );
  }
}
export default ImportExcelDialog;