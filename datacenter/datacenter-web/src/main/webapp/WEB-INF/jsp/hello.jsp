<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>提交页面</title>
</head>
<body>
<p>信息录入表格</p>
<div>
    <table id="message" name="message" border="1">
        <tr>
            <td>
                物品名称
            </td>
            <td>
                <input id="name" />
            </td>
        </tr>
        <tr>
            <td>
                物品数量
            </td>
            <td>
                <input id="count" />
            </td>
        </tr>
    </table>
</div>
<div class="submit_btn">
    <button id="submit" class="submit"  onclick="inputData()">提交</button>
</div>
</body>
</html>
<script>

    function inputData(){
        var name = $("#name").val();
        var count = $("#count").val();

        $.get("http://127.0.0.1/goodsmanagment?&name="+name+"&count="+count);
    }
</script>