$('tr').click(function(){
    if($(this).parent().prop('tagName') != 'TBODY') return;
    console.log('selected');
    if($(this).hasClass('selected')) {
        console.log('selected 1');
        $(this).removeClass('selected');
    } else {
        console.log('selected 2');
        $('tr.selected').removeClass('selected');
        $(this).addClass('selected');
    }
});
