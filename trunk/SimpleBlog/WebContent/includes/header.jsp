<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/main.js"></script>
<title><fmt:message key="${title}"/></title>
</head>
<body>
<div class="container">