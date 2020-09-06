package com.neilas;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;


public class BookAnAppointment {

    private String days[]
            = { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31" };
    private String months[]
            = { "Jan", "feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sup", "Oct", "Nov", "Dec" };
    private String times[]
            = { "08:00", "08:30", "09:00",
                "09:30", "10:00", "10:30",
                "11:00", "11:30", "13:00",
                "13:30", "14:00", "14:30",
                "15:00", "15:30", "16:00",
                "16:30"};


    private static void Insert(String appointmentCode, String name, String month, String day, String time) {
        Connection con = DbConnect.connect();
        PreparedStatement ps = null;
        try{
            String sql = "INSERT INTO appointments(appointmentCode, name, month, day, time) VALUES(?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, appointmentCode);
            ps.setString(2, name);
            ps.setString(3, month);
            ps.setString(4, day);
            ps.setString(5, time);
            ps.execute();
            System.out.println("Data has been inserted!");
        } catch(SQLException e){
            System.out.println(e.toString());
        }
    }

    public BookAnAppointment(){

        Connection con = DbConnect.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String specialistName = null;
        try {
            String sql = "SELECT name FROM users";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                specialistName = rs.getString("name");
                System.out.println(specialistName);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

        JComboBox day = new JComboBox(days);
        JComboBox month = new JComboBox(months);
        JComboBox time = new JComboBox(times);
        //JComboBox specialist = new JComboBox((specialistName));
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        JLabel specialistLabel = new JLabel("Specialist");
        specialistLabel.setBounds(10, 20, 80,25);
        panel.add(specialistLabel);


        //specialist.addItem(specialistName);
       // panel.add(specialist);

        JLabel monthLabel = new JLabel("Month");
        monthLabel.setBounds(10, 20, 80,25);
        panel.add(monthLabel);

        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(60, 20);
        month.setLocation(250, 250);
        panel.add(month);

        JLabel dayLabel = new JLabel("Day");
        dayLabel.setBounds(10, 20, 80,25);
        panel.add(dayLabel);

        day.setFont(new Font("Arial", Font.PLAIN, 15));
        day.setSize(50, 20);
        day.setLocation(200, 250);
        panel.add(day);

        JLabel timeLabel = new JLabel("Time");
        timeLabel.setBounds(10, 20, 80,25);
        panel.add(timeLabel);

        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(60, 20);
        month.setLocation(250, 250);
        panel.add(time);

        JLabel name = new JLabel("Name");
        name.setBounds(10, 20, 80,25);
        panel.add(name);

        JTextField nameText = new JTextField();
        nameText.setBounds(100, 20 , 200, 45);
        panel.add(nameText);

        JButton buttonRegister = new JButton("Register");
        buttonRegister.setBounds(75, 80, 200, 25);
        buttonRegister.addActionListener(e -> {
            String appointmentCodeGenerated = generatedCode();
            String customerName = nameText.getText();
            String getMonth = month.getSelectedItem().toString();
            String getDay = day.getSelectedItem().toString();
            String getTime = time.getSelectedItem().toString();
            Insert(appointmentCodeGenerated,customerName, getMonth, getDay, getTime);
            JOptionPane.showMessageDialog(null,"Your appointment code: " + appointmentCodeGenerated);
            //frame.dispose();
        });


        panel.add(buttonRegister);
        panel.setBorder(BorderFactory.createEmptyBorder(200,350,200,350));
        panel.setLayout(new GridLayout(0, 2));
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Log In");
        frame.pack();
        frame.setVisible(true);
    }

    public String generatedCode() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        return generatedString;
    }


}