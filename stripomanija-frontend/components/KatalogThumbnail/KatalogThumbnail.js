import React from "react";
import cx from "classnames";
import styles from "./KatalogThumbnail.module.css";
import {routes} from "../../util/routes";
import Link from "next/link";


const KatalogThumbnail = ({title, description, id, animated})=>{
    const routeToCatalogue = routes.jedanKatalog.path;
    const cssList = ["card text-white mb-3", animated ? styles.container : "", styles.wrapper];

    return(
        <Link href={`${routeToCatalogue}[idKatalog]`} as={`${routeToCatalogue}${id}`}>
            <div className={cx(cssList)}>
                <div className="card-body">
                    <h4 className="card-title">{title}</h4>
                    <p className="card-text">{description}</p>
                </div>
            </div>
        </Link>
    );
}

export default KatalogThumbnail;