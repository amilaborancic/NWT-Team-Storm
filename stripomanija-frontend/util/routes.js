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
    },
    strip:{
        label:"stripovi",
        path: "/strip",
        pretraga: {
            naziv: {
                path: "/trazi-naziv",
                params: ["brojStranice", "naziv"]
            },
            svi: {
                path: "/svi",
                params: ["brojStranice"]
            },
            autor: {
                path: "/trazi-autor",
                params: ["brojStranice", "ime", "prezime"]
            },
            zanr: {
                path: "/trazi-zanr",
                params: ["brojStranice", "id_zanr"]
            },
            izdavac: {
                path: "/trazi-izdavac",
                params: ["brojStranice", "id_izdavac"]
            }
        }
    }
}
