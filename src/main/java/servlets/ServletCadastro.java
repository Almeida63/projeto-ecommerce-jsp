package servlets;

import java.io.IOException;

import dao.DAOUsuario;
import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Usuario;

@WebServlet("/ServletCadastro")
public class ServletCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletCadastro() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String msg = "Cadastro realizado com sucesso";

		String nome = request.getParameter("nome");
		String tipoConta = request.getParameter("tipoConta");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setTipoConta(tipoConta);
		usuario.setEmail(email);
		usuario.setSenha(senha);

		DAOUsuario dao = new DAOUsuario();

		try {
			if (dao.validarEmail(usuario.getEmail())) {
				msg = "E-mail já cadastrado";
			} else {

				msg = "Cadastro realizado com sucesso!";
				dao.gravarUsuario(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher redirecionar = request.getRequestDispatcher("principal/cadastro.jsp");
		request.setAttribute("msg", msg);
		redirecionar.forward(request, response);

	}

}
