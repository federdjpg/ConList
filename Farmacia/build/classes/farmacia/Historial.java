/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import MySql.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Osvaldo
 */
public class Historial extends javax.swing.JFrame {

    conexion cn = new conexion();
    Connection con = cn.getConnection();
    PreparedStatement ps,ps1;
    ResultSet rs, rs1;
    String SQL, SQL1;
    DefaultTableModel model;
     SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Creates new form Historial
     */
    public Historial() {
        initComponents();
        visualizarHistorial();
         this.setExtendedState(JFrame.MAXIMIZED_BOTH);
          }
//ACA INICIAN LO METODOS UTILIZADOS EN LA CLASE HISTORIAL**************************************************************************************************

    
    //METODO PARA VISUALIZAR LA TABLA HISTORIAL#########################################################################################
    
        public void visualizarHistorial(){
        try {
            String [] titulos={"No. Folio", "Clave del Producto","Descripcion", "Gramos", "Contenido", "Tipo", "Precio Publico","Cantidad","Metodo de Pago","Desuento","Total","Fecha","Usuario"};
            String [] registros = new String[13];
            String SQL="SELECT h.folio,h.id_producto,p.descripcion,p.gramos,p.contenido,p.tipo,p.precio_publico,h.cantidad,h.metodo_pago,h.descuento,sum(h.total) as total,h.fecha, h.usuario FROM historial_farmacia h, productos p WHERE h.id_producto=p.id_producto and h.id_producto>10 group by h.folio";
            String SQL1 ="SELECT sum(total) as total from historial_farmacia where id_producto >100 ";           
            model = new DefaultTableModel(null,titulos);
            Statement st =con.createStatement();
            Statement st1 =con.createStatement();
            ResultSet rs = st.executeQuery(SQL);  
              ResultSet rs1 = st1.executeQuery(SQL1);
            
            while(rs.next()){
                registros[0]=rs.getString("h.folio");
                registros[1]=rs.getString("h.id_producto");
                registros[2]=rs.getString("p.descripcion");
                registros[3]=rs.getString("p.gramos");
                registros[4]=rs.getString("p.contenido");
                registros[5]=rs.getString("p.tipo");
                registros[6]=rs.getString("p.precio_publico");
                registros[7]=rs.getString("h.cantidad");
                 registros[8]=rs.getString("h.metodo_pago");
                registros[9]=rs.getString("h.descuento");
                registros[10]=rs.getString("total");
                registros[11]=rs.getString("h.fecha");
                registros[12]=rs.getString("h.usuario");
                
                model.addRow(registros);
                while(rs1.next()){
                 txtHistorial_total.setText("$"+rs1.getString("total")+".00");
                }
                
            }
           
               
            Tabla_Hitorial.setModel(model);
         
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        //METODO PARA VISUALIZAR LA VENTA POR DIA#############################################################################################3
          public void VentaDiaria(){
        try {
            Date date1 = txtV_dia_fecha1.getDate();
            long d1 = date1.getTime();
             java.sql.Date fecha1 = new  java.sql.Date(d1);
             
            Date date2 = txtV_dia_fecha2.getDate();
            long d2 = date2.getTime();
           
            java.sql.Date fecha2 = new  java.sql.Date(d2);
            
            
            String [] titulos={"No. Folio", "Clave del Producto","Descripcion", "Gramos", "Contenido", "Tipo", "Precio Publico","Cantidad","Metodo de Pago","Desuento","Total","Fecha","Usuario"};
            String [] registros = new String[13];
            String SQL="SELECT h.folio,h.id_producto,p.descripcion,p.gramos,p.contenido,p.tipo,p.precio_publico,h.cantidad,h.metodo_pago,h.descuento,sum(h.total) as total,h.fecha, h.usuario FROM historial_farmacia h, productos p WHERE p.id_producto>10 AND h.id_producto=p.id_producto AND h.fecha between '"+fecha1+"' AND'"+fecha2+"' group by h.folio ";
            String SQL1 ="SELECT sum(total) as total from historial_farmacia where id_producto >100 AND fecha between '"+fecha1+"' AND'"+fecha2+"' ";           
            model = new DefaultTableModel(null,titulos);
            Statement st =con.createStatement();
            Statement st1 =con.createStatement();
            ResultSet rs = st.executeQuery(SQL);  
              ResultSet rs1 = st1.executeQuery(SQL1);
            
            while(rs.next()){
                registros[0]=rs.getString("h.folio");
                registros[1]=rs.getString("h.id_producto");
                registros[2]=rs.getString("p.descripcion");
                registros[3]=rs.getString("p.gramos");
                registros[4]=rs.getString("p.contenido");
                registros[5]=rs.getString("p.tipo");
                registros[6]=rs.getString("p.precio_publico");
                registros[7]=rs.getString("h.cantidad");
                 registros[8]=rs.getString("h.metodo_pago");
                registros[9]=rs.getString("h.descuento");
                registros[10]=rs.getString("total");
                registros[11]=rs.getString("h.fecha");
                registros[12]=rs.getString("h.usuario");
                
                model.addRow(registros);
                while(rs1.next()){
               txtV_Dia.setText("$"+rs1.getString("total")+".00");
                }
                
            }
           
               
            Tabla_Hitorial.setModel(model);
         
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          //METODO PARA HACER EL CORTE DEL CAJERO###############################################################################
          public void CorteCajero(){
          try{
              Date date1 = txtC_cajero_fecha1.getDate();
            long d1 = date1.getTime();
             java.sql.Date fecha1 = new  java.sql.Date(d1);
             
            Date date2 = txtC_cajero_fecha2.getDate();
            long d2 = date2.getTime();
            java.sql.Date fecha2 = new  java.sql.Date(d2);
            
            
            String [] titulos={"No. Folio", "Clave del Producto","Descripcion", "Gramos", "Contenido", "Tipo", "Precio Publico","Cantidad","Metodo de Pago","Desuento","Total","Fecha","Usuario"};
            String [] registros = new String[13];
            String SQL="SELECT h.folio,h.id_producto,p.descripcion,p.gramos,p.contenido,p.tipo,p.precio_publico,h.cantidad,h.metodo_pago,h.descuento,sum(h.total) as total,h.fecha, h.usuario FROM historial_farmacia h, productos p WHERE h.id_producto=p.id_producto AND h.fecha between '"+fecha1+"' AND'"+fecha2+"' group by h.folio ";
            String SQL1 ="SELECT sum(total) as total from historial_farmacia where usuario='"+txtHistorial_Usuario.getText()+"' and fecha between '"+fecha1+"' AND'"+fecha2+"' ";           
            model = new DefaultTableModel(null,titulos);
            Statement st =con.createStatement();
            Statement st1 =con.createStatement();
            ResultSet rs = st.executeQuery(SQL);  
              ResultSet rs1 = st1.executeQuery(SQL1);
            
            while(rs.next()){
                registros[0]=rs.getString("h.folio");
                registros[1]=rs.getString("h.id_producto");
                registros[2]=rs.getString("p.descripcion");
                registros[3]=rs.getString("p.gramos");
                registros[4]=rs.getString("p.contenido");
                registros[5]=rs.getString("p.tipo");
                registros[6]=rs.getString("p.precio_publico");
                registros[7]=rs.getString("h.cantidad");
                 registros[8]=rs.getString("h.metodo_pago");
                registros[9]=rs.getString("h.descuento");
                registros[10]=rs.getString("total");
                registros[11]=rs.getString("h.fecha");
                registros[12]=rs.getString("h.usuario");
                
                model.addRow(registros);
                while(rs1.next()){
               txtCorte_cajero_total.setText("$"+rs1.getString("total")+".00");
                }
                
            }
           
               
            Tabla_Hitorial.setModel(model);
         
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
          }
          
   //METODO PARA VER LA VENTA POR NUMERO DE FOLIO####################################################################################3
  public void VentaFolio(){
    try{
             
            
            
            String [] titulos={"No. Folio", "Clave del Producto","Descripcion", "Gramos", "Contenido", "Tipo", "Precio Publico","Cantidad","Metodo de Pago","Desuento","Total","Fecha","Usuario"};
            String [] registros = new String[13];
            String SQL="SELECT h.folio,h.id_producto,p.descripcion,p.gramos,p.contenido,p.tipo,p.precio_publico,h.cantidad,h.metodo_pago,h.descuento,h.total,h.fecha, h.usuario FROM historial_farmacia h, productos p WHERE h.id_producto=p.id_producto AND h.folio='"+txtHistorial_Folio.getText() +"' AND h.id_producto>10";
             model = new DefaultTableModel(null,titulos);
            Statement st =con.createStatement();
          
            ResultSet rs = st.executeQuery(SQL);  
            
            while(rs.next()){
                registros[0]=rs.getString("h.folio");
                registros[1]=rs.getString("h.id_producto");
                registros[2]=rs.getString("p.descripcion");
                registros[3]=rs.getString("p.gramos");
                registros[4]=rs.getString("p.contenido");
                registros[5]=rs.getString("p.tipo");
                registros[6]=rs.getString("p.precio_publico");
                registros[7]=rs.getString("h.cantidad");
                 registros[8]=rs.getString("h.metodo_pago");
                registros[9]=rs.getString("h.descuento");
                registros[10]=rs.getString("h.total");
                registros[11]=rs.getString("h.fecha");
                registros[12]=rs.getString("h.usuario");
                
                model.addRow(registros);
                
            }
           
               
            Tabla_Hitorial.setModel(model);
         
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
  }        
  
  //METODO PARA VER LOS RETIROS HECHOS EN LA FARMACIA###############################################################################
  
    public void Retiros(){
    try{
             
             Date date1 = txtHistorial_Movimientos1.getDate();
            long d1 = date1.getTime();
             java.sql.Date fecha1 = new  java.sql.Date(d1);
             
            Date date2 = txtHistorial_Movimientos2.getDate();
            long d2 = date2.getTime();
            java.sql.Date fecha2 = new  java.sql.Date(d2);
            
            
            String [] titulos={"No. Folio", "Clave de Retiro","Nombre","Cantidad de Retiro","Fecha de Retiro","Usuario","Descripcion"};
            String [] registros = new String[7];
            String SQL="SELECT h.folio,h.id_producto,p.descripcion,h.total,h.fecha, h.usuario, h.motivo FROM historial_farmacia h, productos p WHERE h.id_producto=p.id_producto AND h.id_producto=1 AND fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"'";
             model = new DefaultTableModel(null,titulos);
            Statement st =con.createStatement();
          
            ResultSet rs = st.executeQuery(SQL);  
            
            while(rs.next()){
                registros[0]=rs.getString("h.folio");
                registros[1]=rs.getString("h.id_producto");
                registros[2]=rs.getString("p.descripcion");              
                registros[3]=rs.getString("h.total"); 
                registros[4]=rs.getString("h.fecha");
                registros[5]=rs.getString("h.usuario");
                 registros[6]=rs.getString("h.motivo");
                
                model.addRow(registros);
                
            }
           
               
            Tabla_Hitorial.setModel(model);
         
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
  }  
    
    
  //METODO PARA MOSTRAS LOS INGRESOS DE CAJA QUE HACEN DIARIO#############################################################
      public void IngrasosCaja(){
    try{
             
             Date date1 = txtHistorial_Movimientos1.getDate();
            long d1 = date1.getTime();
             java.sql.Date fecha1 = new  java.sql.Date(d1);
             
            Date date2 = txtHistorial_Movimientos2.getDate();
            long d2 = date2.getTime();
            java.sql.Date fecha2 = new  java.sql.Date(d2);
            
            
            String [] titulos={"No. Folio", "Clave","Nombre","Cantidad en Caja","Fecha de Ingreso","Usuario","Descripcion"};
            String [] registros = new String[7];
            String SQL="SELECT h.folio,h.id_producto,p.descripcion,h.total,h.fecha, h.usuario, h.motivo FROM historial_farmacia h, productos p WHERE h.id_producto=p.id_producto AND h.id_producto=2 AND fecha BETWEEN '"+fecha1+"' AND '"+fecha2+"'";
             model = new DefaultTableModel(null,titulos);
            Statement st =con.createStatement();
          
            ResultSet rs = st.executeQuery(SQL);  
            
            while(rs.next()){
                registros[0]=rs.getString("h.folio");
                registros[1]=rs.getString("h.id_producto");
                registros[2]=rs.getString("p.descripcion");              
                registros[3]=rs.getString("h.total"); 
                registros[4]=rs.getString("h.fecha");
                registros[5]=rs.getString("h.usuario");
                registros[6]=rs.getString("h.motivo");
                
                model.addRow(registros);
                
            }
           
               
            Tabla_Hitorial.setModel(model);
         
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
  }  
      
  //METODO PARA LIMPIAR################################################################################################################################
 
      public void Limp(){
      txtC_cajero_fecha1.setDate(null);
      txtC_cajero_fecha2.setDate(null);
      txtCorte_cajero_total.setText("");
      txtHistorial_Folio.setText("");
      txtHistorial_Movimientos1.setDate(null);
      txtHistorial_Movimientos2.setDate(null);
      txtHistorial_Usuario.setText("");
     
      txtV_Dia.setText("");
      txtV_dia_fecha1.setDate(null);
      txtV_dia_fecha2.setDate(null);
      }
//FIN DE LOS METODOS UTILIZADOS EN LA CLASE HISTORIAL*******************************************************************************************
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Hitorial = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnHistorial_cerrar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnHistorial_VentaDia = new javax.swing.JButton();
        txtV_dia_fecha1 = new com.toedter.calendar.JDateChooser();
        txtV_dia_fecha2 = new com.toedter.calendar.JDateChooser();
        txtV_Dia = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        txtHistorial_total = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtHistorial_Usuario = new javax.swing.JTextField();
        btnHistorial_Corte = new javax.swing.JButton();
        txtC_cajero_fecha1 = new com.toedter.calendar.JDateChooser();
        txtC_cajero_fecha2 = new com.toedter.calendar.JDateChooser();
        txtCorte_cajero_total = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtHistorial_Folio = new javax.swing.JTextField();
        btnHistorial_VerTicket = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtHistorial_Movimientos1 = new com.toedter.calendar.JDateChooser();
        txtHistorial_Movimientos2 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Tabla_Hitorial.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Tabla_Hitorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla_Hitorial.setRowHeight(40);
        jScrollPane1.setViewportView(Tabla_Hitorial);

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Historial de Ventas");

        btnHistorial_cerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnHistorial_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/btnCerrar_login.png"))); // NOI18N
        btnHistorial_cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHistorial_cerrarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1811, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHistorial_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHistorial_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Venta del Dia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        btnHistorial_VentaDia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnHistorial_VentaDia.setText("Visualizar");
        btnHistorial_VentaDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorial_VentaDiaActionPerformed(evt);
            }
        });

        txtV_dia_fecha1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtV_dia_fecha2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtV_Dia.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        txtV_Dia.setForeground(new java.awt.Color(51, 204, 0));
        txtV_Dia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtV_Dia, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnHistorial_VentaDia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(txtV_dia_fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtV_dia_fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(txtV_Dia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtV_dia_fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtV_dia_fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHistorial_VentaDia))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton3.setText("Ver Ventas");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtHistorial_total.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        txtHistorial_total.setForeground(new java.awt.Color(51, 204, 0));
        txtHistorial_total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtHistorial_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(txtHistorial_total, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Corte de cajero", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        txtHistorial_Usuario.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnHistorial_Corte.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnHistorial_Corte.setText("Corte");
        btnHistorial_Corte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorial_CorteActionPerformed(evt);
            }
        });

        txtC_cajero_fecha1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtC_cajero_fecha2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtCorte_cajero_total.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        txtCorte_cajero_total.setForeground(new java.awt.Color(51, 204, 0));
        txtCorte_cajero_total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Usuario:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtC_cajero_fecha1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                        .addComponent(txtC_cajero_fecha2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtHistorial_Usuario))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHistorial_Corte, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(txtCorte_cajero_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtC_cajero_fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtC_cajero_fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(txtCorte_cajero_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHistorial_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHistorial_Corte, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. Folio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        txtHistorial_Folio.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        txtHistorial_Folio.setForeground(new java.awt.Color(0, 0, 204));
        txtHistorial_Folio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHistorial_Folio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHistorial_FolioActionPerformed(evt);
            }
        });

        btnHistorial_VerTicket.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnHistorial_VerTicket.setText("Ver Venta");
        btnHistorial_VerTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorial_VerTicketActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHistorial_Folio)
                    .addComponent(btnHistorial_VerTicket, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(txtHistorial_Folio, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHistorial_VerTicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Movimientos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton1.setText("Retiros");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton2.setText("Caja");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(txtHistorial_Movimientos1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHistorial_Movimientos2, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHistorial_Movimientos1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHistorial_Movimientos2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtHistorial_FolioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHistorial_FolioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHistorial_FolioActionPerformed

    private void btnHistorial_cerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorial_cerrarMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHistorial_cerrarMouseClicked

    private void btnHistorial_VentaDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorial_VentaDiaActionPerformed
        // TODO add your handling code here:
        VentaDiaria();
      
    }//GEN-LAST:event_btnHistorial_VentaDiaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
visualizarHistorial();     
Limp();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnHistorial_CorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorial_CorteActionPerformed
        // TODO add your handling code here:
        CorteCajero();
    }//GEN-LAST:event_btnHistorial_CorteActionPerformed

    private void btnHistorial_VerTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorial_VerTicketActionPerformed
        // TODO add your handling code here:
      VentaFolio();
    }//GEN-LAST:event_btnHistorial_VerTicketActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Retiros();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
IngrasosCaja();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Historial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Historial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Historial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Historial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Historial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla_Hitorial;
    private javax.swing.JButton btnHistorial_Corte;
    private javax.swing.JButton btnHistorial_VentaDia;
    private javax.swing.JButton btnHistorial_VerTicket;
    private javax.swing.JLabel btnHistorial_cerrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser txtC_cajero_fecha1;
    private com.toedter.calendar.JDateChooser txtC_cajero_fecha2;
    private javax.swing.JLabel txtCorte_cajero_total;
    private javax.swing.JTextField txtHistorial_Folio;
    private com.toedter.calendar.JDateChooser txtHistorial_Movimientos1;
    private com.toedter.calendar.JDateChooser txtHistorial_Movimientos2;
    public javax.swing.JTextField txtHistorial_Usuario;
    private javax.swing.JLabel txtHistorial_total;
    private javax.swing.JLabel txtV_Dia;
    private com.toedter.calendar.JDateChooser txtV_dia_fecha1;
    private com.toedter.calendar.JDateChooser txtV_dia_fecha2;
    // End of variables declaration//GEN-END:variables
}
