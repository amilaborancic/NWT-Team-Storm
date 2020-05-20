import React, {useEffect, useState} from "react";
import styles from "./index.module.css";
import NavbarContainer from "../../components/NavbarContainer/NavbarContainer";
import cx from "classnames";
import {baseUrl, comicsUrl} from "../../util/url";
import {routes} from "../../util/routes";
import axios from "axios";
import Pagination from "../../components/Pagination/Pagination";
import {SEARCH_TYPES} from "../../util/searchTypes";

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
            </div>
        </NavbarContainer>);
}


const CustomSearchBar = ({setActiveSearchType, isDropDownOpen, setIsDropDownOpen, activeSearchType, setIsSearchDisabled,
                             isSearchDisabled, url, setUrl, currentPage, setCurrentPage, params, setParams, searchQuery, setSearchQuery})=>{

    const [isSearchQueried, setIsSearchQueried] = useState(false);
    const [numberOfPages, setNumberOfPages] = useState(null);

    //temporary - should call api
    const [genrePublisherArray, setGenrePublisherArray] = useState([{naziv: "marvel", boja: "success", id:0}, {naziv: "dc", boja:"info", id:1}]);

    return(
        <form className="form-inline my-2 my-lg-0 w-50 mr-3">
            <div className="w-75 d-flex flex-column">
                <input className={cx("form-control", {"is-invalid":!activeSearchType && isSearchQueried})} type="text" disabled={isSearchDisabled} placeholder="Pretraga stripova"
                       onChange={(e)=>handleChangeInput(e, setSearchQuery, searchQuery)} value={searchQuery}/>
                <div className="invalid-feedback">Odaberite tip pretrage!</div>
            </div>
            <SearchDropDown
                setUrl={setUrl}
                url={url}
                activeSearchType={activeSearchType}
                setIsSearchDisabled={setIsSearchDisabled}
                currentPage={currentPage}
                isDropDownOpen={isDropDownOpen}
                setIsDropDownOpen={setIsDropDownOpen}
                searchQuery={searchQuery}
                setActiveSearchType={setActiveSearchType}
                setIsSearchQueried={setIsSearchQueried}
                setParams={setParams}
                params={params}
                setSearchQuery={setSearchQuery}
                setNumberOfPages = {setNumberOfPages}
            />
            <div className="d-flex w-100 justify-content-center mt-2">
                {isSearchDisabled && <GenrePublisherButtons array={genrePublisherArray} setSearchQuery={setSearchQuery}/> }
            </div>
            {isSearchQueried && numberOfPages && <SearchResults numberOfPages={numberOfPages}/>}
        </form>);
}

const SearchDropDown = ({isDropDownOpen, setIsDropDownOpen, setActiveSearchType, activeSearchType, setIsSearchDisabled, setUrl, url,
                            setParams, params, currentPage, searchQuery, setSearchQuery, setIsSearchQueried, setNumberOfPages})=>{
    const searchValues = Object.values(SEARCH_TYPES);
    const searchKeys = Object.keys(SEARCH_TYPES);
    //state update
    useEffect(() => {}, [searchQuery]);

    return(
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
            <button className={cx("btn my-2 my-sm-0", styles.button)} type="button" onClick={()=>handleSearch(url, params, activeSearchType, setIsSearchQueried, searchQuery, setNumberOfPages)}>Traži!</button>
        </div>
    );
}

//switch between different search types
function changeActiveSearchType(activeSearchType, setActiveSearchType, setIsSearchDisabled, setUrl, setParams, currentPage, searchQuery){
    setActiveSearchType(SEARCH_TYPES[activeSearchType]);
    switch(SEARCH_TYPES[activeSearchType]){
        case SEARCH_TYPES.NAZIV:
            const routeNaziv = routes.strip.pretraga.naziv;
            const paramObject = {
                brojStranice: currentPage,
                naziv: searchQuery
            };
            setUrl(comicsUrl + routes.strip.path + routeNaziv.path);
            setParams(paramObject);
            setIsSearchDisabled(false);
            break;

        case SEARCH_TYPES.SVI:
            const routeSvi = routes.strip.pretraga.svi;
            setIsSearchDisabled(false);
            setUrl(comicsUrl + routes.strip.path + routeSvi.path);
            setParams({brojStranice: currentPage});
            break;

        case SEARCH_TYPES.AUTOR:
            const routeAutor = routes.strip.pretraga.autor;
            setUrl(comicsUrl + routes.strip.path + routeAutor.path);
            break;

        case SEARCH_TYPES.ZANR:
            setIsSearchDisabled(true);
            setUrl(comicsUrl + routes.strip.path + routes.strip.pretraga.zanr.path);
            setParams({
                brojStranice: currentPage,
                id_zanr: searchQuery
            });
            break;

        case SEARCH_TYPES.IZDAVAC:
            setIsSearchDisabled(true);
            setUrl(comicsUrl + routes.strip.path + routes.strip.pretraga.izdavac.path);
            setParams({
                brojStranice: currentPage,
                id_izdavac: searchQuery
            });
            break;

        default: console.log("blah");
    }
}

function handleChangeInput(e, setSearchQuery, searchQuery){
    setSearchQuery(e.target.value);
}

//format params and send request
function handleSearch(url, params, activeSearchType, setIsSearchQueried, searchQuery, setNumberOfPages){

    setIsSearchQueried(true);
    let queryParams = params;
    if(activeSearchType === SEARCH_TYPES.AUTOR){
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
            brojStranice: 0,
            ime: name,
            prezime: surname
        };
        queryParams = autorParams;
    }
    fetchComics(url, queryParams,setNumberOfPages);

}

const GenrePublisherButtons = ({array, setSearchQuery})=>{
    return(
        array.map(value=> <button type="button" value={value.id} key={value.naziv} className={`btn btn-outline-${value.boja} mr-4`}
                                  onClick={(e)=>handleChangeInput(e, setSearchQuery)}>{value.naziv}</button>)
    );
}

const SearchResults = ({numberOfPages})=>{
    return(
        <div className="d-flex w-100 flex-column justify-content-center align-items-center">
            <h1 className={styles.title}>Rezultati pretrage</h1>
            <div className="d-flex fixed-bottom justify-content-center">
                <Pagination numberOfPages={numberOfPages}/>
            </div>
        </div>
    );
}


/*     API CALLS        */

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

function fetchComics(url, params, setNumberOfPages){
    axios.get(url, {
        params: params
    }).then(res=>{
        setNumberOfPages(res.data.brojStranica);
        return res.data.stripovi;
    }).catch(err=>{
        console.log(err);
    });
}


export default Stripovi;