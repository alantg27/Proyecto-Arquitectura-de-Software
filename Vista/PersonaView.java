package proyectof.View;
import proyectof.Conn.PersonaConn;
import java.awt.Color;
import proyectof.Model.ConexionBD;
import java.sql.Connection; // Para manejar la conexión con la base de datos
import java.sql.DriverManager; // Para manejar el registro de los drivers JDBC
import java.sql.PreparedStatement; // Para ejecutar consultas preparadas
import java.sql.ResultSet; // Para manejar los resultados de las consultas
import java.sql.SQLException; // Para manejar excepciones SQL
import javax.swing.JComboBox; // Para trabajar con el JComboBox
import java.util.ArrayList; // Si usas ArrayList para manejar listas temporales (si es necesario)

public class PersonaView extends javax.swing.JFrame {

    public PersonaView() {
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
        jLabel1 = new javax.swing.JLabel();
        TXT_ID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TXT_Nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TXT_Edad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TXT_Correo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        CB_Rol = new javax.swing.JComboBox<>();
        TXT_Contraseña = new javax.swing.JTextField();
        BTN_Consultar = new javax.swing.JButton();
        BTN_Insertar = new javax.swing.JButton();
        BTN_Actualizar = new javax.swing.JButton();
        BTN_Eliminar = new javax.swing.JButton();
        BTN_Volver = new javax.swing.JButton();
        CB_Cuadrilla = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LBL_Persona.setText("Menú Personas");

        jLabel1.setText("ID:");

        jLabel2.setText("Nombre:");

        TXT_Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_NombreActionPerformed(evt);
            }
        });

        jLabel3.setText("Edad:");

        jLabel4.setText("Correo:");

        jLabel5.setText("Contraseña:");

        jLabel6.setText("Rol:");

        jLabel7.setText("Cuadrilla:");

        CB_Rol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Administrador", "Jefe de Cuadrilla", "Empleado" }));
        CB_Rol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_RolActionPerformed(evt);
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
                .addGap(162, 162, 162)
                .addComponent(LBL_Persona)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(TXT_ID)
                    .addComponent(TXT_Nombre)
                    .addComponent(TXT_Edad)
                    .addComponent(TXT_Correo)
                    .addComponent(CB_Rol, 0, 167, Short.MAX_VALUE)
                    .addComponent(TXT_Contraseña)
                    .addComponent(CB_Cuadrilla, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TXT_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(BTN_Consultar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TXT_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(BTN_Insertar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TXT_Edad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(BTN_Actualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TXT_Correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(BTN_Eliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TXT_Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Rol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CB_Cuadrilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BTN_Volver))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Para obtener el campo TXT_ID
public javax.swing.JTextField getTXT_ID() {
    return TXT_ID;
}

// Para obtener el campo TXT_Nombre
public javax.swing.JTextField getTXT_Nombre() {
    return TXT_Nombre;
}

// Para obtener el campo TXT_Edad
public javax.swing.JTextField getTXT_Edad() {
    return TXT_Edad;
}

// Para obtener el campo TXT_Correo
public javax.swing.JTextField getTXT_Correo() {
    return TXT_Correo;
}

// Para obtener el campo TXT_Contraseña
public javax.swing.JTextField getTXT_Contraseña() {
    return TXT_Contraseña;
}

// Para obtener el campo CB_Rol (JComboBox)
public javax.swing.JComboBox<String> getCB_Rol() {
    return CB_Rol;
}

// Para obtener el campo CB_Cuadrilla (JComboBox)
public javax.swing.JComboBox<String> getCB_Cuadrilla() {
   return CB_Cuadrilla;
}
    
    private void TXT_NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_NombreActionPerformed

    private void BTN_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ConsultarActionPerformed
        PersonaConn controlador = new PersonaConn();
    controlador.consultarPorId(this);
    }//GEN-LAST:event_BTN_ConsultarActionPerformed

    private void BTN_InsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_InsertarActionPerformed
        PersonaConn controlador = new PersonaConn();
    controlador.insertar(this);
    }//GEN-LAST:event_BTN_InsertarActionPerformed

    private void BTN_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ActualizarActionPerformed
        PersonaConn controlador = new PersonaConn();
    controlador.actualizar(this);
    }//GEN-LAST:event_BTN_ActualizarActionPerformed

    private void BTN_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EliminarActionPerformed
        PersonaConn controlador = new PersonaConn();
    controlador.eliminar(this); 
    }//GEN-LAST:event_BTN_EliminarActionPerformed

    private void BTN_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VolverActionPerformed
            // Cerrar la ventana actual
    this.dispose();
    
    // Crear una instancia de la vista de administración
    AdminView adminView = new AdminView();
    
    // Hacer visible la ventana de administración
    adminView.setVisible(true);
    }//GEN-LAST:event_BTN_VolverActionPerformed

    
    
    private void CB_RolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_RolActionPerformed
        String rolSeleccionado = (String) CB_Rol.getSelectedItem();
    PersonaConn controlador = new PersonaConn();

    // Verifica si el rol está vacío o no
    if (!rolSeleccionado.isEmpty()) {
        // Pasa la conexión abierta y el JComboBox al controlador para actualizar la cuadrilla
        Connection conexion = ConexionBD.conectar(); // Obtiene la conexión desde la clase ConexionBD
        controlador.actualizarCuadrilla(rolSeleccionado, CB_Cuadrilla, conexion); // Pasa el JComboBox aquí
    } else {
        // Si el valor está vacío, limpiar la CB_Cuadrilla
        CB_Cuadrilla.removeAllItems();
        CB_Cuadrilla.addItem("Seleccione un rol primero");
        }
    }//GEN-LAST:event_CB_RolActionPerformed

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
            java.util.logging.Logger.getLogger(PersonaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PersonaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PersonaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PersonaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Actualizar;
    private javax.swing.JButton BTN_Consultar;
    private javax.swing.JButton BTN_Eliminar;
    private javax.swing.JButton BTN_Insertar;
    private javax.swing.JButton BTN_Volver;
    private javax.swing.JComboBox<String> CB_Cuadrilla;
    private javax.swing.JComboBox<String> CB_Rol;
    private javax.swing.JLabel LBL_Persona;
    private javax.swing.JTextField TXT_Contraseña;
    private javax.swing.JTextField TXT_Correo;
    private javax.swing.JTextField TXT_Edad;
    private javax.swing.JTextField TXT_ID;
    private javax.swing.JTextField TXT_Nombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
