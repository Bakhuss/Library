    console.log('navigPanel.js');

    function checkNavPanel(filter) {
        console.log('checkNavPanel: ' + JSON.stringify(filter));
        $('#activeId').val(parseInt($('#page').val())+1);
        console.log('checkNavPanel: activeId: ' + $('#activeId').val())
        var fetchSize = $('#fetchSize').val();
        var count = $('#count').val();
        var pageCount = (count/fetchSize + 1).toFixed();
        console.log(count + ', ' + fetchSize + ', ' + pageCount);

        if(count <= fetchSize) {
            $('#navigation').hide();
        } else {
            $('#navigation').show();

            var startId = parseInt($('#prevPage').next().attr('id'));
            var endId = parseInt($('#nextPage').prev().attr('id'));
            var activeId = parseInt($('#activeId').val());
            console.log('checkNavPanel: activeId: ' + activeId + ', startId: ' + startId + ', endId: ' + endId);
            if(activeId > endId) buildNav(activeId-10, 10);
            if(activeId < startId) buildNav(activeId-1, 10);
            startId = parseInt($('#prevPage').next().attr('id'));
            endId = parseInt($('#nextPage').prev().attr('id'));

            if(activeId >= pageCount) $('#nextPage').addClass('disabled');
            else $('#nextPage').removeClass('disabled');

            if(activeId <= 1) $('#prevPage').addClass('disabled');
            else $('#prevPage').removeClass('disabled');

            if(startId <= 1) $('#prevBlock').addClass('disabled');
            else $('#prevBlock').removeClass('disabled');

            if(endId >= pageCount) $('#nextBlock').addClass('disabled');
            else $('#nextBlock').removeClass('disabled');

            $('li.active').removeClass('active');
            $('#' + ($('#activeId').val())).addClass('active');
            console.log('navigPanel: activeId: ' + $('#activeId').val())
        }
        return false;
    }

    function buildNavPanel(startPage, amountPages) {
        console.log('buildNavPanel: ' + startPage + ', ' + amountPages);
        $('li:not(#prevBlock, #prevPage, #nextPage, #nextBlock)').remove();
        for(var i=startPage + 1; i<=startPage + amountPages; i++) {
            $('#nextPage').before('<li id="'+ i +'"><a href="#">' + i + '</a></li>');
        }
    }

    function buildNav(startPage, amountPages) {
        var elems = $('li:not(#prevBlock, #prevPage, #nextPage, #nextBlock)');
        for(var i=startPage + 1, j = 0; i<=startPage + amountPages, j < 10; i++, j++) {
            $(elems[j]).attr('id', i);
            $(elems[j]).find('a').text(i);
        }
    }
