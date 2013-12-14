<h1>${title}</h1>
<form action="${pageContext.request.contextPath}/post.do?post=salvar&post_id=${post_edit.id}" method="post">
	<c:if test="${post_edit.id != null }">
		<p>
			<label for="post_id">#</label>
			<input class="input-mini" id="disabledInput" type="text" value="${post_edit.id}" disabled />
		</p>
	</c:if>
	<p>
		<label for="post_nome">Título:</label>
		<input class="input-xlarge" id="post_nome" type="text" value="${post_edit.nome}" name="post_nome" required/>
	</p>
	<p>
		<label for="post_content">Conteúdo:</label>
		<textarea id="post_content" name="post_content">${post_edit.content}</textarea>
	</p>
	<p>
		<label for="post_excerpt">Resumo:</label>
		<textarea id="post_excerpt" name="post_excerpt">${post_edit.excerpt}</textarea>
	</p>
	<p>
		<h4>Categoria:</h4>
		<c:if test="${categorias_setadas != null}">
			<c:forEach var="cat_set" items="${categorias_setadas}">
				<label>
					<input type="checkbox" value="${cat_set.id }" name="post_cat" checked/>
					${cat_set.nome}
				</label>
			</c:forEach>
		</c:if>
		<c:forEach var="cat" items="${not_duplicate_cats}">
			<label>
				<input type="checkbox" value="${cat.id }" name="post_cat" />
				${cat.nome}
			</label>
		</c:forEach>
		<c:if test="${post_edit == null }">
			<c:forEach var="cat" items="${cats }">
				<label>
					<input type="checkbox" value="${cat.id }" name="post_cat"/>
					${cat.nome }
				</label>
			</c:forEach>
		</c:if>
	</p>
	<c:if test="${user_geral.role.id == 1 }">
		<p>
		<label for="post_user">Usuário:</label>
		<c:if test="${users == null }">
			<input class="input-xlarge" id="disabledInput" type="text" value="${user_geral.nome}" disabled/>
			<input type="hidden" name="post_user" value="${user_geral.id }"/>
		</c:if>
		<c:if test="${users != null }">
			<select name="post_user" id="post_user">
				<c:forEach var="user" items="${users}">
					<option value="${user.id }" ${user.id == post_edit.user.id ? "selected" : ""} >${user.nome }</option>
				</c:forEach>
			</select>
		</c:if>
		</p>
	</c:if>
	<c:if test="${post_edit.date != null }">
		<p>
			<label for="post_date">Data de Adição:</label>
			<input class="input-medium" id="disabledInput" type="text" name="post_date" value="<fmt:formatDate value="${post_edit.date}" />" disabled/>
		</p>
	</c:if>
	<c:if test="${post_edit.dateModification != null }">
		<p>
			<label for="post_date_modification">Data de Modificação:</label>
			<input class="input-medium" id="disabledInput" type="text" name="post_date_modification" value="<fmt:formatDate value="${post_edit.dateModification }" />" disabled/>
		</p>
	</c:if>
	<p>
		<label for="status">Status:</label>
		<select name="post_status" id="status">
			<c:forEach var="status" items="${posts_status.values}">
				<option value="${status }" ${status == post_edit.status? "selected" : ""}>${status }</option>
			</c:forEach>
		</select>
	</p>
	<p class="form-actions">
    	<button type="submit" class="btn btn-primary">Salvar</button>
    	<a href="${pageContext.request.contextPath}/sb-admin/posts.jsp" type="button" class="btn">Cancelar</a>
    </p>
</form>