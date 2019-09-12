<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exceções</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<h3>Capturando Exceções com JSP</h3>
	<input type="text" value="" id="txtValue" placeholder="Valor Informado">
	<input type="button" value="Testar" onclick="testarExcecao();">
	<br>
	<a href="../index.jsp">Voltar</a>
</body>
<script type="text/javascript">
	function testarExcecao() {
		valorInformado = $('#txtValue').val();
		$.ajax({
			method : "POST",
			url : "capturarExcecao",
			data : {
				valorParam : valorInformado
			}
		}).done(function(response) {
			alert(response)
		}).fail(function(response) {
			alert(response.responseText);
		});
	}
</script>
</html>