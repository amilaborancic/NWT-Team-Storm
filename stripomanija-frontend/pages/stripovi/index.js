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
import ToastMessage from "../../components/ToastMessage/ToastMessage";

const Stripovi = ()=>{
    //search drop down menu
    const [activeSearchType, setActiveSearchType] = useState(null);
    const [isDropDownOpen, setIsDropDownOpen] = useState(false);
    //search functionality
    const [searchQuery, setSearchQuery] = useState("");
    const [isSearchDisabled, setIsSearchDisabled] = useState(false);
    //sending requests
    const [url, setUrl] = useState("");
    const [params, setParams] = useState([]);
    //add comic to catalogue
    const [isAddToCatalogueModalOpen, setIsAddToCatalogueModalOpen] = useState(false);
    const [catalogueList, setCatalogueList] = useState([]);
    const [comic, setComic] = useState(null);
    //toast message
    const [toast, setToast] = useState(null);
    const [toastIsOpen, setToastIsOpen] = useState(false);
    return(
        <>
            {isAddToCatalogueModalOpen && <AddToCatalogueModal
                isModalOpen={isAddToCatalogueModalOpen}
                setIsAddToCatalogueModalOpen={setIsAddToCatalogueModalOpen}
                catalogueList={catalogueList}
                setCatalogueList={setCatalogueList}
                comic={comic}
                setToast={setToast}
                toastIsOpen={toastIsOpen}
                setToastIsOpen={setToastIsOpen}
            />}
            {toast && <ToastMessage message={toast.message} type={toast.type} isOpen={toastIsOpen} setIsOpen={setToastIsOpen}/>}
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
                        setComic={setComic}
                        setToast={setToast}
                        setToastIsOpen={setToastIsOpen}
                    />
                </div>
            </NavbarContainer>
        </>
    );
}

//ADD COMICBOOK TO CATALOGUE
const AddToCatalogueModal = ({setIsAddToCatalogueModalOpen, catalogueList, setCatalogueList, isModalOpen, comic, setToast, setToastIsOpen})=>{
    const [activeCatalogue, setActiveCatalogue] = useState(null);

    return(
        <GenericModal modalTitle={"Dodaj u katalog"}
                      btnText={"Dodaj"}
                      showModal={isModalOpen}
                      closeModal={()=>setIsAddToCatalogueModalOpen(false)}>
            <CatalogueList
                setIsToastOpen={setToastIsOpen}
                setToast={setToast}
                catalogueList={catalogueList}
                activeCatalogue={activeCatalogue}
                setActiveCatalogue={setActiveCatalogue}
                setCatalogueList={setCatalogueList}
            />
            <div className="d-flex w-100 justify-content-end">
                <button disabled={catalogueList.length === 0} type="button" className="btn btn-primary" onClick={()=>addComicToCatalogue(comic, activeCatalogue, setIsAddToCatalogueModalOpen, setToast, setToastIsOpen)}>Dodaj!</button>
            </div>
        </GenericModal>
    );
}

//LIST OF CATALOGUES
const CatalogueList = ({catalogueList, activeCatalogue, setCatalogueList, setActiveCatalogue, setToast, setIsToastOpen})=>{
    useEffect(()=>{
        fetchCatalogues(setCatalogueList, setActiveCatalogue, setToast, setIsToastOpen);
    }, []);
    return(
        <div className={cx("d-flex form-group flex-wrap", styles.modal)}>
            {catalogueList.length === 0 ?
                <span>Trenutno nemate nijedan katalog. Molimo, kreirajte ga.</span>
            :
                activeCatalogue && catalogueList.map((catalogue)=>
                    <div className="d-flex flex-column" key={catalogue.id}>
                        <div className={cx(["d-flex flex-column mx-2 my-1 align-items-center", activeCatalogue.id === catalogue.id ? styles.activeKat : ""])}
                             onClick={()=>changeSelectedCatalogue(catalogue.id, activeCatalogue, setActiveCatalogue, catalogueList)}>
                            <div className={cx("card text-white", styles.katalogContainer)}>
                                <div className="card-body">
                                    <h5 className="card-title">{catalogue.naziv}</h5>
                                </div>
                            </div>
                        </div>
                        {activeCatalogue.id === catalogue.id && <span className="text-primary text-center">Odabrani katalog</span>}
                    </div>
                )}
        </div>
    );
}

