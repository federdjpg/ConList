package VO;

public class ProductoVO2 {

    /*Todo los atributos*/
    int idAlumno;
    String Nombre;
    String Correo;
    String Telefono;
    String Perfil;
    String Tipo;
    private byte[] foto;

    public ProductoVO2() {
    }

    /*Todo los codigos get*/
    public int getidAlumno() {
        return idAlumno;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public String getPerfil() {
        return Perfil;
    }

    public String getTipo() {
        return Tipo;
    }

    /*Todo los codigos set*/
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public void setPerfil(String Perfil) {
        this.Perfil = Perfil;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    /**
     * @return the foto
     */
    public byte[] getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

}
