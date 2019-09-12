<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Barra de progresso</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<style type="text/css">
#myprogress {
	width: 100%;
	background-color: #ddd; /* fundo barra de progresso - cor cinza*/
}

#mybar {
	width: 1%;
	height: 30px;
	background-color: #4CAF50;
}

.ui-progressbar {
	position: relative;
}

.progress-label {
	position: relative;
	left: 50%;
	top: 4px;
	font-weight: bold;
	text-shadow: 1px 1px 0 #fff;
}
</style>

</head>
<body>

	<h1>Exemplo com JavaScript</h1>

	<div id="myprogress">
		<div id="mybar"></div>
	</div>
	<br>
	<button onclick="exibirBarra();">Iniciar Barra de progresso</button>

	<br>

	<h1>Exemplo com jQuery</h1>
	<div id="progressbar">
		<div class="progress-label">Carregando...</div>
	</div>

	<br>
	<a href="../index.jsp">Voltar</a>



	<script type="text/javascript">
		/* Progess Bar jQuery*/

		$(function() {
			var progressbar = $('#progressbar'), progresslabel = $('.progress-label');
			progressbar.progressbar({
				value : false,
				change : function() {
					progresslabel.text(progressbar.progressbar('value') + "%");
				},
				complete : function() {
					progresslabel.text('Completo!');
				}
			});
			function progress() {
				var val = progressbar.progressbar("value") || 0;
				progressbar.progressbar("value", val + 2);

				if (val < 99) {
					setTimeout(progress, 80);
				}
			}
			setTimeout(progress, 2000);

		});

		/* progress bar JavaScript*/
		function exibirBarra() {
			var elem = document.getElementById('mybar');
			var width = 1;
			var id = setInterval(frame, 10);
			function frame() {
				if (width >= 100) {
					clearInterval(id);
				} else {
					width++;
					elem.style.width = width + "%";
				}
			}
		}
	</script>
</body>
</html>