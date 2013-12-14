<h1>${title}</h1>
<%@ include file="busca-users.jsp" %>
<table class="table table-condensed table-hover">
<thead>
	<tr>
		<th>&nbsp;</th>
		<th>#</th>
		<th>Nome</th>
		<th>E-Mail</th>
		<th>Login</th>
		<th>Privilégios</th>
		<th>Data de Cadastro</th>
		<th>Data de Modificação</th>
	</tr>
</thead>
<tbody>
<c:forEach var="user" items="${users_busca}">
	<tr>
		<td><a href="${pageContext.request.contextPath}/user.do?user=editar&id=${user.id}">Editar</a> 
		<c:if test="${user.id != user_geral.id}">
			<a href="${pageContext.request.contextPath}/user.do?user=excluir&id=${user.id}">Excluir</a></td>
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