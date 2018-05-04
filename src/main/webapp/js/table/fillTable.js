console.log('Fill Table');
var param = window.location.search.substring(1);
var params = {}
var paramm = param.split('&');
for(var k=0; k<paramm.length; k++) {
    var key = paramm[k].split('=')[0];
    var val = paramm[k].split('=')[1];
    params[key] = val;
}

function getFilter() {
    var filter = {
        page: parseInt($("#page").val()),
        fetchSize: $("#fetchSize").val(),
        orderSort: $("#orderSort").val()
    };
    return filter;
}

function getCount(urlCount) {
    console.log('getCount');
    if(!params.bookId) {
        $.ajax({
            url:urlCount,
            type:"GET",
            dataType:"json",
            success: function(result) {
                var elem = $('#navigation');
                $('#count').val(result.data.count);
                var fetchSize = parseInt($('#fetchSize').val());
                var count = parseInt($('#count').val());
                var pageCount = (count/fetchSize + 1).toFixed();
                if(count <= fetchSize) {
                    $('#navigation').hide();
                } else {
                    $('#navigation').show();
                    checkNavPanel(getFilter());
                }
            }
        });
    }
    return false;
}

function getTable(urlList, tabId) {
    var filter = getFilter();
    var tableData;
    if(params.bookId){
        var url = "/book/";
        url += params.bookId + "?";
        delete params.bookId;
        for(f in params) {
            url += f + "=" + params[f] + "&";
        }
        $.ajax({
            url: url,
            type:"GET",
            success: function(result) {
                if(result.error != null) {
                    alert(result.error);
                    location.href = "../../html/book/bookList.html";
                    return;
                }
                tableData = {
                    tableId: tabId,
                    offset: filter.page*filter.fetchSize,
                    list: result.data.writers
                }
                $('#count').val(tableData.list.length);
                for(p in params) {
                    if(p === 'w') {
                        $('h1').html('Book: ' + result.data.name);
                        $('h1').append('<br>Writers:');
                    }
                }
                buildTable(tableData);
            }
        });
    } else {
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
}