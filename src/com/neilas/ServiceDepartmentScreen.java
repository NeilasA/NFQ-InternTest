package com.neilas;

import javax.swing.*;
import java.awt.*;

public class ServiceDepartmentScreen {

    public ServiceDepartmentScreen(){

        JFrame frame;
        frame = new JFrame();


        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(250,300,250,300));
        panel.setLayout(new GridLayout(0, 1));

        JButton buttonCustomer = new JButton("Hello Specialist");
        buttonCustomer.setBounds(25, 50, 80, 25);
        // buttonCustomer.addActionListener(new Main());
        panel.add(buttonCustomer);


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Service Department");
        frame.pack();
        frame.setVisible(true);
    }

}
