import React, {useEffect, useState} from "react";
import Sidebar from "../../../components/Sidebar/Sidebar";
import {authenticatedApi, baseUrl} from "../../../util/url";
import StripThumbnail from "../../../components/StripThumbnail/StripThumbnail";
import GenericModal from "../../../components/GenericModal/GenericModal";
import {routes} from "../../../util/routes";
import Pagination from "../../../components/Pagination/Pagination";
import GenericField from "../../../components/FormFields/GenericField";
import ToastMessage from "../../../components/ToastMessage/ToastMessage";

const fetchUrl = routes.strip.path + routes.strip.pretraga.svi.path;

const Stripovi = ()=>{
    const [stripList, setStripList] = useState(null);
    const [isOpen, setIsOpen] = useState(false);
    const [isToastOpen, setIsToastOpen] = useState(false);
    const [currentPage, setCurrentPage] = useState(0);
    const [params, setParams] = useState({
        brojStranice: currentPage
    })
    const [totalPages, setTotalPages] = useState(null);

    useEffect(()=>{
        fetchItems(fetchUrl, params, setStripList, setTotalPages);
    }, []);

    return(
        <Sidebar>
            <div>
                <div className="d-flex justify-content-between w-100">
                    <h1>Stripovi</h1>
                    <button type="button" className="btn btn-success btn-lg" onClick={()=>setIsOpen(true)}>Novi strip</button>
                </div>
                <div className="d-flex justify-content-around mt-5">
                    {stripList && stripList.map(item=>
                        <div className="mx-2"  key={item.id}>
                            <StripThumbnail image={item.slika} title={item.naziv} animated/>
                        </div>
                    )}
                </div>
                {stripList && totalPages &&
                <div className="d-flex fixed-bottom justify-content-center">
                    <Pagination url={fetchUrl} currentPage={currentPage} setCurrentPage={setCurrentPage} setSearchResults={setStripList} params={params} numberOfPages={totalPages}/>
                </div>}
            </div>
            <NewComicModal isOpen={isOpen} setIsOpen={setIsOpen} setIsToastOpen={setIsToastOpen}/>
            <ToastMessage title={"Potvrda"} message={"Uspješno ste dodali novi strip."} type={"success"} isOpen={isToastOpen} setIsOpen={setIsToastOpen}/>

        </Sidebar>
    );
}

const NewComicModal = ({isOpen, setIsOpen, setIsToastOpen})=>{
    const [publisherList, setPublisherList] = useState(null);
    const [genreList, setGenreList] = useState(null);
    const [authorList, setAuthorList] = useState([]);
    const [checkedAuthors, setCheckedAuthors] = useState([]);
    const [newComic, setNewComic] = useState({
        naziv: "",
        opis: "",
        slika: "",
        izdanje: null,
        idIzdavac: null,
        idZanr: null,
        autori: [],
        ukupniRating: 1,
        ukupnoKomentara: 0
    });

    useEffect(()=>{
        fetchAdditionalResources(routes.izdavac.path + routes.izdavac.svi.path, setPublisherList);
        fetchAdditionalResources(routes.zanr.path + routes.zanr.svi.path, setGenreList);
        fetchAdditionalResources(routes.autor.path + routes.autor.svi.path, setAuthorList);
    }, []);

    return(
        <GenericModal modalTitle="Novi Strip" showModal={isOpen} closeModal={setIsOpen}>
            <GenericField label={"Naziv"} name={"naziv"} placeholder={"Naziv novog stripa"} type={"text"} onChange={(e)=>handleFieldChange(e, newComic, setNewComic)} />
            <div className="form-group">
                <label>Opis</label>
                <textarea className="form-control" name={"opis"} placeholder={"Koja je glavna radnja stripa?"} rows="3"
                          onChange={(e)=>handleFieldChange(e, newComic, setNewComic)} />
            </div>
            <div className="form-group">
                <label>Slika</label>
                <textarea className="form-control" name={"slika"} placeholder={"Ovdje stavite putanju do slike."} rows="3"
                          onChange={(e)=>handleFieldChange(e, newComic, setNewComic)} />
            </div>
            <GenericField type={"number"} placeholder={"Broj izdanja stripa. Za standalone strip ovo polje ostaje prazno."} name={"izdanje"} label={"Izdanje"}
                          onChange={(e)=>handleFieldChange(e, newComic, setNewComic)} />
            <CustomSelect title="Izdavač" itemList={publisherList} name={"idIzdavac"} newComic={newComic} setNewComic={setNewComic} />
            <CustomSelect title="Žanr" itemList={genreList} name={"idZanr"} newComic={newComic} setNewComic={setNewComic}  />
            <CustomCheckboxGroup authorList={authorList} setAuthorList={setAuthorList} checkedAuthors={checkedAuthors} setCheckedAuthors={setCheckedAuthors}/>
            <button type="button" className="btn btn-danger" onClick={()=>addNewComic(newComic, setNewComic, checkedAuthors, authorList, setIsOpen, setIsToastOpen)}>Dodaj strip</button>
        </GenericModal>
    );
}

