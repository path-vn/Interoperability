import React, { Component } from "react";
import {
  Dialog,
  Button,
  DialogTitle,
  Grid,
  FormControlLabel,
  Icon, DialogActions,
  IconButton,
  DialogContent
} from "@material-ui/core";
import Draggable from "react-draggable";
import Paper from '@material-ui/core/Paper';
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import {  updateUser, addNew, update,exportToExcel,exportToExcelElog } from "./DataFileService";
import {searchByPage as getDataSource} from "../DataSource/DataSourceService"
// import { generateRandomId } from "utils";
import Autocomplete from "@material-ui/lab/Autocomplete";
import ImportExcelDialog from "./ImportExcelDialog"
import axios from "axios";
import ConstantList from "../../appConfig";
import { toast } from "react-toastify";
import { saveAs } from "file-saver";
import SaveIcon from '@material-ui/icons/Save';
import BlockIcon from '@material-ui/icons/Block';
import CloudDownloadIcon from '@material-ui/icons/CloudDownload';
toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3,
});

function PaperComponent(props) {
  return (
    <Draggable handle="#draggable-dialog-title" cancel={'[class*="MuiDialogContent-root"]'}>
      <Paper {...props} />
    </Draggable>
  );
}

class DataFileDialog extends Component {
  state = {
    name: "",
    code: "",
    description: "",
    isActive: false,
    change: true
  };
  //
  handleFileSelect = event => {
    // this.setState({change : false},()=>{
    let files = event.target.files;
    console.log(files[0])
    if (
      files[0].type !== "application/vnd.ms-excel" &&
      files[0].type !== "application/vnd.ms-excel" &&
      files[0].type !== "application/vnd.ms-excel" &&
      files[0].type !== "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" &&
      files[0].type !== "application/vnd.openxmlformats-officedocument.spreadsheetml.template" &&
      files[0].type !== "application/vnd.ms-excel.sheet.macroEnabled.12" &&
      files[0].type !== "application/vnd.ms-excel.template.macroEnabled.12" &&
      files[0].type !== "application/vnd.ms-excel.addin.macroEnabled.12" &&
      files[0].type !== "application/vnd.ms-excel.sheet.binary.macroEnabled.12"

    ) {
      // alert("------------")
      toast.error("File incorrect format!");
      return
    }
    let list = [];
    let { file } = this.state
    let index = 0;
    // let checkError = {}
    let progress = 0
    for (const iterator of files) {
      index++;
      file = iterator
      // checkError.check = false,
      progress = 0
    }

    this.setState({
      file: file,
      // check : check,
      progress: progress, change: true
    }, function () {

      // document.getElementById('upload-single-file').value = null;
    });
    // })



  };
  ///
  handleSingleRemove = () => {
    this.setState({
      file: null
    }, function () {
    });
  };
  //
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
  //
  handleSingleCancel = index => {
    let allFiles = [...this.state.files];
    let file = this.state.files[index];

    allFiles[index] = { ...file, uploading: false, error: true };

    this.setState({
      files: [...allFiles]
    });
  };
  //
  fileUpload(file, dataSource) {
    const url = ConstantList.API_ENPOINT + "/api/uploadExcel/file/uploadfile";
    let formData = new FormData();
    formData.append('uploadfile', file);//Lưu ý tên 'uploadfile' phải trùng với tham số bên Server side
    formData.append('dataSourceId', dataSource.id)
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
  handleChange = (event, source) => {
    event.persist();
    if (source === "switch") {
      this.setState({ isActive: event.target.checked });
      return;
    }
    this.setState({
      [event.target.name]: event.target.value
    });
  };

  handleFormSubmit = () => {
    let { id } = this.state;
    let { file, dataSource } = this.state;
    console.log(file.file)

    this.fileUpload(file, dataSource).then(res => {
      alert("File uploaded successfully.")

      this.props.handleClose();
    })

    //Nếu trả về false là code chưa sử dụng có thể dùng
    // if (id) {
    //     update(
    //     {...this.state}
    //   ).then(() => {
    //     this.fileUpload(file,dataSource).then(res => {
    //       this.props.handleClose();
    //       alert("File uploaded successfully.")
    //       // toast.success('general.success')

    //     })

    //   });
    // } else {
    //     this.fileUpload(file).then(res => {
    //       alert("File uploaded successfully.")

    //       this.props.handleClose();
    //       // toast.success('general.success')
    //       let data = res;
    //       data.dataSource = this.state.dataSource
    //       if(data != null){
    //         data = {}
    //       }
    //       data.dataSource = this.state.dataSource
    //       addNew({...data}).then((e)=>{
    //       })
    //     })

    // }


  };

  componentWillMount() {
    //getUserById(this.props.uid).then(data => this.setState({ ...data.data }));
    let { open, handleClose, item } = this.props;
    this.setState(item);
  }

  componentDidMount() {
    var searchObject = {};
    searchObject.pageIndex = 1;
    searchObject.pageSize = 100000;
    getDataSource(searchObject).then(({ data }) => {
      this.setState({ listDataSource: [...data.content] })
    }
    );
  }
  changeSelected = (value, type) => {
    if (type === 'dataSource') {
      this.setState({ dataSource: value }, () => {
      });
    }

  }

  handleExportExcel = () => {
    let { t } = this.props;
    const { dataSource } = this.state;
    // if (round != null && round.id != "") {
    if (dataSource != null && dataSource.id != null) {

      exportToExcel(dataSource.id)
        .then((res) => {
          toast.success(this.props.t('general.successExport'));
          let blob = new Blob([res.data], {
            type:
              "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
          });
          saveAs(blob, "elog.xlsx");
        })
        .catch((err) => {
          console.log(err);
        });
    } else {
      alert("Choose Data Source")
      // toast.warning(t("general.noData"));
    }
  };

  render() {
    let { open, handleClose, t, i18n } = this.props;
    let {
      id,
      name,
      code,
      description
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
          <span className="mb-20 text-white" > {(id ? t('general.button.edit') : t('general.button.add')) + " " + t("data_source.title")} </span>
          <IconButton style={{ position: "absolute", right: "10px", top: "10px" }} onClick={() => handleClose()}>
            <Icon color="disabled" title={t('general.button.close')}>close</Icon>
          </IconButton>
        </DialogTitle>

        <ValidatorForm ref="form" onSubmit={this.handleFormSubmit}>
          <div className="dialog-body">
            <DialogContent className="o-hidden">
              <Grid className="mb-16" container spacing={3}>

                <Grid item sm={6} xs={12}>
                  <Autocomplete
                    id="combo-box"
                    className=""
                    disabled={this.props.checkView}
                    options={this.state.listDataSource ? this.state.listDataSource : []}
                    value={this.state.dataSource != null ? this.state.dataSource : null}
                    renderInput={(params) => <TextValidator {...params}
                      value={this.state.dataSource ? this.state.dataSource : null}
                      label={<span><span style={{ color: "red" }}>* </span>Data Source</span>}
                      variant="outlined"
                      size="small"
                      validators={["required"]}
                      errorMessages={[t("general.required")]}
                    />}
                    // defaultValue = {listCategory[0]}
                    getOptionLabel={(option) => option.name}
                    getOptionSelected={(option, value) =>
                      option.id === value.id
                    }
                    onChange={(event, value) => { this.changeSelected(value, 'dataSource') }}
                  />
                </Grid>
                <Grid item sm={6} xs={12}>
                  {!this.props.checkView && <Button
                    size="small"
                    startIcon={<CloudDownloadIcon />}
                    className="btn btn-primary-d d-inline-flex"
                    component="span"
                    variant="contained"
                    color="primary"
                    onClick={this.handleExportExcel}
                  >
                     {t('general.button.download_sample_file')}
                  </Button>}
                </Grid>

                <Grid item sm={12} xs={12} >
                  {!this.props.checkView && (<ImportExcelDialog
                    changeSelectedFile={this.changeSelectedFile}
                    handleFileSelect={this.handleFileSelect}
                    handleSingleRemove={this.handleSingleRemove}
                    t={t}
                    file={this.state.file ? this.state.file : null}
                    isView={!this.state.id}
                  />)}

                </Grid>
              </Grid>

            </DialogContent>

          </div>

          <div className="dialog-footer">
            <DialogActions className="p-0">
              <div className="flex flex-space-between flex-middle">
                <Button
                  startIcon={<BlockIcon />}
                  variant="contained"
                  className="mr-12 btn btn-secondary d-inline-flex"
                  color="secondary"
                  onClick={() => handleClose()}
                >
                  {t("general.button.cancel")}
                </Button>
                <Button
                  startIcon={<SaveIcon />}
                  className="mr-0 btn btn-success d-inline-flex"
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

export default DataFileDialog;
