<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>    
<spring:message var="seccion" code="alumnos.titulo" />
<c:set scope="request" var="seccion" value="${seccion}"/>
<jsp:include page="../includes/header.jsp" />
<main class="container-fluid">
	<section class="row">
		<header class="col-xs-12"><h2 class="text-center text-capitalize">Lista de Alumnos</h2></header>
		<a class="btn btn-info" href="<c:url value='alumnos/addAlumno'/>">Crear Alumno</a>
		<a class="btn btn-danger" href="#">Borrar Alumnos</a>
		<div class="col-xs-12">
		<!-- Si se anidan las columnas tiene que haber un row -->
			<div class="row">
				<div class="col-xs-1"><input id="selectall" type="checkbox"></div>
				<div class="col-xs-2 col-md-1">Nombre</div>
				<div class="col-xs-3 col-md-2">Apellidos</div>
				<div class="col-xs-2 col-md-2">Email</div>
				<div class="hidden-xs col-sm-1 col-md-1">Telefono</div>
				<div class="hidden-xs hidden-sm col-md-2">Direccion</div>
				<div class="col-xs-4 col-sm-3 col-md-3">Acciones</div>
			</div>
			
			<div>
			<c:choose>
				<c:when test="${not empty listadoAlumnos}">	<!-- cuando la lista tiene datos -->
					<c:forEach var="alumno" items="${listadoAlumnos}">
						<div class="row">
								<div class="col-xs-1"> <!--  col-md-1 se puede quitar ya que al ser el mismo numero no es necesario -->
									<input type="checkbox" value="${alumno.codigo}">
								</div>
								<div class="col-xs-2 col-md-1">
									${alumno.nombre}
								</div>
								<div class="col-xs-3 col-md-2">
									${alumno.apellidos}
								</div>
								<div class="col-xs-2 col-md-2">
									${alumno.email}
								</div>
								<div class="hidden-xs col-sm-1 col-md-1">
									${alumno.telefono}
								</div>
								<div class="hidden-xs hidden-sm col-md-2">
									${alumno.direccion}
								</div>
								<div class="col-xs-4 col-sm-3 col-md-3">
									<a class ="" href="<c:url value='alumnos/${alumno.codigo}'/>">Editar</a>
		 							<a class ="" href="<c:url value='alumnos/deleteAlumno/${alumno.codigo}'/>">Borrar</a>
		 							<a class ="" href="<c:url value='/alumnos/informeAlumno/${alumno.codigo}'/>">Informe</a>
								</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise> <!-- Cuando la lista NO tiene datos -->
					<div class="row"><span class="text-danger text-center">No se han encontrado alumnos en la Base de Datos</span></div>
	 			</c:otherwise>
			</c:choose>
			</div>
		</div>
		
	</section>
</main>
<footer>
</footer>
</body>
</html>