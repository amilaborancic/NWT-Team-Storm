import React, {useEffect, useState} from "react";
import styles from "./index.module.css";
import NavbarContainer from "../../components/NavbarContainer/NavbarContainer";
import cx from "classnames";
import {authenticatedApi} from "../../util/url";
import {routes} from "../../util/routes";
import Pagination from "../../components/Pagination/Pagination";
import {SEARCH_TYPES} from "../../util/searchTypes";
import StripThumbnail from "../../components/StripThumbnail/StripThumbnail";
import GenericModal from "../../components/GenericModal/GenericModal";
import CatalogueList from "../../components/CatalogueList/CatalogueList";

const Stripovi = ()=>{
    const [activeSearchType, setActiveSearchType] = useState(null);
    const [isDropDownOpen, setIsDropDownOpen] = useState(false);
    const [searchQuery, setSearchQuery] = useState("");
    const [url, setUrl] = useState("");
    const [params, setParams] = useState([]);
    const [isSearchDisabled, setIsSearchDisabled] = useState(false);
    const [isAddToCatalogueModalOpen, setIsAddToCatalogueModalOpen] = useState(false);
    const [catalogueList, setCatalogueList] = useState(null);

    //USER SE VADI IZ TOKENA!
    const [userId, setUserId] = useState(3);

    return(
        <>
            {isAddToCatalogueModalOpen &&
            <GenericModal modalTitle={"Dodaj u katalog"}
                          btnText={"Dodaj"}
                          showModal={()=>setIsAddToCatalogueModalOpen(true)}
                          closeModal={()=>setIsAddToCatalogueModalOpen(false)}>

                <CatalogueList catalogueList={catalogueList} />
                <div className="d-flex w-100 justify-content-end">
                    <button type="button" className="btn btn-primary">Dodaj!</button>
                </div>
            </GenericModal>}
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
                        setIsAddToCatalogueModalOpen={setIsAddToCatalogueModalOpen}
                        setCatalogueList={setCatalogueList}
                        userId={userId}
                    />
                </div>
            </NavbarContainer>
        </>
    );
}


const CustomSearchBar = ({setActiveSearchType, isDropDownOpen, setIsDropDownOpen, activeSearchType, setIsSearchDisabled, userId, setCatalogueList,
                             isSearchDisabled, url, setUrl, params, setParams, searchQuery, setSearchQuery, setIsAddToCatalogueModalOpen})=>{

    const [isSearchQueried, setIsSearchQueried] = useState(false);
    const [numberOfPages, setNumberOfPages] = useState(null);

    const [genreArray, setGenreArray] = useState([]);
    const [publisherArray, setPublisherArray] = useState([]);

    const [currentPage, setCurrentPage] = useState(0);
    const [searchResults, setSearchResults] = useState([]);

    //fetching every genre and publisher
    useEffect(()=>{
        fetchGenreOrPublisher(routes.zanr.path + routes.zanr.svi.path, setGenreArray);
        fetchGenreOrPublisher( routes.izdavac.path + routes.izdavac.svi.path, setPublisherArray);
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
                setNumberOfPages={setNumberOfPages}
                setSearchResults={setSearchResults}
                setCurrentPage={setCurrentPage}
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
                    setSearchResults={setSearchResults}
                    currentPage={currentPage}
                /> }
            </div>
            {isSearchQueried && numberOfPages &&
            <SearchResults
                searchResults={searchResults}
                numberOfPages={numberOfPages}
                currentPage={currentPage}
                setCurrentPage={setCurrentPage}
                url={url}
                activeSearchType={activeSearchType}
                setIsSearchQueried={setIsSearchQueried}
                searchQuery={searchQuery}
                setNumberOfPages={setNumberOfPages}
                setSearchResults={setSearchResults}
                setIsAddToCatalogueModalOpen={setIsAddToCatalogueModalOpen}
                userId={userId}
                setCatalogueList={setCatalogueList}
            />}
        </form>);
}

const SearchDropDown = ({isDropDownOpen, setIsDropDownOpen, setActiveSearchType, activeSearchType, setCurrentPage,
                            setIsSearchDisabled, setUrl, url, currentPage, searchQuery, setIsSearchQueried, setNumberOfPages, setSearchResults, setSearchQuery})=>{
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
                           onClick={()=>changeActiveSearchType(searchKeys[index], setActiveSearchType, setIsSearchDisabled, setUrl, setCurrentPage, setNumberOfPages)}
                           style={{ backgroundColor: activeSearchType && activeSearchType.label === value.label && "orange"}}
                        >{value.label}</a>)
                }
            </div>
            <button className={cx("btn my-2 my-sm-0", styles.button)} type="button" onClick={()=>handleSearch(url, activeSearchType, setIsSearchQueried, searchQuery, setSearchQuery, setNumberOfPages, setSearchResults, currentPage)}>Traži!</button>
        </div>
    );
}

const GenrePublisherButtons = ({array, url, activeSearchType, setIsSearchQueried, setNumberOfPages, setSearchResults, currentPage, setSearchQuery})=>{
    return(
        array.map(value=>
            <button type="button" value={value.id} key={value.naziv} className={`btn btn-outline-${value.boja} mr-4`}
                    onClick={(e)=>handleSearch(url, activeSearchType, setIsSearchQueried, value.id, setSearchQuery, setNumberOfPages, setSearchResults, currentPage)}>{value.naziv}</button>)
    );
}

