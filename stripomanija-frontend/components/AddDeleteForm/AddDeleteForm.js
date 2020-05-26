import React, {useEffect, useState} from "react";
import styles from "./AddDeleteForm.module.css";
import {authenticatedApi} from "../../util/url";
import StripThumbnail from "../StripThumbnail/StripThumbnail";
import GenericModal from "../GenericModal/GenericModal";

const AddDeleteForm = (itemTitle, fetchUrl, params, itemPluralTitle, isThumbnail)=>{
    const [itemList, setItemList] = useState(null);
    const [isOpen, setIsOpen] = useState(false);
    useEffect(()=>{
        fetchItems(fetchUrl, params, setItemList);
    }, []);

    return(
        <>
            <div>
                <button type="button" className="btn btn-success btn-lg">Novi {itemTitle}</button>
                <h1>{itemPluralTitle}</h1>
                <div className="d-flex mt-4">
                    {itemList && isThumbnail && itemList.map(item=>
                        <StripThumbnail image={item.slika} title={item.naziv} animated/>
                    )}
                    {itemList && !isThumbnail && itemList.map(item=>
                        <div>hajlala tralala</div>
                    )}
                </div>
            </div>
            <GenericModal modalTitle={`Novi ${itemTitle}`} showModal={isOpen} closeModal={setIsOpen}></GenericModal>
        </>
    );
}

function addNew(){}

//fetch items
function fetchItems(fetchUrl, params, setItemList){
    authenticatedApi.get(fetchUrl, {
        params: params
    }).then(res=>{
        //transform so that itemList items have only one field to display
        setItemList(res.data);
    }).catch(err=>{
        console.log(err);
    });
}

export default AddDeleteForm;