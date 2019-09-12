<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Autenticar Usuario</title>
</head>
<body>
	<h3>Autenticar Usuario</h3>

	<form action="autenticarServlet" method="post">
		<input type="hidden" id="pagina" name="pagina" value="<%=request.getParameter("pagina")%>">
		<table>
			<tr>
				<td>Login</td>
				<td>
					<input type="text" id="login" name="login" placeholder="Login">
				</td>
			</tr>
			<tr>
				<td>Senha</td>
				<td>
					<input type="password" id="senha" name="senha" placeholder="Senha">
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" id="logar" name="logar" value="Logar">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>