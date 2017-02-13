<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>    
<spring:message var="seccion" code="alumnos.titulo" />
<c:set scope="request" var="seccion" value="${seccion}"/>
<jsp:include page="../includes/header.jsp" />
<main>
<a href="alumnos/addAlumno">Crear Alumno</a>
<table>
	<thead>
		<tr>
			<th>Nombre</th>
			<th>Apellidos</th>
			<th>Editar</th>
		</tr>
	</thead>
<tbody>		
<c:choose>
	<c:when test="${not empty listadoAlumnos}">	<!-- cuando la lista tiene datos -->
		<c:forEach var="alumno" items="${listadoAlumnos}">
		<tr>
		 	<td>${alumno.nombre}</td> 
		 	<td>${alumno.apellidos}</td> 
		 	<td>
		 		<a href="alumnos/${alumno.codigo}">Editar</a>
		 		<a href="alumnos/deleteAlumno/${alumno.codigo}">Borrar</a>
		 	</td>
		 </tr>
		</c:forEach>
	</c:when>
	<c:otherwise> <!-- Cuando la lista no tiene datos -->
		<tr>
	 		<td colspan="3">No se han encontrado alumnos en la Base de Datos</td>
	 	</tr>	
	</c:otherwise>
</c:choose>
</tbody>
</table>
</main>
<footer>
</footer>
</body>
</html>