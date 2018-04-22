$(function() {
    console.log('PERSON LIST');
    var param = window.location.search.substring(1).split('&');
    for (var i=0; i<param.length; i++) {

    }
    console.log('param: ' + param);
    var person = {
        id: ""
    };
    $.ajax({
        url:"/person/list",
        type:"POST",
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(person),
        dataType:"json",
        success: function(result) {
            console.log(result);
            console.log(JSON.stringify(result));
//            document.getElementsByTagName('h1')[0].firstChild.nodeValue = 'Writers';
            $('#personListTable thead tr th:last').remove();
            $('#personListTable thead tr th:last').remove();
            result.data.forEach(function(item, i) {
                var tr = '<tr>';
                tr += '<td hidden>' + item.id + '</td>';
                tr += '<td>' + (i+1) + '</td>';
                tr += '<td>' + item.surname + '</td>';
                tr += '<td>' + item.firstName + '</td>';
                tr += '<td>' + item.secondName + '</td>';
                tr += '<td>' + item.birthday + '</td>';
                tr += '</tr';
                $('#personListTable').append(tr);
            });
        }
    });

});

var response = {
    data:""
}