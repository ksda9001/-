window.onload = function () {
    var str = "";
    var Words = document.getElementById("words");
    window.setInterval(function () {
        //定时刷新页面，
        $.ajax({
            url: '/message/getMessage',
            contentType: "application/json;charset=UTF-8",
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify({"user": "A"}),
            success: function (res) {
                //请求成功后执行的代码
                var aa = res.flag;
                if (aa) {
                    str = '<div class="atalk"><span>用户 :' + res.val + '</span></div>';
                    Words.innerHTML = Words.innerHTML + str;
                }
            }
        });
    }, 2000);
}

function sender() {
    var Words = document.getElementById("words");
    //var Who = document.getElementById("who");
    var TalkWords = document.getElementById("talkwords");
    var message = TalkWords.value;
    //定义空字符串
    var str = "";
    if (TalkWords.value == "") {
        // 消息为空时弹窗
        alert("消息不能为空");
        return;
    }
    str = '<div class="btalk"><span>我 :' + TalkWords.value + '</span></div>';
    Words.innerHTML = Words.innerHTML + str;
    TalkWords.value = "";
    $.ajax({
        url: '/message/sendMessage',
        contentType: "application/json;charset=UTF-8",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify({"user": "A", "message": message}),
        success: function (res) {
            var a = "";
        }
    });
}