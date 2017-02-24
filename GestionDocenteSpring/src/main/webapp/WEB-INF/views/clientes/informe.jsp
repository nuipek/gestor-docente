
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
<main>
<h1>Informe del cliente</h1>

	<c:choose>
		<c:when test="${not empty cliente}">
		<div>
			<p>${cliente.nombre} ${cliente.email} </p>
			<p></p>
	
		</div>
		<c:if test="${cliente.cursos.size() > 0}" >
			<table>
				<thead>
					<tr>
					   <th>Identificador</th>
						<th>Nombre</th>
						<th>Horas</th>	
						<th>Precio</th>		
					</tr>
				</thead>
				<c:forEach var="curso" items="${cliente.cursos}">
				<tbody>
					<tr>
						<td>${curso.value.identificador}</td>
						<td>${curso.value.nombre}</td>
						<td>${curso.value.nHoras}</td>
						<td>${curso.value.precio}</td>
					</tr>
				</tbody>
				
				</c:forEach>
				</table>
			</c:if>
		<c:if test="${cliente.cursos.size() == 1}" >
			No hay cursos asociados a ese cliente
		</c:if>
		</c:when>
		<c:otherwise>
		No se han encontrador datos del cliente
		</c:otherwise>
</c:choose>
</main>

</body>
</html>