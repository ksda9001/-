$(document).ready(function () {
    //获得文本框对象
    var t = $("#buynum");
    //初始化数量为1,并失效减
    $('#min').attr('disabled', true);
    //数量增加操作
    $("#add").click(function () {
        t.val(parseInt(t.val()) + 1)
        if (parseInt(t.val()) != 1) {
            $('#min').attr('disabled', false);
        }

    })
    //数量减少操作
    $("#min").click(function () {
        t.val(parseInt(t.val()) - 1);
        if (parseInt(t.val()) == 1) {
            $('#min').attr('disabled', true);
        }
    })
})

// 提交购物车
function buy() {
    // /mall/order/createOrder?id=1
    var url = "http://localhost:8080/mall/order/createOrder" + search +"&buynum=" + $("#buynum").val();
    window.location = url;
}