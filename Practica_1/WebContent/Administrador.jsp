<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido administrador</title>
<style type="text/css">
#caja{
	
	background:#1CB1B3;
	width:750px;
	border:1px solid white;
	margin: 100px auto;
	padding: 1em;
	border-radius:6px;
}

</style>
</head>
	<body style="background-color:#72959C;">
		<div id="caja">
			<form>
				<h1>Cookie: </h1>
			</form>
			${cookie.JSESSIONID.value}
			<h1>Usuarios: </h1>
			<table>
			<tr>
			<td><b> Nombre</b></td>
			<td> </td>
			<td><b> Email</b></td>
			<td> </td>
			<td><b> Contraseña</b></td>
			<td> </td>
			<td> <b>Teléfono</b></td>
			<td> </td>
			<td> <b>Dirección</b></td>
			</tr>
	<c:forEach var="list" items="${usuario}" varStatus="estado">

		<tr>
		<td><c:out value="" /><c:out value="${list.mail }" /></td>
		<td> </td>
		<td><c:out value=""/><c:out value="${list.codigopostal}"/></td>
		<td> </td>
		<td><c:out value="" /><c:out value="${list.nombre}" /></td>
		<td> </td>
		<td><c:out value="" /><c:out value="${list.pass}" /></td>	
		<td> </td>
		<td><c:out value="" /><c:out value="${list.telefono}" /></td>

		 </tr>
  	</c:forEach>
 
  
</table>
		</div>
	</body>
</html>