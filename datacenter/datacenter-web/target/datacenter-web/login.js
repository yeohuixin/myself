/**
 * Created by yhx on 2016/3/9.
 */

function checkLogin() {
    var loginName = $("#login_putin").find("loginName").text();
    var loginPassword = $("#login_putin").find("loginPassword").text();
    window.location="/welcome?&loginName="+loginName+"&loginPassword="+loginPassword;
}
