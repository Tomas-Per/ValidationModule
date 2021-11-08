<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
    <p>Add/Update User:</p>
    <form:form method="post" modelAttribute="user">

        <form:label path="name">Name</form:label>
        <form:input path="name" type="text" required="required" />
        <form:errors path="name" />

        <form:label path="surname">Surname</form:label>
        <form:input path="surname" type="text" required="required" />
        <form:errors path="surname" />

        <form:label path="password">Password</form:label>
        <form:input path="password" type="text" required="required" />
        <form:errors path="password" />

        <form:label path="phoneNumber">Phone Number</form:label>
        <form:input path="phoneNumber" type="text" required="required" />
        <form:errors path="phoneNumber" />

        <form:label path="adress">Adress</form:label>
        <form:input path="adress" type="text" required="required" />
        <form:errors path="adress" />

        <form:label path="email">Email</form:label>
        <form:input path="email" type="text" required="required" />
        <form:errors path="email" />

        <button type="submit">OK</button>
    </form:form>
</div>