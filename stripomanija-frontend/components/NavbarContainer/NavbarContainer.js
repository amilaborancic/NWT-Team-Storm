import React, {useState} from "react";
import styles from "./NavbarContainer.module.css";
import cx from "classnames";
import { useRouter } from 'next/router';
import {navbarRoutes, routes} from "../../util/routes";

const NavbarContainer = ({children})=>{
    const router = useRouter();
    const [isDropDownClicked, setIsDropDownClicked] = useState(false);
    const [isOptionMenuClicked, setIsOptionMenuClicked] = useState(false);

    return(
        <div className={styles.container}>
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary" >
                <a className="navbar-brand">STRIPOMANIJA</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation" onClick={()=>setIsOptionMenuClicked(!isOptionMenuClicked)}>
                    <span className="navbar-toggler-icon"/>
                </button>
                <div className={cx("collapse navbar-collapse", {"show":isOptionMenuClicked})} id="navbarSupportedContent">
                    <ul className="navbar-nav mr-auto">
                        {Object.values(navbarRoutes).map(route=>
                            <li className={cx("nav-item", {"active": router.pathname === route.path})} key={route.path}>
                                <a className="nav-link" href={route.path}>{route.label}</a>
                            </li>
                        )}
                    </ul>
                    <DropDownMenu isDropDownClicked={isDropDownClicked} setIsDropDownClicked={setIsDropDownClicked} router={router}/>
                </div>
            </nav>
            {children}
        </div>
    );
}


const DropDownMenu = ({setIsDropDownClicked, isDropDownClicked, router})=>{
    return(
        <div className={styles.dropdownContainer}>
            <a className={cx("nav-link dropdown-toggle show")} role="button"
               onClick={()=>handleDropDownClick(setIsDropDownClicked, isDropDownClicked)}>Opcije</a>
            <div className={cx("dropdown-menu", styles.customDropdown, {"show":isDropDownClicked})}>
                <a className={cx("dropdown-item")} onClick={()=>logOut(router)}>Odjava</a>
            </div>
        </div>
    );
}

export const logOut = (router)=>{
    localStorage.removeItem("jwt");
    localStorage.removeItem("role");
    localStorage.removeItem("username");
    //navigate to homepage
    router.push(routes.home.path);
}

const handleDropDownClick = (setIsDropDownClicked, isDropDownClicked)=>{
    setIsDropDownClicked(!isDropDownClicked);
}


export default NavbarContainer;