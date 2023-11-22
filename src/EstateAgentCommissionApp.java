import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EstateAgentCommissionApp extends JFrame {
    private JComboBox<String> locationComboBox;
    private JTextField nameTextField;
    private JTextField priceTextField;
    private JTextField commissionTextField;
    private JTextArea reportTextArea;
    private IEstateAgent estateAgent;

    public EstateAgentCommissionApp() {
        estateAgent = new EstateAgent();

        // GUI components setup
        locationComboBox = new JComboBox<>(new String[]{"Cape Town", "Durban", "Pretoria"});
        nameTextField = new JTextField(20);
        priceTextField = new JTextField(20);
        commissionTextField = new JTextField(20);
        reportTextArea = new JTextArea(10, 30);
        reportTextArea.setEditable(false);

        // Create menu bar and menus
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processReportMenuItem = new JMenuItem("Process Report");
        processReportMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processReport();
            }
        });
        toolsMenu.add(processReportMenuItem);

        JMenuItem clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        toolsMenu.add(clearMenuItem);

        JMenuItem saveReportMenuItem = new JMenuItem("Save Report");
        saveReportMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveReport();
            }
        });
        toolsMenu.add(saveReportMenuItem);

        menuBar.add(toolsMenu);

        // Use GridBagLayout for organized layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to the frame with proper layout constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Agent Location:"), gbc);

        gbc.gridx = 1;
        add(locationComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Agent Name:"), gbc);

        gbc.gridx = 1;
        add(nameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Property Price:"), gbc);

        gbc.gridx = 1;
        add(priceTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Commission Percentage:"), gbc);

        gbc.gridx = 1;
        add(commissionTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Report:"), gbc);

        gbc.gridx = 1;
        add(new JScrollPane(reportTextArea), gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void processReport() {
        String location = (String) locationComboBox.getSelectedItem();
        String name = nameTextField.getText();
        String priceStr = priceTextField.getText();
        String commissionStr = commissionTextField.getText();

        if (!estateAgent.validateData(new Data(location, name, priceStr, commissionStr))) {
            JOptionPane.showMessageDialog(this, "Invalid data. Please check your input.");
            return;
        }

        double commission = estateAgent.calculateCommission(priceStr, commissionStr);

        reportTextArea.setText(String.format("AGENT LOCATION: %s\nAGENT NAME: %s\nPROPERTY PRICE: R %s\nCOMMISSION PERCENTAGE: %s\nCALCULATED COMMISSION: R %.2f",
                location, name, priceStr, commissionStr, commission));
    }

    private void clearFields() {
        locationComboBox.setSelectedIndex(0);
        nameTextField.setText("");
        priceTextField.setText("");
        commissionTextField.setText("");
        reportTextArea.setText("");
    }

    private void saveReport() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"))) {
            writer.write(reportTextArea.getText());
            JOptionPane.showMessageDialog(this, "Report saved to report.txt");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving report.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EstateAgentCommissionApp();
            }
        });
    }
}

interface IEstateAgent {
    double calculateCommission(String propertyPrice, String agentCommission);
    boolean validateData(Data dataToValidate);
}

class EstateAgent implements IEstateAgent {
    @Override
    public double calculateCommission(String propertyPrice, String agentCommission) {
        try {
            double price = Double.parseDouble(propertyPrice);
            double commissionPercentage = Double.parseDouble(agentCommission);
            return price * (commissionPercentage / 100);
        } catch (NumberFormatException e) {
            return -1; // Return -1 to indicate an error
        }
    }

    @Override
    public boolean validateData(Data dataToValidate) {
        return !dataToValidate.getLocation().isEmpty() &&
               !dataToValidate.getName().isEmpty() &&
               dataToValidate.getPrice() > 0 &&
               dataToValidate.getCommission() > 0;
    }
}

class Data {
    private String location;
    private String name;
    private double price;
    private double commission;

    public Data(String location, String name, String priceStr, String commissionStr) {
        this.location = location;
        this.name = name;
        this.price = Double.parseDouble(priceStr);
        this.commission = Double.parseDouble(commissionStr);
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getCommission() {
        return commission;
    }
}

