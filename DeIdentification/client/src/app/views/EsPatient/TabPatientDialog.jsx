import React from "react";
import PropTypes from "prop-types";
import { makeStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import CD4 from './TabPatientDialog/CD4';
import HbvCoInfection from './TabPatientDialog/HbvCoInfection';
import HcvCoInfection from './TabPatientDialog/HcvCoInfection';
import ArvTreatment from './TabPatientDialog/ArvTreatment';
import Regimen from './TabPatientDialog/Regimen';
import TbPrevention from './TabPatientDialog/TbPrevention';
import PreventOpportunisticInfections from './TabPatientDialog/PreventOpportunisticInfections';
import TransferTreatment from './TabPatientDialog/TransferTreatment'
import TuberculosisCoInfection from './TabPatientDialog/TuberculosisCoInfection';
import VL from './TabPatientDialog/VL';
import RiskPopulations from "./TabPatientDialog/RiskPopulations";
import RickBehaviors from "./TabPatientDialog/RiskBehaviors";
import  TransmissionRoutes from "./TabPatientDialog/TransmissionRoutes";
import Prenancy from "./TabPatientDialog/Prenancy";
toast.configure({
    autoClose: 1000,
    draggable: false,
    limit: 3,
});

function TabPanel(props) {
    const { children, value, index, ...other } = props;

    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`scrollable-auto-tabpanel-${index}`}
            aria-labelledby={`scrollable-auto-tab-${index}`}
            {...other}
        >
            {value === index && (
                <Box p={3}>
                    <Typography>{children}</Typography>
                </Box>
            )}
        </div>
    );
}

TabPanel.propTypes = {
    children: PropTypes.node,
    index: PropTypes.any.isRequired,
    value: PropTypes.any.isRequired,
};

function a11yProps(index) {
    return {
        id: `scrollable-auto-tab-${index}`,
        "aria-controls": `scrollable-auto-tabpanel-${index}`,
    };
}

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
        width: "100%",
        backgroundColor: theme.palette.background.paper,
    },
}));

export default function CreateStaff(props) {
    const t = props.t
    const i18n = props.i18n
    const classes = useStyles();
    const [value, setValue] = React.useState(0);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    return (

        <div className={classes.root} value={value} index={0} >
            <AppBar position="static" color="#ffffff">
                <Tabs
                    value={value}
                    onChange={handleChange}
                    indicatorColor="primary"
                    textColor="primary"
                    variant="scrollable"
                    scrollButtons="auto"
                    aria-label="scrollable auto tabs example"
                >
                    <Tab label={t('patient.tab.cd4_during')}  {...a11yProps(0)} />
                    <Tab label={t('patient.tab.vl')}  {...a11yProps(1)} />
                    <Tab label={t('patient.tab.regimen')}  {...a11yProps(2)} />{/*Phác đồ điều trị*/}
                    {/* <Tab label={t('patient.tab.risk_population')}  {...a11yProps(3)} /> 
                    <Tab label={t('patient.tab.risk_behavior')}  {...a11yProps(4)} /> 
                    <Tab label={t('patient.tab.transmission_route')}  {...a11yProps(5)} />  */}
                    <Tab label={t('patient.tab.tb_treatment')}  {...a11yProps(3)} /> {/*Đồng nhiễm Lao*/}
                    <Tab label={t('patient.tab.hbv')}  {...a11yProps(4)} /> {/*Đồng nhiễm HBV*/}
                    <Tab label={t('patient.tab.hcv')}   {...a11yProps(5)} /> {/*Đồng nhiễm HCV*/}
                    <Tab label={t('patient.tab.tbp')}  {...a11yProps(6)} /> {/*Dự phòng bệnh Lao*/}
                    <Tab label={t('patient.tab.arv_treatment')}  {...a11yProps(7)} /> 
                    {/* <Tab label={t('patient.tab.transfer_treatment')}  {...a11yProps(11)} />  */}
                    <Tab label={t('patient.tab.drug_resistance')}  {...a11yProps(8)} /> 
                    <Tab label={t('patient.tab.prenancy')}  {...a11yProps(9)} /> 
                </Tabs>
            </AppBar>
            <TabPanel value={value} index={0} color="#ffffff">
                <CD4 t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            <TabPanel value={value} index={1} color="#ffffff">
                <VL t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            <TabPanel value={value} index={2} color="#ffffff">
                <Regimen t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>

            {/* <TabPanel value={value} index={3} color="#ffffff">
                <RiskPopulations t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel> */}
            {/* <TabPanel value={value} index={4} color="#ffffff">
                <RickBehaviors t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel> */}
            {/* <TabPanel value={value} index={5} color="#ffffff">
                <TransmissionRoutes t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel> */}
            <TabPanel value={value} index={3} color="#ffffff">
                <TuberculosisCoInfection t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            <TabPanel value={value} index={4} color="#ffffff">
                <HbvCoInfection t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            <TabPanel value={value} index={5} color="#ffffff">
                <HcvCoInfection t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            <TabPanel value={value} index={6} color="#ffffff">
                <TbPrevention t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            <TabPanel value={value} index={7} color="#ffffff">
                <ArvTreatment t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            {/* <TabPanel value={value} index={11} color="#ffffff">
                <TransferTreatment t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel> */}
            <TabPanel value={value} index={8} color="#ffffff">
                <PreventOpportunisticInfections t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            <TabPanel value={value} index={9} color="#ffffff">
                <Prenancy t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
        </div>
    )
}
