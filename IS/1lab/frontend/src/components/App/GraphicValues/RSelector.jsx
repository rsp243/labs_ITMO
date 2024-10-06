import { useState } from 'react';
import PropTypes from 'prop-types';

import { Dropdown } from 'primereact/dropdown';
import { Message } from 'primereact/message';
import { drawBeginnigGraph } from "../../../Pages/src/js/canvas"

export default function RSelector({ selectedR, setSelectedR, isCorrectR, setIsCorrectR }) {
	const values = [
		{ name: '1.0' },
		{ name: '1.5' },
		{ name: '2.0' },
		{ name: '2.5' },
	];

	function handleChange(e) {
		if (!values.includes(e)) {
			setIsCorrectR(false)
		} else {
			drawBeginnigGraph("canvas")
			setIsCorrectR(true)
		}
	}

	return (
		<>
			<div className="card mt-2 mb-2">
				<Dropdown value={selectedR} 
					onChange={(e) => {
						setSelectedR(e.value)
						handleChange(e.value)
					}}
					options={values} optionLabel="name"
					placeholder="Select R value"
					className="w-full md:w-14rem" />
			</div>
			{
				!isCorrectR &&
				<Message className='mb-2' severity="error" text="Passed R don't belong availible values" />
			}
		</>
	);
}

RSelector.propTypes = {
	setSelectedR: PropTypes.func.isRequired,
	setIsCorrectR: PropTypes.func.isRequired,
};
