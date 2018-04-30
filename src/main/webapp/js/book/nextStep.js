function nextStep() {
    console.log('----------nextStep');
    $('li').click(function(){
        var id = parseInt($('li.active').attr('id'));
        console.log('id: ' + id);
        switch($(this).attr('id')) {

            case 'prevPage': prevPage();
            break;

            case 'nextPage': nextPage();
            break;
            
            default:
        }

        function prevPage() {
            if($('#page').val() == 0) return false;
            $('#page').val(id-2);
            console.log("page: " + $('#page').val())
            $.getScript('../../js/book/bookList/fillBookList.js');
        }

        function nextPage() {
            $('#page').val(id);
            console.log("page: " + $('#page').val())
            $.getScript('../../js/book/bookList/fillBookList.js');
        }
    });
}