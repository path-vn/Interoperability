import React, { Component } from "react";
import {
  Grid
} from "@material-ui/core";
import { checkCode, addNew, update, getOne } from "../HealthOrg/HealthOrgService";
import Constant from '../HealthOrg/Constant';
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { getCurrentUser, getAllOrgByUserId } from "./UserService";
import UnitForm from './UnitForm';
import {
  getUserOrganizationDtoByOrgId
} from '../User/UserService';
import { Breadcrumb } from "egret";

toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3,
});

class Unit extends Component {
  state = {
    name: "",
    code: "",
    description: "",
    parent: {},
    isExternalOrg: false,
    isActive: false,
    shouldOpenSelectParentPopup: false,
  };

  handleFormSubmit = (values) => {
    let { t } = this.props
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
    console.log(values);
    console.log(obj);
    checkCode(id, obj.code).then((result) => {
      if (result.data) {
        toast.warning(t('toast.duplicate_code'));
      } else {
        if (id) {
          update(obj).then(() => {
            toast.success(t('toast.update_success'));
            this.props.history.push('/dashboard/health_org');
          });

        } else {
          addNew(obj).then(() => { 
            toast.success(t('toast.add_success'));
          });
        }
      }
    });
  };

  componentWillMount() {
    getCurrentUser().then(({ data }) => {
      this.setState({userId: data.id}, () => {
        getAllOrgByUserId(this.state.userId).then(({data}) => {
          this.setState({ ...data.org }, () => {
            console.log(this.state);
          })
        })
      })
    })
  }

  componentDidMount() {
    if (Constant.listOrgType != null && Constant.listOrgType.length > 0) {
      Constant.listOrgType.forEach(element => {
        if (element.value === this.state.orgType) {
          this.setState({ type: element });
        }
      });
    }
    if (Constant.listLevel != null && Constant.listLevel.length > 0) {
      Constant.listLevel.forEach(element => {
        if (element.value === this.state.level) {
          this.setState({ type: element });
        }
      });
    }
    
  }
  render() {
    let { t } = this.props;
    let {
      name,
      code,
      orgType,
      level,
      parent,
      adminUnit,
      checking,
      treatment,
      manager,
      confirmation,
      screening
      // itemList
    } = this.state;

    return (
      <div className="m-sm-30">
        <div className="mb-sm-30">
          <Breadcrumb
            routeSegments={[
              { name: t("navigation.manage.org_info") }
            ]}
          />
        </div>
        <Grid container>
          <Grid item md={12}>
            <UnitForm
              initialValues={{
                code: code ? code : "",
                name: name ? name : "",
                // orgType: orgType ? orgType : "",
                level: level ? level : "",
                parent: parent ? parent : {},
                adminUnit: adminUnit ? adminUnit : {},
                checking: checking ? checking : false,
                treatment: treatment ? treatment : false,
                screening: screening ? screening : false,
                manager: manager ? manager : false,
                confirmation: confirmation ? confirmation : false,
              }}
              handleSubmit={this.handleFormSubmit}
              // itemList={itemList}
            />
          </Grid>
        </Grid>
      </div>
    );
  }
}

export default Unit;
