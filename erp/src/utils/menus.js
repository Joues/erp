import {getRequest} from "./api";

export const initMenu = (router, store) => {
    if (store.state.routes.length > 0) {
        return;
    }
    getRequest("/system/config/menu").then(data => {
        if (data) {
            let fmtRoutes = formatRoutes(data);
            router.addRoutes(fmtRoutes);
            store.commit('initRoutes', fmtRoutes);
            store.dispatch('connect');
        }
    })
}
export const formatRoutes = (routes) => {
    let fmRoutes = [];
    routes.forEach(router => {
        let {
            path,
            component,
            name,
            meta,
            iconCls,
            children
        } = router;
        if (children && children instanceof Array) {
            children = formatRoutes(children);
        }
        let fmRouter = {
            path: path,
            name: name,
            iconCls: iconCls,
            meta: meta,
            children: children,
            component(resolve) {
                if (component.startsWith("Home")) {
                    require(['../view/' + component + '.vue'], resolve);
                } else if (component.startsWith("Pur")) {
                    require(['../view/pur/' + component + '.vue'], resolve);
                } else if (component.startsWith("Mat")) {
                    require(['../view/mat/' + component + '.vue'], resolve);
                } else if (component.startsWith("Pro")) {
                    require(['../view/pro/' + component + '.vue'], resolve);
                } else if (component.startsWith("Che")) {
                    require(['../view/che/' + component + '.vue'], resolve);
                } else if (component.startsWith("Bas")) {
                    require(['../view/bas/' + component + '.vue'], resolve);
                } else if (component.startsWith("Sys")) {
                    require(['../view/sys/' + component + '.vue'], resolve);
                }
            }
        }
        fmRoutes.push(fmRouter);
    })
    return fmRoutes;
}
