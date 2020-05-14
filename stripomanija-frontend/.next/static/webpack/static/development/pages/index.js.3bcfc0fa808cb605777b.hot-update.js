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
    label: "",
    path: "/katalozi/katalog/"
  },
  authenticate: {
    label: "login",
    path: "/authenticate"
  },
  register: {
    label: "register",
    path: "".concat(_url__WEBPACK_IMPORTED_MODULE_0__["user"], "/sign-up")
  }
};

/***/ })

})
//# sourceMappingURL=index.js.3bcfc0fa808cb605777b.hot-update.js.map