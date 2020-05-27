import React from "react";
import Link from "next/link";
import styles from "./index.module.css";
const StripRating = ({naziv, id})=>{

    const routeToRating = "/rejtinzi/rejting/";
    return(
        <div className={styles.container}>
            <div className="card-body">
                <Link href={`${routeToRating}[idStrip]`} as={`${routeToRating}${id}`}>
                    Pregledaj rejtinge
                </Link>
            </div>
        </div>
    );
}

export default StripRating;