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
import CD4BeforeArt from './TabPatientDialog/CD4BeforeArt';
import CD4DuringArt from './TabPatientDialog/CD4DuringArt';
import RecencyTest from './TabPatientDialog/RecencyTest'
import Hbv from './TabPatientDialog/Hbv';
import Hcv from './TabPatientDialog/Hcv';
import VL from './TabPatientDialog/VL';
import DrugResistance from './TabPatientDialog/DrugResistance';
import ArvTreatment from './TabPatientDialog/ArvTreatment';
import RiskPopulations from "./TabPatientDialog/RiskPopulations";
import TBTreatment from './TabPatientDialog/TBTreatment';
import TBProphylaxis from './TabPatientDialog/TBProphylaxis';
import Pregnancy from './TabPatientDialog/Pregnancy';
toast.configure({
    autoClose: 1000,
    draggable: false,
    limit: 3,
});
function MaterialButton(props) {
    const { t, i18n } = props;
    const item = props.item;
}

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

export default function CreateTab(props) {
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
                    <Tab label={t('Risk Population')}  {...a11yProps(0)} />
                    <Tab label={t('Recency Test')}  {...a11yProps(1)} />
                    <Tab label={t('CD4 Before ART')}  {...a11yProps(2)} />
                    <Tab label={t('CD4 During ART')}  {...a11yProps(3)} />
                    <Tab label={t('Viral load during ART')}  {...a11yProps(4)} />
                    <Tab label={t('Drug Resistance')}  {...a11yProps(5)} />
                    <Tab label={t('Arv Treatment')}  {...a11yProps(6)} />
                    <Tab label={t('TB Treatment')}  {...a11yProps(7)} />
                    <Tab label={t('TB Prophylaxis')}  {...a11yProps(8)} />
                    <Tab label={t('Hbv')}   {...a11yProps(9)} />
                    <Tab label={t('Hcv')}  {...a11yProps(10)} />
                    <Tab label={t('Pregnancy')}  {...a11yProps(11)} />
                </Tabs>
            </AppBar>
            <TabPanel value={value} index={0} style={{ height: "450px" }} color="#ffffff">
                <RiskPopulations t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            <TabPanel value={value} index={1} style={{ height: "450px" }} color="#ffffff">
                <RecencyTest t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>

            <TabPanel value={value} index={2} style={{ height: "450px" }} color="#ffffff">
                <CD4BeforeArt t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            <TabPanel value={value} index={3} style={{ height: "450px" }} color="#ffffff">
                <CD4DuringArt t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>

            <TabPanel value={value} index={4} style={{ height: "450px" }} color="#ffffff">
                <VL t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            <TabPanel value={value} index={5} style={{ height: "450px" }} color="#ffffff">
                <DrugResistance t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            <TabPanel value={value} index={6} style={{ height: "450px" }} color="#ffffff">
                <ArvTreatment t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            <TabPanel value={value} index={7} style={{ height: "450px" }} color="#ffffff">
                <TBTreatment t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            <TabPanel value={value} index={8} style={{ height: "450px" }} color="#ffffff">
                <TBProphylaxis t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            <TabPanel value={value} index={9} style={{ height: "450px" }} color="#ffffff">
                <Hbv t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            <TabPanel value={value} index={10} style={{ height: "450px" }} color="#ffffff">
                <Hcv t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>
            <TabPanel value={value} index={11} style={{ height: "450px" }} color="#ffffff">
                <Pregnancy t={t}
                    useStyles={useStyles}
                    item={props.item ? props.item : {}} />
            </TabPanel>

        </div>
    )
}
