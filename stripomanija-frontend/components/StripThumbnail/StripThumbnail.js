import React from "react";
import styles from "./StripThumbnail.module.css";
import cx from "classnames";

const StripThumbnail = ({image, title})=>{
    return(
        <div className={cx("d-flex flex-column align-items-center justify-content-center", styles.container)}>
            <img src={image} className={cx("my-4",styles.image)} />
            <h4>{title}</h4>
        </div>
    );
}

export default StripThumbnail;