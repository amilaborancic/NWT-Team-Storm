import React, { createContext, useContext, useState, useEffect } from 'react';
import { useRouter } from 'next/router';
import {navbarRoutes} from "./routes";
const AuthContext = createContext();

function AuthProvider({ children }) {
    const { pathname, events } = useRouter();
    const router = useRouter();
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
                router.push(navbarRoutes.home.path);
            }
        }

        // Check that initial route is OK
        if(!token || pathname.substring(1, 6) === "admin" && role !== "ROLE_ADMIN"){
            router.push(navbarRoutes.home.path);
        }

        // Monitor routes
        events.on('routeChangeStart', handleRouteChange)
        return () => {
            events.off('routeChangeStart', handleRouteChange)
        }
    }, [pathname])

    return (
        <AuthContext.Provider value={{ token, role }}>{children}</AuthContext.Provider>
    )
}

const useAuth = () => useContext(AuthContext)

export { AuthProvider, useAuth }