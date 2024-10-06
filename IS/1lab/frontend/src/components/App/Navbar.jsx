import { useState, useEffect } from "react";
import { useNavigate } from 'react-router'
import axios from "axios";

import PropTypes from 'prop-types';
import { Menubar } from 'primereact/menubar';

export default function NavigationBar({start, getToken, logout}) {
    const navigate = useNavigate()    
    const token = getToken()

    const [isAdmin, setIsAdmin] = useState(false);


    	useEffect(() => {
            if (token) {
                let data = {
                    token: getToken()
                }
                    
                axios.post(`http://localhost:8080/api/v1/admin/check`, data)
                .then(res => {
                    setIsAdmin(res.data.status)
                })
                .catch(function (error) {
                    let myError = "";
                    if (error.response) {
                        // The request was made and the server responded with a status code
                        // that falls out of the range of 2xx
                        console.log(error.response.data);
                        myError = error.response.data.message
                    } else {
                        // Something happened in setting up the request that triggered an Error
                        console.log('Error', error.message);
                    }
                });
            }
        }, [token])

    const notAuthorizedItems = [
        {
            label: 'Home',
            icon: 'pi pi-fw pi-home',
            url: "/home",
        },
        {
            label: 'About',
            icon: 'pi pi-fw pi-info-circle',
            url: "/about",
        },
        {
            label: 'Users',
            icon: 'pi pi-fw pi-user',
            items: [
                {
                    label: 'Register',
                    icon: 'pi pi-fw pi-user-plus',
                    url: "/register",
                }
            ]
        },
    ];

    const items = [
        {
            label: 'Main Page',
            icon: 'pi pi-fw pi-table',
            url: "/",
        },
        {
            label: 'Home',
            icon: 'pi pi-fw pi-home',
            url: "/home",
        },
        {
            label: 'About',
            icon: 'pi pi-fw pi-info-circle',
            url: "/about",
        },
        {
            label: 'Users',
            icon: 'pi pi-fw pi-user',
            items: [
                {
                    label: 'Logout',
                    icon: 'pi pi-fw pi-sign-out',
                    command: () => logout(navigate),
                },
                {
                    label: 'Request admin priviliges',
                    icon: 'pi pi-fw pi-user-plus',
                    url: "/request",
                },
            ]
        },
    ];

    const adminItems = [
        {
            label: 'Main Page',
            icon: 'pi pi-fw pi-table',
            url: "/",
        },
        {
            label: 'Home',
            icon: 'pi pi-fw pi-home',
            url: "/home",
        },
        {
            label: 'About',
            icon: 'pi pi-fw pi-info-circle',
            url: "/about",
        },
        {
            label: 'Users',
            icon: 'pi pi-fw pi-user',
            items: [
                {
                    label: 'Logout',
                    icon: 'pi pi-fw pi-sign-out',
                    command: () => logout(navigate),
                },
                {
                    label: 'Approve user\'s admin priviliges',
                    icon: 'pi pi-fw pi-user-plus',
                    url: "/approve",
                },
            ]
        },
    ];

    if (!token) {
        return (
            <div className="card w-full">
                <Menubar model={notAuthorizedItems} start={start} />
            </div>
        )        
    }

    if (isAdmin) {
        return (
            <div className="card w-full">
                <Menubar model={adminItems} start={start} />
            </div>
        )
    }
    return (
        <div className="card w-full">
            <Menubar model={items} start={start} />
        </div>
    )   
}

NavigationBar.propTypes = {
    getToken: PropTypes.func.isRequired
};
