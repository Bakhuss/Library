$(function () {
    $("#remove").click(function () {
            console.log('REMOVE PERSON');
            var elem = document.getElementsByClassName('selected');
            if(elem.length === 0) return false;
            var person = {
                id: elem[0]
                    .getElementsByTagName('td')[0]
                    .innerHTML
            };
            console.log('person: ' + person);

            $.ajax({
                url:"/person/delete",
                type:"POST",
                data: JSON.stringify(person),
                contentType:"application/json; charset=utf-8",
                dataType:"json",
                success: function(result){
                    console.log(JSON.stringify(result));
                    location.reload();
                }
            });
    });
});