import React from "react";
import cx from "classnames";

const GenericField = ({type, id, label, placeholder, onChange, name, isInvalid, validationMsg})=>{
    return(
        <div className="form-group">
            <label>{label}</label>
            <input type={type} className={cx("form-control", {"is-invalid":isInvalid})} id={id} placeholder={placeholder} onChange={onChange} name={name}/>
            <div className="col">
                <small id={`${id}validation`} className="text-danger">
                    {validationMsg}
                </small>
            </div>
        </div>
    );
}

export default GenericField;