(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner(0);


    // Fixed Navbar
    $(window).scroll(function () {
        if ($(window).width() < 992) {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow');
            } else {
                $('.fixed-top').removeClass('shadow');
            }
        } else {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow').css('top', -55);
            } else {
                $('.fixed-top').removeClass('shadow').css('top', 0);
            }
        } 
    });
    
    
   // Back to top button
   $(window).scroll(function () {
    if ($(this).scrollTop() > 300) {
        $('.back-to-top').fadeIn('slow');
    } else {
        $('.back-to-top').fadeOut('slow');
    }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Testimonial carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 2000,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav : true,
        navText : [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0:{
                items:1
            },
            576:{
                items:1
            },
            768:{
                items:1
            },
            992:{
                items:2
            },
            1200:{
                items:2
            }
        }
    });


    // vegetable carousel
    $(".vegetable-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1500,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav : true,
        navText : [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0:{
                items:1
            },
            576:{
                items:1
            },
            768:{
                items:2
            },
            992:{
                items:3
            },
            1200:{
                items:4
            }
        }
    });


    // Modal Video
    $(document).ready(function () {
        var $videoSrc;
        $('.btn-play').click(function () {
            $videoSrc = $(this).data("src");
        });
        console.log($videoSrc);

        $('#videoModal').on('shown.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0");
        })

        $('#videoModal').on('hide.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc);
        })
    });

    function updateTotalPrice() {
		const shippingFee = 3.00
        var total = 0;
        $('tr[data-product-id]').each(function() {
            var pricePerItem = parseFloat($(this).data('product-price'));
            var quantity = parseFloat($(this).find('.quantity-input').val());
            total += pricePerItem * quantity;
        });
        $('#subtotal-price').text(total.toFixed(2) + ' $');
        var finalTotal = total + shippingFee;
        $('#total-price').text(finalTotal.toFixed(2) + ' $');
        
    }
    // Product Quantity
    $('.quantity button').on('click', function () {
        var button = $(this);
        var inputGroup = button.closest('.input-group');
        var input = inputGroup.find('.quantity-input');
        var oldValue = parseFloat(input.val());
        var pricePerItem = parseFloat(button.closest('tr').data('product-price'));
        var newVal = oldValue;

        if (button.hasClass('btn-plus')) {
            newVal = oldValue + 1;
        } else if (button.hasClass('btn-minus') && oldValue > 0) {
            newVal = oldValue - 1;
        }

        input.val(newVal);
        var newTotal = newVal * pricePerItem;
        var formattedTotal = newTotal.toFixed(2) + ' $'; // Định dạng số làm tròn đến 2 chữ số

        button.closest('tr').find('.total-price').text(formattedTotal);
        updateTotalPrice();
    });
	updateTotalPrice();
	
	
})(jQuery);

