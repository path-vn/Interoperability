import { Fab, Icon, Card, Grid, Divider, Button, DialogActions, Dialog } from "@material-ui/core";
import { createMuiTheme } from "@material-ui/core/styles";
import React, { Component } from "react";
import ReactDOM from "react-dom";
import MaterialTable, { MTableToolbar, Chip, MTableBody, MTableHeader } from 'material-table';
import { useTranslation, withTranslation, Trans } from 'react-i18next';
import DateFnsUtils from "@date-io/date-fns";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import Input from "@material-ui/core/Input";
import InputLabel from "@material-ui/core/InputLabel";
import { toast } from 'react-toastify';
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import Draggable from 'react-draggable';
import Paper from '@material-ui/core/Paper';
import { Breadcrumb, SimpleCard, EgretProgressBar } from "egret";
import axios from "axios";
import ConstantList from "../../appConfig";
import CloudUploadIcon from '@material-ui/icons/CloudUpload';


function PaperComponent(props) {
  return (
    <Draggable handle="#draggable-dialog-title" cancel={'[class*="MuiDialogContent-root"]'}>
      <Paper {...props} />
    </Draggable>
  );
}
class ImportExcelDialog extends React.Component {
  state = {
    dragClass: "",
    files: [],
    statusList: [],
    queProgress: 0
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

    console.log(files);
    let index = 0;
    for (const iterator of files) {
      index++;
      console.log(index);
      list.push({
        file: iterator,
        uploading: false,
        error: false,
        progress: 0
      });
    }

    this.setState({
      files: [...list]
    }, function () {
      console.log(this.state);
      document.getElementById('upload-single-file').value = null;
    });
  };

  handleDragOver = event => {
    event.preventDefault();
    this.setState({ dragClass: "drag-shadow" });
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
    }, function () {
      console.log(this.state);
    });
  };

  handleAllRemove = () => {
    this.setState({ files: [] });
  };
  fileUpload(file) {
    const url = ConstantList.API_ENPOINT + "/api/uploadExcel/file/uploadfile";
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
    console.log(file.file)
    this.fileUpload(file.file).then(res => {
      alert("File uploaded successfully.")
      console.log(res)
      // toast.success('general.success')

    })

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
    // let { dragClass, files, queProgress } = this.state;
    let file = this.props.file
    let isEmpty
    if (file == null || !file) {
      isEmpty = false
    } else {
      isEmpty = true
    }


    return (
      <React.Fragment>
        <div className="upload-form">
          {(this.props.isView) && <div className="flex flex-wrap mb-24">
            <label htmlFor="upload-single-file">
              {/* <Button
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
                </Button> */}
              <Button
                size="small"
                className="btn btn-secondary d-inline-flex"
                startIcon={<CloudUploadIcon />}
                component="span"
                variant="contained"
              >
                {t('general.button.select_file')}

              </Button>
            </label>
            <input
              className="display-none"
              onChange={this.props.handleFileSelect}
              id="upload-single-file"
              type="file"
            />
            <div className="px-16"></div>
          </div>}
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
                  {t('general.button.file_name')}
                </Grid>
                <Grid item lg={4} md={4}>
                  {t('general.button.size')}
                </Grid>
                <Grid item lg={4} md={4}>
                  {t('general.action')}
                </Grid>
              </Grid>
            </div>
            <Divider></Divider>

            {!isEmpty && <p className="px-16 center">{t('general.empty_file')}</p>}
            {isEmpty && (<div className="px-16 py-16" key={(file && file.name) ? file.name : ""}>
              <Grid
                container
                spacing={2}
                justify="center"
                alignItems="center"
                direction="row"
              >
                <Grid item lg={4} md={4} sm={12} xs={12}>
                  {(file && file.name) ? file.name : ""}
                </Grid>
                <Grid item lg={1} md={1} sm={12} xs={12}>
                  {(file && file.size) ? (file.size / 1024 / 1024).toFixed(1) : ""}MB
                  {/* {(file.size / 1024 / 1024).toFixed(1)} MB */}
                </Grid>
                <Grid item lg={2} md={2} sm={12} xs={12}>
                  <EgretProgressBar value={(file && file.progress) ? file.progress : ""}></EgretProgressBar>
                </Grid>
                <Grid item lg={1} md={1} sm={12} xs={12}>
                  {(file ? false : true) && <Icon color="error">error</Icon>}
                  {/* {uploading && <Icon className="text-green">done</Icon>} */}
                </Grid>
                <Grid item lg={4} md={4} sm={12} xs={12}>
                  <div className="flex">
                    {/* <Button
                            variant="contained"
                            color="primary"
                            disabled={uploading}
                            onClick={() => this.props.uploadSingleFile(index)}
                          >
                            {t('general.upload')}
                          </Button> */}
                    {/* <Button
                            className="mx-8"
                            variant="contained"
                            disabled={!uploading}
                            color="secondary"
                            onClick={() => this.props.handleSingleCancel(index)}
                          >
                            {t('general.cancel')}
                          </Button> */}
                    <Button
                      variant="contained"
                      className="bg-error"
                      onClick={() => this.props.handleSingleRemove()}
                    >
                      {t('Remove')}
                    </Button>
                  </div>

                </Grid>
              </Grid>
            </div>)}
          </Card>
        </div>
      </React.Fragment>
    );
  }
}
export default ImportExcelDialog;