import React, {useEffect, useState} from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import "../public/bootstrap.theme.min.css";
import "./global.css";
import Router from 'next/router';
import {AuthProvider} from "../util/AuthProvider";
import PageLoader from "../components/PageLoader/PageLoader";

// eslint-disable-next-line react/prop-types
const MyApp = ({ Component, pageProps }) => {
    const [isLoading, setIsLoading] = useState(false);

    useEffect(()=>{
        Router.events.on('routeChangeStart', ()=>setIsLoading(true));
        Router.events.on('routeChangeComplete', ()=>setIsLoading(false));
        Router.events.on('routeChangeError', ()=>setIsLoading(false));
        return ()=>{
            Router.events.off('routeChangeStart', ()=>setIsLoading(true));
            Router.events.off('routeChangeComplete', ()=>setIsLoading(false));
            Router.events.off('routeChangeError', ()=>setIsLoading(false));
        }
    });
    return (
        <>
            {isLoading && <PageLoader/>}
            <Component {...pageProps} />
        </>
    )
}

MyApp.getInitialProps = async ({ Component, ctx }) => {
    let pageProps = {};

    if (Component.getInitialProps) {
        pageProps = await Component.getInitialProps(ctx);
    }

    return { pageProps };
};

export default MyApp;