const CustomCheckboxGroup = ({authorList, setAuthorList, checkedAuthors, setCheckedAuthors})=>{

    return(
        <>
            <label>Autori</label>
            <div className="d-flex flex-wrap">
                {authorList.map((author, index)=>
                    <div key={author.id} className="form-group mx-2">
                        <label className="custom-control custom-checkbox" htmlFor={author.id}>
                            <input type="checkbox" className="custom-control-input"
                                   checked={checkedAuthors[index]}
                                   onChange={(e)=>changeSelectedAuthors(e, setAuthorList, authorList, checkedAuthors, setCheckedAuthors)}
                                   value={author.id}
                                   id={author.id}
                            />
                            <span className="custom-control-label" htmlFor={author.id}>{author.ime} {author.prezime}</span>
                        </label>
                    </div>
                )}
            </div>
        </>
    );
}

const CustomSelect = ({title, itemList, name, newComic, setNewComic})=>{
    useEffect(()=>{
        setNewComic(prevState=>({
                ...prevState,
                [name]: itemList[0].id
            })
        )
    }, [])
    return(
        <div className="form-group">
            <label>{title}</label>
            <select className="custom-select" name={name} onChange={(e)=>handleFieldChange(e, newComic, setNewComic)}>
                {itemList && itemList.map(item=><option key={item.id} value={`${item.id}`}>{item.naziv}</option>)}
            </select>
        </div>
    );
}

function changeSelectedAuthors(e, setSelectedAuthorsList, selectedAuthorsList, checkedAuthors, setCheckedAuthors){
    const authorId = parseInt(e.target.value);
    //search for author first
    let index = selectedAuthorsList.findIndex((author)=>author.id === authorId);
    let tempArray = checkedAuthors;
    tempArray[index] = !tempArray[index];
    setCheckedAuthors(tempArray);
}

//send new comic request
function addNewComic(newComic, setNewComic, checkedAuthors, authorList, setIsOpen, setIsToastOpen){
    //set authors first
    let comicInformation = newComic;
    authorList.map((author, index)=>{if(checkedAuthors[index]) comicInformation.autori.push(author)});
    setNewComic(comicInformation);
    //send request
    authenticatedApi.post(routes.strip.path + routes.strip.novi.path, newComic)
        .then(res=>{
            console.log(res);
            //close modal, show toast
            setIsOpen(false);
            setIsToastOpen(true);
            //reset comic object
            setNewComic({
                naziv: "",
                opis: "",
                slika: "",
                izdanje: null,
                idIzdavac: null,
                idZanr: null,
                autori: [],
                ukupniRating: 1,
                ukupnoKomentara: 0
            });
        })
        .catch(err=>{
            console.log(err);
            //validation messages!
        });
}

//fetch genre and publishers
function fetchAdditionalResources(url, setArray){
    authenticatedApi.get(url).then(res=>{
        setArray(res.data);
    }).catch(error=>{
        console.log(error);
    });
}

//field change function
function handleFieldChange(e, field, setField){
    const {name, value} = e.target;
    setField(prevState=>({
            ...prevState,
            [name]: value
        })
    )
}

//fetch items
function fetchItems(fetchUrl, params, setStripList, setNumberOfPages){
    authenticatedApi.get(fetchUrl, {
        params: params
    }).then(res=>{
        setStripList(res.data.stripovi);
        setNumberOfPages(res.data.brojStranica);
    }).catch(err=>{
        console.log(err);
    });
}

export default Stripovi;