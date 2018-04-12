$(document).ready (function () {

    $("#addPerson").click(function () {
        console.log('PERSON');
        var person = {
            name: $("#name").val(),
            age: $("#age").val()
        };
        console.log('PER', person);

        $.ajax({
            url:"/person",
            type:"POST",
            data: JSON.stringify(person),
            contentType:"application/json; charset=utf-8",
            success: function(){
                alert('Success');
            }
        });

        clearFields();
    });

var clearFields = function () {
    $("#name").val('');
    $("#age").val('');
};

});