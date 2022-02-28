$(document).ready(function () {
    // Scroll to top on page reload
    $(this).scrollTop(0);
    
    // SET BOOLEAN
    var scrolled = false;

    //SCROLL
    $(window).on("scroll", function () {
        //SOCIAL ICONS
        if ($(this).scrollTop() > 150 && !scrolled) {
            $(".promise-heading").each(function (i) {
                // store the item around for use in the 'timeout' function
                var $item = $(this);
                // execute this function sometime later:
                $item.animate({
                    opacity: 1
                },
                    {
                        easing: 'swing',
                        duration: 1500,
                    });
                // each element should animate half a second after the last one.
            });
            $(".card").each(function (i) {
                // store the item around for use in the 'timeout' function
                var $item = $(this);
                // execute this function sometime later:
                setTimeout(function () {
                    $item.animate({
                        opacity: 1,
                        bottom: "0px"
                    },
                        {
                            // easing: 'swing',
                            duration: 1000,
                        })
                }, 500 * i);
                // each element should animate half a second after the last one.
            });
            scrolled = true;
        } else {
            scrolled = false;
        }
    });
})