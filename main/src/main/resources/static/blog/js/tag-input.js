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
$('.ui.form').form({
    fields:{
        title:{
            identifier:'title',
            rules:[{
                type:'empty',
                prompt:'标题：请输入博客标题'
            }]
        }
    }
})

function PageRefresh() {
    window.location = window.location.pathname;
}