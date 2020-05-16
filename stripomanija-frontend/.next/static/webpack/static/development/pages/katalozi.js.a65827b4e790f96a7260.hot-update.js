webpackHotUpdate("static\\development\\pages\\katalozi.js",{

/***/ "./components/NavbarContainer/NavbarContainer.js":
/*!*******************************************************!*\
  !*** ./components/NavbarContainer/NavbarContainer.js ***!
  \*******************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _NavbarContainer_module_css__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./NavbarContainer.module.css */ "./components/NavbarContainer/NavbarContainer.module.css");
/* harmony import */ var _NavbarContainer_module_css__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(_NavbarContainer_module_css__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! classnames */ "./node_modules/classnames/index.js");
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(classnames__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var next_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! next/router */ "./node_modules/next/dist/client/router.js");
/* harmony import */ var next_router__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(next_router__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var _util_routes__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../util/routes */ "./util/routes.js");
var _this = undefined,
    _jsxFileName = "C:\\Users\\USER\\Desktop\\NWT-Team-Storm\\stripomanija-frontend\\components\\NavbarContainer\\NavbarContainer.js";

var __jsx = react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement;






var NavbarContainer = function NavbarContainer(_ref) {
  var children = _ref.children;
  var router = Object(next_router__WEBPACK_IMPORTED_MODULE_3__["useRouter"])();

  var _useState = Object(react__WEBPACK_IMPORTED_MODULE_0__["useState"])(false),
      isDropDownClicked = _useState[0],
      setIsDropDownClicked = _useState[1];

  return __jsx("div", {
    className: _NavbarContainer_module_css__WEBPACK_IMPORTED_MODULE_1___default.a.container,
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 12,
      columnNumber: 9
    }
  }, __jsx("nav", {
    className: "navbar navbar-expand-lg navbar-dark bg-primary",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 13,
      columnNumber: 13
    }
  }, __jsx("a", {
    className: "navbar-brand",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 14,
      columnNumber: 17
    }
  }, "STRIPOMANIJA"), __jsx("button", {
    className: "navbar-toggler",
    type: "button",
    "data-toggle": "collapse",
    "data-target": "#navbarColor01",
    "aria-controls": "navbarColor01",
    "aria-expanded": "false",
    "aria-label": "Toggle navigation",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 15,
      columnNumber: 17
    }
  }), __jsx("div", {
    className: "collapse navbar-collapse",
    id: "navbarColor01",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 19,
      columnNumber: 17
    }
  }, __jsx("ul", {
    className: "navbar-nav mr-auto",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 20,
      columnNumber: 21
    }
  }, Object.values(_util_routes__WEBPACK_IMPORTED_MODULE_4__["navbarRoutes"]).map(function (route) {
    return __jsx("li", {
      className: classnames__WEBPACK_IMPORTED_MODULE_2___default()("nav-item", {
        "active": router.pathname === route.path
      }),
      key: route.path,
      __self: _this,
      __source: {
        fileName: _jsxFileName,
        lineNumber: 22,
        columnNumber: 29
      }
    }, __jsx("a", {
      className: "nav-link",
      href: route.path,
      __self: _this,
      __source: {
        fileName: _jsxFileName,
        lineNumber: 23,
        columnNumber: 33
      }
    }, route.label));
  })), __jsx("form", {
    className: "form-inline my-2 my-lg-0 w-50 d-flex justify-content-end mr-3",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 27,
      columnNumber: 21
    }
  }, __jsx("input", {
    className: "form-control mr-sm-2 w-75",
    type: "text",
    placeholder: "Pretraga",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 28,
      columnNumber: 25
    }
  }), __jsx("button", {
    className: classnames__WEBPACK_IMPORTED_MODULE_2___default()("btn my-2 my-sm-0", _NavbarContainer_module_css__WEBPACK_IMPORTED_MODULE_1___default.a.button),
    type: "submit",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 29,
      columnNumber: 29
    }
  }, "Tra\u017Ei!")), __jsx("ul", {
    className: "navbar-nav mr-2",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 31,
      columnNumber: 21
    }
  }, __jsx("li", {
    className: "nav-item dropdown show",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 32,
      columnNumber: 25
    }
  }, __jsx("a", {
    className: "nav-link dropdown-toggle",
    "data-toggle": "dropdown",
    href: "#",
    role: "button",
    onClick: function onClick() {
      return handleDropDownClick(setIsDropDownClicked, isDropDownClicked);
    },
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 33,
      columnNumber: 29
    }
  }, "Opcije"), __jsx("div", {
    className: classnames__WEBPACK_IMPORTED_MODULE_2___default()("dropdown-menu", {
      "show": isDropDownClicked
    }),
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 35,
      columnNumber: 29
    }
  }, __jsx("a", {
    className: "dropdown-item",
    href: "#",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 36,
      columnNumber: 33
    }
  }, "Odjava")))))), children);
};

