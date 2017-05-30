package dk.weebsystems.helpdesk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisterWindow extends JFrame implements ActionListener {

    //defining stuff
    public JFrame frame2 = new JFrame();
    public JLabel label1 = new JLabel("Title");
    public JLabel label2 = new JLabel("Username");
    public JLabel label3 = new JLabel("Password");
    public JLabel label5 = new JLabel("Email");
    public JLabel label4 = new JLabel();
    public JTextField usernamefield = new JTextField();
    public JPasswordField passwordfield = new JPasswordField();
    public JTextField emailfield = new JTextField();
    public JPanel jpl = new JPanel();
    public JButton button1 = new JButton("register");
    public String usernames;
    public String passwords;
    public String receivedpassword;
    public Connection con;
    public Statement st;
    public ResultSet rs;
    public String emails;
    public void registerwindow() {

        //window properties
        frame2.setTitle("Help Desk Manager");
        frame2.setSize(400, 400);
        frame2.setResizable(false);
        frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame2.setVisible(false);
        jpl.setLayout(null);

        //labels
        label1.setVerticalTextPosition(JLabel.TOP);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setText("Register Agent");
        label1.setBounds(100, -100, 400, 300);
        label1.setFont(new Font(label1.getFont().getName(), label1.getFont().getStyle(), 30));
        label1.setPreferredSize(new Dimension(100, 70));
        label2.setVerticalTextPosition(JLabel.TOP);
        label2.setHorizontalTextPosition(JLabel.CENTER);
        label2.setText("Username:  ");
        //defining position of the object
        //this is called absolute positioning which people normally don't recommend
        label2.setBounds(130, -60, 200, 300);
        label2.setFont(new Font(label1.getFont().getName(), label1.getFont().getStyle(), 11));
        label3.setVerticalTextPosition(JLabel.TOP);
        label3.setHorizontalTextPosition(JLabel.CENTER);
        label3.setText("Password:  ");
        label3.setBounds(130, -20, 200, 300);
        label3.setFont(new Font(label1.getFont().getName(), label1.getFont().getStyle(), 11));
        label5.setVerticalTextPosition(JLabel.TOP);
        label5.setHorizontalTextPosition(JLabel.CENTER);
        label5.setText("Email:  ");
        label5.setBounds(130, 20, 200, 300);
        label5.setFont(new Font(label1.getFont().getName(), label1.getFont().getStyle(), 11));
        label4.setVerticalTextPosition(JLabel.TOP);
        label4.setHorizontalTextPosition(JLabel.CENTER);
        label4.setBounds(125, 60, 200, 300);
        label4.setForeground (Color.red);
        label4.setFont(new Font(label1.getFont().getName(), label1.getFont().getStyle(), 9));
        label4.setPreferredSize(new Dimension(100, 70));

        //input field
        usernamefield.setBounds(130, 100, 120, 20);
        passwordfield.setBounds(130, 140, 120, 20);
        emailfield.setBounds(130, 180, 120, 20);
        //button
        //button
        button1.setBounds(180, 210, 80, 20);
        button1.addActionListener(this);

        jpl.add(label1);
        jpl.add(usernamefield);
        jpl.add(passwordfield);
        jpl.add(emailfield);
        jpl.add(button1);
        jpl.add(label2);
        jpl.add(label3);
        jpl.add(label4);
        jpl.add(label5);
        frame2.add(jpl);
        frame2.validate();

    }


    public void actionPerformed(ActionEvent e) {


        try {


            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost/helpdesk?user=root");


            System.out.println(st);
            st = con.createStatement();

        } catch (Exception ex) {
            System.out.println("error: " + ex);


            }

        usernames = usernamefield.getText();
        char[] passwordchar = passwordfield.getPassword();
        passwords = String.valueOf(passwordchar);
        emails = emailfield.getText();
        System.out.println(usernames+" "+emails);
        try {
            if (!passwords.equals("") || !usernames.equals("") ||  !passwords.equals("")) {

                String query = "INSERT INTO `agents`(`username`, `password`, `email`) VALUES ('"+usernames+"','"+passwords+"','"+emails+"')";

                st.execute(query);
                con.close();
                frame2.setVisible(false);
                frame2.dispose();
                LoginWindow lf = new LoginWindow();
                lf.window();
                lf.frame.setVisible(true);


            } else {
                JOptionPane.showMessageDialog(frame2,
                        "Username, password, or email CANNOT be empty.",
                        "error",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
