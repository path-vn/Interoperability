import ConstantList from "./appConfig";
export const navigations = [{
        name: "Dashboard.dashboard",
        icon: "home",
        path: ConstantList.ROOT_PATH + "dashboard/analytics",
        isVisible: true
    },
    {
        name: "DeIdentification Config",
        icon: "category",
        path: ConstantList.ROOT_PATH + "dashboard/deIdentification-config",
        isVisible: true
    },
    {
        name: "FHIR Patient",
        isVisible: true,
        path: ConstantList.ROOT_PATH + "fhir/FhirPatient",
        icon: "keyboard_arrow_right"
    },
    {
        name: "ES Patient",
        isVisible: true,
        path: ConstantList.ROOT_PATH + "fhir/EsPatient",
        icon: "keyboard_arrow_right"
    },
    {
        name: "navigation.manage.title",
        isVisible: true,
        icon: "engineering",
        children: [{
                name: "navigation.manage.user",
                isVisible: true,
                path: ConstantList.ROOT_PATH + "user_manager/user",
                icon: "keyboard_arrow_right"
            },
            // {
            //     name: "navigation.manage.menu",
            //     isVisible: true,
            //     path: ConstantList.ROOT_PATH + "list/menu",
            //     icon: "keyboard_arrow_right"
            // }
        ]
    }
];