//$(function() {
    console.log('BOOK LIST');
    var param = window.location.search.substring(1);
    console.log('param: ' + param);

    function getFilter() {
        var filter = {
            page: parseInt($("#page").val()),
            fetchSize: $("#fetchSize").val(),
            orderSort: $("#orderSort").val()
        };
        console.log('fillBookList: filter: ' + JSON.stringify(filter));
        return filter;
    }

    function getCount() {
        console.log('getCount');
        $.ajax({
            url:"/book/count",
            type:"GET",
            dataType:"json",
            success: function(result) {
                console.log(JSON.stringify(result));
                var elem = $('#navigation');
                $('#count').val(result.data.count);
                var fetchSize = $('#fetchSize').val();
                var count = $('#count').val();
                var pageCount = (count/fetchSize + 1).toFixed();
                console.log(count + ', ' + fetchSize + ', ' + pageCount);
                if(count <= fetchSize) {
                    $('#navigation').hide();
                } else {
                    $('#navigation').show();
                    checkNavPanel(getFilter());
                }
            }
        });
        return false;
    }

    function getBooks() {
        var filter = getFilter();
        var books;
        $.ajax({
            url:"/book/list",
            type:"POST",
            contentType:"application/json; charset=utf-8",
            data: JSON.stringify(filter),
            dataType:"json",
            success: function(result) {
                $('#bookListTable tbody tr').remove();
                books = result.data;
                var offset = filter.page*filter.fetchSize;
                result.data.forEach(function(item, i) {
                    var tr = '<tr>';
                    tr += '<td hidden>' + item.id + '</td>';
                    tr += '<td>' + (i+1+offset) + '</td>';
                    tr += '<td>' + item.name + '</td>';
                    tr += '</tr';
                    $('#bookListTable tbody').append(tr);
                    $('head script[src="../../js/mark.js"]').remove();
                    $('head script').after('<script src="../../js/mark.js"></script>');
                });
            }
//            return data;
        });
        return books;
    }
//});