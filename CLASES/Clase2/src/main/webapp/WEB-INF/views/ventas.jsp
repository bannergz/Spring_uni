<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>VENTAS</h1>
<c:if test= "${total == null}"> 
<form method="post" action="ventas.htm" >
 <p>Precio: <input name="precio" type="text"></p>
 <p>Cantidad: <input name="cantidad" type="text"></p>
 <p><input value="Procesar" type="submit"></p>
 </form>
</c:if>

<c:if test=" ${total != null}">
<p>Precio: ${precio}</p>
<p>Cantidad: ${cantidad}</p>
<p>Importe: ${importe}</p>
<p>Impuesto: ${impuesto}</p>
<p>Total: ${total}</p>
<a href="ventas.htm">Retornar</a>
</c:if>




</body>
</html>