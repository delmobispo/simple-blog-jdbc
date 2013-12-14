<%@ include file="includes/header.jsp" %>
<div class="span12">
	<h1><a href="${pageContext.request.contextPath}" title="<fmt:message key="site.saudacao"/>"><fmt:message key="site.saudacao"/></a></h1>
</div>
<div class="span8">
	<div class="page-header">
    	<h3>${post.nome} <small><fmt:message key="site.author" /> ${post.user.nome} <fmt:message key="site.date"/> ${post.date }</small></h3>
    	<c:if test="${post.dateModification != null}">
    		<p class="text-info"><fmt:message key="site.dateModification.post"/> ${post.dateModification }</p>
    	</c:if>
   	</div>
   	<p class="lead">${post.content}</p>
</div>
<div class="span3">
	<%@ include file="includes/cat-list.jsp" %>
</div>
<div class="clear"></div>
<div class="span8">
	<c:forEach var="coment" items="${coments}">
		<hr />
		<div class="page-header">
    		<h5>${coment.author} <small>${coment.email }</small></h5>
    		<p class="text-info"><fmt:message key="site.author" /> <fmt:formatDate value="${coment.date }" /></p>
    		<p class="lead">${coment.content}</p>
   		</div>
	</c:forEach>
	<c:if test="${status != null }">
		<h4><fmt:message key="site.comentario.status" /></h4>
	</c:if>
	<h4><fmt:message key="site.comentario.title"/></h4>
	<form action="coment.do?coment=adicionar" method="post"/>
		<input type="hidden" name="post_id" value="${post.id}"/>
		<input type="hidden" name="ip" value="${pageContext.request.remoteHost}">
		<label for="nome"><fmt:message key="label.nome"/></label>
		<input type="text" name="nome" id="nome" required/>
		<br />
		<label for="email"><fmt:message key="label.email"/></label>
		<input type="email" name="email" id="email" required/>
		<br />
		<label for="comentario"><fmt:message key="label.comentario" /></label>
		<textarea rows="4" cols="3" name="comentario" id="comentario" required></textarea>
		<br />
		<button class="btn"><fmt:message key="label.btn.enviar"/></button>
	</form>
</div>
<%@ include file="includes/footer.jsp" %>