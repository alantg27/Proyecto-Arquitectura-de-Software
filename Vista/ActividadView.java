package proyectof.View;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import proyectof.Model.*;
import proyectof.Conn.*;
public class ActividadView extends javax.swing.JFrame {

    public ActividadView() {
        initComponents();
        desactivarBotonesSegunRol() ;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        TXT_ID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TXT_Descripcion = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        FormatteedFiel_Fecha = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        TXT_Cuadrilla = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TXT_Colonia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TXT_Evidencia = new javax.swing.JTextArea();
        LBL_Previa = new javax.swing.JLabel();
        BTN_Previa = new javax.swing.JButton();
        BTN_Insertar = new javax.swing.JButton();
        BTN_Consulta = new javax.swing.JButton();
        BTN_Actualizar = new javax.swing.JButton();
        BTN_Eliminar = new javax.swing.JButton();
        BTN_Volver = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID");

        jLabel2.setText("Descripción");

        TXT_Descripcion.setColumns(20);
        TXT_Descripcion.setRows(5);
        jScrollPane1.setViewportView(TXT_Descripcion);

        jLabel3.setText("Fecha");

        FormatteedFiel_Fecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        jLabel4.setText("IdCuadrilla");

        TXT_Cuadrilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_CuadrillaActionPerformed(evt);
            }
        });

        jLabel5.setText("IdColonia");

        jLabel6.setText("Evidencia");

        TXT_Evidencia.setColumns(20);
        TXT_Evidencia.setRows(5);
        jScrollPane2.setViewportView(TXT_Evidencia);

        LBL_Previa.setText("Vista Previa");

        BTN_Previa.setText("Vista Previa");
        BTN_Previa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_PreviaActionPerformed(evt);
            }
        });

        BTN_Insertar.setText("Insertar");
        BTN_Insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_InsertarActionPerformed(evt);
            }
        });

        BTN_Consulta.setText("Consultar");
        BTN_Consulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ConsultaActionPerformed(evt);
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

        BTN_Volver.setText("Volver");
        BTN_Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(TXT_Colonia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                        .addComponent(TXT_Cuadrilla, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(FormatteedFiel_Fecha, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel6)
                    .addComponent(TXT_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(LBL_Previa, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BTN_Actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BTN_Insertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(81, 81, 81)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BTN_Consulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BTN_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(85, 85, 85)))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(BTN_Previa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BTN_Volver)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXT_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_Insertar)
                    .addComponent(BTN_Consulta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BTN_Actualizar)
                        .addComponent(BTN_Eliminar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(FormatteedFiel_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXT_Cuadrilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXT_Colonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LBL_Previa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_Previa)
                    .addComponent(BTN_Volver))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VolverActionPerformed
        // Verifica el valor del rol global
    if (Login.obtenerRol() == 1) {
        // Si el rol es 1 (Admin), redirige a AdminView
        AdminView adminView = new AdminView();  // Crea una instancia de la vista de Admin
        adminView.setVisible(true);  // Muestra la vista de Admin
        this.setVisible(false);  // Oculta la vista actual (LoginView)
    } else {
        // Si el rol es diferente de 1 (Limpieza), redirige a LimpiezaView
        LimpiezaView limpiezaView = new LimpiezaView();  // Crea una instancia de la vista de Limpieza
        limpiezaView.setVisible(true);  // Muestra la vista de Limpieza
        this.setVisible(false);  // Oculta la vista actual (LoginView)
    }
    }//GEN-LAST:event_BTN_VolverActionPerformed

    public void desactivarBotonesSegunRol() {
    // Verifica si el rol es 1 (Admin)
    if (Login.obtenerRol() != 1) {
        // Si el rol no es 1, desactiva los botones de actualizar e insertar
        BTN_Actualizar.setEnabled(false);  // Desactiva el botón de Actualizar
        BTN_Eliminar.setEnabled(false);  // Desactiva el botón de Insertar
    }
}

    
    private void BTN_PreviaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_PreviaActionPerformed
        // Obtener el texto de la JTextArea (TXT_Evidencia)
    String evidencia = TXT_Evidencia.getText();

    // Verificar si el texto es una ruta válida de una imagen
    File archivo = new File(evidencia);
    if (archivo.exists() && (evidencia.endsWith(".png") || evidencia.endsWith(".jpg") || evidencia.endsWith(".jpeg"))) {
        // Si es una imagen, cargarla y mostrarla en la JLabel
        try {
            ImageIcon imageIcon = new ImageIcon(archivo.getAbsolutePath()); // Cargar la imagen

            // Redimensionar la imagen para que se ajuste al tamaño del JLabel
            Image image = imageIcon.getImage(); // Obtener la imagen original
            Image resizedImage = image.getScaledInstance(LBL_Previa.getWidth(), LBL_Previa.getHeight(), Image.SCALE_SMOOTH); // Redimensionar

            // Establecer la imagen redimensionada en el JLabel
            LBL_Previa.setIcon(new ImageIcon(resizedImage));
            LBL_Previa.setText(""); // Limpiar cualquier texto en la JLabel
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen.");
        }
    } else {
        // Si no es una imagen válida, mostrar un mensaje
        JOptionPane.showMessageDialog(this, "El texto no es una ruta válida de una imagen.");
    }
    }//GEN-LAST:event_BTN_PreviaActionPerformed

    private void BTN_InsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_InsertarActionPerformed
    // Obtén los datos de los campos de texto y combo boxes
    String descripcion = TXT_Descripcion.getText();  // JTextField para descripción
    String fechaString = FormatteedFiel_Fecha.getText();  // JTextField para fecha (formato esperado: "yyyy-MM-dd")
    String evidencia = TXT_Evidencia.getText();  // JTextField para evidencia
    String idCuadrillaStr = TXT_Cuadrilla.getText();  // ComboBox con cuadrillas
    String idColoniaStr = TXT_Colonia.getText();

    // Llamar al controlador con los datos obtenidos de la vista
    try {
        // Crear una instancia del controlador
        ActividadConn actividadController = new ActividadConn();

        // Pasar los datos obtenidos al controlador para insertar la actividad
        actividadController.insertarActividad(descripcion, fechaString, evidencia, idCuadrillaStr, idColoniaStr);
    } catch (Exception e) {
        e.printStackTrace();
        // Aquí puedes mostrar un mensaje de error al usuario si algo falla
    }

    }//GEN-LAST:event_BTN_InsertarActionPerformed

    private void BTN_ConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ConsultaActionPerformed
     ActividadConn controlador = new ActividadConn();
    controlador.consultarActividad(this);  // Llamamos al controlador de actividad y pasamos la vista
    
    }//GEN-LAST:event_BTN_ConsultaActionPerformed

    private void BTN_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ActualizarActionPerformed
        // Obtén los datos de los campos de texto y combo boxes
