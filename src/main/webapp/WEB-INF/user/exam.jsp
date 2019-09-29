<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="myTag" uri="training.company.ua" %>
<fmt:setBundle basename="messages/msg" var="lang"/>
<html>
<head>
    <%@ include file="/WEB-INF/components/bootstrap.jsp" %>
    <title><fmt:message key="exam.title" bundle="${lang}"/></title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/WEB-INF/user/navbar.jsp" %>
<div class="container">
    <h2 class="mt-2 text-center">
        <fmt:message key="exam.currentDate" bundle="${lang}"/>:
        <myTag:currentDate/>
    </h2>
    <c:if test="${not empty requestScope.userExams}">
        <h2 class="mt-2">
            <fmt:message key="exam.myExams" bundle="${lang}"/>
        </h2>
        <form action="<c:url value="/examCancelRegistration"/>" method="post">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col"><fmt:message key="exam.subject" bundle="${lang}"/></th>
                    <th scope="col"><fmt:message key="exam.date" bundle="${lang}"/></th>
                    <th scope="col"><fmt:message key="exam.address" bundle="${lang}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="exam" items="${requestScope.userExams}">
                    <tr>
                        <th scope="row"><input type="checkbox" value="${exam.id}" name="exam_id"></th>
                        <td>
                                ${exam.subject.name}
                        </td>
                        <td>
                            <fmt:formatDate value="${exam.date}" pattern="dd-MM-yyyy HH:mm"/>
                        </td>
                        <td>
                                ${exam.address}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <button type="submit" class="btn btn-danger btn-sm float-right">
                <fmt:message key="exam.cancelRegistration" bundle="${lang}"/>
            </button>
            <br>
        </form>
    </c:if>
    <c:if test="${not empty requestScope.availableExams}">
        <h2 class="mt-2">
            <fmt:message key="exam.availableExams" bundle="${lang}"/>
        </h2>
        <form class="mb-4" action="<c:url value="/examRegistration"/>" method="post">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col"><fmt:message key="exam.subject" bundle="${lang}"/></th>
                    <th scope="col"><fmt:message key="exam.date" bundle="${lang}"/></th>
                    <th scope="col"><fmt:message key="exam.address" bundle="${lang}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="exam" items="${requestScope.availableExams}">
                    <tr>
                        <th scope="row"><input type="checkbox" value="${exam.id}" name="exam_id"></th>
                        <td>
                                ${exam.subject.name}
                        </td>
                        <td>
                            <fmt:formatDate value="${exam.date}" pattern="dd-MM-yyyy HH:mm"/>
                        </td>
                        <td>
                                ${exam.address}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <button type="submit" class="btn btn-success btn-sm float-right">
                <fmt:message key="exam.register" bundle="${lang}"/>
            </button>
            <br>
        </form>
    </c:if>
</div>
<c:if test="${empty requestScope.userExams and empty requestScope.availableExams}">
    <div class="container h-100">
        <div class="row align-items-center h-100">
            <div class="col-6 mx-auto">
                <div class="jumbotron text-center">
                    <h1>
                        <fmt:message key="exam.noExams" bundle="${lang}"/> ;)
                    </h1>
                </div>
            </div>
        </div>
    </div>
</c:if>
<%@ include file="/WEB-INF/components/footer.jsp" %>
</body>
</html>
