<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Recursos Avançado em Java</title>
</head>
<body>
	<h3>Recursos Avançado em Java</h3>

	<table>
		<tr>
			<td>
				<a href="pages/capturarexcecoes.jsp">Capturar Exceções</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="pages/acessoAoSistema.jsp">Acesso ao Sistema</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="pages/paginaPai.jsp">Loading jQuery</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="pages/progressbar.jsp">Progess Bar</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="pages/upload.jsp">Upload</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="pages/datatabel.jsp">Data Table</a>
			</td>
		</tr>
		<c:if test="${usuario != null}">
			<tr>
				<td>
					Logado: ${usuario.login}
					<a href="pages/autenticarServlet?deslogar=true">Deslogar</a>
				</td>
			</tr>
		</c:if>
	</table>
</body>
</html>