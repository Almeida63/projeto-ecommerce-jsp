<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>The Shop- Visualizar Produto</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<style type="text/css">
    .container-form {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh; 
}

.form-container {
    width: 100%;
    max-width: 500px; /
    padding: 20px;
}

    h5 {
        margin-bottom: 20px;
        text-align: center;
    }

    .msg {
        position: absolute;
        top: 10%; -
        left: 33%;
        font-size: 15px;
        color: black;
        text-align: center; 
    }
    
    .link {
      padding: 20px;
    }
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">The Shop</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
					<li class="nav-item"><a class="nav-link" href="#!">Sobre</a></li>
				</ul>
			</div>
		</div>
	</nav>

     <div class="link">
   <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/ServletProduto">Voltar</a>
   </div>
   
    <div class="container-form">	    
    <div class="form-container">
    
	<h5>
		Visualizar Produto
	</h5>
    <h4 class="msg">${msg}</h4> 
    <form action="${pageContext.request.contextPath}/ServletProduto" enctype="multipart/form-data" method="post" id="formUser">
        <input type="hidden" name="acao" id="acao" value="">
        <input type="hidden" name="idProduto" value="${produto.id}">
        
        <div class="form-group">
            <div class="input-group-prepend">
                <img alt="Foto Produto" id="fotoembase64" src="${produto.fotoProduto}" width="150px" class="img-thumbnail">
            </div>
            <input type="file" id="fileFoto" name="fileFoto" accept="image/*" onchange="visualizarFoto('fotoembase64', 'fileFoto');" class="form-control-file" style="margin-top: 15px; margin-left: 5px;">
        </div>    
                
        
        <label for="tipoProduto">Tipo do Produto: </label>
<select class="form-select" name="tipoProduto" id="tipoProduto" aria-label="Default select example">
    <option value="camiseta" ${produto.tipo == 'camiseta' ? 'selected' : ''}>Camiseta</option>
    <option value="calça" ${produto.tipo == 'calça' ? 'selected' : ''}>Calça</option>
    <option value="calçado" ${produto.tipo == 'calçado' ? 'selected' : ''}>Calçado</option>
    <option value="acessorio" ${produto.tipo == 'acessorio' ? 'selected' : ''}>Acessório</option>
</select>

        <div class="form-group">
            <label for="nome">Titulo: </label> 
            <input type="text" class="form-control" name="titulo" value="${produto.titulo}" id="titulo" placeholder="titulo" required="required">
        </div>
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Descrição: </label>
            <textarea class="form-control" id="descricao" name="descricao" rows="3">${produto.descricao}</textarea>
        </div>
        <div class="form-group">
            <label for="senha">Preço:</label> 
            <input type="text" class="form-control" name="preco" value="${produto.valor}" id="preco" placeholder="Preco" required="required">
        </div>

        <br>
        
        <div class="buttons">
            <button type="submit" class="btn btn-outline-dark mt auto" onclick="setAcao('atualizar')">Atualizar</button>
            <button class="btn btn-outline-dark mt auto" onClick="return criarDelete();">Excluir Produto</button>
        </div>
    </form>
    </div>
    </div>
    
	
    
    
     
 
     <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; The Shop 2025</p></div>
        </footer>

</body>



<script type="text/javascript">

function visualizarFoto(fotoembase64, fileFoto) {
    var preview = document.getElementById(fotoembase64);
    var fileUser = document.getElementById(fileFoto).files[0];
    var reader = new FileReader();
    
    reader.onloadend = function () {
       preview.src = reader.result; /*Carrega foto na tela */
    };
    
    if(fileUser) {
        reader.readAsDataURL(fileUser); /*Preview da imagem */
    } else {
        preview.src= '';
    }
}

function setAcao(acao) {
    document.getElementById('acao').value = acao;
    console.log("Ação definida: " + acao);  

    document.getElementById('formUser').submit();
}

function criarDelete(){
    if(confirm("Deseja excluir o produto?")) {
        document.getElementById('formUser').method = 'get';
        document.getElementById("acao").value = 'deletar';
        document.getElementById("formUser").submit();
        
        alert('Produto Excluído!')
    } else {
        return false;
    }
}

</script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
</html>
