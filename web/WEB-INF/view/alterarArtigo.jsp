<%-- 
    Document   : alterarArtigo
    Created on : 05/04/2019, 12:11:06
    Author     : Tarcisio Alves
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="br.edu.ifce.Avalia.modelo.Artigo" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Modifique seu artigo</h1>
        <form method="post" action="controle" enctype="multipart/form-data">
            <input type="hidden" name="acao" value="AlterarArtigo" />
            <input type="hidden" name="subacao" value="update" />
            <input type="hidden" name="codigo_usuario" value="${artigo.solicitante}" />
            <input type="hidden" name="tipo_usuario" value="${artigo.tipo}" />
            <input type="hidden" name="codigo_artigo" value="${artigo.codigo}" /> <br />
            Título:  <input type="text" name="titulo" value="${artigo.titulo}" /> <br />
            Trabalho: <input type='file' name="arquivo_artigo" accept=".pdf" /> <br />
            <input type="submit" value="Alterar trabalho" />
        
        </form>
    </body>
</html>
