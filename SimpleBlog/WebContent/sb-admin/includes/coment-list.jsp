<h1>${title}</h1>
<%@ include file="busca-cats.jsp" %>
<table class="table table-condensed table-hover">
<thead>
	<tr>
		<th>&nbsp;</th>
		<th>&nbsp;</th>
		<th>&nbsp;</th>
		<th>#</th>
		<th>Comentário</th>
		<th>E-mail</th>
		<th>IP</th>
		<th>Post</th>
		<th>Data</th>
		<th>Status</th>
	</tr>
</thead>
<tbody>
<c:forEach var="coment" items="${coments}">
	<tr>
		<td><a href="${pageContext.request.contextPath}/coment.do?coment=aprovar&id=${coment.id}">${coment.aprovado == false? "Aprovar" : "Desaprovar" }</a></td>
		<td><a href="${pageContext.request.contextPath}/coment.do?coment=editar&id=${coment.id}">Editar</a></td>
		<td><a href="${pageContext.request.contextPath}/coment.do?coment=excluir&id=${coment.id}">Excluir</a></td>
		<td>${coment.id}</td>
		<td>${coment.content}</td>
		<td>${coment.email}</td>
		<td>${coment.ip}</td>
		<td>${coment.post.nome}</td>
		<td><fmt:formatDate value="${coment.date }"/></td>
		${coment.aprovado }
		<td>${coment.aprovado == false? "Aguardando Aprovação" : "Aprovado" }</td>
	</tr>
</c:forEach>
</tbody>
</table>