function nextStep() {
    $('li').click(function(){
        $('li').removeClass('active');
        $(this).addClass('active');
        console.log($(this).context.id);
        console.log(document.getElementById('startPage').value);
    });
}