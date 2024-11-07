package servlets;

import java.io.IOException;

import dao.DAOUsuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Usuario;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletLogin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		String msg = "";

		if (email != null && !email.isEmpty() && senha != null && !senha.isEmpty()) {

			Usuario usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setSenha(senha);

			DAOUsuario dao = new DAOUsuario();
			

			try {
				
				Long idUsuario = dao.logarUsuaurio(usuario);
				
				if (idUsuario != null) {
					
					HttpSession session = request.getSession();
					session.setAttribute("idUsuario", idUsuario); //Guarda o id na sessão
					
					Usuario usuarioLogado = dao.consultaUsuario(idUsuario);
					session.setAttribute("usuario", usuarioLogado);
					
					String tipoConta = dao.obterTipoConta(idUsuario);
					session.setAttribute("tipoconta", tipoConta);
					
					//System.out.println(tipoConta);
					
					if (dao.verificarTipoContaCliente(idUsuario)) {
						RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
						redirecionar.forward(request, response);
					} else if (dao.verificarTipoContaVendedor(idUsuario)) {
						RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
						redirecionar.forward(request, response);
					}
				} else {
					msg = "Não foi possível realizar o login!";

					RequestDispatcher redirecionar = request.getRequestDispatcher("principal/login.jsp");
					request.setAttribute("msg", msg);
					redirecionar.forward(request, response);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			msg = "Preencha todos os campos!";

			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/login.jsp");
			request.setAttribute("msg", msg);
			redirecionar.forward(request, response);
		}

	}

}
