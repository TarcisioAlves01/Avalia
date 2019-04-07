<%-- 
    Document   : index
    Created on : 22/02/2019, 21:07:36
    Author     : tarcisio
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="br.edu.ifce.Avalia.modelo.Artigo"%>
<%@page import="br.edu.ifce.Avalia.modelo.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">

</head>

<body>
          <h3>Envio do trabalho acadêmico ${user.codigo}</h3>
          <br/>
          
          <form method="post" action="controle" enctype="multipart/form-data">
                    <input type="hidden" name="acao" value="NovoArtigo"> 
                    <input type="hidden" name="codigo_usuario" value="${user.codigo}"> 
                    <label for="nome"> Título do trabalho: </label>
                    <input type="text" name="titulo_artigo"/>
                         
                    <label for="nome"> Arquivo: </label>
                    <input type='file' name="arquivo_artigo" accept=".pdf">
                    
              <input type="submit" value="Enviar Atigo"/>
                  
          </form>

</body>

</html>
