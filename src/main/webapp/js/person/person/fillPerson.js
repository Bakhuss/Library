$(function() {
    var id = window.location.search.substring(1).split('=')[1];
    console.log(id);
    var newUrl = "/person/" + id;
    if(id != null) {
        $.ajax({
            url: newUrl,
            type:"GET",
            dataType:"json",
            success: function(result) {
                if(result.error != null) {
                    alert(result.error);
                    location.href = "../../html/person/personList.html";
                    return;
                }
                console.log(JSON.stringify(result));
                $('#bodyTitle').append(': ' + result.data.surname);
                $('#bodyTitle').append(' ' + result.data.firstName);
                $('#bodyTitle').append(' ' + result.data.secondName);
                document.getElementById('personId').value = result.data.id;
                document.getElementById('surname').value = result.data.surname;
                document.getElementById('firstName').value = result.data.firstName;
                document.getElementById('secondName').value = result.data.secondName;
                document.getElementById('birthday').value = result.data.birthday;
                document.getElementById('phone').value = result.data.phone;
                document.getElementById('email').value = result.data.email;

                console.log(document.getElementById('personId').value);
                console.log(document.getElementById('surname').value);
            }
        });
    }
});