<h1>${title}</h1>
<form action="${pageContext.request.contextPath}/cat.do?cat=salvar&cat_id=${cat_edit.id}" method="post">
	<c:if test="${cat_edit.id != null }">
		<p>
			<label for="cat_id"><fmt:message key="table.label.numero"/></label>
		<input class="input-mini" id="disabledInput" type="text" value="${cat_edit.id}" disabled />
		</p>
	</c:if>
	<p>
		<label for="cat_nome"><fmt:message key="label.nome"/></label>
		<input class="input-xlarge" id="cat_nome" type="text" value="${cat_edit.nome}" name="cat_nome" required/>
	</p>
	<p>
		<label for="cat_describe"><fmt:message key="label.descricao"/></label>
		<textarea rows="3" id="cat_describe" type="text" name="cat_describe">${cat_edit.describe}</textarea>
	</p>
	<p>
		<label for="cat_parent"><fmt:message key="label.categoria"/></label>
		<select name="cat_parent" id="cat_parent">
			<option value="1"><fmt:message key="label.categoria"/></option>
			<c:forEach var="cat" items="${cats_op}">
				<option value="${cat.id}">${cat.nome}</option>
			</c:forEach>
		</select>
		
	</p>
	<p class="form-actions">
    	<button type="submit" class="btn btn-primary"><fmt:message key="label.salvar"/></button>
    	<c:if test="${user_geral.role.id == 1}">
    		<a href="${pageContext.request.contextPath}/cat.do?cat=listar" type="button" class="btn"><fmt:message key="label.cancelar"/></a>
    	</c:if>
    	<c:if test="${user_geral.role.id == 2}">
    		<a href="${pageContext.request.contextPath}/sb-admin/index.jsp" type="button" class="btn"><fmt:message key="label.cancelar"/></a>
    	</c:if>
    </p>
</form>