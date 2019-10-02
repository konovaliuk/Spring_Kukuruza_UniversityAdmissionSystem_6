<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"
         import="ua.company.spring.SpringUniversityAdmissionSystem.util.UserTypes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages/msg" var="lang"/>
<html>
<head>
    <%@ include file="/WEB-INF/components/bootstrap.jsp" %>
    <title><fmt:message key="errorPage.title" bundle="${lang}"/></title>
</head>
<body class="d-flex flex-column h-100">
<div class="container">
    <div class="jumbotron bg-dark mt-4 pb-4 text-center">
        <h3 class="display-4 text-white">
            <fmt:message key="errorPage.message" bundle="${lang}"/>
        </h3>
        <p class="lead text-white-50">
            <fmt:message key="errorPage.advice" bundle="${lang}"/>
        </p>
        <hr class="my-4">
        <c:if test="${(sessionScope.user eq null) or (sessionScope.user.userType.id eq UserTypes.STUDENT.id)}">
            <a class="btn btn-light font-weight-bold" href="<c:url value="/index"/>" role="button">
                <fmt:message key="errorPage.button" bundle="${lang}"/>
            </a>
        </c:if>
        <c:if test="${sessionScope.user.userType.id eq UserTypes.ADMIN.id}">
            <a class="btn btn-light font-weight-bold" href="<c:url value="/admin/adminPage"/>"
               role="button">
                <fmt:message key="errorPage.button" bundle="${lang}"/>
            </a>
        </c:if>
    </div>
    <article class="invisible">Message: ${requestScope.message}</article>
</div>
</body>
</html>
