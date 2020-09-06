package com.neilas;

import javax.swing.*;
import java.awt.*;

public class CustomerScreen {



    public CustomerScreen(){

        JFrame frame;
        frame = new JFrame();
        JPanel panel = new JPanel();
        JButton button = new JButton();


        JButton buttonRegister = new JButton("Book an appointment");
        buttonRegister.setBounds(25, 50, 80, 25);
        buttonRegister.addActionListener(e -> {
            new BookAnAppointment();
            frame.dispose();
        });

        JButton buttonCancel = new JButton("Cancel an appointment");
        buttonCancel.setBounds(40, 50, 80, 25);
        buttonCancel.addActionListener(e -> {
            new CancelVisit();
            frame.dispose();
        });



        panel.setBorder(BorderFactory.createEmptyBorder(250,300,250,300));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(buttonRegister);
        panel.add(buttonCancel);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Customer Screen");
        frame.pack();
        frame.setVisible(true);
    }

}
