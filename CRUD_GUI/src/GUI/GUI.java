package GUI;

import CONTROL.Validacion;
import ADO.Persona;
import ADO.PersonaSQL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class GUI extends JFrame {

    private PersonaSQL personaSQL;
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JTextField txtId, txtNombre, txtEdad, txtPeso;

    public GUI() {
        personaSQL = new PersonaSQL();
        setupGUI();
    }

    private void setupGUI() {
        setTitle("Operaciones CRUD");
        setSize(600, 400); // Aumento del tamaño para más espacio
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // MenuBar
        menuBar = new JMenuBar();
        JMenu menuPersona = new JMenu("Persona");
        JMenu menuOtros = new JMenu("Otros");

        // Persona menu items
        JMenuItem menuInsertar = new JMenuItem("Insertar");
        JMenuItem menuBuscar = new JMenuItem("Buscar");
        JMenuItem menuModificar = new JMenuItem("Modificar");
        JMenuItem menuEliminar = new JMenuItem("Eliminar");

        menuPersona.add(menuInsertar);
        menuPersona.add(menuBuscar);
        menuPersona.add(menuModificar);
        menuPersona.add(menuEliminar);

        // Otros menu items
        JMenuItem menuReporte = new JMenuItem("Reporte");
        JMenuItem menuGrafica = new JMenuItem("Gráfica");
        JMenuItem menuSalir = new JMenuItem("Salir");

        menuOtros.add(menuReporte);
        menuOtros.add(menuGrafica);
        menuOtros.add(menuSalir);

        menuBar.add(menuPersona);
        menuBar.add(menuOtros);
        setJMenuBar(menuBar);

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        add(mainPanel);

        // Menu Item Actions
        menuInsertar.addActionListener(e -> mostrarPanelInsertar());
        menuBuscar.addActionListener(e -> mostrarPanelBuscar());
        menuModificar.addActionListener(e -> mostrarPanelModificar());
        menuEliminar.addActionListener(e -> mostrarPanelEliminar());
        menuReporte.addActionListener(e -> mostrarReporte());
        menuGrafica.addActionListener(e -> mostrarGrafica());
        menuSalir.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null);
    }

    // Panel para insertar persona
    private void mostrarPanelInsertar() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNombre = new JTextField(20);
        txtEdad = new JTextField(20);
        txtPeso = new JTextField(20);

        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblEdad = new JLabel("Edad:");
        JLabel lblPeso = new JLabel("Peso:");

        JButton btnInsertar = new JButton("Insertar");
        btnInsertar.addActionListener(e -> insertarPersona());

        // Agregar componentes al panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblNombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblEdad, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtEdad, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblPeso, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtPeso, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(btnInsertar, gbc);

        // Actualizar el panel principal
        mainPanel.removeAll();
        mainPanel.add(panel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Panel para buscar persona
    private void mostrarPanelBuscar() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtId = new JTextField(20);
        JLabel lblId = new JLabel("ID:");

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarPersona());

        // Agregar componentes al panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblId, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(btnBuscar, gbc);

        // Actualizar el panel principal
        mainPanel.removeAll();
        mainPanel.add(panel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Panel para modificar persona
    private void mostrarPanelModificar() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtId = new JTextField(20);
        txtNombre = new JTextField(20);
        txtEdad = new JTextField(20);
        txtPeso = new JTextField(20);

        JLabel lblId = new JLabel("ID:");
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblEdad = new JLabel("Edad:");
        JLabel lblPeso = new JLabel("Peso:");

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> {
            if (!Validacion.validarId(txtId.getText())) {
                JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Persona persona = personaSQL.buscarPersona(Integer.parseInt(txtId.getText()));
            if (persona != null) {
                txtNombre.setText(persona.getNombre());
                txtEdad.setText(String.valueOf(persona.getEdad()));
                txtPeso.setText(String.valueOf(persona.getPeso()));

                txtNombre.setEnabled(true);
                txtEdad.setEnabled(true);
                txtPeso.setEnabled(true);
                txtId.setEnabled(false);

                JOptionPane.showMessageDialog(this, "Datos cargados. Modifique y presione 'Modificar'.");
            } else {
                JOptionPane.showMessageDialog(this, "Persona no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> modificarPersona(
                Integer.parseInt(txtId.getText()),
                txtNombre.getText(),
                Integer.parseInt(txtEdad.getText()),
                Double.parseDouble(txtPeso.getText())
        ));

        // Agregar componentes al panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblId, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(btnBuscar, gbc);

        gbc.gridwidth = 1; // Reset gridwidth
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblNombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblEdad, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(txtEdad, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(lblPeso, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(txtPeso, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(btnModificar, gbc);

        // Actualizar el panel principal
        mainPanel.removeAll();
        mainPanel.add(panel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Panel para eliminar persona
    private void mostrarPanelEliminar() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtId = new JTextField(20);
        JLabel lblId = new JLabel("ID:");

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> {
            if (!Validacion.validarId(txtId.getText())) {
                JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Persona persona = personaSQL.buscarPersona(Integer.parseInt(txtId.getText()));
            if (persona != null) {
                String mensaje = String.format("ID: %d\nNombre: %s\nEdad: %d\nPeso: %.2f",
                        persona.getId(), persona.getNombre(),
                        persona.getEdad(), persona.getPeso());
                int confirmacion = JOptionPane.showConfirmDialog(this, mensaje + "\n¿Está seguro de eliminar esta persona?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    eliminarPersona(persona.getId());
                    JOptionPane.showMessageDialog(this, "Persona eliminada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Persona no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Agregar componentes al panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblId, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(btnBuscar, gbc);

        // Actualizar el panel principal
        mainPanel.removeAll();
        mainPanel.add(panel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void eliminarPersona(int id) {
        // Lógica para eliminar la persona de la base de datos
        personaSQL.eliminarPersona(id);
    }

    // Acción de insertar persona
    private void insertarPersona() {
        if (!validarCamposInsertar()) {
            JOptionPane.showMessageDialog(this, "Por favor, verifique los datos ingresados",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Persona persona = new Persona(
                txtNombre.getText(),
                Integer.parseInt(txtEdad.getText()),
                Double.parseDouble(txtPeso.getText())
        );

        personaSQL.insertarPersona(persona);
        JOptionPane.showMessageDialog(this, "Persona insertada correctamente");
        limpiarCampos();
    }

    // Acción de buscar persona
    private void buscarPersona() {
        if (!Validacion.validarId(txtId.getText())) {
            JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Persona persona = personaSQL.buscarPersona(Integer.parseInt(txtId.getText()));
        if (persona != null) {
            String mensaje = String.format("ID: %d\nNombre: %s\nEdad: %d\nPeso: %.2f",
                    persona.getId(), persona.getNombre(),
                    persona.getEdad(), persona.getPeso());
            JOptionPane.showMessageDialog(this, mensaje, "Persona encontrada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Persona no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Acción de modificar persona
    private void modificarPersona(int id, String nombre, int edad, double peso) {
        if (!validarCamposInsertar()) {
            JOptionPane.showMessageDialog(this, "Por favor, verifique los datos ingresados",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Persona persona = new Persona(
                id,
                nombre,
                edad,
                peso
        );

        if (personaSQL.modificarPersona(persona)) {
            JOptionPane.showMessageDialog(this, "Persona modificada correctamente");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Persona no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Función de validación de campos
    private boolean validarCamposInsertar() {
        return Validacion.validarNombre(txtNombre.getText())
                && Validacion.validarEdad(txtEdad.getText())
                && Validacion.validarPeso(txtPeso.getText());
    }

    // Limpiar campos de texto
    private void limpiarCampos() {
        if (txtNombre != null) {
            txtNombre.setText("");
        }
        if (txtEdad != null) {
            txtEdad.setText("");
        }
        if (txtPeso != null) {
            txtPeso.setText("");
        }
    }

    // Función para mostrar el reporte en JTable con scroll
    private void mostrarReporte() {
        List<Persona> personas = personaSQL.buscarTodasPersonas();
        String[] columnNames = {"ID", "Nombre", "Edad", "Peso"};
        Object[][] data = new Object[personas.size()][4];

        for (int i = 0; i < personas.size(); i++) {
            data[i][0] = personas.get(i).getId();
            data[i][1] = personas.get(i).getNombre();
            data[i][2] = personas.get(i).getEdad();
            data[i][3] = personas.get(i).getPeso();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.removeAll();
        mainPanel.add(scrollPane);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Función para mostrar la gráfica de pastel
    private void mostrarGrafica() {
        PieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset, "Distribución de Pesos");
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        mainPanel.removeAll();
        mainPanel.add(chartPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private static List<Double> obtenerPesos() {
        List<Double> pesos = new ArrayList<>();
        String usuario = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/TAP";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, usuario, password);

            String sql = "SELECT peso FROM persona";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pesos.add(rs.getDouble("peso"));
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return pesos;
    }

    private PieDataset createDataset() {
        List<Double> pesos = obtenerPesos();
        DefaultPieDataset dataset = new DefaultPieDataset();

        int rango1 = 0; // Peso < 50 kg
        int rango2 = 0; // Peso entre 50 y 70 kg
        int rango3 = 0; // Peso entre 70 y 90 kg
        int rango4 = 0; // Peso > 90 kg

        for (double peso : pesos) {
            if (peso < 50) {
                rango1++;
            } else if (peso >= 50 && peso < 70) {
                rango2++;
            } else if (peso >= 70 && peso < 90) {
                rango3++;
            } else {
                rango4++;
            }
        }

        dataset.setValue("Menos de 50 kg", rango1);
        dataset.setValue("Entre 50 y 70 kg", rango2);
        dataset.setValue("Entre 70 y 90 kg", rango3);
        dataset.setValue("Más de 90 kg", rango4);

        return dataset;
    }

    private JFreeChart createChart(PieDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(0);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
    }

}
