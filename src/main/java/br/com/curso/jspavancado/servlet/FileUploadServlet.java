package br.com.curso.jspavancado.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import br.com.curso.jspavancado.dao.DaoUsuario;
import br.com.curso.jspavancado.model.Usuario;

@WebServlet("/pages/fileUpload")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public FileUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			if (acao != null && acao.equalsIgnoreCase("carregar")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("upload.jsp");
				request.setAttribute("listUsuarios", daoUsuario.findAll(Usuario.class));

				dispatcher.forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("download")) {

				Long id = Long.parseLong(request.getParameter("id"));

				Usuario usuario = daoUsuario.find(new Usuario(id));

				if (usuario.getImagemMimeType() != null) {
					/** Informa o contentType(imagem/jpeg...) do arquivo **/
					response.setContentType(usuario.getImagemMimeType());
					/** Seta o cabeçalho para salvar o arquivo **/
					response.setHeader("Content-Disposition", "attachment");
					/** Converte a base64 (imagem pura) em byte para salvar **/
					byte[] bytes = Base64.decodeBase64(usuario.getImagemBase64());
					OutputStream os = response.getOutputStream();
					os.write(bytes);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileUpload = request.getParameter("fileUpload");
		if (fileUpload != null) {
			try {
				Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
				daoUsuario.merge(usuario.fotoPerfil(fileUpload));
				response.getWriter().write("Upload realizado com sucesso.");
			} catch (Exception e) {
				response.getWriter().write("Erro ao realizar upload.");
			}
		} else {
			response.getWriter().write("Não recebeu a imagem");
		}
	}

}
