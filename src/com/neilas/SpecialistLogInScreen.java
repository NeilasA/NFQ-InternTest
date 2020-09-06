package com.neilas;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialistLogInScreen {

    public SpecialistLogInScreen(){

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        Connection con = DbConnect.connect();
        PreparedStatement psName = null;
        PreparedStatement psPassword = null;
        ResultSet rsName = null;
        ResultSet rsPassword = null;
        String specialistName = null;
        String specialistPassword = null;
        try {
            String sqlName = "SELECT name FROM users";
            String sqlPassword = "SELECT password FROM users";
            psName = con.prepareStatement(sqlName);
            psPassword = con.prepareStatement(sqlPassword);
            rsName = psName.executeQuery();
            rsPassword = psPassword.executeQuery();
            while (rsName.next()) {
                specialistName = rsName.getString("name");
                System.out.println(specialistName);
                specialistPassword = rsPassword.getString("password");
                System.out.println(specialistPassword);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                rsName.close();
                psName.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

        JLabel specialistLabel = new JLabel("Specialist");
        specialistLabel.setBounds(10, 20, 80,25);
        panel.add(specialistLabel);

        JTextField specialistText = new JTextField();
        specialistText.setBounds(100, 20 , 200, 45);
        panel.add(specialistText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80,25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 200, 45);
        panel.add(passwordText);

        JButton buttonLogIn = new JButton("Login");
        buttonLogIn.setBounds(75, 80, 200, 25);
        buttonLogIn.addActionListener(e -> {
            String specialist = specialistText.getText();
            String password = String.valueOf(passwordText.getPassword());

            if (specialist.equals("Test") && password.equals("123456")){
                    new ServiceDepartmentScreen();
                    frame.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null,"User Name or Password was incorrect");
            }
        });

        JButton buttonRegister = new JButton("Register");
        buttonRegister.setBounds(75, 80, 200, 25);
        buttonRegister.addActionListener(e -> {
            new SpecialistRegistration();
            frame.dispose();
        });

        panel.add(buttonLogIn);
        panel.add(buttonRegister);
        panel.setBorder(BorderFactory.createEmptyBorder(200,350,200,350));
        panel.setLayout(new GridLayout(0, 2));
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Log In");
        frame.pack();
        frame.setVisible(true);
    }

}
