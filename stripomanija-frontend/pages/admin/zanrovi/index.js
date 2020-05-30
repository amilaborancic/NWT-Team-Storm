import React, {useEffect, useState} from "react";
import Sidebar from "../../../components/Sidebar/Sidebar";
import {authenticatedApi} from "../../../util/url";
import {routes} from "../../../util/routes";
import cx from "classnames";
import styles from "../izdavaci/index.module.css";
import GenericModal from "../../../components/GenericModal/GenericModal";
import GenericField from "../../../components/FormFields/GenericField";
import {handleFieldChange} from "../../index";
import ToastMessage from "../../../components/ToastMessage/ToastMessage";
import {useWindowDimensions} from "../../../components/hooks/useWindowDimensions";
import NavbarContainer from "../../../components/NavbarContainer/NavbarContainer";

const Zanrovi = ()=>{
    const [isOpen, setIsOpen] = useState(false);
    const [isToastOpen, setIsToastOpen] = useState(false);
    const [toast, setToast] = useState({});
    const [genreList, setGenreList] = useState([]);
    const [newGenre, setNewGenre] = useState({
        naziv: ""
    });
    const [windowDims, setWindowDims] = useState({});
    useWindowDimensions(windowDims, setWindowDims);

    useEffect(()=>{
        fetchGenre(setGenreList);
    }, [genreList]);
    return(
        windowDims.width <= 749 ?
        <NavbarContainer admin>
            <Body
                isToastOpen={isToastOpen}
                setIsToastOpen={setIsToastOpen}
                isOpen={isOpen}
                setIsOpen={setIsOpen}
                setToast={setToast}
                newGenre={newGenre}
                genreList={genreList}
                setNewGenre={setNewGenre}
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
                newGenre={newGenre}
                genreList={genreList}
                setNewGenre={setNewGenre}
                toast={toast}
            />
        </Sidebar>
    );
}

const Body = ({genreList, setIsOpen, isOpen, newGenre, setNewGenre, setIsToastOpen, setToast, toast, isToastOpen})=>{
    return(
        <div className={styles.container}>
            <div className={cx("d-flex flex-column", styles.titleRow)}>
                <h1>Žanrovi</h1>
                <button type="button" className={cx("btn btn-success", styles.newBtn)} onClick={()=>setIsOpen(true)}>Novi žanr</button>
            </div>
            <div className="d-flex mt-5">
                <ul className="list-group">
                    {genreList.map(genre=>
                        <li key={genre.id} className="list-group-item d-flex justify-content-between align-items-center">
                            {genre.naziv}
                        </li>
                    )}
                </ul>
            </div>
            {isOpen &&
            <GenericModal modalTitle={"Novi žanr"} closeModal={()=>setIsOpen(false)} showModal={isOpen}>
                <GenericField type="text" label="Naziv" placeholder="Naziv žanra." name="naziv" id="naziv" onChange={(e)=>handleFieldChange(e, newGenre, setNewGenre)}/>
                <div className="d-flex w-100 justify-content-end">
                    <button type="button" className="btn btn-success" onClick={()=>addNewGenre(newGenre, setNewGenre, setIsToastOpen, setToast, setIsOpen)}>Ok</button>
                </div>
            </GenericModal>
            }
            {toast && <ToastMessage message={toast.message} type={toast.type} isOpen={isToastOpen} setIsOpen={setIsToastOpen}/>}
        </div>
    );
}

function fetchGenre(setGenreList){
    authenticatedApi.get(routes.zanr.path + routes.zanr.svi.path)
        .then(res=>{
            setGenreList(res.data);
        })
        .catch(err=>{console.log(err)})
}

function addNewGenre(genre, setGenre, setIsToastOpen, setToast, setIsModalOpen){
    authenticatedApi.post(routes.zanr.path + routes.zanr.novi.path, genre)
        .then(res=>{
            setIsToastOpen(true);
            setIsModalOpen(false);
            setToast({
                type:"success",
                message: "Uspješno ste kreirali novog izdavača!"
            });
            setGenre({
                naziv:""
            });
        })
        .catch(err=>{
            setIsModalOpen(false);
            setToast({
                type:"danger",
                message: "Došlo je do greške!"
            });
            setIsToastOpen(true);
        })

}

export default Zanrovi;