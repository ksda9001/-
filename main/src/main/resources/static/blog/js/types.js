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
        url:"/type",
        data:{"begin":begin,"size":size,name:$("[name='name']").val()},
        type:"get",
        success:function (result) {
            $("#table-container").html(result);
        }
    })
}
function deleteType(button) {
    $.ajax({
        url: "/type",
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