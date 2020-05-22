import React from "react";
import styles from "./StripThumbnail.module.css";
import cx from "classnames";

const StripThumbnail = ({children, image, title, animated})=>{
    const classList = ["d-flex flex-column", animated ? styles.container : "", styles.wrapper];
    return(
        <div className={cx(classList)}>
            <img src={image} className={cx("my-3",styles.image)}/>
            <h4 className={styles.cursor}>{title}</h4>
            {children}
        </div>
    );
}

export default StripThumbnail;