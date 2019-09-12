<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Load jQquery - Pai</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<h1>Pagina pai Load jQuery</h1>
	<input type="button" value="Carregar pagina" onclick="carregar();">
	<div id="mostraPaginaFilha"></div>
	<br>
	<a href="../index.jsp">Voltar</a>
</body>
<script type="text/javascript">
	function carregar() {
		$("#mostraPaginaFilha").load('paginaFilha.jsp');
	}
</script>
</html>