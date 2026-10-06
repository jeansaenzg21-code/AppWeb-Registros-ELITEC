package entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pelicula {

	private int idPelicula;
	private String titulo;
	private String genero;
	private String director;
	private LocalDate fechaEstreno;

}