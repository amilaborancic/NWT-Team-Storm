import React from "react";
import {Modal} from "react-bootstrap";
import style from "./GenericModal.module.css";

const GenericModal = ({children, modalTitle, closeModal, showModal, bottomText, btnText})=>{
    return(
        <Modal show={showModal} onHide={closeModal}>
            <Modal.Header closeButton>
                <Modal.Title>{modalTitle}</Modal.Title>
            </Modal.Header>

            <ModalBody text={bottomText}>
                {children}
            </ModalBody>

            <Modal.Footer>
                <button type="button" className="btn btn-primary">{btnText}</button>
            </Modal.Footer>
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