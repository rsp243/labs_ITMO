import { useState, useEffect, useRef } from "react";
import { useNavigate } from 'react-router'
import axios from "axios";

import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { Messages } from 'primereact/messages';

export default function ResultTable({getToken}) {
    const msgs = useRef(null);
    const [results, setResults] = useState([]);
    const [reload, setReload] = useState(true);

    const [resultsCoordinates, setResultsCoordinates] = useState([]);
    const [reloadCoordinates, setReloadCoordinates] = useState(true);

    const [resultsLocation, setResultsLocation] = useState([]);
    const [reloadLocation, setReloadLocation] = useState(true);

    const handleDeleteClick = async (id) => {
        let data = {
            id: id,
            token: getToken()
        }
        msgs.current.clear();
        axios.post(`http://localhost:8080/api/v1/person/delete`, data)
            .then(res => {
                console.log(res)
                setReload(!reload)
                msgs.current.show([
                    { sticky: false, life: 2000, severity: 'success', summary: 'Success', detail: res.data.message, closable: false },
                ])
            })
            .catch(function (error) {
                let myError = "";
                if (error.response) {
                    // The request was made and the server responded with a status code
                    // that falls out of the range of 2xx
                    
                    myError = error.response.data.message
                } else {
                    // Something happened in setting up the request that triggered an Error
                    console.log('Error', error.message);
                    myError = "An error during request setting up has happened"
                }
                msgs.current.replace([
                    { severity: 'error', life: 5000, summary: 'Error', detail: myError, sticky: false, closable: false }
                ]);
            });
    }


	useEffect(() => {
        msgs.current.clear();
		axios.post(`http://localhost:8080/api/v1/person/all`, { token: getToken() })
			.then(res => {
				console.log(res.status);
				console.log(res.data);
				setResults(res.data);
			})
			.catch(function (error) {
				let myError = "";
				if (error.response) {
					// The request was made and the server responded with a status code
					// that falls out of the range of 2xx
					
					myError = error.response.data.message
				} else {
					// Something happened in setting up the request that triggered an Error

					myError = "An error during request setting up has happened"
				}
                msgs.current.replace([
                    { severity: 'error', life: 5000, summary: 'Error', detail: myError, sticky: false, closable: false }
                ]);
			})
	}, [reload])

    useEffect(() => {
        msgs.current.clear();
		axios.post(`http://localhost:8080/api/v1/coordinates/all`, { token: getToken() })
			.then(res => {
				console.log(res.status);
				console.log(res.data);
				setResultsCoordinates(res.data);
			})
			.catch(function (error) {
				let myError = "";
				if (error.response) {
					// The request was made and the server responded with a status code
					// that falls out of the range of 2xx
					
					myError = error.response.data.message
				} else {
					// Something happened in setting up the request that triggered an Error

					myError = "An error during request setting up has happened"
				}
                msgs.current.replace([
                    { severity: 'error', life: 5000, summary: 'Error', detail: myError, sticky: false, closable: false }
                ]);
			})
	}, [reloadCoordinates])

    useEffect(() => {
        msgs.current.clear();
		axios.post(`http://localhost:8080/api/v1/location/all`, { token: getToken() })
			.then(res => {
				console.log(res.status);
				console.log(res.data);
				setResultsLocation(res.data);
			})
			.catch(function (error) {
				let myError = "";
				if (error.response) {
					// The request was made and the server responded with a status code
					// that falls out of the range of 2xx
					
					myError = error.response.data.message
				} else {
					// Something happened in setting up the request that triggered an Error

					myError = "An error during request setting up has happened"
				}
                msgs.current.replace([
                    { severity: 'error', life: 5000, summary: 'Error', detail: myError, sticky: false, closable: false }
                ]);
			})
	}, [reloadCoordinates])
    
    return (
        <>
            <div className="card flex flex-column justify-content-center align-items-center">
                <Messages ref={msgs} />
            </div>
            <table style={{ width: '80%', borderCollapse: 'collapse', margin: "auto auto", backgroundColor: "rgba(52,62,77,0.9)" }}>
                <thead style={{ }}>
                    <tr>
                        <th style={{ border: '1px solid blue', padding: '8px' }}>Id</th>
                        <th style={{ border: '1px solid blue', padding: '8px' }}>Name</th>
                        <th style={{ border: '1px solid blue', padding: '8px' }}>Coordinates</th>
                        <th style={{ border: '1px solid blue', padding: '8px' }}>Creation date</th>
                        <th style={{ border: '1px solid blue', padding: '8px' }}>Eye</th>
                        <th style={{ border: '1px solid blue', padding: '8px' }}>Hair</th>
                        <th style={{ border: '1px solid blue', padding: '8px' }}>Location</th>
                        <th style={{ border: '1px solid blue', padding: '8px' }}>Height</th>
                        <th style={{ border: '1px solid blue', padding: '8px' }}>Nationality</th>
                        <th style={{ border: '1px solid blue', padding: '8px' }}>Actions</th>
                        </tr>
                </thead>
                <tbody>
                    {results.map((row) => (
                        <tr key={row.id}>
                            <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.id || "Not specified"}</td>
                            <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.name || "Not specified"}</td>
                            <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.coordinates_id || "Not specified"}</td>
                            <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.creationDate.join(".") || "Not specified"}</td>
                            <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.eyeColor || "Not specified"}</td>
                            <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.hairColor || "Not specified"}</td>
                            <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.location_id || "Not specified"}</td>
                            <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.height || "Not specified"}</td>
                            <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.nationality || "Not specified"}</td>
                            <td  className='tableButtons' style={{ border: '1px solid blue', padding: '8px'}}>
                                <Button label="Edit" icon="pi pi-pencil"></Button>
                                <Button label="Delete" icon="pi pi-times" onClick={() => handleDeleteClick(row.id)}></Button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </>

        // <DataTable scrollable scrollHeight="400px" value={results} tableStyle={{ maxWidth: '70rem', margin: "auto auto" }}>
        //     <Column field="name" header="Name" sortable></Column>
        //     <Column field="coordinates_id" header="Coordinates" sortable></Column>
        //     <Column field="creation_date" header="Creation date" sortable></Column>
        //     <Column field="eye_color" header="Eye color" sortable></Column>
        //     <Column field="hair_color" header="Hair color" sortable></Column>
        //     <Column field="location_id" header="Location" sortable></Column>
        //     <Column field="height" header="Height" sortable></Column>
        //     <Column field="nationality" header="Nationality" sortable></Column>
        //     <Column field="actions" header="Actions"></Column>
        // </DataTable>
    );
}