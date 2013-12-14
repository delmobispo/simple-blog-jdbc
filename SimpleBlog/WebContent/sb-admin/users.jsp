<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<div class="span9">
	<c:if test="${users != null}">	
		<%@ include file="includes/user-list.jsp" %>
	</c:if>
	<c:if test="${users_busca != null}">
		<%@ include file="includes/result-busca-users.jsp" %>
	</c:if>
	<div class="clear"></div>
	<c:if test="${user_edit != null || user_add == 1}">
		<%@include file="includes/user-edit.jsp" %>
	</c:if>
</div><!-- .span9 -->
<%@ include file="includes/footer.jsp" %>