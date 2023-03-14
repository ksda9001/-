$(function () {
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });

    $("#form-admin-add").validate({
        rules: {
            adminName: {
                required: true,
                minlength: 4,
                maxlength: 16
            },
            password: {
                required: true,
            },
            userPassword2: {
                required: true,
                equalTo: "#password"
            },
            userSex: {
                required: true,
            },
            userPhone: {
                required: true,
                isPhone: true,
            },
            userEmail: {
                required: true,
                email: true,
            },
            role: {
                required: true,
            },
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid"
    });
})