function passwordCheck() {
    var userPassword = document.getElementsByName("password")[0].value;
    var userPassword2 = document.getElementsByName("userPassword2")[0].value;
    var tel = document.getElementsByName("userPhone")[0].value;
    var email = document.getElementsByName("userEmail")[0].value;
    var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
    if (userPassword !== userPassword2) {
        alert("密码不一致！");
        return false;
    }
    if (!(reg.test(email))) {
        alert("邮箱格式不正确!");
        return false;
    }
    if (tel.length !== 11) {
        alert("请输入正确的手机号码！");
        return false;
    }
    return true;
}