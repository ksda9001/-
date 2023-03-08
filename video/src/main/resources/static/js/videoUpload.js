$("#b1").click(function () {
    $(".m-item").toggleClass("m-mobile-hide");
});
$('#payButton').popup({
    popup: $('.popup.payQR'),
    on: 'click',
    position: 'bottom center'
});
$('.ui.dropdown').dropdown({
    on: 'hover'
})
$('.ui.form').form({
    fields: {
        title: {
            identifier: 'title',
            rules: [{
                type: 'empty',
                prompt: '标题：请输入标题'
            }]
        },
        type: {
            identifier: 'type',
            rules: [{
                type: 'empty',
                prompt: '分类：请选择分类'
            }]
        },
        pictureUrl: {
            identifier: 'pictureUrl',
            rules: [{
                type: 'empty',
                prompt: '封面地址：请输入封面地址'
            }]
        },
    }
})

function PageRefresh() {
    window.location = window.location.pathname;
}