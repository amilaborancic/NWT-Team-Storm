import React, {useState, useEffect} from "react";
import NavbarContainer from "../../components/NavbarContainer/NavbarContainer";
import styles from "./index.module.css";
import KatalogThumbnail from "../../components/KatalogThumbnail/KatalogThumbnail";
import {authenticatedApi, catalogue} from "../../util/url";
import {routes} from "../../util/routes";
import {useRouter} from "next/router";
import cx from "classnames";
import GenericField from "../../components/FormFields/GenericField";
import GenericModal from "../../components/GenericModal/GenericModal";
import ToastMessage from "../../components/ToastMessage/ToastMessage";

const Profile = ()=>{
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
        <NavbarContainer>
            <div className={cx("d-flex", styles.container)}>
                <UserDetails user={user}/>
                <Katalozi user={user}/>
            </div>
        </NavbarContainer>);
}

const UserDetails = ({user})=>{
    return(
        <div className={styles.infoContainer}>
            <p className={cx(styles.infoTitle)}>Info</p>
            <p className={styles.infoText}>Ime i prezime</p>
            <p className={styles.infoTextSmall}>{user.ime} {user.prezime}</p>
            <p className={styles.infoText}>Email</p>
            <p className={styles.infoTextSmall}>{user.email}</p>
            <p className={styles.infoText}>Ukupno {user.ukupno_reviewa} ostavljenih recenzija </p>
        </div>
    );
}

const Katalozi = ({user})=>{
    const router = useRouter();
    const [catalogueList, setCatalogueList] = useState(null);
    //creating a new catalogue
    const [isOpen, setIsOpen] = useState(false);
    const [newCatalogue, setNewCatalogue] = useState({
        naziv: ""
    })
    //validation message
    const [isToastOpen, setIsToastOpen] = useState(false);
    const [toast, setToast] = useState(null);

    useEffect(()=>{
        fetchCatalogueList(setCatalogueList);
    }, [catalogueList]);
    return(
        <div className={cx("d-flex flex-column w-75", styles.katalogContainer)}>
            <div className={cx(styles.wrapper, "justify-content-center")}>
                <p className={cx("d-flex justify-content-center align-items-center", styles.katTitle)}>Moji katalozi</p>
                <button type="button" className={cx("ml-4", styles.newButton)} onClick={()=>setIsOpen(true)}>+</button>
            </div>
            <div className={cx(styles.wrapper, "flex-wrap")}>
                {catalogueList && catalogueList.map(katalog=>
                    <div className="d-flex flex-column mx-3 my-2" key={katalog.id}>
                        <KatalogThumbnail animated title={katalog.naziv} id={katalog.id}/>
                        <button type="button" className="btn btn-primary" onClick={()=>deleteCatalogue(katalog, user, setIsToastOpen, setToast)}>Obriši</button>
                    </div>
                )}
            </div>
            <GenericModal showModal={isOpen} closeModal={()=>setIsOpen(false)} modalTitle={"Vaš novi katalog"}
                          bottomText={"Dobro organizovan katalog olakšava pronalazak dobrih stripova :D"}>
                <GenericField type="text" label={"Naziv"} placeholder={"Dajte ime Vašem novom katalogu!"} name={"naziv"} onChange={(e)=>handleFieldChange(e, newCatalogue, setNewCatalogue)}/>
                <div className="d-flex w-100 justify-content-end">
                    <button type="button" className="btn btn-success"
                            onClick={()=>createNewCatalogue(newCatalogue, setIsOpen, setIsToastOpen, setNewCatalogue, setToast)}>Kreiraj</button>
                </div>
            </GenericModal>
            {toast && <ToastMessage message={toast.message} type={toast.type} isOpen={isToastOpen} setIsOpen={setIsToastOpen}/>}
        </div>
    );
}

function deleteCatalogue(catalogue, user, setIsToastOpen, setToast){
    authenticatedApi.delete(routes.katalozi.path + routes.katalozi.brisi_katalog.path, {
        data:{
            idKorisnik: user.id,
            idKatalog: catalogue.id
        }
    })
        .then(res=>{
            setIsToastOpen(true);
            setToast({
                type:"success",
                message: "Uspješno ste obrisali katalog " + catalogue.naziv + "."
            })
        })
        .catch(err=>{
            setToast({
                type: "danger",
                message: "Došlo je do greške!"
            });
            setIsToastOpen(true);
        })
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

function createNewCatalogue(newCatalogue, setIsOpen, setIsToastOpen, setCatalogue, setToast){
    authenticatedApi.post(routes.katalozi.path + routes.katalozi.novi.path, newCatalogue)
        .then(res=>{
            console.log(res)
            setIsOpen(false);
            setIsToastOpen(true);
            setToast({
                type:"success",
                message:"Uspješno ste kreirali novi katalog!"
            })
            setCatalogue({
                naziv: ""
            });
        })
        .catch(err=>{
            console.log(err);
            setIsToastOpen(true);
            setToast({
                type:"danger",
                message:"Došlo je do greške!"
            })
        });
}


export default Profile;