$(function () {
    var id = window.location.search.substring(1).split('=')[1];
    console.log(id);
    var newUrl = "/person/" + id;
    if (id != null) {
        $.ajax({
            url: newUrl,
            type: "GET",
            dataType: "json",
            success: function (result) {
                if (result.error != null) {
                    alert(result.error);
                    location.href = "../../html/person/personList.html";
                    return;
                }
                console.log(JSON.stringify(result));
                var titleName = ': ' + result.data.surname;
                if (result.data.firstName)
                    titleName += ' ' + result.data.firstName;
                if (result.data.secondName)
                    titleName += ' ' + result.data.secondName;
                $('#bodyTitle').append(titleName);

                var headTitle = $('head title').text().concat(titleName);
                $('head title').text(headTitle);

                var persData = result.data;
                document.getElementById('personId').value = result.data.id;
                document.getElementById('surname').value = result.data.surname;
                document.getElementById('firstName').value = result.data.firstName;
                document.getElementById('secondName').value = result.data.secondName;

                if (persData.birthday)
                    $('#birthday').val(persData.birthday);
                if (persData.phone)
                    $('#phone').val(persData.phone);
                if (persData.email)
                    $('#email').val(persData.email);
                if (persData.image.img)
                document.getElementById('image').src =
                    "data:image/jpg;base64," + result.data.image.img;

                console.log(document.getElementById('personId').value);
                console.log(document.getElementById('surname').value);
            }
        });
    }
});