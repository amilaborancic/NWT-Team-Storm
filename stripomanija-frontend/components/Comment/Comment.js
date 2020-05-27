import React from "react";

const Comment = ({username, commentBody})=>{
    return(
        <div className="card card border-danger">
            <div className="card-body">
                <div className="d-flex align-items-baseline">
                    <h4 className="card-title">{username}</h4>
                    <h6 className="card-subtitle mb-2 ml-2 text-muted">je komentarisa{username.slice(-1) === "a" ? "la" : "o"}:</h6>
                </div>
                <p className="card-text">{commentBody}</p>
            </div>
        </div>
    );
}


export default Comment;