<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>

<div class="span9">
	<%--@ include file="includes/busca.jsp" --%>
	<c:if test="${coments != null}">
		<%@ include file="includes/coment-list.jsp" %>
	</c:if>
	<c:if test="${coments_busca != null}">
		<%@ include file="includes/result-busca-cats.jsp" %>
	</c:if>
	<div class="clear"></div>
	<c:if test="${coment_edit != null}">
		<%@include file="includes/cat-edit.jsp" %>
	</c:if>
</div><!-- .span9 -->
<%@ include file="includes/footer.jsp" %>