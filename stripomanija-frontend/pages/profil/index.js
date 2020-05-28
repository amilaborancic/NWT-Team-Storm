import React, {useState, useEffect} from "react";
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

const Profile = ()=>{
    return(
        <NavbarContainer>
            <div className="d-flex">
                <UserDetails/>
                <Katalozi/>
            </div>
        </NavbarContainer>);
}

const UserDetails = ()=>{
    const [user, setUser] = useState({
        id: null,
        role: {
            id:null,
            roleName: ""
        },
        ime: "",
        prezime: "",
        email: "",
        ukupno_reviewa: null
    });
    useEffect(()=>{
        fetchUserDetails(setUser);
    }, []);

    return(
        <div className={cx("flex-column w-25 text-left px-4 py-4", styles.infoContainer)}>
            <h1>Info</h1>
            <h4 className="mt-4">Ime i prezime</h4>
            <h1 className="mt-3 ml-3">{user.ime} {user.prezime}</h1>
            <h4 className="mt-4">Email</h4>
            <h3 className="mt-3 ml-3">{user.email}</h3>
            <h4 className="mt-4">Ukupno {user.ukupno_reviewa} ostavljenih recenzija </h4>
        </div>
    );
}

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
        <div className={cx("d-flex flex-column w-75", styles.katalogContainer)}>
            <div className={cx(styles.wrapper, "justify-content-center align-items-center")}>
                <h1 className="d-flex justify-content-center align-items-center">Moji katalozi</h1>
                <button type="button" className={cx("ml-4", styles.newButton)} onClick={()=>setIsOpen(true)}>+</button>
            </div>
            <div className={cx(styles.wrapper, "flex-wrap")}>
                {catalogueList && catalogueList.map(katalog=>
                    <div className="mx-3 my-2" key={katalog.id}>
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
        </div>
    );
}

function fetchUserDetails(setUser){
    authenticatedApi(routes.user.path + routes.user.details.path + localStorage.getItem("username"))
        .then(res=>{
            console.log(res.data);
            setUser(res.data);
        })
        .catch(err=>{console.log(err)});
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


export default Profile;