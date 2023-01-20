//页面载入完后即启动
window.onload = function () {
    //复选框
    var all = document.querySelector('#all');
    var options = Array.from(document.querySelectorAll('.item'));
    all.onchange = function () {
        options.forEach(item => {
            item.checked = all.checked;
        })
    }
    //监听当所有的选上后
    options.forEach(item => {
        item.onchange = function () {
            all.checked = options.every(x => x.checked)
        }
    })
}
/*
   参数解释：
   title	标题
   url		请求的url
   id		需要操作的数据id
   w		弹出层宽度（缺省调默认值）
   h		弹出层高度（缺省调默认值）
*/

/*管理员-增加*/
function admin_add(title, url, w, h) {
    layer_show(title, url, w, h);
}

/*管理员-删除*/
function admin_del(obj, id) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'POST', url: '', dataType: 'json', success: function (data) {
                $(obj).parents("tr").remove();
                layer.msg('已删除!', {icon: 1, time: 1000});
            }, error: function (data) {
                console.log(data.msg);
            },
        });
    });
}

/*管理员-编辑*/
function admin_edit(title, url, id, w, h) {
    layer_show(title, url, w, h);
}

//页面刷新
function PageRefresh() {
    window.location = window.location.pathname;
}

//判断复选框多选
function Check() {
    if (checkboxs("item") == false) { // checkboxname: checkbox的名字
        layer.confirm('请至少选择一条要改变状态的数据！');
        return false;
    }
    return true;
}

function checkboxs(objNam) {
    var obj = document.getElementsByClassName(objNam); // 获取对象
    var objLen = obj.length; // 获取数组长度
    var objYN; // bool
    var i;
    objYN = false;
    for (i = 0; i < objLen; i++) {
        if (obj[i].checked == true) {
            objYN = true;
            break;
        }
    }
    return objYN;
}

/* 管理员启用、禁用函数 */
function editStatus(t, status) {
    var check = Check();
    // 获取所有页面要删除的数据集合，并判断用户至少选择了一条数据
    if (check) {
        var userId = $("input[name=userID]");
        var obj = Array.from(document.querySelectorAll('.item')); // 获取复选框
        var objLen = obj.length; // 获取复选框总数
        var i;
        var num;
        num = 0;
        //统计选中复选框个数
        for (i = 0; i < objLen; i++) {
            if (obj[i].checked == true) {
                num++;
            }
        }
        if (status === 0) {
            layer.confirm('确认要停用选中的' + num + '个账户吗？', function () {
                $.ajax({
                    type: "put",
                    dataType: "json",
                    url: "/admin/editStatus/" + status,
                    data: userId.serialize(),
                    async: false,   // 设置 Ajax 之间的同步（该同步会让Ajax锁住浏览器请求响应交互，保证每次请求被响应之后才释放浏览器）
                    success: function (data) {
                        if (data.code === 200) {
                            layer.msg('已停用!', {icon: 6, time: 1000});
                            //成功后定时刷新页面
                            setTimeout(() => location.replace(location.href), 1000);
                        } else {
                            layer.msg('操作失败!', {icon: 5, time: 1000});
                            setTimeout(() => location.replace(location.href), 1000);
                        }
                    }
                });
            });
        }
        if (status === 1) {
            layer.confirm('确认要启用选中的' + num + '个账户吗？', function () {
                $.ajax({
                    type: "put",
                    dataType: "json",
                    url: "/admin/editStatus/" + status,
                    data: userId.serialize(),
                    async: false,  // 设置 Ajax 之间的同步（该同步会让Ajax锁住浏览器请求响应交互，保证每次请求被响应之后才释放浏览器）
                    success: function (data) {
                        if (data.code === 200) {
                            layer.msg('已启用!', {icon: 6, time: 1000});
                            //成功后定时刷新页面
                            setTimeout(() => location.replace(location.href), 1000);
                        } else {
                            layer.msg('操作失败!', {icon: 5, time: 1000});
                            setTimeout(() => location.replace(location.href), 1000);
                        }
                    }
                });
            });
        }
    }
}


// //搜索（暂时无用）
//     function search() {
//         //获取关键字并进行序列化
//         var keywords = $("input[name=keywords]").serialize();
//         //获取当前路径URL并拼接关键字
//         window.location = window.location.pathname + "?" + keywords;
//     }