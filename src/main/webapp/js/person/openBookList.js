$(function() {
    $("#writtenBooks").click(function() {
        console.log('BOOK LIST');
        var personId = document.getElementById('personId').value;
        var title = 'Written Books';
        console.log(personId);
        console.log(location.search);
        var url = "../../html/book/bookList.html"
        + "?personId=" + personId + "&b";
        console.log(url);
        document.location.href = url;
    });
});