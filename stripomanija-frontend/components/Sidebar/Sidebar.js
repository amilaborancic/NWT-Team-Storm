import React from "react";
import cx from "classnames";
import styles from "./Sidebar.module.css";
import {adminPanelRoutes} from "../../util/routes";
import Link from "next/link";
import {useRouter} from "next/router";

const Sidebar = ({children})=>{
    const router = useRouter();
    return(
        <div className={styles.wrapper}>
            <div className={cx("d-flex flex-column", styles.container)}>
                <h3 className="d-flex font-weight-bold text-white justify-content-center mt-4">ADMIN PANEL</h3>
                <div className="btn-group-vertical mt-3">
                    {Object.values(adminPanelRoutes).map(route=>
                        <Link href={`${route.path}`} as={`${route.path}`} key={route.label}>
                            <button type="button" className={cx("btn btn-primary btn-lg mt-2", {"active":route.path === router.pathname})}>{route.label}</button>
                        </Link>
                    )}
                </div>
            </div>
            <div className="d-flex flex-column w-100 p-4">
                <h1 className="d-flex justify-content-center display-3">STRIPOMANIJA</h1>
                <p className="d-flex justify-content-center lead">Dobrodošli na admin panel.</p>
                <div className="mt-2">{children}</div>
            </div>
        </div>
    );
}

export default Sidebar;