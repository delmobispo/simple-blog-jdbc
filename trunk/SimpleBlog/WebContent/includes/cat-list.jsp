<h4><fmt:message key="site.cats"/></h4>
	<ul>
		<c:forEach var="cat" items="${cats}">
			<li><a href="viewer.do?page=visualizar&cat=${cat.id}" title="${cat.nome}">${cat.nome}</a></li>
		</c:forEach>
	</ul>