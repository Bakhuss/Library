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
                var persData = result.data;
                // alert(JSON.stringify(persData));
                var titleName = ':';
                if (persData.firstName) {
                    titleName += ' ' + persData.firstName;
                    $('#firstName').val(persData.firstName);
                }
                if (persData.secondName) {
                    titleName += ' ' + persData.secondName;
                    $('#secondName').val(persData.secondName);
                }
                if (persData.surname) {
                    titleName += ' ' + persData.surname;
                    $('#surname').val(persData.surname);
                }
                $('#bodyTitle').append(titleName);

                var headTitle = $('head title').text().concat(titleName);
                $('head title').text(headTitle);
                $('#personId').val(persData.id);

                if (persData.birthday)
                    $('#birthday').val(persData.birthday);
                if (persData.phone)
                    $('#phone').val(persData.phone);
                if (persData.email)
                    $('#email').val(persData.email);
                if (persData.image)
                    document.getElementById('image').src =
                        "data:image/jpg;base64," + persData.image.img;
                if (persData.description)
                    $('#description').text(persData.description);

                console.log($('#personId').val());
                console.log($('#surname').val());
            }
        });
    }
});