const SearchResults = ({searchResults, numberOfPages, setCurrentPage, setNumberOfPages, currentPage, url, activeSearchType, setIsSearchQueried,
                           setSearchResults, searchQuery,setIsAddToCatalogueModalOpen, userId, setCatalogueList})=>{
    return(
        <div className="d-flex w-100 flex-column justify-content-center">
            <h1 className={styles.title}>Rezultati pretrage</h1>
            <div className="d-flex w-100 mt-4 justify-content-center align-items-start">
                {searchResults.map(comic=>{
                    let izdanje = "";
                    if(comic.izdanje) izdanje = `#${comic.izdanje}`;
                    return(
                        <div className={cx("d-flex mx-5 flex-column justify-content-between", styles.thumbnailContainer)} key={comic.id}>
                            <StripThumbnail animated image={comic.slika} title={`${comic.naziv} ${izdanje}`} id={comic.id}/>
                            <AddToCatalogueButton onClick={()=>setIsAddToCatalogueModalOpen(true)} />
                        </div>
                    )
                })}
            </div>
            <div className="d-flex fixed-bottom justify-content-center">
                <Pagination currentPage={currentPage} setCurrentPage={setCurrentPage} numberOfPages={numberOfPages} url={url}
                            params={extractParams(url, activeSearchType, setIsSearchQueried, searchQuery, setNumberOfPages, setSearchResults, currentPage)}
                            setSearchResults={setSearchResults}
                />
            </div>
        </div>
    );
}

function catalogueModalActions(setIsAddToCatalogueModalOpen, userId, setCatalogueList){
    setIsAddToCatalogueModalOpen(true);
    //PRIVREMENO
    //fetchCatalogues(catalogueUrl + routes.katalozi.path + routes.katalozi.svi.path, {id_korisnik: userId}, setCatalogueList);
}

const AddToCatalogueButton = ({onClick})=>{
    return(
        <button type="button" className={cx("btn btn-info btn-lg btn-block mt-4", styles.addToCatButton)} onClick={onClick}>Dodaj u katalog</button>
    );
}


//switch between different search types
function changeActiveSearchType(activeSearchType, setActiveSearchType, setIsSearchDisabled, setUrl, setCurrentPage, setNumberOfPages){
    setActiveSearchType(SEARCH_TYPES[activeSearchType]);
    setCurrentPage(0);
    setNumberOfPages(null);
    switch(SEARCH_TYPES[activeSearchType]){
        case SEARCH_TYPES.NAZIV:
            const routeNaziv = routes.strip.pretraga.naziv;
            setUrl(routes.strip.path + routeNaziv.path);
            setIsSearchDisabled(false);
            break;

        case SEARCH_TYPES.SVI:
            const routeSvi = routes.strip.pretraga.svi;
            setIsSearchDisabled(false);
            setUrl(routes.strip.path + routeSvi.path);
            break;

        case SEARCH_TYPES.AUTOR:
            const routeAutor = routes.strip.pretraga.autor;
            setUrl(routes.strip.path + routeAutor.path);
            setIsSearchDisabled(false);
            break;

        case SEARCH_TYPES.ZANR:
            setIsSearchDisabled(true);
            setUrl(routes.strip.path + routes.strip.pretraga.zanr.path);
            break;

        case SEARCH_TYPES.IZDAVAC:
            setIsSearchDisabled(true);
            setUrl(routes.strip.path + routes.strip.pretraga.izdavac.path);
            break;

        default: console.log("blah");
    }
}

//onChange event
function handleChangeInput(e, setSearchQuery){
    setSearchQuery(e.target.value);
}

//send request
function handleSearch(url, activeSearchType, setIsSearchQueried, searchQuery, setSearchQuery, setNumberOfPages, setSearchResults, currentPage){
    setIsSearchQueried(true);
    let queryParams = extractParams(url, activeSearchType, setIsSearchQueried, searchQuery, setNumberOfPages, setSearchResults, currentPage);
    fetchComics(url, queryParams, setNumberOfPages, setSearchResults);
    if(activeSearchType === SEARCH_TYPES.IZDAVAC || activeSearchType === SEARCH_TYPES.ZANR){
        setSearchQuery(searchQuery);
    }
}

/*     API CALLS        */

function fetchGenreOrPublisher(url, setArray){
    const colors = ["success", "info", "danger", "warning"];
    authenticatedApi.get(url).then(res=>{
        let genreArray = res.data;
        genreArray.map((item, index)=>{
            item.boja = colors[index % colors.length];
        });
        setArray(genreArray);
    }).catch(error=>{
        console.log(error);
    });
}

function fetchComics(url, params, setNumberOfPages, setSearchResults){
    authenticatedApi.get(url, {
        params: params
    }).then(res=>{
        setNumberOfPages(res.data.brojStranica);
        setSearchResults(res.data.stripovi);
    }).catch(err=>{
        console.log(err);
    });
}

//get params based on seach type
function extractParams(url, activeSearchType, setIsSearchQueried, searchQuery, setNumberOfPages, setSearchResults, currentPage){
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
        queryParams = {
            brojStranice: currentPage,
            ime: name,
            prezime: surname
        };
    }
    else if(activeSearchType === SEARCH_TYPES.NAZIV){
        queryParams = {
            brojStranice: currentPage,
            naziv: searchQuery
        };
    }
    else if(activeSearchType === SEARCH_TYPES.ZANR){
        queryParams = {
            brojStranice: currentPage,
            id_zanr: searchQuery
        };
    }
    else if(activeSearchType === SEARCH_TYPES.IZDAVAC){
        queryParams = {
            brojStranice: currentPage,
            id_izdavac: searchQuery
        };
    }
    else{
        queryParams={
            brojStranice: currentPage
        }
    }
    return queryParams;
}


export default Stripovi;