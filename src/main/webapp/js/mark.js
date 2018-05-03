$(function mark() {
    $('tr').click(function(){
        if($(this).parent().prop('tagName') != 'TBODY') return;
        $('tr.selected').removeClass('selected');
        $(this).addClass('selected');
    });
});