//update selected catalogue
function changeSelectedCatalogue(catId, activeCatalogue, setActiveCatalogue, catalogueList){
    let newActiveCatInd = catalogueList.findIndex(cat=>parseInt(cat.id) === parseInt(catId));
    setActiveCatalogue(catalogueList[newActiveCatInd]);
}

//SEARCH FUNCTIONALITY
const CustomSearchBar = ({setActiveSearchType, isDropDownOpen, setIsDropDownOpen, activeSearchType, setIsSearchDisabled, setCatalogueList, setToast, setToastIsOpen,
                             isSearchDisabled, url, setUrl, params, setParams, searchQuery, setSearchQuery, setIsAddToCatalogueModalOpen, setComic})=>{

    const [isSearchQueried, setIsSearchQueried] = useState(false);
    const [numberOfPages, setNumberOfPages] = useState(null);

    const [genreArray, setGenreArray] = useState([]);
    const [publisherArray, setPublisherArray] = useState([]);

    const [currentPage, setCurrentPage] = useState(0);
    const [searchResults, setSearchResults] = useState([]);

    //fetching every genre and publisher
    useEffect(()=>{
        fetchGenreOrPublisher(routes.zanr.path + routes.zanr.svi.path, setGenreArray, setToast, setToastIsOpen);
        fetchGenreOrPublisher( routes.izdavac.path + routes.izdavac.svi.path, setPublisherArray, setToast, setToastIsOpen);
    }, []);


    return(
        <form className={cx("form-group my-2", styles.formStyle)}>
            <div className="d-flex">
                <div className={cx("d-flex flex-column", styles.searchContainer)}>
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
                    setToastIsOpen={setToastIsOpen}
                    setToast={setToast}
                    isSearchQueried={isSearchQueried}
                />
            </div>
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
                    isSearchQueried={isSearchQueried}

                /> }
            </div>
            {isSearchQueried && numberOfPages !== null &&
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
                setCatalogueList={setCatalogueList}
                setComic={setComic}
            />}
        </form>);
}

//SELECT SEARCH TYPE
const SearchDropDown = ({isDropDownOpen, setIsDropDownOpen, setActiveSearchType, activeSearchType, setCurrentPage, setToast, setToastIsOpen,
                            setIsSearchDisabled, setUrl, url, currentPage, searchQuery, isSearchQueried, setIsSearchQueried, setNumberOfPages, setSearchResults, setSearchQuery})=>{
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
            <button className={cx("btn my-2 my-sm-0", styles.button)} type="button"
                    onClick={()=>handleSearch(url, activeSearchType, setIsSearchQueried, searchQuery, setSearchQuery, setNumberOfPages, setSearchResults, currentPage, setToast, setToastIsOpen, isSearchQueried)}>Traži!</button>
        </div>
    );
}

//RENDER GENRE OR PUBLISHER BUTTONS
const GenrePublisherButtons = ({array, url, activeSearchType, setIsSearchQueried, setNumberOfPages, setSearchResults, currentPage, setSearchQuery, isSearchQueried})=>{
    return(
        array.map(value=>
            <button type="button" value={value.id} key={value.naziv} className={`btn btn-outline-${value.boja} mr-4`}
                    onClick={(e)=>handleSearch(url, activeSearchType, setIsSearchQueried, value.id, setSearchQuery, setNumberOfPages, setSearchResults, currentPage, isSearchQueried)}>{value.naziv}</button>)
    );
}

