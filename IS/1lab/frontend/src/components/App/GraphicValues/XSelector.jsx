import { useState } from 'react';
import PropTypes from 'prop-types';

import { Dropdown } from 'primereact/dropdown';
import { Message } from 'primereact/message';

export default function XSelector({ selectedX, setSelectedX, isCorrectX, setIsCorrectX }) {
	const values = [
		{ name: '-2.0' },
		{ name: '-1.5' },
		{ name: '-1.0' },
		{ name: '-0.5' },
		{ name: '0.0' },
		{ name: '0.5' },
		{ name: '1.0' },
		{ name: '1.5' },
		{ name: '2.0' },
	];
	
	function handleChange(e) {
		if (!values.includes(e)) {
			setIsCorrectX(false)
		} else {
			setIsCorrectX(true)
		}
	}

	return (
		<>
			<div className="card my-2">
				<Dropdown value={selectedX}
					onChange={(e) => {
						setSelectedX(e.value)
						handleChange(e.value)}}
					options={values} optionLabel="name"
					placeholder="Select X value" className="w-full md:w-14rem" />
			</div>
			{	
				!isCorrectX &&
				<Message className='Xerror' severity="error" text="Passed X don't belong availible values" />
			}
		</>
	);
}

XSelector.propTypes = {
	setSelectedX: PropTypes.func.isRequired,
	setIsCorrectX: PropTypes.func.isRequired,
};
