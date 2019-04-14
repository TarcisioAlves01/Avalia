<%-- 
    Document   : nota_artigo
    Created on : 08/04/2019, 12:11:30
    Author     : Tarcisio Alves
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@page import="br.edu.ifce.Avalia.modelo.Artigo"%>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Informe uma nota para o trabalho de título ${artigo.titulo}</h1>
        <form action="controle" method="post"enctype="multipart/form-data">
            <input type="hidden" name="acao" value="Kanban" />
            <input type="hidden" name="subacao" value="nota" />
            <input type="hidden" name="codigo_artigo" value="${artigo.codigo}" />
            <input type="hidden" name="codigo_usuario" value="${artigo.solicitante}" />
            Nota do trabalho: <input type="text" name="nota" />
            <input type="submit" value="Enviar nota" />
        </form>
    </body>
</html>
