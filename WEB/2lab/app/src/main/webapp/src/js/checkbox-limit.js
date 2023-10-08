jQuery(function(){
    let max = 1;
    let checkboxes = $('input[type="checkbox"]');

    checkboxes.change(function(){
        let current = checkboxes.filter(':checked').length;
        checkboxes.filter(':not(:checked)').prop('disabled', current >= max);
    });
});
