import React from 'react';
import {
  Dialog,
  DialogTitle,
  Icon,
  IconButton
} from "@material-ui/core";
// import Paper from '@material-ui/core/Paper'
import Draggable from "react-draggable";
import Paper from '@material-ui/core/Paper';
import {
  update,
  checkCode,
  addNew
} from "./DataSourceService";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import DataSourceForm from './DataSourceForm';
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

class DataSourceDialog extends React.Component {
  state = {
    id: "",
    name: "",
    code: "",
    description: "",
    channelUrl: "",
    type: "",

    shouldOpenNotificationPopup: false,
    Notification: "",
  };

  // handleDialogClose = () => {
  //   this.setState({ shouldOpenNotificationPopup: false });
  // };

  // handleChange = (event, source) => {
  //   event.persist();
  //   if (source === "switch") {
  //     this.setState({ isActive: event.target.checked });
  //     return;
  //   }
  //   this.setState({
  //     [event.target.name]: event.target.value,
  //   });
  // };

  // handleFormSubmit = () => {
  //   let { id } = this.state;
  //   let { code } = this.state;
  //   let { channelUrl } = this.state;
  //   var { t } = this.props;
  //   // checkCode(id, code).then((result) => {
  //   //   //Nếu trả về true là code đã được sử dụng
  //   //   if (result.data) {
  //   //     toast.warning(t("general.dupli_code"));
  //   //     // alert("Code đã được sử dụng");
  //   //   }
  //   //    else {
  //   //     //Nếu trả về false là code chưa sử dụng có thể dùng
  //   //     checkUrl(id, channelUrl).then((result) => {
  //   //       //Nếu trả về true là code đã được sử dụng
  //   //       if (result.data) {
  //   //         toast.warning(t("general.dupli_channelUrl"));
  //   //         // alert("Code đã được sử dụng");
  //   //       }
  //   //        else {
  //   //         //Nếu trả về false là code chưa sử dụng có thể dùng
  //   //         if (id) {
  //   //           update({
  //   //             ...this.state,
  //   //           }).then(() => {
  //   //             toast.success(t("general.updateSuccess"));
  //   //             this.props.handleOKEditClose();
  //   //           });
  //   //         } else {
  //   //             save({
  //   //             ...this.state,
  //   //           }).then(() => {
  //   //             toast.success(t("general.addSuccess"));
  //   //             this.props.handleOKEditClose();
  //   //           });
  //   //         }
  //   //       }
  //   //     });
  //   //   }
  //   // });



  //   if (id) {
  //     update({
  //       ...this.state,
  //     }).then(() => {
  //       toast.success(t("general.updateSuccess"));
  //       this.props.handleOKEditClose();
  //     });
  //   } else {
  //       save({
  //       ...this.state,
  //     }).then(() => {
  //       toast.success(t("general.addSuccess"));
  //       this.props.handleOKEditClose();
  //     });
  //   }
  // };


  // changeSelected = (value, type) => {
  //   if (type === 'sourceCode') {
  //     this.setState({ sourceCode: value,code:value.value }, () => {
  //     });
  //   }

  // }

  // componentWillMount() {
  //   //getUserById(this.props.uid).then(data => this.setState({ ...data.data }));
  //   let { open, handleClose, item } = this.props;
  //   this.setState({...item});
  //   if(item){
  //   if(Constants.DataSourceCode && Constants.DataSourceCode.length>0){
  //     Constants.DataSourceCode.forEach(e=>{
  //       if(e.value == item.code){
  //         this.setState({sourceCode:e})
  //       }
  //     })
  //   }
  // }
  // }
  componentDidMount() {
    let item = this.props.item;
    if (item) {
      this.setState({ ...item });
    }
  }

  handleFormSubmit = (values) => {
    let { t, handleClose } = this.props
    let { id } = this.state;
    var obj = {};
    obj.id = id;
    obj.code = values.code;
    obj.name = values.name;
    obj.channelUrl = values.channelUrl;
    obj.description = values.description;
    checkCode(id, values.code).then((result) => {
      if (result.data) {
        toast.warning(t('toast.duplicate_code'));
      } else {
        if (id) {
          update(obj).then(() => {
            handleClose();
            toast.success(t('toast.update_success'));
          });

        } else {
          addNew(obj).then(() => {
            handleClose();
            toast.success(t('toast.add_success'));
          });
        }
      }
    });
  };

  render() {
    let {
      id,
      name,
      code,
      description,
      channelUrl,
    } = this.state;
    let { open, handleClose, t } = this.props;
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
        <DataSourceForm
          initialValues={
            {
              code: code ? code : '',
              name: name ? name : '',
              description: description ? description : '',
              channelUrl: channelUrl ? channelUrl : ''
            }
          }
          handleSubmit={this.handleFormSubmit}
          handleClose={handleClose}
        />
      </Dialog>
    );
  }
}

export default DataSourceDialog;
