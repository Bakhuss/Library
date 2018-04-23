$(document).ready(function () {
    $("#addBook").click(function () {
            console.log('BOOK');
            var book = {
                id: $("#id").val(),
                name: $("#name").val()
            };
            console.log('Book', book);

            var res = "";

            $.ajax({
                url:"/book/save",
                type:"POST",
                data: JSON.stringify(book),
                contentType:"application/json; charset=utf-8",
                dataType:"json",
                success: function(result){
                    console.log(JSON.stringify(result));
                    document.location.href = "../../html/book/bookList.html";
                }
//                async: false
            });
//            location.href = "../../html/book/bookList.html";
    });
});

var clearFields = function () {
    $("#id").val('');
};

var response = {
    result:"",
    message:""
}