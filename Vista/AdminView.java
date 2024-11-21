package proyectof.View;
import proyectof.Conn.AdminConn;
public class AdminView extends javax.swing.JFrame {
    
    private AdminConn controlador;  // Declarar el controlador

    public AdminView() {
         initComponents();
        controlador = new AdminConn(this);  // Inicializar el controlador
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BTN_Actividad = new javax.swing.JButton();
        BTN_Persona = new javax.swing.JButton();
        BTN_Cuadrilla = new javax.swing.JButton();
        BTN_Colonia = new javax.swing.JButton();
        BTN_Salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        BTN_Dashboard = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BTN_Actividad.setText("Actividades");
        BTN_Actividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ActividadActionPerformed(evt);
            }
        });

        BTN_Persona.setText("Personas");
        BTN_Persona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_PersonaActionPerformed(evt);
            }
        });

        BTN_Cuadrilla.setText("Cuadrillas");
        BTN_Cuadrilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CuadrillaActionPerformed(evt);
            }
        });

        BTN_Colonia.setText("Colonias");
        BTN_Colonia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ColoniaActionPerformed(evt);
            }
        });

        BTN_Salir.setText("Salir");
        BTN_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SalirActionPerformed(evt);
            }
        });

        jLabel1.setText("Eliga una opción:");

        BTN_Dashboard.setText("Dashboard");
        BTN_Dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DashboardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTN_Salir)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1)
                    .addComponent(BTN_Actividad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_Persona, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_Cuadrilla, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_Colonia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTN_Dashboard, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(BTN_Actividad)
                .addGap(18, 18, 18)
                .addComponent(BTN_Persona)
                .addGap(18, 18, 18)
                .addComponent(BTN_Cuadrilla)
                .addGap(18, 18, 18)
                .addComponent(BTN_Colonia)
                .addGap(18, 18, 18)
                .addComponent(BTN_Dashboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTN_Salir)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SalirActionPerformed
        controlador.salir(AdminView.this);
    }//GEN-LAST:event_BTN_SalirActionPerformed

    private void BTN_ActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ActividadActionPerformed
        controlador.abrirActividadView();
    }//GEN-LAST:event_BTN_ActividadActionPerformed

    private void BTN_PersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_PersonaActionPerformed
        controlador.abrirPersonaView();
    }//GEN-LAST:event_BTN_PersonaActionPerformed

    private void BTN_CuadrillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CuadrillaActionPerformed
        controlador.abrirCuadrillaView();
    }//GEN-LAST:event_BTN_CuadrillaActionPerformed

    private void BTN_ColoniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ColoniaActionPerformed
         controlador.abrirColoniaView();
    }//GEN-LAST:event_BTN_ColoniaActionPerformed

    private void BTN_DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DashboardActionPerformed
        // Crear una nueva instancia de AdminView
    MainDashboardFrame vista = new MainDashboardFrame();
    
    // Cerrar la ventana actual (ColoniaView)
    this.dispose();
    
    // Mostrar la vista de AdminView
    vista.setVisible(true);
    }//GEN-LAST:event_BTN_DashboardActionPerformed

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
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Actividad;
    private javax.swing.JButton BTN_Colonia;
    private javax.swing.JButton BTN_Cuadrilla;
    private javax.swing.JButton BTN_Dashboard;
    private javax.swing.JButton BTN_Persona;
    private javax.swing.JButton BTN_Salir;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
