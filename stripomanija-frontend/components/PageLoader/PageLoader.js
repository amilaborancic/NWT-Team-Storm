import styles from "./PageLoader.module.css";
import Spinner from "react-bootstrap/Spinner";
import React from "react";


const PageLoader = ()=>{
    return(
        <div className={styles.container}>
            <Spinner animation="border" variant="primary" role="status" />
        </div>
    );
}

export default PageLoader;