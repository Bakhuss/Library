$(function () {
    $("#edit").click(function () {
            console.log('EDIT PERSON');
            var id = document
                    .getElementsByClassName('selected')[0]
                    .getElementsByTagName('td')[0].innerHTML;

            console.log('personId: ' + id);
            document.location.href = "../../html/person/person.html?id=" + id;
    });
});