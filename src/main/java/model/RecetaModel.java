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
					+ "r.dificultad, r.autor, "
					+ "c.idCategoria, c.descripcion "
					+ "from receta r "
					+ "inner join categoria c "
					+ "on r.idCategoria = c.idCategoria "
					+ "where r.nombre like ?";

			pstm = conn.prepareStatement(sql);

			pstm.setString(1, "%" + nombre + "%");

			rs = pstm.executeQuery();

			while (rs.next()) {

				//Objeto Categoria
				Categoria objCategoria = new Categoria();
				objCategoria.setIdCategoria(rs.getInt("idCategoria"));
				objCategoria.setDescripcion(rs.getString("descripcion"));

				//Objeto Receta
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

}