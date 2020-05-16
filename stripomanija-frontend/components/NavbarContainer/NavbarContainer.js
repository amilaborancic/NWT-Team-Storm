import React, {useState} from "react";
import styles from "./NavbarContainer.module.css";
import cx from "classnames";
import { useRouter } from 'next/router';
import {navbarRoutes} from "../../util/routes";
import axios from "axios";

const NavbarContainer = ({children})=>{
    const router = useRouter();
    const [isDropDownClicked, setIsDropDownClicked] = useState(false);

    return(
        <div className={styles.container}>
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                <a className="navbar-brand">STRIPOMANIJA</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01"
                        aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
                </button>

                <div className="collapse navbar-collapse" id="navbarColor01">
                    <ul className="navbar-nav mr-auto">
                        {Object.values(navbarRoutes).map(route=>
                            <li className={cx("nav-item", {"active": router.pathname === route.path})} key={route.path}>
                                <a className="nav-link" href={route.path}>{route.label}</a>
                            </li>
                        )}
                    </ul>
                </div>
                <DropDownMenu isDropDownClicked={isDropDownClicked} setIsDropDownClicked={setIsDropDownClicked} />
            </nav>
            {children}
        </div>
    );
}


const DropDownMenu = ({setIsDropDownClicked, isDropDownClicked})=>{
    return(
        <div className={styles.dropdownContainer}>
            <a className={cx("nav-link dropdown-toggle show")} role="button"
               onClick={()=>handleDropDownClick(setIsDropDownClicked, isDropDownClicked)}>Opcije</a>
            <div className={cx("dropdown-menu", styles.customDropdown, {"show":isDropDownClicked})}>
                <a className={cx("dropdown-item")} >Odjava</a>
            </div>
        </div>
    );
}

const Dummy = ({setIsDropDownClicked,isDropDownClicked})=>{
    return(
        <div className={styles.dropdownContainer}>
            <button type="button" className={cx("btn dropdown-toggle ml-0")}/>
            <div className={cx("dropdown-menu", {"show": isDropDownClicked}, styles.customDropdown)}>
                <a className={cx("dropdown-item")} onClick={()=>handleDropDownClick(setIsDropDownClicked, isDropDownClicked)}>fdffff</a>)
            </div>
        </div>
    );
}

const handleDropDownClick = (setIsDropDownClicked, isDropDownClicked)=>{
    setIsDropDownClicked(!isDropDownClicked);
}


export default NavbarContainer;