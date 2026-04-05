package view;

import javax.swing.*;
import java.awt.*;
import controller.AuthController;
import model.User;

public class LoginFrame extends JFrame {

    public LoginFrame() {

        setTitle("Secure Notes Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(209, 250, 243)); // dark theme
        add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 22));
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

        JTextField userField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(userField, gbc);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.BLACK);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(passLabel, gbc);

        JPasswordField passField = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(passField, gbc);

        // Buttons
        JButton loginBtn = new JButton("Login");
        JButton regBtn = new JButton("Register");

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(loginBtn, gbc);

        gbc.gridx = 1;
        panel.add(regBtn, gbc);

        // Actions
        loginBtn.addActionListener(e -> {
            User user = AuthController.login(
                    userField.getText(),
                    new String(passField.getPassword())
            );

            if (user != null) {
                new Dashboard(user);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Login");
            }
        });

        regBtn.addActionListener(e -> new RegisterFrame());

        setVisible(true);
    }
}