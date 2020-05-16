webpackHotUpdate("static\\development\\pages\\index.js",{

/***/ "./pages/index.js":
/*!************************!*\
  !*** ./pages/index.js ***!
  \************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "default", function() { return Home; });
/* harmony import */ var _babel_runtime_helpers_esm_defineProperty__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @babel/runtime/helpers/esm/defineProperty */ "./node_modules/@babel/runtime/helpers/esm/defineProperty.js");
/* harmony import */ var next_head__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! next/head */ "./node_modules/next/dist/next-server/lib/head.js");
/* harmony import */ var next_head__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(next_head__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var _index_module_css__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./index.module.css */ "./pages/index.module.css");
/* harmony import */ var _index_module_css__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(_index_module_css__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! classnames */ "./node_modules/classnames/index.js");
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(classnames__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var _components_GenericModal_GenericModal__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../components/GenericModal/GenericModal */ "./components/GenericModal/GenericModal.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_5___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_5__);
/* harmony import */ var _components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../components/FormFields/GenericField */ "./components/FormFields/GenericField.js");
/* harmony import */ var axios__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! axios */ "./node_modules/axios/index.js");
/* harmony import */ var axios__WEBPACK_IMPORTED_MODULE_7___default = /*#__PURE__*/__webpack_require__.n(axios__WEBPACK_IMPORTED_MODULE_7__);
/* harmony import */ var _util_url__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../util/url */ "./util/url.js");
/* harmony import */ var _util_routes__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ../util/routes */ "./util/routes.js");
/* harmony import */ var next_router__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! next/router */ "./node_modules/next/dist/client/router.js");
/* harmony import */ var next_router__WEBPACK_IMPORTED_MODULE_10___default = /*#__PURE__*/__webpack_require__.n(next_router__WEBPACK_IMPORTED_MODULE_10__);


var _jsxFileName = "C:\\Users\\USER\\Desktop\\NWT-Team-Storm\\stripomanija-frontend\\pages\\index.js",
    _this = undefined;

var __jsx = react__WEBPACK_IMPORTED_MODULE_5___default.a.createElement;

function ownKeys(object, enumerableOnly) { var keys = Object.keys(object); if (Object.getOwnPropertySymbols) { var symbols = Object.getOwnPropertySymbols(object); if (enumerableOnly) symbols = symbols.filter(function (sym) { return Object.getOwnPropertyDescriptor(object, sym).enumerable; }); keys.push.apply(keys, symbols); } return keys; }

function _objectSpread(target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i] != null ? arguments[i] : {}; if (i % 2) { ownKeys(Object(source), true).forEach(function (key) { Object(_babel_runtime_helpers_esm_defineProperty__WEBPACK_IMPORTED_MODULE_0__["default"])(target, key, source[key]); }); } else if (Object.getOwnPropertyDescriptors) { Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)); } else { ownKeys(Object(source)).forEach(function (key) { Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key)); }); } } return target; }











function Home() {
  var _useState = Object(react__WEBPACK_IMPORTED_MODULE_5__["useState"])(false),
      isRegisterModalOpen = _useState[0],
      setIsRegisterModalOpen = _useState[1];

  var _useState2 = Object(react__WEBPACK_IMPORTED_MODULE_5__["useState"])(false),
      isLoginModalOpen = _useState2[0],
      setIsLoginModalOpen = _useState2[1];

  return __jsx(react__WEBPACK_IMPORTED_MODULE_5___default.a.Fragment, null, isRegisterModalOpen && __jsx(RegistrationModal, {
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
    className: _index_module_css__WEBPACK_IMPORTED_MODULE_2___default.a.container,
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 21,
      columnNumber: 13
    }
  }, __jsx(next_head__WEBPACK_IMPORTED_MODULE_1___default.a, {
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
    className: _index_module_css__WEBPACK_IMPORTED_MODULE_2___default.a.upperSection,
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
    className: _index_module_css__WEBPACK_IMPORTED_MODULE_2___default.a.buttons,
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 28,
      columnNumber: 21
    }
  }, __jsx("button", {
    type: "button",
    className: classnames__WEBPACK_IMPORTED_MODULE_3___default()("btn btn-primary btn-lg", _index_module_css__WEBPACK_IMPORTED_MODULE_2___default.a.button),
    onClick: function onClick() {
      return setIsRegisterModalOpen(true);
    },
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 29,
      columnNumber: 25
    }
  }, "Registracija"), __jsx("button", {
    type: "button",
    className: classnames__WEBPACK_IMPORTED_MODULE_3___default()("btn btn-primary btn-lg", _index_module_css__WEBPACK_IMPORTED_MODULE_2___default.a.space, _index_module_css__WEBPACK_IMPORTED_MODULE_2___default.a.button),
    onClick: function onClick() {
      return setIsLoginModalOpen(true);
    },
    __self: this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 31,
      columnNumber: 25
    }
  }, "Login")))));
} //ne radi jos

