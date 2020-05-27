import {catalogue, user} from "./url";

export const navbarRoutes = {
    home: {
        label: "Home",
        path: "/"
    },
    katalozi: {
        label: "Moji katalozi",
        path: "/katalozi"
    },
    pretraga: {
        label: "Pretraga stripova",
        path:"/stripovi"
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
        path: "/katalog",
        svi:{
            label:"svi",
            path:"/svi"
        },
        jedan:{
            label: "jedan katalog",
            path: "/jedan"
        },
        iz_kataloga: {
            label: "stripovi u katalogu",
            path: "/iz-kataloga"
        },
        novi: {
            label: "novi katalog",
            path: "/novi"
        }
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
        },
        novi: {
            path: "/noviStrip",
            label: "novi strip"
        },
        jedan:{
            path: "/stripovi/strip/"
        }
    },
    zanr: {
        label: "zanrovi",
        path: "/zanr",
        novi: {
            label: "novi zanr",
            path: "/novi"
        },
        svi: {
            label: "svi zanrovi",
            path: "/svi"
        }
    },
    izdavac: {
        label: "izdavaci",
        path: "/izdavac",
        svi: {
            label: "svi izdavaci",
            path: "/svi"
        }
    },
    autor:{
        label: "autori",
        path: "/autor",
        svi: {
            path: "/svi",
            label: "svi autori"
        }
    },
    rating:{
        path:"/rating",
        label:"rating",
        komentari: {
            path: "/komentari-stripa/", //id stripa se ovdje doda
            label:"komentari za strip"
        }
    },
    adminPanel:{
        label: "Admin panel",
        path: "/admin"
    }
}

export const adminPanelRoutes = {
    stripovi: {
        label: "Stripovi",
        path: "/admin/stripovi"
    },
    izdavaci: {
        label: "Izdavači",
        path: "/admin/izdavaci"
    },
    zanrovi: {
        label: "Žanrovi",
        path: "/admin/zanrovi"
    }
}
