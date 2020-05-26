import React, {useEffect, useState} from "react";
import Sidebar from "../../../components/Sidebar/Sidebar";
import {authenticatedApi} from "../../../util/url";
import StripThumbnail from "../../../components/StripThumbnail/StripThumbnail";
import GenericModal from "../../../components/GenericModal/GenericModal";
import {routes} from "../../../util/routes";
import Pagination from "../../../components/Pagination/Pagination";
import GenericField from "../../../components/FormFields/GenericField";
import axios from "axios";

const fetchUrl = routes.strip.path + routes.strip.pretraga.svi.path;

const Stripovi = ()=>{
    const [stripList, setStripList] = useState(null);
    const [isOpen, setIsOpen] = useState(false);
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
                <div className="d-flex mt-4">
                    {stripList && stripList.map(item=>
                        <StripThumbnail image={item.slika} title={item.naziv} animated/>
                    )}
                </div>
                {stripList && <Pagination url={fetchUrl} currentPage={currentPage} setCurrentPage={setCurrentPage} setSearchResults={setStripList} params={params} numberOfPages={totalPages}/>}
            </div>
            <NewComicModal isOpen={isOpen} setIsOpen={setIsOpen}/>
        </Sidebar>
    );
}

const NewComicModal = ({isOpen, setIsOpen})=>{
    const [publisherList, setPublisherList] = useState(null);
    const [genreList, setGenreList] = useState(null);
    const [authorList, setAuthorList] = useState([{id:1, ime: "ami", prezime: "seef", checked: false}, {id: 2, ime:"slkm", prezime:"FDN", checked: false}]);
    const [newComic, setNewComic] = useState({
        naziv: "",
        opis: "",
        slika: "",
        izdanje: null,
        idIzdavac: null,
        idZanr: null,
        autori: authorList
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
                <textarea className="form-control" name={"opis"} placeholder={"Koja je glavna radnja stripa?"} rows="3"/>
            </div>
            <div className="form-group">
                <label>Slika</label>
                <input type="file" className="form-control-file" name={"slika"} aria-describedby="fileHelp" />
                <small className="form-text text-muted">Odaberite naslovnicu stripa.</small>
            </div>
            <GenericField type={"number"} placeholder={"Broj izdanja stripa. Za standalone strip ovo polje ostaje prazno."} name={"izdanje"} label={"Izdanje"} />
            <CustomSelect title="Izdavač" itemList={publisherList} name={"idIzdavac"}/>
            <CustomSelect title="Žanr" itemList={genreList} name={"idZanr"}/>
            <CustomCheckboxGroup authorList={authorList} setAuthorList={setAuthorList}/>
        </GenericModal>
    );
}

const CustomCheckboxGroup = ({authorList, setAuthorList})=>{
    return(
        <>
            <label>Autori</label>
            {authorList.map(author=>
                <div key={author.id} className="form-group">
                    <label className="custom-control custom-checkbox" htmlFor={author.id}>
                        <input type="checkbox" className="custom-control-input"
                               checked={author.checked}
                               onChange={(e)=>changeSelectedAuthors(e, setAuthorList, authorList)}
                               value={author.id}
                               id={author.id}
                        />
                        <span className="custom-control-label" htmlFor={author.id}>{author.ime} {author.prezime} {author.checked ? "true" : "false"}</span>
                    </label>
                </div>
            )}
        </>
    );
}

function changeSelectedAuthors(e, setSelectedAuthorsList, selectedAuthorsList){
    const authorId = parseInt(e.target.value);
    //search for author first
    let authorInList = selectedAuthorsList.filter(authorInList=>authorInList.id === authorId);
    if(authorInList[0].checked){
        //remove
        let tempArray = selectedAuthorsList;
        tempArray.map(author=>{
            if(author.id === authorId){
                author.checked = false;
            }
        })
        setSelectedAuthorsList(tempArray);
    }
    else{
        //add
        let tempArray = selectedAuthorsList;
        tempArray.map(author=>{
            if(author.id === authorId){
                author.checked = true;
            }
        })
        setSelectedAuthorsList(tempArray);
    }
}

const CustomSelect = ({title, itemList, name})=>{
    return(
        <div className="form-group">
            <label>{title}</label>
            <select className="custom-select" name={name}>
                {itemList && itemList.map(item=><option value={`${item.id}`}>{item.naziv}</option>)}
            </select>
        </div>
    );
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