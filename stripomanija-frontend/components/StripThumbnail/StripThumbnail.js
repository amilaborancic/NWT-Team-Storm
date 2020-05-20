import React from "react";
import axios from "axios";
import styles from "./StripThumbnail.module.css";
import cx from "classnames";

const StripThumbnail = ({image, title})=>{
    return(
        <div className={"d-flex flex-column align-items-center justify-content-center"}>
            <img src={image} className={cx("my-4",styles.image)} />
            <h4>{title}</h4>
        </div>
    );
}

function fetchCoverImage(url){
    axios.get(url)
        .then(res=>{

        })
        .catch(err=>{
            console.log(error);
        });
}

export default StripThumbnail;