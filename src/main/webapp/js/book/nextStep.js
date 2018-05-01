$(function(){
    console.log('----------nextStep');
    var activeId;
    var clickId;
    $('li').click(function(){
        activeId = parseInt($('#activeId').val());
        clickId = $(this).attr('id');
        console.log('start: clickId: ' + clickId + ', activeId: ' + activeId);
        if(clickId == activeId) return false;

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
        }
        console.log('nextStep: switch: clickId');
    });

    function prevBlock() {
        console.log('prevBlock');
        if($('#prevBlock.disabled').length != 0){
            console.log('prevBlock disabled');
            return false;
        }
    }

    function prevPage() {
        console.log('prevPage');
        if($('#prevPage.disabled').length != 0){
            console.log('prevPage disabled');
            return false;
        }
        console.log('prevPage enabled');
            $('#page').val(activeId-2);
            console.log("page: " + $('#page').val());
            checkNavPanel(getFilter());
            getBooks();
    }

    function nextPage() {
        if($('#nextPage.disabled').length != 0){
            console.log('nextPage disabled');
            return false;
        }
        console.log('nextPage');
        $('#page').val(activeId);
        console.log("page: " + $('#page').val())
        checkNavPanel(getFilter());
        getBooks();
    }

    function nextBlock() {
        console.log('nextBlock');
        if($('#nextBlock.disabled').length != 0){
            console.log('nextBlock disabled');
            return false;
        }
//            var amountPages = 10;
//            var endId = $('#nextPage').prev().attr('id');
//            var count = $('#count').val();
//            var fetchSize = $('#fetchSize').val();
//            var pageCount = (count/fetchSize + 1).toFixed();
//            console.log('endId: ' + endId + '; pageCount: ' + pageCount);
//            if(endId >= pageCount) return false;
//            var page = parseInt($('#prevPage').next().attr('id')) - 1 + amountPages;
//            console.log('page: ' + page + '; amountPages: ' + amountPages);
//            $.getScript('../../js/book/navigPanel.js');
//            buildNavPanel(page, amountPages);
    }

    function nextNumberPage() {
        console.log('nextNumberPage');
        console.log('clickId: ' + clickId);
        if(clickId == activeId) return;
        console.log('clickId: ' + clickId + ', activeId: ' + activeId);
        $('#page').val(clickId-1);
        console.log('page: ' + getFilter().page);
        $('#activeId').val(clickId);
        checkNavPanel(getFilter());
        getBooks();
        console.log('nextNumberPage: getFilter: ' + JSON.stringify(getFilter()));
    }

});