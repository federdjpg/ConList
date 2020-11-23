package Tabla;

import DAO.ProductoDAO2;
import VO.ProductoVO2;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Tabla_ProductoVO2{

   ProductoDAO2 dao = null;
   
// public void visualizar_ProductoVO(JTable tabla){
// 
// 
// }
   

    public void visualizar_ProductoVO2(JTable tabla){
        
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        dt.addColumn("idProfesor");
        dt.addColumn("Nombre");
        dt.addColumn("Correo");
        dt.addColumn("Telefono");
        dt.addColumn("Perfil");
        dt.addColumn("Tipo");
        dt.addColumn("Foto");

        dao = new ProductoDAO2();
        ProductoVO2 vo = new ProductoVO2();
        ArrayList<ProductoVO2> list = dao.Listar_ProductoVO2();

        if(list.size() > 0){
            for(int i=0; i<list.size(); i++){
                Object fila[] = new Object[7];
                vo = list.get(i);
                fila[0] = vo.getidAlumno();
                fila[1] = vo.getNombre();
                fila[2] = vo.getCorreo();
                fila[3] = vo.getTelefono();
                fila[4] = vo.getPerfil();
                fila[5] = vo.getTipo();
                try{
                    byte[] bi = vo.getFoto();
                    BufferedImage image = null;
                    InputStream in = new ByteArrayInputStream(bi);
                    image = ImageIO.read(in);
                    ImageIcon imgi = new ImageIcon(image.getScaledInstance(60, 60, 0));
                    fila[6] = new JLabel(imgi);

                }catch(Exception ex){
                    fila[6] = new JLabel("No imagen");
                }
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(60);
        }
    }

}


