import React, {useEffect, useState} from "react";
import Sidebar from "../../../components/Sidebar/Sidebar";
import cx from "classnames";
import styles from "../izdavaci/index.module.css";
import GenericModal from "../../../components/GenericModal/GenericModal";
import GenericField from "../../../components/FormFields/GenericField";
import {handleFieldChange} from "../../index";
import ToastMessage from "../../../components/ToastMessage/ToastMessage";
import {authenticatedApi} from "../../../util/url";
import {routes} from "../../../util/routes";
import {useWindowDimensions} from "../../../components/hooks/useWindowDimensions";
import NavbarContainer from "../../../components/NavbarContainer/NavbarContainer";

const Autori = ()=>{
    const [isOpen, setIsOpen] = useState(false);
    const [isToastOpen, setIsToastOpen] = useState(false);
    const [toast, setToast] = useState({});
    const [authorList, setAuthorList] = useState([]);
    const [newAuthor, setNewAuthor] = useState({
        ime: "",
        prezime: ""
    });
    const [windowDimensions, setWindowDimensions] = useState({});
    useWindowDimensions(windowDimensions, setWindowDimensions);

    useEffect(()=>{
        fetchAuthors(setAuthorList);
    }, [authorList]);

    return(
        windowDimensions.width <= 749 ?
        <NavbarContainer admin>
            <Body
                authorList={authorList}
                setIsOpen={setIsOpen}
                isOpen={isOpen}
                newAuthor={newAuthor}
                setNewAuthor={setNewAuthor}
                setIsToastOpen={setIsToastOpen}
                setToast={setToast}
                toast={toast}
                isToastOpen={isToastOpen}
            />
        </NavbarContainer>
            :
        <Sidebar>
            <Body
                authorList={authorList}
                setIsOpen={setIsOpen}
                isOpen={isOpen}
                newAuthor={newAuthor}
                setNewAuthor={setNewAuthor}
                setIsToastOpen={setIsToastOpen}
                setToast={setToast}
                toast={toast}
                isToastOpen={isToastOpen}
            />
        </Sidebar>
    );
}

const Body = ({authorList, setIsOpen, isOpen, newAuthor, setNewAuthor, setIsToastOpen, setToast, toast, isToastOpen})=>{
    return(
        <div className={styles.container}>
            <div className={cx("d-flex flex-column", styles.titleRow)}>
                <h1>Autori</h1>
                <button type="button" className={cx("btn btn-success", styles.newBtn)} onClick={()=>setIsOpen(true)}>Novi autor</button>
            </div>
            <div className="d-flex mt-5">
                <ul className="list-group">
                    {authorList.map(author=>
                        <li key={author.id} className="list-group-item d-flex justify-content-between align-items-center">
                            {author.ime} {author.prezime}
                        </li>
                    )}
                </ul>
            </div>
            {isOpen &&
            <GenericModal modalTitle={"Novi autor"} closeModal={()=>setIsOpen(false)} showModal={isOpen}>
                <GenericField type="text" label="Ime" placeholder="Ime" name="ime" id="ime" onChange={(e)=>handleFieldChange(e, newAuthor, setNewAuthor)}/>
                <GenericField type="text" label="Prezime" placeholder="Prezime" name="prezime" id="prezime" onChange={(e)=>handleFieldChange(e, newAuthor, setNewAuthor)}/>
                <div className="d-flex w-100 justify-content-end">
                    <button type="button" className="btn btn-success" onClick={()=>addNewAuthor(newAuthor, setNewAuthor, setIsToastOpen, setToast, setIsOpen)}>Ok</button>
                </div>
            </GenericModal>
            }
            {toast && <ToastMessage message={toast.message} type={toast.type} isOpen={isToastOpen} setIsOpen={setIsToastOpen}/>}
        </div>
    );
}

function fetchAuthors(setAuthors){
    authenticatedApi.get(routes.autor.path + routes.autor.svi.path)
        .then(res=>{
            setAuthors(res.data.autori);
        })
        .catch(err=>{console.log(err)})
}

function addNewAuthor(author, setAuthor, setIsToastOpen, setToast, setIsModalOpen){
    authenticatedApi.post(routes.autor.path + routes.autor.novi.path, author)
        .then(res=>{
            setIsToastOpen(true);
            setIsModalOpen(false);
            setToast({
                type:"success",
                message: "Uspješno ste dodali novog autora!"
            });
            setAuthor({
                ime:"",
                prezime: ""
            });
        })
        .catch(err=>{
            setIsModalOpen(false);
            setToast({
                type:"danger",
                message: "Došlo je do greške pilikom dobavljanja autora!"
            });
            setIsToastOpen(true);
        })

}

export default Autori;