import { useState, useEffect } from "react";
import {
    BrowserRouter,
    Route,
    Routes,
} from "react-router-dom";

import App from "../../Pages/App";
import Home from "../../Pages/Home";
import About from "../../Pages/About";
import NotFound from "./NotFound";
import Header from '../App/Header.jsx';
import Register from "../Authorization/Register.jsx";
import Login from "../Authorization/Login.jsx";
import Request from "../Authorization/Request.jsx";
import Approve from "../App/Approve.jsx";

import { getToken, setTokenLS, logout } from "../Authorization/token.js";
import Add from "../App/Add.jsx";
import AddPerson from "../App/AddPerson.jsx";
import AddLocation from "../App/AddLocation.jsx";
import AddCoordinates from "../App/AddCoordinates.jsx";
import AddWrapper from "../App/AddWrapper.jsx";
import EditPerson from "../App/EditPerson.jsx";


export default function Router({ isAdmin }) {
    let token = getToken()

    if (!token) {
        return (
            <BrowserRouter>
                <Routes>
                    <Route path='*' element={<NotFound />} />
                    <Route path="/" element={<Header getToken={getToken} logout={logout}/>}>
                        <Route path="/home" element={<Home />} />
                        <Route path="/about" element={<About />} />
                    </Route>
                    <Route path="/register" element={<Register />} />
                    <Route path="/login" element={<Login setToken={setTokenLS} />} />
                </Routes>
            </BrowserRouter>
        );
    }

    return (
        <BrowserRouter>
            <Routes>
                <Route path='*' element={<NotFound />} />
                <Route path="/" element={<Header getToken={getToken} logout={logout} isAdmin={isAdmin} />}>
                    <Route path="/" element={<App getToken={getToken}/>} />
                    <Route path="/home" element={<Home />} />
                    <Route path="/about" element={<About />} />
                    <Route path="/request" element={<Request getToken={getToken} />} />
                    <Route path="/approve" element={<Approve getToken={getToken} />} />
                    <Route path="/add" element={<Add getToken={getToken} />} />
                    <Route path="/add/coordinates" element={<AddWrapper Component={AddCoordinates} getToken={getToken} />} />
                    <Route path="/add/location" element={<AddWrapper Component={AddLocation} getToken={getToken} />} />
                    <Route path="/add/person" element={<AddWrapper Component={AddPerson} getToken={getToken} />} />
                    <Route path="/edit/coordinates/:id" element={<EditPerson getToken={getToken} />} />
                    <Route path="/edit/location/:id" element={<EditPerson getToken={getToken} />} />
                    <Route path="/edit/person/:id" element={<EditPerson getToken={getToken} />} />
                </Route>
                <Route path="/register" element={<Register />} />
                <Route path="/login" element={<Login setToken={setTokenLS} />} />
            </Routes>
        </BrowserRouter>
    );
}
