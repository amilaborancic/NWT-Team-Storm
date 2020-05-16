import React, {useState} from "react";
import styles from "./index.module.css";
import NavbarContainer from "../../components/NavbarContainer/NavbarContainer";
import cx from "classnames";
import {baseUrl} from "../../util/url";
import {routes} from "../../util/routes";
import axios from "axios";

const SEARCH_TYPES = {
    SVI: {
        id:0,
        label: "Svi"
    },
    NAZIV: {
        id:1,
        label: "Pretraga po nazivu"
    },
    AUTOR: {
        id:2,
        label: "Pretraga po autoru"
    },
    ZANR: {
        id:3,
        label: "Pretraga po žanru"
    },
    IZDAVAC: {
        id: 4,
        label: "Pretraga po izdavaču"
    }
}

const Stripovi = ()=>{
    const [activeSearchType, setActiveSearchType] = useState(null);
    const [isDropDownOpen, setIsDropDownOpen] = useState(false);
    const [searchQuery, setSearchQuery] = useState("");
    const [url, setUrl] = useState("");
    const [params, setParams] = useState([]);
    const [isSearchDisabled, setIsSearchDisabled] = useState(false);
    const [currentPage, setCurrentPage] = useState(0);

    return(
        <NavbarContainer>
            <div className={styles.wrapper}>
                <CustomSearchBar
                    setActiveSearchType={setActiveSearchType}
                    isDropDownOpen={isDropDownOpen}
                    setIsDropDownOpen={setIsDropDownOpen}
                    isSearchDisabled={isSearchDisabled}
                    setIsSearchDisabled={setIsSearchDisabled}
                    activeSearchType={activeSearchType}
                    url={url}
                    setUrl={setUrl}
                    setSearchQuery={setSearchQuery}
                    searchQuery={searchQuery}
                    setParams={setParams}
                    params={params}
                    setCurrentPage={setCurrentPage}
                    currentPage={currentPage}
                />
                {/*<h1 className={styles.title}>Rezultati pretrage za </h1>*/}
            </div>
        </NavbarContainer>);
}


const CustomSearchBar = ({setActiveSearchType, isDropDownOpen, setIsDropDownOpen, activeSearchType, setIsSearchDisabled,
                             isSearchDisabled, url, setUrl, currentPage, setCurrentPage, params, setParams, searchQuery, setSearchQuery})=>{
    const searchValues = Object.values(SEARCH_TYPES);
    const searchKeys = Object.keys(SEARCH_TYPES);
    const arr = [{naziv: "marvel", boja: "success"}, {naziv: "dc", boja:"info"}];

    return(
        <form className="form-inline my-2 my-lg-0 w-50 mr-3">
            <input className="form-control w-75" type="text" disabled={isSearchDisabled} placeholder="Pretraga stripova"  onChange={(e)=>handleChangeInput(e)} />
            <div className={styles.buttonContainer}>
                <button type="button" className={cx("btn dropdown-toggle ml-0", styles.button)} onClick={()=>setIsDropDownOpen(!isDropDownOpen)}/>
                <div className={cx("dropdown-menu", {"show": isDropDownOpen}, styles.searchDropdown)}>
                    {
                        searchValues.map((value, index) =>
                            <a className={cx("dropdown-item", styles.option)} key={value.id}
                               onClick={()=>changeActiveSearchType(searchKeys[index], setActiveSearchType, setIsSearchDisabled, setUrl, setParams, currentPage, searchQuery)}
                               style={{ backgroundColor: activeSearchType && activeSearchType.label === value.label && "orange"}}
                            >{value.label}</a>)
                    }
                </div>
                <button className={cx("btn my-2 my-sm-0", styles.button)} type="submit" onClick={()=>handleSearch(activeSearchType)}>Traži!</button>
            </div>
            <div className="d-flex w-100 justify-content-center mt-2">
                {isSearchDisabled && <GenrePublisherButtons array={arr}/> }
            </div>
        </form>);
}

function changeActiveSearchType(activeSearchType, setActiveSearchType, setIsSearchDisabled, setUrl, setParams, currentPage, searchQuery){
    setActiveSearchType(SEARCH_TYPES[activeSearchType]);
    switch(SEARCH_TYPES[activeSearchType]){
        case SEARCH_TYPES.NAZIV:
            const routeNaziv = routes.strip.pretraga.naziv;
            const paramObject = {
                brojStranice: currentPage,
                naziv: searchQuery
            };
            setUrl(baseUrl + routes.strip.path + routeNaziv.path);
            setParams(paramObject);
            setIsSearchDisabled(false);
            break;

        case SEARCH_TYPES.SVI:
            const routeSvi = routes.strip.pretraga.svi;
            setIsSearchDisabled(false);
            setUrl(baseUrl + routes.strip.path + routeSvi.path);
            setParams({brojStranice: currentPage});
            break;

        case SEARCH_TYPES.AUTOR:
            const routeAutor = routes.strip.pretraga.autor;
            const nameSurname = searchQuery.split(" ");
            let name = "";
            let surname = "";
            if(nameSurname.length === 1){
                name = nameSurname[0];
            }
            else{
                name = nameSurname[0];
                surname = nameSurname[1];
            }
            const autorParams = {
                brojStranice: currentPage,
                ime: name,
                prezime: surname
            };
            setIsSearchDisabled(false);
            setParams(autorParams);
            setUrl(baseUrl + routes.strip.path + routeAutor.path);
            break;

        case SEARCH_TYPES.ZANR:
            setIsSearchDisabled(true);
            setUrl(baseUrl + routes.strip.path + routes.strip.pretraga.zanr.path);
            setParams({
                brojStranice: currentPage,
                naziv: searchQuery
            });
            break;

        case SEARCH_TYPES.IZDAVAC:
            setIsSearchDisabled(true);
            setUrl(baseUrl + routes.strip.path + routes.strip.pretraga.izdavac.path);
            setParams({
                brojStranice: currentPage,
                naziv: searchQuery
            });
            break;

        default: console.log("blah");
    }
}

function handleChangeInput(e){
    console.log(e.target.value);
}

function handleSearch(activeSearchType){
    if(activeSearchType === null){
        console.log("odaberite tip pretrage")
    }
    else{

    }
}

const GenrePublisherButtons = ({array})=>{
    return(
        array.map(value=> <button type="button" key={value.naziv} className={`btn btn-outline-${value.boja} mr-4`}>{value.naziv}</button>)
    );
}

function fetchGenreOrPublisher(url, params){
    const colors = ["success", "info", "danger", "warning"];
    axios.get(url, {
        params: params
    }).then(res=>{
        const genrePublisherArray = res.data;
        genrePublisherArray.map((item, index)=>{
            item.boja = colors[index % colors.length];
        });
        return genrePublisherArray;
    }).catch(error=>{
        console.log(error);
    });
}



export default Stripovi;