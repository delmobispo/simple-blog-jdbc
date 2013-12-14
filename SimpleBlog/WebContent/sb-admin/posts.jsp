<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<div class="span9">
	<%--@ include file="includes/busca.jsp" --%>
	<c:if test="${posts != null}">	
		<%@ include file="includes/post-list.jsp" %>
	</c:if>
	<div class="clear"></div>
	<c:if test="${post_edit != null || post_add == 1}">
		<%@include file="includes/post-edit.jsp" %>
	</c:if>
</div><!-- .span9 -->
<%@ include file="includes/footer.jsp" %>