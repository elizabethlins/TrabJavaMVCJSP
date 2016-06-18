<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link type="text/css" href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <link type="text/css" href="css/default.css.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
    <title>Add new user</title>
</head>
<body>
<script>
    $(function () {
        $('input[name=dob]').datepicker();
    });
</script>

<form method="POST" action='UserController?action=adduser' name="adduser">
    <input type="hidden" name="userid" value="<c:out value="${user.userid}" />"/>
    Login : <input type="text" name="login" value="<c:out value="${user.login}" />"/> <br/>
    Password : <input type="password" name="password" value="<c:out value="${user.password}" />"/>
    <br/>
    First Name : <input type="text" name="firstName" value="<c:out value="${user.firstName}" />"/> <br/>
    Last Name : <input type="text" name="lastName" value="<c:out value="${user.lastName}" />"/> <br/>
    DOB : <input type="text" name="dob" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${user.dob}" />"/> <br/>
    Email : <input type="text" name="email" value="<c:out value="${user.email}" />"/> <br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>