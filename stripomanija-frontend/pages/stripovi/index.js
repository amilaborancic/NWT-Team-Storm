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

    const [genreArray, setGenreArray] = useState([]);
    const [publisherArray, setPublisherArray] = useState([]);

    //fetching every genre and publisher
    useEffect(()=>{
        fetchGenreOrPublisher(comicsUrl + routes.zanr.path + routes.zanr.svi.path, setGenreArray);
        fetchGenreOrPublisher(comicsUrl + routes.izdavac.path + routes.izdavac.svi.path, setPublisherArray);
    }, []);

    return(
        <form className="form-inline my-2 my-lg-0 w-50 mr-3">
            <div className="w-75 d-flex flex-column">
                <input className={cx("form-control", {"is-invalid":!activeSearchType && isSearchQueried})} type="text" disabled={isSearchDisabled} placeholder="Pretraga stripova"
                       onChange={(e)=>handleChangeInput(e, setSearchQuery)}/>
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
                setGenrePublisherArray={activeSearchType === SEARCH_TYPES.IZDAVAC ? setPublisherArray : setGenreArray}
                genrePublisherArray={activeSearchType === SEARCH_TYPES.IZDAVAC ? publisherArray : genreArray}
            />
            <div className="d-flex w-100 justify-content-center mt-2">
                {isSearchDisabled &&
                <GenrePublisherButtons
                    array={activeSearchType === SEARCH_TYPES.IZDAVAC ? publisherArray : genreArray}
                    setSearchQuery={setSearchQuery}
                    url={url}
                    setIsSearchQueried={setIsSearchQueried}
                    activeSearchType={activeSearchType}
                    setNumberOfPages={setNumberOfPages}
                /> }
            </div>
            {isSearchQueried && numberOfPages && <SearchResults numberOfPages={numberOfPages}/>}
        </form>);
}

const SearchDropDown = ({isDropDownOpen, setIsDropDownOpen, setActiveSearchType, activeSearchType, setIsSearchDisabled, setUrl, url,
                            setParams, params, currentPage, searchQuery, setIsSearchQueried, setNumberOfPages, setGenrePublisherArray, genrePublisherArray})=>{
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
                           onClick={()=>changeActiveSearchType(searchKeys[index], setActiveSearchType, setIsSearchDisabled, setUrl, setParams, currentPage, searchQuery, setGenrePublisherArray, genrePublisherArray)}
                           style={{ backgroundColor: activeSearchType && activeSearchType.label === value.label && "orange"}}
                        >{value.label}</a>)
                }
            </div>
            <button className={cx("btn my-2 my-sm-0", styles.button)} type="button" onClick={()=>handleSearch(url, activeSearchType, setIsSearchQueried, searchQuery, setNumberOfPages)}>Traži!</button>
        </div>
    );
}

//switch between different search types
function changeActiveSearchType(activeSearchType, setActiveSearchType, setIsSearchDisabled, setUrl, setParams, currentPage, searchQuery, setGenrePublisherArray, genrePublisherArray){
    setActiveSearchType(SEARCH_TYPES[activeSearchType]);
    switch(SEARCH_TYPES[activeSearchType]){
        case SEARCH_TYPES.NAZIV:
            const routeNaziv = routes.strip.pretraga.naziv;
            setUrl(comicsUrl + routes.strip.path + routeNaziv.path);
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
            break;

        case SEARCH_TYPES.IZDAVAC:
            setIsSearchDisabled(true);
            setUrl(comicsUrl + routes.strip.path + routes.strip.pretraga.izdavac.path);
            break;

        default: console.log("blah");
    }
}

function handleChangeInput(e, setSearchQuery){
    setSearchQuery(e.target.value);
}

//format params and send request
function handleSearch(url, activeSearchType, setIsSearchQueried, searchQuery, setNumberOfPages){
    setIsSearchQueried(true);
    let queryParams = null;
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
    else if(activeSearchType === SEARCH_TYPES.NAZIV){
        queryParams = {
            brojStranice: 0,
            naziv: searchQuery
        };
    }
    else if(activeSearchType === SEARCH_TYPES.ZANR){
        queryParams = {
            brojStranice: 0,
            id_zanr: searchQuery
        };
    }
    else if(activeSearchType === SEARCH_TYPES.IZDAVAC){
        queryParams = {
            brojStranice: 0,
            id_izdavac: searchQuery
        };
    }
    fetchComics(url, queryParams, setNumberOfPages);

}

const GenrePublisherButtons = ({array, url, activeSearchType, setIsSearchQueried, setNumberOfPages})=>{
    return(
        array.map(value=> <button type="button" value={value.id} key={value.naziv} className={`btn btn-outline-${value.boja} mr-4`}
                                  onClick={(e)=>handleSearch(url, activeSearchType, setIsSearchQueried, value.id, setNumberOfPages)}>{value.naziv}</button>)
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

function fetchGenreOrPublisher(url, setArray){
    const colors = ["success", "info", "danger", "warning"];
    axios.get(url).then(res=>{
        let genreArray = res.data;
        genreArray.map((item, index)=>{
            item.boja = colors[index % colors.length];
        });
        setArray(genreArray);
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