$(function() {
    console.log('PERSON LIST');
    var param = window.location.search.substring(1).split('&');
    for (var i=0; i<param.length; i++) {
        console.log(param[i]);
    }
    var map = new Map;
    for (var i=0; i< param.length; i++) {
        var k = param[i].split('=')[0];
        var v = param[i].split('=')[1];
        map.set(k, v);
    }
    if(map.has('w')) {
        console.log('map.w: ' + map.get('w'));
        var newUrl = "/book/" + map.get('bookId') + "?w";
        $.ajax({
            url:newUrl,
            type:"GET",
            dataType:"json",
            success: function(result) {
                console.log(result);
                console.log(JSON.stringify(result));
                if(result.error != null) {
                    alert(result.error);
                    return;
                }
//                document.insertBefore('<h1>Book</h1>');
                document.getElementsByTagName('h1')[0]
                        .firstChild.nodeValue = 'Book: ' + result.data.name + '. Writers.';
                $('#personListTable thead tr th:last').remove();
                $('#personListTable thead tr th:last').remove();
                console.log('writers: ' + result.data.writers);


//                result.data.forEach(function(item, i) {
//                    var tr = '<tr>';
//                    tr += '<td hidden>' + item.id + '</td>';
//                    tr += '<td>' + (i+1) + '</td>';
//                    tr += '<td>' + item.surname + '</td>';
//                    tr += '<td>' + item.firstName + '</td>';
//                    tr += '<td>' + item.secondName + '</td>';
//                    tr += '<td>' + item.birthday + '</td>';
//                    tr += '</tr';
//                    $('#personListTable').append(tr);
//                });
            }
        })
    }
    else {
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
    }
});

var response = {
    data:""
}