<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>LogIn</title>
</head>
<body>

<div class="login_putin">
    <ul>
        <li><input id="loginName" type="text"></li>
        <li><input id="loginPassword" type="password"></li>
    </ul>
</div>
<div class="login_btn">
    <a id="login" class="login" href="http://127.0.0.1/welcome" onclick="checkLogin()">登陆</a>
</div>
</body>
</html>
<script>

    function checkLogin(){
        var loginName = $("#loginName").val();
        var loginPassword = $("#loginPassword").val();

        $.get("http://127.0.0.1/welcome?&loginName="+loginName+"&loginPassword="+loginPassword);
    }
</script>

<%--*{--%>
<%--margin:0;--%>
<%--padding:0;}--%>

<%--li{--%>
<%--list-style-type:none;--%>
<%--margin:0;--%>
<%--padding:0;}--%>
<%--a{--%>
<%--text-decoration:none;--%>
<%--color:#000;}--%>

<%--/*---------------------按钮-----------------------------*/--%>
<%--.login_putin ul li input{--%>
<%--margin: 0;--%>
<%--width:70%;--%>
<%--padding: 1em 2em 1em 5.4em;--%>
<%---webkit-border-radius:.3em;--%>
<%---moz-border-radius: .3em;--%>
<%--border: 1px solid #999;--%>
<%--}--%>

<%--.login_btn{--%>
<%--width:300px;--%>
<%--margin:40px auto 0 auto;--%>
<%--}--%>

<%--.login_btn input{--%>
<%--width:100%;--%>
<%--margin:0;--%>
<%--padding:.5em 0;--%>
<%---webkit-border-radius:.3em;--%>
<%---moz-border-radius: .3em;--%>
<%--border:#1263be solid 1px;--%>
<%--background:#1b85fd;--%>
<%--color:#FFF;--%>
<%--font-size:17px;--%>
<%--font-weight:bolder;--%>
<%--letter-spacing:1em;--%>
<%--}--%>

<%--.login_btn input:hover{--%>
<%--background:#1263be;--%>
<%--}--%>