import Head from 'next/head';
import styles from "./index.module.css";
import cx from "classnames";
import GenericModal from "../components/GenericModal/GenericModal";
import React, {useEffect, useState} from "react";
import GenericField from "../components/FormFields/GenericField";
import axios from "axios";
import {baseUrl} from "../util/url";
import {navbarRoutes, routes} from "../util/routes";
import Router from 'next/router';
import {useWindowDimensions} from "../components/hooks/useWindowDimensions";
import ToastMessage from "../components/ToastMessage/ToastMessage";

export default function Home() {
    const [isRegisterModalOpen, setIsRegisterModalOpen] = useState(false);
    const [isLoginModalOpen, setIsLoginModalOpen] = useState(false);
    const [windowDimensions, setWindowDimensions] = useState({});
    useWindowDimensions(windowDimensions, setWindowDimensions);
    const [toast, setToast] = useState({});
    const [isToastOpen, setIsToastOpen] = useState(false);

    return (
        <>
            {isRegisterModalOpen && <RegistrationModal isRegisterModalOpen={isRegisterModalOpen} setIsRegisterModalOpen={setIsRegisterModalOpen}/>}
            {isLoginModalOpen && <LoginModal setToast={setToast} setIsToastOpen={setIsToastOpen} isLoginModalOpen={isLoginModalOpen} setIsLoginModalOpen={setIsLoginModalOpen}/>}
            <div className={styles.container}>
                <Head>
                    <title>Stripomanija</title>
                    <link rel="icon" href="/favicon.ico" />
                </Head>
                <div className={styles.upperSection}>
                    <div className={styles.title}>STRIPOMANIJA</div>
                    {windowDimensions.width > 1200 && <Buttons setIsRegisterModalOpen={setIsRegisterModalOpen} setIsLoginModalOpen={setIsLoginModalOpen} />}
                </div>
                {windowDimensions.width <= 1199 && <Buttons setIsRegisterModalOpen={setIsRegisterModalOpen} setIsLoginModalOpen={setIsLoginModalOpen} />}
            </div>
            <ToastMessage isOpen={isToastOpen} setIsOpen={setIsToastOpen} type={toast.type} message={toast.message}/>
        </>
    )
}


const Buttons = ({setIsRegisterModalOpen, setIsLoginModalOpen})=>{
    return(
        <div className={styles.buttons}>
        <button type="button" className={cx("btn btn-primary btn-lg", styles.button)}
                onClick={()=>setIsRegisterModalOpen(true)}>Registracija</button>
        <button type="button" className={cx("btn btn-primary btn-lg", styles.space, styles.button)} onClick={()=>setIsLoginModalOpen(true)}>Login</button>
    </div>
    );
}

const RegistrationModal = ({setIsRegisterModalOpen, isRegisterModalOpen})=>{
    const [validationMsg, setValidationMsg] = useState(null);

    const [user, setUser] = useState({
        ime: "",
        prezime: "",
        userName: "",
        sifra: "",
        email:"",
        broj_losih_reviewa: 0,
        ukupno_reviewa: 0
    })

    return(<GenericModal modalTitle={"Registracija"}
                         closeModal={()=>setIsRegisterModalOpen(false)}
                         showModal={isRegisterModalOpen}
                         bottomText={"Želimo Vam ugodno iskustvo na Stripomaniji!"}
                         btnText={"Registruj me!"}
        >
            <GenericField id={"ime"} name={"ime"} label={"Ime"} placeholder={"Vaše ime"} type={"text"} onChange={(e)=>handleFieldChange(e, user, setUser)}/>
            <GenericField id={"prezime"} name={"prezime"} label={"Prezime"} placeholder={"Vaše prezime"} type={"text"} onChange={(e)=>handleFieldChange(e, user, setUser)}/>
            <GenericField id={"email"} name={"email"} label={"Email adresa"} placeholder={"Npr. jane@doe.com"} type={"email"} onChange={(e)=>handleFieldChange(e, user, setUser)}/>
            <GenericField id={"usernameRegistracija"} name={"userName"} label={"Username"} placeholder={"Pomoću username-a se prijavljujete na Stripomaniju."} type={"text"} onChange={(e)=>handleFieldChange(e, user, setUser)}/>
            <GenericField id={"sifraRegistracija"} name={"sifra"} label={"Šifra"} placeholder={"Vaša šifra"} type={"password"} validationMsg={validationMsg} onChange={(e)=>handleFieldChange(e, user, setUser)}/>
            <div className="d-flex w-100 justify-content-end">
                <button type="button" className="btn btn-primary" onClick={()=>handleRegistrationRequest(baseUrl + routes.register.path, user, setValidationMsg)}>Predaj</button>
            </div>
        </GenericModal>
    );
}

