$(function(){
    console.log('----------nextStep');
    var activeId;
    var clickId;
    var amountPages = 10;
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
        var startId = parseInt($('#prevPage').next().attr('id'));
        var page = startId - amountPages - 1;
        if(page <= 0){
            page = 0;
            $('#prevBlock').addClass('disabled');
        }
        buildNav(page, amountPages);
        $('#nextBlock').removeClass('disabled');
        $('li').removeClass('active');
        if(activeId <= (page + amountPages)) $('#' + activeId).addClass('active');
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
            getTable($('#urlList').val(), $('table').attr('id'));
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
        getTable($('#urlList').val(), $('table').attr('id'));
    }

    function nextBlock() {
        console.log('nextBlock');
        if($('#nextBlock.disabled').length != 0){
            console.log('nextBlock disabled');
            return false;
        }
        var endId = parseInt($('#nextPage').prev().attr('id'));
        var count = $('#count').val();
        var fetchSize = $('#fetchSize').val();
        var pageCount;
        if(count % fetchSize === 0) pageCount = count/fetchSize;
        else pageCount = Math.floor(count/fetchSize + 1);
        console.log('endId: ' + endId + '; pageCount: ' + pageCount);
        if(endId >= pageCount) return;
        var page = endId;
        if( (endId + amountPages) >= pageCount ){
            page = pageCount - amountPages;
            $('#nextBlock').addClass('disabled');
        }
        console.log('page: ' + page + '; amountPages: ' + amountPages);
        buildNav(page, amountPages);
        $('#prevBlock').removeClass('disabled');
        $('li').removeClass('active');
        if(activeId >= (page + 1)) $('#' + activeId).addClass('active');
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
        getTable($('#urlList').val(), $('table').attr('id'));
        console.log('nextNumberPage: getFilter: ' + JSON.stringify(getFilter()));
    }

});