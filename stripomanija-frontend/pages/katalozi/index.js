import React from "react";
import NavbarContainer from "../../components/NavbarContainer/NavbarContainer";
import styles from "./index.module.css";
import KatalogThumbnail from "../../components/KatalogThumbnail/KatalogThumbnail";

const Katalozi = ()=>{
    const listaKataloga = [{id:1, naziv:"Captain America"}, {id:2, naziv:"Procitani stripovi"}, {id:3, naziv: "Želim procitati"}];
    return(
        <NavbarContainer>
            <h1 className={styles.title}>Moji katalozi</h1>
            <div className={styles.wrapper}>
                {listaKataloga.map(katalog=>
                    <div className={styles.katalog} key={katalog.id}>
                        <KatalogThumbnail animated title={katalog.naziv} id={katalog.id}/>
                    </div>
                )}
            </div>
        </NavbarContainer>);
}

export default Katalozi;