const LoginModal = ({setIsLoginModalOpen, isLoginModalOpen, setIsToastOpen, setToast})=>{
    const [validationMsg, setValidationMsg] = useState(null);
    const [isInvalid, setIsInvalid] = useState(false);
    const [user, setUser] = useState({
        username:"",
        password:""
    });

    return(
        <GenericModal
            modalTitle={"Prijava"}
            btnText={"Predaj"}
            showModal={isLoginModalOpen}
            closeModal={()=>setIsLoginModalOpen(false)}>
            <GenericField id={"usernameLogin"} name={"username"} label={"Username"} placeholder={"Vaš username"} type={"text"} isInvalid={isInvalid} onChange={(e)=>handleFieldChange(e, user, setUser)}/>
            <GenericField id={"sifraLogin"} name={"password"} label={"Šifra"} placeholder={"Vaša šifra"} type={"password"} isInvalid={isInvalid} validationMsg={validationMsg} onChange={(e)=>handleFieldChange(e, user, setUser)}/>
            <div className="d-flex w-100 justify-content-end">
                <button type="button" className="btn btn-primary" onClick={()=> handleLoginRequest(baseUrl + routes.authenticate.path, user, setValidationMsg, setIsInvalid, setIsToastOpen, setToast)}>Predaj</button>
            </div>
        </GenericModal>
    );
}

export function handleFieldChange(e, user, setUser){
    const {name, value} = e.target;
    setUser(prevState=>({
            ...prevState,
            [name]: value
        })
    )
}

function handleLoginRequest(url,reqBody, setValidationMsg, setIsInvalid, setIsToastOpen, setToast){
    axios.post(url, reqBody)
        .then(response=>{
            // save token to local storage
            localStorage.setItem("jwt", response.data.jwt);
            localStorage.setItem("role", response.data.role);
            localStorage.setItem("username", reqBody.username);
            setValidationMsg(null);
            setIsInvalid(false);
            //check role
            if(response.data.role === "ROLE_ADMIN") Router.push(routes.adminPanel.path);
            else Router.push(navbarRoutes.profil.path);
        }).catch(error=>{
            try{
                if(error.response.status === 400){
                    //validacija
                    setIsInvalid(true);
                    setValidationMsg(error.response.data.message);
                }
            }
            catch(error){}
            console.log(error);
            setIsToastOpen(true);
            setToast({
                type:"danger",
                message:"Došlo je do greške!"
            });
    });
}

function handleRegistrationRequest(url,reqBody, setValidationMsg, setIsToastOpen, setToast){
    axios.post(url, reqBody)
        .then(response=>{
            setValidationMsg(null);
            Router.push(navbarRoutes.katalozi.path);
        }).catch(error=>{
        try{
            console.log(error);
            let errorObj = error.response.data;
            setValidationMsg(errorObj.message);
        }
        catch (e) {
            console.log(e);
            setIsToastOpen(true);
            setToast({
                type:"danger",
                message: "Došlo je do greške!"
            });
        }
    });
}