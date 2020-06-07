import React, { createContext, useContext, useState, useEffect } from 'react';
import Router, { useRouter } from 'next/router';
import {routes} from "./routes";
const AuthContext = createContext();

function AuthProvider({ children }) {
    const { pathname, events } = useRouter();
    const [token, setToken] = useState();
    const [role, setRole] = useState();

    async function getUserCredentials() {
        setToken(localStorage.getItem("jwt"));
        setRole(localStorage.getItem("role"));
    }
    useEffect(() => {
        getUserCredentials();
    }, [pathname]);

    useEffect(() => {
        // Check that a new route is OK
        const handleRouteChange = url => {
            if(!token || url.substring(1, 6) === "admin" && role !== "ROLE_ADMIN"){
                Router.push(routes.home.path);
            }
        }

        // Check that initial route is OK
        if(!token || pathname.substring(1, 6) === "admin" && role !== "ROLE_ADMIN"){
            Router.push(routes.home.path);
        }

        // Monitor routes
        Router.events.on('routeChangeStart', handleRouteChange)
        return () => {
            Router.events.off('routeChangeStart', handleRouteChange)
        }
    }, [token])

    return (
        <AuthContext.Provider value={{ token, role }}>{children}</AuthContext.Provider>
    )
}

const useAuth = () => useContext(AuthContext)

export { AuthProvider, useAuth }