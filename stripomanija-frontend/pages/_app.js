import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import "../public/bootstrap.theme.min.css";
import "./global.css";
import axios from "axios";
import {AuthProvider} from "../util/AuthProvider";
import 'react-rater/lib/react-rater.css'
// eslint-disable-next-line react/prop-types
const MyApp = ({ Component, pageProps }) => {
    return (
        <Component {...pageProps} />
    )
}

export default MyApp;
