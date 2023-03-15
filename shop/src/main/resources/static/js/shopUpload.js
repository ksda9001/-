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
                prompt:'标题：请输入名称'
            }]
        },
        type:{
            identifier:'type',
            rules:[{
                type:'empty',
                prompt:'分类：请选择分类'
            }]
        },
        pictureUrl:{
            identifier:'pictureUrl',
            rules:[{
                type:'empty',
                prompt:'首图地址：请输入首图地址'
            }]
        },
    }
})
var contentEditor;
$(function() {
    contentEditor = editormd( {
        id      :"md-content",
        width   : "100%",
        height  : 640,
        syncScrolling : "single",
        path    : "/lib/editor.md-master/lib/"
    });

    /*
    // or
    testEditor = editormd({
        id      : "test-editormd",
        width   : "90%",
        height  : 640,
        path    : "../lib/"
    });
    */
});

function PageRefresh() {
    window.location = window.location.pathname;
}