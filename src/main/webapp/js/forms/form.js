$(document).ready(function () {
    $("#getBooks").click(function () {
        console.log('BOOK');
        var book = {
            id: $("#id").val()
        };
        console.log('BOOK', book);

        $.ajax({
            url:"/book/list",
            type:"POST",
            data: JSON.stringify(book),
            contentType:"application/json; charset=utf-8",
            success: function(result){
                alert(JSON.stringify(result));
            }
        });

        clearFields();
    });

    $("#addPerson").click(function () {
            console.log('PERSON');
            var person = {
                id: $("#id").val(),
                surname: $("#surname").val(),
                firstName: $("#firstName").val(),
                secondName: $("#secondName").val(),
                birthday: $("#birthday").val(),
                phone: $("#phone").val(),
                email: $("#email").val()
            };
            console.log('PER', person);

            $.ajax({
                url:"/person/save",
                type:"POST",
                data: JSON.stringify(person),
                contentType:"application/json; charset=utf-8",
                success: function(result){
                    console.log(result);
                    alert('Success');
                }
            });

    //        clearFields();
        });

});

var clearFields = function () {
    $("#id").val('');
};