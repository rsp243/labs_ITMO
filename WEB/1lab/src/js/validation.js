function isFloat(str) {
    str = str.trim();
    if (!str) {
        return false;
    }
    str = str.replace("/,/", ".");
    let result = str.match(/^[0-9]+(?:|\.)[0-9]*$/);
    if (result !== null) {
        return result[0];
    }
    return false;
}

function validateEntryData(xNum, yFloat, rFloat) {
    let result = true;
    if (xNum == NaN || !(xNum in [-3, -2, -1, 0, 1, 2, 3, 4, 5])) {
        $(".X-error-message").css("display", "block");
        result = false;
    } else {
        $(".X-error-message").css("display", "none");
    }

    if (!(yFloat > -3 && yFloat < 5)) {
        $(".Y-error-message").css("display", "block");
        result = false;
    } else {
        $(".Y-error-message").css("display", "none");
    }

    if (rFloat == NaN || !(rFloat >= 1 && rFloat <= 3)) {
        $(".R-error-message").css("display", "block");
        result = false;
    } else {
        $(".R-error-message").css("display", "none");
    }
    return result;
}

$(".btn-process").on( "click", function() {
    let xValue = $("input[name=\'Xvalue\']:checked").val();
    let yValue = $(".Yselection-text").val();
    let rValue = $("select[name=\'Rvalue\'] option:selected").val();
    let xNum = Math.floor(xValue);
    let yFloat = parseFloat(isFloat(yValue));
    let rFloat = parseFloat(isFloat(rValue));

    if (validateEntryData(xNum, yFloat, rFloat)) {
        console.log("Values are ready to go to php server.");
    }
});


