<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	
	<% 
    // Redireciona para o servlet se a lista de produtos não estiver preenchida
    if (request.getAttribute("produtos") == null) {
        response.sendRedirect("ServletProdutoCliente");
        return;
    }
%>

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
<title>The Shop - HomePage</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<link href="assets/css/styles.css" rel="stylesheet" />

<style>
.invisivel {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    text-decoration: none;
    color: inherit;
    opacity: 0; /* Tornar o link transparente */
    z-index: 10;
}


 

</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="#!">The Shop</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#!">Home</a></li>
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
								href="${pageContext.request.contextPath}/principal/contaUsuario.jsp">Minha conta</a></li>
						
						
					<% }else{ %>
					 <li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/principal/cadastro.jsp">Crie a sua conta</a></li>
								
				 <% } %>
				 
				 <% if("vendedor".equals(tipoConta)) { %>
				 <li class="nav-item"><a class="nav-link" href="principal/acessoVendedor.jsp">Gerenciamento de Produtos</a>
				 
				 <% } %>
								
				</ul>

                <!--  
				<form class="d-flex">
				<button class="btn btn-outline-dark" type="submit">
						<i class="bi-cart-fill me-1"></i> Carrinho 
						<span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
					</button>
				</form>
				-->
				
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
	
	
        
      <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">The Shop</h1>
                </div>
            </div>
        </header>

	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

				<c:forEach items="${produtos}" var="produto">
				
				<div class="col mb-5">
					<div class="card h-100">
						
						<img class="card-img-top"
							src="${produto.fotoProduto}" alt="..."/>
						<div class="card-body p-4">
							<div class="text-center">
								<h5 class="fw-bolder"><c:out value="${produto.titulo}" /> </h5>
								<a href="${pageContext.request.contextPath}/ServletProdutoCliente?id=${produto.id}" class="invisivel"></a>
								<span class="text-muted"><c:out value="R$ ${produto.valor}" /></span>
							</div>
						</div>
						<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
							<div class="text-center">
								<a class="btn btn-outline-dark mt-auto" href="#">Comprar</a>
							</div>
						</div>
					</div>
				</div>
				
				</c:forEach>



			</div>
		</div>
	</section>
	
	<nav aria-label="Page navigation example">
	 <div class="d-flex justify-content-center">
	 <ul class="pagination">
	 
	 <%
	 
	 int totalPagina = (int) request.getAttribute("totalPagina");
	 
	 for(int p = 0; p < totalPagina; p++) {
		 String url = request.getContextPath() + "/ServletProdutoCliente?acao=paginar&pagina=" + (p * 5);
		 out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"\">"+(p + 1)+"</a></li>");
	 }
	 
	 %>
	 </ul>
	 </div>
	</nav>
	
	 <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; The Shop 2025</p></div>
        </footer>


 
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</html>