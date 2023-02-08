$("#b1").click(function (){
    $(".m-item").toggleClass("m-mobile-hide");
});
$('#payButton').popup({
    popup:$('.popup.payQR'),
    on:'click',
    position:'bottom center'
});
$('.ui.dropdown').dropdown({
    on: 'hover'
})