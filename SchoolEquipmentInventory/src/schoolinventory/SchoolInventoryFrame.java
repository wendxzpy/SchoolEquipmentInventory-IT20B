package schoolinventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SchoolInventoryFrame extends JFrame {
    // STACK DATA STRUCTURE
    private EquipmentStack equipmentStack = new EquipmentStack(100);

    // INPUT FIELDS
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtCategory;
    private JTextField txtQuantity;
    private JComboBox<String> cmbAvailability;
    
    // NEW SECTION: LAST UPDATED DISPLAY
    private JTextArea txtLastUpdated;

    // TABLE
    private JTable equipmentTable;
    private DefaultTableModel tableModel;

    public SchoolInventoryFrame() {
        setTitle("School Equipment Inventory System");
        setSize(950, 700); // Slightly increased height to comfortably accommodate the new section
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(240, 245, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // HEADER
        JLabel title = new JLabel("SCHOOL EQUIPMENT INVENTORY SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 23));
        title.setForeground(new Color(29, 76, 59));
        mainPanel.add(title, BorderLayout.NORTH);

        // CENTER PANEL
        JPanel centerPanel = new JPanel(new BorderLayout(10, 15));
        centerPanel.setBackground(new Color(240, 245, 240));

        // INPUT & UPDATED PANEL (LEFT & RIGHT SPLIT)
        JPanel topGridPanel = new JPanel(new BorderLayout(15, 0));
        topGridPanel.setBackground(new Color(240, 245, 240));

        // INPUT PANEL
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Equipment Information"));
        
        txtID = new JTextField();
        txtName = new JTextField();
        txtCategory = new JTextField();
        txtQuantity = new JTextField();
        cmbAvailability = new JComboBox<>(new String[]{ "Available", "Borrowed", "Under Maintenance" });

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
        topGridPanel.add(inputPanel, BorderLayout.CENTER);

        // NEW SECTION PANEL: LAST UPDATED EQUIPMENT INFO
        JPanel lastUpdatedPanel = new JPanel(new BorderLayout());
        lastUpdatedPanel.setBackground(Color.WHITE);
        lastUpdatedPanel.setBorder(BorderFactory.createTitledBorder("Last Updated Equipment"));
        lastUpdatedPanel.setPreferredSize(new Dimension(300, 150));
        
        txtLastUpdated = new JTextArea("No recent updates.");
        txtLastUpdated.setFont(new Font("Arial", Font.PLAIN, 12));
        txtLastUpdated.setEditable(false);
        txtLastUpdated.setBackground(new Color(248, 250, 248));
        txtLastUpdated.setMargin(new Insets(5, 5, 5, 5));
        lastUpdatedPanel.add(new JScrollPane(txtLastUpdated), BorderLayout.CENTER);
        topGridPanel.add(lastUpdatedPanel, BorderLayout.EAST);

        centerPanel.add(topGridPanel, BorderLayout.NORTH);

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBackground(new Color(240, 245, 240));

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
        String[] columns = { "Equipment ID", "Equipment Name", "Category", "Quantity", "Availability" };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        equipmentTable = new JTable(tableModel);
        equipmentTable.setRowHeight(25);
        equipmentTable.setFont(new Font("Arial", Font.PLAIN, 13));
        equipmentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        equipmentTable.getTableHeader().setBackground(new Color(29, 76, 59));
        equipmentTable.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(equipmentTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Equipment Records"));
        scrollPane.setPreferredSize(new Dimension(900, 280));
        centerPanel.add(scrollPane, BorderLayout.SOUTH);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel);

        // BUTTON ACTIONS
        btnRegister.addActionListener(e -> registerEquipment());
        btnDisplay.addActionListener(e -> displayEquipment());
        btnSearch.addActionListener(e -> searchEquipment());
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

    // REGISTER EQUIPMENT
    private void registerEquipment() {
        String id = txtID.getText().trim();
        String name = txtName.getText().trim();
        String category = txtCategory.getText().trim();
        String quantityText = txtQuantity.getText().trim();

        if (id.isEmpty() || name.isEmpty() || category.isEmpty() || quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be greater than zero!");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid quantity!");
            return;
        }

        // CHECK DUPLICATE ID
        if (equipmentStack.search(id) != null) {
            JOptionPane.showMessageDialog(this, "Equipment ID already exists!", "Duplicate Record", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String availability = cmbAvailability.getSelectedItem().toString();
        Equipment equipment = new Equipment(id, name, category, quantity, availability);

        // PUSH INTO STACK
        if (equipmentStack.push(equipment)) {
            JOptionPane.showMessageDialog(this, "Equipment successfully registered!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            displayEquipment();
        } else {
            JOptionPane.showMessageDialog(this, "Stack is full!");
        }
    }

    // DISPLAY EQUIPMENT
    private void displayEquipment() {
        tableModel.setRowCount(0);
        for (int i = equipmentStack.size() - 1; i >= 0; i--) {
            Equipment equipment = equipmentStack.getEquipment(i);
            if (equipment != null) {
                tableModel.addRow(new Object[]{
                    equipment.getEquipmentID(),
                    equipment.getEquipmentName(),
                    equipment.getCategory(),
                    equipment.getQuantity(),
                    equipment.getAvailability()
                });
            }
        }
    }

    // SEARCH EQUIPMENT
    private void searchEquipment() {
        String id = txtID.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Equipment ID!");
            return;
        }

        Equipment equipment = equipmentStack.search(id);
        if (equipment != null) {
            txtName.setText(equipment.getEquipmentName());
            txtCategory.setText(equipment.getCategory());
            txtQuantity.setText(String.valueOf(equipment.getQuantity()));
            cmbAvailability.setSelectedItem(equipment.getAvailability());

            JOptionPane.showMessageDialog(this, 
                "Equipment Found!\n\n" +
                "ID: " + equipment.getEquipmentID() +
                "\nName: " + equipment.getEquipmentName() +
                "\nCategory: " + equipment.getCategory() +
                "\nQuantity: " + equipment.getQuantity() +
                "\nAvailability: " + equipment.getAvailability(), 
                "Search Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Equipment ID not found!", "Search Result", JOptionPane.ERROR_MESSAGE);
        }
    }

        // UPDATE AVAILABILITY
    private void updateEquipment() {
        String id = txtID.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Equipment ID!", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String newAvailability = cmbAvailability.getSelectedItem().toString();
        Equipment equipment = equipmentStack.search(id);

        if (equipment != null) {
            String oldAvailability = equipment.getAvailability();
            equipment.setAvailability(newAvailability);

            // UPDATE THE "LAST UPDATED EQUIPMENT" SECTION
            txtLastUpdated.setText(
                "ID: " + equipment.getEquipmentID() + "\n" +
                "Name: " + equipment.getEquipmentName() + "\n" +
                "Category: " + equipment.getCategory() + "\n" +
                "Quantity: " + equipment.getQuantity() + "\n" +
                "Status: " + oldAvailability + " -> " + newAvailability
            );

            JOptionPane.showMessageDialog(this, "Equipment status updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            displayEquipment();
        } else {
            JOptionPane.showMessageDialog(this, "Equipment ID not found!", "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // CLEAR INPUT FIELDS
    private void clearFields() {
        txtID.setText("");
        txtName.setText("");
        txtCategory.setText("");
        txtQuantity.setText("");
        cmbAvailability.setSelectedIndex(0);
    }

    // MAIN METHOD TO RUN THE APPLICATION
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SchoolInventoryFrame().setVisible(true);
        });
    }
}
