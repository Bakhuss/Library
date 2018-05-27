$(function () {
    $("#addPerson").click(function () {
        console.log('PERSON');
        var person = {
            id: $("#personId").val(),
            image: $("#imageFile").val(),
            surname: $("#surname").val(),
            firstName: $("#firstName").val(),
            secondName: $("#secondName").val(),
            birthday: $("#birthday").val(),
            phone: $("#phone").val(),
            email: $("#email").val(),
            description: $("#description").val()
        };

        alert(JSON.stringify(person));

        var newUrl = "";
        if (person.id === "") newUrl = "/person/save";
        else newUrl = "/person/update";
        console.log('Person ' + person.id + "; url: " + newUrl);
        $.ajax({
            url: newUrl,
            type: "POST",
            data: JSON.stringify(person),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (result) {
                console.log(JSON.stringify(result));
                document.location.href = "../../html/person/personList.html";
            }
        });
    });
});