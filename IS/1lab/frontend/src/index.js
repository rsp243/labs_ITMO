import React from 'react';
import ReactDOM from 'react-dom/client';
import { PrimeReactProvider } from 'primereact/api';

import "primereact/resources/themes/bootstrap4-dark-blue/theme.css";
import 'primeicons/primeicons.css';
import '/node_modules/primeflex/primeflex.css';
import './index.css';

import Router from './components/Router/Router.jsx'

import reportWebVitals from './reportWebVitals.js';

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
	<React.StrictMode>
		<PrimeReactProvider>
			<Router />
		</PrimeReactProvider>
	</React.StrictMode>
);

reportWebVitals();
