<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CRUD de Receta ELITEC</title>

<script src="js/bootstrap.bundle.js"></script>
<script src="js/jquery-4.0.0.min.js"></script>
<script src="js/datatables.js"></script>
<script src="js/sweetalert2@11.js"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/datatables.css" rel="stylesheet">

</head>
<body>

<div class="container mt-4">

    <h1>Crud de Receta</h1>

    <div class="row mt-3 mb-3">

        <div class="col-4">
            <input class="form-control" id="nombre"
                placeholder="Buscar receta por nombre">
        </div>

        <div class="col-4">
            <button class="btn btn-primary" id="btnBuscar">
                Buscar
            </button>
        </div>

        <div class="col-4">
            <button class="btn btn-primary" onclick="abrirModal()">
                Registra
            </button>
        </div>

    </div>

    <table class="table table-striped" id="id_table">
        <thead>
            <tr>
                <th>Código</th>
                <th>Nombre</th>
                <th>Categoría</th>
                <th>Ingredientes</th>
                <th>Preparación</th>
                <th>Minutos</th>
                <th>Porciones</th>
                <th>Dificultad</th>
                <th>Autor</th>
                <th>Estado</th>
                <th>Editar</th>
                <th>Estado</th>
                <th>Eliminar</th>
            </tr>
        </thead>
        <tbody></tbody>
    </table>

</div>

<!-- MODAL REGISTRA -->

<div class="modal fade" id="id_div_modal_registra" tabindex="-1">

    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <div class="modal-header">
                <h5>Registro de Receta</h5>
                <button type="button" class="btn-close"
                    data-bs-dismiss="modal"></button>
            </div>

            <div class="modal-body">

                <form id="id_form_registra">

                    <input type="hidden" name="metodo" value="registra">

                    <div class="row">

                        <div class="col-md-6 mb-3">
                            <label>Nombre</label>
                            <input class="form-control"
                                name="nombre" required>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label>Categoría</label>
                            <select class="form-select"
                                name="categoria"
                                id="id_reg_categoria" required>
                                <option value="">[Seleccione]</option>
                            </select>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label>Ingredientes</label>
                            <textarea class="form-control"
                                name="ingredientes" required></textarea>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label>Preparación</label>
                            <textarea class="form-control"
                                name="preparacion" required></textarea>
                        </div>

                        <div class="col-md-4 mb-3">
                            <label>Tiempo (minutos)</label>
                            <input type="number" min="1"
                                class="form-control"
                                name="tiempoPreparacion" required>
                        </div>

                        <div class="col-md-4 mb-3">
                            <label>Porciones</label>
                            <input type="number" min="1"
                                class="form-control"
                                name="porciones" required>
                        </div>

                        <div class="col-md-4 mb-3">
                            <label>Dificultad</label>
                            <select class="form-select"
                                name="dificultad" required>
                                <option value="">[Seleccione]</option>
                                <option value="Facil">Facil</option>
                                <option value="Intermedio">Intermedio</option>
                                <option value="Dificil">Dificil</option>
                            </select>
                        </div>

                        <div class="col-md-12 mb-3">
                            <label>Autor</label>
                            <input class="form-control"
                                name="autor" required>
                        </div>

                    </div>

                    <div class="text-center">
                        <button type="button"
                            class="btn btn-primary"
                            id="id_btn_registra">
                            Registrar
                        </button>
                    </div>

                </form>

            </div>
        </div>
    </div>
</div>


<!-- MODAL ACTUALIZA -->

