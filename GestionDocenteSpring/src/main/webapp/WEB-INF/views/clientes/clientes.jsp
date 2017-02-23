<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>   
<spring:message var="seccion" code="clientes.titulo"/>
<c:set scope="request" var="seccion" value="${seccion}"/>
<jsp:include page="../includes/header.jsp" />
<main>
<a href="<c:url value='/clientes/addCliente'/>">Crear Cliente</a>
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
	<c:when test="${not empty listadoClientes}">	<!-- cuando la lista tiene datos -->
		<c:forEach var="cliente" items="${listadoClientes}">
		<tr>
		 	<td>${cliente.nombre}</td> 
		 	<td>${cliente.apellidos}</td> 
		 	<td>
		 		<a href="<c:url value='/clientes/${cliente.codigo}'/>">Editar</a>
				<a href="<c:url value='/clientes/deleteCliente/${cliente.codigo}'/>">Borrar</a>
		 	</td>
		 </tr>
		</c:forEach>
	</c:when>
	<c:otherwise> <!-- Cuando la lista no tiene datos -->
		<tr>
	 		<td colspan="3">No se han encontrado clientes en la Base de Datos</td>
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