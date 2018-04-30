$(function() {
    console.log('BOOK LIST');
    var param = window.location.search.substring(1);
    console.log('param: ' + param);

    var filter = {
        page: $("#page").val(),
        fetchSize: $("#fetchSize").val(),
        orderSort: $("#orderSort").val()
    };
    console.log(JSON.stringify(filter));

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
            console.log(count + ' ' + fetchSize + ' ' + pageCount);

            if(count <= fetchSize) {
                $('#navigation').addClass('disabled');
            } else {
                $('#navigation').removeClass('disabled');
                $.getScript('../../js/book/test.js');
                buildNavPanel(filter);
                $('li:not(#prevBlock, #prevPage, #nextPage, #nextBlock)').remove();
                for(var i=1; i<=10; i++) {
                    $('#nextPage').before('<li id="'+ i +'"><a href="#">' + i + '</a></li>');
                }
                $('li.active').removeClass('active');
                var id = parseInt(filter.page) + 1;
                $('#' + id).addClass('active');
            }
        }
    });

    $.ajax({
        url:"/book/list",
        type:"POST",
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(filter),
        dataType:"json",
        success: function(result) {
//            console.log(result);
//            console.log(JSON.stringify(result));
            $('#bookListTable tr').remove();
            var offset = filter.page*filter.fetchSize;
            result.data.forEach(function(item, i) {
                var tr = '<tr>';
                tr += '<td hidden>' + item.id + '</td>';
                tr += '<td>' + (i+1+offset) + '</td>';
                tr += '<td>' + item.name + '</td>';
                tr += '</tr';
                $('#bookListTable').append(tr);
            });
        }
    });
});