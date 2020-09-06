package com.neilas;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        DbConnect.connect();
        new Main();

    }
        public Main(){

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();


        panel.setBorder(BorderFactory.createEmptyBorder(250,300,250,300));
        panel.setLayout(new GridLayout(0, 1));

        JButton buttonCustomer = new JButton("Customer");
        buttonCustomer.setBounds(25, 50, 80, 25);
        buttonCustomer.addActionListener(e -> {
            new CustomerScreen();
            frame.dispose();
        });
        panel.add(buttonCustomer);

        JButton buttonSpecialist = new JButton("A Specialist");
        buttonSpecialist.setBounds(25, 35, 80, 25);
        buttonSpecialist.addActionListener(e -> {
            new SpecialistLogInScreen();
            frame.dispose();
        });
        panel.add(buttonSpecialist);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Main Page");
        frame.pack();
        frame.setVisible(true);
    }

}
