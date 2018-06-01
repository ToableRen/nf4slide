$(function () {
    var playSlideId = getUrlParameter("slideId");
    var isControl = getUrlParameter("control");
    $.ajax({
        url: "fetchSlidePlayById",
        type: "POST",
        cache: false,
        data: {slideId: playSlideId},
        dataType: "JSON",
        async: false,
        success: function (data) {
            pageContentInit(playSlideId, isControl, data);
        },
        error: function () {
        }
    })
    dialogInit(isControl);
})