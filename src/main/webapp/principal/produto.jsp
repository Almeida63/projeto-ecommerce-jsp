<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%@ page session="true" %>

<%@ page import="models.Usuario"  %>

<%
   Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");

   String tipoConta = (String) session.getAttribute("tipoconta"); 
  // Integer quantidadeCarrinho = (Integer) session.getAttribute("quantidadeCarrinho");

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>The Shop - Produto</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<link href="assets/css/styles.css" rel="stylesheet" />

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="index.jsp">The Shop</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="index.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#!">Sobre</a></li>
					
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">Produtos</a>
						
						
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<form action="${pageContext.request.contextPath}/ServletProdutoCliente" method="get">
							<input type="hidden" name="tipoProduto">
                            <input type="hidden" name="acao" value="buscar">
							
							
							<li><a class="dropdown-item" href="#!">Todos os produtos</a></li>
							<li><hr class="dropdown-divider" /></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/ServletProdutoCliente?acao=buscar&tipoProduto=camiseta">Camisetas</a></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/ServletProdutoCliente?acao=buscar&tipoProduto=calça">Calças</a></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/ServletProdutoCliente?acao=buscar&tipoProduto=calçado" >Calçados</a></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/ServletProdutoCliente?acao=buscar&tipoProduto=acessorio">Acessorios</a></li>
							
							</form>
							
							
						</ul></li>
					
						
					<% if(usuarioLogado != null) { %>	
						<li class="nav-item"><a class="nav-link"
								href="principal/contaUsuario.jsp">Minha conta</a></li>
						
						
					<% }else{ %>
					 <li class="nav-item"><a class="nav-link"
								href="principal/cadastro.jsp">Crie a sua conta</a></li>
								
				 <% } %>
				 
				 <% if("vendedor".equals(tipoConta)) { %>
				 <li class="nav-item"><a class="nav-link" href="principal/acessoVendedor.jsp">Gerenciamento de Produtos</a>
				 
				 <% } %>				

				</ul>
                     
                    <% if("cliente".equals(tipoConta)) { %> 
					<form action="${pageContext.request.contextPath}/ServletCarrinho" method="get">
    <button class="btn btn-outline-dark" type="submit">
        <i class="bi-cart-fill me-1"></i> Meu Carrinho 
        <span class="badge bg-dark text-white ms-1 rounded-pill"></span>
    </button>
    </form>
    
    <% } %>
			</div>
		</div>
	</nav>
	
	<section class="py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="${produto.fotoProduto}" alt="..." /></div>
                    <div class="col-md-6">
                        <h1 class="display-5 fw-bolder">${produto.titulo} </h1> 
                        <div class="fs-5 mb-5">
                            <span class="text">R$ ${produto.valor}</span>
                        </div>
                        <p class="lead">${produto.descricao} </p>
                        <div class="d-flex">
                            <form action="${pageContext.request.contextPath}/ServletCarrinho" method="post">
                            <input type="hidden" name="idProduto" value="${produto.id}">
                            <input type="hidden" name="acao" value="adicionar">
                            
                           
                            
                            <% if("cliente".equals(tipoConta)) { %>
                            <button id="adicionarCarrinho" type="button" data-id="${produto.id}" class="btn btn-outline-dark flex-shrink-0">
                           <i class="bi-cart-fill me-1"></i>Adicionar ao carrinho
                           </button>
                           
                           <% } else { %>
                           
                           <div class="alert alert-warning" role="alert">
                             Crie uma <a href="principal/cadastro.jsp">conta</a>
                             ou realize o <a href="principal/login.jsp">login</a>
                             para realizar a compra deste produto!
                             </div>
                           
                           
                           <% } %>
                                                         
                            
                            
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        	 <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; The Shop 2025</p></div>
        </footer>
        
        

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    $(document).ready(function() {
        $('#adicionarCarrinho').click(function(event) {
            event.preventDefault(); // Impede o envio do formulário

            
            
            var idProduto = $(this).data('id');
            

            $.ajax({
                url: '${pageContext.request.contextPath}/ServletCarrinho',
                method: 'POST',
                data: {
                    acao: 'adicionar',
                    idProduto: idProduto
                },
                success: function(response) {
                    alert('Produto adicionado ao carrinho!');
                },
                error: function(xhr, status, error) {
                    alert('Erro ao adicionar produto ao carrinho: ' + error);
                }
            });
        });
    });
</script>


<script 
src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="js/scripts.js"></script>
</html>