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
    if(params.bookId) alert('params.bookId: ' + params.bookId);
    if(params.personId) alert('params.personId: ' + params.personId);
//    if( (params.bookId = '') | (params.personId = '') ) {
    if(params.personId = '') {
        alert('getCount ' + params.personId);
        $.ajax({
            url:urlCount,
            type:"GET",
            dataType:"json",
            success: function(result) {
                var elem = $('#navigation');
                $('#count').val(result.data.count);
                var fetchSize = parseInt($('#fetchSize').val());
                var count = parseInt($('#count').val());
                var pageCount;
                if(count % fetchSize === 0) pageCount = count/fetchSize;
                else pageCount = Math.floor(count/fetchSize + 1);
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
    } else if(params.personId){
            console.log('Person id');
            alert(JSON.stringify(params));
            var filter = getFilter();
            alert(JSON.stringify(filter));
            for(p in params) {
                if(p === 'b') params[p] = filter.fetchSize + ',' + filter.page;
                alert(p + ': ' + params[p]);
            }
            var url = "/person/";
            url += params.personId + "?";
            delete params.personId;
            for(f in params) {
             url += f + "=" + params[f] + "&";
            }
            alert(url);
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
                     list: result.data.writtenBooks
                 }
                 console.log(JSON.stringify(tableData))
                 $('#count').val(tableData.list.length);
//                 alert(tableData.list.length);
                 for(p in params) {
                     if(p === 'b') {
                         $('h1').html('Person: ' + result.data.surname
                                           + ' ' + result.data.firstName
                                           + ' ' + result.data.secondName);
                         $('h1').append('<br>Written books:');
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