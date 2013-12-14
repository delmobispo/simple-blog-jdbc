<form class="form-search" method="post" action="${pageContext.request.contextPath}/user.do">
    <div class="input-append">
    	<input type="text" class="span2 search-query" name="termo">
    	<input type="hidden" name="user" value="pesquisar">
    	<button type="submit" class="btn"><fmt:message key="label.pesquisar"/></button>
    </div>
</form>