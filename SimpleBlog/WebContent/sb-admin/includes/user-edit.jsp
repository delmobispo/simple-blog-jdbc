<h1><fmt:message key="${title}"/></h1>
<form action="${pageContext.request.contextPath}/user.do?user=salvar&user_id=${user_edit.id}" method="post">
	<c:if test="${user_edit.id != null}">
		<p>
			<label for="user_id"><fmt:message key="table.label.numero"/></label>
			<input class="input-mini" id="disabledInput" type="text" value="${user_edit.id}" disabled />
		</p>
	</c:if>
	<p>
		<label for="user_nome"><fmt:message key="label.user.nome"/></label>
		<input class="input-xlarge" id="user_nome" type="text" value="${user_edit.nome}" name="user_nome" required/>
	</p>
	<p>
		<label for="user_email"><fmt:message key="label.email"/></label>
		<input class="input-xlarge" id="user_email" type="email" value="${user_edit.email}" name="user_email" ${user_edit.email != null? "disabled" : ""} required/>
	</p>
	<p>
		<label for="user_perfil"><fmt:message key="label.perfil"/></label>
		<textarea rows="3" id="user_perfil" type="text" name="user_perfil">${user_edit.perfil}</textarea>
	</p>
	<p>
		<label for="user_login"><fmt:message key="label.login"/></label>
		<input class="input-medium" id="disabledInput" type="text" name="user_login" value="${user_edit.login}" ${user_edit.login != null? "disabled": "" } required/>
	</p>
	<p>
		<label for="user_pass"><fmt:message key="label.senha"/></label>
		<input class="input-medium" id="user_pass" type="text" name="user_pass"/>
		<input type="hidden" name="user_pass_bd" value="${user_geral.pass }">
	</p>
	<c:if test="${user_geral.role.id == 2 }">
	<p>
		<label for="user_login"><fmt:message key="label.privilegios"/></label>
		<input class="input-medium" id="disabledInput" type="text" name="user_login" value="${user_geral.role.nome}"} disabled/>
	</p>
	</c:if>
	<c:if test="${user_geral.role.id == 1}">
	<p>
		<label for="user_role"><fmt:message key="label.privilegios"/></label>
		<select id="user_role" name="user_role">
			<option value="1" ${user_geral.role.id == 1? "selected" : ""}><fmt:message key="label.adm.role"/></option>
			<option value="2" ${user_geral.role.id == 2? "selected" : ""}><fmt:message key="label.editor.role" /></option>
		</select>
	</p>
	</c:if>
	<c:if test="${user_edit.date != null }">
		<p>
			<label for="user_date"><fmt:message key="label.data.cadastro"/></label></th>
			<input class="input-small" id="disabledInput" type="date" value="<fmt:formatDate value="${user_edit.date}" />" name="user_date" disabled/>
		</p>
	</c:if>
	<c:if test="${user_edit.dateModification != null }">
		<p>
			<label for="user_dataModification"><fmt:message key="label.data.modificacao"/></label></th>
			<input class="input-small" id="disabledInput" type="date" value="<fmt:formatDate value="${user_edit.dateModification}" />" name="user_date_modification" disabled/>
		</p>
	</c:if>
	<p class="form-actions">
    	<button type="submit" class="btn btn-primary"><fmt:message key="label.salvar"/></button>
    	<c:if test="${user_geral.role.id == 1}">
    		<a href="${pageContext.request.contextPath}/sb-admin/users.jsp" type="button" class="btn"><fmt:message key="label.cancelar"/></a>
    	</c:if>
    	<c:if test="${user_geral.role.id == 2}">
    		<a href="${pageContext.request.contextPath}/sb-admin/index.jsp" type="button" class="btn"><fmt:message key="label.cancelar"/></a>
    	</c:if>
    </p>
</form>