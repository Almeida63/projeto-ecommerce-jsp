package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.DAOCarrinho;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Carrinho;
import models.Produto;

@WebServlet("/ServletCarrinho")
public class ServletCarrinho extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private DAOCarrinho dao;


	public ServletCarrinho() {
		super();
		dao = new DAOCarrinho();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Long idUsuario = (Long) session.getAttribute("idUsuario");
		
		if(idUsuario == null) {
			response.sendRedirect(request.getContextPath() + "/principal/login.jsp");
			return;
		}
		
		try {
			List<Carrinho> itensCarrinho = dao.visualizarCarrinhoUsuario(idUsuario);
			session.setAttribute("itensCarrinho", itensCarrinho);
			
			request.getRequestDispatcher("principal/meuCarrinho.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		//DAOCarrinho dao = new DAOCarrinho();

		HttpSession session = request.getSession();
		List<Produto> carrinho = (List<Produto>) session.getAttribute("carrinho");

		if (carrinho == null) {
			carrinho = new ArrayList<>();
		}

		Long idUsuario = (Long) request.getSession().getAttribute("idUsuario");
		String idProd = request.getParameter("idProduto");

		Long idProduto = Long.parseLong(idProd);

		if (idUsuario == null) {

			System.out.println("Usuario não logado");

			response.sendRedirect(request.getContextPath() + "/principal/login.jsp");
			return;

		}

		if (acao != null && !acao.isEmpty() && acao.equals("adicionar")) {

			try {

				
				response.setContentType("application/json");

				//DAOCarrinho dao = new DAOCarrinho();

				System.out.println(idUsuario);
				System.out.println(idProd);

				dao.adicionarProdutoCarrinho(idProduto, idUsuario);

				response.setContentType("text/plain");
				response.getWriter().write("Produto adicionado ao carrinho");

				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/produto.jsp");
				redirecionar.forward(request, response);
				
				

			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if(acao != null && !acao.isEmpty() && acao.equals("deletar")) {
			
			try {
				
				

				dao.deletarProdutoCarrinho(idProduto, idUsuario);                
				//response.sendRedirect(request.getContextPath() + "principal/meuCarrinho.jsp");
				
				
					
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		

	}

}