var handleDropDownClick = function handleDropDownClick(setIsDropDownClicked, isDropDownClicked) {
  setIsDropDownClicked(!isDropDownClicked);
};

/* harmony default export */ __webpack_exports__["default"] = (NavbarContainer);

/***/ }),

/***/ "./node_modules/css-loader/dist/cjs.js?!./node_modules/next/dist/compiled/postcss-loader/index.js?!./components/NavbarContainer/NavbarContainer.module.css":
/*!******************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/css-loader/dist/cjs.js??ref--5-oneOf-2-1!./node_modules/next/dist/compiled/postcss-loader??__nextjs_postcss!./components/NavbarContainer/NavbarContainer.module.css ***!
  \******************************************************************************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(/*! ../../node_modules/css-loader/dist/runtime/api.js */ "./node_modules/css-loader/dist/runtime/api.js")(true);
// Module
exports.push([module.i, ".NavbarContainer_container__3cgbN {\r\n    width: 100vw;\r\n    height: 100vh;\r\n}\r\n\r\n.NavbarContainer_button__2rT8n {\r\n    color: var(--white) !important;\r\n    background-color: var(--dark-gray) !important;\r\n}\r\n\r\n.NavbarContainer_button__2rT8n:hover{\r\n    color: var(--white) !important;\r\n    background-color: var(--dark) !important;\r\n}\r\n\r\n.NavbarContainer_dropdown__3xLQf {\r\n    position: absolute;\r\n    transform: translate3d(0px, 39px, 0px) !important;\r\n    top: 0px;\r\n    left: 0px;\r\n    will-change: transform;\r\n}", "",{"version":3,"sources":["C:/Users/USER/Desktop/NWT-Team-Storm/stripomanija-frontend/components/NavbarContainer/NavbarContainer.module.css"],"names":[],"mappings":"AAAA;IACI,YAAY;IACZ,aAAa;AACjB;;AAEA;IACI,8BAA8B;IAC9B,6CAA6C;AACjD;;AAEA;IACI,8BAA8B;IAC9B,wCAAwC;AAC5C;;AAEA;IACI,kBAAkB;IAClB,iDAAiD;IACjD,QAAQ;IACR,SAAS;IACT,sBAAsB;AAC1B","file":"NavbarContainer.module.css","sourcesContent":[".container {\r\n    width: 100vw;\r\n    height: 100vh;\r\n}\r\n\r\n.button {\r\n    color: var(--white) !important;\r\n    background-color: var(--dark-gray) !important;\r\n}\r\n\r\n.button:hover{\r\n    color: var(--white) !important;\r\n    background-color: var(--dark) !important;\r\n}\r\n\r\n.dropdown {\r\n    position: absolute;\r\n    transform: translate3d(0px, 39px, 0px) !important;\r\n    top: 0px;\r\n    left: 0px;\r\n    will-change: transform;\r\n}"]}]);
// Exports
exports.locals = {
	"container": "NavbarContainer_container__3cgbN",
	"button": "NavbarContainer_button__2rT8n",
	"dropdown": "NavbarContainer_dropdown__3xLQf"
};

/***/ })

})
//# sourceMappingURL=katalozi.js.a65827b4e790f96a7260.hot-update.js.map