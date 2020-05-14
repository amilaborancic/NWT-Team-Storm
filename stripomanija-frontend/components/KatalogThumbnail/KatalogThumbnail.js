import React from "react";
import cx from "classnames";
import styles from "./KatalogThumbnail.module.css";
import {routes} from "../../util/routes";
import Link from "next/link";


const KatalogThumbnail = ({title, description, id})=>{
    const routeToCatalogue = routes.jedanKatalog.path;

    return(
        <Link href={`${routeToCatalogue}[idKatalog]`} as={`${routeToCatalogue}${id}`}>
            <div className={cx("card text-white mb-3", styles.container)}>
                <div className="card-body">
                    <h4 className="card-title">{title}</h4>
                    <p className="card-text">{description}</p>
                </div>
            </div>
        </Link>
    );
}

export default KatalogThumbnail;