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

function getCount(urlCount) {
    console.log('getCount');
    $.ajax({
        url:urlCount,
        type:"GET",
        dataType:"json",
        success: function(result) {
            console.log(JSON.stringify(result));
            var elem = $('#navigation');
            $('#count').val(result.data.count);
            var fetchSize = parseInt($('#fetchSize').val());
            var count = parseInt($('#count').val());
            var pageCount = (count/fetchSize + 1).toFixed();
            console.log('getCount: ' + count + ', ' + fetchSize + ', ' + pageCount);
            if(count <= fetchSize) {
                $('#navigation').hide();
                console.log('getCount: navigation hide');
            } else {
                $('#navigation').show();
                console.log('getCount: navigation show');
                checkNavPanel(getFilter());
            }
        }
    });
    return false;
}

function getTable(urlList, tabId) {
    var filter = getFilter();
    var tableData;
    $.ajax({
        url:urlList,
        type:"POST",
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(filter),
        dataType:"json",
        success: function(result) {
            tableData = {
                tableId: tabId,
                offset: filter.page*filter.fetchSize,
                list: result.data
            }
            buildTable(tableData);
        }
    });
}