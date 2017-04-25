<!-- aunque  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

	<div class="btn-group">
		<button class="btn btn-default btn-lg dropdown-toggle" data-toggle="dropdown">Idiomas<span class="caret"></span>
		</button>
		<ul class="dropdown-menu" >
			<li>
				<a class="btn btn-default" href="?locale=es">				
					<spring:message code="idioma.castellano"  text="Castellano"/>       
				</a>
			</li>
			<li>
				<a class="btn btn-default"  href="?locale=en">
					<spring:message code="idioma.ingles" text="Ingles" />       
				</a>
			</li>
			<li>
				<a class="btn btn-default" href="?locale=eu">				
					<spring:message code="idioma.euskera"  text="Euskera"/>       
				</a>
			</li>
			
		</ul>
	</div>
	

  <nav class="navbar navbar-default">
      <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display RESPONSIVO -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Pincha para visualizar</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Logo de la empresa Ipartek S. Coop.</a>
        </div>
        
        
    
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navabar-right">
            <li><a href="<c:url value='/alumnos'/>"><spring:message code="menu.alumnos" text="G. Alumnos" /></a>	</li>
				<li><a href="<c:url value='/profesores'/>"><spring:message code="menu.profesores" text="G. Profesores" /></a></li>
				<li><a href="<c:url value='/clientes'/>"><spring:message code="menu.clientes" text="G. Clientes" /></a></li>
				<li><a href="<c:url value='/cursos'/>"><spring:message code="menu.cursos" text="G. Cursos" /></a></li>
            <!-- 
              <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Separated link</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
            
             <li>
			<sec:authorize access="isAnonymous()">
			    <form method="POST" action="<c:url value='/login'/>">
			        Username: <input name="userId" type="text" value="${SPRING_SECURITY_LAST_USERNAME}" /> 
			        Password: <input name="password" type="password" />
			        <!-- 
			        <input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />
			         --> 
			        <input type="submit" value="Login" />
			    </form>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
			    <a class="btn btn-default" href="<c:url value="/logout" />">Logout</a>
			</sec:authorize>
			</li>
			
          </ul>
          <!-- 
          <form class="navbar-form navbar-left">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
          </form>
           -->
           <!-- 
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Link</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Separated link</a></li>
              </ul>
            </li>
          </ul>
           -->
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
   </nav>
    <div class="container">
        <c:if test="${not empty mensaje}">
            <div class="${mensaje.type.styles}">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                
                 <strong>${mensaje.msg}</strong> 
            </div>
        </c:if>
    </div>