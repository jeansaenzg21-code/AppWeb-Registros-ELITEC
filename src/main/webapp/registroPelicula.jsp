<!DOCTYPE html>
<html>

<head>

	<meta charset="UTF-8">

	<title>Registro Película ELITEC</title>

	<script src="js/bootstrap.js" type="text/javascript"></script>
	<script src="js/bootstrap.bundle.js" type="text/javascript"></script>
	<script src="js/bootstrap.esm.js" type="text/javascript"></script>
	<script src="js/jquery-4.0.0.min.js" type="text/javascript"></script>

	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/bootstrap-grid.css" rel="stylesheet">
	<link href="css/bootstrap-reboot.css" rel="stylesheet">
	<link href="css/bootstrap-utilities.css" rel="stylesheet">

</head>

<body>

	<div class="container">

		<h1>Registro de Película</h1>

		<form id="formPelicula" method="post" novalidate>

			<div class="row" style="margin-top: 2%;">

				<div class="col-6">

					<label for="titulo">
						Título
					</label>

					<input
						type="text"
						class="form-control"
						id="titulo"
						name="titulo"
						placeholder="Ingrese el título"
						maxlength="100"
						required>

					<div class="invalid-feedback">
						Ingrese el título
					</div>

				</div>


				<div class="col-6">

					<label for="genero">
						Género
					</label>

					<input
						type="text"
						class="form-control"
						id="genero"
						name="genero"
						placeholder="Ingrese el género"
						maxlength="50"
						required>

					<div class="invalid-feedback">
						Ingrese el género
					</div>

				</div>

			</div>


			<div class="row" style="margin-top: 2%;">

				<div class="col-6">

					<label for="director">
						Director
					</label>

					<input
						type="text"
						class="form-control"
						id="director"
						name="director"
						placeholder="Ingrese el director"
						maxlength="100"
						required>

					<div class="invalid-feedback">
						Ingrese el director
					</div>

				</div>


				<div class="col-6">

					<label for="fecEstreno">
						Fecha de Estreno
					</label>

					<input
						type="date"
						class="form-control"
						id="fecEstreno"
						name="fecEstreno"
						required>

					<div class="invalid-feedback">
						Ingrese la Fecha de Estreno
					</div>

				</div>

			</div>


			<div
				class="row justify-content-center"
				style="margin-top: 2%;">

				<button
					class="btn btn-primary"
					id="btnRegistrar"
					style="width: 200px">

					Registrar

				</button>

			</div>

		</form>

	</div>


	<script type="text/javascript">

	$("#btnRegistrar").click(function(e) {

		console.log("click en registrar película");

		e.preventDefault();

		let form = $('#formPelicula')[0];

		if (form.checkValidity() === false) {

			$(form).addClass('was-validated');

			return;
		}


		$.ajax({

			url: 'registraPeliculaAlias',

			type: 'POST',

			data: $(form).serialize(),

			success: function(response) {

				console.log(
					'response >>> ' + response
				);


				// Limpiar formulario

				$('#formPelicula')[0].reset();


				// Limpiar validaciones

				$('#formPelicula')
					.removeClass('was-validated');


				// Mostrar mensaje

				$('#formPelicula').prepend(
					'<div class="alert alert-success" role="alert">'
					+ response.mensajeSalida
					+ '</div>'
				);


				setTimeout(function() {

					$('.alert').remove();

				}, 3000);

			},


			error: function(xhr, status, error) {

				console.error(
					'Error al registrar película:',
					error
				);

			}

		});

	});

	</script>

</body>

</html>