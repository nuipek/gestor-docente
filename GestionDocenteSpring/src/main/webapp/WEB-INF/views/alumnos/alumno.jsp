<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion Docente - Alumno</title>
</head>
<body>
	<form:form action="save" method="post" commandName="alumno">
		<c:if test"${!emtpy alumno}">
			<form:hidden path="codigo"/>
		</c:if>
		<div>
			<form:label path="nombre">Nombre</form:label>
			<form:input path="nombre" cssErrorClass="" cssClass=""/>
			<form:errors path="nombre" cssClass="" />
		</div>
		<div>
			<form:label path="apellidos">Apellidos:</form:label>
			<form:input path="apellidos"  cssErrorClass="" cssClass=""/>
			<form:errors path="apellidos" />
		</div>
		<div>
			<form:label path="dni">DNI:</form:label>
			<form:input path="dni"  cssErrorClass="" cssClass=""/>
			<form:errors path="dni" />
		</div>
		<div>
			<form:label path="email">Email:</form:label>
			<form:input path="email"/>
			<form:errors path="email"/>
		</div>
		<div>
			<form:label path="telefono">Teléfono:</form:label>
			<form:input path="telefono"/>
			<form:errors path="telefono"/>
		</div>
		<div>
			<form:label path="fNacimiento">Fecha Nacimiento</form:label>
			<form:input path="fNacimiento" placeholder="dd/mm/yyyy" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" cssErrorClass="" cssClass=""/>
			<form:errors path="fNacimiento"/>
		</div>

		<div>
			<form:label path="direccion">Dirección:</form:label>
			<form:input path="direccion"/>
			<form:errors path="direccion"/>
		</div>
		<div>
			<form:label path="codigoPostal">Código Postal:</form:label>
			<form:input path="codigoPostal"/>
			<form:errors path="codigoPostal"/>
		</div>
		<div>
			<form:label path="poblacion">Poblacion:</form:label>
			<form:input path="poblacion"/>
			<form:errors path="poblacion"/>
		</div>
		<div>
			<form:label path="nHermanos">Número Hermanos:</form:label>
			<form:input path="nHermanos"  pattern="[0-9]*"/>
			<form:errors path="nHermanos"/>
		</div>
		<div>
		   <c:set var="men" value="Crear"/>
		   <c:if test="${alumno.codigo > 0}">
		   	<c:set var="men" value="Editar"/>
		   </c:if>
		   <input type="submit" value="${men}">
			<form:button></form:button>
		</div>
	</form:form>
	
</body>
</html>