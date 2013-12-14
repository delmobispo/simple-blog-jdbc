<%@ include file="includes/header.jsp" %>
<c:if test="${posts == null }">
	<c:redirect url="viewer.do?page=home"/>
</c:if>
<div class="span12">
	<h1><a href="${pageContext.request.contextPath}" title="<fmt:message key="site.saudacao"/>"><fmt:message key="site.saudacao"/></a></h1>
</div>
	<div class="span8">
		<c:forEach var="post" items="${posts}">
		    <div class="page-header">
    			<h3>${post.nome} <small><fmt:message key="site.author" /> ${post.user.nome } <fmt:message key="site.date"/> ${post.date }</small></h3>
   			 </div>
			<p class="lead">${post.excerpt }</p>
			<a class="btn" href="viewer.do?page=visualizar&post=${post.id}" title="${post.nome}"><fmt:message key="site.btn"/></a>
		</c:forEach>
	</div>
	<div class="span3">
		<%@ include file="includes/cat-list.jsp" %>
	</div>
<%@ include file="includes/footer.jsp" %>
