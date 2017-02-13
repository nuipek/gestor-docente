
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<spring:message var="men" code="form.crear" text="nombre" />
<c:if test="${cliente.codigo > 0}" >
	<spring:message var="men"  code="form.editar" text="nombre" />
</c:if>   
<spring:message var="seccion" code="cliente.titulo" text="cliente" />
<c:set scope="request" var="seccion" value="${men} ${seccion}"/>
<jsp:include page="../includes/header.jsp" />

	<form:form action="save" method="post" modelAttribute="cliente">
		<c:if test="${!empty cliente}">
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
			<form:label path="identificativo">Identificativo:</form:label>
			<form:input path="identificativo"  cssErrorClass="" cssClass=""/>
			<form:errors path="identificativo" />
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
			<form:label path="direccion">Direccion</form:label>
			<form:input path="direccion" placeholder="" cssErrorClass="" cssClass=""/>
			<form:errors path="direccion"/>
		</div>

		<div>
		   <c:set var="men" value="Crear"/>
		   <c:if test="${cliente.codigo > 0}">
		   	<c:set var="men" value="Editar"/>
		   </c:if>
		   <input type="submit" value="${men}">
		</div>
	</form:form>
	
</body>
</html>