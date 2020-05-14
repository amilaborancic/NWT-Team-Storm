webpackHotUpdate("static\\development\\pages\\index.js",{

/***/ "./components/FormFields/GenericField.js":
/*!***********************************************!*\
  !*** ./components/FormFields/GenericField.js ***!
  \***********************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! react */ "./node_modules/react/index.js");
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! classnames */ "./node_modules/classnames/index.js");
/* harmony import */ var classnames__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(classnames__WEBPACK_IMPORTED_MODULE_1__);
var _this = undefined,
    _jsxFileName = "C:\\Users\\USER\\Desktop\\NWT-Team-Storm\\stripomanija-frontend\\components\\FormFields\\GenericField.js";

var __jsx = react__WEBPACK_IMPORTED_MODULE_0___default.a.createElement;



var GenericField = function GenericField(_ref) {
  var type = _ref.type,
      id = _ref.id,
      label = _ref.label,
      placeholder = _ref.placeholder,
      onChange = _ref.onChange,
      name = _ref.name,
      validationMsg = _ref.validationMsg;
  return __jsx("div", {
    className: "form-group",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 6,
      columnNumber: 9
    }
  }, __jsx("label", {
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 7,
      columnNumber: 13
    }
  }, label), __jsx("input", {
    type: type,
    className: classnames__WEBPACK_IMPORTED_MODULE_1___default()("form-control", {
      "is-invalid": validationMsg
    }),
    id: id,
    placeholder: placeholder,
    onChange: onChange,
    name: name,
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 8,
      columnNumber: 13
    }
  }), __jsx("div", {
    className: "col-sm-3",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 9,
      columnNumber: 13
    }
  }, __jsx("small", {
    id: "".concat(id, "validation"),
    className: "text-danger",
    __self: _this,
    __source: {
      fileName: _jsxFileName,
      lineNumber: 10,
      columnNumber: 17
    }
  }, validationMsg)));
};

/* harmony default export */ __webpack_exports__["default"] = (GenericField);

/***/ })

})
//# sourceMappingURL=index.js.e165c9fbe5db29537f8e.hot-update.js.map