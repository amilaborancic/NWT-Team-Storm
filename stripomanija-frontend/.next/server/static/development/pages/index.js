module.exports =
/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = require('../../../ssr-module-cache.js');
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		var threw = true;
/******/ 		try {
/******/ 			modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/ 			threw = false;
/******/ 		} finally {
/******/ 			if(threw) delete installedModules[moduleId];
/******/ 		}
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, { enumerable: true, get: getter });
/******/ 		}
/******/ 	};
/******/
/******/ 	// define __esModule on exports
/******/ 	__webpack_require__.r = function(exports) {
/******/ 		if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 			Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 		}
/******/ 		Object.defineProperty(exports, '__esModule', { value: true });
/******/ 	};
/******/
/******/ 	// create a fake namespace object
/******/ 	// mode & 1: value is a module id, require it
/******/ 	// mode & 2: merge all properties of value into the ns
/******/ 	// mode & 4: return value when already ns object
/******/ 	// mode & 8|1: behave like require
/******/ 	__webpack_require__.t = function(value, mode) {
/******/ 		if(mode & 1) value = __webpack_require__(value);
/******/ 		if(mode & 8) return value;
/******/ 		if((mode & 4) && typeof value === 'object' && value && value.__esModule) return value;
/******/ 		var ns = Object.create(null);
/******/ 		__webpack_require__.r(ns);
/******/ 		Object.defineProperty(ns, 'default', { enumerable: true, value: value });
/******/ 		if(mode & 2 && typeof value != 'string') for(var key in value) __webpack_require__.d(ns, key, function(key) { return value[key]; }.bind(null, key));
/******/ 		return ns;
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 3);
/******/ })
/************************************************************************/
/******/ ({

/***/ "./components/FormFields/GenericField.js":
/*!***********************************************!*\
  !*** ./components/FormFields/GenericField.js ***!
  \***********************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "react");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! classnames */ "classnames");
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(classnames__WEBPACK_IMPORTED_MODULE_1__);
var _jsxFileName = "C:\\Users\\USER\\Desktop\\NWT-Team-Storm\\stripomanija-frontend\\components\\FormFields\\GenericField.js";
var __jsx = react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement;



const GenericField = ({
  type,
  id,
  label,
  placeholder,
  onChange,
  name,
  isInvalid,
  validationMsg
}) => {
  return __jsx("div", {
    className: "form-group",
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 6,
      columnNumber: 9
    }
  }, __jsx("label", {
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 7,
      columnNumber: 13
    }
  }, label), __jsx("input", {
    type: type,
    className: classnames__WEBPACK_IMPORTED_MODULE_1___default()("form-control", {
      "is-invalid": isInvalid
    }),
    id: id,
    placeholder: placeholder,
    onChange: onChange,
    name: name,
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 8,
      columnNumber: 13
    }
  }), __jsx("div", {
    className: "col",
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 9,
      columnNumber: 13
    }
  }, __jsx("small", {
    id: `${id}validation`,
    className: "text-danger",
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 10,
      columnNumber: 17
    }
  }, validationMsg)));
};

/* harmony default export */ __webpack_exports__["default"] = (GenericField);

/***/ }),

/***/ "./components/GenericModal/GenericModal.js":
/*!*************************************************!*\
  !*** ./components/GenericModal/GenericModal.js ***!
  \*************************************************/
/*! exports provided: ModalBody, default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ModalBody", function() { return ModalBody; });
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "react");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react_bootstrap__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! react-bootstrap */ "react-bootstrap");
/* harmony import */ var react_bootstrap__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(react_bootstrap__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var _GenericModal_module_css__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./GenericModal.module.css */ "./components/GenericModal/GenericModal.module.css");
/* harmony import */ var _GenericModal_module_css__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(_GenericModal_module_css__WEBPACK_IMPORTED_MODULE_2__);
var _jsxFileName = "C:\\Users\\USER\\Desktop\\NWT-Team-Storm\\stripomanija-frontend\\components\\GenericModal\\GenericModal.js";
var __jsx = react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement;




const GenericModal = ({
  children,
  modalTitle,
  closeModal,
  showModal,
  bottomText
}) => {
  return __jsx(react_bootstrap__WEBPACK_IMPORTED_MODULE_1__["Modal"], {
    show: showModal,
    onHide: closeModal,
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 7,
      columnNumber: 9
    }
  }, __jsx(react_bootstrap__WEBPACK_IMPORTED_MODULE_1__["Modal"].Header, {
    closeButton: true,
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 8,
      columnNumber: 13
    }
  }, __jsx(react_bootstrap__WEBPACK_IMPORTED_MODULE_1__["Modal"].Title, {
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 9,
      columnNumber: 17
    }
  }, modalTitle)), __jsx(ModalBody, {
    text: bottomText,
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 12,
      columnNumber: 13
    }
  }, children));
};

