package controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entity.Categoria;
import entity.Receta;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import model.RecetaModel;

@WebServlet("/crudRecetaAlias")
public class CrudRecetaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String metodo = req.getParameter("metodo");

        if (metodo == null)
            metodo = "";

        switch (metodo) {

        case "listaPorNombre":
            listaPorNombre(req, res);
            break;

        case "registra":
            registra(req, res);
            break;

        case "actualiza":
            actualiza(req, res);
            break;

        case "eliminacionFisica":
            eliminacionFisica(req, res);
            break;

        case "eliminacionLogica":
            eliminacionLogica(req, res);
            break;

        default:
            res.setContentType("application/json");
            res.getWriter().write(
                    "{\"mensajeSalida\":\"Método no encontrado\"}");
        }
    }

    private void enviarLista(ServletResponse res, RecetaModel model)
            throws IOException {

        List<Receta> lista = model.listaRecetaPorNombre("");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonSalida = gson.toJson(lista);

        System.out.println("Respuesta JSON: " + jsonSalida);

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(jsonSalida);
    }

    public void listaPorNombre(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        String nombre = req.getParameter("nombre");

        RecetaModel model = new RecetaModel();

        List<Receta> lista = model.listaRecetaPorNombre(
                nombre == null ? "" : nombre);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonSalida = gson.toJson(lista);

        System.out.println("Respuesta JSON: " + jsonSalida);

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(jsonSalida);
    }

    private Receta obtenerReceta(ServletRequest req) {

        Categoria categoria = new Categoria();

        categoria.setIdCategoria(
                Integer.parseInt(req.getParameter("categoria")));

        Receta receta = new Receta();

        receta.setNombre(req.getParameter("nombre"));
        receta.setIngredientes(req.getParameter("ingredientes"));
        receta.setPreparacion(req.getParameter("preparacion"));

        receta.setTiempoPreparacion(
                Integer.parseInt(req.getParameter("tiempoPreparacion")));

        receta.setPorciones(
                Integer.parseInt(req.getParameter("porciones")));

        receta.setDificultad(req.getParameter("dificultad"));
        receta.setAutor(req.getParameter("autor"));
        receta.setCategoria(categoria);

        return receta;
    }

    public void registra(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        Receta receta = obtenerReceta(req);

        RecetaModel model = new RecetaModel();

        model.insertaReceta(receta);

        enviarLista(res, model);
    }

    public void actualiza(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        Receta receta = obtenerReceta(req);

        receta.setIdReceta(
                Integer.parseInt(req.getParameter("id")));

        receta.setEstado(
                Integer.parseInt(req.getParameter("estado")));

        RecetaModel model = new RecetaModel();

        model.actualizarReceta(receta);

        enviarLista(res, model);
    }

    public void eliminacionFisica(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        int idReceta = Integer.parseInt(
                req.getParameter("idReceta"));

        RecetaModel model = new RecetaModel();

        model.eliminarReceta(idReceta);

        enviarLista(res, model);
    }

    public void eliminacionLogica(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        int idReceta = Integer.parseInt(
                req.getParameter("idReceta"));

        RecetaModel model = new RecetaModel();

        Receta receta = model.buscarPorId(idReceta);

        if (receta != null) {

            receta.setEstado(
                    receta.getEstado() == 1 ? 0 : 1);

            model.actualizarReceta(receta);
        }

        enviarLista(res, model);
    }

}