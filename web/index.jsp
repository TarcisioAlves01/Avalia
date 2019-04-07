<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
  <meta charset="UTF-8" />
  <title>Formulário de Login e Registro</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
  <link rel="stylesheet" type="text/css" href="css/login.css" />
</head>
<body>
    <p></p><p></p>
  <div class="container" >     
    <div class="content">      
      <!--FORMULÁRIO DE LOGIN-->
      <div id="login">
         
        <form method="post" action="controle"> 
          <h1>Login</h1> 
           
          <p> 
            <label for="nome_login">Seu E-mail</label>
            <input id="nome_login" name="nome_login" required="required" type="text" placeholder="ex. contato@htmlecsspro.com"/>
          </p>
           
          <p> 
            <label for="email_login">Sua senha</label>
            <input id="email_login" name="email_login" required="required" type="password" placeholder="ex. senha" /> 
          </p>
          <input type="hidden" name="acao" value="AutenticaUsuario"> 
          <p> 
            <input type="submit" value="Logar" /> 
          </p>               
           
          <p class="link">
            Ainda não tem conta?
            <a href="/Avalia/controle?acao=NovoUsuario&subacao=solicitar">Cadastre-se</a>
          </p>
        </form>
      </div>    
    </div>
  </div>  
</body>
</html>