package proyectof.View;
import java.awt.Color;
import proyectof.Conn.ColoniaConn;
public class ColoniaView extends javax.swing.JFrame {

    public ColoniaView() {
    initComponents(); // Inicialización generada por el diseñador de NetBeans
    
    // Configuración del placeholder para TXT_ID
    TXT_ID.setText("No llenar al insertar"); // Texto inicial como placeholder
    TXT_ID.setForeground(Color.GRAY); // Color del texto del placeholder

    TXT_ID.addFocusListener(new java.awt.event.FocusListener() {
        @Override
        public void focusGained(java.awt.event.FocusEvent e) {
            if (TXT_ID.getText().equals("No llenar al insertar")) {
                TXT_ID.setText(""); // Borra el placeholder al ganar el foco
                TXT_ID.setForeground(Color.BLACK); // Cambia el color del texto
            }
        }

        @Override
        public void focusLost(java.awt.event.FocusEvent e) {
            if (TXT_ID.getText().isEmpty()) {
                TXT_ID.setText("No llenar al insertar"); // Restaura el placeholder si el campo está vacío
                TXT_ID.setForeground(Color.GRAY); // Cambia el color del texto al original
            }
        }
    });
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LBL_Persona = new javax.swing.JLabel();
        BTN_Volver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TXT_ID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TXT_Código = new javax.swing.JTextField();
        TXT_Nombre = new javax.swing.JTextField();
        BTN_Consultar = new javax.swing.JButton();
        BTN_Insertar = new javax.swing.JButton();
        BTN_Actualizar = new javax.swing.JButton();
        BTN_Eliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LBL_Persona.setText("Menú Colonias");

        BTN_Volver.setText("Volver");
        BTN_Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VolverActionPerformed(evt);
            }
        });

        jLabel1.setText("ID:");

        jLabel7.setText("Código Postal:");

        jLabel2.setText("Nombre:");

        TXT_Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_NombreActionPerformed(evt);
            }
        });

        BTN_Consultar.setText("Consultar");
        BTN_Consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ConsultarActionPerformed(evt);
            }
        });

        BTN_Insertar.setText("Insertar");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(LBL_Persona)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(TXT_ID, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(TXT_Nombre)
                    .addComponent(TXT_Código))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BTN_Actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_Insertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_Consultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_Volver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LBL_Persona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXT_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXT_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXT_Código, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTN_Consultar)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_Insertar)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_Actualizar)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_Eliminar)))
                .addGap(8, 8, 8)
                .addComponent(BTN_Volver)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXT_NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_NombreActionPerformed

    private void BTN_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ConsultarActionPerformed
        ColoniaConn controlador = new ColoniaConn();
    controlador.consultar(this);
    }//GEN-LAST:event_BTN_ConsultarActionPerformed

    private void BTN_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ActualizarActionPerformed
        ColoniaConn controlador = new ColoniaConn();
    controlador.actualizar(this);
    }//GEN-LAST:event_BTN_ActualizarActionPerformed

    private void BTN_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EliminarActionPerformed
        ColoniaConn controlador = new ColoniaConn();
    controlador.eliminar(this);
    }//GEN-LAST:event_BTN_EliminarActionPerformed

    private void BTN_InsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_InsertarActionPerformed
        ColoniaConn controlador = new ColoniaConn();
    controlador.insertar(this);
    }//GEN-LAST:event_BTN_InsertarActionPerformed

    private void BTN_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VolverActionPerformed
        // Crear una nueva instancia de AdminView
    AdminView adminView = new AdminView();
    
    // Cerrar la ventana actual (ColoniaView)
    this.dispose();
    
    // Mostrar la vista de AdminView
    adminView.setVisible(true);
    }//GEN-LAST:event_BTN_VolverActionPerformed
    
    // Métodos getter para obtener los campos de texto
public javax.swing.JTextField getTXT_ID() {
    return TXT_ID;
}

public javax.swing.JTextField getTXT_Nombre() {
    return TXT_Nombre;
}

public javax.swing.JTextField getTXT_Codigo() {
    return TXT_Código;
}
    
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
            java.util.logging.Logger.getLogger(ColoniaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ColoniaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ColoniaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ColoniaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ColoniaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Actualizar;
    private javax.swing.JButton BTN_Consultar;
    private javax.swing.JButton BTN_Eliminar;
    private javax.swing.JButton BTN_Insertar;
    private javax.swing.JButton BTN_Volver;
    private javax.swing.JLabel LBL_Persona;
    private javax.swing.JTextField TXT_Código;
    private javax.swing.JTextField TXT_ID;
    private javax.swing.JTextField TXT_Nombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
