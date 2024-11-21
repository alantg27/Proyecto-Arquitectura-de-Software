package limpieza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import limpieza.DashboardActividadesFecha;
import limpieza.DashboardNumeroActividadesColonia;
import limpieza.DashboardNumeroActividadesCuadrilla;
import limpieza.DashboardIntegrantesCuadrillas;

public class MainDashboardFrame extends JFrame {

    public MainDashboardFrame() {
        // Configuración básica de la ventana
        setTitle("Dashboard Principal");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout
        setLayout(new FlowLayout());

        // Crear botones para cada dashboard
        JButton btnDashboard1 = new JButton("Dashboard Actividades Cuadrilla");
        JButton btnDashboard2 = new JButton("Dashboard Actividades Colonia");
        JButton btnDashboard3 = new JButton("Dashboard Actividades Por Día");
        JButton btnDashboard4 = new JButton("Dashboard Integrantes Cuadrillas");

        // Agregar ActionListener a cada botón para abrir el correspondiente Dashboard
        btnDashboard1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear el dashboard correspondiente (reemplaza con tu implementación)
                new DashboardNumeroActividadesCuadrilla().setVisible(true);
                setVisible(false); // Cerrar la ventana principal
            }
        });

        btnDashboard2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear el dashboard correspondiente
                new DashboardNumeroActividadesColonia().setVisible(true);
                setVisible(false); // Cerrar la ventana principal
            }
        });

        btnDashboard3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear el dashboard correspondiente
                new DashboardActividadesFecha().setVisible(true);
                setVisible(false); // Cerrar la ventana principal
            }
        });

        btnDashboard4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear el dashboard correspondiente
                new DashboardIntegrantesCuadrillas().setVisible(true);
                setVisible(false); // Cerrar la ventana principal
            }
        });

        // Agregar los botones a la ventana
        add(btnDashboard1);
        add(btnDashboard2);
        add(btnDashboard3);
        add(btnDashboard4);
    }

    public static void main(String[] args) {
        // Iniciar la ventana principal
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainDashboardFrame().setVisible(true);
            }
        });
    }
}