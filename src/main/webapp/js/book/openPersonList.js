$(function() {
    $("#writers").click(function() {
        console.log('PERSON LIST');
        var bookId = document.getElementById('id').value;
        var title = 'Writers';
        console.log(id);
        console.log(location.search);
        var url = "../../html/person/personList.html"
        + "?bookId=" + bookId + "&w";
        console.log(url);
        document.location.href = url;
    });
});