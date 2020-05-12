import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/css/bootstrap-theme.min.css';

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
