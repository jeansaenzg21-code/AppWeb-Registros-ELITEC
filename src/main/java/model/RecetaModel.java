package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Categoria;
import entity.Receta;
import util.MySqlDBConexion;

public class RecetaModel {

    public int insertaReceta(Receta obj) {

        int salida = -1;

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = MySqlDBConexion.getConexion();

            String sql = "insert into receta(nombre,ingredientes,preparacion,"
                    + "tiempoPreparacion,porciones,dificultad,autor,idCategoria) "
                    + "values (?,?,?,?,?,?,?,?)";

            pstm = conn.prepareStatement(sql);

            pstm.setString(1, obj.getNombre());
            pstm.setString(2, obj.getIngredientes());
            pstm.setString(3, obj.getPreparacion());
            pstm.setInt(4, obj.getTiempoPreparacion());
            pstm.setInt(5, obj.getPorciones());
            pstm.setString(6, obj.getDificultad());
            pstm.setString(7, obj.getAutor());
            pstm.setInt(8, obj.getCategoria().getIdCategoria());

            salida = pstm.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (pstm != null)
                    pstm.close();

                if (conn != null)
                    conn.close();

            } catch (Exception e2) {

                e2.printStackTrace();

            }

        }

        return salida;
    }

    public List<Receta> listaRecetaPorNombre(String nombre) {

        ArrayList<Receta> salida = new ArrayList<Receta>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = MySqlDBConexion.getConexion();

            String sql = "select r.idReceta, r.nombre, r.ingredientes, "
                    + "r.preparacion, r.tiempoPreparacion, r.porciones, "
                    + "r.dificultad, r.autor, r.estado, "
                    + "c.idCategoria, c.descripcion "
                    + "from receta r "
                    + "inner join categoria c "
                    + "on r.idCategoria = c.idCategoria "
                    + "where r.nombre like ?";

            pstm = conn.prepareStatement(sql);

            pstm.setString(1, "%" + nombre + "%");

            rs = pstm.executeQuery();

            while (rs.next()) {

                // Objeto Categoria
                Categoria objCategoria = new Categoria();
                objCategoria.setIdCategoria(rs.getInt("idCategoria"));
                objCategoria.setDescripcion(rs.getString("descripcion"));

                // Objeto Receta
                Receta objReceta = new Receta();

                objReceta.setIdReceta(rs.getInt("idReceta"));
                objReceta.setNombre(rs.getString("nombre"));
                objReceta.setIngredientes(rs.getString("ingredientes"));
                objReceta.setPreparacion(rs.getString("preparacion"));
                objReceta.setTiempoPreparacion(rs.getInt("tiempoPreparacion"));
                objReceta.setPorciones(rs.getInt("porciones"));
                objReceta.setDificultad(rs.getString("dificultad"));
                objReceta.setAutor(rs.getString("autor"));
                objReceta.setCategoria(objCategoria);
                objReceta.setEstado(rs.getInt("estado"));

                salida.add(objReceta);

            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (rs != null)
                    rs.close();

                if (pstm != null)
                    pstm.close();

                if (conn != null)
                    conn.close();

            } catch (Exception e2) {

                e2.printStackTrace();

            }

        }

        return salida;
    }

    // Actualizar receta
    public int actualizarReceta(Receta receta) {

        int salida = -1;

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = MySqlDBConexion.getConexion();

            String sql = "update receta set nombre=?, ingredientes=?, "
                    + "preparacion=?, tiempoPreparacion=?, porciones=?, "
                    + "dificultad=?, autor=?, idCategoria=?, estado=? "
                    + "where idReceta=?";

            pstm = conn.prepareStatement(sql);

            pstm.setString(1, receta.getNombre());
            pstm.setString(2, receta.getIngredientes());
            pstm.setString(3, receta.getPreparacion());
            pstm.setInt(4, receta.getTiempoPreparacion());
            pstm.setInt(5, receta.getPorciones());
            pstm.setString(6, receta.getDificultad());
            pstm.setString(7, receta.getAutor());
            pstm.setInt(8, receta.getCategoria().getIdCategoria());
            pstm.setInt(9, receta.getEstado());
            pstm.setInt(10, receta.getIdReceta());

            System.out.println("SQL ==> " + pstm);

            salida = pstm.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (pstm != null)
                    pstm.close();

                if (conn != null)
                    conn.close();

            } catch (Exception e2) {

                e2.printStackTrace();

            }

        }

        return salida;
    }

    // Eliminar receta fisicamente
    public int eliminarReceta(int idReceta) {

        int salida = -1;

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = MySqlDBConexion.getConexion();

            String sql = "delete from receta where idReceta=?";

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, idReceta);

            System.out.println("SQL ==> " + pstm);

            salida = pstm.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (pstm != null)
                    pstm.close();

                if (conn != null)
                    conn.close();

            } catch (Exception e2) {

                e2.printStackTrace();

            }

        }

        return salida;
    }

    // Buscar receta por ID
    public Receta buscarPorId(int idReceta) {

        Receta receta = null;

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            conn = MySqlDBConexion.getConexion();

            String sql = "select r.*, c.descripcion from receta r "
                    + "inner join categoria c "
                    + "on r.idCategoria = c.idCategoria "
                    + "where r.idReceta=?";

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, idReceta);

            System.out.println("SQL ==> " + pstm);

            rs = pstm.executeQuery();

            if (rs.next()) {

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setDescripcion(rs.getString("descripcion"));

                receta = new Receta();

                receta.setIdReceta(rs.getInt("idReceta"));
                receta.setNombre(rs.getString("nombre"));
                receta.setIngredientes(rs.getString("ingredientes"));
                receta.setPreparacion(rs.getString("preparacion"));
                receta.setTiempoPreparacion(rs.getInt("tiempoPreparacion"));
                receta.setPorciones(rs.getInt("porciones"));
                receta.setDificultad(rs.getString("dificultad"));
                receta.setAutor(rs.getString("autor"));
                receta.setCategoria(categoria);
                receta.setEstado(rs.getInt("estado"));

            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (rs != null)
                    rs.close();

                if (pstm != null)
                    pstm.close();

                if (conn != null)
                    conn.close();

            } catch (Exception e2) {

                e2.printStackTrace();

            }

        }

        return receta;
    }

}