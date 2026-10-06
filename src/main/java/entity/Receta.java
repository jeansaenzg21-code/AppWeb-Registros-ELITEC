package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Receta {

	private int idReceta;
	private String nombre;
	private String ingredientes;
	private String preparacion;
	private int tiempoPreparacion;
	private int porciones;
	private String dificultad;
	private String autor;
	private Categoria categoria;

}