package controller;

import java.io.IOException;

import entity.Pelicula;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PeliculaModel;

@WebServlet("/registraPeliculaAlias")
public class RegistraPeliculaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 1 Recibir los datos del formulario del JSP

		String titulo = req.getParameter("titulo");
		String genero = req.getParameter("genero");
		String director = req.getParameter("director");
		String fechaEstreno = req.getParameter("fecEstreno");

		System.out.println(
				"Datos recibidos: "
				+ titulo + " - "
				+ genero + " - "
				+ director + " - "
				+ fechaEstreno
		);

		// 2 Crear un objeto Pelicula

		Pelicula pelicula = new Pelicula();

		pelicula.setTitulo(titulo);
		pelicula.setGenero(genero);
		pelicula.setDirector(director);
		pelicula.setFechaEstreno(
				java.time.LocalDate.parse(fechaEstreno)
		);

		// 3 Crear un objeto PeliculaModel

		PeliculaModel model = new PeliculaModel();

		int salida = model.insertaPelicula(pelicula);

		String mensajeSalida =
				(salida > 0)
				? "Película registrada correctamente (OK)"
				: "Error al registrar la película";

		// 4 Enviar respuesta JSON al jQuery

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		resp.getWriter().write(
				"{\"mensajeSalida\":\""
				+ mensajeSalida
				+ "\"}"
		);

	}

}