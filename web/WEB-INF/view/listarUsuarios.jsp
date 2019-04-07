<%-- 
    Document   : listarUsuarios
    Created on : 03/04/2019, 22:11:13
    Author     : Tarcisio Alves
--%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="br.edu.ifce.Avalia.modelo.Artigo"%>
<%@page import="br.edu.ifce.Avalia.modelo.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        Lista de Usuarios <br />
	
	<ul>
		<c:forEach items="${usuarios}" var="usuario">
			
			<li>
                            ID: ${usuario.codigo} Nome: ${usuario.nome} Inscrição: ${usuario.inscricao} Tipo: ${usuario.tipo} E-mail: ${usuario.email}                                      
                            <a href="/Avalia/controle?acao=AlterarUsuario&codigo_solitante=${usuario.solicitante}&tipo_solitante=${usuario.tipoSolicitante}&codigo_usuario=${usuario.codigo}&subacao=solicitar">Alterar</a>                          
                                      
			</li>
		</c:forEach>
	</ul>
    </body>
</html>
