package controller;

import java.io.IOException;

import entity.Categoria;
import entity.Receta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.RecetaModel;

@WebServlet("/registraRecetaAlias")
public class RegistraRecetaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1 Recibir los datos del formulario del JSP
		req.setCharacterEncoding("UTF-8");

		String nombre = req.getParameter("nombre");
		String categoria = req.getParameter("categoria");
		String ingredientes = req.getParameter("ingredientes");
		String preparacion = req.getParameter("preparacion");
		String tiempoPreparacion = req.getParameter("tiempoPreparacion");
		String porciones = req.getParameter("porciones");
		String dificultad = req.getParameter("dificultad");
		String autor = req.getParameter("autor");

		//2 Crear un objeto Categoria
		Categoria objCategoria = new Categoria();
		objCategoria.setIdCategoria(Integer.parseInt(categoria));

		//3 Crear un objeto Receta
		Receta objReceta = new Receta();

		objReceta.setNombre(nombre);
		objReceta.setIngredientes(ingredientes);
		objReceta.setPreparacion(preparacion);
		objReceta.setTiempoPreparacion(Integer.parseInt(tiempoPreparacion));
		objReceta.setPorciones(Integer.parseInt(porciones));
		objReceta.setDificultad(dificultad);
		objReceta.setAutor(autor);
		objReceta.setCategoria(objCategoria);

		//4 Crear un objeto RecetaModel
		RecetaModel model = new RecetaModel();

		int salida = model.insertaReceta(objReceta);

		String mensajeSalida = (salida > 0)
				? "Receta registrada correctamente (OK)"
				: "Error al registrar la receta";

		//5 Enviar una respuesta al cliente en JSON al jquery
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write("{\"mensajeSalida\":\"" + mensajeSalida + "\"}");

	}

}