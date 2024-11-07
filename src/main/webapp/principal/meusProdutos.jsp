<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>The Shop - Meus Produtos</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />



<link
	href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'
	rel='stylesheet'>


<style type="text/css">
.text-gray {
	color: #aaa
}

img {
	height: 170px;
	width: 140px
}

.titulo {
	top: 30%;
	left: 50%;
}


.link {
 
  padding: 20px;

}
</style>
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
					<li class="nav-item"><a class="nav-link" href="#!">Sobre</a></li>
					<li class="nav-item"><a class="nav-link"
						href="principal/contaUsuario.jsp">Minha Conta</a></li>

				</ul>
			</div>
		</div>
	</nav>

    <div class="link">
   <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/principal/acessoVendedor.jsp">Voltar</a>
   </div>

	<div class="container py-5">
		<h5 class="text-center my-5">Meus Produtos</h5>

         <c:if test="${empty produtos }">
         <div class="col-12 text-center">
						<div class="alert alert-warning" role="alert">Nenhum produto cadastro no momento!</div>
					</div>
				</c:if>

		<div class="row">
			<div class="col-lg-8 mx-auto">

				<c:forEach items="${produtos}" var="produto">

					<li class="list-group-item"><input type="hidden"
						name="idProduto" value="${produto.id}">


						<div
							class="media align-items-lg-center flex-column flex-lg-row p-3">
							<div class="media-body order-2 order-lg-1">
								<h5 class="mt-0 font-weight-bold mb-2">
									<c:out value="${produto.titulo}" />
								</h5>


								<p class="font-italic text-muted mb-0 small">
									<c:out value="${produto.descricao}" />
								</p>
								<div
									class="d-flex align-items-center justify-content-between mt-1">
									<h6 class="font-weight-bold my-2">
										<c:out value="R$ ${produto.valor}" />
									</h6>
									<ul class="list-inline small">

									</ul>
								</div>
							</div>
							<img src="${produto.fotoProduto}" alt="Generic placeholder image"
								width="200" class="ml-lg-5 order-1 order-lg-2">
						</div>

						<div>

							
							<a
								href="${pageContext.request.contextPath}/ServletProduto?id=${produto.id}"
								class="btn btn-primary">Visualizar Produto</a>


				
							</form>


						</div></li>




				</c:forEach>





			</div>
		</div>


	</div>
	



</body>
</html>