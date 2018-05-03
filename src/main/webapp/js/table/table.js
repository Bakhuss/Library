function buildTable(tableData) {
    console.log(tableData);
    $('#' + tableData.tableId + ' tbody tr').remove();
    tableData.list.forEach(function(item, i) {
        var tr = '<tr>';
        tr += '<td hidden>' + item.id + '</td>';
        tr += '<td>' + (i+1+tableData.offset) + '</td>';
        tr += '<td>' + item.name + '</td>';
        tr += '</tr';
        $('#' + tableData.tableId +  ' tbody').append(tr);
        $('head script[src="../../js/mark.js"]').remove();
        $('head script').after('<script src="../../js/mark.js"></script>');
    });
//    alert(tableId);
}