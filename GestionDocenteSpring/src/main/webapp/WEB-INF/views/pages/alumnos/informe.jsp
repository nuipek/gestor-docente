<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<spring:message var="seccion" code="alumno.titulo" text="alumno" />
<c:set scope="request" var="seccion" value="${men} ${seccion}"/>
<h1>Informe del Alumno</h1>

	<c:choose>
		<c:when test="${not empty alumno}">
		<div>
			<p>${alumno.nombre} ${alumno.apellidos} </p>
			<p></p>
	
		</div>
		<c:if test="${alumno.cursos.size() > 0}" >
			<table>
				<thead>
					<tr>
					   <th>Identificador</th>
						<th>Nombre</th>
						<th>Horas</th>	
						<th>Precio</th>		
					</tr>
				</thead>
				<c:forEach var="curso" items="${alumno.cursos}">
				<tbody>
					<tr>
						<td>${curso.identificador}</td>
						<td>${curso.nombre}</td>
						<td>${curso.nHoras}</td>
						<td>${curso.precio}</td>
					</tr>
				</tbody>
				
				</c:forEach>
				</table>
			</c:if>
		</c:when>
		<c:otherwise>
		No se han encontrador datos del alumno
		</c:otherwise>
</c:choose>
