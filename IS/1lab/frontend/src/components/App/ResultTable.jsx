import { useState, useEffect, useRef } from "react";
import { useNavigate } from 'react-router'
import axios from "axios";

import { Button } from 'primereact/button';
import { Messages } from 'primereact/messages';
import { TabMenu } from 'primereact/tabmenu';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';


export default function ResultTable({getToken}) {
    const msgs = useRef(null);
    const navigate = useNavigate();
    const [results, setResults] = useState([]);
    const [reload, setReload] = useState(true);
    const [tableVal, setTableVal] = useState(0); // Default value

    const [resultsCoordinates, setResultsCoordinates] = useState([]);
    const [reloadCoordinates, setReloadCoordinates] = useState(true);

    const [resultsLocation, setResultsLocation] = useState([]);
    const [reloadLocation, setReloadLocation] = useState(true);
    const [expandedRows, setExpandedRows] = useState({});


    function performCoordinatesOption(coord)  {
        return {
            label: `id: ${coord.id}, X: ${coord.x}, Y: ${coord.y}`, // Constructing the label
            value: `${coord.id}` // Constructing the value
        };
    }
    
    function performLocationOption(loc)  {
        return {
            label: `id: ${loc.id}, X: ${loc.x}, Y: ${loc.y}, Z: ${loc.z}`, // Constructing the label
            value: `${loc.id}` // Constructing the value
        };
    }

    const handleEditClick = (id) => {
        navigate("/edit/person/" + id)
        navigate(0)
    }

    const handleTabChange = (e) => {
        setTableVal(e.index);
    };

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

    const handleMoreClick = (id) => {
        setExpandedRows((prev) => ({
            ...prev,
            [id]: !prev[id], // Toggle the visibility of the row
        }));
    };

    const getObjectById = (array, id) => {
        return array.find(item => item.id === id);
    };

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

    const tableItems = [
        {
            label: 'Person',
            icon: 'pi pi-fw pi-user',
            tableVal: 0,
        },
        {
            label: 'Coordinates',
            icon: 'pi pi-fw pi-map',
            tableVal: 1,
        },
        {
            label: 'Location',
            icon: 'pi pi-fw pi-map-marker',
            tableVal: 2,
        },
    ]

    const users = [
        { id: 1, name: 'John Doe', email: 'john@example.com' },
        { id: 2, name: 'Jane Smith', email: 'jane@example.com' },
        { id: 3, name: 'Alice Johnson', email: 'alice@example.com' }
    ];
    return (
        <>
            <div className="card flex flex-column justify-content-center align-items-center">
                <Messages ref={msgs} />
            </div>
            <div className="" style={{ width: '80%', borderCollapse: 'collapse', margin: "auto auto" }}>
                <TabMenu 
                    model={tableItems} 
                    onTabChange={handleTabChange} />
                { tableVal === 0 &&
                    (<DataTable value={results} paginator rows={5} header="Person List">
                        <Column field="id" header="ID" />
                        <Column field="name" header="Name" />
                        <Column field="coordinates_id" header="Coordinates" />
                        <Column field="eyeColor" header="Eye Color" />
                        <Column field="hairColor" header="Hair Color" />
                        <Column field="location_id" header="Location" />
                        <Column field="height" header="Height (cm)" />
                        <Column field="nationality" header="Nationality" />
                    </DataTable>)
                }
                { tableVal === 1 &&
                    (<DataTable value={users} paginator rows={5} header="Coordinates List">
                        <Column field="id" header="ID" />
                        <Column field="name" header="Name" />
                        <Column field="email" header="Email" />
                    </DataTable>)
                }
                { tableVal === 2 &&
                    (<DataTable value={users} paginator rows={5} header="Location List">
                        <Column field="id" header="ID" />
                        <Column field="name" header="Name" />
                        <Column field="email" header="Email" />
                    </DataTable>)
                }
                {/* <table>
                    <thead style={{ }}>
                        <tr>
                            <th style={{ border: '1px solid blue', padding: '8px' }}>Id</th>
                            <th style={{ border: '1px solid blue', padding: '8px' }}>Name</th>
                            <th style={{ border: '1px solid blue', padding: '8px' }}>Coordinates</th>
                            <th style={{ border: '1px solid blue', padding: '8px' }}>Creation date</th>
                            <th style={{ border: '1px solid blue', padding: '8px' }}>Eye color</th>
                            <th style={{ border: '1px solid blue', padding: '8px' }}>Hair color</th>
                            <th style={{ border: '1px solid blue', padding: '8px' }}>Location</th>
                            <th style={{ border: '1px solid blue', padding: '8px' }}>Height (cm)</th>
                            <th style={{ border: '1px solid blue', padding: '8px' }}>Nationality</th>
                            <th style={{ border: '1px solid blue', padding: '8px' }}>Actions</th>
                            </tr>
                    </thead>
                    <tbody>
                        {results.map((row) => (
                            <tr key={row.id}>
                                <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.id || "Not specified"}</td>
                                <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.name || "Not specified"}</td>
                                <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >
                                    {expandedRows[row.id] ? performCoordinatesOption(getObjectById(resultsCoordinates, row.coordinates_id))["label"] : "id: " + row.coordinates_id || "Not specified"}
                                </td>
                                <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.creationDate.join(".") || "Not specified"}</td>
                                <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.eyeColor || "Not specified"}</td>
                                <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.hairColor || "Not specified"}</td>
                                <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >
                                    {expandedRows[row.id] ? performLocationOption(getObjectById(resultsLocation, row.location_id))["label"] : "id: " + row.location_id || "Not specified"}
                                </td>
                                <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.height || "Not specified"}</td>
                                <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.nationality || "Not specified"}</td>
                                <td  className='tableButtons' style={{ border: '1px solid blue', padding: '8px'}}>
                                    <Button label="Edit" icon="pi pi-pencil" onClick={() => handleEditClick(row.id)}></Button>
                                    <Button label="Delete" icon="pi pi-times" onClick={() => handleDeleteClick(row.id)}></Button>
                                    <Button label={expandedRows[row.id] ? 'Less info' : 'More info'} icon="pi pi-info" onClick={() => handleMoreClick(row.id)}></Button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table> */}
            </div>
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