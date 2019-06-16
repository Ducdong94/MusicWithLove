$(function () {
    config.showMore($('#top100vn a'), config.topvnShow, $('.btn-top-vn-more'));
    config.showMore($('#top100us a'), config.topusShow, $('.btn-top-us-more'));
    config.showMore($('#topedm a'), config.topedmShow, $('.btn-edm-more'));

    $('.btn-top-vn-more').click(function () {
        config.topvnShow += 10;
        config.showMore($('#top100vn a'), config.topvnShow, $(this));
    });

    $('.btn-top-us-more').click(function () {
        config.topusShow += 10;
        config.showMore($('#top100us a'), config.topusShow, $(this));
    });
    
    $('.btn-edm-more').click(function () {
        config.topedmShow += 10;
        config.showMore($('#topedm a'), config.topedmShow, $(this));
    });
});

var config = {
    topvnShow: 10,
    topusShow: 10,
    topedmShow: 10,
    showMore: function (target, number, btn) {
        target.each(function (index, item) {
            if (index < number) {
                console.log($(this).prop('hidden'))
                console.log($(this).html())
                if ($(this).prop('hidden') === true) {
                    $(this).prop('hidden', false);
                    if (number >= target.length) {
                        btn.hide();
                    }
                }
            }
        });
    }
}