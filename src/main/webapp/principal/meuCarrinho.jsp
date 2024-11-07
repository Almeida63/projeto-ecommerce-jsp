<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meu carrinho</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css">
    <style>
    
    .link {
    
    padding: 20px;
    
    }
    
    </style>
</head>
<body>


   <div class="link">
   <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/index.jsp">Voltar</a>
   </div>

    <section class="h-100">
        <div class="container h-100 py-5">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-10">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <h3 class="fw-normal mb-0">Meu carrinho</h3>
                    </div>

                    <c:if test="${empty itensCarrinho}">
                        <div class="alert alert-warning" role="alert">
                            Seu carrinho está vazio.
                        </div>
                    </c:if>

                    <c:forEach items="${itensCarrinho}" var="item">
                        <div class="card rounded-3 mb-4">
                            <div class="card-body p-4">
                                <div class="row d-flex justify-content-between align-items-center">
                                    <div class="col-md-2 col-lg-2 col-xl-2">
                                        <img src="${item.fotoProduto}" class="img-fluid rounded-3" alt="">
                                    </div>

                                    <div class="col-md-3 col-lg-3 col-xl-3">
                                        <p class="lead fw-normal mb-2">${item.titulo}</p>
                                        <p class="font-weight-light">${item.descricao}</p>
                                    </div>

                                    <p>Qnt: 1</p>

                                    <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                                        <h5 class="mb-0">R$ ${item.preco}</h5>
                                    </div>
                                      
                                    
                                    <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                                    <button class="btn btn-link text-danger" onclick="removerProduto(${item.idProduto}, ${item.idUsuario});">
                                    <i class="bi bi-trash-fill"></i>
                                     </button>
                                     </div>
                                    
                                    
                                  
                                      
                            </div>
                            </div>
                        </div>
                    </c:forEach>
                    
                    
                    <div class="card">
                        <div class="card-body">
                            <button type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-warning btn-block btn-lg">Finalizar compra</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
    
   <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
   
    <!--  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    
     <script>
        function removerProduto(idProduto, idUsuario) {
            if (confirm('Deseja remover este item do carrinho?')) {
                $.ajax({
                    method: 'POST',
                    url: '${pageContext.request.contextPath}/ServletCarrinho',
                    data: {
                        acao: 'deletar',
                        idProduto: idProduto,
                        idUsuario: idUsuario
                    },
                    success: function(response) {
                        alert('Produto removido do carrinho!');
                        // Atualiza a página para refletir a mudança
                        location.reload();
                    },
                    error: function(xhr, status, error) {
                        alert('Erro ao remover produto do carrinho: ' + error);
                    }
                });
            }
        }
    </script>
</body>
</html>
