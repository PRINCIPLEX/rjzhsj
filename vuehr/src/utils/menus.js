import {getRequest} from "./api";

export const initMenu = (router, store) => {
    /*(store.state.routes.length > 0) {
        return;
    }*/
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
                    require(['../views/' + component + '.vue'], resolve);
                } else if (component.startsWith("Emp")) {
                    require(['../views/emp/' + component + '.vue'], resolve);
                } else if (component.startsWith("Per")) {
                    require(['../views/per/' + component + '.vue'], resolve);
                } else if (component.startsWith("Sal")) {
                    require(['../views/sal/' + component + '.vue'], resolve);
                } else if (component.startsWith("Sta")) {
                    require(['../views/sta/' + component + '.vue'], resolve);
                } else if (component.startsWith("Sys")) {
                    require(['../views/sys/' + component + '.vue'], resolve);
                }
				else if (component.startsWith("Map")) {
				    require(['../views/map/' + component + '.vue'], resolve);
				}
				else if (component.startsWith("Learn")) {
				    require(['../views/learn/' + component + '.vue'], resolve);
				}
				else if (component.startsWith("Experience")) {
				    require(['../views/learn/' + component + '.vue'], resolve);
				}
				else if (component.startsWith("TimeManage")) {
				    require(['../views/learn/' + component + '.vue'], resolve);
				}
				else if (component.startsWith("MaxMap")) {
				    require(['../views/map/' + component + '.vue'], resolve);
				}
                else if (component.startsWith("User")) {
                    require(['../views/user/' + component + '.vue'], resolve);
                }
                else if (component.startsWith("AddMap")) {
                    require(['../views/map/' + component + '.vue'], resolve);
                }
                else if (component.startsWith("olMap")) {
                    require(['../views/map/' + component + '.vue'], resolve);
                }
            }
        }
        fmRoutes.push(fmRouter);
    })
    return fmRoutes;
}