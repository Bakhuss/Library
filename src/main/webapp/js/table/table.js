function buildTable(tableData) {
//    console.log(tableData);
    $('#' + tableData.tableId + ' tbody tr').remove();
    var set = new Set();
    var elems = $('thead th');
    for(var l=2; l<elems.length; l++) {
        set.add(elems[l].innerText.replace(' ','').toLowerCase().trim());
    }
    tableData.list.forEach(function(item, i) {
        var tr = '<tr>';
        tr += '<td hidden>' + item.id + '</td>';
        tr += '<td>' + (i+1+tableData.offset) + '</td>';
        for(var s in set) {
            for(var key in item) {
                if(key.toLowerCase() === s) {
                    tr += '<td>' + item[key] + '</td>'
                }
            }
        }
        set.forEach( f => {
            for(var key in item) {
                if(key.toLowerCase() === f) {
                    tr += '<td>' + item[key] + '</td>'
                }
            }
            }
//            tr += '<td>' + item['firstName'] + '</td>'
        );
//        for(var key in item) {
//            if(set.has(key.toLowerCase())){
//                tr += '<td>' + item[key] + '</td>';
//            } else {
//                tr += '<td></td>';
//            }
//        }
//        tr += '<td>' + item.name + '</td>';
        tr += '</tr';
        $('#' + tableData.tableId +  ' tbody').append(tr);
        $('head script[src="../../js/mark.js"]').remove();
        $('head script').after('<script src="../../js/mark.js"></script>');

//        var tr = '<tr>';
//        tr += '<td hidden>' + item.id + '</td>';
//        tr += '<td>' + (i+1+tableData.offset) + '</td>';
//        tr += '<td>' + item.name + '</td>';
//        tr += '</tr';
//        $('#' + tableData.tableId +  ' tbody').append(tr);
//        $('head script[src="../../js/mark.js"]').remove();
//        $('head script').after('<script src="../../js/mark.js"></script>');
    });
}

function buildTr(tr, item) {
//    tr += '<td>' + item
}