package dk.weebsystems.helpdesk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow extends JFrame implements ActionListener {

    //defining stuff
    public JFrame frame = new JFrame();
    public JLabel label1 = new JLabel("Title");
    public JLabel label2 = new JLabel("Username");
    public JLabel label3 = new JLabel("Password");
    public JLabel label4 = new JLabel();
    public JTextField usernamefield = new JTextField();
    public JPasswordField passwordfield = new JPasswordField();
    public JPanel jpl = new JPanel();
    public JButton button1 = new JButton("login");
    public String usernames;
    public String passwords;
    public String receivedpassword;
    public Connection con;
    public Statement st;
    public ResultSet rs;

    public void window() {

        //window properties
        frame.setTitle("Help Desk Manager");
        frame.setSize(400, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        jpl.setLayout(null);

        //labels
        label1.setVerticalTextPosition(JLabel.TOP);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setText("Login");
        label1.setBounds(150, -100, 200, 300);
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
        label4.setVerticalTextPosition(JLabel.TOP);
        label4.setHorizontalTextPosition(JLabel.CENTER);
        label4.setBounds(125, 50, 200, 300);
        label4.setForeground (Color.red);
        label4.setFont(new Font(label1.getFont().getName(), label1.getFont().getStyle(), 9));
        label4.setPreferredSize(new Dimension(100, 70));
        //input field
        usernamefield.setBounds(130, 100, 120, 20);
        passwordfield.setBounds(130, 140, 120, 20);
        //button
        button1.setBounds(180, 170, 70, 20);
        button1.addActionListener(this);

        jpl.add(label1);
        jpl.add(usernamefield);
        jpl.add(passwordfield);
        jpl.add(button1);
        jpl.add(label2);
        jpl.add(label3);
        jpl.add(label4);
        frame.add(jpl);
        frame.validate();

    }


    public void actionPerformed(ActionEvent e) {


        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3IOCURI\\SQLEXPRESS:1433;DatabaseName=helpdesk;","sa", "kek1234");
            usernames = usernamefield.getText();
            char[] passwordchar = passwordfield.getPassword(); // getting password and putting it in to a char array
            passwords = String.valueOf(passwordchar); // making char into a string

            System.out.println(st);
            st = con.createStatement();

            String query = "SELECT * FROM dbo.users WHERE username = '"+usernames+"'";

            rs = st.executeQuery(query);

            while(rs.next()) {
                receivedpassword = rs.getString("password");

            }


        } catch (Exception ex) {
            System.out.println("error: " + ex);


            }


        try {
            if (passwords.equals(receivedpassword)) {
                System.out.println(usernames+ " logged in" );
                System.out.println("starting second frame..");
                frame.setVisible(false);
                frame.dispose();
                SearchWindow sf = new SearchWindow();
                sf.searchwindow();
                sf.frame1.setVisible(true);

                sf.st = st;

            } else {
                label4.setText("Wrong Username or Password.");

            }
        } catch (Exception ex) {
            System.out.println("Database server is down!");
        }

    }

}
