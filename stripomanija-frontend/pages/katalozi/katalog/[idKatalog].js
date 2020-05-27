import React, {useEffect, useState} from "react";
import {useRouter, withRouter} from "next/router";
import NavbarContainer from "../../../components/NavbarContainer/NavbarContainer";
import axios from "axios";
import Pagination from "../../../components/Pagination/Pagination";
import {authenticatedApi} from "../../../util/url";
import {routes} from "../../../util/routes";
import StripThumbnail from "../../../components/StripThumbnail/StripThumbnail";

const KatalogDetails = ({ router: { query } })=>{
    const [katalog, setKatalog] = useState(null);
    const [comicList, setComicList] = useState(null);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

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
    }, [query.idKatalog]);

    return(
        <NavbarContainer>
            {katalog && comicList &&
            <div className="d-flex w-100 flex-column justify-content-center">
                <h1 className="d-flex justify-content-center mt-4 w-100">{katalog.naziv}</h1>
                <CatalogueBody comicList={comicList}/>
                <div className="d-flex fixed-bottom justify-content-center">
                    <Pagination numberOfPages={totalPages} url={catalogueComicsUrl + `/${query.idKatalog}`}
                                params={{brojStranice: currentPage}} setSearchResults={setComicList}
                                setCurrentPage={setCurrentPage} currentPage={currentPage}
                    />
                </div>
            </div>}
        </NavbarContainer>
    );
}

const CatalogueBody = ({comicList})=>{
    return(
        <div className="d-flex w-100 mt-4 justify-content-center align-items-start">
            {comicList.map(comic=>{
                let izdanje = "";
                if(comic.izdanje) izdanje = `#${comic.izdanje}`;
                return(
                    <div className="d-flex mx-5" key={comic.id}>
                        <StripThumbnail id={comic.id} animated image={comic.slika} title={`${comic.naziv} ${izdanje}`} />
                    </div>
                )
            })}
        </div>
    );
}

const catalogueDetailsUrl = routes.katalozi.path + routes.katalozi.jedan.path;
const catalogueComicsUrl = routes.katalozi.path + routes.katalozi.iz_kataloga.path;

// API CALLS
function getCatalogue(url, params, setter){
    axios.get(url, {params})
        .then(res=>{
            setter(res.data);
        }).catch(err=>{
        console.log(err)
    })
}

export default withRouter(KatalogDetails);