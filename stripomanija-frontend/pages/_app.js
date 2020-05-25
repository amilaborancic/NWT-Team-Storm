import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import "../public/bootstrap.theme.min.css";
import "./global.css";
import axios from "axios";

// eslint-disable-next-line react/prop-types
const MyApp = ({ Component, pageProps }) => {
    return (
        <Component {...pageProps} />
    )
}
/*
MyApp.getInitialProps = async ({ Component, ctx}) => {
    let pageProps = {};
    if (Component && Component.getInitialProps) {
        pageProps = await Component.getInitialProps(ctx) || {};
    }
    if (!pageProps.namespacesRequired) {
        pageProps.namespacesRequired = []
        // eslint-disable-next-line no-console
    }
    return { pageProps };
}*/

export default MyApp;
