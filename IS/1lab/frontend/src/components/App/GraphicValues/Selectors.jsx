import PropTypes from 'prop-types';

import RSelector from "./RSelector";
import XSelector from "./XSelector";
import YSelector from "./YSelector";

export default function Selectors(
    {   xValue, setXValue, isCorrectX, setIsCorrectX,
        yValue, setYValue, isCorrectY, setIsCorrectY,
        rValue, setRValue, isCorrectR, setIsCorrectR}) {
    return (
        <>
            <XSelector 
                selectedX={xValue}
                setSelectedX={setXValue}
                isCorrectX={isCorrectX}
                setIsCorrectX={setIsCorrectX}/>
            <YSelector 
                selectedY={yValue}
                setSelectedY={setYValue}
                isCorrectY={isCorrectY}
                setIsCorrectY={setIsCorrectY}/>
            <RSelector 
                selectedR={rValue}
                setSelectedR={setRValue}
                isCorrectR={isCorrectR}
                setIsCorrectR={setIsCorrectR}/>
        </>
    );
}

Selectors.propTypes = {
    setXValue: PropTypes.func.isRequired,
    setYValue: PropTypes.func.isRequired,
    setRValue: PropTypes.func.isRequired,

    setIsCorrectX: PropTypes.func.isRequired,
    setIsCorrectY: PropTypes.func.isRequired,
    setIsCorrectR: PropTypes.func.isRequired
};
