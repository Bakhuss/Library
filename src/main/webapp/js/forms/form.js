$(document).ready(function () {
console.log('getBooks')
    $("#getBooks").click(function () {
        console.log('BOOK');
        var book = {
            id: $("#id").val(),
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
});

var clearFields = function () {
    $("#id").val('');
};