import React from "react";
import NavbarContainer from "../../components/NavbarContainer/NavbarContainer";
import styles from "./index.module.css";
import KatalogThumbnail from "../../components/KatalogThumbnail/KatalogThumbnail";

const Katalozi = ()=>{
    const listaKataloga = [{id:1, naziv:"Test", brojStripova:5}, {id:2, naziv:"lsnlk", brojStripova:1}, {id:3, naziv: "Gluposti", brojStripova:4}];
    const listaBoja = ["danger", "info", "dark", "primary", "success", "warning"];
    return(
        <NavbarContainer>
            <h1 className={styles.title}>Moji katalozi</h1>
            <div className={styles.wrapper}>
                {listaKataloga.map(katalog=>
                    <div className={styles.katalog}>
                        <KatalogThumbnail title={katalog.naziv} color={listaBoja[Math.floor(Math.random() * listaBoja.length)]} key={katalog.id}
                                          description={"Ovaj katalog sadrži " + katalog.brojStripova + " stripova."} />
                    </div>
                )}
            </div>
        </NavbarContainer>);
}

export default Katalozi;