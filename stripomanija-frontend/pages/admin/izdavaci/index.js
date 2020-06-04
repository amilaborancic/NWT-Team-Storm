import React, {useEffect, useState} from "react";
import Sidebar from "../../../components/Sidebar/Sidebar";
import {authenticatedApi} from "../../../util/url";
import {routes} from "../../../util/routes";
import cx from "classnames";
import styles from "./index.module.css";
import GenericModal from "../../../components/GenericModal/GenericModal";
import GenericField from "../../../components/FormFields/GenericField";
import {handleFieldChange} from "../../index";
import ToastMessage from "../../../components/ToastMessage/ToastMessage";
import {useWindowDimensions} from "../../../components/hooks/useWindowDimensions";
import NavbarContainer from "../../../components/NavbarContainer/NavbarContainer";


const Izdavaci = ()=>{
    const [isOpen, setIsOpen] = useState(false);
    const [isToastOpen, setIsToastOpen] = useState(false);
    const [toast, setToast] = useState({});
    const [publisherList, setPublisherList] = useState([]);
    const [newPublisher, setNewPublisher] = useState({
        naziv: ""
    });
    const [windowDims, setWindowDims] = useState({});
    useWindowDimensions(windowDims, setWindowDims);

    useEffect(()=>{
        fetchPublishers(setPublisherList);
    }, [publisherList]);

    return(
        windowDims.width <= 749 ?
            <NavbarContainer admin>
                <Body
                    isToastOpen={isToastOpen}
                    setIsToastOpen={setIsToastOpen}
                    isOpen={isOpen}
                    setIsOpen={setIsOpen}
                    setToast={setToast}
                    newPublisher={newPublisher}
                    publisherList={publisherList}
                    setNewPublisher={setNewPublisher}
                    toast={toast}
                />
            </NavbarContainer>
            :
            <Sidebar>
                <Body
                    isToastOpen={isToastOpen}
                    setIsToastOpen={setIsToastOpen}
                    isOpen={isOpen}
                    setIsOpen={setIsOpen}
                    setToast={setToast}
                    newPublisher={newPublisher}
                    publisherList={publisherList}
                    setNewPublisher={setNewPublisher}
                    toast={toast}
                />
            </Sidebar>
    );
}

const Body = ({publisherList, setIsOpen, isOpen, newPublisher, setNewPublisher, setIsToastOpen, isToastOpen, setToast, toast})=>{
    return(
        <div className={styles.container}>
            <div className={cx("d-flex flex-column", styles.titleRow)}>
                <h1>Izdavači</h1>
                <button type="button" className={cx("btn btn-success", styles.newBtn)} onClick={()=>setIsOpen(true)}>Novi izdavač</button>
            </div>
            <div className="d-flex mt-5">
                <ul className="list-group">
                    {publisherList.map(publisher=>
                        <li key={publisher.id} className="list-group-item d-flex justify-content-between align-items-center">
                            {publisher.naziv}
                        </li>
                    )}
                </ul>
            </div>
            {isOpen &&
            <GenericModal modalTitle={"Novi izdavač"} closeModal={()=>setIsOpen(false)} showModal={isOpen}>
                <GenericField type="text" label="Naziv" placeholder="Naziv izdavačke kuće." name="naziv" id="naziv" onChange={(e)=>handleFieldChange(e, newPublisher, setNewPublisher)}/>
                <div className="d-flex w-100 justify-content-end">
                    <button type="button" className="btn btn-success" onClick={()=>addNewPublisher(newPublisher, setNewPublisher, setIsToastOpen, setToast, setIsOpen)}>Ok</button>
                </div>
            </GenericModal>
            }
            {toast && <ToastMessage message={toast.message} type={toast.type} isOpen={isToastOpen} setIsOpen={setIsToastOpen}/>}
        </div>
    );
}

function fetchPublishers(setPublisherList){
    authenticatedApi.get(routes.izdavac.path + routes.izdavac.svi.path)
        .then(res=>{
            setPublisherList(res.data.izdavaci);
        })
        .catch(err=>{console.log(err)})
}

function addNewPublisher(publisher, setPublisher, setIsToastOpen, setToast, setIsModalOpen){
    authenticatedApi.post(routes.izdavac.path + routes.izdavac.novi.path, publisher)
        .then(res=>{
            setIsToastOpen(true);
            setIsModalOpen(false);
            setToast({
                type:"success",
                message: "Uspješno ste kreirali novog izdavača!"
            });
            setPublisher({
                naziv:""
            });
        })
        .catch(err=>{
            setIsModalOpen(false);
            setToast({
                type:"danger",
                message: "Došlo je do greške prilikom dobavljanja izdavača!"
            });
            setIsToastOpen(true);
        })

}

export default Izdavaci;