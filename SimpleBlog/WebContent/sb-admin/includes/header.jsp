<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:if test="${not empty idioma}">
  <fmt:setLocale value="${idioma}" scope="session"/>
</c:if>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/tinymce/tinymce.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<title>${title}</title>
</head>
<body>
<div class="container">
	<c:if test="${erroCatch != null}">
		<div class="alert alert-error">
			${erroCatch}
    	</div>
	</c:if>
	<c:if test="${status != null}">
    	<div class="alert alert-success">
    		<fmt:message key="${status}"/>
    	</div>
	</c:if>