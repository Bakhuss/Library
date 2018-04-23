$(function () {
    $("#edit").click(function () {
            console.log('EDIT BOOK');
            var id = document
                    .getElementsByClassName('selected')[0]
                    .getElementsByTagName('td')[0].innerHTML;

            console.log('bookId: ' + id);
            document.location.href = "../../html/book/book.html?id=" + id;
    });
});