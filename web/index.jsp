<!DOCTYPE html>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Login - Página de autenticação</title>

  <!-- Custom fonts for this template-->
  <link href="boot/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template-->
  <link href="boot/css/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">

  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Login</div>
      <div class="card-body">
        <form method="post" action="controle">
          <input type="hidden" name="acao" value="AutenticaUsuario"> 
          <div class="form-group">
            <div class="form-label-group">
              <input type="email" name="nome_login" id="inputEmail" class="form-control" placeholder="Email address" required="required" autofocus="autofocus">
              <label for="inputEmail">Email:</label>
            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group">
              <input type="password" name="email_login" id="inputPassword" class="form-control" placeholder="Password" required="required">
              <label for="inputPassword">Senha:</label>
            </div>
          </div>
          <div class="form-group">
            <div class="checkbox">
              <label>
                  
                <input type="checkbox" value="remember-me">
                Lembrar senha
              </label>
            </div>
          </div>
          <input type="submit" value="Logar" class="btn btn-primary btn-block"/> 
        </form>
        <div class="text-center">
          <a class="d-block small mt-3" href="/Avalia/controle?acao=NovoUsuario&subacao=solicitar">Criar uma conta</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="boot/vendor/jquery/jquery.min.js"></script>
  <script src="boot/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="boot/vendor/jquery-easing/jquery.easing.min.js"></script>

</body>

</html>
