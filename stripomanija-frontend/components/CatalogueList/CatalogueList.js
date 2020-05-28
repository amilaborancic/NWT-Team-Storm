import React, {useEffect, useState} from "react";
import styles from "./CatalogueList.module.css";
import axios from "axios";
import {authenticatedApi, catalogueUrl} from "../../util/url";
import {routes} from "../../util/routes";
import cx from "classnames";

const CatalogueList = ({setSelectedCatalogue})=>{
    const [catalogueList, setCatalogueList] = useState(null);
    //PRIVREMENO
    const [idUser, setIdUser] = useState(3);
    const [activeRadio, setActiveRadio] = useState(null);
    useEffect(()=>{
        fetchCatalogues(routes.katalozi.path + routes.katalozi.svi.path, {id_korisnik: idUser}, setCatalogueList, setActiveRadio);
    }, []);

    return(
        <div className={cx("d-flex form-group flex-wrap", styles.modal)}>
            {catalogueList && activeRadio && catalogueList.map((catalogue)=>
                <div key={catalogue.id} className="d-flex flex-column mx-3 align-items-center my-1">
                    <div className={cx("card text-white mb-3", styles.container)}>
                        <div className="card-body">
                            <h5 className="card-title">{catalogue.naziv}</h5>
                        </div>
                    </div>
                    <div className="custom-control custom-radio">
                        <label className="custom-control-label">
                            <input type="radio" value={catalogue.id} name="radio" className="custom-control-input"
                                   onChange={()=>setActiveRadio(catalogue.id)}
                                   checked={activeRadio === catalogue.id} />
                        </label>
                    </div>
                </div>)}
        </div>
    );
}

function fetchCatalogues(url, params, setCatalogueList, setActiveRadio){
    authenticatedApi.get(url, {
        params: params
    }).then(res=>{
        setCatalogueList(res.data);
        setActiveRadio(res.data[0].id);
    }).catch(err=>{
        console.log(err);
    })
}

export default CatalogueList;