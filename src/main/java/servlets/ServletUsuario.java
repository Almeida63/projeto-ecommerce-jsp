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

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletUsuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Long idUsuario = (Long) session.getAttribute("idUsuario");
		System.out.println(idUsuario);

		if (idUsuario != null) {
			DAOUsuario dao = new DAOUsuario();

			try {
				Usuario usuario = dao.consultaUsuario(idUsuario);

				request.setAttribute("usuario", usuario);
				
				 // Encaminha para a página de conta do usuário
                //RequestDispatcher redirecionar = request.getRequestDispatcher("principal/contaUsuario.jsp");
                //redirecionar.forward(request, response);
				
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Long idUsuario = (Long) session.getAttribute("idUsuario");
		String msg = "";
		DAOUsuario dao = new DAOUsuario();

		try {

			//Usuario usuario = new Usuario();

			String acao = request.getParameter("acao");
            


			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("atualizar")) {

				Usuario usuario = dao.consultaUsuario(idUsuario);
				request.setAttribute("usuario", usuario);
				
                if(usuario != null) {
				 
				String nome = request.getParameter("nome");
				String email = request.getParameter("email");
				String senha = request.getParameter("senha");

				if (nome != null && !nome.isEmpty() && email != null && !email.isEmpty() && senha != null
						&& !senha.isEmpty()) {

					usuario.setNome(nome);
					usuario.setEmail(email);
					usuario.setSenha(senha);

					dao.atualizarUsuario(usuario, idUsuario);

					msg = "Atualizado com sucesso!";
					
					//Guarda atualização do usuario na sessão
					session.setAttribute("usuario", usuario);

				} else {
					msg = "Erro! Nenhum campo pode ficar vazio.";

				}
                }else {
                	msg = "Erro ao buscar usuario";
                }

				request.setAttribute("msg", msg);
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/contaUsuario.jsp");
				redirecionar.forward(request, response);

			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				Usuario usuario = new Usuario();
				
				dao.deletarUsuario(usuario, idUsuario);
					
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/login.jsp");
				redirecionar.forward(request, response);
				
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
				session.invalidate(); //invalida sessão atual
				
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/login.jsp");
				redirecionar.forward(request, response);
				
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
