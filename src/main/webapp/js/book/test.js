function buildNavPanel(filter) {
    if(filter.page <= 0) $('#prevBlock, #prevPage').addClass('disabled');
    else $('#prevBlock, #prevPage').removeClass('disabled');


}