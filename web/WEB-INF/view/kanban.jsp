<%-- 
    Document   : kanban
    Created on : 07/04/2019, 16:32:48
    Author     : tarcisio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h1 >Lista de trabalhos acadêmicos</h1> 
        <hr>=> A serem avaliado</hr>
        
        <ul>
		<c:forEach items="${ListaArtigos}" var="artigo">
                    <c:if test ="${artigo.fase == '2' }">
			
			<li>                            
                            
                            ID: ${artigo.codigo} Título: ${artigo.titulo} Publicado em: ${artigo.dataPublic}  |   
                            <c:if test ="${artigo.tipo == 'Avaliador' }">
                                <a href="/Avalia/controle?acao=Kanban&subacao=2&codigo_artigo=${artigo.codigo}&codigo_usuario=${artigo.solicitante}">Avaliar</a> | 
                            </c:if> 
                            <a href="/web/arquivos/${artigo.nomeDoarquivo}" target="_blank">Baixar</a>        
			</li>
                  </c:if>     
		</c:forEach>
	</ul>
        
        <hr>=> Sendo avaliado</hr>
        
        <ul>
		<c:forEach items="${ListaArtigos}" var="artigo">
                    <c:if test ="${artigo.fase == '3' }">
			
			<li>                            
                            
                            ID: ${artigo.codigo} Título: ${artigo.titulo} Nome do Arquivo: ${artigo.nomeDoarquivo} Publicado em: ${artigo.dataPublic}                                      
                            
                            <a href="/web/arquivos/${artigo.nomeDoarquivo}" target="_blank">Baixar</a>        
			</li>
                  </c:if>     
		</c:forEach>
	</ul>
        
        <hr>=> Avaliados </hr>
        
        <ul>
		<c:forEach items="${ListaArtigos}" var="artigo">
                    <c:if test ="${artigo.fase == '4' }">
			
			<li>                            
                            
                            ID: ${artigo.codigo} Título: ${artigo.titulo} Nome do Arquivo: ${artigo.nomeDoarquivo} Publicado em: ${artigo.dataPublic}                                      
                            
                            <a href="/web/arquivos/${artigo.nomeDoarquivo}" target="_blank">Baixar</a>        
			</li>
                  </c:if>     
		</c:forEach>
	</ul>
    </body>
</html>