var RegistrationModal = function RegistrationModal(_ref) {
  var setIsRegisterModalOpen = _ref.setIsRegisterModalOpen;

  var _useState3 = Object(react__WEBPACK_IMPORTED_MODULE_5__["useState"])(null),
      validationMsg = _useState3[0],
      setValidationMsg = _useState3[1];

  var _useState4 = Object(react__WEBPACK_IMPORTED_MODULE_5__["useState"])(false),
      isInvalid = _useState4[0],
      setIsInvalid = _useState4[1]; //role povuci sa apija!!


  var _useState5 = Object(react__WEBPACK_IMPORTED_MODULE_5__["useState"])({
    role: "ROLE_USER",
    ime: "",
    prezime: "",
    userName: "",
    sifra: "",
    email: "",
    broj_losih_reviewa: 0,
    ukupno_reviewa: 0
  }),
      user = _useState5[0],
      setUser = _useState5[1];

  return __jsx(_components_GenericModal_GenericModal__WEBPACK_IMPORTED_MODULE_4__["default"], {
    modalTitle: "Registracija",
    closeModal: function closeModal() {
      return setIsRegisterModalOpen(false);
    },
    showModal: function showModal() {
      return setIsRegisterModalOpen(true);
    },
    bottomText: "Želimo Vam ugodno iskustvo na Stripomaniji!",
    btnText: "Registruj me!",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 56,
      columnNumber: 12
    }
  }, __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_6__["default"], {
    id: "ime",
    name: "ime",
    label: "Ime",
    placeholder: "Vaše ime",
    type: "text",
    onChange: function onChange(e) {
      return handleFieldChange(e, user, setUser);
    },
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 62,
      columnNumber: 13
    }
  }), __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_6__["default"], {
    id: "prezime",
    name: "prezime",
    label: "Prezime",
    placeholder: "Vaše prezime",
    type: "text",
    onChange: function onChange(e) {
      return handleFieldChange(e, user, setUser);
    },
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 63,
      columnNumber: 13
    }
  }), __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_6__["default"], {
    id: "email",
    name: "email",
    label: "Email adresa",
    placeholder: "Npr. jane@doe.com",
    type: "email",
    onChange: function onChange(e) {
      return handleFieldChange(e, user, setUser);
    },
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 64,
      columnNumber: 13
    }
  }), __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_6__["default"], {
    id: "usernameRegistracija",
    name: "userName",
    label: "Username",
    placeholder: "Pomoću username-a se prijavljujete na Stripomaniju.",
    type: "text",
    onChange: function onChange(e) {
      return handleFieldChange(e, user, setUser);
    },
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 65,
      columnNumber: 13
    }
  }), __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_6__["default"], {
    id: "sifraRegistracija",
    name: "sifra",
    label: "Šifra",
    placeholder: "Vaša šifra",
    type: "password",
    onChange: function onChange(e) {
      return handleFieldChange(e, user, setUser);
    },
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 66,
      columnNumber: 13
    }
  }), __jsx("div", {
    className: "d-flex w-100 justify-content-end",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 67,
      columnNumber: 13
    }
  }, __jsx("button", {
    type: "button",
    className: "btn btn-primary",
    onClick: function onClick() {
      return sendRequest(_util_url__WEBPACK_IMPORTED_MODULE_8__["baseUrl"] + _util_routes__WEBPACK_IMPORTED_MODULE_9__["routes"].register.path, user, setValidationMsg, setIsInvalid);
    },
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 68,
      columnNumber: 17
    }
  }, "Predaj")));
};