const ModalBody = ({
  children,
  text
}) => {
  return __jsx(react_bootstrap__WEBPACK_IMPORTED_MODULE_1__["Modal"].Body, {
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 21,
      columnNumber: 13
    }
  }, children, __jsx("small", {
    className: _GenericModal_module_css__WEBPACK_IMPORTED_MODULE_2___default.a.smallTxt,
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 23,
      columnNumber: 9
    }
  }, text));
};
/* harmony default export */ __webpack_exports__["default"] = (GenericModal);

/***/ }),

/***/ "./components/GenericModal/GenericModal.module.css":
/*!*********************************************************!*\
  !*** ./components/GenericModal/GenericModal.module.css ***!
  \*********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

// Exports
module.exports = {
	"smallTxt": "GenericModal_smallTxt__3Tuzm"
};

/***/ }),

/***/ "./pages/index.js":
/*!************************!*\
  !*** ./pages/index.js ***!
  \************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "default", function() { return Home; });
/* harmony import */ var next_head__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! next/head */ "next/head");
/* harmony import */ var next_head__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(next_head__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var _index_module_css__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./index.module.css */ "./pages/index.module.css");
/* harmony import */ var _index_module_css__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(_index_module_css__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! classnames */ "classnames");
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(classnames__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var _components_GenericModal_GenericModal__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../components/GenericModal/GenericModal */ "./components/GenericModal/GenericModal.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! react */ "react");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_4__);
/* harmony import */ var _components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../components/FormFields/GenericField */ "./components/FormFields/GenericField.js");
/* harmony import */ var axios__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! axios */ "axios");
/* harmony import */ var axios__WEBPACK_IMPORTED_MODULE_6___default = /*#__PURE__*/__webpack_require__.n(axios__WEBPACK_IMPORTED_MODULE_6__);
/* harmony import */ var _util_url__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../util/url */ "./util/url.js");
/* harmony import */ var _util_routes__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../util/routes */ "./util/routes.js");
/* harmony import */ var next_router__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! next/router */ "next/router");
/* harmony import */ var next_router__WEBPACK_IMPORTED_MODULE_9___default = /*#__PURE__*/__webpack_require__.n(next_router__WEBPACK_IMPORTED_MODULE_9__);
var _jsxFileName = "C:\\Users\\USER\\Desktop\\NWT-Team-Storm\\stripomanija-frontend\\pages\\index.js";
var __jsx = react__WEBPACK_IMPORTED_MODULE_4___default.a.createElement;

function ownKeys(object, enumerableOnly) { var keys = Object.keys(object); if (Object.getOwnPropertySymbols) { var symbols = Object.getOwnPropertySymbols(object); if (enumerableOnly) symbols = symbols.filter(function (sym) { return Object.getOwnPropertyDescriptor(object, sym).enumerable; }); keys.push.apply(keys, symbols); } return keys; }

function _objectSpread(target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i] != null ? arguments[i] : {}; if (i % 2) { ownKeys(Object(source), true).forEach(function (key) { _defineProperty(target, key, source[key]); }); } else if (Object.getOwnPropertyDescriptors) { Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)); } else { ownKeys(Object(source)).forEach(function (key) { Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key)); }); } } return target; }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }











function Home() {
  const {
    0: isRegisterModalOpen,
    1: setIsRegisterModalOpen
  } = Object(react__WEBPACK_IMPORTED_MODULE_4__["useState"])(false);
  const {
    0: isLoginModalOpen,
    1: setIsLoginModalOpen
  } = Object(react__WEBPACK_IMPORTED_MODULE_4__["useState"])(false);
  return __jsx(react__WEBPACK_IMPORTED_MODULE_4___default.a.Fragment, null, isRegisterModalOpen && __jsx(RegistrationModal, {
    setIsRegisterModalOpen: setIsRegisterModalOpen,
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 19,
      columnNumber: 37
    }
  }), isLoginModalOpen && __jsx(LoginModal, {
    setIsLoginModalOpen: setIsLoginModalOpen,
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 20,
      columnNumber: 34
    }
  }), __jsx("div", {
    className: _index_module_css__WEBPACK_IMPORTED_MODULE_1___default.a.container,
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 21,
      columnNumber: 13
    }
  }, __jsx(next_head__WEBPACK_IMPORTED_MODULE_0___default.a, {
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 22,
      columnNumber: 17
    }
  }, __jsx("title", {
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 23,
      columnNumber: 21
    }
  }, "Stripomanija"), __jsx("link", {
    rel: "icon",
    href: "/favicon.ico",
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 24,
      columnNumber: 21
    }
  })), __jsx("div", {
    className: _index_module_css__WEBPACK_IMPORTED_MODULE_1___default.a.upperSection,
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 26,
      columnNumber: 17
    }
  }, __jsx("div", {
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 27,
      columnNumber: 21
    }
  }, "STRIPOMANIJA"), __jsx("div", {
    className: _index_module_css__WEBPACK_IMPORTED_MODULE_1___default.a.buttons,
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 28,
      columnNumber: 21
    }
  }, __jsx("button", {
    type: "button",
    className: classnames__WEBPACK_IMPORTED_MODULE_2___default()("btn btn-primary btn-lg", _index_module_css__WEBPACK_IMPORTED_MODULE_1___default.a.button),
    onClick: () => setIsRegisterModalOpen(true),
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 29,
      columnNumber: 25
    }
  }, "Registracija"), __jsx("button", {
    type: "button",
    className: classnames__WEBPACK_IMPORTED_MODULE_2___default()("btn btn-primary btn-lg", _index_module_css__WEBPACK_IMPORTED_MODULE_1___default.a.space, _index_module_css__WEBPACK_IMPORTED_MODULE_1___default.a.button),
    onClick: () => setIsLoginModalOpen(true),
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 31,
      columnNumber: 25
    }
  }, "Login")))));
} //ne radi jos

