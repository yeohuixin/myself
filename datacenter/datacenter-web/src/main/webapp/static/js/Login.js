/**
 * Created by yhx on 2016/4/11.
 */
$(document).ready(function () {
    $("form").submit(function () {
        if ($("#txtUsername").hasClass("entry_gray") || $(".pwd_gray").is(":visible")) {
            $(".warnmsg").text("提示：请输入用户名和密码！");
            return false;
        } else {
            if($("#txtUsername").val() == "" || $("#password").val() == "") {
                $(".warnmsg").text("提示：请输入用户名和密码！");
                return false;
            }
            $("#username").val($("#txtUsername").val());
        }
    });
});
