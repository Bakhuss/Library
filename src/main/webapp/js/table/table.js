function buildTable(tableData) {
    $('#' + tableData.tableId + ' tbody tr').remove();
    var map = new Map();
    var elems = $('thead th');
    for(var l=0; l<elems.length; l++) {
        map.set(l, elems[l].innerText.replace(' ','').toLowerCase().trim());
    }
    console.log(map);
    tableData.list.forEach(function(item, i) {
        var tr = '<tr>';
        tr += '<td hidden>' + item.id + '</td>';
        tr += '<td>' + (i+1+tableData.offset) + '</td>';

        for(var m=2; m<map.size; m++) {
            var bool = true;
            for(t in item) {
                if(map.get(m) === t.toLowerCase()) {
                    console.log(item[t]);
                    tr += '<td>' + item[t] + '</td>';
                    bool = false;
                }
            }
            if(bool) tr += '<td></td>';
        }

        tr += '</tr';
        $('#' + tableData.tableId +  ' tbody').append(tr);
    });
    $('head script[src="../../js/mark.js"]').remove();
    $('head script').after('<script src="../../js/mark.js"></script>');
    alert(JSON.stringify(getFilter()));
    checkNavPanel(getFilter());
//    alert($('#bookListTable').width() + ' | ' + $('#bookListTable').height());
//    alert($('tbody tr').width() + ' | ' + $('tbody tr').height());
}