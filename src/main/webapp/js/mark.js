function mark() {
    $('tr').click(function(){
        $('tr.selected').removeClass('selected');
        $(this).addClass('selected');
    });
}