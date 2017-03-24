<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message var="nombreApp" scope="request" code="aplicacion.nombre" />   
<!DOCTYPE html>
<html>
<head>

<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous">
 </script>
  
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!--  NUESTRO LINK A LAS HOJAS DE ESTILOS PROPIAS -->
<spring:url  var="misEstilos" value="/resources/css/styles.css"/>
<link rel="stylesheet" href="${misEstilos}">


 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
 			integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" 
 			crossorigin="anonymous">
 </script>
 
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<![endif]-->

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>${nombreApp} - ${seccion} </title>
</head>
<body>
<header class="container-fluid">
	<h1 class="row"><span class="text-uppercase text-center">${nombreApp} - ${seccion}</span></h1>
	<nav>
		<ul>
			<li><a href="<c:url value='/alumnos'/>"><spring:message code="menu.alumnos" text="G. Alumnos" /></a>	</li>
			<li><a href="<c:url value='/profesores'/>"><spring:message code="menu.profesores" text="G. Profesores" /></a></li>
			<li><a href="<c:url value='/clientes'/>"><spring:message code="menu.clientes" text="G. Clientes" /></a></li>
			<li><a href="<c:url value='/cursos'/>"><spring:message code="menu.cursos" text="G. Cursos" /></a></li>
		</ul>
	</nav>
	<div class="btn-group">
		<button class="btn btn-default btn-lg dropdown-toggle" data-toggle="dropdown">Idiomas<span class="caret"></span>
		</button>
		<ul class="dropdown-menu" >
			<li>
				<a class="btn btn-default" href="?locale=es">				
					<spring:message code="idioma.castellano"  text="Castellano"/>       
				</a>
			</li>
			<li>
				<a class="btn btn-default"  href="?locale=en">
					<spring:message code="idioma.ingles" text="Ingles" />       
				</a>
			</li>
			<li>
				<a class="btn btn-default" href="?locale=eu">				
					<spring:message code="idioma.euskera"  text="Euskera"/>       
				</a>
			</li>
			
		</ul>
	</div>
	
</header>