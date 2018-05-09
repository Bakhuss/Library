$(function() {
    var id = window.location.search.substring(1).split('=')[1];
    console.log(id);
    var newUrl = "/catalog/" + id;
    if(id != null) {
        $.ajax({
            url: newUrl,
            type:"GET",
            dataType:"json",
            success: function(result) {
                if(result.error != null) {
                    alert(result.error);
                    location.href = "../../html/catalog/catalogList.html";
                    return;
                }
                console.log(JSON.stringify(result));
                $('#bodyTitle').append(': ' + result.data.bookName);
                document.getElementById('id').value = result.data.id;
                document.getElementById('bookId').value = result.data.bookId;
                document.getElementById('name').value = result.data.bookName;
                console.log(document.getElementById('id').value);
                console.log(document.getElementById('name').value);
            }
        });
    }
});