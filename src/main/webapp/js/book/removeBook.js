$(function () {
    $("#remove").click(function () {
            console.log('REMOVE BOOK');
            var book = {
                id: document
                    .getElementsByClassName('selected')[0]
                    .getElementsByTagName('td')[0].innerHTML
            };
            console.log('bookId: ' + book);

            $.ajax({
                url:"/book/delete",
                type:"POST",
                data: JSON.stringify(book),
                contentType:"application/json; charset=utf-8",
                dataType:"json",
                success: function(result){
                    console.log(JSON.stringify(result));
                    location.reload();
                }
            });
    });
});