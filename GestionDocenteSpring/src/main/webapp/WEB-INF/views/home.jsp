<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:message var="seccion" code="home.titulo" />
<c:set scope="request" var="seccion" value="${seccion}"/>
<jsp:include page="includes/header.jsp" />


</body>
</html>
