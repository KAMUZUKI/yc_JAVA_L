<%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2022/9/18
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Cookie中的内容：
    <hr>
    <%
        Cookie[] ck = request.getCookies();
        for (Cookie c:ck){
            //out.println(c.getName()+":"+c.getValue() +"<br>");
        }
    %>


</body>
</html>
