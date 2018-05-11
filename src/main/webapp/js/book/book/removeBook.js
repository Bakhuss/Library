$(function () {
    $("#remove").click(function () {
            console.log('REMOVE BOOK');
            var elem = document.getElementsByClassName('selected');
            if(elem.length === 0) return false;
            var book = {
                id: elem[0]
                    .getElementsByTagName('td')[0]
                    .innerHTML
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
                    var param = window.location.search.substring(1);
                    console.log(param);
                    console.log($('#page').val());
//                    alert();
                    location.reload();
                }
            });
    });
});