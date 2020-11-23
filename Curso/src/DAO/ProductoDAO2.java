package DAO;

import MySql.conexion;
import VO.ProductoVO2;
import java.sql.*;
import java.util.ArrayList;


/*Metodo listar*/
public class ProductoDAO2 {

    public ArrayList<ProductoVO2> Listar_ProductoVO2() {
        ArrayList<ProductoVO2> list = new ArrayList<ProductoVO2>();
        conexion conec = new conexion();
        String sql = "SELECT * FROM alumnos;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductoVO2 vo = new ProductoVO2();
                vo.setIdAlumno(rs.getInt(1));
                vo.setNombre(rs.getString(2));
                vo.setCorreo(rs.getString(3));
                vo.setTelefono(rs.getString(4));
                vo.setPerfil(rs.getString(5));
                vo.setTipo(rs.getString(6));
                vo.setFoto(rs.getBytes(7));
                list.add(vo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
        return list;
    }


    /*Metodo agregar*/
    public void Agregar_ProductoVO2(ProductoVO2 vo) {
        conexion conec = new conexion();
        String sql = "INSERT INTO profesor (idProfesor, Nombre, Correo, Telefono, Perfil, Tipo, imagen)\n"
                + "VALUES (?,?,?,?,?,?,?);";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setInt(1, vo.getidAlumno());
            ps.setString(2, vo.getNombre());
            ps.setString(3, vo.getCorreo());
            ps.setString(4, vo.getTelefono());
            ps.setString(5, vo.getPerfil());
            ps.setString(6, vo.getTipo());
            ps.setBytes(7, vo.getFoto());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("A " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("B " + ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }


    /*Metodo Modificar*/
    public void Modificar_ProductoVO2(ProductoVO2 vo) {
        conexion conec = new conexion();
        String sql = "UPDATE producto SET nombre = ?, precio = ?, marca = ?, foto = ?\n"
                + "WHERE idProducto = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getNombre());
            ps.setString(2, vo.getCorreo());
            ps.setString(3, vo.getTelefono());
            ps.setString(4, vo.getPerfil());
            ps.setString(5, vo.getTipo());
            ps.setBytes(6, vo.getFoto());

            ps.setInt(5, vo.getidAlumno());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }

//    public void Modificar_ProductoVO2(ProductoVO2 vo) {
//        conexion conec = new conexion();
//        String sql = "UPDATE producto SET nombre = ?, precio = ?, marca = ? \n"
//                + "WHERE idProducto = ?;";
//        PreparedStatement ps = null;
//        try {
//            ps = conec.getConnection().prepareStatement(sql);
//            ps.setString(1, vo.getNombre());
//            ps.setString(2, vo.getCorreo());
//            ps.setString(3, vo.getTelefono());
//            ps.setString(4, vo.getPerfil());
//            ps.setString(5, vo.getTipo());
//            ps.setInt(6, vo.getidAlumno());
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        } finally {
//            try {
//                ps.close();
//                conec.desconectar();
//            } catch (Exception ex) {
//            }
//        }
//    }

    /*Metodo Eliminar*/
    public void Eliminar_ProductoVO2(ProductoVO2 vo) {
        conexion conec = new conexion();
        String sql = "DELETE FROM producto WHERE idProducto = ?;";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setInt(1, vo.getidAlumno());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conec.desconectar();
            } catch (Exception ex) {
            }
        }
    }

}
