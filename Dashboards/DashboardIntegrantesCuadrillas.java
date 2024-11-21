package limpieza;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class DashboardIntegrantesCuadrillas extends JFrame {

    public DashboardIntegrantesCuadrillas() {
        // Configuración de la ventana del dashboard
        setTitle("Dashboard de Integrantes de Cuadrillas");
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar solo esta ventana

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear la tabla
        JTable table = createTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Crear panel para el botón de regresar
        JPanel buttonPanel = new JPanel();
        JButton btnRegresar = new JButton("Regresar");
        buttonPanel.add(btnRegresar);

        // Acción del botón regresar
        btnRegresar.addActionListener(e -> {
            MainDashboardFrame mainFrame = new MainDashboardFrame();
            mainFrame.setVisible(true); // Abre la ventana principal
            dispose(); // Cierra la ventana actual
        });

        // Agregar el panel del botón a la parte inferior
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Agregar el panel al frame
        add(panel);
    }

    private JTable createTable() {
        String[] columns = {"Cuadrilla", "Integrante", "Rol"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT c.IdCuadrilla, p.Nombre, r.NombreRol "
                       + "FROM cuadrillas c "
                       + "JOIN personas p ON c.IdCuadrilla = p.IdCuadrilla "
                       + "JOIN roles r ON p.IdRol = r.IdRol "
                       + "ORDER BY c.IdCuadrilla, "
                       + "CASE WHEN r.NombreRol = 'jefe' THEN 0 ELSE 1 END, "
                       + "p.Nombre";

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Object[] row = {
                        rs.getInt("IdCuadrilla"),
                        rs.getString("Nombre"),
                        rs.getString("NombreRol")
                    };
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new JTable(model);
    }
}

