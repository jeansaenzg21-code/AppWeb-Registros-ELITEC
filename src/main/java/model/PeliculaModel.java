package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entity.Pelicula;
import util.MySqlDBConexion;

public class PeliculaModel {

	public int insertaPelicula(Pelicula obj) {

		int salida = -1;
		Connection cn = null;
		PreparedStatement ps = null;

		try {

			// 1 Crear la conexion a la BD
			cn = MySqlDBConexion.getConexion();

			// 2 Crear el SQL de insercion
			String sql = "INSERT INTO pelicula "
					+ "(titulo, genero, director, fechaEstreno) "
					+ "VALUES (?,?,?,?)";

			// 3 Crear el PreparedStatement
			ps = cn.prepareStatement(sql);

			ps.setString(1, obj.getTitulo());
			ps.setString(2, obj.getGenero());
			ps.setString(3, obj.getDirector());
			ps.setDate(4, java.sql.Date.valueOf(obj.getFechaEstreno()));

			System.out.println("SQL: " + ps);

			// 4 Ejecutar el SQL
			salida = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if (ps != null)
					ps.close();

				if (cn != null)
					cn.close();

			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}

		return salida;
	}
}