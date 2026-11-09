package schoolinventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SchoolInventoryFrame extends JFrame {

    // STACK DATA STRUCTURE
    private EquipmentStack equipmentStack =
            new EquipmentStack(100);

    // INPUT FIELDS
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtCategory;
    private JTextField txtQuantity;

    private JComboBox<String> cmbAvailability;
 // TABLE
    private JTable equipmentTable;
    private DefaultTableModel tableModel;

    public SchoolInventoryFrame() {

        setTitle("School Equipment Inventory System");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(240, 245, 240));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // HEADER
        JLabel title = new JLabel(
                "SCHOOL EQUIPMENT INVENTORY SYSTEM",
                SwingConstants.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 23));
        title.setForeground(new Color(29, 76, 59));

        mainPanel.add(title, BorderLayout.NORTH);

// CENTER PANEL
        JPanel centerPanel = new JPanel(new BorderLayout(10, 15));
        centerPanel.setBackground(new Color(240, 245, 240));

        // INPUT PANEL
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(
                BorderFactory.createTitledBorder("Equipment Information"));

        txtID = new JTextField();
        txtName = new JTextField();
        txtCategory = new JTextField();
        txtQuantity = new JTextField();

        cmbAvailability = new JComboBox<>(new String[]{
            "Available",
            "Borrowed",
            "Under Maintenance"
        });

        inputPanel.add(new JLabel("Equipment ID:"));
        inputPanel.add(txtID);

        inputPanel.add(new JLabel("Equipment Name:"));
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(txtCategory);

        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(txtQuantity);

        inputPanel.add(new JLabel("Availability:"));
        inputPanel.add(cmbAvailability);

        centerPanel.add(inputPanel, BorderLayout.NORTH);
// BUTTON PANEL
        JPanel buttonPanel = new JPanel(
                new GridLayout(1, 4, 10, 10));

        buttonPanel.setBackground(
                new Color(240, 245, 240));

        JButton btnRegister = new JButton("Register");
        JButton btnDisplay = new JButton("Display Records");
        JButton btnSearch = new JButton("Search");
        JButton btnUpdate = new JButton("Update Status");

        styleButton(btnRegister);
        styleButton(btnDisplay);
        styleButton(btnSearch);
        styleButton(btnUpdate);

        buttonPanel.add(btnRegister);
        buttonPanel.add(btnDisplay);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnUpdate);

        centerPanel.add(buttonPanel, BorderLayout.CENTER);

        // TABLE PANEL
        String[] columns = {
            "Equipment ID",
            "Equipment Name",
            "Category",
            "Quantity",
            "Availability"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        equipmentTable = new JTable(tableModel);
        equipmentTable.setRowHeight(25);
        equipmentTable.setFont(new Font("Arial", Font.PLAIN, 13));

        equipmentTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 13));

        equipmentTable.getTableHeader().setBackground(
                new Color(29, 76, 59));

        equipmentTable.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(equipmentTable);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Equipment Records"));

        centerPanel.add(scrollPane, BorderLayout.SOUTH);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);
// BUTTON ACTIONS

        // REGISTER EQUIPMENT
        btnRegister.addActionListener(e -> registerEquipment());

        // DISPLAY EQUIPMENT
        btnDisplay.addActionListener(e -> displayEquipment());

        // SEARCH EQUIPMENT
        btnSearch.addActionListener(e -> searchEquipment());

        // UPDATE AVAILABILITY
        btnUpdate.addActionListener(e -> updateEquipment());

        // INITIAL DISPLAY
        displayEquipment();
    }

    // BUTTON DESIGN
    private void styleButton(JButton button) {

        button.setBackground(new Color(29, 76, 59));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }