<h1><fmt:message key="${title}"/></h1>
<%@ include file="busca-cats.jsp" %>
<table class="table table-condensed table-hover">
<thead>
	<tr>
		<th>&nbsp;</th>
		<th><fmt:message key="table.label.numero"/></th>
		<th><fmt:message key="table.label.categoria.nome"/></th>
		<th><fmt:message key="table.label.categoria.descricao"/></th>
		<th><fmt:message key="table.label.categoria.parent"/></th>
	</tr>
</thead>
<tbody>
<c:forEach var="cat" items="${cats_busca}">
	<tr>
		<td><a href="${pageContext.request.contextPath}/cat.do?cat=editar&id=${cat.id}"><fmt:message key="label.editar"/></a> 
		<c:if test="${cat.id != 2}">
			<a href="${pageContext.request.contextPath}/cat.do?cat=excluir&id=${cat.id}"><fmt:message key="label.excluir"/></a></td>
		</c:if>
		<td>${cat.id}</td>
		<td>${cat.nome}</td>
		<td>${cat.describe}</td>
		<td>${cat.parent.nome}</td>
	</tr>
</c:forEach>
</tbody>
</table>