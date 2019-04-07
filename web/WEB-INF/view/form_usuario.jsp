<%-- 
    Document   : form_usuario
    Created on : 07/04/2019, 07:36:17
    Author     : Tarcisio Alves
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="br.edu.ifce.Avalia.modelo.Usuario" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            h3 {
                color: #FF0000;
            }
        </style>
    </head>
    <body>
        <h1>Cadastramento de usuário</h1>
        <form action="controle" method="post">
            <input type="hidden" name="acao" value="NovoUsuario" />
            <input type="hidden" name="subacao" value="novo" />
            <p>
            Número da Inscrição: <input type="text" name="inscricao" value="${user.inscricao}"/>
            </p><p>
            Nome: <input type="text" name="nome" value="${user.nome}" /><br />
            </p><p>
            E-mail:<input type="text" name="email" value="${user.email}"/><br />
            </p><p>
            Senha: <input type="password" name="senha1"/><br />
            </p><p>
            Confirma senha: <input type="password" name="senha2"/><br />
            </p><p>
            <input type="submit" value="Cadastrar"/><br />
            </p>
        </form>
        <c:if test ="${user.alerta != '' }">
            <h3>${user.alerta}<h1/>
        </c:if>
    </body>
</html>
