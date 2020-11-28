<%-- 
    Document   : index
    Created on : 28 de nov de 2020, 03:11:26
    Author     : isame
--%>

<%@page import="listener.DBListener"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% if(DBListener.e!=null){ %>
            <div style="color:red"> <%= DBListener.e %> </div>
        <% } %>
        <h1>Hello World!</h1>
    </body>
</html>
