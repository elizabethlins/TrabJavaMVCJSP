<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Meu Crud MVC </title>
    <link type="text/css" href="css/default.css.css" rel="stylesheet"/>
</head>
<body>

<form method="POST" action='UserController?action=login' name="login">
    Login : <input type="text" name="login" value="<c:out value="${user.userid}" />"/> <br/>
    Password : <input type="password" name="password" value="<c:out value="${user.password}" />"/>
    <br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
