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
        url:"/book/count",
        type:"GET",
        dataType:"json",
        success: function(result) {
            console.log(JSON.stringify(result));
            document.getElementById('count').value = result.data.count;
            var fetchSize = document.getElementById('fetchSize').value;
            var count = document.getElementById('count').value;
            var pageCount = (count/fetchSize).toFixed();
            console.log(count + ' ' + fetchSize + ' ' + pageCount);
            var elem = document.getElementById('navPrevious');
            if(count <= fetchSize) {
                document.getElementById('navigation').remove();
            } else {
                if(document.getElementById('navNext').className === '') {
                    var li = '<li id="" class=""><a href="#">...</a></li>';
                    elem.outerHTML += li;
                }
                var startPage = document.getElementById('startPage').value + 1;
                for(var i=10; i>=1; i--) {
//                    li = '<li id="' + i + '" class=""><a href="#">' + i + '</a></li>';
                    li = '<li id="' + i + '"';
                    if(i === Number.parseInt(startPage)) {
                        li += ' class="active"'
                    }
                    li += '><a href="#">' + i + '</a></li>';
                    document.getElementById('navPrevious').outerHTML += li;
                }
                if(startPage > 10) {
                    li = '<li id="" class=""><a href="#">...</a></li>';
                    elem.outerHTML += li;
                }
            }
        }
    });

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