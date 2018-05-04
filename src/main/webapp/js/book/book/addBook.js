$(function () {
    $("#addBook").click(function () {
            console.log('BOOK');
            var book = {
                id: $("#id").val(),
                name: $("#name").val()
            };

            var newUrl = "";
            if(book.id === "") newUrl = "/book/save";
            else newUrl = "/book/update";
            console.log('Book ' + book.id + "; url: " + newUrl);
            $.ajax({
                url:newUrl,
                type:"POST",
                data: JSON.stringify(book),
                contentType:"application/json; charset=utf-8",
                dataType:"json",
                success: function(result){
                    console.log(JSON.stringify(result));
                    document.location.href = "../../html/book/bookList.html";
                }
            });
    });
});

var clearFields = function () {
    $("#id").val('');
};

var response = {
    result:"",
    message:""
}