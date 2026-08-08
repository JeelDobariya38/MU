import javax.swing.*;
import java.awt.*;

public class P3 extends JFrame {
    private JLabel enrollmentLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel classLabel;
    private JLabel genderLabel;
    private JLabel addressLabel;

    private JTextField enrollmentField;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    private JComboBox<String> classComboBox;

    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;

    private JTextArea addressArea;

    private JButton submitButton;

    private ButtonGroup genderGroup;

    public P3() {
        setTitle("Student Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create labels
        enrollmentLabel = new JLabel("Enrollment No:");
        nameLabel = new JLabel("Name:");
        emailLabel = new JLabel("Email:");
        passwordLabel = new JLabel("Password:");
        classLabel = new JLabel("Class:");
        genderLabel = new JLabel("Gender:");
        addressLabel = new JLabel("Address:");

        // Create textfields
        enrollmentField = new JTextField(20);
        nameField = new JTextField(20);
        emailField = new JTextField(20);

        // Password field
        passwordField = new JPasswordField(20);

        // Class combo box
        classComboBox = new JComboBox<>(
                new String[] { "EV1", "EV2", "EV3", "EV4" });

        // Gender radio buttons
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // Address text area
        addressArea = new JTextArea(5, 20);
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);

        // Scroll pane for address
        JScrollPane addressScrollPane = new JScrollPane(addressArea);

        // Submit button
        submitButton = new JButton("Submit");

        // Add action listener
        submitButton.addActionListener(e -> submitForm());

        // Create main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 25, 25, 25));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Enrollment Number
        gbc.gridy = 0;

        gbc.gridx = 0;
        mainPanel.add(enrollmentLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(enrollmentField, gbc);

        // Name
        gbc.gridy = 1;

        gbc.gridx = 0;
        mainPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(nameField, gbc);

        // Email
        gbc.gridy = 2;

        gbc.gridx = 0;
        mainPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(emailField, gbc);

        // Password
        gbc.gridy = 3;

        gbc.gridx = 0;
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        // Class
        gbc.gridy = 4;

        gbc.gridx = 0;
        mainPanel.add(classLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(classComboBox, gbc);

        // Gender
        gbc.gridy = 5;

        gbc.gridx = 0;
        mainPanel.add(genderLabel, gbc);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);

        gbc.gridx = 1;
        mainPanel.add(genderPanel, gbc);

        // Address
        gbc.gridy = 6;

        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        mainPanel.add(addressLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(addressScrollPane, gbc);

        // Submit button
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        mainPanel.add(submitButton, gbc);

        // Add main panel to frame
        add(mainPanel);

        // Set window size
        setSize(500, 500);

        // Center the window
        setLocationRelativeTo(null);
    }

    /**
     * Handles form submission and validation
     */
    private void submitForm() {
        String erno = enrollmentField.getText().trim();
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String address = addressArea.getText().trim();

        String gender = "";
        if (maleRadioButton.isSelected()) {
            gender = "Male";
        } else if (femaleRadioButton.isSelected()) {
            gender = "Female";
        }

        String selectedClass = classComboBox.getSelectedItem().toString();

        // 1. Enrollment Number Validation
        if (erno.isBlank() || !erno.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Enrollment Number should contain only numbers",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);

            enrollmentField.requestFocus();
            return;
        }

        // 2. Name Validation
        if (name.isBlank() || !name.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Name should contain only alphabets",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);

            nameField.requestFocus();
            return;
        }

        // 3. Email Validation
        if (email.isBlank() || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Email",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);

            emailField.requestFocus();
            return;
        }

        // 4. Password Validation
        if (password.isBlank() || password.length() < 6) {
            JOptionPane.showMessageDialog(
                    this,
                    "Password must be at least 6 characters",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);

            passwordField.requestFocus();
            return;
        }

        // 5. Gender Validation
        if (gender == "") {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select Gender",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);

            return;
        }

        // 6. Address Validation
        if (address.isBlank()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Address",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);

            addressArea.requestFocus();
            return;
        }

        // Registration Successful
        JOptionPane.showMessageDialog(
                this,
                "Details:- \n\n"
                        + "Enrollment No: " + erno + "\n"
                        + "Name: " + name + "\n"
                        + "Email: " + email + "\n"
                        + "Password: " + password + "\n"
                        + "Class: " + selectedClass + "\n"
                        + "Gender: " + gender + "\n"
                        + "Address: " + address,
                "Registration Successful!!",
                JOptionPane.INFORMATION_MESSAGE);

        clearForm();

        enrollmentField.requestFocus();
    }

    private void clearForm() {
        enrollmentField.setText("");
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        addressArea.setText("");

        genderGroup.clearSelection();
        classComboBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        // Set Nimbus Look and Feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
            P3 form = new P3();
            form.setVisible(true);
        });
    }
}
