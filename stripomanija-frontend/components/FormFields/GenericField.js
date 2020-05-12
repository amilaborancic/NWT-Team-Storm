import React from "react";

const GenericField = ({type, id, label, placeholder})=>{
    return(
        <div className="form-group">
            <label>{label}</label>
            <input type={type} className="form-control" id={id} placeholder={placeholder} />
        </div>
    );
}

export default GenericField;