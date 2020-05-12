import Head from 'next/head';
import styles from "./index.module.css";
import cx from "classnames";
import GenericModal from "../components/GenericModal/GenericModal";
import React, {useState} from "react";
import GenericField from "../components/FormFields/GenericField";

export default function Home() {
    const [isRegisterModalOpen, setIsRegisterModalOpen] = useState(false);
    const [isLoginModalOpen, setIsLoginModalOpen] = useState(false);
    return (
        <>
            {isRegisterModalOpen && <RegistrationModal setIsRegisterModalOpen={setIsRegisterModalOpen}/>}
            {isLoginModalOpen && <LoginModal setIsLoginModalOpen={setIsLoginModalOpen}/>}
            <div className={styles.container}>
                <Head>
                    <title>Stripomanija</title>
                    <link rel="icon" href="/favicon.ico" />
                </Head>
                <div className={styles.upperSection}>
                    <div>STRIPOMANIJA</div>
                    <div className={styles.buttons}>
                        <button type="button" className={cx("btn btn-primary btn-lg", styles.button)}
                                onClick={()=>setIsRegisterModalOpen(true)}>Registracija</button>
                        <button type="button" className={cx("btn btn-primary btn-lg", styles.space, styles.button)} onClick={()=>setIsLoginModalOpen(true)}>Login</button>
                    </div>
                </div>
            </div>

        </>
    )
}

const RegistrationModal = ({setIsRegisterModalOpen})=>{
    return(<GenericModal modalTitle={"Registracija"}
                         closeModal={()=>setIsRegisterModalOpen(false)}
                         showModal={()=>setIsRegisterModalOpen(true)}
                         bottomText={"Želimo Vam ugodno iskustvo na Stripomaniji!"}
                         btnText={"Registruj me!"}
        >
            <GenericField id={"ime"} label={"Ime"} placeholder={"Vaše ime"} type={"text"}/>
            <GenericField id={"prezime"} label={"Prezime"} placeholder={"Vaše prezime"} type={"text"}/>
            <GenericField id={"email"} label={"Email adresa"} placeholder={"Npr. jane@doe.com"} type={"email"}/>
            <GenericField id={"usernameRegistracija"} label={"Username"} placeholder={"Pomoću username-a se prijavljujete na Stripomaniju."} type={"text"}/>
            <GenericField id={"sifraRegistracija"} label={"Šifra"} placeholder={"Vaša šifra"} type={"password"}/>
        </GenericModal>
    );
}

const LoginModal = ({setIsLoginModalOpen})=>{
    return(<GenericModal
            modalTitle={"Prijava"}
            btnText={"Predaj"}
            showModal={()=>setIsLoginModalOpen(true)}
            closeModal={()=>setIsLoginModalOpen(false)}
        >
            <GenericField id={"usernameLogin"} label={"Username"} placeholder={"Vaš username"} type={"text"}/>
            <GenericField id={"sifraLogin"} label={"Šifra"} placeholder={"Vaša šifra"} type={"password"}/>
    </GenericModal>
    );
}