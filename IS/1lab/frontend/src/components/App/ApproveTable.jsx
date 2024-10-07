import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';

export default function ApproveTable({results}) {
    return (
        <>
            <table style={{ width: '80%', borderCollapse: 'collapse', margin: "auto auto" }}>
                <thead>
                    <tr>
                        <th style={{ border: '1px solid blue', padding: '8px' }}>user ID</th>
                        <th style={{ border: '1px solid blue', padding: '8px' }}>Username</th>
                        <th style={{ border: '1px solid blue', padding: '8px' }}>Status</th>
                        <th style={{ border: '1px solid blue', padding: '8px' }}>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {results.map((row) => (
                        <tr key={row.id}>
                            <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }} >{row.userId}</td>
                            <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }}>{row.name}</td>
                            <td style={{ border: '1px solid blue', padding: '8px', textAlign: "center" }}>{row.status}</td>
                            <td className='tableButtons' style={{ border: '1px solid blue', padding: '8px'}}>
                                <Button label="Approve" icon="pi pi-check"></Button>
                                <Button label="Decline" icon="pi pi-times"></Button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    );
}