function nextStep() {
    console.log('----------nextStep');
    $('li').click(function(){
        var id = parseInt($('#activeId').val());
        var clickId = $(this).attr('id');
        console.log('id: ' + id);
        switch(clickId) {

            case 'prevBlock': prevBlock();
            break;

            case 'prevPage': prevPage();
            break;

            case 'nextPage': nextPage();
            break;

            case 'nextBlock': nextBlock();
            break;

            default: nextNumberPage();
            break;
        }

        function prevBlock() {
            console.log('prevBlock');
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

        function nextBlock() {
            console.log('nextBlock');
            var amountPages = 10;
            var endId = $('#nextPage').prev().attr('id');
            var count = $('#count').val();
            var fetchSize = $('#fetchSize').val();
            var pageCount = (count/fetchSize + 1).toFixed();
            console.log('endId: ' + endId + '; pageCount: ' + pageCount);
            if(endId >= pageCount) return false;
            var page = parseInt($('#prevPage').next().attr('id')) - 1 + amountPages;
            console.log('page: ' + page + '; amountPages: ' + amountPages);
            $.getScript('../../js/book/navigPanel.js');
            buildNavPanel(page, amountPages);
        }

        function nextNumberPage() {
            console.log(clickId);
            $('#page').val(clickId-1);
            $.getScript('../../js/book/bookList/fillBookList.js');
        }
    });
}