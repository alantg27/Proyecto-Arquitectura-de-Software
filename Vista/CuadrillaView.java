package proyectof.View;
import java.awt.Color;
import java.sql.Connection;
import javax.swing.JOptionPane;
import proyectof.Conn.CuadrillaConn;
import proyectof.Model.ConexionBD;
public class CuadrillaView extends javax.swing.JFrame {

    public CuadrillaView() {
        initComponents();
        
        // Configuración del placeholder para TXT_ID
    TXT_ID.setText("Sólo para consultas"); // Texto inicial como placeholder
    TXT_ID.setForeground(Color.GRAY); // Color del texto del placeholder

    TXT_ID.addFocusListener(new java.awt.event.FocusListener() {
        @Override
        public void focusGained(java.awt.event.FocusEvent e) {
            if (TXT_ID.getText().equals("Sólo para consultas")) {
                TXT_ID.setText(""); // Borra el placeholder al ganar el foco
                TXT_ID.setForeground(Color.BLACK); // Cambia el color del texto
            }
        }

        @Override
        public void focusLost(java.awt.event.FocusEvent e) {
            if (TXT_ID.getText().isEmpty()) {
                TXT_ID.setText("Sólo para consultas"); // Restaura el placeholder si el campo está vacío
                TXT_ID.setForeground(Color.GRAY); // Cambia el color del texto al original
            }
        }
    });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        LBL_Persona = new javax.swing.JLabel();
        BTN_Volver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TXT_ID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BTN_Consultar = new javax.swing.JButton();
        BTN_Insertar = new javax.swing.JButton();
        BTN_Actualizar = new javax.swing.JButton();
        BTN_Eliminar = new javax.swing.JButton();
        CB_Jefe = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        TBL_Miembros = new javax.swing.JTable();
        CB_NuevoM = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        BTN_AgregarM = new javax.swing.JButton();
        BTN_BorrarM = new javax.swing.JButton();
        BTN_RefrescarJ = new javax.swing.JButton();
        BTN_RefrescarJ1 = new javax.swing.JButton();
        BTN_RefrescarJ2 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LBL_Persona.setText("Menú Cuadrillas");

        BTN_Volver.setText("Volver");
        BTN_Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VolverActionPerformed(evt);
            }
        });

        jLabel1.setText("ID:");

        jLabel2.setText("Jefe:");

        jLabel3.setText("Miembros:");

        BTN_Consultar.setText("Consultar");
        BTN_Consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ConsultarActionPerformed(evt);
            }
        });

        BTN_Insertar.setText("Crear Cuadrilla");
        BTN_Insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_InsertarActionPerformed(evt);
            }
        });

        BTN_Actualizar.setText("Actualizar");
        BTN_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ActualizarActionPerformed(evt);
            }
        });

        BTN_Eliminar.setText("Eliminar");
        BTN_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_EliminarActionPerformed(evt);
            }
        });

        CB_Jefe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_JefeActionPerformed(evt);
            }
        });

        TBL_Miembros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(TBL_Miembros);
        if (TBL_Miembros.getColumnModel().getColumnCount() > 0) {
            TBL_Miembros.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            TBL_Miembros.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            TBL_Miembros.getColumnModel().getColumn(2).setHeaderValue("Title 3");
            TBL_Miembros.getColumnModel().getColumn(3).setHeaderValue("Title 4");
        }

        jLabel4.setText("Agregar miembro:");

        BTN_AgregarM.setText("Agregar a cuadrilla");
        BTN_AgregarM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AgregarMActionPerformed(evt);
            }
        });

        BTN_BorrarM.setText("Eliminar Miembro");
        BTN_BorrarM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_BorrarMActionPerformed(evt);
            }
        });

        BTN_RefrescarJ.setText("Refrescar");
        BTN_RefrescarJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_RefrescarJActionPerformed(evt);
            }
        });

        BTN_RefrescarJ1.setText("Refrescar");
        BTN_RefrescarJ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_RefrescarJ1ActionPerformed(evt);
            }
        });

        BTN_RefrescarJ2.setText("Refrescar");
        BTN_RefrescarJ2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_RefrescarJ2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(220, 220, 220)
                                            .addComponent(BTN_BorrarM)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(BTN_RefrescarJ2))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(TXT_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BTN_Eliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BTN_Actualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BTN_Insertar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                    .addComponent(BTN_Consultar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BTN_Volver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CB_Jefe, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CB_NuevoM, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN_RefrescarJ)
                            .addComponent(BTN_RefrescarJ1))
                        .addGap(18, 18, 18)
                        .addComponent(BTN_AgregarM))))
            .addGroup(layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(LBL_Persona)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LBL_Persona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTN_Consultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_Insertar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_Actualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_Eliminar)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TXT_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CB_Jefe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTN_RefrescarJ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(9, 9, 9)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CB_NuevoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_AgregarM))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_Volver)
                    .addComponent(BTN_RefrescarJ1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_BorrarM)
                    .addComponent(BTN_RefrescarJ2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VolverActionPerformed
        this.dispose();
    
    // Crear una instancia de la vista de administración
    AdminView adminView = new AdminView();
    
    // Hacer visible la ventana de administración
    adminView.setVisible(true);
    }//GEN-LAST:event_BTN_VolverActionPerformed

    private void BTN_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ConsultarActionPerformed
        String idCuadrilla = TXT_ID.getText().trim();
