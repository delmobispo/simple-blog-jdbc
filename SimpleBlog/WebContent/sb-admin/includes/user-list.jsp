<h1>${title}</h1>
<%@ include file="busca-users.jsp" %>
<table class="table table-condensed table-hover">
<thead>
	<tr>
		<th>&nbsp;</th>
		<th><fmt:message key="table.label.numero"/></th>
		<th><fmt:message key="label.user.nome"/></th>
		<th><fmt:message key="label.email"/></th>
		<th><fmt:message key="label.login"/></th>
		<th><fmt:message key="label.privilegios"/></th>
		<th><fmt:message key="label.data.cadastro"/></th>
		<th><fmt:message key="label.data.modificacao"/></th>
	</tr>
</thead>
<tbody>
<c:forEach var="user" items="${users}">
	<tr>
		<td><a href="${pageContext.request.contextPath}/user.do?user=editar&id=${user.id}"><fmt:message key="label.editar"/></a> 
		<c:if test="${user.id != user_geral.id}">
			<a href="${pageContext.request.contextPath}/user.do?user=excluir&id=${user.id}"><fmt:message key="label.excluir"/></a></td>
		</c:if>
		<td>${user.id}</td>
		<td>${user.nome}</td>
		<td>${user.email}</td>
		<td>${user.login}</td>
		<td>${user.role.nome}</td>
		<td><fmt:formatDate value="${user.date}" /></td>
		<td><fmt:formatDate value="${user.dateModification}" /></td>
	</tr>
</c:forEach>
</tbody>
</table>