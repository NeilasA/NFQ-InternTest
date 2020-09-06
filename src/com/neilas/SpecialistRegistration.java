package com.neilas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialistRegistration{


    private static void Insert(String name, String password) {
        Connection con = DbConnect.connect();
        PreparedStatement ps = null;
        try{
            String sql = "INSERT INTO users(name, password) VALUES(?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.execute();
            System.out.println("user has been register");
        } catch(SQLException e){
            System.out.println(e.toString());
        }
    }


    public SpecialistRegistration(){
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

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

    JLabel repeatPasswordLabel = new JLabel("Repeat Password");
            repeatPasswordLabel.setBounds(10, 50, 80,25);
            panel.add(repeatPasswordLabel);

    JPasswordField repeatPasswordText = new JPasswordField();
            repeatPasswordText.setBounds(100, 50, 200, 45);
            panel.add(repeatPasswordText);

    JLabel accessCodeLabel = new JLabel("Access Code");
        accessCodeLabel.setBounds(10, 50, 80,25);
        panel.add(accessCodeLabel);

    JTextField accessCodeText = new JTextField();
        accessCodeText.setBounds(100, 50, 200, 45);
        panel.add(accessCodeText);

    JButton buttonLogIn = new JButton("Register");
        buttonLogIn.setBounds(75, 80, 200, 25);
        buttonLogIn.addActionListener(e -> {
            String specialist = specialistText.getText();
            String access = accessCodeText.getText();
            String password = String.valueOf(passwordText.getPassword());
            String repeatPassword = String.valueOf(repeatPasswordText.getPassword());
            if(access.equals("test")){
            if (password.equals(repeatPassword)) {
                new ServiceDepartmentScreen();
                Insert(specialist, password);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "User Name or Password was incorrect");
            }
            }else{
                JOptionPane.showMessageDialog(null, "Access code was incorrect");
            }
    });

    JButton buttonGoToLogIn = new JButton("Back");
        buttonGoToLogIn.setBounds(75, 80, 200, 25);
        buttonGoToLogIn.addActionListener(e -> {
        new SpecialistLogInScreen();
        frame.dispose();
    });

        panel.add(buttonLogIn);
        panel.add(buttonGoToLogIn);
        panel.setBorder(BorderFactory.createEmptyBorder(200,350,200,350));
        panel.setLayout(new GridLayout(0, 2));
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Log In");
        frame.pack();
        frame.setVisible(true);
}
}