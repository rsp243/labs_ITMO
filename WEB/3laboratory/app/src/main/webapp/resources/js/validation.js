export function isFloat(str) {
    str = str.trim();
    if (!str) {
        return false;
    }
    str = str.replace("/,/", ".");
    let result = str.match(/^(?:|\+|-)[0-9]+(?:|\.)[0-9]*$/);
    if (result !== null) {
        return result[0];
    }
    return false;
}

export function validateEntryData(xNum, yFloat, rFloat) {
    let result = true;
    if (xNum == NaN || !(-3 <= xNum <= 5)) {
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
