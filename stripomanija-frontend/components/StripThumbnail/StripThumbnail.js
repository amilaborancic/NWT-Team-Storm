import React from "react";
import styles from "./StripThumbnail.module.css";
import cx from "classnames";
import {routes} from "../../util/routes";
import Link from "next/link";

const StripThumbnail = ({children, image, title, animated, id})=>{
    const privileged = isUserPriviledged();
    return(
        privileged ?
            <Link href={`${routes.strip.jedan.path}[id_strip]`} as={`${routes.strip.jedan.path}${id}`}>
                <div className={cx(["d-flex flex-column", animated ? styles.container : "", styles.wrapper])}>
                    <img src={image} className={cx("my-3",styles.image)}/>
                    <h5 className={styles.cursor}>{title}</h5>
                    {children}
                </div>
            </Link>
            :
            <ThumbnailBody animated={animated} image={image} title={title}>{children}</ThumbnailBody>
    );
}

const ThumbnailBody = ({animated, image, title, children})=>{
    return(
        <div className={cx(["d-flex flex-column", animated ? styles.container : "", styles.wrapper])}>
            <img src={image} className={cx("my-3",styles.image)}/>
            <h5 className={styles.cursor}>{title}</h5>
            {children}
        </div>
    );
}

const isUserPriviledged = ()=>{
    return localStorage.getItem("role") === "ROLE_USER";
}

export default StripThumbnail;