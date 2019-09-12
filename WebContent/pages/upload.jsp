<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8" />
<title>Upload file</title>
<script type="text/javascript" src="../resources/js/jquery-3.4.1.min.js"></script>
</head>
<body>
	<input type="file" id="file" name="file" onchange="uploadFile();" />
	<img alt="Imagem" id="target" width="200">
	<br>
	<a href="../index.jsp">Voltar</a>
	<br />
	<br />
	<a href="fileUpload?acao=carregar">Carregar imagens</a>
	<br />
	<br /> ${listUsuarios.size()}
	<table style="border: 1px">
		<thead>
			<tr>
				<td>ID</td>
				<td>Login</td>
				<td>Imagem</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listUsuarios}" var="usuario">
				<tr>
					<td>${usuario.id}</td>
					<td>${usuario.login}</td>
					<td>
						<c:if test="${not empty usuario.imagemMimeType}">
							<a href="fileUpload?acao=download&id=${usuario.id}">Download</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>



	<script type="text/javascript">
		function uploadFile() {
			var target = document.querySelector("img");
			var file = document.querySelector("input[type=file]").files[0];

			var reader = new FileReader();

			reader.onload = function() {
				target.src = reader.result;

				$.ajax({
					method : "POST",
					url : "fileUpload",
					data : {
						fileUpload : reader.result
					}
				}).done(function(response) {
					alert('Sucesso: ' + response);
				}).fail(function(xhr, status, errorThrown) {
					alert('Error: ' + xhr.responseText);
				});
			}
			if (file) {
				reader.readAsDataURL(file);
			} else {
				target.src = "";
			}

		}
	</script>
</body>
</html>