const RegistrationModal = ({
  setIsRegisterModalOpen
}) => {
  const {
    0: validationMsg,
    1: setValidationMsg
  } = Object(react__WEBPACK_IMPORTED_MODULE_4__["useState"])(null);
  const {
    0: isInvalid,
    1: setIsInvalid
  } = Object(react__WEBPACK_IMPORTED_MODULE_4__["useState"])(false); //role povuci sa apija!!

  const {
    0: user,
    1: setUser
  } = Object(react__WEBPACK_IMPORTED_MODULE_4__["useState"])({
    role: "ROLE_USER",
    ime: "",
    prezime: "",
    userName: "",
    sifra: "",
    email: "",
    broj_losih_reviewa: 0,
    ukupno_reviewa: 0
  });
  return __jsx(_components_GenericModal_GenericModal__WEBPACK_IMPORTED_MODULE_3__["default"], {
    modalTitle: "Registracija",
    closeModal: () => setIsRegisterModalOpen(false),
    showModal: () => setIsRegisterModalOpen(true),
    bottomText: "Želimo Vam ugodno iskustvo na Stripomaniji!",
    btnText: "Registruj me!",
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 56,
      columnNumber: 12
    }
  }, __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_5__["default"], {
    id: "ime",
    name: "ime",
    label: "Ime",
    placeholder: "Vaše ime",
    type: "text",
    onChange: e => handleFieldChange(e, user, setUser),
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 62,
      columnNumber: 13
    }
  }), __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_5__["default"], {
    id: "prezime",
    name: "prezime",
    label: "Prezime",
    placeholder: "Vaše prezime",
    type: "text",
    onChange: e => handleFieldChange(e, user, setUser),
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 63,
      columnNumber: 13
    }
  }), __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_5__["default"], {
    id: "email",
    name: "email",
    label: "Email adresa",
    placeholder: "Npr. jane@doe.com",
    type: "email",
    onChange: e => handleFieldChange(e, user, setUser),
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 64,
      columnNumber: 13
    }
  }), __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_5__["default"], {
    id: "usernameRegistracija",
    name: "userName",
    label: "Username",
    placeholder: "Pomoću username-a se prijavljujete na Stripomaniju.",
    type: "text",
    onChange: e => handleFieldChange(e, user, setUser),
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 65,
      columnNumber: 13
    }
  }), __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_5__["default"], {
    id: "sifraRegistracija",
    name: "sifra",
    label: "Šifra",
    placeholder: "Vaša šifra",
    type: "password",
    onChange: e => handleFieldChange(e, user, setUser),
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 66,
      columnNumber: 13
    }
  }), __jsx("div", {
    className: "d-flex w-100 justify-content-end",
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 67,
      columnNumber: 13
    }
  }, __jsx("button", {
    type: "button",
    className: "btn btn-primary",
    onClick: () => sendRequest(_util_url__WEBPACK_IMPORTED_MODULE_7__["baseUrl"] + _util_routes__WEBPACK_IMPORTED_MODULE_8__["routes"].register.path, user, setValidationMsg, setIsInvalid),
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 68,
      columnNumber: 17
    }
  }, "Predaj")));
};

const LoginModal = ({
  setIsLoginModalOpen
}) => {
  const {
    0: validationMsg,
    1: setValidationMsg
  } = Object(react__WEBPACK_IMPORTED_MODULE_4__["useState"])(null);
  const {
    0: isInvalid,
    1: setIsInvalid
  } = Object(react__WEBPACK_IMPORTED_MODULE_4__["useState"])(false);
  const {
    0: user,
    1: setUser
  } = Object(react__WEBPACK_IMPORTED_MODULE_4__["useState"])({
    username: "",
    password: ""
  });
  return __jsx(_components_GenericModal_GenericModal__WEBPACK_IMPORTED_MODULE_3__["default"], {
    modalTitle: "Prijava",
    btnText: "Predaj",
    showModal: () => setIsLoginModalOpen(true),
    closeModal: () => setIsLoginModalOpen(false),
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 83,
      columnNumber: 9
    }
  }, __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_5__["default"], {
    id: "usernameLogin",
    name: "username",
    label: "Username",
    placeholder: "Vaš username",
    type: "text",
    isInvalid: isInvalid,
    onChange: e => handleFieldChange(e, user, setUser),
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 88,
      columnNumber: 13
    }
  }), __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_5__["default"], {
    id: "sifraLogin",
    name: "password",
    label: "Šifra",
    placeholder: "Vaša šifra",
    type: "password",
    isInvalid: isInvalid,
    validationMsg: validationMsg,
    onChange: e => handleFieldChange(e, user, setUser),
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 89,
      columnNumber: 13
    }
  }), __jsx("div", {
    className: "d-flex w-100 justify-content-end",
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 90,
      columnNumber: 13
    }
  }, __jsx("button", {
    type: "button",
    className: "btn btn-primary",
    onClick: () => sendRequest(_util_url__WEBPACK_IMPORTED_MODULE_7__["baseUrl"] + _util_routes__WEBPACK_IMPORTED_MODULE_8__["routes"].authenticate.path, user, setValidationMsg, setIsInvalid),
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 91,
      columnNumber: 17
    }
  }, "Predaj")));
};

function handleFieldChange(e, user, setUser) {
  const {
    name,
    value
  } = e.target;
  setUser(prevState => _objectSpread({}, prevState, {
    [name]: value
  }));
}

function sendRequest(url, reqBody, setValidationMsg, setIsInvalid) {
  axios__WEBPACK_IMPORTED_MODULE_6___default.a.post(url, reqBody).then(response => {
    // save token to local storage
    localStorage.setItem("jwt", response.data.jwt);
    setValidationMsg(null);
    setIsInvalid(false);
    next_router__WEBPACK_IMPORTED_MODULE_9___default.a.push(_util_routes__WEBPACK_IMPORTED_MODULE_8__["navbarRoutes"].katalozi.path);
  }).catch(error => {
    console.log(error);

    if (error.response.status === 400) {
      //validacija
      setIsInvalid(true);
      setValidationMsg(error.response.data.message);
    }

    console.log(error.response.data.message);
  });
}

/***/ }),

/***/ "./pages/index.module.css":
/*!********************************!*\
  !*** ./pages/index.module.css ***!
  \********************************/
/*! no static exports found */
/***/ (function(module, exports) {

// Exports
module.exports = {
	"container": "index_container__3XRMM",
	"upperSection": "index_upperSection__B2qHW",
	"space": "index_space__38vhU",
	"buttons": "index_buttons__1bs4F",
	"button": "index_button__14f7q"
};

/***/ }),

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

const navbarRoutes = {
  home: {
    label: "Home",
    path: "/"
  },
  katalozi: {
    label: "Moji katalozi",
    path: "/katalozi"
  }
};
const routes = {
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
    path: `${_url__WEBPACK_IMPORTED_MODULE_0__["user"]}/sign-up`
  },
  katalozi: {
    label: "katalozi",
    path: `${_url__WEBPACK_IMPORTED_MODULE_0__["catalogue"]}/svi`
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
const baseUrl = "http://localhost:8086";
const user = "/user";
const catalogue = "/katalog";
const comicbook = "/strip";

/***/ }),

/***/ 3:
/*!******************************!*\
  !*** multi ./pages/index.js ***!
  \******************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! C:\Users\USER\Desktop\NWT-Team-Storm\stripomanija-frontend\pages\index.js */"./pages/index.js");


/***/ }),

/***/ "axios":
/*!************************!*\
  !*** external "axios" ***!
  \************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = require("axios");

/***/ }),

/***/ "classnames":
/*!*****************************!*\
  !*** external "classnames" ***!
  \*****************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = require("classnames");

/***/ }),

/***/ "next/head":
/*!****************************!*\
  !*** external "next/head" ***!
  \****************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = require("next/head");

/***/ }),

/***/ "next/router":
/*!******************************!*\
  !*** external "next/router" ***!
  \******************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = require("next/router");

/***/ }),

/***/ "react":
/*!************************!*\
  !*** external "react" ***!
  \************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = require("react");

/***/ }),

/***/ "react-bootstrap":
/*!**********************************!*\
  !*** external "react-bootstrap" ***!
  \**********************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = require("react-bootstrap");

/***/ })

/******/ });
//# sourceMappingURL=index.js.map