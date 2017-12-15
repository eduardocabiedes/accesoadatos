package topicos.view;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import topicos.model.ConexionBD;

public class FrmDisqueras extends javax.swing.JFrame {

    DefaultTableModel modeloTabla;
    ConexionBD bd;
    int selectedRow=-1;
    String selectedId ;
     private void limpiarCajas(){
     
     txt_nomDis.setText(null);
     txt_artista.setText(null);
     txt_album.setText(null);
     txt_anio.setText(null);
     txt_cancion.setText(null);
 
 
     
 }
    public void agregarDisquera(String nombre,String artista,String album,String fecha,String cancion){
        
       String stmtSQL = "insert into disqueras (nombre,artistas,album,fecha,cancion) values ('"+nombre+"','"+artista+"','"+album+"','"+fecha+"','"+cancion+"');";
             
        try {
            if(nombre.equals("") ||artista.equals("") || album.equals("") || fecha.equals("")|| cancion.equals("")){
            JOptionPane.showMessageDialog(this, "Datos Vacios");
            
            }else {
                
                            if ( bd.stat.execute(stmtSQL) ){
               JOptionPane.showMessageDialog(this, "Error en insercion de registro");
            }else {
                JOptionPane.showMessageDialog(this, "Registro Guardado");
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmDisqueras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void actualizarDisquera(String selectedId, String nombre,String artista,String album,String fecha,String cancion) {
       String stmtSQL = "update disqueras set nombre = '"+nombre+"', artistas = '"+artista+"', album = '"+album+"',fecha = '"+fecha+"',cancion = '"+cancion+"'  where idDisquera = '"+selectedId+"'";
               
        try {
            if(nombre.equals("") ||artista.equals("") || album.equals("") || fecha.equals("")|| cancion.equals("")){
            JOptionPane.showMessageDialog(this, "Datos Vacios");
            
            }else {
             
                if ( bd.stat.executeUpdate(stmtSQL) ==0){
               JOptionPane.showMessageDialog(this, "Error en actualizacion de registro");
            }else {
                JOptionPane.showMessageDialog(this, "Registro Actualizado");
            }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FrmDisqueras.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.selectedRow = -1;
        this.cargarDisqueras();
    }    

    private void eliminarDisquera(String selectedId) {
       String stmtSQL = "delete from disqueras where idDisquera = "+selectedId+";";
               
        try {
            if ( bd.stat.execute(stmtSQL) ){
               JOptionPane.showMessageDialog(this, "Error en eliminacion de registro");
            } else {
                 JOptionPane.showMessageDialog(this, "Registro Eliminado");
                this.selectedRow = -1;
                this.cargarDisqueras();                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmDisqueras.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void cargarDisqueras(){
        modeloTabla = (DefaultTableModel) this.Lista.getModel();
        
        modeloTabla.setRowCount(0);  // Eliminamos toda la informacion del JTable
        try {
        
            this.bd.res =  bd.stat.executeQuery("select * from disqueras");
            
            while (bd.res.next()){
                int idDisquera = bd.res.getInt("idDisquera");
                String nombre = bd.res.getString("nombre");
             String artista = bd.res.getString("artistas");
                String album = bd.res.getString("album");
                 String fecha = bd.res.getString("fecha");
                  String cancion = bd.res.getString("cancion");
                Object[] obj = {idDisquera, nombre,artista,album,fecha,cancion};
                
                modeloTabla.addRow(obj);
                              
            }                                    
             JOptionPane.showMessageDialog(this, "Registros Actualizados");
        } catch (SQLException ex) {
            Logger.getLogger(FrmDisqueras.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
    
    public void filtrarDisqueras(String _nombre){
        modeloTabla = (DefaultTableModel) this.Lista.getModel();
        
        modeloTabla.setRowCount(0);  // Eliminamos toda la informacion del JTable
        try {
        
            this.bd.res =  bd.stat.executeQuery("select * from Disqueras where nombre like '%"+_nombre.trim()+"%'");
            
            while (bd.res.next()){
                int idDisquera = bd.res.getInt("idDisquera");
                String nombre = bd.res.getString("nombre");
                String artista = bd.res.getString("artistas");
                String album = bd.res.getString("album");
                String fecha = bd.res.getString("fecha");
                String cancion = bd.res.getString("cancion");
                Object[] obj = {idDisquera, nombre,artista,album, fecha, cancion};
                
                modeloTabla.addRow(obj);
                              
            }                                    
            
        } catch (SQLException ex) {
            Logger.getLogger(FrmDisqueras.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
    
 
    public FrmDisqueras() {
        this.bd = new ConexionBD();   
        this.bd.conectar();
        initComponents();
        this.cargarDisqueras();
        
        this.Lista.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
            selectedRow = Lista.getSelectedRow();
            
            if (selectedRow>=0){
                selectedId = Lista.getValueAt(selectedRow, 0).toString();
                txt_nomDis.setText(Lista.getValueAt(selectedRow, 1).toString());            
            }
        }
    });
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_nomDis = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        btn_Filtrar = new javax.swing.JButton();
        btn_Agregar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_Actualizar = new javax.swing.JButton();
        btn_lim = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Lista = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txt_artista = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_album = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_anio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_cancion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 102));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(txt_nomDis, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 200, 30));

        jToolBar1.setFloatable(false);

        btn_Filtrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_Filtrar.setText("Filtrar");
        btn_Filtrar.setFocusable(false);
        btn_Filtrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_Filtrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_Filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FiltrarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_Filtrar);

        btn_Agregar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_Agregar.setText("Agregar");
        btn_Agregar.setFocusable(false);
        btn_Agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_Agregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_Agregar);

        btn_guardar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setFocusable(false);
        btn_guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_guardar);

        btn_eliminar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setFocusable(false);
        btn_eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_eliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_eliminar);

        btn_Actualizar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_Actualizar.setText("Actualizar");
        btn_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ActualizarActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_Actualizar);

        btn_lim.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_lim.setText("Limpiar");
        btn_lim.setFocusable(false);
        btn_lim.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_lim.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_lim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_lim);

        getContentPane().add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, 40));

        Lista.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        Lista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Artista", "Álbum", "Año", "Cancion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Lista.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(Lista);
        Lista.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (Lista.getColumnModel().getColumnCount() > 0) {
            Lista.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 610, 390));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel2.setText("Nombre de Disquera:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 270, -1));
        getContentPane().add(txt_artista, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 340, 30));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setText("Álbum:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 140, 40));

        txt_album.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_albumActionPerformed(evt);
            }
        });
        getContentPane().add(txt_album, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 350, 30));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel4.setText("Año:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 60, -1));
        getContentPane().add(txt_anio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 380, 30));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel5.setText("Canción:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 110, -1));
        getContentPane().add(txt_cancion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 330, 30));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel7.setText("Artista:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 100, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActualizarActionPerformed
     
        this.cargarDisqueras();
    }//GEN-LAST:event_btn_ActualizarActionPerformed

    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed
   
        this.agregarDisquera(this.txt_nomDis.getText(),this.txt_artista.getText(),this.txt_album.getText(),this.txt_anio.getText(),this.txt_cancion.getText());    
        this.cargarDisqueras();
    }//GEN-LAST:event_btn_AgregarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
     
        if (this.Lista.getSelectedRow()>=0){
            this.eliminarDisquera(this.selectedId);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un registro");
        }     
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_FiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FiltrarActionPerformed
     
        this.filtrarDisqueras(this.txt_nomDis.getText()); 
        
    }//GEN-LAST:event_btn_FiltrarActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed

        if (this.Lista.getSelectedRow()>=0){
            this.actualizarDisquera(this.selectedId,this.txt_nomDis.getText(),this.txt_artista.getText(),this.txt_album.getText(),this.txt_anio.getText(),this.txt_cancion.getText());
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un registro");
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_limActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limActionPerformed
      limpiarCajas();
    }//GEN-LAST:event_btn_limActionPerformed

    private void txt_albumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_albumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_albumActionPerformed

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDisqueras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Lista;
    private javax.swing.JButton btn_Actualizar;
    private javax.swing.JButton btn_Agregar;
    private javax.swing.JButton btn_Filtrar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_lim;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField txt_album;
    private javax.swing.JTextField txt_anio;
    private javax.swing.JTextField txt_artista;
    private javax.swing.JTextField txt_cancion;
    private javax.swing.JTextField txt_nomDis;
    // End of variables declaration//GEN-END:variables

}
