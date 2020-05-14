webpackHotUpdate("static\\development\\pages\\katalozi.js",{

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
/* harmony import */ var _url__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./url */ "./util/url.js");

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
    label: "katalog",
    path: "/katalozi/katalog/"
  },
  authenticate: {
    label: "login",
    path: "/authenticate"
  },
  register: {
    label: "register",
    path: "".concat(_url__WEBPACK_IMPORTED_MODULE_0__["user"], "/sign-up")
  },
  katalozi: {
    label: "katalozi",
    path: "".concat(_url__WEBPACK_IMPORTED_MODULE_0__["catalogue"], "/svi")
  }
};

/***/ })

})
//# sourceMappingURL=katalozi.js.c22b975aff0cdfd97b31.hot-update.js.map