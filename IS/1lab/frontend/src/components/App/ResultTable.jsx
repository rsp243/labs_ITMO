import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';

export default function ResultTable({results}) {
    return (
        <DataTable scrollable scrollHeight="400px" value={results} tableStyle={{ maxWidth: '70rem', margin: "auto auto" }}>
            <Column field="name" header="Name" sortable></Column>
            <Column field="coordinates_id" header="Coordinates" sortable></Column>
            <Column field="creation_date" header="Creation date" sortable></Column>
            <Column field="eye_color" header="Eye color" sortable></Column>
            <Column field="hair_color" header="Hair color" sortable></Column>
            <Column field="location_id" header="Location" sortable></Column>
            <Column field="height" header="Height" sortable></Column>
            <Column field="nationality" header="Nationality" sortable></Column>
            <Column field="actions" header="Actions"></Column>
        </DataTable>
    );
}