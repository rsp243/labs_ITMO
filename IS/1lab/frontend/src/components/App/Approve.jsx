import { useState, useRef, useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import axios from "axios";

import { Button } from 'primereact/button';
import { Messages } from 'primereact/messages';
import ApproveTable from './ApproveTable';

export default function Request({ getToken }) {
    const msgs = useRef(null);
    const navigate = useNavigate();
    const [results, setResults] = useState([]);
    
    useEffect(() => {
        let data = {
            token: getToken()
        }
        msgs.current.clear();

        axios.post(`http://localhost:8080/api/v1/admin/requests`, data)
            .then(res => {
                setResults(res.data);
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
                    myError = "An error during request setting up has happened"
                }
                msgs.current.show([
                    { severity: 'error', life: 5000, summary: 'Error', detail: myError, sticky: false, closable: false }
                ]);
            });
        }, [msgs])

    return (
        <>
            <div className="card flex flex-column justify-content-center align-items-center">
				<Messages ref={msgs} />
			</div>
            <div style={{"height": "50px"}}></div>
            <ApproveTable results={results} />
            <div style={{"height": "50px"}}></div>
        </>
    );
}