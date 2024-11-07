<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>The Shop - Minha Conta</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<style type="text/css">
form {
	position: absolute;
	top: 40%;
	left: 33%;
	right: 33%;
}

h5 {
	position: absolute;
	top: 30%;
	left: 33%;
}

.msg {
	position: absolute;
	top: 25%;
	left: 33%;
	font-size: 15px;
	color: black;
}

.buttons {
   padding: 10px;
   position:absolute
}

.botao-sair {
    position: absolute;
    padding: 10px;
    left: 440px;

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

	        <h5>Minha Conta</h5>

            <form action="${pageContext.request.contextPath}/ServletUsuario" method="post" id="formUser">
           
			<input type="hidden" name="acao" id="acao" value="">
			<div class="form-group">
				<label for="nome">Nome: </label> 
				<input type="text"class="form-control" name="nome" id="nome" placeholder="Nome" value="${usuario.nome}" required="required">
			</div>
			<div class="form-group">
				<label for="email">E-mail: </label> <input type="email"
					class="form-control" name="email" id="email" placeholder="E-mail"
					required="required" value="${usuario.email}">
			</div>
			<div class="form-group">
				<label for="senha">Senha:</label> <input type="password"
					class="form-control" name="senha" id="senha" placeholder="Senha" value="${usuario.senha}"required="required">
			</div>

			<div class="buttons">
			 <button type="button" class="btn btn-outline-dark mt auto" onclick="setAcao('atualizar')">Atualizar</button>
            <button class="btn btn-outline-dark mt auto" onClick="return criarDelete();">Excluir conta</button>
        </div>

        <div class="botao-sair">
            <button class="btn btn-outline-dark mt auto" onClick="return logout();">Sair</button>
        </div>
        
        
		</form>
		
		<h4 class="msg">${msg}</h4> 
		

       
</body>

 <script>
    function setAcao(acao) {
        document.getElementById('acao').value = acao;
        document.getElementById('formUser').submit();
    }
    
    function criarDelete(){
    	if(confirm("Deseja excluir a sua conta?")) {
    		
    		document.getElementById('formUser').method = 'post';
    		document.getElementById("acao").value = 'deletar';
    		document.getElementById("formUser").submit();
    		
    		alert('Conta excluida com sucesso!')
    		}else{
    			return false;
    		}
    }
    
    
    function logout(){
    	if(confirm("Tem certeza que deseja sair da sua conta?")) {
    		
    		document.getElementById('formUser').method = 'post';
    		document.getElementById("acao").value = 'logout';
    		document.getElementById("formUser").submit();
    		
    	}else{
    		return false;
    	}
    }
    
</script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
</html>