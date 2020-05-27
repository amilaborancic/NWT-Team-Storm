import React, {useEffect, useState} from "react";
import NavbarContainer from "../../components/NavbarContainer/NavbarContainer";
import styles from "./index.module.css";
import KatalogThumbnail from "../../components/KatalogThumbnail/KatalogThumbnail";
import {authenticatedApi} from "../../util/url";
import {routes} from "../../util/routes";
import {useRouter} from "next/router";
import cx from "classnames";
import GenericField from "../../components/FormFields/GenericField";
import GenericModal from "../../components/GenericModal/GenericModal";
import ToastMessage from "../../components/ToastMessage/ToastMessage";

const Katalozi = ()=>{
    const router = useRouter();
    const [catalogueList, setCatalogueList] = useState(null);
    //creating a new catalogue
    const [isOpen, setIsOpen] = useState(false);
    const [newCatalogue, setNewCatalogue] = useState({
        naziv: ""
    })
    //validation message
    const [isToastOpen, setIsToastOpen] = useState(false);


    useEffect(()=>{
        fetchCatalogueList(setCatalogueList);
    }, [catalogueList]);
    return(
        <NavbarContainer>
            <>
                <div className={cx(styles.wrapper, "justify-content-center align-items-center")}>
                    <h1 className={styles.title}>Moji katalozi</h1>
                    <button type="button" className={cx("btn btn-success ml-4", styles.newButton)} onClick={()=>setIsOpen(true)}>Novi</button>
                </div>
                <div className={cx(styles.wrapper, "flex-wrap")}>
                    {catalogueList && catalogueList.map(katalog=>
                        <div className="mx-2" key={katalog.id}>
                            <KatalogThumbnail animated title={katalog.naziv} id={katalog.id}/>
                        </div>
                    )}
                </div>
                <GenericModal showModal={isOpen} closeModal={()=>setIsOpen(false)} modalTitle={"Vaš novi katalog"}
                              bottomText={"Dobro organizovan katalog olakšava pronalazak dobrih stripova :D"}>
                    <GenericField type="text" label={"Naziv"} placeholder={"Dajte ime Vašem novom katalogu!"} name={"naziv"} onChange={(e)=>handleFieldChange(e, newCatalogue, setNewCatalogue)}/>
                    <div className="d-flex w-100 justify-content-end">
                        <button type="button" className="btn btn-success" onClick={()=>createNewCatalogue(newCatalogue, setIsOpen, setIsToastOpen, setNewCatalogue)}>Kreiraj</button>
                    </div>
                </GenericModal>
                <ToastMessage title={"Potvrda"} message={"Uspješno ste kreirali novi katalog!"} type={"success"} isOpen={isToastOpen} setIsOpen={setIsToastOpen}/>
            </>
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


function handleFieldChange(e, catalogue, setCatalogue){
    const {name, value} = e.target;
    setCatalogue(prevState=>({
            ...prevState,
            [name]: value
        })
    )
}

function createNewCatalogue(newCatalogue, setIsOpen, setIsToastOpen, setCatalogue){
    authenticatedApi.post(routes.katalozi.path + routes.katalozi.novi.path, newCatalogue)
        .then(res=>{
            console.log(res)
            setIsOpen(false);
            setIsToastOpen(true);
            setCatalogue({
                naziv: ""
            });

        })
        .catch(err=>{
            console.log(err);
        });
}


export default Katalozi;