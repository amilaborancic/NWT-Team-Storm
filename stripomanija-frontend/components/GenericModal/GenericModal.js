import React from "react";
import {Modal} from "react-bootstrap";
import style from "./GenericModal.module.css";

const GenericModal = ({children, modalTitle, closeModal, showModal, bottomText})=>{
    return(
        <Modal show={showModal} onHide={closeModal}>
            <Modal.Header closeButton>
                <Modal.Title>{modalTitle}</Modal.Title>
            </Modal.Header>
            <ModalBody text={bottomText}>
                {children}
            </ModalBody>
        </Modal>
    );
}

export const ModalBody = ({children, text})=>{
    return( <Modal.Body>
        {children}
        <small className={style.smallTxt}>{text}</small>
    </Modal.Body>)
}


export default GenericModal;