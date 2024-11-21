package limpieza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DashboardNumeroActividadesColonia extends JFrame {

    public DashboardNumeroActividadesColonia() {
        // Crear la ventana principal
        setTitle("Dashboard - Número de Actividades por Colonia");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo este dashboard

        // Crear el panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Panel donde se dibuja el gráfico
        JPanel chartPanel = new ColoniaChartPanel();
        mainPanel.add(chartPanel, BorderLayout.CENTER);

        // Agregar el botón "Regresar"
        JButton backButton = new JButton("Regresar");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre la ventana principal
                MainDashboardFrame mainFrame = new MainDashboardFrame();
                mainFrame.setVisible(true);
                dispose(); // Cierra esta ventana
            }
        });

        // Agregar el botón a un panel en la parte inferior
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Agregar el panel principal al frame
        add(mainPanel);

        // Mostrar la ventana
        setVisible(true);
    }

    class ColoniaChartPanel extends JPanel {
        private Map<String, Integer> data = new HashMap<>();

        public ColoniaChartPanel() {
            // Obtener los datos desde la base de datos
            loadData();
        }

        private void loadData() {
            try (Connection conn = ConexionDB.getConnection()) {
                // Consulta para obtener el número de actividades por colonia
                String query = "SELECT c.IdColonia, COUNT(*) AS numActividades " +
                        "FROM registroactividades a " +
                        "JOIN colonias c ON a.IdColonia = c.IdColonia " +
                        "GROUP BY c.IdColonia";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                // Llenar el mapa con los datos de actividades por colonia
                while (rs.next()) {
                    String idColonia = rs.getString("IdColonia");
                    int numActividades = rs.getInt("numActividades");
                    data.put(idColonia, numActividades);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar datos: " + ex.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Dibujar el gráfico
            drawBarChart((Graphics2D) g);
        }

        private void drawBarChart(Graphics2D g2d) {
            // Margen y dimensiones
            int padding = 50;
            int barWidth = 40;
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int chartWidth = panelWidth - 2 * padding;
            int chartHeight = panelHeight - 2 * padding;

            // Calcular la escala
            int maxY = data.values().stream().max(Integer::compareTo).orElse(1);
            double yScale = (double) chartHeight / maxY;

            // Dibujar los ejes
            g2d.drawLine(padding, padding, padding, padding + chartHeight); // Eje Y
            g2d.drawLine(padding, padding + chartHeight, padding + chartWidth, padding + chartHeight); // Eje X

            // Etiquetas de los ejes
            g2d.setColor(Color.BLACK);
            FontMetrics metrics = g2d.getFontMetrics();

            // Etiqueta vertical (eje Y)
            String yLabel = "Número de actividades";
            int yLabelWidth = metrics.stringWidth(yLabel);
            g2d.rotate(-Math.PI / 2);
            g2d.drawString(yLabel, -(padding + chartHeight / 2 + yLabelWidth / 2), padding - 20);
            g2d.rotate(Math.PI / 2);

            // Etiqueta horizontal (eje X)
            String xLabel = "Id Colonias";
            g2d.drawString(xLabel, padding + chartWidth / 2 - metrics.stringWidth(xLabel) / 2, padding + chartHeight + 40);

            // Dibujar las barras
            int xPosition = padding + 10;
            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                String colonia = entry.getKey();
                int numActividades = entry.getValue();

                // Calcular la altura de la barra
                int barHeight = (int) (numActividades * yScale);
                int yPosition = padding + chartHeight - barHeight;

                // Dibujar la barra
                g2d.setColor(Color.BLUE);
                g2d.fillRect(xPosition, yPosition, barWidth, barHeight);

                // Dibujar etiquetas
                g2d.setColor(Color.BLACK);
                g2d.drawString(colonia, xPosition + 10, padding + chartHeight + 15);
                g2d.drawString(String.valueOf(numActividades), xPosition + 10, yPosition - 5);

                xPosition += barWidth + 20; // Separación entre barras
            }
        }
    }
}
