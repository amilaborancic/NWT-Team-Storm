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
/******/ 	return __webpack_require__(__webpack_require__.s = 4);
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
var _jsxFileName = "C:\\Users\\USER\\Desktop\\NWT-Team-Storm\\stripomanija-frontend\\components\\FormFields\\GenericField.js";
var __jsx = react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement;


const GenericField = ({
  type,
  id,
  label,
  placeholder
}) => {
  return __jsx("div", {
    className: "form-group",
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 5,
      columnNumber: 9
    }
  }, __jsx("label", {
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 6,
      columnNumber: 13
    }
  }, label), __jsx("input", {
    type: type,
    className: "form-control",
    id: id,
    placeholder: placeholder,
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 7,
      columnNumber: 13
    }
  }));
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
  bottomText,
  btnText
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
  }, children), __jsx(react_bootstrap__WEBPACK_IMPORTED_MODULE_1__["Modal"].Footer, {
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 16,
      columnNumber: 13
    }
  }, __jsx("button", {
    type: "button",
    className: "btn btn-primary",
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 17,
      columnNumber: 17
    }
  }, btnText)));
};

const ModalBody = ({
  children,
  text
}) => {
  return __jsx(react_bootstrap__WEBPACK_IMPORTED_MODULE_1__["Modal"].Body, {
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 24,
      columnNumber: 13
    }
  }, children, __jsx("small", {
    className: _GenericModal_module_css__WEBPACK_IMPORTED_MODULE_2___default.a.smallTxt,
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 26,
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
var _jsxFileName = "C:\\Users\\USER\\Desktop\\NWT-Team-Storm\\stripomanija-frontend\\pages\\index.js";
var __jsx = react__WEBPACK_IMPORTED_MODULE_4___default.a.createElement;






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
      lineNumber: 13,
      columnNumber: 37
    }
  }), isLoginModalOpen && __jsx(LoginModal, {
    setIsLoginModalOpen: setIsLoginModalOpen,
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 14,
      columnNumber: 34
    }
  }), __jsx("div", {
    className: _index_module_css__WEBPACK_IMPORTED_MODULE_1___default.a.container,
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 15,
      columnNumber: 13
    }
  }, __jsx(next_head__WEBPACK_IMPORTED_MODULE_0___default.a, {
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 16,
      columnNumber: 17
    }
  }, __jsx("title", {
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 17,
      columnNumber: 21
    }
  }, "Stripomanija"), __jsx("link", {
    rel: "icon",
    href: "/favicon.ico",
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 18,
      columnNumber: 21
    }
  })), __jsx("div", {
    className: _index_module_css__WEBPACK_IMPORTED_MODULE_1___default.a.upperSection,
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 20,
      columnNumber: 17
    }
  }, __jsx("div", {
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 21,
      columnNumber: 21
    }
  }, "STRIPOMANIJA"), __jsx("div", {
    className: _index_module_css__WEBPACK_IMPORTED_MODULE_1___default.a.buttons,
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 22,
      columnNumber: 21
    }
  }, __jsx("button", {
    type: "button",
    className: classnames__WEBPACK_IMPORTED_MODULE_2___default()("btn btn-primary btn-lg", _index_module_css__WEBPACK_IMPORTED_MODULE_1___default.a.button),
    onClick: () => setIsRegisterModalOpen(true),
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 23,
      columnNumber: 25
    }
  }, "Registracija"), __jsx("button", {
    type: "button",
    className: classnames__WEBPACK_IMPORTED_MODULE_2___default()("btn btn-primary btn-lg", _index_module_css__WEBPACK_IMPORTED_MODULE_1___default.a.space, _index_module_css__WEBPACK_IMPORTED_MODULE_1___default.a.button),
    onClick: () => setIsLoginModalOpen(true),
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 25,
      columnNumber: 25
    }
  }, "Login")))));
}

const RegistrationModal = ({
  setIsRegisterModalOpen
}) => {
  return __jsx(_components_GenericModal_GenericModal__WEBPACK_IMPORTED_MODULE_3__["default"], {
    modalTitle: "Registracija",
    closeModal: () => setIsRegisterModalOpen(false),
    showModal: () => setIsRegisterModalOpen(true),
    bottomText: "Želimo Vam ugodno iskustvo na Stripomaniji!",
    btnText: "Registruj me!",
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 35,
      columnNumber: 12
    }
  }, __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_5__["default"], {
    id: "ime",
    label: "Ime",
    placeholder: "Vaše ime",
    type: "text",
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 41,
      columnNumber: 13
    }
  }), __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_5__["default"], {
    id: "prezime",
    label: "Prezime",
    placeholder: "Vaše prezime",
    type: "text",
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 42,
      columnNumber: 13
    }
  }), __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_5__["default"], {
    id: "email",
    label: "Email adresa",
    placeholder: "Npr. jane@doe.com",
    type: "email",
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 43,
      columnNumber: 13
    }
  }), __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_5__["default"], {
    id: "usernameRegistracija",
    label: "Username",
    placeholder: "Pomoću username-a se prijavljujete na Stripomaniju.",
    type: "text",
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 44,
      columnNumber: 13
    }
  }), __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_5__["default"], {
    id: "sifraRegistracija",
    label: "Šifra",
    placeholder: "Vaša šifra",
    type: "password",
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 45,
      columnNumber: 13
    }
  }));
};

const LoginModal = ({
  setIsLoginModalOpen
}) => {
  return __jsx(_components_GenericModal_GenericModal__WEBPACK_IMPORTED_MODULE_3__["default"], {
    modalTitle: "Prijava",
    btnText: "Predaj",
    showModal: () => setIsLoginModalOpen(true),
    closeModal: () => setIsLoginModalOpen(false),
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 51,
      columnNumber: 12
    }
  }, __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_5__["default"], {
    id: "usernameLogin",
    label: "Username",
    placeholder: "Vaš username",
    type: "text",
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 57,
      columnNumber: 13
    }
  }), __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_5__["default"], {
    id: "sifraLogin",
    label: "Šifra",
    placeholder: "Vaša šifra",
    type: "password",
    __self: undefined,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 58,
      columnNumber: 13
    }
  }));
};

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

/***/ 4:
/*!******************************!*\
  !*** multi ./pages/index.js ***!
  \******************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! C:\Users\USER\Desktop\NWT-Team-Storm\stripomanija-frontend\pages\index.js */"./pages/index.js");


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