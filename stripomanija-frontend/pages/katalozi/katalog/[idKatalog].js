import React, {useEffect, useState} from "react";
import {useRouter, withRouter} from "next/router";
import NavbarContainer from "../../../components/NavbarContainer/NavbarContainer";
import axios from "axios";
import Pagination from "../../../components/Pagination/Pagination";
import {authenticatedApi} from "../../../util/url";
import {navbarRoutes, routes} from "../../../util/routes";
import StripThumbnail from "../../../components/StripThumbnail/StripThumbnail";
import Link from "next/link";
import styles from "./index.module.css";
import cx from "classnames";
import ToastMessage from "../../../components/ToastMessage/ToastMessage";

const KatalogDetails = ({ router: { query } })=>{
    const [katalog, setKatalog] = useState(null);
    const [comicList, setComicList] = useState(null);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    //toast
    const [toast, setToast] = useState(null);
    const [toastIsOpen, setToastIsOpen] = useState(false);

    useEffect(()=>{
        if(query.idKatalog){
            authenticatedApi.get(catalogueDetailsUrl, {params: {id_katalog: query.idKatalog}}).then(res=>{setKatalog(res.data)}).catch(err=>{console.log(err)});
            authenticatedApi.get(catalogueComicsUrl + `/${query.idKatalog}`, {params: {brojStranice: currentPage}})
                .then(res=>{
                    setComicList(res.data.stripovi);
                    console.log(res.data.stripovi)
                    setTotalPages(res.data.ukupnoStranica);
                })
                .catch(err=>{console.log(err)});
        }
    }, [query.idKatalog, comicList]);

    return(
        <NavbarContainer>
            {katalog && comicList &&
            <div className={cx("d-flex w-100 flex-column justify-content-center", styles.bottom)}>
                <h1 className="d-flex justify-content-center mt-4 w-100">{katalog.naziv}</h1>
                <CatalogueBody comicList={comicList} catalogue={katalog} setToastIsOpen={setToastIsOpen} setToast={setToast}/>
                <div className="d-flex fixed-bottom justify-content-center">
                    <Pagination numberOfPages={totalPages} url={catalogueComicsUrl + `/${query.idKatalog}`}
                                params={{brojStranice: currentPage}} setSearchResults={setComicList}
                                setCurrentPage={setCurrentPage} currentPage={currentPage}
                    />
                </div>
            </div>}
            {toast && <ToastMessage setIsOpen={setToastIsOpen} isOpen={toastIsOpen} message={toast.message} type={toast.type} />}
        </NavbarContainer>
    );
}

const CatalogueBody = ({comicList, catalogue, setToast, setToastIsOpen})=>{
    return(
        <div className={cx("d-flex w-100 mt-4 justify-content-center align-items-start", styles.catalogueBody)}>
            {comicList.length === 0 ?
                <span>
                    Ovaj katalog je trenutno prazan. <Link href={navbarRoutes.pretraga.path}>Pronađite</Link> strip koji Vam se sviđa i dodajte ga.
                </span>
                :
                comicList.map(comic=>{
                let izdanje = "";
                if(comic.izdanje) izdanje = `#${comic.izdanje}`;
                return(
                    <div className={cx("d-flex flex-column justify-content-between", styles.thumbnailContainer)} key={comic.id}>
                        <StripThumbnail id={comic.id} animated image={comic.slika} title={`${comic.naziv} ${izdanje}`} />
                        <button type="button" className={cx("btn", styles.deleteBtn)} onClick={()=>deleteComicFromCatalogue(comic, catalogue, setToast, setToastIsOpen)}><span>Obriši iz kataloga</span></button>
                    </div>
                )
            })}
        </div>
    );
}

const catalogueDetailsUrl = routes.katalozi.path + routes.katalozi.jedan.path;
const catalogueComicsUrl = routes.katalozi.path + routes.katalozi.iz_kataloga.path;

// API CALLS

function deleteComicFromCatalogue(comic, catalogue, setToast, setIsToastOpen){
    authenticatedApi.delete(routes.katalozi.path + routes.katalozi.brisi_strip.path,{
        data:{
            id_strip: comic.id,
            id_katalog: catalogue.id
        }
    })
        .then(res=>{
            console.log(res)
            setToast({
                type: "success",
                message: "Uspješno ste obrisali strip " + comic.naziv + " iz kataloga " + catalogue.naziv
            });
            setIsToastOpen(true);
        })
        .catch(err=>{
            setIsToastOpen(true);
            setToast({
                type:"danger",
                message: err.response.data
            })
        });
}

function getCatalogue(url, params, setter){
    axios.get(url, {params})
        .then(res=>{
            setter(res.data);
        }).catch(err=>{
        console.log(err)
    })
}

export default withRouter(KatalogDetails);