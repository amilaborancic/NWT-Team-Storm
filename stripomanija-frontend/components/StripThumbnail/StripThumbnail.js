import React from "react";
import styles from "./StripThumbnail.module.css";
import cx from "classnames";

const StripThumbnail = ({children, image, title, animated})=>{
    const classList = ["d-flex flex-column", animated ? styles.container : ""];
    return(
        <div className={cx(classList)}>
            <div>
                <img src={image} className={cx("my-4",styles.image)}/>
                <h4 className={styles.cursor}>{title}</h4>
            </div>
            {children}
        </div>
    );
}

export default StripThumbnail;