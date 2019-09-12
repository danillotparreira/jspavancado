package br.com.curso.jspavancado.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.curso.jspavancado.dao.DaoUsuario;
import br.com.curso.jspavancado.model.Usuario;
import br.com.curso.jspavancado.service.RelatorioService;

@WebServlet("/donwloadFileServlet")
public class DonwloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RelatorioService relatorioService = new RelatorioService();

	private DaoUsuario dao = new DaoUsuario();

	public DonwloadFileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ServletContext context = request.getServletContext();
			String tipoExportar = request.getParameter("tipoExportar");

			List<Usuario> usuarios = dao.findAll(Usuario.class);

			List dados = new ArrayList<>();
			dados.add(usuarios);
			String fileUrl = relatorioService.gerarRelatorio(dados, new HashMap<>(), "relatoriousuario",
					"relatoriousuario", context);

			// Construir o caminho completo e absoluto do arquivo

			File downwloadFile = new File(fileUrl);
			FileInputStream inputStream = new FileInputStream(downwloadFile);

			String mimeType = context.getMimeType(fileUrl);

			if (mimeType == null) {
				// define tipo binário se mapeamento mime não for encontrado
				mimeType = "application/octet-stream";
			}
			// define atributo para resposta;
			response.setContentType(mimeType);
			response.setContentLength((int) downwloadFile.length());

			// define cabeçalho para resposta
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%$s\"", downwloadFile.getName());

			response.setHeader(headerKey, headerValue);

			OutputStream outputStream = response.getOutputStream();
			byte[] buffer = new byte[4096];
			int byteReader = -1;

			while ((byteReader = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, byteReader);
			}

			inputStream.close();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
