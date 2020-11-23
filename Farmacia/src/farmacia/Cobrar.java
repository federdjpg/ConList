/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia;

import MySql.conexion;
import com.placeholder.PlaceHolder;
import static farmacia.Login.DESEDE_ENCRYPTION_SCHEME;
import static farmacia.Login.UNICODE_FORMAT;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.Timer;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.apache.commons.codec.binary.Base64;



/**
 *
 * @author Osvaldo
 */


public class Cobrar extends javax.swing.JFrame {

    /**
     * Creates new form Cobrar
     */
   
    
    
    conexion cn = new conexion();
    Connection con = cn.getConnection();
    
    DefaultTableModel model;
     private TableRowSorter Buscar;
     
     PreparedStatement ps;
    ResultSet rs;
    String SQL;
    TableRowSorter trs;
    double total = 0.0, descuento=0.0, resul=0.0;
                double cambio=0.0,sub_total =0.0, efectivo=0.0;
     SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
     
       private java.text.DecimalFormat formato =
                              new java.text.DecimalFormat("0.00"); 
       
  
          
    public Cobrar() throws UnsupportedEncodingException  {
       
            initComponents();
            txtCobrar_Clave.requestFocus();
            SDbuscar.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            visualizarSD();
            btnCobrar_cobrar.setEnabled(false);
            btnRetirar.setEnabled(false);
            btnRetirarr.setEnabled(false);
            
            Font fuente = new Font("Times New Roman", 0, 30);
            PlaceHolder holder = new PlaceHolder(txtCobrar_Descuento, "0");
            txtCobrar_Descuento.setFont(fuente);
            txtCobrar_Descuento.setForeground(Color.BLUE);
            
            model = (DefaultTableModel)TablaCobrar.getModel();
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            VerFolio();
            //verCajero();
            //FECHA DEL SISTEMA
            Date sistFecha=new Date();
            fecha.setText(formatofecha.format(sistFecha));
            //HORA DEL SISTEMA
            Timer tiemp=new Timer(100, new Cobrar.horas());
            tiemp.start();
            txtCobrar_Total.setText("0.0");
            
            txtCobrar_Subtotal.setText("0.0");
            txtCobrar_Efectivo.setText("0");
            txtCobrar_Cambio.setText("0.0");
            
     
    }

    
    private void PlaceHolder(JTextField txtCobrar_Descuento, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 // INICIO DE METODOS A UTILIZAR EN COBRAR******************************************************************************************************************************
  
    class horas implements ActionListener{
    
        public void actionPerformed(ActionEvent e){
            Date sistHora=new Date();
            String pmAm="hh:mm:ss a";
            SimpleDateFormat format=new SimpleDateFormat(pmAm);
            Calendar hoy=Calendar.getInstance();
            hora.setText(String.format(format.format(sistHora),hoy));         
        }   
} 
   //METODO PARA IMPIRMIR TICKET#############################################################################################3
       public PageFormat getPageFormat(PrinterJob pj)
{
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double middleHeight =80.0;  
    double headerHeight = 2.0;                  
    double footerHeight = 2.0;                  
    double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
    double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(                    
        0,
        10,
        width,            
        height - convert_CM_To_PPI(1)
    );   //define boarder size    after that print area width is about 180 points
            
    pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
    pf.setPaper(paper);    

    return pf;
}
    
    protected static double convert_CM_To_PPI(double cm) {            
	        return toPPI(cm * 0.393600787);            
}
 
protected static double toPPI(double inch) {            
	        return inch * 72d;            
}
    //###################333
    public class BillPrintable implements Printable {
    
   
    
    
  public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
  throws PrinterException 
  {    
      
                
        
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    

            double width = pageFormat.getImageableWidth();                    
           
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 

            ////////// code by alqama//////////////

            FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
        //    int idLength=metrics.stringWidth("000000");
            //int idLength=metrics.stringWidth("00");
            int idLength=metrics.stringWidth("000");
            int amtLength=metrics.stringWidth("000000");
            int qtyLength=metrics.stringWidth("00000");
            int priceLength=metrics.stringWidth("000000");
            int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;

        //    int idPosition=0;
        //    int productPosition=idPosition + idLength + 2;
        //    int pricePosition=productPosition + prodLength +10;
        //    int qtyPosition=pricePosition + priceLength + 2;
        //    int amtPosition=qtyPosition + qtyLength + 2;
            
            int productPosition = 0;
            int discountPosition= prodLength+5;
            int pricePosition = discountPosition +idLength+10;
            int qtyPosition=pricePosition + priceLength + 4;
            int amtPosition=qtyPosition + qtyLength;
            
            
              
        try{
            /*Draw Header*/
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
            int headerRectHeighta=40;
            
            ///////////////// Product names Get ///////////
                
            ///////////////// Product names Get ///////////
                
            
            ///////////////// Product price Get ///////////
               
            ///////////////// Product price Get ///////////
                
             g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
            g2d.drawString("-------------------------------------",12,y);y+=yShift;
            g2d.drawString("   Grupo Pharma Medic",12,y);y+=yShift;
          
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString("Calle tucanes #257",10,y);y+=yShift;
            g2d.drawString("entre callejon torcasas",10,y);y+=yShift;
            g2d.drawString("y calle codornices",10,y);y+=yShift;
            g2d.drawString("Col. Los Pajaros 29096",10,y);y+=yShift;
            g2d.drawString("Tuxtla Gtz, Chiapas",10,y);y+=yShift; 
            g2d.drawString("Telefono: 961-122-1277",10,y);y+=yShift;  
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString("No.Folio:"+txtCobrar_Folio.getText(),10,y);y+=yShift;
             g2d.drawString("Cajero:"+txtClave_Usuario.getText(),10,y);y+=yShift;
            g2d.drawString("Fecha:"+fecha.getText(),10,y);y+=yShift;          
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString("Cant. Pre.    Producto   ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
             for (int i = 0; i < TablaCobrar.getRowCount(); i++) {
             
             String pro=(" "+TablaCobrar.getValueAt(i, 6)+ "   "+TablaCobrar.getValueAt(i, 7)+                  
                       "  "+TablaCobrar.getValueAt(i, 1)
                       );
            
            g2d.drawString(""+pro,10,y);y+=yShift;
               }
            
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString("Subtotal:      $"+txtCobrar_Subtotal.getText(),10,y);y+=yShift;
            g2d.drawString("Descuento:     $"+txtCobrar_Descuento.getText(),10,y);y+=yShift;
            g2d.drawString("Total:         $"+txtCobrar_Total.getText(),10,y);y+=yShift;
            g2d.drawString("Pago en EFE:   $"+txtCobrar_Efectivo.getText(),10,y);y+=yShift;
            g2d.drawString("Cambio:        $"+txtCobrar_Cambio.getText(),10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString("Para garantizar la",10,y);y+=yShift;
            g2d.drawString("calidad de nuestros ",10,y);y+=yShift;
            g2d.drawString("productos no aceptamos",10,y);y+=yShift;
            g2d.drawString("devoluciones.",10,y);y+=yShift;         
            g2d.drawString("************************",10,y);y+=yShift;
            g2d.drawString(" GRACIAS POR SU COMPRA",10,y);y+=yShift;
            g2d.drawString("************************",10,y);y+=yShift;
                   
           
             
           
            
//            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
//            g2d.drawString("Customer Shopping Invoice", 30,y);y+=yShift; 
          

    }
    catch(Exception r){
    r.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
      }
    }
   //METODO PARA VISUALIZAR LA TABLA EN EL SOW DIALOG DE COBRAR##############################################################################################
   public void visualizarSD(){
        try {
            String [] titulos={"Clave", "Descripcion", "Gramos", "Contenido", "Tipo", "Precio Publico", "Stock"};
            String [] registros = new String[8];
            String SQL="SELECT id_producto,descripcion,gramos,contenido,tipo,precio_publico,stock FROM productos WHERE id_producto>10";
            model = new DefaultTableModel(null,titulos);
            Statement st =con.createStatement();
            ResultSet rs = st.executeQuery(SQL);          
            while(rs.next()){
                registros[0]=rs.getString("id_producto");
                registros[1]=rs.getString("descripcion");
                registros[2]=rs.getString("gramos");
                registros[3]=rs.getString("contenido");
                registros[4]=rs.getString("tipo");              
                registros[5]=rs.getString("precio_publico");
                registros[6]=rs.getString("stock");
                model.addRow(registros);  
            }
               
            TablaBuscarP.setModel(model);
         
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //METODO PARA FILTRAR EN LA TABLA DE  SOW DIALOG##########################################################################################################
   public void SDBuscar(){
        int Columna=1;
    
        Buscar.setRowFilter(RowFilter.regexFilter(txtSDbuscar.getText(),Columna));
    }
   
   //METODO PARA QUE LA PRIMER LETRA SEA MAYUSCULA#############################################################################################################
   
   public void Mayus(){
String mayuscula = txtSDbuscar.getText();
//String mayuscula1 = txtEdit_Nombre.getText();

//String mayuscula3 = txtAgregar_Nombre.getText();


       if(mayuscula.length()>0){
            char primer=mayuscula.charAt(0);
            mayuscula=Character.toUpperCase(primer)+mayuscula.substring(1,mayuscula.length());  
            txtSDbuscar.setText(mayuscula);   
//       } if(mayuscula1.length()>0){
//             char primer1=mayuscula1.charAt(0);
//             mayuscula1=Character.toUpperCase(primer1)+mayuscula1.substring(1,mayuscula1.length());
//             txtEdit_Nombre.setText(mayuscula1);        
//       }if(mayuscula3.length()>0){
//         char primer3=mayuscula3.charAt(0);
//             mayuscula3=Character.toUpperCase(primer3)+mayuscula3.substring(1,mayuscula3.length());
//             txtAgregar_Nombre.setText(mayuscula3);
        }
          
}

   
    //METODO PARA AGREGAR UN PRODUCTO A LA TABLA DE COBRAR######################################################################################
    
//    public void AgregarCobrar ()
//            {
//               
//          int fsel  = TablaBuscarP.getSelectedRow();
//        try {
//            String clave , descripcion,gramos,contenido, tipo, precio,cantidad,importe;
//            double calcula = 0, x= 0, igvs=0.0;
//           
//            int canti = 0 ;
//            if (fsel == -1){
//                txtSDbuscar_mensaje.setText("Selecciona un Producto");
//               
//            }else{
//  
//                model = (DefaultTableModel) TablaBuscarP.getModel();
//                clave = TablaBuscarP.getValueAt(fsel, 0).toString();
//                descripcion = TablaBuscarP.getValueAt(fsel, 1).toString();
//                gramos = TablaBuscarP.getValueAt(fsel, 2).toString();
//                contenido = TablaBuscarP.getValueAt(fsel, 3).toString();
//                tipo = TablaBuscarP.getValueAt(fsel, 4).toString();
//                precio = TablaBuscarP.getValueAt(fsel, 5).toString();
//            
//
//                x = (Double.parseDouble(precio) * Integer.parseInt(cantidad));
//                importe = String.valueOf(x);
//
//                 model = (DefaultTableModel) TablaCobrar.getModel();
//                 String filaelemento[] = {clave,descripcion,gramos,contenido,tipo,precio,cantidad,importe};
//                 model.addRow(filaelemento);
//                
//                
//                 
//                 calcula = (Double.parseDouble(precio) * Integer.parseInt(txtSDcantidad.getText()));
//                 sub_total= sub_total + calcula;
//              descuento = Double.parseDouble(txtCobrar_Descuento.getText());
//        
//                 total = sub_total - ((sub_total/100) *descuento );
//              
//                 txtCobrar_Subtotal.setText(""+sub_total);
//                 txtCobrar_Total.setText(""+total);
//               
//                 
//               txtSDbuscar_mensaje.setText("");
//               btnCobrar_Agregar.setEnabled(true);
//            }
//
//        } catch (Exception e) {
//        }
//
//    }
    
    //METODO PARA DAR CAMBIO EN LA VENTANA COBRAR ########################################################################################
     public void cambio()
    {
        efectivo = Double.parseDouble(txtCobrar_Efectivo.getText());
         total = Double.parseDouble(txtCobrar_Total.getText());

         if(efectivo>=total){
             
           
         resul = efectivo - total;    
              txtCobrar_Cambio.setText(""+ resul);
            
         
         }else{
         JOptionPane.showMessageDialog(null,"PAGO INSUFICIENTE","ADVERTENCIA!" ,JOptionPane.WARNING_MESSAGE);
         }
      
      
    }
     
     //METODO PARA HACER DESCUENTO EN LA VENTANA COBRAR#############################################################################
      public void Descuento()
    {

      if (txtCobrar_Efectivo!=null)
      {
        descuento = Double.parseDouble(txtCobrar_Descuento.getText());
        sub_total = Double.parseDouble(txtCobrar_Subtotal.getText());
        resul =  sub_total - ((sub_total/100) *descuento );
        txtCobrar_Total.setText(""+ resul);
                     
      }
    }
      
      //METODO PRA BUSCAR POR CLAVE EN LA VENTANA DE COBRAR
      public void bus (){

        try {
            
            SQL ="SELECT * FROM productos WHERE id_producto=?";

          ps=con.prepareStatement(SQL);
            
             ps.setString(1, txtCobrar_Clave.getText());
             rs=ps.executeQuery();
 
             if(rs.next()){
                   txtCobrar_Clave.setText(rs.getString("id_producto"));
                 txtCobrar_Descripcion.setText(rs.getString("descripcion"));
                 txtCobrar_Gramos.setText(rs.getString("gramos"));
                 txtCobrar_Contenido.setText(rs.getString("contenido"));
                 txtCobrar_Tipo.setText(rs.getString("tipo"));
                 txtCobrar_Precio.setText(rs.getString("precio_publico"));
                 txtCobrar_Cantidad.setText(""+1);
             }
             else
             {
                 JOptionPane.showMessageDialog(null,"LA CLAVE DEL PRODUCTO NO EXISTE","ADVERTENCIA!" ,JOptionPane.WARNING_MESSAGE);
             }
          
        } catch (SQLException ex) {
            Logger.getLogger(Cobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
     
      
      //METODO PARA AGREGAR PRODUCTOS A LA TABLA COBRAR DESDE LA VENTANA DE COBRAR###################################################
      
      Object[] o = new Object[8];
      
    public void agregarTabla(){
        
        int cantidad = Integer.parseInt(txtCobrar_Cantidad.getText());
        int precio = Integer.parseInt(txtCobrar_Precio.getText());
        double resultado = precio *cantidad;
        
        o[0]= txtCobrar_Clave.getText();
         o[1]= txtCobrar_Descripcion.getText();
          o[2]= txtCobrar_Gramos.getText();
           o[3]= txtCobrar_Contenido.getText();
            o[4]= txtCobrar_Tipo.getText();
             o[5]= txtCobrar_Precio.getText(); 
              o[6]= txtCobrar_Cantidad.getText(); 
               o[7]=  (resultado);
           model.addRow(o);
           
            String precios = txtCobrar_Precio.getText();
        String canti = txtCobrar_Cantidad.getText();
        double descuentos = Double.parseDouble(txtCobrar_Descuento.getText());
            double x = (Double.parseDouble(precios) * (Double.parseDouble(canti)));
       // String importe = String.valueOf(x);
        
        sub_total= Double.parseDouble(txtCobrar_Subtotal.getText()) +  x;
        total =  sub_total - ((sub_total/100) * descuentos );
              
                 txtCobrar_Subtotal.setText(""+sub_total);
                 txtCobrar_Total.setText(""+total);
                 txtCobrar_Clave.setText("");
                 txtCobrar_Descripcion.setText("");
                 txtCobrar_Gramos.setText("");
                 txtCobrar_Contenido.setText("");
                 txtCobrar_Tipo.setText("");
                 txtCobrar_Precio.setText(""); 
                 txtCobrar_Cantidad.setText("");
               
       
    }
    
    //METODO PARA ELIMINAR UN PRODUCTO DE LA TABLA COBRAR##############################################################################
    
    private void resta(){
     double  precioactual=0.0, sub_actual=0.0, importe = 0.0;
     int fsel,resp;
        try {
            fsel = TablaCobrar.getSelectedRow();
       
            if (fsel == -1){
                JOptionPane.showMessageDialog(null,"Debe seleccionar un producto a eliminar");
            }else{
                resp = JOptionPane.showConfirmDialog(null,"QUIERES ELIMINAR EL PRODUTO?","ADVERTENCIA",JOptionPane.YES_NO_OPTION);
                if (resp==JOptionPane.YES_OPTION){
                importe = Double.parseDouble(TablaCobrar.getValueAt(fsel, 7).toString());
                precioactual = Double.parseDouble(txtCobrar_Subtotal.getText())- importe;
                 double descuentos = Double.parseDouble(txtCobrar_Descuento.getText());
                sub_total = precioactual;
                total =  sub_total - ((sub_total/100) * descuentos );
                  txtCobrar_Subtotal.setText(""+sub_total);
                 txtCobrar_Total.setText(""+total);
                 txtCobrar_Clave.setText("");
                 txtCobrar_Descripcion.setText("");
                 txtCobrar_Gramos.setText("");
                 txtCobrar_Contenido.setText("");
                 txtCobrar_Tipo.setText("");
                 txtCobrar_Precio.setText(""); 
                 txtCobrar_Cantidad.setText("");
                model =(DefaultTableModel)TablaCobrar.getModel();
                model.removeRow(fsel);
                } 
                                    
                 
            }

        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"No se realizo la elimancion correcta","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //METODO PARA VISUALIZAR LA VENTANA DE RETIRO##############################################################################################
    
    public void ClaveRetiroView(){
        txtSDcantidad_retiro.setText("0");
            SDretirar.setVisible(true);
       SDretirar.setSize(385, 320);
       SDretirar.setModal(true);
       SDretirar.setLocationRelativeTo(null);  
    }
    
    //METODO PARA INSERTAR UN NUEVO REGISTRO DE RETIRO###################################################################################################
    
    public void InsertarRetiro(){
     try {
    String pasofecha  = "";                
                    Date sistFecha=new Date();
                    pasofecha = (formatofecha.format(sistFecha));
                   
            SQL="INSERT INTO historial_farmacia (folio,id_producto,fecha,usuario,cantidad,metodo_pago,descuento,total,motivo) VALUES (?,?,?,?,?,?,?,?,?) ";
            ps = con.prepareStatement(SQL);
            
            ps.setString(1, txtCobrar_Folio.getText());
            ps.setString(2, "1");
            ps.setString(3, pasofecha);
            ps.setString(4, txtClave_Usuario.getText());
            ps.setString(5, "0");
            ps.setString(6, "RETIRO");
            ps.setString(7, "0");        
            Double total1 = Double.parseDouble(txtSDcantidad_retiro.getText())* -1;
            ps.setString(8, ""+total1);
            ps.setString(9, txtSDcantidad_Motivo.getText());

            
            
            int res = ps.executeUpdate();
                if(res>0){
                    LimpiarJtext();
                     SDretirar.dispose();
                     JOptionPane.showMessageDialog(null,"EL RETIRO DE DINERO SE REGISTRO CORRECTAMENTE");
                     
                    
                    
                }else{
                JOptionPane.showMessageDialog(null,"NO SE PUDO REALIZAR EL PROCESO DE RETIRO","ADVERTENCIA!" ,JOptionPane.WARNING_MESSAGE);
                }
            } 
        
        catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    //METODO PARA INSERTAR EL SALDO DISPONIBLE EN CAJA#######################################################################################
    
    
     public void InsertarCaja(){
    try {
        
   String pasofecha  = "";                
                    Date sistFecha=new Date();
                    pasofecha = (formatofecha.format(sistFecha));
            SQL="INSERT INTO historial_farmacia (folio,id_producto,fecha,usuario,cantidad,metodo_pago,descuento,total,motivo) VALUES (?,?,?,?,?,?,?,?,?) ";
            ps = con.prepareStatement(SQL);
              Double caja = Double.parseDouble(txtSDcaja_Cantidad.getText());
            ps.setString(1, "0");
            ps.setString(2, "2");
            ps.setString(3, pasofecha);
            ps.setString(4, txtClave_Usuario.getText());
            ps.setString(5, "0");
            ps.setString(6, "SALDO EN CAJA");
            ps.setString(7, "0");        
            ps.setString(8, ""+caja);
            ps.setString(9, txtSDcaja_descripcion.getText());


            
            
            int res = ps.executeUpdate();
                if(res>0){
                      
                     LimpiarJtext();
                     SDcaja.dispose();
                    
                    btnCobrar_cobrar.setEnabled(true);
           btnRetirar.setEnabled(true);
           btnRetirarr.setEnabled(true);
                     JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO, PUEDES EMPEZAR A COBRAR");
                    
                }else{
                JOptionPane.showMessageDialog(null,"NO SE PUDO REALIZAR EL PROCESO DE RETIRO","ADVERTENCIA!" ,JOptionPane.WARNING_MESSAGE);
                }
            } 
        
        catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    //METODO PARA LIMPIAR LOS JTEXTFIELD#############################################################################################################
    
    public void LimpiarJtext(){
       txtSDcantidad_retiro.setText("");
       txtSDcaja_Cantidad.setText("");
       txtSDcaja_mensaje.setText("");
       txtSDretirar_mensaje.setText("");
    }
    
    
    //METODO PARA EL FOLIO###################################################################################################################################################
    
    public void VerFolio(){
    try {
            
            
             String SQL="";
            SQL="SELECT Max(folio)+1 as folio FROM historial_farmacia ";
            Statement st =con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            
           while(rs.next()){
                txtCobrar_Folio.setText(rs.getString("folio"));
              
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //METODO PARA GUARDAR EN HISTORIAL##################################################################################################################################
    
      public void GuardarHistorial(){
        int cont = TablaCobrar.getRowCount();
        String pasofecha  = "";
            for(int i = 0; i<cont; i++)
            {       
            try {
                Date sistFecha=new Date();
                pasofecha = (formatofecha.format(sistFecha));
     
                SQL="INSERT INTO historial_farmacia (folio,id_producto,fecha,usuario,cantidad,metodo_pago,descuento,total) VALUES (?,?,?,?,?,?,?,?) ";
                ps = con.prepareStatement(SQL);
                
                ps.setString(1, txtCobrar_Folio.getText());
                ps.setString(2, TablaCobrar.getValueAt(i, 0).toString());
                ps.setString(3, pasofecha);
                ps.setString(4, txtClave_Usuario.getText());
                ps.setString(5, TablaCobrar.getValueAt(i, 6).toString());
                ps.setString(6, "Efectivo");
                ps.setString(7, txtCobrar_Descuento.getText());
                ps.setString(8, TablaCobrar.getValueAt(i, 7).toString());
                ps.executeUpdate();  
                
            }
            catch (SQLException ex) {
                Logger.getLogger(Cobrar.class.getName()).log(Level.SEVERE, null, ex);
            }
     
            }           
      }     
            
            //METODO PARA MODIFICAR EN INVENTARIO############################################################################################################################
              public void ModificarInventario(){
                  int cont = TablaCobrar.getRowCount();
                  for(int i = 0; i<cont; i++)
                    {   
                        try {
                                SQL="UPDATE productos SET stock=stock-? WHERE id_producto=? ";
                                ps = con.prepareStatement(SQL);
                                ps.setString(1, TablaCobrar.getValueAt(i, 6).toString());
                                ps.setString(2, TablaCobrar.getValueAt(i, 0).toString());
                                int res = ps.executeUpdate();
                            } 
                        catch (SQLException ex) 
                        {
                          Logger.getLogger(Cobrar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
              }
         //METODO PARA LIMIPAR Y REALIZAR OTRA COMPRA###########################################################################################################################################
          
              public void LimpiarCobro(){
               DefaultTableModel tb = (DefaultTableModel) TablaCobrar.getModel();
                int a = TablaCobrar.getRowCount()-1;
                for (int i = a; i >= 0; i--) {          
                tb.removeRow(tb.getRowCount()-1);
                }
                 txtCobrar_Clave.setText("");
                 txtCobrar_Descripcion.setText("");
                 txtCobrar_Gramos.setText("");
                 txtCobrar_Contenido.setText("");
                 txtCobrar_Tipo.setText("");
                 txtCobrar_Precio.setText(""); 
                 txtCobrar_Cantidad.setText("");
              txtCobrar_Total.setText(""+0.0);
              txtCobrar_Descuento.setText(""+0);
              txtCobrar_Subtotal.setText(""+0.0);
              txtCobrar_Efectivo.setText(""+0);
              txtCobrar_Cambio.setText(""+0.0);
              sub_total=0;
              }
              
 //METODO PARA ENTRAR A HISTORIAL DESDE LA VENTANA COBRAR#########################################################################################################################################
     
    public void LoginHistorial()
    {
        try {
            SQL="UPDATE productos SET stock=stock-? WHERE id_producto=? ";
            ps = con.prepareStatement(SQL);
            ps.setString(1, "");
            ps.setString(2, "");
            int res = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Cobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  //METODO PARA LOGIN DESDE COBRAR###########################################################################################################
 
//FIN DE METODOS EN LA VENTANA COBRAR*****************************************************************************************************************************
            

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SDbuscar = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtSDbuscar = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtSDbuscar_mensaje = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaBuscarP = new javax.swing.JTable();
        SDretirar = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtSDcantidad_retiro = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        btnSDretiro = new javax.swing.JButton();
        txtSDretirar_mensaje = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtSDcantidad_Motivo = new javax.swing.JTextField();
        SDlogin_corte = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtSDlogin_historial_usuario = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        txtSDlogin_historial_contra = new javax.swing.JPasswordField();
        SDcaja = new javax.swing.JDialog();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        txtSDcaja_Cantidad = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        btnSDcaja = new javax.swing.JButton();
        txtSDcaja_mensaje = new javax.swing.JLabel();
        txtSDcaja_ingCaja = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtSDcaja_descripcion = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        txtClave_Usuario = new javax.swing.JLabel();
        hora = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JLabel();
        btnCerrar_sesion = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtCobrar_buscar = new javax.swing.JLabel();
        txtCobrar_Clave = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCobrar_Descripcion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCobrar_Gramos = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCobrar_Contenido = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCobrar_Tipo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCobrar_Cantidad = new javax.swing.JTextField();
        btnCobrar_Agregar = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtCobrar_Precio = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JLabel();
        btnEliminarProducto = new javax.swing.JLabel();
        btnRetirar = new javax.swing.JLabel();
        btnRetirarr = new javax.swing.JLabel();
        btnCaja = new javax.swing.JLabel();
        btnCajaa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCobrar = new javax.swing.JTable();
        txtCobrar_Subtotal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCobrar_Descuento = new javax.swing.JTextField();
        btnEliminar4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCobrar_Efectivo = new javax.swing.JTextField();
        txtCobrar_Total = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtCobrar_Cambio = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnCobrar_cobrar = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        txtCobrar_Folio = new javax.swing.JLabel();

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(0, 0, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        txtSDbuscar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSDbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSDbuscarKeyTyped(evt);
            }
        });

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/btnBuscarPro.png"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Nombre del Producto");

        txtSDbuscar_mensaje.setBackground(new java.awt.Color(255, 0, 0));
        txtSDbuscar_mensaje.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtSDbuscar_mensaje.setForeground(new java.awt.Color(255, 204, 204));
        txtSDbuscar_mensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSDbuscar_mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
                        .addGap(264, 264, 264))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtSDbuscar)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtSDbuscar_mensaje))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSDbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        TablaBuscarP.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TablaBuscarP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Clave", "Descripcion", "Gramos", "Contenido", "Tipo", "Precio", "Existencia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaBuscarP.setRowHeight(30);
        jScrollPane2.setViewportView(TablaBuscarP);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1297, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SDbuscarLayout = new javax.swing.GroupLayout(SDbuscar.getContentPane());
        SDbuscar.getContentPane().setLayout(SDbuscarLayout);
        SDbuscarLayout.setHorizontalGroup(
            SDbuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SDbuscarLayout.setVerticalGroup(
            SDbuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Retirar Efectivo de Caja");

        txtSDcantidad_retiro.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSDcantidad_retiro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSDcantidad_retiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDcantidad_retiroActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel22.setText("Cantidad del Retiro:");

        btnSDretiro.setBackground(new java.awt.Color(0, 0, 255));
        btnSDretiro.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSDretiro.setForeground(new java.awt.Color(255, 255, 255));
        btnSDretiro.setText("Confirmar");
        btnSDretiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSDretiroActionPerformed(evt);
            }
        });

        txtSDretirar_mensaje.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtSDretirar_mensaje.setForeground(new java.awt.Color(255, 0, 0));
        txtSDretirar_mensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel23.setText("Motivo de Retiro:");

        txtSDcantidad_Motivo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSDcantidad_Motivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtSDretirar_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel22)
                                .addComponent(txtSDcantidad_retiro, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                .addComponent(btnSDretiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtSDcantidad_Motivo))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(txtSDretirar_mensaje)
                .addGap(7, 7, 7)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSDcantidad_retiro, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSDcantidad_Motivo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(btnSDretiro, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout SDretirarLayout = new javax.swing.GroupLayout(SDretirar.getContentPane());
        SDretirar.getContentPane().setLayout(SDretirarLayout);
        SDretirarLayout.setHorizontalGroup(
            SDretirarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SDretirarLayout.setVerticalGroup(
            SDretirarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("ADMINISTRADOR");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel27.setText("Contraseña:");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel28.setText("Usuario: ");

        txtSDlogin_historial_usuario.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jButton3.setBackground(new java.awt.Color(0, 0, 255));
        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("INICIAR SESION");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(txtSDlogin_historial_usuario)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                            .addComponent(txtSDlogin_historial_contra))
                        .addContainerGap())))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSDlogin_historial_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSDlogin_historial_contra, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout SDlogin_corteLayout = new javax.swing.GroupLayout(SDlogin_corte.getContentPane());
        SDlogin_corte.getContentPane().setLayout(SDlogin_corteLayout);
        SDlogin_corteLayout.setHorizontalGroup(
            SDlogin_corteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SDlogin_corteLayout.setVerticalGroup(
            SDlogin_corteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Ingresar Caja");

        txtSDcaja_Cantidad.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSDcaja_Cantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSDcaja_Cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDcaja_CantidadKeyReleased(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel31.setText("Cantidad de Ingreso:");

        btnSDcaja.setBackground(new java.awt.Color(0, 0, 255));
        btnSDcaja.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSDcaja.setForeground(new java.awt.Color(255, 255, 255));
        btnSDcaja.setText("Confirmar");
        btnSDcaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSDcajaActionPerformed(evt);
            }
        });

        txtSDcaja_mensaje.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtSDcaja_mensaje.setForeground(new java.awt.Color(255, 0, 0));
        txtSDcaja_mensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtSDcaja_ingCaja.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtSDcaja_ingCaja.setForeground(new java.awt.Color(0, 0, 255));
        txtSDcaja_ingCaja.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtSDcaja_ingCaja.setText("Seguir sin Confirmar");
        txtSDcaja_ingCaja.setAlignmentX(0.1F);
        txtSDcaja_ingCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSDcaja_ingCajaMouseClicked(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel32.setText("Descripcion:");

        txtSDcaja_descripcion.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSDcaja_descripcion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSDcaja_descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDcaja_descripcionKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDcaja_mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSDcaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSDcaja_ingCaja))
                            .addComponent(txtSDcaja_Cantidad)
                            .addComponent(txtSDcaja_descripcion, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(txtSDcaja_mensaje)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSDcaja_ingCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSDcaja_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSDcaja_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSDcaja, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout SDcajaLayout = new javax.swing.GroupLayout(SDcaja.getContentPane());
        SDcaja.getContentPane().setLayout(SDcajaLayout);
        SDcajaLayout.setHorizontalGroup(
            SDcajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SDcajaLayout.setVerticalGroup(
            SDcajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 255));
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Farmacia");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(210, 0, 1450, 42);

        fecha.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        fecha.setForeground(new java.awt.Color(255, 255, 255));
        fecha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fecha.setText("Fecha:");
        jPanel2.add(fecha);
        fecha.setBounds(670, 40, 240, 28);

        txtClave_Usuario.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtClave_Usuario.setForeground(new java.awt.Color(255, 255, 255));
        txtClave_Usuario.setText("Clave");
        jPanel2.add(txtClave_Usuario);
        txtClave_Usuario.setBounds(10, 40, 122, 28);

        hora.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        hora.setForeground(new java.awt.Color(255, 255, 255));
        hora.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hora.setText("Hora:");
        jPanel2.add(hora);
        hora.setBounds(960, 40, 260, 28);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("-");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(930, 40, 20, 28);

        btnCerrar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCerrar.setText("Cerrar Sesion");
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
        });
        jPanel2.add(btnCerrar);
        btnCerrar.setBounds(1790, 80, 100, 22);

        btnCerrar_sesion.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnCerrar_sesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar_sesion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnCerrar_sesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/btnCerrar_Sesion.png"))); // NOI18N
        btnCerrar_sesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrar_sesionMouseClicked(evt);
            }
        });
        jPanel2.add(btnCerrar_sesion);
        btnCerrar_sesion.setBounds(1820, 0, 70, 80);

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText(" Bienvenido ");
        jPanel2.add(jLabel24);
        jLabel24.setBounds(0, 0, 141, 28);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Administrador");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel2);
        jLabel2.setBounds(1660, 10, 130, 20);

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("____________");
        jPanel2.add(jLabel19);
        jLabel19.setBounds(1670, 10, 130, 20);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar produccto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        txtCobrar_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/btnBuscarPro.png"))); // NOI18N
        txtCobrar_buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCobrar_buscarMouseClicked(evt);
            }
        });

        txtCobrar_Clave.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCobrar_Clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCobrar_ClaveKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Clave de Producto:");

        txtCobrar_Descripcion.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Nombre del Producto");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Gramos:");

        txtCobrar_Gramos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Contenido:");

        txtCobrar_Contenido.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Tipo:");

        txtCobrar_Tipo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Cantidad:");

        txtCobrar_Cantidad.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnCobrar_Agregar.setBackground(new java.awt.Color(0, 0, 255));
        btnCobrar_Agregar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCobrar_Agregar.setForeground(new java.awt.Color(255, 255, 255));
        btnCobrar_Agregar.setText("Modificar");
        btnCobrar_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrar_AgregarActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("Precio:");

        txtCobrar_Precio.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtCobrar_buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtCobrar_Clave, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCobrar_Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtCobrar_Gramos, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtCobrar_Contenido, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtCobrar_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(txtCobrar_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtCobrar_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(btnCobrar_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCobrar_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCobrar_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(41, 41, 41)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCobrar_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCobrar_Descripcion))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCobrar_Gramos))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCobrar_Contenido))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCobrar_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCobrar_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(txtCobrar_Clave, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(23, 23, 23))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/btnEliminarProducto.png"))); // NOI18N
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });

        btnEliminarProducto.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnEliminarProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEliminarProducto.setText("Eliminar");
        btnEliminarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarProductoMouseClicked(evt);
            }
        });

        btnRetirar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/btnCobrar_Retiro.png"))); // NOI18N
        btnRetirar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRetirarMouseClicked(evt);
            }
        });

        btnRetirarr.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnRetirarr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnRetirarr.setText("Retirar");
        btnRetirarr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRetirarrMouseClicked(evt);
            }
        });

        btnCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/btnCobrar_Caja.png"))); // NOI18N
        btnCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCajaMouseClicked(evt);
            }
        });

        btnCajaa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnCajaa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCajaa.setText("Caja");
        btnCajaa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCajaaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRetirarr)
                    .addComponent(btnRetirar))
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCajaa, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnCaja, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCajaa, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnRetirar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRetirarr, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        TablaCobrar.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TablaCobrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Descripcion", "Gramos", "Contenido", "Tipo", "Precio", "Cantidad", "Importe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaCobrar.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TablaCobrar.setRowHeight(40);
        TablaCobrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaCobrarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaCobrar);
        if (TablaCobrar.getColumnModel().getColumnCount() > 0) {
            TablaCobrar.getColumnModel().getColumn(0).setPreferredWidth(190);
            TablaCobrar.getColumnModel().getColumn(1).setPreferredWidth(450);
            TablaCobrar.getColumnModel().getColumn(2).setPreferredWidth(140);
            TablaCobrar.getColumnModel().getColumn(3).setPreferredWidth(140);
            TablaCobrar.getColumnModel().getColumn(4).setPreferredWidth(140);
            TablaCobrar.getColumnModel().getColumn(5).setPreferredWidth(100);
            TablaCobrar.getColumnModel().getColumn(6).setPreferredWidth(100);
            TablaCobrar.getColumnModel().getColumn(7).setPreferredWidth(100);
        }

        txtCobrar_Subtotal.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtCobrar_Subtotal.setForeground(new java.awt.Color(51, 204, 0));
        txtCobrar_Subtotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Subtotal");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Descuento");

        txtCobrar_Descuento.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtCobrar_Descuento.setForeground(new java.awt.Color(0, 0, 255));
        txtCobrar_Descuento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCobrar_Descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCobrar_DescuentoActionPerformed(evt);
            }
        });
        txtCobrar_Descuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCobrar_DescuentoKeyReleased(evt);
            }
        });

        btnEliminar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icono_Porcentaje.png"))); // NOI18N
        btnEliminar4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminar4MouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Total");

        txtCobrar_Efectivo.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtCobrar_Efectivo.setForeground(new java.awt.Color(0, 0, 255));
        txtCobrar_Efectivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCobrar_Efectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCobrar_EfectivoKeyReleased(evt);
            }
        });

        txtCobrar_Total.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtCobrar_Total.setForeground(new java.awt.Color(51, 204, 0));
        txtCobrar_Total.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Recibo en Efectivo");

        txtCobrar_Cambio.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtCobrar_Cambio.setForeground(new java.awt.Color(51, 204, 0));
        txtCobrar_Cambio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCobrar_Cambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCobrar_CambioActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Cambio");

        btnCobrar_cobrar.setBackground(new java.awt.Color(51, 204, 0));
        btnCobrar_cobrar.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        btnCobrar_cobrar.setText("Cobrar");
        btnCobrar_cobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrar_cobrarActionPerformed(evt);
            }
        });

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "No. Folio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        txtCobrar_Folio.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        txtCobrar_Folio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCobrar_Folio.setText("1");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 186, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                    .addContainerGap(57, Short.MAX_VALUE)
                    .addComponent(txtCobrar_Folio, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(58, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                    .addContainerGap(30, Short.MAX_VALUE)
                    .addComponent(txtCobrar_Folio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(30, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(txtCobrar_Subtotal))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCobrar_Descuento)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                        .addGap(4, 4, 4)
                        .addComponent(btnEliminar4)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(txtCobrar_Total))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCobrar_Efectivo)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCobrar_Cambio, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnCobrar_cobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(486, 486, 486))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCobrar_Subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCobrar_Descuento, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCobrar_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCobrar_Efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCobrar_Cambio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCobrar_cobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(51, 51, 51))
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

    private void btnCobrar_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrar_AgregarActionPerformed
         SDbuscar.dispose();
        int fsel2 = this.TablaCobrar.getSelectedRow();
    if(fsel2 == -1 )
    {
          agregarTabla();   
    }else {
          
             double  precioactual=0.0, sub_actual=0.0, importe = 0.0;
       importe = Double.parseDouble(TablaCobrar.getValueAt(fsel2, 7).toString());
                precioactual = Double.parseDouble(txtCobrar_Subtotal.getText())- importe;
                 double descuentos = Double.parseDouble(txtCobrar_Descuento.getText());
                sub_total = precioactual;
                total =  sub_total - ((sub_total/100) * descuentos );
                  txtCobrar_Subtotal.setText(""+sub_total);
                 txtCobrar_Total.setText(""+total);
                 
                model =(DefaultTableModel)TablaCobrar.getModel();
                model.removeRow(fsel2);
                  agregarTabla();
           
              
    }        
        
    }//GEN-LAST:event_btnCobrar_AgregarActionPerformed

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
              // TODO add your handling code here:
              resta();
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnEliminarProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarProductoMouseClicked
                // TODO add your handling code here:
                resta();
    }//GEN-LAST:event_btnEliminarProductoMouseClicked

    private void btnRetirarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRetirarMouseClicked
        // TODO add your handling code here:
        ClaveRetiroView();
       
    }//GEN-LAST:event_btnRetirarMouseClicked

    private void btnRetirarrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRetirarrMouseClicked
        // TODO add your handling code here:
        ClaveRetiroView();
    }//GEN-LAST:event_btnRetirarrMouseClicked

    private void btnCajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCajaMouseClicked
        // TODO add your handling code here:
        txtSDcaja_Cantidad.setText("0");
        SDcaja.setVisible(true);
       SDcaja.setSize(385, 320);
       SDcaja.setModal(true);
       SDcaja.setLocationRelativeTo(null);  
    }//GEN-LAST:event_btnCajaMouseClicked

    private void btnCajaaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCajaaMouseClicked
        // TODO add your handling code here:
        txtSDcaja_Cantidad.setText("0");
         SDcaja.setVisible(true);
       SDcaja.setSize(385, 235);
       SDcaja.setModal(true);
       SDcaja.setLocationRelativeTo(null);  
        
    }//GEN-LAST:event_btnCajaaMouseClicked

    private void btnEliminar4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminar4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminar4MouseClicked

    private void txtCobrar_CambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCobrar_CambioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCobrar_CambioActionPerformed

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
    
    }//GEN-LAST:event_jLabel15MouseClicked

    private void txtCobrar_buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCobrar_buscarMouseClicked
        // TODO add your handling code here:
       // visualizarSD();
         SDbuscar.setVisible(true);
        SDbuscar.setSize(1200, 688);
        SDbuscar.setModal(true);
        
        SDbuscar.setLocationRelativeTo(null);

    }//GEN-LAST:event_txtCobrar_buscarMouseClicked

    private void txtSDbuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDbuscarKeyTyped
        // TODO add your handling code here:
        txtSDbuscar.addKeyListener(new KeyAdapter() {
           public void keyReleased (final KeyEvent e){  
               String cadena =  (txtSDbuscar.getText());
               txtSDbuscar.setText(cadena);
               SDBuscar();
               Mayus();
           }
    
});
       Buscar = new TableRowSorter(TablaBuscarP.getModel());
       TablaBuscarP.setRowSorter(Buscar);
    }//GEN-LAST:event_txtSDbuscarKeyTyped

    private void txtCobrar_EfectivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCobrar_EfectivoKeyReleased
        // TODO add your handling code here:
          char tecla = evt.getKeyChar();
       if (tecla  == KeyEvent.VK_ENTER){
       
       cambio();
        }
         
       
        
              
    }//GEN-LAST:event_txtCobrar_EfectivoKeyReleased

    private void txtCobrar_DescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCobrar_DescuentoKeyReleased
        // TODO add your handling code here:
        char tecla = evt.getKeyChar();
       if (tecla  == KeyEvent.VK_ENTER){
       
       Descuento();
        }
        Font fuente = new Font("Times New Roman", 0, 30);
        PlaceHolder holder = new PlaceHolder(txtCobrar_Descuento, "0");
        txtCobrar_Descuento.setFont(fuente); 
        txtCobrar_Descuento.setForeground(Color.BLUE);
        
    }//GEN-LAST:event_txtCobrar_DescuentoKeyReleased

    private void txtCobrar_ClaveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCobrar_ClaveKeyReleased
        // TODO add your handling code here:
        char tecla = evt.getKeyChar();
       if (tecla  == KeyEvent.VK_ENTER){
       bus();
      SDbuscar.dispose();
        int fsel2 = this.TablaCobrar.getSelectedRow();
    if(fsel2 == -1 )
    {
          agregarTabla();   
    }else {
          
             double  precioactual=0.0, sub_actual=0.0, importe = 0.0;
       importe = Double.parseDouble(TablaCobrar.getValueAt(fsel2, 7).toString());
                precioactual = Double.parseDouble(txtCobrar_Subtotal.getText())- importe;
                 double descuentos = Double.parseDouble(txtCobrar_Descuento.getText());
                sub_total = precioactual;
                total =  sub_total - ((sub_total/100) * descuentos );
                  txtCobrar_Subtotal.setText(""+sub_total);
                 txtCobrar_Total.setText(""+total);
                 
                model =(DefaultTableModel)TablaCobrar.getModel();
                model.removeRow(fsel2);
                  agregarTabla();
           
              
    }        
        }
    }//GEN-LAST:event_txtCobrar_ClaveKeyReleased

    private void txtCobrar_DescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCobrar_DescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCobrar_DescuentoActionPerformed

    private void TablaCobrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaCobrarMouseClicked
        // TODO add your handling code here:
        int FilaSelect = this.TablaCobrar.getSelectedRow();
            txtCobrar_Clave.setText(TablaCobrar.getValueAt(FilaSelect, 0).toString());
            txtCobrar_Descripcion.setText(TablaCobrar.getValueAt(FilaSelect, 1).toString());
            txtCobrar_Gramos.setText(TablaCobrar.getValueAt(FilaSelect, 2).toString());
            txtCobrar_Contenido.setText(TablaCobrar.getValueAt(FilaSelect, 3).toString());
            txtCobrar_Tipo.setText(TablaCobrar.getValueAt(FilaSelect, 4).toString());
            txtCobrar_Precio.setText(TablaCobrar.getValueAt(FilaSelect, 5).toString());
            txtCobrar_Cantidad.setText(TablaCobrar.getValueAt(FilaSelect, 6).toString());
            
    }//GEN-LAST:event_TablaCobrarMouseClicked

    private void btnSDretiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSDretiroActionPerformed
        // TODO add your handling code here:
          Double retiro = Double.parseDouble(txtSDcantidad_retiro.getText());
        
          if(retiro == 0){
         
          txtSDretirar_mensaje.setText("Ingrese una Cantidad");
          }
          else{
           InsertarRetiro();
                      VerFolio();

          }
        
        
    }//GEN-LAST:event_btnSDretiroActionPerformed

    private void btnCerrar_sesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrar_sesionMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnCerrar_sesionMouseClicked

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        // TODO add your handling code here:
         System.exit(0);
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void btnSDcajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSDcajaActionPerformed
          // TODO add your handling code here:
         
         Double caja = Double.parseDouble(txtSDcaja_Cantidad.getText());
          if(caja > 0){
              
          InsertarCaja();
                                VerFolio();

          }
          else{
            //   txtSDcaja_mensaje1.setText("Ingrese Una Cantidad");
           txtSDcaja_mensaje.setText("Ingrese una Cantidad");
          
          }
          
           
    }//GEN-LAST:event_btnSDcajaActionPerformed

    private void btnCobrar_cobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrar_cobrarActionPerformed
        // TODO add your handling code here
        total= Double.parseDouble(txtCobrar_Total.getText());
        sub_total= Double.parseDouble(txtCobrar_Subtotal.getText());
         efectivo= Double.parseDouble(txtCobrar_Efectivo.getText());
         cambio= Double.parseDouble(txtCobrar_Cambio.getText());
        
         
        if(total >0 && sub_total >0 && efectivo >= total && cambio >= 0){
        GuardarHistorial();
        ModificarInventario();
        PrinterJob pj = PrinterJob.getPrinterJob();        
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
             pj.print();
          
        }
         catch (PrinterException ex) {
                 ex.printStackTrace();
        }
        LimpiarCobro();
        VerFolio();
        
        
       
        JOptionPane.showMessageDialog(null,"COBRO EXITOSO");
        
       
        }
       else  {
        JOptionPane.showMessageDialog(null,"VERIFICA QUE NO EXISTA UN CAMPO VACIO O QUE TU CAMBIO ESTE CORRECTAMENTE");
        }
       txtCobrar_Clave.requestFocus();
    }//GEN-LAST:event_btnCobrar_cobrarActionPerformed

    private void txtSDcaja_CantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDcaja_CantidadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDcaja_CantidadKeyReleased

    private void txtSDcaja_ingCajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSDcaja_ingCajaMouseClicked
        // TODO add your handling code here:
        btnCobrar_cobrar.setEnabled(true);
           btnRetirar.setEnabled(true);
           btnRetirarr.setEnabled(true);
           SDcaja.dispose();
    }//GEN-LAST:event_txtSDcaja_ingCajaMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        try {
            Login ir =new Login();
            ir.setVisible(true);
            this.dispose();
