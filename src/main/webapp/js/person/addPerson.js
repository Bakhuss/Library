$(function () {
    $("#addPerson").click(function () {
            console.log('PERSON');
            var person = {
                id: $("#id").val(),
                surname: $("#surname").val(),
                firstName: $("#firstName").val(),
                secondName: $("#secondName").val(),
                birthday: $("#birthday").val(),
                phone: $("#phone").val(),
                email: $("#email").val()
            };
            console.log('PER', person);

            $.ajax({
                url:"/person/save",
                type:"POST",
                data: JSON.stringify(person),
                contentType:"application/json; charset=utf-8",
                dataType:"json",
                success: function(result){
                    alert(JSON.stringify(result));
                }
            });
    });
});

var clearFields = function () {
    $("#id").val('');
};

var response = {
    result:"",
    message:""
}