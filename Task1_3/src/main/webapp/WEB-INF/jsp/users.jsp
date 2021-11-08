<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="common/navigation.jspf"%>
<mvc:resources mapping="/resources/**" location="/resources/static/" />

<html>
<head>
    <title>View Users</title>
    <link href="/css/common.css" rel="stylesheet" type="text/css">
</head>
<body>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Phone Number</th>
        <th>Adress</th>
        <th>Email</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.phoneNumber}</td>
            <td>${user.adress}</td>
            <td>${user.email}</td>
            <td><a type="button" href="/update-user/${user.id}">UPDATE</a></td>
            <td><a type="button" href="/delete-user/${user.id}">DELETE</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
    <a href="add-user">ADD USER</a>
</div>
</body>
</html>