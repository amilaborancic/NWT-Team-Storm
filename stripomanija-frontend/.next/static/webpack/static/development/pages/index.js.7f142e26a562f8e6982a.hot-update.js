webpackHotUpdate("static\\development\\pages\\index.js",{

/***/ "./util/routes.js":
/*!************************!*\
  !*** ./util/routes.js ***!
  \************************/
/*! exports provided: navbarRoutes, routes */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "navbarRoutes", function() { return navbarRoutes; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "routes", function() { return routes; });
var navbarRoutes = {
  home: {
    label: "Home",
    path: "/"
  },
  katalozi: {
    label: "Moji katalozi",
    path: "/katalozi"
  }
};
var routes = {
  searchStripovi: {
    label: "Rezultati pretrage",
    path: "/search"
  },
  stripoviUKatalogu: {
    label: "Stripovi u mom katalogu",
    path: "/katalozi/katalog/[idKatalog]/stripovi" //privremeno

  },
  jedanKatalog: {
    label: "",
    path: "/katalozi/katalog/"
  },
  authenticate: {
    label: "",
    path: "/authenticate"
  },
  register: {
    label: "register",
    path: "/register"
  }
};

/***/ }),

/***/ "./util/url.js":
/*!*********************!*\
  !*** ./util/url.js ***!
  \*********************/
/*! exports provided: baseUrl, user, catalogue, comicbook */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "baseUrl", function() { return baseUrl; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "user", function() { return user; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "catalogue", function() { return catalogue; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "comicbook", function() { return comicbook; });
var baseUrl = "http://localhost:8086";
var user = "/user";
var catalogue = "/katalog";
var comicbook = "/strip";

/***/ })

})
//# sourceMappingURL=index.js.7f142e26a562f8e6982a.hot-update.js.map