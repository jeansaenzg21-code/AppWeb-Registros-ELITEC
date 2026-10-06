package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entity.Director;

public class DirectorModel {

	public int registrarDirector(Director director) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = util.MySqlDBConexion.getConexion();
			String sql = "insert into director(nombre,email,idTipo) values (?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, director.getNombre());
			pstm.setString(2, director.getEmail());
			pstm.setInt(3, director.getTipo().getIdTipo());
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
}