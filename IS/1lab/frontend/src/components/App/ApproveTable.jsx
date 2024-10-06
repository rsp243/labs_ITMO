import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';

export default function ApproveTable({results}) {
    return (
        <DataTable scrollable scrollHeight="400px" value={results} tableStyle={{ maxWidth: '70rem', margin: "auto auto" }}>
            <Column field="userId" header="user ID" sortable></Column>
            <Column field="name" header="Username" sortable></Column>
            <Column field="status" header="Status" sortable></Column>
            <Column field="actions" header="Actions"></Column>
        </DataTable>
    );
}