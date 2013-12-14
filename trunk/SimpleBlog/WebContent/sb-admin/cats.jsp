<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<div class="span9">
	<%--@ include file="includes/busca.jsp" --%>
	<c:if test="${cats != null}">	
		<%@ include file="includes/cat-list.jsp" %>
	</c:if>
	<c:if test="${cats_busca != null}">
		<%@ include file="includes/result-busca-cats.jsp" %>
	</c:if>
	<div class="clear"></div>
	<c:if test="${cat_edit != null || cat_add == 1}">
		<%@include file="includes/cat-edit.jsp" %>
	</c:if>
</div><!-- .span9 -->
<%@ include file="includes/footer.jsp" %>