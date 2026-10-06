<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>Consulta de Receta</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link
	rel="stylesheet"
	href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css">

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script
	src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js">
</script>

</head>

<body>

<div class="container mt-4">

	<h3>Consulta de Receta</h3>

	<div class="row mb-3">

		<div class="col-md-6">

			<label for="nombre" class="form-label">
				Nombre de Receta
			</label>

			<input
				type="text"
				class="form-control"
				id="nombre">

		</div>

		<div class="col-md-3 d-flex align-items-end">

			<button
				type="button"
				id="btnConsultar"
				class="btn btn-primary">

				Consultar

			</button>

		</div>

	</div>


	<table
		id="tablaReceta"
		class="table table-bordered table-striped">

		<thead>

			<tr>

				<th>ID</th>
				<th>Nombre</th>
				<th>Categoria</th>
				<th>Ingredientes</th>
				<th>Preparacion</th>
				<th>Tiempo</th>
				<th>Porciones</th>
				<th>Dificultad</th>
				<th>Autor</th>

			</tr>

		</thead>

		<tbody></tbody>

	</table>

</div>


<script>

var tabla;

$(document).ready(function () {

	tabla = $('#tablaReceta').DataTable({

		data: [],

		columns: [

			{ data: "idReceta" },

			{ data: "nombre" },

			{ data: "categoria.descripcion" },

			{ data: "ingredientes" },

			{ data: "preparacion" },

			{ data: "tiempoPreparacion" },

			{ data: "porciones" },

			{ data: "dificultad" },

			{ data: "autor" }

		]

	});


	$("#btnConsultar").click(function () {

		var nombre = $("#nombre").val();

		$.ajax({

			url: 'consultaRecetaAlias',
			type: 'POST',

			data: {
				nombre: nombre
			},

			success: function (data) {

				tabla.clear();

				tabla.rows.add(data);

				tabla.draw();

			},

			error: function () {

				alert("Error al consultar las recetas");

			}

		});

	});

});

</script>

</body>

</html>