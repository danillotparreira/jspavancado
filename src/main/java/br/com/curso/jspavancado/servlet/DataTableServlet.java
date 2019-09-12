package br.com.curso.jspavancado.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.curso.jspavancado.dao.DaoUsuario;
import br.com.curso.jspavancado.model.Usuario;

@WebServlet("/pages/dataTableServlet")
public class DataTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario dao = new DaoUsuario();

	public DataTableServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Usuario> usuarios = dao.findAll(Usuario.class);
		String dadosCompleto = "{\"draw\": 1," + "\"recordsTotal\": " + (usuarios.size() - 1) + ",\"recordsFiltered\":"
				+ (usuarios.size() - 1) + ",\"data\": " + usuarios + "}";
		response.setStatus(200);
		response.getWriter().write(dadosCompleto);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
