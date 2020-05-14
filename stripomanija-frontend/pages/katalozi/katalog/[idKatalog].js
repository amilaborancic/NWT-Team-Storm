import React from "react";
import {useRouter} from "next/router";
import NavbarContainer from "../../../components/NavbarContainer/NavbarContainer";
import styles from "./index.module.css";
import cx from "classnames";
import Pagination from "../../../components/Pagination/Pagination";

const KatalogDetails = ()=>{
    const router = useRouter();
    const { idKatalog } = router.query;

    return(
        <NavbarContainer>
            <div>id sa rute: {idKatalog}</div>
            <div className="d-flex fixed-bottom justify-content-center">
                <Pagination numberOfPages={5}/>
            </div>
        </NavbarContainer>
    );
}

export default KatalogDetails;