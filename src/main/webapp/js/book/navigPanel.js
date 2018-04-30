    console.log('navigPanel.js');

    function checkNavPanel(filter) {
        if(filter.page <= 0) $('#prevBlock, #prevPage').addClass('disabled');
        else $('#prevBlock, #prevPage').removeClass('disabled');

    //    $('li:not(#prevBlock, #prevPage, #nextPage, #nextBlock)').remove();
    //    var page = parseInt(filter.page);
    //
    //    for(var i=page + 1; i<=page + 10; i++) {
    //        $('#nextPage').before('<li id="'+ i +'"><a href="#">' + i + '</a></li>');
    //    }
    }

    function buildNavPanel(startPage, amountPages) {
        $('li:not(#prevBlock, #prevPage, #nextPage, #nextBlock)').remove();
        for(var i=startPage + 1; i<=startPage + amountPages; i++) {
            $('#nextPage').before('<li id="'+ i +'"><a href="#">' + i + '</a></li>');
        }
    }