<div class="modal fade" id="id_div_modal_actualiza" tabindex="-1">

    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <div class="modal-header">
                <h5>Actualiza Receta</h5>
                <button type="button" class="btn-close"
                    data-bs-dismiss="modal"></button>
            </div>

            <div class="modal-body">

                <form id="id_form_actualiza">

                    <input type="hidden" name="metodo" value="actualiza">
                    <input type="hidden" name="id" id="id">

                    <div class="row">

                        <div class="col-md-6 mb-3">
                            <label>Nombre</label>
                            <input class="form-control"
                                id="id_act_nombre"
                                name="nombre" required>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label>Categoría</label>
                            <select class="form-select"
                                id="id_act_categoria"
                                name="categoria" required>
                            </select>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label>Ingredientes</label>
                            <textarea class="form-control"
                                id="id_act_ingredientes"
                                name="ingredientes" required></textarea>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label>Preparación</label>
                            <textarea class="form-control"
                                id="id_act_preparacion"
                                name="preparacion" required></textarea>
                        </div>

                        <div class="col-md-4 mb-3">
                            <label>Tiempo (minutos)</label>
                            <input type="number" min="1"
                                class="form-control"
                                id="id_act_tiempo"
                                name="tiempoPreparacion" required>
                        </div>

                        <div class="col-md-4 mb-3">
                            <label>Porciones</label>
                            <input type="number" min="1"
                                class="form-control"
                                id="id_act_porciones"
                                name="porciones" required>
                        </div>

                        <div class="col-md-4 mb-3">
                            <label>Dificultad</label>
                            <select class="form-select"
                                id="id_act_dificultad"
                                name="dificultad">
                                <option value="Facil">Facil</option>
                                <option value="Intermedio">Intermedio</option>
                                <option value="Dificil">Dificil</option>
                            </select>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label>Autor</label>
                            <input class="form-control"
                                id="id_act_autor"
                                name="autor" required>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label>Estado</label>
                            <select class="form-select"
                                id="id_act_estado" name="estado">
                                <option value="1">Activo</option>
                                <option value="0">Inactivo</option>
                            </select>
                        </div>

                    </div>

                    <div class="text-center">
                        <button type="button"
                            class="btn btn-primary"
                            id="id_btn_actualiza">
                            Actualizar
                        </button>
                    </div>

                </form>

            </div>
        </div>
    </div>
</div>


<script>

var IDIOMA = {
    processing: "Procesando...",
    lengthMenu: "_MENU_ registros por página",
    zeroRecords: "No existen registros",
    info: "Página _PAGE_ de _PAGES_",
    infoEmpty: "Sin registros",
    search: "Buscar:",
    paginate: {
        first: "Primero",
        last: "Último",
        next: "Siguiente",
        previous: "Anterior"
    }
};

$(document).ready(function() {

    $.ajax({
        url: 'cargaComboCategoriaAlias',
        type: 'GET',
        success: function(data) {

            data.forEach(function(obj) {

                $('#id_reg_categoria').append(
                    $('<option>').val(obj.idCategoria)
                        .text(obj.descripcion)
                );

                $('#id_act_categoria').append(
                    $('<option>').val(obj.idCategoria)
                        .text(obj.descripcion)
                );

            });

        },
        error: function() {
            Swal.fire('Error',
                'No se pudieron cargar las categorías', 'error');
        }
    });

    buscarRecetas();

});


$('#btnBuscar').click(function() {
    buscarRecetas();
});


function buscarRecetas() {

    $.ajax({
        url: 'crudRecetaAlias',
        type: 'GET',
        data: {
            metodo: 'listaPorNombre',
            nombre: $('#nombre').val()
        },
        success: function(data) {
            agregarGrilla(data);
        },
        error: function() {
            Swal.fire('Error', 'No se pudo buscar', 'error');
        }
    });

}


function agregarGrilla(lista) {

    if ($.fn.DataTable.isDataTable('#id_table')) {
        $('#id_table').DataTable().destroy();
    }

    $('#id_table').DataTable({

        data: lista,
        language: IDIOMA,
        scrollX: true,
        pageLength: 10,

        columns: [

            {data: 'idReceta'},
            {data: 'nombre'},
            {data: 'categoria.descripcion'},
            {data: 'ingredientes'},
            {data: 'preparacion'},
            {data: 'tiempoPreparacion'},
            {data: 'porciones'},
            {data: 'dificultad'},
            {data: 'autor'},

            {
                data: function(row) {
                    return row.estado == 1 ? 'Activo' : 'Inactivo';
                }
            },

            {
                data: function(row) {
                    return '<button class="btn btn-info btn-sm" ' +
                        'onclick="verFormularioActualiza(' +
                        row.idReceta + ')">Editar</button>';
                }
            },

            {
                data: function(row) {
                    return '<button class="btn btn-warning btn-sm" ' +
                        'onclick="eliminacionLogica(' +
                        row.idReceta + ')">E.Lógica</button>';
                }
            },

            {
                data: function(row) {
                    return '<button class="btn btn-danger btn-sm" ' +
                        'onclick="eliminacionFisica(' +
                        row.idReceta + ')">E.Física</button>';
                }
            }

        ]

    });

}


