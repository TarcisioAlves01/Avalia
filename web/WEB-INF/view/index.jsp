<%-- 
    Document   : index
    Created on : 03/04/2019, 18:30:27
    Author     : Tarcisio Alves
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="br.edu.ifce.Avalia.modelo.Artigo"%>
<%@page import="br.edu.ifce.Avalia.modelo.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>


<head>
</head>
<h1>Olá senhor(a) ${user.nome }, escolha uma função </h1>
<a href="/Avalia/controle?acao=Home&codigo_usuario=${user.codigo}">Home</a> | 

<c:if test ="${user.tipo.equals('Moderador')}">
<a href="/Avalia/controle?acao=ListaUsuarios&codigo=${user.codigo}&tipo=${user.tipo}">Lista Contas</a> |
</c:if>
<a href="/Avalia/controle?acao=SolicitarNovoArtigo&codigo_usuario=${user.codigo}">Enviar novo artigo</a> | 
<a href="/Avalia/controle?acao=ListarArtigos&codigo_usuario=${user.codigo}&tipo=${user.tipo}">Artigos publicados</a> | 
<a href="/Avalia/controle?acao=AlterarConta&subacao=solicitar&codigo_usuario=${user.codigo}&tipoDeusuario=${user.tipo}">Minha conta</a>  










