$(function() {
    console.log('BOOK LIST');
    var param = window.location.search.substring(1);
    console.log('param: ' + param);
    var book = {
        id: ""
    };
    $.ajax({
        url:"/book/list",
        type:"POST",
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(book),
        dataType:"json",
        success: function(result) {
            console.log(result);
            console.log(JSON.stringify(result));
            result.data.forEach(function(item, i) {
                var tr = '<tr>';
                tr += '<td hidden>' + item.id + '</td>';
                tr += '<td>' + (i+1) + '</td>';
                tr += '<td>' + item.name + '</td>';
                tr += '</tr';
                $('#bookListTable').append(tr);
            });
        }
    });
});