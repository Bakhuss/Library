$(function() {
    $("#writers").click(function() {
        console.log('PERSON LIST');
        var bookId = document.getElementById('bookId').value;
        var title = 'Writers';
        console.log(bookId);
        console.log(location.search);
        var url = "../../html/person/personList.html"
        + "?bookId=" + bookId + "&w";
        console.log(url);
        document.location.href = url;
    });
});