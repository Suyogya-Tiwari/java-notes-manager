package view;

import javax.swing.*;
import java.awt.*;
import controller.AuthController;

public class RegisterFrame extends JFrame {

    public RegisterFrame() {

        setTitle("Register");
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(209, 250, 243));
        add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(title, gbc);

        gbc.gridwidth = 1;

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.BLACK);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(userLabel, gbc);

        JTextField userField = new JTextField();
        gbc.gridx = 1;
        panel.add(userField, gbc);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.BLACK);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(passLabel, gbc);

        JPasswordField passField = new JPasswordField();
        gbc.gridx = 1;
        panel.add(passField, gbc);

        // Button
        JButton registerBtn = new JButton("Register");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(registerBtn, gbc);

        registerBtn.addActionListener(e -> {
            boolean success = AuthController.register(
                    userField.getText(),
                    new String(passField.getPassword())
            );

            JOptionPane.showMessageDialog(this,
                    success ? "Registered Successfully!" : "Error!");
        });

        setVisible(true);
    }
}