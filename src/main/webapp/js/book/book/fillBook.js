$(function() {
    var id = window.location.search.substring(1).split('=')[1];
    console.log(id);
    var newUrl = "/book/" + id;
    if(id != null) {
        $.ajax({
            url: newUrl,
            type:"GET",
            dataType:"json",
            success: function(result) {
                if(result.error != null) {
                    alert(result.error);
                    location.href = "../../html/book/bookList.html";
                    return;
                }
                console.log(result.data.id);
                console.log(JSON.stringify(result));
                $('#bodyTitle').append(': ' + result.data.name);
                document.getElementById('id').value = result.data.id;
                document.getElementById('name').value = result.data.name;
                console.log(document.getElementById('id').value);
                console.log(document.getElementById('name').value);
            }
        });
    }
});