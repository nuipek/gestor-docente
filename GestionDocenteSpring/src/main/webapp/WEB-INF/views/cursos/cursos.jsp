<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<c:set scope="request" var="seccion" value="listado de cursos"/>
<jsp:include page="../includes/header.jsp" />
<main class="container-fluid">
   <section class="row">
   	<header class="col-xs-12"><h2 class="text-center text-capitalize">Listado de Cursos</h2></header>
   	<a class="btn btn-info "  href="<c:url value='cursos/addCurso'/>">Añadir Curso </a>
   	<div class="col-xs-12">
		<!-- Si se anidan las columnas tiene que haber un row -->
			<div class="row">
				<div class="col-xs-1">Codigo</div>
				<div class="col-xs-5">Nombre</div>
				<div class="col-xs-1">Fecha Inicio</div>
				<div class="col-xs-1">Fecha Fin</div>
				<div class="col-xs-4">Acciones</div>
			</div>
		</div>	
   </section>
	<section class="col-xs-12">
	
			<c:forEach var="curso" items="${listadocursos}">
				<div class="row">
					<div class="col-xs-1">${curso.codigo}</div>
					<div class="col-xs-5">${curso.nombre}</div>
					<div class="col-xs-1">${curso.fInicio}</div>
					<div class="col-xs-1">${curso.fFin}</div>
					<div class="col-xs-4">
						<a class ="btn btn-info" href="<c:url value='cursos/${curso.codigo}'/>">Editar</a>
		 				<a class ="btn btn-danger" href="<c:url value='cursos/deleteCurso/${curso.codigo}'/>">Borrar</a>
		 				<a class ="btn btn-default" href="<c:url value='/cursos/informeCursos/${curso.codigo}'/>">Informe</a>
					</div>
				</div>	
			</c:forEach>
	</section>
</main>
<footer>
</footer>
</html>
