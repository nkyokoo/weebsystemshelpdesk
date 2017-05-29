package dankclient;

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

public class window extends JFrame implements ActionListener {

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
    public Connection con;
    public Statement st;

    public void window() {

        //window properties
        frame.setTitle("Dank Client");
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

        //adding panel to the frame and adding objects to the panel itself
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

    //what happens when a user clicks on a button
    @Override
    public void actionPerformed(ActionEvent e) {
        //getting username and putting into a string
        usernames = usernamefield.getText();
        char[] passwordchar = passwordfield.getPassword(); // getting password and putting it in to a char array
        passwords = String.valueOf(passwordchar); // making char into a string
        if(usernames == "" && passwords  == ""){
            System.out.println("please type a password in!");
            
        }else{
        try {
            //calling the sql server driver for java
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //making a connection with username and password 
            con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3IOCURI\\SQLEXPRESS:1433;DatabaseName=dankmemes;", usernames, passwords);
            System.out.println("connecting...");
            System.out.println(con);
            //creating statment
            System.out.println(st);
            st = con.createStatement();

            //catching bad exceptions such as if the password is wrong, ect.  
        } catch (Exception ex) {
            System.out.println("error: " + ex);
            label4.setText("username or password is WRONG!");

            }
        }

        try {
            //checking if connection is not closed
            if (!st.isClosed()) {
                System.out.println("connected");
                System.out.println("starting second frame..");
                //sets the login frame to be invisible 
                frame.setVisible(false);
                //releasing the resources the login frame used.
                frame.dispose();
                //calling a new frame which is where the search takes place
                searchwindow sf = new searchwindow();
                sf.searchwindow();
                sf.frame1.setVisible(true);
                //remembering to give the new frame a juicy gift. the statement. 
                //this is important. if you dont parse the statement to the second frame, it will not work! 
                sf.st = st;

            } else {

                //what else todo? nothing really, this else isn't really needed.
            }
        } catch (Exception ex) {
            System.out.println("not connected! either server not running or wrong username/password!");
        }

    }

}
