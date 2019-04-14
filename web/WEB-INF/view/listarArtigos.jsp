<!DOCTYPE html>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pt-br">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SB Admin - Tables</title>

  <!-- Custom fonts for this template-->
  <link href="boot/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

  <!-- Page level plugin CSS-->
  <link href="boot/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="boot/css/sb-admin.css" rel="stylesheet">

</head>

<body id="page-top">

  <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

    <a class="navbar-brand mr-1" href="index.html">Start Bootstrap</a>

    <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
      <i class="fas fa-bars"></i>
    </button>

    <!-- Navbar Search -->
    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
        <div class="input-group-append">
          <button class="btn btn-primary" type="button">
            <i class="fas fa-search"></i>
          </button>
        </div>
      </div>
    </form>

    <!-- Navbar -->
    <ul class="navbar-nav ml-auto ml-md-0">
      <li class="nav-item dropdown no-arrow mx-1">
        <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fas fa-bell fa-fw"></i>
          <span class="badge badge-danger">9+</span>
        </a>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="alertsDropdown">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      <li class="nav-item dropdown no-arrow mx-1">
        <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fas fa-envelope fa-fw"></i>
          <span class="badge badge-danger">7</span>
        </a>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="messagesDropdown">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      <li class="nav-item dropdown no-arrow">
        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fas fa-user-circle fa-fw"></i>
        </a>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
          <a class="dropdown-item" href="#">Settings</a>
          <a class="dropdown-item" href="#">Activity Log</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Logout</a>
        </div>
      </li>
    </ul>

  </nav>

  <div id="wrapper">

    <!-- Sidebar -->
 <ul class="sidebar navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/Avalia/controle?acao=Home&codigo_usuario=${user.codigo}"">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dashboard</span>
        </a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fas fa-fw fa-folder"></i>
          <span>Páginas</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="pagesDropdown">
          <h6 class="dropdown-header">Usuário:</h6>          
          <a class="dropdown-item" href="/Avalia/controle?acao=AlterarConta&subacao=solicitar&codigo_usuario=${user.codigo}&tipoDeusuario=${user.tipo}">Minha conta</a>
          <c:if test ="${user.tipo.equals('Avaliador')}">
            <a class="dropdown-item" href="/Avalia/controle?acao=Kanban&subacao=listar&codigo_usuario=${user.codigo}&tipo_usuario=${user.tipo}">Minha avaliações</a>
          </c:if>          
          <a class="dropdown-item" href="forgot-password.html">Logout</a>
          <div class="dropdown-divider"></div>
          <h6 class="dropdown-header">Other Pages:</h6>
          <a class="dropdown-item" href="404.html">404 Page</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/Avalia/controle?acao=Kanban&subacao=listar&codigo_usuario=${user.codigo}&tipo_usuario=${user.tipo}">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>Kanban</span></a>
      </li>
      <c:if test ="${user.tipo.equals('Moderador')}">       
          
        <li class="nav-item">
          <a class="nav-link" href="/Avalia/controle?acao=ListaUsuarios&codigo=${user.codigo}&tipo=${user.tipo}">
            <i class="fas fa-fw fa-table"></i>
            <span>Usuários</span></a>
        </li>
      </c:if>
        
      <c:if test ="${(user.tipo.equals('Moderador')) || (user.tipo.equals('Aluno'))}">       
          
        <li class="nav-item">
          <a class="nav-link" href="/Avalia/controle?acao=ListarArtigos&codigo_usuario=${user.codigo}&tipo=${user.tipo}">
            <i class="fas fa-fw fa-table"></i>
            <span>Trabalhos</span></a>
        </li>
      </c:if>
    </ul>

    <div id="content-wrapper">

      <div class="container-fluid">
        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <a href="#">Dashboard</a>
          </li>
          <li class="breadcrumb-item active">Tabela</li>
        </ol>

        <!-- DataTables Example -->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fas fa-table"></i>
            Trabalhos publicados</div>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                  <tr>
                    <th>Código</th>
                    <th>Título</th>
                    <th>Arquivo</th>
                    <th>Fase</th>
                    <th>Publicado</th>
                    <th>Deletar</th>
                    <th>Alternar</th>
                    <th>Baixar</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${ListaArtigos}" var="artigo">
                    <tr>                      
                      <td>${artigo.codigo}</td>
                      <td>${artigo.titulo}</td>
                      <td>${artigo.nomeDoarquivo}</td>
                      <td>${artigo.fase}</td>
                      <td>${artigo.dataPublic}</td>
                      
                      
                      <td>
                          <div class="text-center">
                                <c:if test ="${artigo.fase == '1' && artigo.tipo == 'Moderador'}">
                                    <a class="btn btn-primary btn-danger" href="/Avalia/controle?acao=DeletarArtigo&codigo_artigo=${artigo.codigo}&nomeArquivo=${artigo.nomeDoarquivo}&codigo_usuario=${artigo.dono}&tipo_usuario=${artigo.tipo}">-</a>
                                </c:if>
                          </div>
                      </td>
                      
                      <td>
                          <div class="text-center">
                                <c:if test ="${(artigo.fase == '1') && (artigo.tipo == 'Moderador')}">
                                  <a class="btn btn-primary btn-group" href="/Avalia/controle?acao=DisponibilizarParaAvaliacao&codigo_artigo=${artigo.codigo}&nomeArquivo=${artigo.nomeDoarquivo}&codigo_usuario=${artigo.dono}&tipo_usuario=${artigo.tipo}">▁ ▂ ▃</a>
                               </c:if>
                          </div>
                      </td>
                      
                      <td>
                          <div class="text-center">                              
                               <a class="btn btn-primary btn-dark" href="/web/arquivos/${artigo.nomeDoarquivo}" target="_blank">▼</a> 
                          </div>
                      </td>                      
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
          <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
        </div>

        <p class="small text-center text-muted my-5">
          <em>More table examples coming soon...</em>
        </p>

      </div>
      <!-- /.container-fluid -->

      <!-- Sticky Footer -->
      <footer class="sticky-footer">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright © Your Website 2019</span>
          </div>
        </div>
      </footer>

    </div>
    <!-- /.content-wrapper -->

  </div>
  <!-- /#wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="boot/vendor/jquery/jquery.min.js"></script>
  <script src="boot/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="boot/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Page level plugin JavaScript-->
  <script src="boot/vendor/datatables/jquery.dataTables.js"></script>
  <script src="boot/vendor/datatables/dataTables.bootstrap4.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="boot/js/sb-admin.min.js"></script>

  <!-- Demo scripts for this page-->
  <script src="boot/js/demo/datatables-demo.js"></script>

</body>

</html>

