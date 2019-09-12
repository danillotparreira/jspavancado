<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Data Table</title>
</head>
<link rel="stylesheet" type="text/css" href="../resources/css/jquery.dataTables.min.css">
<script type="text/javascript" src="../resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../resources/js/jquery.dataTables.min.js"></script>
<body>
	<a href="../index.jsp">Voltar</a>
	<h1>Data Table</h1>

	<table id="tabelaUsuario" class="display" style="width: 100%">
		<thead>
			<tr>
				<th>id</th>
				<th>login</th>
				<th>senha</th>
				<th>Tipo da imagem</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabelaUsuario').DataTable({
				"processing" : true,
				"serverSide" : true,
				"ajax" : "dataTableServlet"
			});
		});
	</script>

</body>
</html>