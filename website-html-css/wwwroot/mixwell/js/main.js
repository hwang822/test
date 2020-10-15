const responsive = {
    0: {
        items: 1
    },
    320: {
        items: 1
    },
    560: {
        items: 2
    },
    960: {
        items: 3
    }
}

$(document).ready(function () {

//    show_hide(0);

    $nav = $('.nav');
    $toggleCollapse = $('.toggle-collapse');

    /** click event on toggle menu */
    $toggleCollapse.click(function () {
        $nav.toggleClass('collapse');
    })

    // owl-crousel for blog
    $('.owl-carousel').owlCarousel({
        loop: true,
        autoplay: false,
        autoplayTimeout: 3000,
        dots: false,
        nav: true,
        navText: [$('.owl-navigation .owl-nav-prev'), $('.owl-navigation .owl-nav-next')],
        responsive: responsive
    });


    // click to scroll top
    $('.move-up span').click(function () {
        $('html, body').animate({
            scrollTop: 0
        }, 1000);
    })

    // AOS Instance
    AOS.init();

});

var a = 0;
function show_hide(a)
{
    
    document.getElementById("post-0").style.display="none";
    document.getElementById("post-1").style.display="none";
    document.getElementById("post-2").style.display="none";
    document.getElementById("post-3").style.display="none";
    document.getElementById("post-4").style.display="none";
    document.getElementById("post-5").style.display="none";

    if(a==0){
        document.getElementById("post-0").style.display="inline";
    }
    else if(a==1){
        document.getElementById("post-1").style.display="inline";
    }
    else if(a==2){
        document.getElementById("post-2").style.display="inline";
    }
    else if(a==3){
        document.getElementById("post-3").style.display="inline";
    }
    else if(a==4){
        document.getElementById("post-4").style.display="inline";
    }
    else if(a==5){
        document.getElementById("post-5").style.display="inline";
    }

    return;           
}