//SEARCH RESULTS
const SearchResults = ({searchResults, numberOfPages, setCurrentPage, setNumberOfPages, currentPage, url, activeSearchType, setIsSearchQueried,
                           setSearchResults, searchQuery,setIsAddToCatalogueModalOpen, setComic})=>{
    return(
        <div className="d-flex w-100 flex-column justify-content-center">
            <h2 className={styles.title}>Rezultati pretrage</h2>
            <div className={cx("d-flex w-100 mt-4 justify-content-center", styles.wrapResults)}>
                {searchResults.length === 0 ?
                <span>Nije pronađen niti jedan strip.</span>
                    :
                    searchResults.map(comic=>{
                        let izdanje = "";
                        if(comic.izdanje) izdanje = `#${comic.izdanje}`;
                        return(
                            <div className={cx("d-flex flex-column justify-content-between", styles.thumbnailContainer)} key={comic.id}>
                                <StripThumbnail animated image={comic.slika} title={`${comic.naziv} ${izdanje}`} id={comic.id}/>
                                <button type="button" className={cx("btn btn-info btn-lg btn-block", styles.addToCatButton)} onClick={()=>{setIsAddToCatalogueModalOpen(true); setComic(comic);}}>Dodaj u katalog</button>
                            </div>
                        )
                    })
                }
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
function handleSearch(url, activeSearchType, setIsSearchQueried, searchQuery, setSearchQuery, setNumberOfPages, setSearchResults, currentPage, setToast, setToastIsOpen, isSearchQueried){
    setIsSearchQueried(true);
    let queryParams = extractParams(url, activeSearchType, setIsSearchQueried, searchQuery, setNumberOfPages, setSearchResults, currentPage);
    fetchComics(url, queryParams, setNumberOfPages, setSearchResults, setToast, setToastIsOpen, isSearchQueried);
    if(activeSearchType === SEARCH_TYPES.IZDAVAC || activeSearchType === SEARCH_TYPES.ZANR){
        setSearchQuery(searchQuery);
    }
}

/*     API CALLS        */

function fetchGenreOrPublisher(url, setArray, setToast, setToastIsOpen){
    const colors = ["success", "info", "danger", "warning"];
    authenticatedApi.get(url).then(res=>{
        let genreArray = res.data;
        if(genreArray.hasOwnProperty("zanrovi")) genreArray = res.data.zanrovi;
        else genreArray = res.data.izdavaci;
        genreArray.map((item, index)=>{
            item.boja = colors[index % colors.length];
        });
        setArray(genreArray);
    }).catch(error=>{
        console.log(error);
        setToast({
            type:"danger",
            message: comicbookServiceOfflineError
        });
        setToastIsOpen(true);
    });
}

function fetchComics(url, params, setNumberOfPages, setSearchResults, setToast, setToastIsOpen, isSearchQueried){
    authenticatedApi.get(url, {
        params: params
    }).then(res=>{
        setNumberOfPages(res.data.brojStranica);
        setSearchResults(res.data.stripovi);
    }).catch(err=>{
        if(!isSearchQueried){}
        else{
            setToast({
                type: "danger",
                message: "Došlo je do greške!"
            });
            setToastIsOpen(true);
        }
        console.log(err);
    });
}

function fetchCatalogues(setCatalogueList,setActiveCatalogue, setToast, setIsToastOpen){
    authenticatedApi.get(routes.user.path + routes.user.single.path + localStorage.getItem("username"))
        .then(response=>{
            authenticatedApi.get(routes.katalozi.path + routes.katalozi.svi.path,{
                params: {id_korisnik: response.data.id},
            }).then(res=>{
                setCatalogueList(res.data.katalozi);
                setActiveCatalogue(res.data.katalozi[0]);
            }).catch(err=>{
                console.log(err);
                setIsToastOpen(true);
                setToast({
                    type:"danger",
                    message:"Katalog servis je offline."
                })
            })
        })
        .catch(err=>{console.log(err)});
}

function addComicToCatalogue(comic, catalogue, setIsModalOpen, setToast, setToastIsOpen){
    authenticatedApi.put(routes.katalozi.path + routes.katalozi.dodaj_strip.path, {
        id_strip: comic.id,
        id_katalog: catalogue.id
    })
        .then(res=>{
            setToast({
                type: "success",
                message: "Uspješno ste dodali strip " + comic.naziv + " u katalog " + catalogue.naziv + "."
            });
            setToastIsOpen(true);
            setIsModalOpen(false);
        })
        .catch(err=>{
            setToast({
                type: "danger",
                message: "Strip " + comic.naziv + " se već nalazi u katalogu " + catalogue.naziv + "!"
            });
            setToastIsOpen(true);
            setIsModalOpen(false);
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

const comicbookServiceOfflineError = "Servis za stripove je offline.";

export default Stripovi;