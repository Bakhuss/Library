function target() {
    $('tr').click(function(){
        $('tr').removeClass();
        $(this).addClass('selected');
    });
}