<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro de Receta</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

</head>

<body>

<div class="container mt-4">

	<h3>Registro de Receta</h3>

	<form id="formRegistro">

		<div class="row">

			<div class="col-md-6 mb-3">
				<label for="nombre" class="form-label">Nombre</label>
				<input type="text"
					class="form-control"
					id="nombre"
					name="nombre"
					required>
			</div>

			<div class="col-md-6 mb-3">
				<label for="categoria" class="form-label">Categoria</label>

				<select
					class="form-control"
					id="categoria"
					name="categoria"
					required>

					<option value="">[Seleccione]</option>

				</select>
			</div>

			<div class="col-md-6 mb-3">
				<label for="ingredientes" class="form-label">Ingredientes</label>

				<textarea
					class="form-control"
					id="ingredientes"
					name="ingredientes"
					required></textarea>
			</div>

			<div class="col-md-6 mb-3">
				<label for="preparacion" class="form-label">Preparacion</label>

				<textarea
					class="form-control"
					id="preparacion"
					name="preparacion"
					required></textarea>
			</div>

			<div class="col-md-4 mb-3">
				<label for="tiempoPreparacion" class="form-label">
					Tiempo de Preparacion
				</label>

				<input type="number"
					class="form-control"
					id="tiempoPreparacion"
					name="tiempoPreparacion"
					required>
			</div>

			<div class="col-md-4 mb-3">
				<label for="porciones" class="form-label">Porciones</label>

				<input type="number"
					class="form-control"
					id="porciones"
					name="porciones"
					required>
			</div>

			<div class="col-md-4 mb-3">
				<label for="dificultad" class="form-label">Dificultad</label>

				<select
					class="form-control"
					id="dificultad"
					name="dificultad"
					required>

					<option value="">[Seleccione]</option>
					<option value="Facil">Facil</option>
					<option value="Media">Media</option>
					<option value="Dificil">Dificil</option>

				</select>
			</div>

			<div class="col-md-6 mb-3">
				<label for="autor" class="form-label">Autor</label>

				<input type="text"
					class="form-control"
					id="autor"
					name="autor"
					required>
			</div>

		</div>

		<button
	type="button"
	id="btnRegistrar"
	class="btn btn-primary">

	Registrar

</button>

<a
	href="consultaReceta.jsp"
	class="btn btn-success">

	Consultar

</a>

	</form>

	<div id="mensaje" class="mt-3"></div>

</div>


<script>

$(document).ready(function () {

	// Cargar categorias desde la base de datos
	$.ajax({

		url: 'cargaComboCategoriaAlias',
		type: 'GET',

		success: function (data) {

			var combo = $('#categoria');

			data.forEach(function (obj) {

				combo.append(
					'<option value="' +
					obj.idCategoria +
					'">' +
					obj.descripcion +
					'</option>'
				);

			});

		},

		error: function () {

			alert("Error al cargar las categorias");

		}

	});


	// Registrar receta
	$("#btnRegistrar").click(function () {

		if ($("#formRegistro")[0].checkValidity() == false) {

			$("#formRegistro")[0].reportValidity();
			return;

		}

		$.ajax({

			url: 'registraRecetaAlias',
			type: 'POST',

			data: {
				nombre: $("#nombre").val(),
				categoria: $("#categoria").val(),
				ingredientes: $("#ingredientes").val(),
				preparacion: $("#preparacion").val(),
				tiempoPreparacion: $("#tiempoPreparacion").val(),
				porciones: $("#porciones").val(),
				dificultad: $("#dificultad").val(),
				autor: $("#autor").val()
			},

			success: function (data) {

				$("#mensaje").html(
					'<div class="alert alert-success">' +
					data.mensajeSalida +
					'</div>'
				);

				$("#formRegistro")[0].reset();

			},

			error: function () {

				$("#mensaje").html(
					'<div class="alert alert-danger">' +
					'Error al registrar la receta' +
					'</div>'
				);

			}

		});

	});

});

</script>

</body>
</html>