import React, {useState} from "react";
import styles from "./NavbarContainer.module.css";
import cx from "classnames";
import { useRouter } from 'next/router';
import {navbarRoutes} from "../../util/routes";

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
                        <li className="nav-item dropdown show">
                            <a className="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button"
                               onClick={()=>handleDropDownClick(setIsDropDownClicked, isDropDownClicked)}>Opcije</a>
                            <div className={cx("dropdown-menu", "dropdown", {"show":isDropDownClicked})}>
                                <a className="dropdown-item" href="#">Odjava</a>
                            </div>
                        </li>
                    </ul>
                    <form className="form-inline my-2 my-lg-0">
                        <input className="form-control mr-sm-2" type="text" placeholder="Pretraga"/>
                            <button className={cx("btn my-2 my-sm-0", styles.button)} type="submit">Traži!</button>
                    </form>
                </div>
            </nav>
            {children}
        </div>
    );
}

const handleDropDownClick = (setIsDropDownClicked, isDropDownClicked)=>{
    setIsDropDownClicked(!isDropDownClicked);
}

export default NavbarContainer;