/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

/**
 *
 * @author fededjpg
 */
public class Curso {
    int idCurso;
    String  Nombre;
    String Duracion;
    Float Precio;
    int Vacante;
    String Disponibilidad;
    String Fecha_Inicial;
    String Fecha_Final;
    
    //GETTERS
    public int getIdCurso() {
        return idCurso;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDuracion() {
        return Duracion;
    }

    public Float getPrecio() {
        return Precio;
    }

    public int getVacante() {
        return Vacante;
    }

    public String getDisponibilidad() {
        return Disponibilidad;
    }

    public String getFecha_Inicial() {
        return Fecha_Inicial;
    }

    public String getFecha_Final() {
        return Fecha_Final;
    }

    
    
    //SETTERS
    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setDuracion(String Duracion) {
        this.Duracion = Duracion;
    }

    public void setPrecio(Float Precio) {
        this.Precio = Precio;
    }

    public void setVacante(int Vacante) {
        this.Vacante = Vacante;
    }

    public void setDisponibilidad(String Disponibilidad) {
        this.Disponibilidad = Disponibilidad;
    }

    public void setFecha_Inicial(String Fecha_Inicial) {
        this.Fecha_Inicial = Fecha_Inicial;
    }

    public void setFecha_Final(String Fecha_Final) {
        this.Fecha_Final = Fecha_Final;
    }
    
    
    
}
