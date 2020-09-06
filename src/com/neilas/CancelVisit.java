package com.neilas;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CancelVisit {


    public static void Delete(String appointmentCode) {
        Connection con = DbConnect.connect();
        PreparedStatement ps = null;
        try{
            String sql = "DELETE from appointments WHERE appointmentCode = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,appointmentCode);
            ps.execute();
            System.out.println("appointment was deleted");
        }catch (Exception e) {
            System.out.println(e.toString() + "here");
        } finally {
            try {
                ps.close();
                con.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
      }

    }

    public CancelVisit() {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        JLabel name = new JLabel("Appointment Code");
        name.setBounds(10, 20, 80,25);
        panel.add(name);

        JTextField appointmentCodeText = new JTextField();
        appointmentCodeText.setBounds(100, 20 , 200, 45);
        panel.add(appointmentCodeText);

        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.setBounds(75, 80, 200, 25);
        buttonCancel.addActionListener(e -> {
            String appointmentCode = appointmentCodeText.getText();
            Delete(appointmentCode);
            JOptionPane.showMessageDialog(null,"Your appointment was canceled");
            //frame.dispose();
        });
        panel.add(buttonCancel);
        panel.setBorder(BorderFactory.createEmptyBorder(200,350,200,350));

        panel.setBorder(BorderFactory.createEmptyBorder(250,300,250,300));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(buttonCancel);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Customer Screen");
        frame.pack();
        frame.setVisible(true);
    }
}
