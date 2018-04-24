$(function() {
    console.log('BOOK LIST');
    var param = window.location.search.substring(1);
    console.log('param: ' + param);

    var book = {
        startPage: $("#startPage").val(),
        fetchSize: $("#fetchSize").val(),
        orderSort: $("#orderSort").val()
    };
    console.log(JSON.stringify(book));
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

//    $.ajax({
//        url:"/book/count",
//        type:"GET",
//        dataType:"json",
//        success: function(result) {
//            console.log(JSON.stringify(result));
//            document.getElementById('count').value = result.data.count;
////            var fetchSize = document.getElementById('fetchSize').value;
////            var count = document.getElementById('count').value;
////            console.log(count + ' ' + fetchSize);
//        }
//    });

});