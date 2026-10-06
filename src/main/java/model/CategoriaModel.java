package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Categoria;
import util.MySqlDBConexion;

public class CategoriaModel {

	public List<Categoria> listaCategoria(){
		ArrayList<Categoria> lista = new ArrayList<Categoria>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
            conn = MySqlDBConexion.getConexion();
            String sql = "select * from categoria";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setDescripcion(rs.getString("descripcion"));
                lista.add(categoria);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			try {
				if (rs != null)		rs.close();
				if (pstm != null)	pstm.close();
				if (conn != null)	conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
        }
		return lista;
	}
}