var LoginModal = function LoginModal(_ref2) {
  var setIsLoginModalOpen = _ref2.setIsLoginModalOpen;

  var _useState6 = Object(react__WEBPACK_IMPORTED_MODULE_5__["useState"])(null),
      validationMsg = _useState6[0],
      setValidationMsg = _useState6[1];

  var _useState7 = Object(react__WEBPACK_IMPORTED_MODULE_5__["useState"])(false),
      isInvalid = _useState7[0],
      setIsInvalid = _useState7[1];

  var _useState8 = Object(react__WEBPACK_IMPORTED_MODULE_5__["useState"])({
    username: "",
    password: ""
  }),
      user = _useState8[0],
      setUser = _useState8[1];

  return __jsx(_components_GenericModal_GenericModal__WEBPACK_IMPORTED_MODULE_4__["default"], {
    modalTitle: "Prijava",
    btnText: "Predaj",
    showModal: function showModal() {
      return setIsLoginModalOpen(true);
    },
    closeModal: function closeModal() {
      return setIsLoginModalOpen(false);
    },
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 83,
      columnNumber: 9
    }
  }, __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_6__["default"], {
    id: "usernameLogin",
    name: "username",
    label: "Username",
    placeholder: "Vaš username",
    type: "text",
    isInvalid: isInvalid,
    onChange: function onChange(e) {
      return handleFieldChange(e, user, setUser);
    },
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 88,
      columnNumber: 13
    }
  }), __jsx(_components_FormFields_GenericField__WEBPACK_IMPORTED_MODULE_6__["default"], {
    id: "sifraLogin",
    name: "password",
    label: "Šifra",
    placeholder: "Vaša šifra",
    type: "password",
    isInvalid: isInvalid,
    validationMsg: validationMsg,
    onChange: function onChange(e) {
      return handleFieldChange(e, user, setUser);
    },
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 89,
      columnNumber: 13
    }
  }), __jsx("div", {
    className: "d-flex w-100 justify-content-end",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 90,
      columnNumber: 13
    }
  }, __jsx("button", {
    type: "button",
    className: "btn btn-primary",
    onClick: function onClick() {
      return sendRequest(_util_url__WEBPACK_IMPORTED_MODULE_8__["baseUrl"] + _util_routes__WEBPACK_IMPORTED_MODULE_9__["routes"].authenticate.path, user, setValidationMsg, setIsInvalid);
    },
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 91,
      columnNumber: 17
    }
  }, "Predaj")));
};

function handleFieldChange(e, user, setUser) {
  var _e$target = e.target,
      name = _e$target.name,
      value = _e$target.value;
  setUser(function (prevState) {
    return _objectSpread({}, prevState, Object(_babel_runtime_helpers_esm_defineProperty__WEBPACK_IMPORTED_MODULE_0__["default"])({}, name, value));
  });
}

function sendRequest(url, reqBody, setValidationMsg, setIsInvalid) {
  axios__WEBPACK_IMPORTED_MODULE_7___default.a.post(url, reqBody).then(function (response) {
    // save token to local storage
    localStorage.setItem("jwt", response.data.jwt);
    setValidationMsg(null);
    setIsInvalid(false);
    next_router__WEBPACK_IMPORTED_MODULE_10___default.a.push(_util_routes__WEBPACK_IMPORTED_MODULE_9__["navbarRoutes"].katalozi.path);
  })["catch"](function (error) {
    console.log(error);

    if (error.response.status === 400) {
      //validacija
      setIsInvalid(true);
      setValidationMsg(error.response.data.message);
    }

    console.log(error.response.data.message);
  });
}

/***/ })

})
//# sourceMappingURL=index.js.99878477ea0a90e9fc74.hot-update.js.map