String idStr = TXT_ID.getText();  // JTextField para ID de la actividad (esto debería estar presente en la vista)
String descripcion = TXT_Descripcion.getText();  // JTextField para descripción
String fechaString = FormatteedFiel_Fecha.getText();  // JTextField para fecha (formato esperado: "yyyy-MM-dd")
String evidencia = TXT_Evidencia.getText();  // JTextField para evidencia
String idCuadrillaStr = TXT_Cuadrilla.getText();  // ComboBox con cuadrillas
String idColoniaStr = TXT_Colonia.getText();  // JTextField o ComboBox para colonia

// Llamar al controlador con los datos obtenidos de la vista
try {
    // Crear una instancia del controlador
    ActividadConn actividadController = new ActividadConn();

    // Pasar los datos obtenidos al controlador para actualizar la actividad
    actividadController.actualizarActividad(idStr, descripcion, fechaString, evidencia, idCuadrillaStr, idColoniaStr);
} catch (Exception e) {
    e.printStackTrace();
    // Aquí puedes mostrar un mensaje de error al usuario si algo falla
    JOptionPane.showMessageDialog(this, "Ocurrió un error al actualizar la actividad.");
}

    }//GEN-LAST:event_BTN_ActualizarActionPerformed

    private void BTN_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EliminarActionPerformed
        ActividadConn controlador = new ActividadConn();
    controlador.eliminar(this);  
    }//GEN-LAST:event_BTN_EliminarActionPerformed

    private void TXT_CuadrillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_CuadrillaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_CuadrillaActionPerformed

// Métodos getter para los campos de la vista
public javax.swing.JTextField getTXT_ID() {
    return TXT_ID;
}

public javax.swing.JTextArea getDescripcion() {
    return TXT_Descripcion;
}

public javax.swing.JTextField getFecha() {
    return FormatteedFiel_Fecha;
}

public javax.swing.JTextArea getEvidencia() {
    return TXT_Evidencia;
}

public javax.swing.JTextField getIdCuadrilla() {
    return TXT_Cuadrilla;
}

public javax.swing.JTextField getIdColonia() {
    return TXT_Colonia;
}

    // Métodos getter para obtener los campos de texto
    

public javax.swing.JTextArea getTXT_Descripcion() {
    return TXT_Descripcion;
}

public javax.swing.JTextField getFormattedFiel_Fecha() {
    return FormatteedFiel_Fecha;
}

public javax.swing.JTextArea getTXT_Evidencia() {
    return TXT_Evidencia;
}

public javax.swing.JTextField getTXT_Cuadrilla() {
    return TXT_Cuadrilla;
}

public javax.swing.JTextField getTXT_Colonia() {
    return TXT_Colonia;
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
            java.util.logging.Logger.getLogger(ActividadView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActividadView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActividadView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActividadView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ActividadView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Actualizar;
    private javax.swing.JButton BTN_Consulta;
    private javax.swing.JButton BTN_Eliminar;
    private javax.swing.JButton BTN_Insertar;
    private javax.swing.JButton BTN_Previa;
    private javax.swing.JButton BTN_Volver;
    private javax.swing.JFormattedTextField FormatteedFiel_Fecha;
    private javax.swing.JLabel LBL_Previa;
    private javax.swing.JTextField TXT_Colonia;
    private javax.swing.JTextField TXT_Cuadrilla;
    private javax.swing.JTextArea TXT_Descripcion;
    private javax.swing.JTextArea TXT_Evidencia;
    private javax.swing.JTextField TXT_ID;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}
