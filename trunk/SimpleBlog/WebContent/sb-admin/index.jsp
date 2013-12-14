<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>
<di class="span9">
    <div class="hero-unit">
    <h1><fmt:message key="ola"/>, ${user_geral.nome}</h1>
    <p><fmt:message key="agradecimento"/></p>
    <p>
    <a href="${pageContext.request.contextPath}/user.do?user=editar&id=${user_geral.id}" class="btn btn-primary btn-large">
    <fmt:message key="btn.comecar"/>
    </a>
    </p>
    </div>
</di>
<%@ include file="includes/footer.jsp" %>