Connection conexion = ConexionBD.conectar();
        CuadrillaConn cuadrillaConn = new CuadrillaConn();
cuadrillaConn.consulta(TXT_ID, CB_Jefe);
cuadrillaConn.cargarMiembros(TBL_Miembros, conexion, idCuadrilla);
    }//GEN-LAST:event_BTN_ConsultarActionPerformed

    private void BTN_InsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_InsertarActionPerformed
        CuadrillaConn cuadrillaConn = new CuadrillaConn();
cuadrillaConn.crearCuadrillaDesdeJefe(CB_Jefe,TXT_ID);
    }//GEN-LAST:event_BTN_InsertarActionPerformed

    private void BTN_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ActualizarActionPerformed
        CuadrillaConn cuadrillaConn = new CuadrillaConn();
cuadrillaConn.actualizarCuadrillaDesdeJefe(CB_Jefe, TXT_ID);
    }//GEN-LAST:event_BTN_ActualizarActionPerformed

    private void BTN_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EliminarActionPerformed
        CuadrillaConn cuadrillaConn = new CuadrillaConn();
cuadrillaConn.eliminarCuadrilla(TXT_ID);
    }//GEN-LAST:event_BTN_EliminarActionPerformed

    private void BTN_AgregarMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AgregarMActionPerformed
        CuadrillaConn cuadrillaConn = new CuadrillaConn();
cuadrillaConn.agregarMiembro(TXT_ID, CB_NuevoM);
    }//GEN-LAST:event_BTN_AgregarMActionPerformed

    private void BTN_BorrarMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_BorrarMActionPerformed
        CuadrillaConn cuadrillaConn = new CuadrillaConn();
cuadrillaConn.eliminarMiembroSeleccionado(TBL_Miembros);
    }//GEN-LAST:event_BTN_BorrarMActionPerformed

    private void CB_JefeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_JefeActionPerformed
    }//GEN-LAST:event_CB_JefeActionPerformed

    private void BTN_RefrescarJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_RefrescarJActionPerformed
        Connection conexion = ConexionBD.conectar();
        CuadrillaConn cuadrillaConn = new CuadrillaConn();
cuadrillaConn.actualizarJefe(CB_Jefe, conexion);
    }//GEN-LAST:event_BTN_RefrescarJActionPerformed

    private void BTN_RefrescarJ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_RefrescarJ1ActionPerformed
        Connection conexion = ConexionBD.conectar();
        CuadrillaConn cuadrillaConn = new CuadrillaConn();
cuadrillaConn.actualizarNuevoM(CB_NuevoM, conexion);
    }//GEN-LAST:event_BTN_RefrescarJ1ActionPerformed

    private void BTN_RefrescarJ2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_RefrescarJ2ActionPerformed
        // Obtener el ID de la cuadrilla desde el campo de texto
String idCuadrilla = TXT_ID.getText().trim();
Connection conexion = ConexionBD.conectar();
// Verificar que el campo no esté vacío
if (!idCuadrilla.isEmpty()) {
    CuadrillaConn cuadrillaConn = new CuadrillaConn();
    cuadrillaConn.cargarMiembros(TBL_Miembros, conexion, idCuadrilla);
} else {
    JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID de cuadrilla válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
}
    }//GEN-LAST:event_BTN_RefrescarJ2ActionPerformed

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
            java.util.logging.Logger.getLogger(CuadrillaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CuadrillaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CuadrillaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CuadrillaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Connection conexion = ConexionBD.conectar();
                new CuadrillaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Actualizar;
    private javax.swing.JButton BTN_AgregarM;
    private javax.swing.JButton BTN_BorrarM;
    private javax.swing.JButton BTN_Consultar;
    private javax.swing.JButton BTN_Eliminar;
    private javax.swing.JButton BTN_Insertar;
    private javax.swing.JButton BTN_RefrescarJ;
    private javax.swing.JButton BTN_RefrescarJ1;
    private javax.swing.JButton BTN_RefrescarJ2;
    private javax.swing.JButton BTN_Volver;
    private javax.swing.JComboBox<String> CB_Jefe;
    private javax.swing.JComboBox<String> CB_NuevoM;
    private javax.swing.JLabel LBL_Persona;
    private javax.swing.JTable TBL_Miembros;
    private javax.swing.JTextField TXT_ID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
