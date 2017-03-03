
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<spring:message var="seccion" code="curso.titulo" text="curso" />
<c:set scope="request" var="seccion" value="${men} ${seccion}"/>
<jsp:include page="../includes/header.jsp" />
<main>
<h1>Curso</h1>

	<c:choose>
		<c:when test="${not empty curso}">
		<div>
			<p>${curso.nombre} </p>
			<p>${curso.identificador}</p>
			<p>${curso.fInicio} ${curso.fFin} </p> 
			<p>${curso.nHoras}</p>
			<p>${curso.precio}</p>
	
		</div>
			
		<c:if test="${curso.alumnos.size() > 0}" >
			<table>
				<thead>
					<tr>
					   <th>Codigo</th>
						<th>Nombre</th>	
						<th>Apellidos</th>		
					</tr>
				</thead>
			
				<c:forEach var="alumno" items="${curso.alumnos}">
				<tbody>
					<tr>
						<a href="<c:url value='/cursos/${curso.codigo}/alumnos/${alumno.codigo}'/>"/>${alumno.codigo} ${alumno.nombre} {alumno.apellidos} </a>
						<td>${alumno.codigo} <!-- <a href="<c:url value='alumnos/${alumno.codigo}'/>">Ir a</a></td>-->
						<td>${alumno.nombre}</td>
						<td>${alumno.apellidos}</td>
					</tr>
				</tbody>
				</c:forEach>
				</table>
			</c:if>	
			
		<c:if test="${curso.modulos.size() > 0}" >
			<table>
				<thead>
					<tr>
					   <th>Nombre</th>
						<th>Horas</th>	
						<th>Precio</th>		
					</tr>
				</thead>
				<c:forEach var="modulo" items="${curso.modulos}">
				<tbody>
					<tr>
					   <a href='<c:url value="/cursos/${curso.codigo}/detalles/${modulo.codigo}"/>'/>${modulo.modulo.nombre}</a>
						<td>${modulo.modulo.nombre}</td>
						<td>${modulo.modulo.nHoras}</td>
						<td>${modulo.modulo.precio}</td>
					</tr>
				</tbody>
				
				</c:forEach>
				</table>
			</c:if>
		<c:if test="${curso.modulos.size() == 1}" >
			No hay Modulos asociados a ese Curso
		</c:if>
		</c:when>
		<c:otherwise>
		No se han encontrador datos del curso
		</c:otherwise>
</c:choose>
</main>

</body>
</html>