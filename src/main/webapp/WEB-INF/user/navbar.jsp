<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/index"/>">
                <i class="fa fa-home" aria-hidden="true"></i>
                <fmt:message key="navbar.home" bundle="${lang}"/>
            </a>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                <i class="fa fa-language" aria-hidden="true"></i>
                <fmt:message key="navbar.language" bundle="${lang}"/>
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="<c:url value="/localization?lang=en-US"/>">
                    <fmt:message key="navbar.en_US" bundle="${lang}"/>
                </a>
                <a class="dropdown-item" href="<c:url value="/localization?lang=uk-UA"/>">
                    <fmt:message key="navbar.uk_UA" bundle="${lang}"/>
                </a>
            </div>
        </li>
    </ul>
    <c:if test="${sessionScope.user eq null}">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a href="<c:url value="/registration"/>"
                   class="nav-link my-2 btn btn-sm btn-success font-weight-bold">
                    <fmt:message key="navbar.registration" bundle="${lang}"/>
                    <i class="fa fa-user-plus" aria-hidden="true"></i>
                </a>
            </li>
            <li class="nav-item">
                <a href="<c:url value="/signIn"/>"
                   class="nav-link my-2 btn btn-sm btn-success ml-3 font-weight-bold">
                    <fmt:message key="navbar.signIn" bundle="${lang}"/>
                    <i class="fa fa-sign-in" aria-hidden="true"></i>
                </a>
            </li>
        </ul>
    </c:if>
    <c:if test="${sessionScope.user ne null}">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item dropdown">
                <a class="nav-link my-2 btn btn-sm btn-info dropdown-toggle font-weight-bold" href="#"
                   data-toggle="dropdown">
                    <i class="fa fa-user-circle-o" aria-hidden="true"></i>
                        ${sessionScope.user.firstName}
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="<c:url value="/exam"/>">
                        <fmt:message key="navbar.exams" bundle="${lang}"/>
                    </a>
                    <a class="dropdown-item" href="<c:url value="/results"/>">
                        <fmt:message key="navbar.results" bundle="${lang}"/>
                    </a>
                    <a class="dropdown-item"
                       href="<c:url value="/universitySelection"/>">
                        <fmt:message key="navbar.universities" bundle="${lang}"/>
                    </a>
                    <a class="dropdown-item" href="<c:url value="/rating"/>">
                        <fmt:message key="navbar.summaryRating" bundle="${lang}"/>
                    </a>
                    <a class="dropdown-item" href="<c:url value="/checkStatus"/>">
                        <fmt:message key="navbar.checkStatus" bundle="${lang}"/>
                    </a>
                </div>
            </li>
            <li class="nav-item">
                <a href="<c:url value="/signOut"/>"
                   class="nav-link my-2 btn btn-sm btn-success ml-3 font-weight-bold">
                    <fmt:message key="navbar.signOut" bundle="${lang}"/>
                    <i class="fa fa-sign-out" aria-hidden="true"></i>
                </a>
            </li>
        </ul>
    </c:if>
</nav>