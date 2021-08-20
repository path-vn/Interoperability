import ConstantList from "./appConfig";

export const navigations = [{
        name: "navigation.dashboard",
        icon: "dashboard",
        path: ConstantList.ROOT_PATH + "dashboard/analytics",
        isVisible: true,
    },

    {
        name: "navigation.directory",
        icon: "dashboard",
        path: "",
        isVisible: true,
        children: [{
                name: "navigation.administrative_unit",
                icon: "account_balance",
                path: ConstantList.ROOT_PATH + "administrative-unit",
                isVisible: true,
            }, {
                name: "navigation.health_unit",
                path: ConstantList.ROOT_PATH + "dashboard/health_org",
                icon: "public",
                isVisible: true,
            },
            {
                name: "navigation.ethnicity",
                path: ConstantList.ROOT_PATH + "fhir/Ethnicity",
                icon: "group",
                isVisible: true,
            },
            {
                name: "Báo cáo trường hợp HIV",
                path: ConstantList.ROOT_PATH + "hiv-case-report",
                icon: "feed",
                isVisible: true,
            },
        ]
    },
    {
        name: "navigation.manage.profile",
        isVisible: true,
        icon: "engineering",
        children: [{
                name: "navigation.manage.personal_info",
                isVisible: true,
                path: ConstantList.ROOT_PATH + "user-profile",
                icon: "remove",
            },
            {
                name: "navigation.manage.change_password",
                isVisible: true,
                path: ConstantList.ROOT_PATH + "user-change-password",
                icon: "remove",
            },
            {
                name: "navigation.manage.list_user",
                isVisible: true,
                path: ConstantList.ROOT_PATH + "user-manage",
                icon: "remove",
            },
            {
                name: "navigation.manage.org_info",
                isVisible: true,
                path: ConstantList.ROOT_PATH + "unit-information",
                icon: "remove",
            },
        ]
    },

];