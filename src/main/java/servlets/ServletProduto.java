package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import dao.DAOProduto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import models.Produto;

@MultipartConfig
@WebServlet("/ServletProduto")
public class ServletProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletProduto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Long idUsuario = (Long) session.getAttribute("idUsuario");
		System.out.println(idUsuario);

		DAOProduto dao = new DAOProduto();
		List<Produto> produtos = new ArrayList<>();

		String idProduto = request.getParameter("id");
		System.out.println(idProduto);

		if (idProduto != null && !idProduto.isEmpty()) {
			try {
				Produto produto = dao.consultaProduto(Long.parseLong(idProduto));

				if (produto != null) {
					request.setAttribute("produto", produto);

					request.getRequestDispatcher("principal/visualizarProduto.jsp").forward(request, response);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		try {
			String acao = request.getParameter("acao");
			Produto produto = new Produto();

			System.out.println(acao);

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				String id = request.getParameter("idProduto");

				Long idProd = Long.parseLong(id);

				dao.deletarProduto(produto, idProd);

				// Recarrega a lista de produtos após exclusão
				List<Produto> list = dao.imprimirListaVendedor(idUsuario);
				request.setAttribute("produtos", list);

				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/meusProdutos.jsp");
				redirecionar.forward(request, response);
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (idUsuario != null) {

			try {
				produtos = dao.imprimirListaVendedor(idUsuario);
				request.setAttribute("produtos", produtos);
				System.out.println("Quantidade de produtos: " + produtos.size());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher redirecionar = request.getRequestDispatcher("principal/meusProdutos.jsp");
		redirecionar.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Long idVendedor = (Long) session.getAttribute("idUsuario");

		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String tipoProduto = request.getParameter("tipoProduto");
		String preco = request.getParameter("preco");
		String idProduto = request.getParameter("idProduto"); 

		Produto produto = new Produto();

		BigDecimal valor = new BigDecimal(preco.replace(",", "."));

		produto.setTitulo(titulo);
		produto.setDescricao(descricao);
		produto.setTipo(tipoProduto);
		produto.setValor(valor);
		produto.setId_vendedor(idVendedor);

		DAOProduto dao = new DAOProduto();

		try {
			if (idProduto == null || idProduto.isEmpty()) {
				// Gravação de um novo produto
				Part part = request.getPart("fileFoto");
				if (part != null && part.getSize() > 0) {
					byte[] foto = IOUtils.toByteArray(part.getInputStream());
					String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64,"
							+ new Base64().encodeAsString(foto);

					produto.setFotoProduto(imagemBase64);
					produto.setExtensaofoto(part.getContentType().split("\\/")[1]);
				}
				dao.gravarProduto(produto);
				request.setAttribute("msg", "Produto cadastrado com sucesso!");
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/telaProduto.jsp");
				redirecionar.forward(request, response);

			} else {
				// Atualização de um produto existente
				Long idProd = Long.parseLong(idProduto);
				produto.setId(idProd); // definindo o ID do produto para atualizar

				// Recuperar a foto existente do banco caso uma nova foto não seja enviada
				Produto produtoExistente = dao.consultaProduto(idProd);
				String fotoExistente = produtoExistente.getFotoProduto();
				String extensaoFotoExistente = produtoExistente.getExtensaofoto();

				Part part = request.getPart("fileFoto");
				if (part != null && part.getSize() > 0) {
					byte[] foto = IOUtils.toByteArray(part.getInputStream());
					String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64,"
							+ new Base64().encodeAsString(foto);
					produto.setFotoProduto(imagemBase64);
					produto.setExtensaofoto(part.getContentType().split("\\/")[1]);
				} else {
					// Mantém a foto existente se nenhuma nova foto for enviada
					produto.setFotoProduto(fotoExistente);
					produto.setExtensaofoto(extensaoFotoExistente);
				}

				dao.atualizarProduto(produto, idProd);
				request.setAttribute("msg", "Produto atualizado com sucesso!");
				
				// Setar o produto atualizado como atributo para manter os valores no formulário
				request.setAttribute("produto", produto);
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/visualizarProduto.jsp");
				redirecionar.forward(request, response);
			}

			// Redirecionar para a página do formulário, mantendo os dados preenchidos
			//RequestDispatcher redirecionar = request.getRequestDispatcher("principal/telaProduto.jsp");
			//redirecionar.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