function abrirModal() {

    document.getElementById('id_form_registra').reset();

    bootstrap.Modal.getOrCreateInstance(
        document.getElementById('id_div_modal_registra')
    ).show();

}


$('#id_btn_registra').click(function() {

    var form = document.getElementById('id_form_registra');

    if (!form.reportValidity())
        return;

    $.ajax({

        url: 'crudRecetaAlias',
        type: 'POST',
        data: $('#id_form_registra').serialize(),

        success: function(data) {

            agregarGrilla(data);

            bootstrap.Modal.getInstance(
                document.getElementById('id_div_modal_registra')
            ).hide();

            Swal.fire('Correcto', 'Receta registrada', 'success');
        },

        error: function() {
            Swal.fire('Error', 'No se pudo registrar', 'error');
        }

    });

});


function verFormularioActualiza(idReceta) {

    var tabla = $('#id_table').DataTable();

    var lista = tabla.rows().data().toArray();

    var obj = lista.find(function(receta) {
        return receta.idReceta == idReceta;
    });

    if (!obj)
        return;

    $('#id').val(obj.idReceta);
    $('#id_act_nombre').val(obj.nombre);
    $('#id_act_categoria').val(obj.categoria.idCategoria);
    $('#id_act_ingredientes').val(obj.ingredientes);
    $('#id_act_preparacion').val(obj.preparacion);
    $('#id_act_tiempo').val(obj.tiempoPreparacion);
    $('#id_act_porciones').val(obj.porciones);
    $('#id_act_dificultad').val(obj.dificultad);
    $('#id_act_autor').val(obj.autor);
    $('#id_act_estado').val(obj.estado);

    bootstrap.Modal.getOrCreateInstance(
        document.getElementById('id_div_modal_actualiza')
    ).show();

}


$('#id_btn_actualiza').click(function() {

    var form = document.getElementById('id_form_actualiza');

    if (!form.reportValidity())
        return;

    $.ajax({

        url: 'crudRecetaAlias',
        type: 'POST',
        data: $('#id_form_actualiza').serialize(),

        success: function(data) {

            agregarGrilla(data);

            bootstrap.Modal.getInstance(
                document.getElementById('id_div_modal_actualiza')
            ).hide();

            Swal.fire('Correcto', 'Receta actualizada', 'success');
        },

        error: function() {
            Swal.fire('Error', 'No se pudo actualizar', 'error');
        }

    });

});


function eliminacionLogica(id) {

    $.ajax({

        url: 'crudRecetaAlias',
        type: 'POST',

        data: {
            metodo: 'eliminacionLogica',
            idReceta: id
        },

        success: function(data) {
            agregarGrilla(data);
        },

        error: function() {
            Swal.fire('Error',
                'No se pudo cambiar el estado', 'error');
        }

    });

}


function eliminacionFisica(id) {

    Swal.fire({

        title: '¿Está seguro?',
        text: 'La receta será eliminada permanentemente.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'

    }).then(function(result) {

        if (result.isConfirmed) {

            $.ajax({

                url: 'crudRecetaAlias',
                type: 'POST',

                data: {
                    metodo: 'eliminacionFisica',
                    idReceta: id
                },

                success: function(data) {
                    agregarGrilla(data);
                },

                error: function() {
                    Swal.fire('Error',
                        'No se pudo eliminar', 'error');
                }

            });

        }

    });

}

</script>

</body>
</html>