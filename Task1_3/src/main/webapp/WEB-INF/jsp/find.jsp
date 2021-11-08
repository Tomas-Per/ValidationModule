<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="common/navigation.jspf"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<mvc:resources mapping="/resources/**" location="/resources/static/" />


<div class="container">
    <p>Find user:</p>
    <form:form method="post" modelAttribute="userID">

        <form:label path="id">Enter ID: </form:label>
        <form:input path="id" type="text" required="required" />
        <form:errors path="id" />

        <button type="submit">OK</button>
    </form:form>
</div>

