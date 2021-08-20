import React, { Component } from "react";
import {
  Dialog,
  Icon,
  DialogTitle,
  IconButton
} from "@material-ui/core";
import { checkCode, addNew, update } from "./HealthOrgService";
import Constant from './Constant';
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Draggable from 'react-draggable';
import Paper from '@material-ui/core/Paper';
import HealthOrgForm from './HealthOrgForm';
import {
  getUserOrganizationDtoByOrgId
} from '../User/UserService';

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
class HealthOrgEditorDialog extends Component {
  state = {
    name: "",
    code: "",
    description: "",
    isExternalOrg: false,
    isActive: false,
    shouldOpenSelectParentPopup: false,
  };

  handleFormSubmit = (values) => {
    let { t, handleClose, updatePageData } = this.props
    let { id } = this.state;

    let obj = {}
    obj.id = id;
    obj.code = values.code;
    obj.name = values.name;
    // obj.orgType = values.orgType;
    obj.checking = values.checking;
    obj.manager = values.manager;
    obj.confirmation = values.confirmation;
    obj.screening = values.screening;
    obj.treatment = values.treatment;

    obj.level = values.level;
    obj.parent = values.parent;
    obj.adminUnit = values.adminUnit;

    checkCode(id, obj.code).then((result) => {
      if (result.data) {
        toast.warning(t('toast.duplicate_code'));
      } else {
        if (id) {
          update(obj).then(() => {
            handleClose();
            updatePageData();
            toast.success(t('toast.update_success'));
          });

        } else {
          addNew(obj).then(() => {
            handleClose();
            updatePageData();
            toast.success(t('toast.add_success'));
          });
        }
      }
    });
  };

  componentDidMount() {
    let { item } = this.props;
    if (Constant.listOrgType != null && Constant.listOrgType.length > 0) {
      Constant.listOrgType.forEach(element => {
        if (element.value == item.orgType) {
          this.setState({ type: element });
        }
      });
    }
    if (Constant.listLevel != null && Constant.listLevel.length > 0) {
      Constant.listLevel.forEach(element => {
        if (element.value == item.level) {
          this.setState({ type: element });
        }
      });
    }
    this.setState(item);
    getUserOrganizationDtoByOrgId(item.id).then(({ data }) => {
      let itemList = [...data];
      this.setState({ itemList });
    })
  }

  // validateForm(value) {
  //   let whitespace = new RegExp(/[^\s]+/);
  //   if (!whitespace.test(value)) {
  //     return true
  //   }
  //   return false
  // }

  // componentDidMount() {
  //   ValidatorForm.addValidationRule('whitespace', (value) => {
  //     if (this.validateForm(value)) {
  //       return false;
  //     }
  //     return true;
  //   });
  // }

  // componentWillUnmount() {
  //   ValidatorForm.removeValidationRule('whitespace');
  //   this.props.updatePageData()
  // }
  // changeSelected = (event, value) => {
  //   console.log(value)
  //   this.setState({ type: value ? value : null, orgType: value ? value.value : null }, () => {
  //     console.log(this.state.orgType)
  //   })
  // }
  render() {
    let { open, handleClose, t, item } = this.props;
    let { id,
      name,
      code,
      orgType,
      level,
      parent,
      adminUnit,
      itemList,
      checking,
      treatment,
      manager,
      confirmation,
      screening
    } = this.state;
    console.log(this.state);

    return (
      <Dialog
        className="dialog-container"
        open={open}
        PaperComponent={PaperComponent}
        fullWidth
        maxWidth="md"
      >
        <DialogTitle
          className="dialog-header bgc-primary-d1"
          style={{ cursor: 'move' }}
          id="draggable-dialog-title"
        >
          <span className="mb-20 text-white" > {(id ? t('general.button.edit') : t('general.button.add')) + " " + t("health_org.title")} </span>
          <IconButton style={{ position: "absolute", right: "10px", top: "10px" }} onClick={() => handleClose()}>
            <Icon color="disabled" title={t('general.button.close')}>close</Icon>
          </IconButton>
        </DialogTitle>
        <HealthOrgForm
          initialValues={{
            code: code ? code : '',
            name: name ? name : '',
            // orgType: orgType ? orgType : '',
            checking: checking ? checking : false,
            treatment: treatment ? treatment : false,
            screening: screening ? screening : false,
            manager: manager ? manager : false,
            confirmation: confirmation ? confirmation : false,

            level: level ? level : '',
            parent: parent ? parent : null,
            adminUnit: adminUnit ? adminUnit : null
          }}
          handleSubmit={this.handleFormSubmit}
          handleClose={handleClose}
          itemList={itemList}
        />
      </Dialog >
    );
  }
}

export default HealthOrgEditorDialog;
