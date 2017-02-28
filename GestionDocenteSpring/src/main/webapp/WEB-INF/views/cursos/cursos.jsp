<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<c:set scope="request" var="seccion" value="listado de cursos"/>
<jsp:include page="../includes/header.jsp" />
<main>
	<c:forEach var="curso" items="${listadoCursos}">
		<div>
			<a href="<c:url value='/cursos/${curso.codigo}'/>">${curso.nombre}</a>
			${curso.fInicio}
			${curso.fFin}
		</div>
	</c:forEach>
</main>
<footer>
</footer>
</html>
