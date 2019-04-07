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
        
        Lista de Artigos <br />
       	
	<ul>
		<c:forEach items="${ListaArtigos}" var="artigo">
                    
			<c:if test ="${artigo.solicitante == artigo.dono || artigo.tipo == 'Moderador' }">
			<li>                            
                            
                            ID: ${artigo.codigo} Título: ${artigo.titulo} Nome do Arquivo: ${artigo.nomeDoarquivo} Fase: 
                            
                            <c:if test ="${artigo.fase == '1' }">
                                Ocioso
                            </c:if>
                            <c:if test ="${artigo.fase == '2' }">
                                Fila de avaliação
                            </c:if>
                            <c:if test ="${artigo.fase == '3' }">
                                Sendo avaliado
                            </c:if>
                            <c:if test ="${artigo.fase == '3' }">
                                Avaliado
                            </c:if>   
                            
                            Publicado em: ${artigo.dataPublic}                                      
                            <a href="/Avalia/controle?acao=AlterarArtigo&codigo_artigo=${artigo.codigo}&subacao=solicitar&codigo_usuario=${artigo.solicitante}&tipo_usuario=${artigo.tipo}">Alterar</a>
                            <c:if test ="${artigo.fase == '1' }">
                                <a href="/Avalia/controle?acao=DeletarArtigo&codigo_artigo=${artigo.codigo}&nomeArquivo=${artigo.nomeDoarquivo}&codigo_usuario=${artigo.dono}&tipo_usuario=${artigo.tipo}">Deletar</a>
                                <a href="/Avalia/controle?acao=DisponibilizarParaAvaliacao&codigo_artigo=${artigo.codigo}&nomeArquivo=${artigo.nomeDoarquivo}&codigo_usuario=${artigo.dono}&tipo_usuario=${artigo.tipo}">Disponibiliazar para avaliação</a>
                            </c:if>
                            <a href="/web/arquivos/${artigo.nomeDoarquivo}" target="_blank">Baixar</a>        
			</li>
                        </c:if>
		</c:forEach>
	</ul>
    </body>
</html>
