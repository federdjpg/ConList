/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cursos;

/**
 *
 * @author Osvaldo
 */
import MySql.conexion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
//import java.sql.SQLException;
//import java.sql.Statement;

public final class Cursos extends javax.swing.JFrame {

    private TableRowSorter Buscar;
    conexion cn = new conexion();
    Connection con = cn.getConnection();

    //el cheko esta bien pendejo
    public static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

    /**
     * Creates new form CursosLista
     */
    public Cursos() {
        initComponents();
        this.setVisible(true);
        visualizar();
        model = (DefaultTableModel) TablaCursos.getModel();
    }

    PreparedStatement ps;
    ResultSet rs;
    String SQL;
    DefaultTableModel model;
    SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SDAgregar_Curso = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtActivo = new javax.swing.JCheckBox();
        txtDesactivo = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        Btn_Agregar_Curso = new javax.swing.JButton();
        txtClave = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDuracion = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtVacante = new javax.swing.JTextField();
        txtF_Inicial = new javax.swing.JTextField();
        txtF_Final = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        SDEditar_Curso = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        idCurso = new javax.swing.JLabel();
        txtEdit_Nombre = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtEdit_Duracion = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtEdit_Precio = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtEdit_Vacantes = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        Check1 = new javax.swing.JCheckBox();
        Check2 = new javax.swing.JCheckBox();
        Btn_Editar = new javax.swing.JButton();
        SDAsig_Prof = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtNombreCurso = new javax.swing.JLabel();
        txtBuscarProf = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        btnAsignarProf = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaProfesores = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarCurso = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCursos = new javax.swing.JTable();

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setFocusable(false);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Agregar Curso Nuevo");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Nombre:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Clave del Curso:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Precio:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Vacantes:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Duracion:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Fecha inicial:");

        txtActivo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtActivo.setText("Habilitado");
        txtActivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtActivoMouseClicked(evt);
            }
        });

        txtDesactivo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtDesactivo.setText("Deshabilitado");
        txtDesactivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDesactivoMouseClicked(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        Btn_Agregar_Curso.setBackground(new java.awt.Color(0, 153, 0));
        Btn_Agregar_Curso.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Btn_Agregar_Curso.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Agregar_Curso.setText("Agregar Curso");
        Btn_Agregar_Curso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Agregar_CursoActionPerformed(evt);
            }
        });

        txtClave.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtNombre.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtDuracion.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtPrecio.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        txtVacante.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel25.setText("Fecha final:");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel26.setText("Disponibilidad:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(337, 337, 337))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(148, 148, 148)
                                            .addComponent(jLabel6))
                                        .addComponent(txtDuracion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(188, 188, 188)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtClave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtVacante, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtF_Inicial, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel9))
                                            .addGap(12, 12, 12)
                                            .addComponent(jLabel25))
                                        .addComponent(txtF_Final, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel26))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtDesactivo, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                            .addGap(96, 96, 96)
                                            .addComponent(Btn_Agregar_Curso, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(11, 11, 11)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtVacante, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtF_Inicial, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtF_Final, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDesactivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Btn_Agregar_Curso)
                .addContainerGap(29, Short.MAX_VALUE))
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout SDAgregar_CursoLayout = new javax.swing.GroupLayout(SDAgregar_Curso.getContentPane());
        SDAgregar_Curso.getContentPane().setLayout(SDAgregar_CursoLayout);
        SDAgregar_CursoLayout.setHorizontalGroup(
            SDAgregar_CursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SDAgregar_CursoLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        SDAgregar_CursoLayout.setVerticalGroup(
            SDAgregar_CursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SDAgregar_CursoLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(51, 153, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Editar Curso");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel19.setText("Nombre:");

        idCurso.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        idCurso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        idCurso.setText("Clave del Curso");

        txtEdit_Nombre.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel21.setText("Duración:");

        txtEdit_Duracion.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel22.setText("Precio:");

        txtEdit_Precio.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel23.setText("Vacantes:");

        txtEdit_Vacantes.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel24.setText("Disponibilidad:");

        Check1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Check1.setText("Habilitado");
        Check1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Check1MouseClicked(evt);
            }
        });

        Check2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Check2.setText("Deshabilitado");
        Check2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Check2MouseClicked(evt);
            }
        });

        Btn_Editar.setBackground(new java.awt.Color(0, 153, 0));
        Btn_Editar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Btn_Editar.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Editar.setText("Confirmar");
        Btn_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_EditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idCurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(122, 122, 122))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtEdit_Nombre, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(txtEdit_Vacantes, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                    .addComponent(txtEdit_Duracion, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addComponent(Check1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(Check2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                                        .addGap(6, 6, 6)
                                                        .addComponent(txtEdit_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(19, 19, 19))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                        .addComponent(Btn_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(174, 174, 174))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(idCurso)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEdit_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEdit_Duracion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEdit_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEdit_Vacantes, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Check1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Check2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(Btn_Editar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SDEditar_CursoLayout = new javax.swing.GroupLayout(SDEditar_Curso.getContentPane());
        SDEditar_Curso.getContentPane().setLayout(SDEditar_CursoLayout);
        SDEditar_CursoLayout.setHorizontalGroup(
            SDEditar_CursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SDEditar_CursoLayout.setVerticalGroup(
            SDEditar_CursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(0, 102, 0));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Asignar Profesor");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel31.setText("Nombre:");

        txtNombreCurso.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        txtNombreCurso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNombreCurso.setText("Nombre del curso");

        txtBuscarProf.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel32.setText("Profesores");

        btnAsignarProf.setBackground(new java.awt.Color(0, 153, 0));
        btnAsignarProf.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnAsignarProf.setForeground(new java.awt.Color(255, 255, 255));
        btnAsignarProf.setText("Confirmar");
        btnAsignarProf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarProfActionPerformed(evt);
            }
        });

        TablaProfesores.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        TablaProfesores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Clave", "Nombre de Profesor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaProfesores.setRowHeight(40);
        jScrollPane2.setViewportView(TablaProfesores);
        if (TablaProfesores.getColumnModel().getColumnCount() > 0) {
            TablaProfesores.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombreCurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                                    .addComponent(txtBuscarProf))
                                .addGap(19, 19, 19))))))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(btnAsignarProf, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(txtNombreCurso)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarProf, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnAsignarProf)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(497, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout SDAsig_ProfLayout = new javax.swing.GroupLayout(SDAsig_Prof.getContentPane());
        SDAsig_Prof.getContentPane().setLayout(SDAsig_ProfLayout);
        SDAsig_ProfLayout.setHorizontalGroup(
            SDAsig_ProfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SDAsig_ProfLayout.setVerticalGroup(
            SDAsig_ProfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 153, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRO DE CURSOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel2.setText("Nombre del curso");

        txtBuscarCurso.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtBuscarCurso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarCursoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBuscarCurso)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 305, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/btn_Agregar.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Agregar Curso");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/btn_Editar.png"))); // NOI18N
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Editar Curso");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/btn_Asignacion.png"))); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Asignar Profesor");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/btn_Asignacion.png"))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Asignar Alumno");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Coffe Break");

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/btn_estado_cuenta.png"))); // NOI18N

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icono/btn_estado_cuenta.png"))); // NOI18N

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Estado de cuenta");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel27)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel28))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel29)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel30)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel17))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel11))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel13))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TablaCursos.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        TablaCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Clave", "Nombre de Curso", "Duracion", "Precio", "Vacantes", "Disponibilidad", "Fecha de Inicio", "Fecha Final"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaCursos.setColumnSelectionAllowed(true);
        TablaCursos.setRowHeight(40);
        jScrollPane1.setViewportView(TablaCursos);
        TablaCursos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (TablaCursos.getColumnModel().getColumnCount() > 0) {
            TablaCursos.getColumnModel().getColumn(0).setResizable(false);
            TablaCursos.getColumnModel().getColumn(0).setPreferredWidth(50);
            TablaCursos.getColumnModel().getColumn(1).setPreferredWidth(400);
            TablaCursos.getColumnModel().getColumn(2).setPreferredWidth(30);
            TablaCursos.getColumnModel().getColumn(3).setPreferredWidth(30);
            TablaCursos.getColumnModel().getColumn(4).setPreferredWidth(30);
            TablaCursos.getColumnModel().getColumn(5).setPreferredWidth(50);
            TablaCursos.getColumnModel().getColumn(6).setPreferredWidth(50);
            TablaCursos.getColumnModel().getColumn(7).setPreferredWidth(50);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Metodo para visualizar la tabla Cursos ###################################################################################
    public void visualizar() {
        try {
            String[] titulos = {"Clave", "Nombre", "Duracion", "Precio", "Vacantes", "Disponibilidad", "Fecha inicial", "Fecha de terminacion"};
            String[] registros = new String[8];
            SQL = "SELECT idCurso,Nombre,Duracion,Precio,Vacantes,Disponibilidad,Fecha_inicio,Fecha_final FROM cursos";

           model = new DefaultTableModel(null, titulos);
            Statement st = con.createStatement();
            rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("idCurso");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Duracion");
                registros[3] = rs.getString("Precio");
                registros[4] = rs.getString("Vacantes");
                registros[5] = rs.getString("Disponibilidad");
                registros[6] = rs.getString("Fecha_inicio");
                registros[7] = rs.getString("Fecha_final");
                model.addRow(registros);
            }

            TablaCursos.setModel(model);

        } catch (SQLException ex) {
            Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Object[] o = new Object[8];

    public void visualizar2() {

        try {
            //String [] titulos={"Clave", "Nombre", "Duracion", "Precio", "Vacantes", "Disponibilidad", "Fecha inicial", "Fecha de terminacion"};
            //  String [] registros = new String[8];
            String r1, r2, r3, r4, r5, r6, r7, r8;
            SQL = "SELECT idCurso,Nombre,Duracion,Precio,Vacantes,Disponibilidad,Fecha_inicio,Fecha_final FROM cursos";

            // model = new DefaultTableModel(null,titulos);
            Statement st = con.createStatement();
            rs = st.executeQuery(SQL);
            while (rs.next()) {
                r1 = rs.getString("idCurso");
                r2 = rs.getString("Nombre");
                r3 = rs.getString("Duracion");
                r4 = rs.getString("Precio");
                r5 = rs.getString("Vacantes");
                r6 = rs.getString("Disponibilidad");
                r7 = rs.getString("Fecha_inicio");
                r8 = rs.getString("Fecha_final");

                
            }
               o[0] = rs.getString("idCurso");
                o[1] = rs.getString("Nombre");
                o[2] = rs.getString("Duracion");
                o[3] = rs.getString("Precio");
                o[4] = rs.getString("Vacantes");
                o[5] = rs.getString("Disponibilidad");
                o[6] = rs.getString("Fecha_inicio");
                o[7] = rs.getString("Fecha_final");
                model.addRow(o); 
        } catch (SQLException ex) {
            Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void verCursos() {

    }

    public void visualizarProf() {
        try {
            String[] titulos = {"Clave", "Nombre"};
            String[] registros = new String[2];
            SQL = "SELECT idProfesor,Nombre FROM profesor";

            model = new DefaultTableModel(null, titulos);
            Statement st = con.createStatement();
            rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("idProfesor");
                registros[1] = rs.getString("Nombre");
                model.addRow(registros);
            }

            TablaProfesores.setModel(model);

        } catch (SQLException ex) {
            Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodo para agregar usuarios
    public void AgregarUsuario() {
        
//        
//        int clave = Integer.parseInt(txt.getText());
//        String nombre  = txtNombre.getText().toUpperCase();
//        String telefono = txtDuracion.getText().toUpperCase();
//        String correo = txtPrecio.getText().toUpperCase();
//        String tipo = txtPrecio.getText().toUpperCase();
//        String perfil = txtPerfil.getText();
//        
//        File imagen = new File(txtRutaImagen.getText());
//        
//        
//  this.agregar(clave, nombre, correo, telefono, perfil, tipo, imagen);
//  clearData();
//        t.visualizar_ProductoVO(tabla);
        
        
        
        
        
        
        try {
            SQL = "INSERT INTO cursos (idCurso, Nombre, Duracion, Precio, Vacantes, Fecha_inicio, Fecha_final, Disponibilidad) VALUES (?,?,?,?,?,?,?,?)";

            ps = con.prepareStatement(SQL);

            ps.setString(1, txtClave.getText());
            ps.setString(2, txtNombre.getText());
            ps.setString(3, txtDuracion.getText());
            ps.setString(4, txtPrecio.getText());
            ps.setString(5, txtVacante.getText());
            ps.setString(6, txtF_Inicial.getText());
            ps.setString(7, txtF_Final.getText());

            if (txtActivo.isSelected()) {
                ps.setString(8, txtActivo.getText());
            }
            if (txtDesactivo.isSelected()) {
                ps.setString(8, txtDesactivo.getText());
            }
            //ps.setString(8, txtAgregar_PrimerasEntradas.getText());

            int res = ps.executeUpdate();
            if (res > 0) {

                //JOptionPane.showMessageDialog(null,"SE AGREGO EL CURSO");
                visualizar();
                Limpiar();
                SDAgregar_Curso.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "NO SE AGREGO EL CURSO");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //METODO PARA SELECCIONAR UNA FILAANTES DE EDITAR##########################################################3
    public void SeleccionarEditar() {
        int FilaSelect = TablaCursos.getSelectedRow();
        if (FilaSelect >= 0) {
            idCurso.setText(TablaCursos.getValueAt(FilaSelect, 0).toString());
            txtEdit_Nombre.setText(TablaCursos.getValueAt(FilaSelect, 1).toString());
            txtEdit_Duracion.setText(TablaCursos.getValueAt(FilaSelect, 2).toString());
            txtEdit_Precio.setText(TablaCursos.getValueAt(FilaSelect, 3).toString());
            txtEdit_Vacantes.setText(TablaCursos.getValueAt(FilaSelect, 4).toString());
            String checar = (TablaCursos.getValueAt(FilaSelect, 5).toString());

            if ("Habilitado".equals(checar)) {
                Check1.setSelected(true);
            }
            if ("Deshabilitado".equals(checar)) {
                Check2.setSelected(true);
            }

            SDEditar_Curso.setVisible(true);
            SDEditar_Curso.setSize(545, 440);
            SDEditar_Curso.setModal(true);
            SDEditar_Curso.setLocationRelativeTo(null);

        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONA EL CURSO QUE QUIERES MODIFICAR");
        }
    }

    public void SeleccionarAgregarProf() {
        int FilaSelect = TablaCursos.getSelectedRow();
        if (FilaSelect >= 0) {
            txtNombreCurso.setText(TablaCursos.getValueAt(FilaSelect, 1).toString());
            SDAsig_Prof.setVisible(true);
            SDAsig_Prof.setSize(545, 550);
            SDAsig_Prof.setModal(true);
            SDAsig_Prof.setLocationRelativeTo(null);
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONA EL CURSO QUE DESEA ASIGNAR");
        }
    }

    //METODO PARA  EDITAR UN CURSO EN LA BASE DE DATOS##########################################################################################################
    public void EditarCurso() {
        try {
            SQL = "UPDATE cursos SET Nombre=?, Duracion=?, Precio=?, Vacantes=?, Disponibilidad=? WHERE idCurso=? ";
            ps = con.prepareStatement(SQL);

            ps.setString(1, txtEdit_Nombre.getText());
            ps.setString(2, txtEdit_Duracion.getText());
            ps.setString(3, txtEdit_Precio.getText());
            ps.setString(4, txtEdit_Vacantes.getText());

            if (Check1.isSelected()) {
                ps.setString(5, Check1.getText());
            }
            if (Check2.isSelected()) {
                ps.setString(5, Check2.getText());
            }
            ps.setString(6, idCurso.getText());

            int res = ps.executeUpdate();
            if (res > 0) {
                Limpiar();
                visualizar();
                SDEditar_Curso.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "VERIFICA QUE LA CLAVE DEL CURSO ESTE CORRECTO");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cursos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Limpiar() {
        txtClave.setText("");

        txtNombre.setText("");
        txtDuracion.setText("");
        txtPrecio.setText("");
        txtVacante.setText("");
        txtF_Inicial.setText("");
        txtF_Final.setText("");

        txtEdit_Nombre.setText("");
        txtEdit_Duracion.setText("");
        txtEdit_Precio.setText("");
        txtEdit_Vacantes.setText("");
        Check1.setSelected(false);
        Check2.setSelected(false);
        txtActivo.setSelected(false);
        txtDesactivo.setSelected(false);
    }

    //METODO PARA FILTRAR EN LA TABLA DE  SOW DIALOG##########################################################################################################
    public void Buscar() {
        int Columna = 1;

        Buscar.setRowFilter(RowFilter.regexFilter(txtBuscarCurso.getText(), Columna));
    }


    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        SDAgregar_Curso.setVisible(true);
        SDAgregar_Curso.setSize(545, 660);
        SDAgregar_Curso.setModal(true);
        SDAgregar_Curso.setLocationRelativeTo(null);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        SDAgregar_Curso.setVisible(true);
        SDAgregar_Curso.setSize(545, 660);
        SDAgregar_Curso.setModal(true);
        SDAgregar_Curso.setLocationRelativeTo(null);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        SeleccionarEditar();

    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        SeleccionarEditar();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void Btn_Agregar_CursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Agregar_CursoActionPerformed
        AgregarUsuario();

    }//GEN-LAST:event_Btn_Agregar_CursoActionPerformed

    private void Btn_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_EditarActionPerformed
        EditarCurso();
    }//GEN-LAST:event_Btn_EditarActionPerformed

    private void Check1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Check1MouseClicked
        if (Check1.isSelected()) {
            Check2.setSelected(false);
        }
    }//GEN-LAST:event_Check1MouseClicked

    private void Check2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Check2MouseClicked
        if (Check2.isSelected()) {
            Check1.setSelected(false);
        }
    }//GEN-LAST:event_Check2MouseClicked

    private void txtActivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtActivoMouseClicked
        if (txtActivo.isSelected()) {
            txtDesactivo.setSelected(false);
        }
    }//GEN-LAST:event_txtActivoMouseClicked

    private void txtDesactivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDesactivoMouseClicked
        if (txtDesactivo.isSelected()) {
            txtActivo.setSelected(false);
        }
    }//GEN-LAST:event_txtDesactivoMouseClicked

    private void btnAsignarProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarProfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAsignarProfActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        visualizarProf();
        SeleccionarAgregarProf();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        visualizarProf();
        SeleccionarAgregarProf();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void txtBuscarCursoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCursoKeyTyped
        txtBuscarCurso.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {

                String cadena = (txtBuscarCurso.getText());
                txtBuscarCurso.setText(cadena);
                Buscar();
            }

        });

        Buscar = new TableRowSorter(TablaCursos.getModel());
        TablaCursos.setRowSorter(Buscar);
    }//GEN-LAST:event_txtBuscarCursoKeyTyped

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cursos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Agregar_Curso;
    private javax.swing.JButton Btn_Editar;
    private javax.swing.JCheckBox Check1;
    private javax.swing.JCheckBox Check2;
    private javax.swing.JDialog SDAgregar_Curso;
    private javax.swing.JDialog SDAsig_Prof;
    private javax.swing.JDialog SDEditar_Curso;
    private javax.swing.JTable TablaCursos;
    private javax.swing.JTable TablaProfesores;
    private javax.swing.JButton btnAsignarProf;
    private javax.swing.JLabel idCurso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JCheckBox txtActivo;
    private javax.swing.JTextField txtBuscarCurso;
    private javax.swing.JTextField txtBuscarProf;
    private javax.swing.JTextField txtClave;
    private javax.swing.JCheckBox txtDesactivo;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtEdit_Duracion;
    private javax.swing.JTextField txtEdit_Nombre;
    private javax.swing.JTextField txtEdit_Precio;
    private javax.swing.JTextField txtEdit_Vacantes;
    private javax.swing.JTextField txtF_Final;
    private javax.swing.JTextField txtF_Inicial;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel txtNombreCurso;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtVacante;
    // End of variables declaration//GEN-END:variables
}
