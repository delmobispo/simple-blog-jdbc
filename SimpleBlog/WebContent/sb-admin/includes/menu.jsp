<div class="row">
	<div class="span3">
		<ul class="nav nav-list">
			<li class="nav-header"><fmt:message key="menu.dahsbord"/></li>
			<li><a href="${pageContext.request.contextPath}/sb-admin/index.jsp"><fmt:message key="menu.gerenciar"/></a></li>
			<li><a href="${pageContext.request.contextPath}/login.do?logout=true"><fmt:message key="menu.sair"/></a></li>
    		<li class="nav-header"><fmt:message key="label.usuarios"/></li>
    		<c:if test="${user_geral.role.id == 1}">
    			<li><a href="${pageContext.request.contextPath}/user.do?user=listar"><fmt:message key="label.user.all"/></a></li>
    			<li><a class="user_add" href="${pageContext.request.contextPath}/user.do?user=adicionar"><fmt:message key="label.adicionar"/></a></li>
    		</c:if>
    		<li><a href="${pageContext.request.contextPath}/user.do?user=editar&id=${user_geral.id}"><fmt:message key="label.perfil"/></a></li>
    		<c:if test="${user_geral.role.id == 1 }">
    			<li class="nav-header">Categorias</li>
    			<li><a href="${pageContext.request.contextPath}/cat.do?cat=listar"><fmt:message key="all.cats"/></a></li>
    			<li><a href="${pageContext.request.contextPath}/cat.do?cat=adicionar"><fmt:message key="label.adicionar"/></a></li>
    		</c:if>
    		<li class="nav-header"><fmt:message key="label.post"/></li>
    		<li><a href="${pageContext.request.contextPath}/post.do?post=listar&user_id=${user_geral.id}"><fmt:message key="all.posts"/></a></li>
    		<li><a href="${pageContext.request.contextPath}/post.do?post=adicionar"><fmt:message key="label.adicionar"/></a></li>
    		<li class="nav-header"><fmt:message key="label.comentarios"/></li>
    		<li><a href="${pageContext.request.contextPath}/coment.do?coment=listar&user_id=${user_geral.id}"><fmt:message key="all.coments"/></a></li>
		</ul>
	</div><!-- .span4 -->
