import { useState, useEffect, useRef } from "react";
import { useNavigate } from 'react-router'
import axios from "axios";

import { Button } from 'primereact/button';
import { Messages } from 'primereact/messages';
import { TabMenu } from 'primereact/tabmenu';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { InputText } from 'primereact/inputtext';
import { Dropdown } from 'primereact/dropdown';


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


    function performCoordinatesOptions(coords)  {
        return coords.map(coord => {
            return {
                label: `id: ${coord.id}, X: ${coord.x}, Y: ${coord.y}`, // Constructing the label
                value: `${coord.id}` // Constructing the value
            };
        });
    }
    
    function performLocationOptions(locations)  {
        return locations.map(loc => {
            return {
                label: `id: ${loc.id}, X: ${loc.x}, Y: ${loc.y}, Z: ${loc.z}`, // Constructing the label
                value: `${loc.id}` // Constructing the value
            };
        });
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
                msgs.current.show([
                    { sticky: false, life: 2000, severity: 'success', summary: 'Success', detail: res.data.message, closable: false },
                ])
                deleteRow(id)
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

    const handleDeleteClickCoordinates = async (id) => {
        let data = {
            id: id,
            token: getToken()
        }
        msgs.current.clear();
        axios.post(`http://localhost:8080/api/v1/coordinates/delete`, data)
            .then(res => {
                console.log(res)
                msgs.current.show([
                    { sticky: false, life: 2000, severity: 'success', summary: 'Success', detail: res.data.message, closable: false },
                ])
                deleteRowCoordinates(id)
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

    const handleDeleteClickLocation = async (id) => {
        let data = {
            id: id,
            token: getToken()
        }
        msgs.current.clear();
        axios.post(`http://localhost:8080/api/v1/location/delete`, data)
            .then(res => {
                console.log(res)
                msgs.current.show([
                    { sticky: false, life: 2000, severity: 'success', summary: 'Success', detail: res.data.message, closable: false },
                ])
                deleteRowLocation(id)
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

    const onRowEditComplete = (e) => {
        let _results = [...results];
        let { newData, index } = e;

        _results[index] = newData;

        const personData = {
            id: newData.id,
            name: newData.name,
            coordinates_id: newData.coordinates_id,
            eye_color: newData.eyeColor,
            hair_color: newData.hairColor,
            location_id: newData.location_id,
            height: newData.height,
            nationality: newData.nationality,
            token: getToken()
        };

        msgs.current.clear();
        axios.post(`http://localhost:8080/api/v1/person/edit`, personData)
            .then(res => {
                console.log(res.data);
                msgs.current.show([
                    { sticky: false, life: 2000, severity: 'success', summary: 'Success', detail: 'Successfully edited', closable: false },
                ])
            })
            .catch(function (error) {
                let myError = error.code;
                if (error.response) {
                    myError += " " + error.response.code;
                } else {
                    myError = "An error during request setting up has happened"
                }
                msgs.current.show([
                    { severity: 'error', life: 5000, summary: 'Error', detail: myError, sticky: false, closable: false }
                ]);
            });
        setResults(_results);
    };

    const textEditor = (options) => {
        return <InputText type="text" value={options.value} onChange={(e) => options.editorCallback(e.target.value)} />;
    };

    const numEditor = (options) => {
        return <InputText type="number" value={options.value} onChange={(e) => options.editorCallback(e.target.value)} />
    };

    const colorOptions = [
        { label: 'RED', value: 'RED' },
        { label: 'GREEN', value: 'GREEN' },
        { label: 'BROWN', value: 'BROWN' }
    ]

    const countryOptions = [
        {label: "RUSSIA", value: "RUSSIA"},
        {label: "VATICAN", value: "VATICAN"},
        {label: "SOUTH_KOREA", value: "SOUTH_KOREA"},
        {label: "JAPAN", value: "JAPAN"}
    ]

    const coordinatesEditor = (options) => {
        return (
            <Dropdown
                value={String(options.value)}
                options={performCoordinatesOptions(resultsCoordinates)}
                onChange={(e) => options.editorCallback(e.value)}
                placeholder="Select Coordinates"
            />
        );
    };

    const locationEditor = (options) => {
        return (
            <Dropdown
                value={String(options.value)}
                options={performLocationOptions(resultsLocation)}
                onChange={(e) => options.editorCallback(e.value)}
                placeholder="Select Location"
            />
        );
    };

    const nationalityEditor = (options) => {
        return (
            <Dropdown 
                options={countryOptions} 
                value={options.value}
                onChange={(e) => options.editorCallback(e.value)}
                placeholder="Select Nationality" 
            />
        )
    }
    const colorEditor = (options) => {
        return (
            <Dropdown 
                options={colorOptions} 
                value={options.value}
                onChange={(e) => options.editorCallback(e.value)}
                placeholder="Select Color"
            />
        )
    }

    const onRowEditCompleteCoordinates = (e) => {
        let _results = [...resultsCoordinates];
        let { newData, index } = e;

        _results[index] = newData;

        const coordinatesData = {
            id: newData.id,
            x: newData.x,
            y: newData.y,
            token: getToken()
        };

        msgs.current.clear();
        axios.post(`http://localhost:8080/api/v1/coordinates/edit`, coordinatesData)
            .then(res => {
                console.log(res.data);
                msgs.current.show([
                    { sticky: false, life: 2000, severity: 'success', summary: 'Success', detail: 'Successfully edited', closable: false },
                ])
            })
            .catch(function (error) {
                let myError = error.code;
                if (error.response) {
                    myError += " " + error.response.code;
                } else {
                    myError = "An error during request setting up has happened"
                }
                msgs.current.show([
                    { severity: 'error', life: 5000, summary: 'Error', detail: myError, sticky: false, closable: false }
                ]);
            });
        setResultsCoordinates(_results);
    };

    const onRowEditCompleteLocation = (e) => {
        let _results = [...resultsLocation];
        let { newData, index } = e;

        _results[index] = newData;

        const locationData = {
            id: newData.id,
            x: newData.x,
            y: newData.y,
            z: newData.z,
            token: getToken()
        };

        msgs.current.clear();
        axios.post(`http://localhost:8080/api/v1/location/edit`, locationData)
            .then(res => {
                console.log(res.data);
                msgs.current.show([
                    { sticky: false, life: 2000, severity: 'success', summary: 'Success', detail: 'Successfully edited', closable: false },
                ])
            })
            .catch(function (error) {
                let myError = error.code;
                if (error.response) {
                    myError += " " + error.response.code;
                } else {
                    myError = "An error during request setting up has happened"
                }
                msgs.current.show([
                    { severity: 'error', life: 5000, summary: 'Error', detail: myError, sticky: false, closable: false }
                ]);
            });
        setResultsLocation(_results);
    };

    const deleteRow = (id) => {
        setResults((prevResults) => prevResults.filter((result) => result.id !== id));
    };

    const deleteRowCoordinates = (id) => {
        setResultsCoordinates((prevResults) => prevResults.filter((result) => result.id !== id));
    };

    const deleteRowLocation = (id) => {
        setResultsLocation((prevResults) => prevResults.filter((result) => result.id !== id));
    };

    const deleteTemplate = (rowData) => {
        return (
            <Button icon="pi pi-trash" className="p-button-danger" onClick={() => handleDeleteClick(rowData.id)} />
        );
    };

    const deleteTemplateCoordinates = (rowData) => {
        return (
            <Button icon="pi pi-trash" className="p-button-danger" onClick={() => handleDeleteClickCoordinates(rowData.id)} />
        );
    };

    const deleteTemplateLocation = (rowData) => {
        return (
            <Button icon="pi pi-trash" className="p-button-danger" onClick={() => handleDeleteClickLocation(rowData.id)} />
        );
    }; 

    const allowEdit = (rowData) => {
        return rowData.name !== 'Blue Band';
    };

    return (
        <>
            <div className="card flex flex-column justify-content-center align-items-center">
                <Messages ref={msgs} />
            </div>
            <div className="card" style={{ width: '80%', borderCollapse: 'collapse', margin: "auto auto", backgroudColor: "#343e4d"}}>
                <TabMenu 
                    model={tableItems}
                    activeIndex={tableVal}
                    onTabChange={handleTabChange} />
                { tableVal === 0 &&
                    (<DataTable value={results} removableSort sortMode="multiple" editMode="row" dataKey="id" onRowEditComplete={onRowEditComplete} showGridlines paginator rows={5} rowsPerPageOptions={[5, 10, 25, 50]} header="Person List">
                        <Column field="id" header="ID" sortable />
                        <Column editor={(options) => textEditor(options)} field="name" header="Name" sortable />
                        <Column editor={(options) => coordinatesEditor(options)} field="coordinates_id" header="Coordinates ID" sortable />
                        <Column editor={(options) => colorEditor(options)} field="eyeColor" header="Eye Color" sortable />
                        <Column editor={(options) => colorEditor(options)} field="hairColor" header="Hair Color" sortable />
                        <Column editor={(options) => locationEditor(options)} field="location_id" header="Location ID" sortable />
                        <Column editor={(options) => numEditor(options)} field="height" header="Height (cm)" sortable />
                        <Column editor={(options) => nationalityEditor(options)} field="nationality" header="Nationality" sortable />
                        <Column rowEditor={allowEdit} header="Edit"/>
                        <Column body={deleteTemplate} header="Delete" />
                    </DataTable>)
                }
                { tableVal === 1 &&
                    (<DataTable value={resultsCoordinates} removableSort sortMode="multiple" editMode="row" dataKey="id" onRowEditComplete={onRowEditCompleteCoordinates} showGridlines paginator rows={5} rowsPerPageOptions={[5, 10, 25, 50]} header="Coordinates List">
                        <Column field="id" header="ID" sortable />
                        <Column editor={(options) => numEditor(options)} field="x" header="X" sortable />
                        <Column editor={(options) => numEditor(options)} field="y" header="Y" sortable />
                        <Column rowEditor={allowEdit} header="Edit" />
                        <Column body={deleteTemplateCoordinates} header="Delete" />
                    </DataTable>)
                }
                { tableVal === 2 &&
                    (<DataTable value={resultsLocation} removableSort sortMode="multiple" editMode="row" dataKey="id" onRowEditComplete={onRowEditCompleteLocation} showGridlines paginator rows={5} rowsPerPageOptions={[5, 10, 25, 50]} header="Location List">
                        <Column field="id" header="ID" sortable/>
                        <Column editor={(options) => numEditor(options)} field="x" header="X" sortable/>
                        <Column editor={(options) => numEditor(options)} field="y" header="Y" sortable/>
                        <Column editor={(options) => numEditor(options)} field="z" header="Z" sortable/>
                        <Column rowEditor={allowEdit} header="Edit" />
                        <Column body={deleteTemplateLocation} header="Delete" />
                    </DataTable>)
                }
            </div>
        </>
    );
}