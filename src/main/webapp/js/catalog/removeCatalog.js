$(function () {
    $("#remove").click(function () {
            console.log('REMOVE CATALOG');
            var elem = document.getElementsByClassName('selected');
            if(elem.length === 0) return false;
            var catalog = {
                id: elem[0]
                    .getElementsByTagName('td')[0]
                    .innerHTML
            };
            console.log('catalog: ' + catalog);

            $.ajax({
                url:"/catalog/delete",
                type:"POST",
                data: JSON.stringify(catalog),
                contentType:"application/json; charset=utf-8",
                dataType:"json",
                success: function(result){
                    console.log(JSON.stringify(result));
                    location.reload();
                }
            });
    });
});