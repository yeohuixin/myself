/**
 * Created by yhx on 2016/3/9.
 */
function checkLogin() {
    var loginName = $("#loginName").text();
    var loginPassword = $("#loginPassword").text();
    window.location="/welcome?&loginName="+loginName+"&loginPassword="+loginPassword;
}
