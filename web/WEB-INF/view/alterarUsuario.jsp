<%-- 
    Document   : form_AuterausuarioComum
    Created on : 07/04/2019, 10:46:16
    Author     : tarcisio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h1>Alterar conta do usuário ${user.nome}</h1>
        ${user.solicitante} <br/>
        <form action="controle" method="post">
            <input type="hidden" name="acao" value="AlterarUsuario" />
            <input type="hidden" name="subacao" value="alterar" />
            <input type="hidden" name="codigo_solitante" value="${user.solicitante}" />
            <input type="hidden" name="codigo_usuario" value="${user.codigo}" />
            <p>
            Número da Inscrição: <input type="text" name="inscricao" value="${user.inscricao}"/>
            </p><p>
            Nome: <input type="text" name="nome" value="${user.nome}" /><br />
             
            </p><p>
                Tipo de Usuário:
                <select name="tipo">                     
                    <option value="Aluno" >Aluno</option>
                    <option value="Avaliador">Avaliador</option>
                    <option value="Moderador" >Moderador</option>
                </select>
            </p><p>
            E-mail:<input type="text" name="email" value="${user.email}"/><br />
            </p><p>
            Senha: <input type="password" name="senha1" value="${user.senha}"/><br />
            </p><p>
            Confirma senha: <input type="password" name="senha2" value="${user.senha2}"/><br />
            </p><p>
            <input type="submit" value="Alterar cadastrar"/><br />
            </p>
        </form>
        <c:if test ="${user.alerta != '' }">
            <h3>${user.alerta}<h1/>
        </c:if>
    </body>
</html>
