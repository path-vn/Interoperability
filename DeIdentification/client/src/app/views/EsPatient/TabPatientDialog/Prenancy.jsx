import React, { Component } from "react";
import moment from "moment";
import MaterialTable from "material-table";
import {
    Grid,
    IconButton,
    Icon,
    Button,
    Collapse
} from "@material-ui/core";
import ChildDialog from "./ChildDialog";
import { withStyles } from "@material-ui/core/styles";
import Tooltip from "@material-ui/core/Tooltip";
const LightTooltip = withStyles((theme) => ({
    tooltip: {
      backgroundColor: theme.palette.common.white,
      color: "rgba(0, 0, 0, 0.87)",
      boxShadow: theme.shadows[1],
      fontSize: 14,
      position: "absolute",
      top: "-10px",
      left: "-25px",
      width: "80px",
    },
  }))(Tooltip);
function MaterialButton(props) {
    const item = props.item;
    return <div>
       <LightTooltip
      title={"Chi tiết đứa trẻ"}
      placement="right-end"
      enterDelay={500}
      leaveDelay={300}
    >
      <IconButton onClick={() => props.onSelect(item, 0)}>
        <Icon color="primary">visibility</Icon>
      </IconButton>
    </LightTooltip>
    </div>;
}


class Prenancy extends Component {
    state = {
        shouldOpenEditorDialog: false
    };

    handleViewItem = item => {
        this.setState({
            ...this.state,
            child: item,
            shouldOpenEditorDialog: true
        });
    };

    handleClose = () => {
        this.setState({
            shouldOpenEditorDialog: false,
        });
    };

    componentWillMount() {
        let { item } = this.props;
        console.log(item);
        if (item == null) {
            item = {}
        }
        this.setState({ item: item });

    }

    render() {

        let { t } = this.props;
        let { shouldOpenEditorDialog,item } = this.state;
        console.log(item)
        let columns = [
            {
                title: t("general.action"),
                field: "custom",
                cellStyle: (data, rowData) => ({
                    backgroundColor: "#fff",
                    position: 'sticky',
                    left: 0,
                }),
                headerStyle: {
                    position: 'sticky',
                    left: 0,
                    zIndex: 11
                },
                render: rowData =>
                    <MaterialButton item={rowData} t={t}
                        onSelect={(rowData, method) => {
                            if (method === 0) {
                                this.handleViewItem(rowData.childs);
                            } else {
                                alert('Call Selected Here:' + rowData.id);
                            }
                        }}
                    />
            },
            {
                title: t("patient.tab.no"),
                field: "Stt",
                render: (rowData) =>
                  rowData ? (<span>{rowData.tableData.id + 1}</span>) : ("")
              },
            // {
            //     title: t("prenancy.tab.no"),
            //     field: "Stt",
            //     render: (rowData) =>
            //         rowData ? (<span>{rowData.tableData.id + 1}</span>) : ("")
            // },

            {
                title: t("prenancy.tab.date_report"),
                field: "dateReported",
                render: (rowData) =>
                    rowData.dateReported ? (<span>{moment(rowData.dateReported).format("DD/MM/YYYY")}</span>) : ("")
            },
            {
                title: t("prenancy.tab.delivery_place"),
                field: "deliveryPlace.name",
            },
            {
                title: t("prenancy.tab.place_reported"),
                field: "placeReported.name",
            },
            {
                title: t("prenancy.tab.outcome_code"),
                field: "outcomeCode.display",
            },
            {
                title: t("prenancy.tab.delivery_date"),
                field: "deliveryDate",
                render: (rowData) =>
                    rowData.deliveryDate ? rowData.deliveryDate[0] ?( (<span>{moment(rowData.deliveryDate[0]).format("DD/MM/YYYY")}</span>) ): (""):("")
            },
            {
                title: t("prenancy.tab.gestational"),
                field: "gestationalAgeAtDelivery",
                render: (rowData) =>
                    rowData.gestationalAgeAtDelivery ? (rowData.gestationalAgeAtDelivery[0] ?<span> {rowData.gestationalAgeAtDelivery[0]}</span>:  ("")):  ("")
            },
        ];

        return (
            <React.Fragment>
                {shouldOpenEditorDialog && (
                    <ChildDialog t={t}
                        handleClose={this.handleClose}
                        open={shouldOpenEditorDialog}
                        childList={this.state.child}
                    />
                )}
                <MaterialTable
                    data={item ? (item.listPregnancy ? item.listPregnancy : []) : []}
                    columns={columns}
                    options={{
                        selection: false,
                        actionsColumnIndex: -1,
                        paging: false,
                        search: false,
                        toolbar: false,
                        maxBodyHeight: "200px",
                        headerStyle: {
                            backgroundColor: "#2a80c8",
                            color: "#fff",
                        },
                        rowStyle: (rowData, index) => ({
                            backgroundColor: index % 2 === 1 ? 'rgb(237, 245, 251)' : '#FFF',
                        }),

                    }}
                    onSelectionChange={(rows) => {
                        this.data = rows;
                    }}
                    localization={{
                        body: {
                            emptyDataSourceMessage: `${t(
                                "general.emptyDataMessageTable"
                            )}`,
                        },
                    }}
                />
            </React.Fragment>
        );
    }
}

export default Prenancy;
