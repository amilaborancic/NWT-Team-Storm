import React, {useState} from "react";
import styles from "./index.module.css";
import NavbarContainer from "../../components/NavbarContainer/NavbarContainer";
import GenericModal from "../../components/GenericModal/GenericModal";
import GenericField from "../../components/FormFields/GenericField";
import {authenticatedApi, baseUrl} from "../../util/url";
import {routes} from "../../util/routes";

const Misplaced = ()=>{
    //KREIRANJE NOVOG KATALOGA
    const [isOpen, setIsOpen] = useState(false);
    const [newCatalogue, setNewCatalogue] = useState({
        naziv: ""
    })
    return(
        <>
            <button type="button" className="btn btn-success" onClick={()=>setIsOpen(true)}>Novi katalog</button>
            <GenericModal showModal={isOpen} closeModal={()=>setIsOpen(false)} modalTitle={"Vaš novi katalog"}
                          bottomText={"Dobro organizovan katalog olakšava pronalazak dobrih stripova :D"}>
                <GenericField type="text" label={"Naziv"} placeholder={"Dajte ime Vašem novom katalogu!"} name={"naziv"} onChange={(e)=>handleFieldChange(e, newCatalogue, setNewCatalogue)}/>
                <div className="d-flex w-100 justify-content-end">
                    <button type="button" className="btn btn-success" onClick={()=>createNewCatalogue(newCatalogue)}>Kreiraj</button>
                </div>
            </GenericModal>
        </>
    );
}

function handleFieldChange(e, catalogue, setCatalogue){
    const {name, value} = e.target;
    setCatalogue(prevState=>({
            ...prevState,
            [name]: value
        })
    )
}

function createNewCatalogue(newCatalogue){
    authenticatedApi.post(routes.katalozi.path + routes.katalozi.novi.path, newCatalogue)
        .then(res=>{
            console.log(res)
        })
        .catch(err=>{
            console.log(err);
        });
}

export default Misplaced;