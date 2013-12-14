<h1>${title}</h1>
<table class="table table-condensed table-hover">
<thead>
	<tr>
		<th>&nbsp;</th>
		<th><fmt:message key="table.label.numero"/></th>
		<th><fmt:message key="label.titulo"/></th>
		<th><fmt:message key="label.resumo"/></th>
		<th><fmt:message key="label.usuario" /></th>
		<th>Data de Cadastro</th>
		<th>Data de Modificação</th>
		<th>Status</th>
		<th>Categoria</th>
	</tr>
</thead>
<tbody>
<c:forEach var="post" items="${posts}">
	<tr>
		<td><a href="${pageContext.request.contextPath}/post.do?post=editar&id=${post.id}">Editar</a> 
		<a href="${pageContext.request.contextPath}/post.do?post=excluir&id=${post.id}">Excluir</a></td>
		<td>${post.id}</td>
		<td>${post.nome}</td>
		<td>${post.excerpt}</td>
		<td>${post.user.nome}</td>
		<td><fmt:formatDate value="${post.date}" /></td>
		<td><ftm:formatDate value="${post.dateModification}" /></td>
		<td>${post.status}</td>
		<td>
			<c:forEach var="cat" items="${post.categorias }">
				${cat.nome},
			</c:forEach>
		</td>
	</tr>
</c:forEach>
</tbody>
</table>