/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import VO.Curso;
import MySql.conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author fededjpg
 */
public class CursoProcess {
        /*Metodo agregar*/

    /**
     *
     * @param vo
     */

    public void Agregar_Curso(Curso vo) {
        conexion conec = new conexion();
        String sql = "INSERT INTO cursos (idCurso, Nombre, Duracion, Precio, Vacantes, Disponibilidad, Fecha_inicio, Fecha_final)\n"
                + "VALUES (?,?,?,?,?,?,?,?);";
        PreparedStatement ps = null;
        try {
            ps = conec.getConnection().prepareStatement(sql);
            ps.setInt(1, vo.getIdCurso());
            ps.setString(2, vo.getNombre());
            ps.setString(3, vo.getDuracion());
            ps.setFloat(4, vo.getPrecio());
            ps.setInt(5, vo.getVacante());
            ps.setString(6, vo.getDisponibilidad());
            ps.setString(7, vo.getFecha_Inicial());
            ps.setString(8, vo.getFecha_Final());
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
}
