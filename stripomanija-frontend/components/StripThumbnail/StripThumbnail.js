import React from "react";
import styles from "./StripThumbnail.module.css";
import cx from "classnames";
import {routes} from "../../util/routes";
import Link from "next/link";

const StripThumbnail = ({children, image, title, animated, id})=>{
    const classList = ["d-flex flex-column", animated ? styles.container : "", styles.wrapper];
    return(
        <Link href={`${routes.strip.jedan.path}[id_strip]`} as={`${routes.strip.jedan.path}${id}`}>
            <div className={cx(classList)}>
                <img src={image} className={cx("my-3",styles.image)}/>
                <h5 className={styles.cursor}>{title}</h5>
                {children}
            </div>
        </Link>
    );
}

export default StripThumbnail;