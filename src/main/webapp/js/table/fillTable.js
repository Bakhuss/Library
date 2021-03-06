console.log('Fill Table');
var param = window.location.search.substring(1);
var params = {}
var paramm = param.split('&');
for(var k=0; k<paramm.length; k++) {
    var key = paramm[k].split('=')[0];
    var val = paramm[k].split('=')[1];
    params[key] = val;
}

function getParams() {
    var param = window.location.search.substring(1);
    var params = {}
    var paramm = param.split('&');
    for(var k=0; k<paramm.length; k++) {
        var key = paramm[k].split('=')[0];
        var val = paramm[k].split('=')[1];
        params[key] = val;
    }
    return params;
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
//    alert($(window).width() + ' | ' + $(window).height());
    if(!params.personId && !params.bookId) {
//        alert('getCount ' + params.personId);
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
//                    alert('Count: ' + count);
                    checkNavPanel(getFilter());
                }
//                checkNavPanel(getFilter());
            }
        });
    }
    return false;
}

function getTable(urlList, tabId) {
//    alert(urlList + ' | ' + JSON.stringify(getParams()));
    var params = getParams();
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

                // Разобраться. Неверный count.
//                alert('count: ' + result.data.writersSize);
                $('#count').val(result.data.writersSize);
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
//            alert(JSON.stringify(params));
            var filter = getFilter();
//            alert(JSON.stringify(filter));
            for(p in params) {
                if(p === 'b') params[p] = filter.fetchSize + ',' + (filter.page * filter.fetchSize);
//                alert(p + ': ' + params[p]);
            }
            var url = "/person/";
            url += params.personId + "?";
            delete params.personId;
            for(f in params) {
             url += f + "=" + params[f] + "&";
            }
//            alert(url);
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
//                 alert(JSON.stringify(result));
                 console.log(JSON.stringify(tableData));
                 console.log(JSON.stringify(result));
//                 alert('count: ' + result.data.writtenBooksSize);
                 $('#count').val(result.data.writtenBooksSize);
                 for(p in params) {
                     if(p === 'b') {
                         $('h1').html('Person: ' + result.data.surname
                                           + ' ' + result.data.firstName
                                           + ' ' + result.data.secondName);
                         $('h1').append('<br>Written books:');
                     }
                 }
                 var count = $('#count').val();
                 if(count <= page.fetchSize) {
                     $('#navigation').hide();
                 } else {
                     $('#navigation').show();
//                     alert('showAlert');
//                     alert('Count: ' + count);
//                     checkNavPanel(getFilter());
                 }
//                 checkNavPanel(getFilter());
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