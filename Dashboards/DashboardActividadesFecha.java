package limpieza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DashboardActividadesFecha extends JFrame {

    public DashboardActividadesFecha() {
        // Crear la ventana principal
        setTitle("Dashboard - Actividades por Fecha");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo este dashboard

        // Crear el panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Panel donde se dibuja el gráfico
        JPanel chartPanel = new FechaChartPanel();
        mainPanel.add(chartPanel, BorderLayout.CENTER);

        // Agregar el botón "Regresar"
        JButton backButton = new JButton("Regresar");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            MainDashboardFrame mainFrame = new MainDashboardFrame();
            mainFrame.setVisible(true); // Abre la ventana principal
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

    class FechaChartPanel extends JPanel {
        private Map<String, Integer> data = new HashMap<>();

        public FechaChartPanel() {
            // Obtener los datos desde la base de datos
            loadData();
        }

        private Connection conn;
        
        private void loadData() {
            try {
                conn = ConexionDB.getConnection();
                // Modifica esta consulta para obtener el número de actividades por mes o día
                String query = "SELECT DATE_FORMAT(fecha, '%Y-%m-%d') AS mes, COUNT(*) AS numActividades " +
                        "FROM registroactividades " +
                        "GROUP BY DATE_FORMAT(fecha, '%Y-%m-%d')";  // Agrupamos por mes (puedes usar '%Y-%m-%d' para días)
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                // Llenar el mapa con los datos
                while (rs.next()) {
                    String mes = rs.getString("mes");
                    int numActividades = rs.getInt("numActividades");
                    data.put(mes, numActividades);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar datos: " + ex.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Dibujar el gráfico
            drawLineChart((Graphics2D) g);
        }

private void drawLineChart(Graphics2D g2d) {
    // Margen y dimensiones
    int padding = 50;
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
    String xLabel = "Fecha";
    g2d.drawString(xLabel, padding + chartWidth / 2 - metrics.stringWidth(xLabel) / 2, padding + chartHeight + 40);

    // Dibujar las líneas y etiquetas
    int xPosition = padding + 10;
    int previousX = xPosition;
    int previousY = padding + chartHeight;
    boolean firstPoint = true;

    for (Map.Entry<String, Integer> entry : data.entrySet()) {
        String fecha = entry.getKey();
        int numActividades = entry.getValue();

        // Calcular la altura del punto
        int pointHeight = (int) (numActividades * yScale);
        int yPosition = padding + chartHeight - pointHeight;

        // Dibujar el punto y la línea
        if (!firstPoint) {
            g2d.setColor(Color.RED);
            g2d.drawLine(previousX, previousY, xPosition, yPosition);
        }

        firstPoint = false;

        g2d.setColor(Color.BLUE);
        g2d.fillOval(xPosition - 3, yPosition - 3, 6, 6);

        // Dibujar número de actividades encima del punto
        g2d.setColor(Color.BLACK);
        String actividadesLabel = String.valueOf(numActividades);
        int labelWidth = metrics.stringWidth(actividadesLabel);
        g2d.drawString(actividadesLabel, xPosition - labelWidth / 2, yPosition - 10);

        // Dibujar etiquetas rotadas para las fechas
        g2d.rotate(Math.toRadians(-45), xPosition, padding + chartHeight + 5);
        g2d.drawString(fecha, xPosition, padding + chartHeight + 15);
        g2d.rotate(Math.toRadians(45), xPosition, padding + chartHeight + 5);

        previousX = xPosition;
        previousY = yPosition;

        xPosition += 50; // Separación entre los puntos
    }
}


    }
}
