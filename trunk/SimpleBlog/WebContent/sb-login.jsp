<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<title><fmt:message key="${title}"/></title>
</head>
<body>
<div class="container">
<c:if test="${erroCatch != null}">
	<div class="alert alert-error">
		${erroCatch}
    </div>
</c:if><c:if test="${erro_session != null}">
	<div class="alert alert-info">
		<fmt:message key="${erro_session}"/>
  	</div>
</c:if>

	<div class="row">
		<div class="offset2 span5">
			<h1><fmt:message key="backend.painel"/></h1>
			<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/login.do">
				<c:if test="${erro_login != null}">
				<div class="alert alert-block">
    				<button type="button" class="close" data-dismiss="alert">&times;</button>
    				<h4><fmt:message key="Error!"/></h4>
   						<fmt:message key="${erro_login}"/>
    			</div>
    			</c:if>
  				<div class="control-group">
    				<label class="control-label" for="inputEmail"><fmt:message key="label.login"/></label>
    				<div class="controls">
    					<input type="text" id="inputEmail" placeholder="Email" name="user_login">
    				</div>
    			</div>
    			<div class="control-group">
    				<label class="control-label" for="inputPassword"><fmt:message key="label.senha"/></label>
    				<div class="controls">
    					<input type="password" id="inputPassword" placeholder="Password" name="user_pass">
    				</div>
    			</div>
    			<div class="control-group">
    				<label class="control-label" for="idioma"><fmt:message key="label.idioma"/></label>
    				<div class="controls">
    					<select name="idioma" id="idioma">
    						<option value="pt"><fmt:message key="label.pt"/></option>
    						<option value="en"><fmt:message key="label.en"/></option>
    					</select>
    				</div>
    			</div>
    			<div class="control-group">
    				<div class="controls">
    					<button type="submit" class="btn"><fmt:message key="label.login"/></button>
    				</div>
    			</div>
    		</form>
    	</div><!-- .offset2 span4 -->
    </div><!-- .row -->
</div><!-- .row -->
</div><!-- .container -->
</body>
</html>