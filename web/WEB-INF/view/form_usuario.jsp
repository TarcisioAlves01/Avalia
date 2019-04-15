<%-- 
    Document   : form_usuario
    Created on : 07/04/2019, 07:36:17
    Author     : Tarcisio Alves
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="br.edu.ifce.Avalia.modelo.Usuario" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Página de cadastros</title>

  <!-- Custom fonts for this template-->
  <link href="boot/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template-->
  <link href="boot/css/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">

  <div class="container">
    <div class="card card-register mx-auto mt-5">
      <div class="card-header">Cadastro de Usuário</div>
      <div class="card-body">
        <form action="controle" method="post">
            <input type="hidden" name="acao" value="NovoUsuario" />
            <input type="hidden" name="subacao" value="novo" />
            
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <div class="form-label-group">
                  <input type="text" name="inscricao" id="firstName" class="form-control" placeholder="Número de Inscrição" required="required" autofocus="autofocus" value="${user.inscricao}">
                  <label for="firstName">Número de Inscrição:</label>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-label-group">
                  <input type="text" name="nome" value="${user.nome}" id="lastName" class="form-control" placeholder="Last name" required="required">
                  <label for="lastName">Nome completo:</label>
                </div>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group">
              <input type="email" name="email" value="${user.email}" id="inputEmail" class="form-control" placeholder="Email address" required="required">
              <label for="inputEmail">Email:</label>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <div class="form-label-group">
                  <input type="password" name="senha1" id="inputPassword" class="form-control" placeholder="Password" required="required">
                  <label for="inputPassword">Senha:</label>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-label-group">
                  <input type="password" name="senha2" id="confirmPassword" class="form-control" placeholder="Confirm password" required="required">
                  <label for="confirmPassword">Confirme sua senha:</label>
                </div>
              </div>
            </div>
          </div>
          <input type="submit" value="Cadastrar" class="btn btn-primary btn-block">
        </form>        
              <c:if test ="${user.alerta != '' }">
                    <h3 class="text-center mt-2">${user.alerta}<h1/>
              </c:if>
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
