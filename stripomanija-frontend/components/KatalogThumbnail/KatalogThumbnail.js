import React from "react";
import cx from "classnames";
import styles from "./KatalogThumbnail.module.css";

const KatalogThumbnail = ({title, color, description})=>{
    return(
        <div className={cx("card text-white mb-3", styles.container, `bg-${color}`)} >
            <div className="card-body">
                <h4 className="card-title">{title}</h4>
                <p className="card-text">{description}</p>
            </div>
        </div>
    );
}

export default KatalogThumbnail;