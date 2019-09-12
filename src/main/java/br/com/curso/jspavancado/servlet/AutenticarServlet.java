package br.com.curso.jspavancado.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.curso.jspavancado.dao.DaoLogin;
import br.com.curso.jspavancado.model.Usuario;

@WebServlet("/pages/autenticarServlet")
public class AutenticarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoLogin daoLogin = new DaoLogin();

	public AutenticarServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (Boolean.parseBoolean(request.getParameter("deslogar"))) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("../index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pagina = request.getParameter("pagina");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		Usuario usuario = daoLogin.logar(login, senha);
		if (usuario != null) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			session.setAttribute("usuario", usuario);

			RequestDispatcher dispatcher = request
					.getRequestDispatcher(pagina.equals("null") || pagina.isEmpty() ? "../index.jsp" : pagina);
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			request.setAttribute("pagina", pagina);
			dispatcher.forward(request, response);
		}
	}
}
