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
<h1>SUMA</h1>
<c:if test= "${Suma == null}"> 
<form method="post" action="suma2.htm" >
 <p>Numero 1: <input name="n1" type="text"></p>
 <p>Numero 2: <input name="n2" type="text"></p>
 <p><input value="Procesar" type="submit"></p>
 </form>
</c:if>

<c:if test="${Suma != null }">

<p>Número 1: ${n1}<p/>
<p>Número 2: ${n2}<p/>
<p>Suma: ${Suma}<p/>
<a href="suma.htm">Retornar</a>
</c:if>

</body>
</html>