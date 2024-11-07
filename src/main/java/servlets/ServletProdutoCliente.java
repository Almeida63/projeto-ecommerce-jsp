package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.DAOProduto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Produto;

@WebServlet("/ServletProdutoCliente")
public class ServletProdutoCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletProdutoCliente() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String acao = request.getParameter("acao");
		DAOProduto dao = new DAOProduto();

		try {
			if (id != null) { 
				Produto produto = dao.consultaProduto(Long.parseLong(id));
				request.setAttribute("produto", produto);
				request.getRequestDispatcher("principal/produto.jsp").forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("buscar")) { // Caso a ação de busca seja solicitada
				String tipoProduto = request.getParameter("tipoProduto");

				// Integer offSet = (Integer.parseInt(request.getParameter("pagina")));

				String pagina = request.getParameter("pagina");

				Integer offSet = (pagina != null) ? Integer.parseInt(pagina) : 0;

				List<Produto> produtos = dao.filtroProdutoPaginado(tipoProduto, offSet);
				request.setAttribute("produtos", produtos);

				request.setAttribute("totalPagina", dao.totalPaginaProdutoFiltrado(tipoProduto));

				request.getRequestDispatcher("principal/filtroProduto.jsp").forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("paginar")) {
				// Integer offSet = (Integer.parseInt(request.getParameter("pagina")));

				Integer offSet = (Integer.parseInt(request.getParameter("pagina")));

				List<Produto> produtos = dao.consultaProdutoPaginada(offSet);

				request.setAttribute("produtos", produtos);

				request.setAttribute("totalPagina", dao.totalPaginaProduto());

				request.getRequestDispatcher("index.jsp").forward(request, response);

			} else { // Caso contrário, exibe todos os produtos

				Integer offSet = 0;

				List<Produto> produtos = dao.consultaProdutoPaginada(offSet);
				request.setAttribute("produtos", produtos);

				request.setAttribute("totalPagina", dao.totalPaginaProduto());

				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
