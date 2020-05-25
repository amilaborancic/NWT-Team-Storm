import React, {useEffect, useState} from "react";
import NavbarContainer from "../../components/NavbarContainer/NavbarContainer";
import styles from "./index.module.css";
import KatalogThumbnail from "../../components/KatalogThumbnail/KatalogThumbnail";
import {authenticatedApi} from "../../util/url";
import {routes} from "../../util/routes";
import {useRouter} from "next/router";

const Katalozi = ()=>{
    const router = useRouter();
    const [catalogueList, setCatalogueList] = useState(null);
    useEffect(()=>{
        fetchCatalogueList(setCatalogueList);
    }, []);
    return(
        <NavbarContainer>
            <h1 className={styles.title}>Moji katalozi</h1>
            <div className={styles.wrapper}>
                {catalogueList && catalogueList.map(katalog=>
                    <div className={styles.katalog} key={katalog.id}>
                        <KatalogThumbnail animated title={katalog.naziv} id={katalog.id}/>
                    </div>
                )}
            </div>
        </NavbarContainer>);
}

function fetchCatalogueList(setCatalogueList){
    authenticatedApi.get(routes.katalozi.path + routes.katalozi.svi.path)
        .then(res=>{
            setCatalogueList(res.data);
        })
        .catch(err=>{
            console.log(err);
        });
}


export default Katalozi;