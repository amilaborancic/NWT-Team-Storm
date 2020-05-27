import React, {useEffect, useState} from "react";
import cx from "classnames";
import styles from "./ToastMessage.module.css";

const ToastMessage = ({message, title, type, isOpen, setIsOpen})=>{
    const [show, setShow] = useState("");

    useEffect(()=>{
        if(isOpen) setShow(styles.show);
        else setShow("");
    }, [isOpen]);

    useEffect(()=>{
        //timer
        const timer = setTimeout(()=>{
            setIsOpen(false);
            setShow("");
        }, 3000);
        return () => clearTimeout(timer);
    }, [isOpen]);

    return(
        <div className={cx(styles.snackbar, show, styles[type])}>
            {message}
        </div>
    );
}

export default ToastMessage;