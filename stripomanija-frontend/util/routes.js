import {catalogue, user} from "./url";

export const navbarRoutes = {
    home: {
        label: "Home",
        path: "/"
    },
    katalozi: {
        label: "Moji katalozi",
        path: "/katalozi"
    }
}

export const routes = {
    searchStripovi: {
        label: "Rezultati pretrage",
        path: "/search"
    },
    stripoviUKatalogu: {
        label: "Stripovi u mom katalogu",
        path: "/katalozi/katalog/[idKatalog]/stripovi" //privremeno
    },
    jedanKatalog: {
        label: "katalog",
        path: "/katalozi/katalog/"
    },
    authenticate: {
        label:"login",
        path: "/authenticate"
    },
    register: {
        label: "register",
        path: `${user}/sign-up`
    },
    katalozi: {
        label: "katalozi",
        path: `${catalogue}/svi`
    }
}
