$("#b1").click(function (){
    $(".m-item").toggleClass("m-mobile-hide");
});



$('.ui.dropdown').dropdown({
    on: 'hover'
})


var currentPage=1;
var size=6;
var begin=0;

function initialization() {
    begin=0;
    loadData();
}
function next() {
    currentPage++;
    begin+=size;
    loadData();
}
function pre() {
    if (begin>0){
        begin-=size;
        loadData();
    }
}
function loadData(){
    $.ajax({
        url:"/blogCombination_blogs",
        data:{"begin":begin,"size":size,title:$("[name='title']").val(),type: $("[name='type']").val(),},
        type:"get",
        success:function (result) {
            $("#table-container").html(result);
        }
    })
}

function deleteBlog(button) {
    $.ajax({
        url: "/blog",
        data:{"id":$(button).attr("data-id")},
        type:"delete",
        success:function(result){
            $("body").html(result);
        }
    })
}

function PageRefresh() {
    window.location = window.location.pathname;
}