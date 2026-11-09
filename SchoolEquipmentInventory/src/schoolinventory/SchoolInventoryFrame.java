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