// TODO add your handling code here:
        } catch (Exception ex) {
            Logger.getLogger(Cobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txtSDcantidad_retiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDcantidad_retiroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDcantidad_retiroActionPerformed

    private void txtSDcaja_descripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDcaja_descripcionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDcaja_descripcionKeyReleased

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
            java.util.logging.Logger.getLogger(Cobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Cobrar().setVisible(true);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Cobrar.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog SDbuscar;
    private javax.swing.JDialog SDcaja;
    private javax.swing.JDialog SDlogin_corte;
    private javax.swing.JDialog SDretirar;
    private javax.swing.JTable TablaBuscarP;
    private javax.swing.JTable TablaCobrar;
    private javax.swing.JLabel btnCaja;
    private javax.swing.JLabel btnCajaa;
    private javax.swing.JLabel btnCerrar;
    private javax.swing.JLabel btnCerrar_sesion;
    private javax.swing.JButton btnCobrar_Agregar;
    private javax.swing.JButton btnCobrar_cobrar;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JLabel btnEliminar4;
    private javax.swing.JLabel btnEliminarProducto;
    private javax.swing.JLabel btnRetirar;
    private javax.swing.JLabel btnRetirarr;
    private javax.swing.JButton btnSDcaja;
    private javax.swing.JButton btnSDretiro;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel hora;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
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
    public javax.swing.JLabel txtClave_Usuario;
    private javax.swing.JTextField txtCobrar_Cambio;
    private javax.swing.JTextField txtCobrar_Cantidad;
    private javax.swing.JTextField txtCobrar_Clave;
    private javax.swing.JTextField txtCobrar_Contenido;
    private javax.swing.JTextField txtCobrar_Descripcion;
    private javax.swing.JTextField txtCobrar_Descuento;
    private javax.swing.JTextField txtCobrar_Efectivo;
    private javax.swing.JLabel txtCobrar_Folio;
    private javax.swing.JTextField txtCobrar_Gramos;
    private javax.swing.JTextField txtCobrar_Precio;
    private javax.swing.JTextField txtCobrar_Subtotal;
    private javax.swing.JTextField txtCobrar_Tipo;
    private javax.swing.JTextField txtCobrar_Total;
    private javax.swing.JLabel txtCobrar_buscar;
    private javax.swing.JTextField txtSDbuscar;
    private javax.swing.JLabel txtSDbuscar_mensaje;
    private javax.swing.JTextField txtSDcaja_Cantidad;
    private javax.swing.JTextField txtSDcaja_descripcion;
    private javax.swing.JLabel txtSDcaja_ingCaja;
    private javax.swing.JLabel txtSDcaja_mensaje;
    private javax.swing.JTextField txtSDcantidad_Motivo;
    private javax.swing.JTextField txtSDcantidad_retiro;
    private javax.swing.JPasswordField txtSDlogin_historial_contra;
    private javax.swing.JTextField txtSDlogin_historial_usuario;
    private javax.swing.JLabel txtSDretirar_mensaje;
    // End of variables declaration//GEN-END:variables
}
