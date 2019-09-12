package br.com.curso.jspavancado.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.curso.jspavancado.model.Usuario;

@WebFilter(urlPatterns = { "/pages/*" })
public class FilterAutenticacao implements Filter {

	public FilterAutenticacao() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		String pagina = req.getServletPath();
		if (usuario == null && !pagina.equalsIgnoreCase("/pages/autenticarServlet")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp?pagina=" + pagina);
			dispatcher.forward(request, response);
			return;
		}

		/** executa as ações do request e response **/
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
