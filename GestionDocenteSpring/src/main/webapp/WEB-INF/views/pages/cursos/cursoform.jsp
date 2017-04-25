<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<spring:message var="men" code="form.crear" text="nombre" />
<c:if test="${curso.codigo > 0}" >
	<spring:message var="men"  code="form.editar" text="nombre" />
</c:if>   
<spring:message var="seccion" code="curso.titulo" text="curso" />
<c:set scope="request" var="seccion" value="${men} ${seccion}"/>
<c:url var="cancelUrl" value="/cursos"/>
<c:url var="sendUrl" value="/cursos/save"/>
<section class="row">
	 <form:form action="save" method="post"  enctype="multipart/form-data" cssClass="form-horizontal"  modelAttribute="curso"> 
		<c:if test="${!empty curso}">
			<form:hidden path="codigo"/>
		</c:if>
		<div class = "form-group">
			<form:label cssClass ="control-label col-xs-2" path="nombre">Nombre</form:label>
			<div class="col-xs-7">
				<form:input placeholder="Introduzca su nombre" path="nombre" cssErrorClass="" cssClass="form-control"/>
			</div>
			<div class="col-xs-3">
			<form:errors path="nombre" cssClass="text-danger" />
			</div>
		</div>
		<div class = "form-group">
			<form:label  cssClass ="control-label col-xs-2" path="identificador">Identificador:</form:label>
			<div class="col-xs-7">
				<form:input cssErrorClass="" cssClass="form-control col-xs-10" path="identificador"  />
			</div>
			<div class="col-xs-3">
				<form:errors path="identificador" />
			</div>
		</div>
		<div class = "form-group">
			<form:label  cssClass ="control-label col-xs-2" path="fInicio">Fecha Inicio:</form:label>
			<div class="col-xs-7">
				<form:input cssErrorClass="" cssClass="form-control col-xs-10" path="fInicio" placeholder="dd/mm/yyyy" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" />
			</div>
			<div class="col-xs-3">
				<form:errors path="fInicio" />
			</div>
		</div>
		<div class = "form-group">
			<form:label  cssClass ="control-label col-xs-2" path="fFin">Fecha Fin:</form:label>
			<div class="col-xs-7">
				<form:input cssErrorClass="" cssClass="form-control col-xs-10"  path="fFin" placeholder="dd/mm/yyyy" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" />
			</div>
			<div class="col-xs-3">
				<form:errors path="fFin" />
			</div>
		</div>
		<div class = "form-group">
			<form:label  cssClass ="control-label col-xs-2" path="nHoras">Numero de Horas:</form:label>
			<div class="col-xs-7">
				<form:input cssErrorClass="" cssClass="form-control col-xs-10" path="nHoras" />
			</div>
			<div class="col-xs-3">
				<form:errors path="nHoras" />
			</div>
		</div>
		<!-- 
		<div class = "form-group">
			<form:label  cssClass ="control-label col-xs-2" path="temario">Temario:</form:label>
			<div class="col-xs-7">
				<form:input cssErrorClass="" cssClass="form-control col-xs-10" path="temario" />
			</div>
			<div class="col-xs-3">
				<form:errors path="temario" />
			</div>
		</div>
		 -->
		<div class = "form-group">
			<form:label  cssClass ="control-label col-xs-2" path="precio">Precio:</form:label>
			<div class="col-xs-7">
				<form:input cssErrorClass="" cssClass="form-control col-xs-10" path="precio" />
			</div>
			<div class="col-xs-3">
				<form:errors path="precio" />
			</div>
		</div>
		<div class="form-group">
			<form:label cssClass="control-label col-xs-2" path="profesor">Profesor:</form:label>
			<div class="col-xs-4">
				<form:select cssClass="form-control" path="profesor">
			   <form:option value="0" label="Elija un profesor"/>
				<form:options items="${listadoProfesores}" itemValue="codigo" itemLabel="nombreCompleto" />
			   </form:select> 
		   </div>
		</div>
		<div class="form-group">
			<form:label cssClass="control-label col-xs-2" path="cliente">Cliente:</form:label>
			<div class="col-xs-4">
				<form:select cssClass="form-control" path="cliente">
			   <form:option value="0" label="Elija un cliente"/>
				<form:options items="${listadoClientes}" itemValue="codigo" itemLabel="nombre" />
			   </form:select> 
		   </div>
		</div>
		<div class="form-group">
			<form:label cssClass="control-label col-xs-2" path="alumnos">Alumnos:</form:label>
				<div class="col-xs-4">
					<form:select multiple = "true" cssClass="form-control" path="alumnos">
			      <form:option value="0" label="Elija Alumnos"/>
					<form:options items="${listadoAlumnos}" itemValue="codigo" itemLabel="nombre" />
			      </form:select> 
		      </div>
			 </div>
		    <div class="form-group">
                    <form:label path="temario" cssClass="control-label  col-xs-2">Temario:</form:label>
                    <div class="col-xs-4">
                        <form:input path="temario" disabled="disabled" cssClass="form-control" cssErrorClass="text-danger"/>
                    </div>
                    <form:errors path="temario" cssClass="text-danger col-xs-6"></form:errors>
                     
                     <label class="btn btn-primary">
                        Examinar&hellip; <input type="file" id="fichero" name="fichero" style="display: none;">
                    </label>
                     <!--
                    <input type="file" id="fichero" name="fichero">
                    -->
     </div>  
		<div class = "form-group ">
			<div class="col-xs-offset-4 col-xs-8">
		   	<c:set var="men" value="Crear"/>
		   	<c:if test="${curso.codigo > 0}">
		   		<c:set var="men" value="Editar"/>
		   	</c:if>
		   	<input type="submit" class="btn btn-info" value="${men}">
		   	<a class="btn btn-warning" href="${cancelUrl}">Cancelar</a>
		   </div>
		</div>
		<form:hidden path="activo"/>
	</form:form>	
</section>
