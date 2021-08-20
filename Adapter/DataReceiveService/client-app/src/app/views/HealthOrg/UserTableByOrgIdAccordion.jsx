import React, { useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import {
    Grid,
    Accordion,
    AccordionDetails,
    AccordionSummary,
    Typography
} from "@material-ui/core";
import { ValidatorForm, TextValidator } from "react-material-ui-form-validator";
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import MaterialTable from 'material-table';

const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
    },
    container: {
        background: 'rgb(237, 245, 251)',
        boxShadow: '0 0.5rem 1rem rgb(0 0 0, 15%)'
    },
    details: {
        background: '#fff'
    },
    heading: {
        fontSize: theme.typography.pxToRem(16),
        flexBasis: '66.66%',
        flexShrink: 0,
    },
    secondaryHeading: {
        fontSize: theme.typography.pxToRem(15),
        color: theme.palette.text.secondary,
    },
}));

export default function ControlledAccordions(props) {
    const classes = useStyles();
    const [expanded, setExpanded] = React.useState(false);

    const handleChange = (panel) => (event, isExpanded) => {
        setExpanded(isExpanded ? panel : false);
    };

    let { t, itemList } = props;
    return (
        <Grid item md={12}>
            <div className={classes.root}>
                <Accordion className={classes.container} expanded={expanded === 'userTableByOrgIdPanel'} onChange={handleChange('userTableByOrgIdPanel')}>
                    <AccordionSummary
                        expandIcon={<ExpandMoreIcon />}
                        aria-controls="userTableByOrgIdPanelbh-content"
                        id="userTableByOrgIdPanelbh-header"
                    >
                        <Typography className={classes.heading}>{t("health_org.list_user")}</Typography>
                    </AccordionSummary>
                    {expanded && <AccordionDetails className={classes.details}>
                    <Grid item sm={12} xs={12}>
                    <MaterialTable
                        data={itemList}
                        columns={[
                            { title: t("user.username"), field: "user.username", width: "120", }, 
                            { title: t("user.display_name"), field: "user.person.displayName", width: "150", },
                            { title: t("user.email"), field: "user.email", align: "left", width: "150", },
                        ]}
                        options={{
                            selection: false,
                            actionsColumnIndex: -1,
                            paging: false,
                            search: false,
                            toolbar: false,
                            maxBodyHeight: "440px",
                            headerStyle: {
                                backgroundColor: "#2a80c8",
                                color: "#fff",
                            },
                            rowStyle: (rowData, index) => ({
                                backgroundColor:
                                index % 2 === 1 ? "rgb(237, 245, 251)" : "#FFF",
                            }),
                            tableLayout: 'fixed'
                        }}
                        onSelectionChange={(rows) => {
                        this.data = rows;
                        }}/>
                    </Grid>
                    </AccordionDetails>}
                </Accordion>
            </div>
        </Grid>

    );
}